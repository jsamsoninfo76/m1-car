package commandes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Classe CommandMgr gere les donnees de la connexion
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandMgr {
	/**
	 * Permet de confirmer que la commande a ete effectue
	 */
	protected Recepteur recepteur;
	
	/**
	 * Permet l'echange avec le serveur
	 */
	protected OutputStream outputStreamDonnee;
	
	/**
	 * Permet de gerer la connexion
	 */
	protected OutputStream outputStreamControl;
	
	/**
	 * Permet l'echange avec le serveur
	 */
	public ServerSocket serverSocketDonnee;
	
/*	/**
	 * Permet de gerer la connexion
	 
	protected ServerSocket serverSocketControl;
*/
	/**
	 * Permet l'echange avec le serveur
	 */
	protected Socket socketDonnee;
	
	/**
	 * Permet de gerer la connexion
	 */
	protected Socket socketControl;
	
	/**
	 * Permet de gerer la connexion
	 */
	protected DataOutputStream dataOutputStreamControl;
	
	/**
	 * Permet l'echange avec le serveur
	 */
	protected DataOutputStream dataOutputStreamDonnee;
	
	/**
	 * Repertoire courant
	 */
	protected File directory;
	
	/**
	 * Reponse du client
	 */
	public String[] reponse;
	
	/**
	 * Mot de passe utilise pour la connexion
	 */
	protected String password;
	
	/**
	 * Nom d'utilisateur utilise pour la connexion
	 */
	protected String user;
	
	/**
	 * Numero du port utilise pour la connexion
	 */
	protected int port = 7000;
	
	/**
	 * Numero d'instance de la connexion
	 */
	protected int numeroSocket;
	
	/**
	 * Numero du port passif utilise pour la connexion
	 */
	protected int portPasv;
	
	/**
	 * Permet de recuperer les dialogues entre le client et le serveur
	 */
	protected InputStream inputStream;
	
	/**
	 * Permet de recuperer les dialogues entre le client et le serveur
	 */
	protected InputStreamReader inputStreamReader;
	
	/**
	 * Permet de recuperer les dialogues entre le client et le serveur
	 */
	public BufferedReader bufferedReader;
	
	/**
	 * Contient les valeurs utilisateur et mot de passe accepte pour la connexion
	 */
	protected Map<String, String> userPass ;
	
	/**
	 * Contient l'historique des commandes utilisees
	 */
	private LinkedList commandHistory = new LinkedList<Command>();
	
	/**
	 * Initialise les outils de control du serveur
	 * 
	 */
	public void initialisationDeControl() throws IOException{
		outputStreamControl     = socketControl.getOutputStream();
		dataOutputStreamControl = new DataOutputStream(outputStreamControl);
		inputStream             = socketControl.getInputStream();
		inputStreamReader       = new InputStreamReader(inputStream);
		bufferedReader          = new BufferedReader(inputStreamReader);
		recepteur 				= new Recepteur();
	}

	/**
	 * Initialise les outils de donnee du donnee du serveur
	 *
	 */
	public void initialisationDeDonne() throws IOException{
		serverSocketDonnee 		= null; 
		socketDonnee       		= null;
		outputStreamDonnee  	= null;
		dataOutputStreamDonnee 	= null;
		reponse                	= null;
		directory      	        = new File("").getAbsoluteFile();
		
		 userPass = new HashMap<String, String>() ;
         userPass.put("anonymous", "anonymous" );
         userPass.put("pom", "pom") ;
         userPass.put("je", "je") ;
	}
	
	/**
	 * Ajoute la commande a la linkedList historique
	 * @param command Commande executee
	 */
	public void recordHistory(Command command){
		commandHistory.addFirst(command);
	}
	
	/**
	 * Affiche l'historique des commandes
	 * 
	 */
	public void printHistory(){
		System.out.println(commandHistory.toString());
	}
	
	/**
	 * Change le numero d'instance de la connexion
	 * @param numeroSocket Numero de l'instance
	 */
	public void setNumeroSocket(int numeroSocket){
		this.numeroSocket = numeroSocket;
	}
	
	/**
	 * Change le numero du port utilise pour la connexion
	 * @param port Numero du port 
	 */
	public void setPort(int port){
		this.port = port;
	}
	
	/**
	 * Change le numero du port passif utilise pour la connexion
	 * @param port Numero du port passif 
	 */
	public void setPortPasv(int portPasv){
		this.portPasv = portPasv;
	}
	
	/**
	 * Change socket de connection du serveur
	 * @param port Socket de connection
	 */
	public void setSocketControl(Socket socketControl){
		this.socketControl = socketControl;
	}
	
/*	/**
	 * 
	 * @param serverSocketControl 
	 
	public void setServerSocketControl(ServerSocket serverSocketControl){
		this.serverSocketControl = serverSocketControl;
	}
	*/
	
	/**
	 * Change la Reponse du client
	 * @param reponse Reponse
	 */
	public void setReponse(String[] reponse){
		this.reponse = reponse;
	}
	
	/**
	 * Retourne la reponse
	 * 
	 */
	public String[] getReponse(){ 
		return this.reponse;
	}
	
	/**
	 * Retourne le bufferedReader
	 * 
	 */
	public BufferedReader getBufferedReader(){
		return bufferedReader;
	}
	
	/**
	 * Executer les commandes d'initialisation de control et de donnees
	 */
	public void init() {
		try {
			initialisationDeControl();
			initialisationDeDonne();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Fermer la connection
	 * 
	 */
	 public void closeSocket() throws IOException{
         socketControl.close() ;
	 }
}
