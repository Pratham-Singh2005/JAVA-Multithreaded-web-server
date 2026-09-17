# AsyncJavaServer: Multithreaded Java Socket Server

A Lightweight, Highly Concurrent Java Socket Server.

## 🚀 The Pitch
Welcome to a streamlined, multithreaded socket server built in pure Java! Originally designed as a straightforward single-threaded application, this project has been upgraded to handle concurrent client connections effortlessly. By instantly dispatching incoming TCP connections to a Fixed Thread Pool, the server remains unblocked and responsive at all times—meaning no client has to wait in line while another is being served.

Whether you're looking to master the fundamentals of network programming, exploring Java's multithreading capabilities, or simply needing a reliable boilerplate for your own scalable networking applications, this project delivers a clean, minimal, and highly educational foundation.

## ✨ Key Features
- **Concurrent Processing:** Uses Java's `ExecutorService` to handle incoming socket connections, serving multiple clients simultaneously without exhausting system memory.
- **Minimalist & Clean:** Written using core Java network I/O (`ServerSocket`, `BufferedReader`, `PrintWriter`) without the bloat of heavy frameworks.
- **Highly Extensible:** The perfect starting point for building chat servers, multiplayer game backends, or your own custom API protocols.

## 🛠️ How to Run Locally

1. **Compile the code:**
   ```bash
   javac Server.java Client.java
   ```

2. **Package as a JAR (Deployment Ready):**
   ```bash
   jar cfe server.jar Server Server.class
   ```

3. **Start the Server:**
   You can now run the packaged server. It defaults to port `8010`, but you can easily change it:
   ```bash
   # Run on default port 8010
   java -jar server.jar
   
   # Or run on a custom port using an argument:
   java -jar server.jar 9090
   ```

4. **Start the Client (or multiple clients!):**
   Open a new terminal window and run:
   ```bash
   java Client
   ```
   *You can run this command in as many terminals as you want to simulate multiple concurrent connections.*

## 🧠 What I Learned & Project Highlights
Building this project solidified my understanding of:
- **Thread Pools (`ExecutorService`):** Managing threads efficiently to prevent OutOfMemory errors and handle concurrent clients smoothly.
- **TCP Sockets:** Establishing reliable, persistent two-way communication between clients and servers.
- **Blocking vs Non-Blocking I/O:** Understanding why single-threaded servers block and how to resolve it using concurrency.
- **DevOps & Deployment:** Packaging Java apps via Maven, deploying to AWS EC2, configuring Linux `systemd` services, and automating CI/CD pipelines via GitHub Actions.
