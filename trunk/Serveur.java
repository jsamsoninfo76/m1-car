import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ServerSocket ss;
	private String message;
	private DataOutputStream dos;
	
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
			out = new ObjectOutputStream(client.getOutputStream());
			dos = new DataOutputStream(out);
			in = new ObjectInputStream(client.getInputStream());
			sendMessage("220 Bienvenue\n");
			
			//4. The two parts communicate via the input and output streams
			do{
				try{
					message = (String)in.readObject();
					System.out.println("client>" + message);
					if (message.equals("bye"))
						sendMessage("bye");
				}
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
				}
			}while(!message.equals("bye"));
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	public void sendMessage(String msg)
	{
		try{
			dos.writeBytes(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		Serveur s = new Serveur();
		//while(true){
			s.run();
		//}
	}
}
