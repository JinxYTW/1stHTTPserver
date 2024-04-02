public class WebServerApplication extends WebServer{
    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.run(80);
    }
}