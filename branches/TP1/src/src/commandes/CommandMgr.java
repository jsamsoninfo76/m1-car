package commandes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CommandMgr {
	protected Recepteur recepteur;
	protected OutputStream outputStreamDonnee;
	protected OutputStream outputStreamControl;
	public ServerSocket serverSocketDonnee;
//	protected ServerSocket serverSocketControl;
	protected Socket socketDonnee;
	protected Socket socketControl;
	protected DataOutputStream dataOutputStreamControl;
	protected DataOutputStream dataOutputStreamDonnee;
	protected String directory;
	public String reponse;
	protected String password;
	protected String user;
	protected int port = 7000;
	protected int numeroSocket;
	protected int portPasv;
	protected InputStream inputStream;
	protected InputStreamReader inputStreamReader;
	public BufferedReader bufferedReader;
	private LinkedList commandHistory = new LinkedList<Command>();
	protected Map<String, String> userPass ;
	
	/* Init methodes */
	public void initialisationDeControl() throws IOException{
		outputStreamControl     = socketControl.getOutputStream();
		dataOutputStreamControl = new DataOutputStream(outputStreamControl);
		inputStream             = socketControl.getInputStream();
		inputStreamReader       = new InputStreamReader(inputStream);
		bufferedReader          = new BufferedReader(inputStreamReader);
		recepteur 				= new Recepteur();
	}

	public void initialisationDeDonne() throws IOException{
		serverSocketDonnee 		= null; 
		socketDonnee       		= null;
		outputStreamDonnee  	= null;
		dataOutputStreamDonnee 	= null;
		reponse                	= null;
		directory      			= "/Users/Pom/ftp/";
		
		userPass = new HashMap<String, String>() ;
		userPass.put("anonymous", "anonymous" );
		userPass.put("pom", "pom") ;
		userPass.put("jerem", "jerem") ;
	}
	
	public void recordHistory(Command command){
		commandHistory.addFirst(command);
	}
	
	public void printHistory(CommandMgr commandMgr){
		System.out.println(commandMgr.commandHistory.toString());
	}
	
	/* Setters */
	public void setNumeroSocket(int numeroSocket){
		this.numeroSocket = numeroSocket;
	}
	public void setPort(int port){
		this.port = port;
	}
	public void setPortPasv(int portPasv){
		this.portPasv = portPasv;
	}
	public void setSocketControl(Socket socketControl){
		this.socketControl = socketControl;
	}
/*	public void setServerSocketControl(ServerSocket serverSocketControl){
		this.serverSocketControl = serverSocketControl;
	}*/
	public void setReponse(String reponse){
		this.reponse = reponse;
	}
	
	/* Getters */
	public String getReponse(){ 
		return this.reponse;
	}
	public BufferedReader getBufferedReader(){
		return bufferedReader;
	}
	
	public void closeSocket() throws IOException{
		socketControl.close() ;
	}
	
	/* Init The Server*/
	public void init() {
		try {
			initialisationDeControl();
			initialisationDeDonne();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
