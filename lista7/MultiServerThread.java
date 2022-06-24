import java.io.*;
import java.net.*;
 
/**
 * function defining the server hosting multiple threads
 */
public class MultiServerThread {
    /**
     * main function
     * @param args
     */
    public static void main(String[] args) {
        //server takes up port #3000
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            System.out.println("Server is listening on port 3000");
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                System.out.println("New client connected");
                //server starts a new thread once a client is connected
                new TreeThread(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}