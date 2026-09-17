import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{

    public void run() throws IOException {
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        // Thread pool with up to 10 concurrent threads
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        
        System.out.println("Server is listening on port " + port);

        while (true) {
            Socket acceptedConnection = serverSocket.accept();

        System.out.println("Connection accepted from client: "
                + acceptedConnection.getRemoteSocketAddress());

        threadPool.execute(() -> {
            try {
                PrintWriter toClient =
                        new PrintWriter(acceptedConnection.getOutputStream(), true);

                BufferedReader fromClient =
                        new BufferedReader(
                                new InputStreamReader(acceptedConnection.getInputStream()));

                String msg = fromClient.readLine();
                System.out.println("Client says: " + msg);

                toClient.println("Hello from server");

                acceptedConnection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}



    public static void main(String[] arg){
        Server server = new Server();
        try{
            server.run();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }


    }
}