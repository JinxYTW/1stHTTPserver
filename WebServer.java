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

                HttpContext context = new HttpContext(clientSocket);
                System.out.println("Created context");

                HttpRequest request = context.getRequest();
                System.out.println("Got request");

                HttpResponse response = context.getResponse();
                System.out.println("Got response");

                String method = request.getMethod();

                String url = request.getUrl();
                System.out.println("Method: " + method);
                System.out.println("URL: " + url);
                //
                if (method.equals("GET") && url.equals("/")) {
                    response.ok("Hello, World!");
                } else {
                    response.notFound("Not found");
                }
                //
                
                System.out.println("Sent response");
                
                context.close();
                System.out.println("Closed context");
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
