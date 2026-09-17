import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public void run(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        // JVM Shutdown Hook for graceful termination
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Initiating graceful shutdown...");
            try {
                serverSocket.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error closing server socket", e);
            }
            threadPool.shutdown();
            try {
                if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                    threadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                threadPool.shutdownNow();
            }
            logger.info("Server shutdown complete.");
        }));

        logger.info("Server is listening on port " + port);

        while (true) {
            try {
                Socket acceptedConnection = serverSocket.accept();
                logger.info("Connection accepted from client: " + acceptedConnection.getRemoteSocketAddress());

                threadPool.execute(() -> {
                    try {
                        PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream(), true);
                        BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                        String msg = fromClient.readLine();
                        logger.info("Client says: " + msg);

                        toClient.println("Hello from server");

                        acceptedConnection.close();
                    } catch (IOException e) {
                        logger.log(Level.SEVERE, "Error handling client connection", e);
                    }
                });
            } catch (IOException e) {
                if (serverSocket.isClosed()) {
                    logger.info("Server socket was closed. Stopping accept loop.");
                    break;
                }
                logger.log(Level.SEVERE, "Error accepting connection", e);
            }
        }
    }

    public static void main(String[] args) {
        int port = 8010; // Default port
        
        // 1. Check command line argument
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                logger.warning("Invalid port in arguments, falling back to " + port);
            }
        } else {
            // 2. Check environment variable
            String envPort = System.getenv("PORT");
            if (envPort != null && !envPort.isEmpty()) {
                try {
                    port = Integer.parseInt(envPort);
                } catch (NumberFormatException e) {
                    logger.warning("Invalid PORT environment variable, falling back to " + port);
                }
            }
        }

        Server server = new Server();
        try {
            server.run(port);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Server encountered a fatal error", ex);
        }
    }
}