import java.net.Socket;

public class HttpContext {
    // Attribut de la classe HttpContext
    private HttpRequest request;
    private HttpResponse response;
    private Socket socket;

    // Constructeur de la classe HttpContext
    HttpContext(Socket socket) {
        this.socket = socket;
        request = new HttpRequest(socket);
        response = new HttpResponse(socket);
    }

    // Méthode de la classe HttpContext
    public HttpRequest getRequest() {
        return request;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
