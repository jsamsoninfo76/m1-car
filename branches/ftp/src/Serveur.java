import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	private ServerSocket ss;
	private String message;
	
	public Serveur(){}
	
	public void run(){
		try{
			//1. creating a server socket
			ss = new ServerSocket(7000);
			
			//2. Wait for connection
			System.out.println("Waiting for connection");
			Socket client = ss.accept();
			System.out.println("Connection received");
			
			//3. get Input and Output streams
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			dos.writeBytes("220 Hello\n");
			System.out.println("server>220 Hello\n");
			
			//4. The two parts communicate via the input and output streams
//			do{
//				try{
//					message = (String)in.readObject();
//					System.out.println("client>" + message);
//					if (message.equals("bye"))
//						dos.writeBytes(  "bye");
//				}
//				catch(ClassNotFoundException classnot){
//					System.err.println("Data received in unknown format");
//				}
//			}while(!message.equals("bye"));
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		Serveur s = new Serveur();
		s.run();
	}
}
