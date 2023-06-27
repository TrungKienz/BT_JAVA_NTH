import java.io.*;
import java.net.Socket;

public class FileTransferClient {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java FileTransferClient <server-address> <port-number> <file-to-send>");
            return;
        }

        String serverAddress = args[0];
        int port = Integer.parseInt(args[1]);
        String fileName = args[2];

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());

            // Open input stream to read the file
            FileInputStream fis = new FileInputStream(fileName);

            // Open output stream to send the file
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Send the file name
            dos.writeUTF(fileName);

            // Read and send the file data
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }

            // Close streams and socket
            fis.close();
            dos.close();
            socket.close();

            System.out.println("File sent: " + fileName);
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
