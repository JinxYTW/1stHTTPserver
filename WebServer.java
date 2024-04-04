import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    // Attribut de la classe WebServer

    // Constructeur de la classe WebServer

    // Méthode de la classe WebServer

    public void run(int portNumber) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection");

               new RequestProcessor(clientSocket);
            }
        }

        catch (IOException e) {
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
