import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileTransferServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileTransferServer <port-number>");
            return;
        }

        int port = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started and listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Start a new thread to handle the client connection
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
                // Open input stream to receive the file
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

                // Read the file name
                String fileName = dis.readUTF();

                // Create a new file output stream
                FileOutputStream fos = new FileOutputStream(fileName);

                // Read and write the file data
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = dis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                // Close streams and sockets
                fos.close();
                dis.close();
                clientSocket.close();

                System.out.println("File received: " + fileName);
            } catch (IOException e) {
                System.out.println("Client handler exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
