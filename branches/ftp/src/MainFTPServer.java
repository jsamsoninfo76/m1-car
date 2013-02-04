import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainFTPServer {

    public static void main(String[] args) throws IOException {
        // Cr�ation socket de control
        ServerSocket ssc = new ServerSocket(8004);
        
        // Cr�ation du compteur de socket 
        int numSc = 0;
        
        // Cr�ation du serveur socket
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