import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainFTPServer {

    public static void main(String[] args) throws IOException {
        // Création socket de control
        ServerSocket ssc = new ServerSocket(8004);
        
        // Création du compteur de socket 
        int numSc = 0;
        
        // Création du serveur socket
        try {
            while (true)
			{
            	numSc++;
            	Socket sc = ssc.accept();
			    FTPServer ftp = new FTPServer(numSc,ssc,sc);
				ftp.start();
				System.out.println("Server out ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}