import java.net.Socket;
import java.io.File;

public class RequestProcessor {
    // Attribut de la classe RequestProcessor
    private HttpContext context;

    // Constructeur de la classe RequestProcessor
    RequestProcessor(Socket socket) {
        System.out.println("Starting RequestProcessor constructor");
        context = new HttpContext(socket);
        process();
        System.out.println("Ending RequestProcessor constructor");
    }

    // Méthode de la classe RequestProcessor
    private void process() {
        try{
        

        HttpRequest request = context.getRequest();
        System.out.println("Got request");

        HttpResponse response = context.getResponse();
        System.out.println("Got response");

String method = request.getMethod();

String url = request.getUrl();
System.out.println("Method: " + method);
System.out.println("URL: " + url);
System.out.println("Processing request for URL: " + request.getUrl());

// Default to index.html for root URL
if ("/".equals(url)) {
    url = "/index.html";
}

// Remove the leading slash from the URL to get the filename
String filename = url.substring(1);

// Check if the file exists in the public folder
File file = new File("public/" + filename);
if (file.exists()) {
    System.out.println("Sending OK response");
    response.sendFile(filename);
} else {
    System.out.println("Sending Not Found response");
    response.notFound("La page demandee n'existe pas");
}
System.out.println("Sent response");

} finally {
    context.close();
System.out.println("Closed context");
}
    }
}
