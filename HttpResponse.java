import java.io.IOException;
import java.net.Socket;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.OutputStream;


public class HttpResponse {
    // Attribut de la classe HttpResponse
    private OutputStream output;

    // Constructeur de la classe HttpResponse
    HttpResponse(Socket socket) {
        try {
            output = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    // Méthode de la classe HttpResponse
    public void ok(String message) {

        try {
            output.write("HTTP/1.1 404 Not Found\r\n".getBytes());
            output.write("Content-Type: text/plain\r\n".getBytes());
            output.write("\r\n".getBytes());
            output.write(message.getBytes());
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notFound(String message) {
        try {
            output.write("HTTP/1.1 200 OK\r\n".getBytes());
            output.write("Content-Type: text/plain\r\n".getBytes());
            output.write("\r\n".getBytes());
            output.write(message.getBytes());
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void sendContent(String contentType, String content) {
        try {
            output.write("HTTP/1.1 200 OK\r\n".getBytes());
            
            output.write(("Content-Type: " + contentType + "\r\n").getBytes());
            output.write("\r\n".getBytes());

            output.write(content.getBytes());
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String filename) {
        try {
            File file = new File("public/" + filename);
    
            if (!file.exists()) {
                output.write("HTTP/1.1 404 Not Found\r\n".getBytes());
                output.write("\r\n".getBytes());
                output.write("File not found: ".getBytes());
                output.write(filename.getBytes());
                output.flush();
                return;
            }
    
            String contentType;
            if (filename.endsWith(".html")) {
                contentType = "text/html";
            } else if (filename.endsWith(".css")) {
                contentType = "text/css";
            } else if (filename.endsWith(".png")) {
                contentType = "image/png";
            } else {
                contentType = "application/octet-stream";
            }
    
            output.write("HTTP/1.1 200 OK\r\n".getBytes());
            output.write(("Content-Type: " + contentType + "\r\n").getBytes());
            output.write("\r\n".getBytes());
    
            FileInputStream input = new FileInputStream(file);
    
            byte[] bytes = new byte[4096];
            int byteRead;
            while ((byteRead = input.read(bytes)) != -1) {
                output.write(bytes, 0, byteRead);
            }
    
            input.close();
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
