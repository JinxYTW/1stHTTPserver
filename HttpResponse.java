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

    public void sendFile(String contentType, String filename) {
        try {
            output.write("HTTP/1.1 200 OK\r\n".getBytes());
            output.write(("Content-Type: " + contentType + "\r\n").getBytes());
            output.write("\r\n".getBytes());

            File file = new File(filename);

            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filename);
            }

            FileInputStream input = new FileInputStream(file);

            byte[] bytes = new byte[4096];
            int byteRead = 0;
            while ((byteRead = input.read(bytes)) != -1) {
                output.write(bytes, 0, byteRead);

                input.close();

                output.flush();
                output.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
