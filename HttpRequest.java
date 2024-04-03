import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpRequest {
    // Attribut de la classe HttpRequest
    private String method;
    private String url;

    // Constructeur de la classe HttpRequest
    HttpRequest(Socket socket) {
        readClientRequest(socket);
    }

    // Méthode de la classe HttpRequest
    private void readClientRequest(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine(); 
            if (response != null) {
                String[] parts = response.split(" "); // Split la chaine de caractère response en fonction des espaces
                if (parts.length >= 2) {
                    method = parts[0];
                    url = parts[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
