import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HttpResponse {
    // Attribut de la classe HttpResponse
    private BufferedWriter output;

    // Constructeur de la classe HttpResponse
    HttpResponse(Socket socket) {
        try {
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // Méthode de la classe HttpResponse
    public void ok(String message) {

        try {
            String response = message;
            output.write("HTTP/1.1 200 OK\r\n" +response);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notFound(String message) {
        try {
            String response = message;
            output.write("HTTP/1.1 404 Not Found\r\n" +response);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
