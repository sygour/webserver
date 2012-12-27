package simple.project;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

	public static void main(String... args) throws IOException {
        String port = System.getenv("PORT");
        if (port == null) {
        	System.out.println("Environment variable not set, reading java property...");
        	port = System.getProperty("PORT");
        }
        if (port == null) {
        	System.out.println("Java property not set... error...");
        	throw new IOException("Port not defined for the server");
        }
		HttpServer server = HttpServer.create(new InetSocketAddress(Integer.parseInt(port)), 0);
        server.createContext("/", new HttpHandler() {
        	public void handle(HttpExchange t) throws IOException {
        		t.sendResponseHeaders(200, 0);
                OutputStream out = t.getResponseBody();
                out.write("hello".getBytes());
                out.close();
        	}
        });
        server.start();
        System.out.println("Server started on port " + port);
	}
}