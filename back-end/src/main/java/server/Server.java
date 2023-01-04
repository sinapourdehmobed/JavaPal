package server;

import handler.BaseHandler;
import handler.HandlerFactory;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import request.CustomParser;
import request.ParsedRequest;

public class Server {

  public static void main(String[] args) {
    ServerSocket ding;
    Socket dong = null;
    try {
      ding = new ServerSocket(1299);
      System.out.println("Opened socket " + 1299);
      while (true) {
        // keeps listening for new clients, one at a time
        try {
          dong = ding.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = dong.getInputStream();
        byte[] b = new byte[1024*20];
        stream.read(b);
        String input = new String(b).trim();
        System.out.println(input);

        BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // HTTP Response
        if(!input.isEmpty()){
          writer.println(processRequest(input));
        }else{
          writer.println("HTTP/1.1 200 OK");
          writer.println("Server: TEST");
          writer.println("Connection: close");
          writer.println("Content-type: text/html");
          writer.println("");
        }

        dong.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }

  public static String processRequest(String requestString){
    ParsedRequest request = CustomParser.parse(requestString);
    BaseHandler handler = HandlerFactory.getHandler(request);
    return handler.handleRequest(request);
  }

}
