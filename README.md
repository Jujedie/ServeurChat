
# ServeurChat

A code written in Java allowing you to execute a chat room between multiple clients on a server.
This branch aim to allow the user to implement that program into a software, thus making an 
in software chat room, it will allow through methods and function the user to from the 
software send and recieve message from a chat room.

## How to Run

1. **Compile the Server:**
   ```bash
   javac serveurs/*.java
   ```

2. **Execute the Server:**
   ```bash
   java serveurs/serveurComplex <port>
   ```

3. **Compile the Client:**
   ```bash
   javac clients/ClientComplex.java
   ```

4. **Execute the Client:**
   ```bash
   java clients/ClientComplex <hostname> <port>
   ```

Repeat steps 3 and 4 for each client that wants to join the chat room.

## Requirements

- Java Development Kit (JDK) installed
- A terminal or command prompt

## Notes

- Ensure that the server is running before starting any clients.
- The clients must connect to the server using the same IP address and port.
