import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServercopy {
    // Attribut de la classe WebServer

    // Constructeur de la classe WebServer

    // Méthode de la classe WebServer
    private void readRequest(Socket socket) {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println("Received: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(Socket socket) {
        try {
            OutputStream output = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            String response = "HTTP/1.1 200 OK\r\n\r\n";
            writer.write(response);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(int portNumber) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Accepted connection");
                readRequest(socket);
                System.out.println("Read request");
                sendResponse(socket);
                System.out.println("Sent response");
                socket.close();
                System.out.println("Closed socket");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}