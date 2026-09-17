# AsyncJavaServer: Multithreaded Java Socket Server

A Lightweight, Highly Concurrent Java Socket Server.

## 🚀 The Pitch
Welcome to a streamlined, multithreaded socket server built in pure Java! Originally designed as a straightforward single-threaded application, this project has been upgraded to handle concurrent client connections effortlessly. By instantly dispatching incoming TCP connections to a Fixed Thread Pool, the server remains unblocked and responsive at all times—meaning no client has to wait in line while another is being served.

Whether you're looking to master the fundamentals of network programming, exploring Java's multithreading capabilities, or simply needing a reliable boilerplate for your own scalable networking applications, this project delivers a clean, minimal, and highly educational foundation.

## ✨ Key Features
- **Concurrent Processing:** Uses Java's `ExecutorService` to handle incoming socket connections, serving multiple clients simultaneously without exhausting system memory.
- **Minimalist & Clean:** Written using core Java network I/O (`ServerSocket`, `BufferedReader`, `PrintWriter`) without the bloat of heavy frameworks.
- **Highly Extensible:** The perfect starting point for building chat servers, multiplayer game backends, or your own custom API protocols.

## 🛠️ How to Run

1. **Compile the code:**
   ```bash
   javac Server.java Client.java
   ```

2. **Start the Server:**
   Open a terminal and run:
   ```bash
   java Server
   ```
   *The server will start listening for connections on port 8010.*

3. **Start the Client (or multiple clients!):**
   Open a new terminal window and run:
   ```bash
   java Client
   ```
   *You can run this command in as many terminals as you want to simulate multiple concurrent connections.*

## 🧠 What I Learned
Building this project solidified my understanding of:
- **Thread Pools (`ExecutorService`):** Managing threads efficiently to prevent OutOfMemory errors.
- **TCP Sockets:** Establishing reliable, persistent two-way communication between clients and servers.
- **Blocking vs Non-Blocking I/O:** Understanding why single-threaded servers block and how to resolve it using concurrency.
