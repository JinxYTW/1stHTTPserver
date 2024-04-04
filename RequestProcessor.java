import java.net.Socket;

public class RequestProcessor {
    // Attribut de la classe RequestProcessor
    private HttpContext context;

    // Constructeur de la classe RequestProcessor
    RequestProcessor(Socket socket) {
        System.out.println("Starting RequestProcessor constructor");
        context = new HttpContext(socket);
        process();
        context.close();
        System.out.println("Closed context");
        System.out.println("Ending RequestProcessor constructor");
    }

    // Méthode de la classe RequestProcessor
    private void process() {
        

        HttpRequest request = context.getRequest();
        System.out.println("Got request");

        HttpResponse response = context.getResponse();
        System.out.println("Got response");

        String method = request.getMethod();

        String url = request.getUrl();
        System.out.println("Method: " + method);
        System.out.println("URL: " + url);
        System.out.println("Processing request for URL: " + request.getUrl());
        if ("/".equals(request.getUrl())) {
            System.out.println("Sending OK response");
            response.ok("Get Jinxed !");
        } else {
            System.out.println("Sending Not Found response");
            response.notFound("La page demandée n'existe pas");
        }
        System.out.println("Sent response");
    }
}
