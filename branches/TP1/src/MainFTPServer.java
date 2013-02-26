import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe MainFTPServer correspondant au main qui initialise le serveur
 * @author Jeremie Samson - Victor Paumier 
 */

public class MainFTPServer {
    public static void main(String[] args) throws IOException {
        int numeroSocket = 0;
        int port = 2121;
        int maxConnections = 0 ;
        
     // Création socket de control
        
        System.out.println("----- FTP-SERVER---- -----");
        System.out.println("****************************");
        System.out.println("Server Started...");
        System.out.println("Listen on " + port);
        System.out.println("Waiting for connections...");
        System.out.println("-");
        
        try{
            ServerSocket listener = new ServerSocket(port);
            Socket server;

            while((numeroSocket++ < maxConnections) || (maxConnections == 0)){

              server = listener.accept();
              FtpRequest conn_c= new FtpRequest(server, port, numeroSocket);
              Thread t = new Thread(conn_c);
              t.start();
            }
          } catch (IOException ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
          }
    }
}