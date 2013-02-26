package Serveur;
import java.io.IOException;
import java.net.Socket;
import commandes.CommandInvoker;
import commandes.CommandMgr;

/**
 * Classe FtpRequest correspondant a un thread qui traite les requètes clients
 * @author Jeremie Samson - Victor Paumier 
 */

public class FtpRequest implements Runnable {
	/**
	 * Manager du design pattern commande
	 */
	private CommandMgr commandMgr ;
	
	/**
	 * Invoker du design pattern commande
	 */
	private CommandInvoker commandInvoker ;
	
	
	/**
	 * Public Constructor
	 * @param server Socket de connection
	 * @param port port d'écoute du server
	 * @param num numéro de la connection
	 */
	public FtpRequest(Socket server, int port, int num){
		int portPasv = 3929;

		commandMgr = new CommandMgr();
		commandInvoker = new CommandInvoker(commandMgr);
        commandMgr.setSocketControl(server) ;
        commandMgr.setPort(port);
        commandMgr.setPortPasv(portPasv) ;
        commandMgr.setNumeroSocket(num);
	}
	
	/**
	 * Run thread method
	 */
	public void run(){
		try {
				commandMgr.init();
                commandInvoker.invoke("HELLO");
                commandMgr.setReponse(commandMgr.getBufferedReader().readLine().split(" "));
                
                /* ProcessRequest */
                while(commandMgr.reponse!=null) 
                {
                    for (int i=0 ; i<commandMgr.reponse.length ; i++)
                        System.out.print("REPONSE : " + commandMgr.reponse[i]);
	                System.out.println();
	                
	                if (commandMgr.reponse[0].equals("USER"))               {commandInvoker.invoke("USER");}
	                else { if (commandMgr.reponse[0].equals("PASS"))  {commandInvoker.invoke("PASS");} 
	                else { if (commandMgr.reponse[0].equals("SYST"))  {commandInvoker.invoke("SYST");}
	                else { if (commandMgr.reponse[0].equals("FEAT"))  {commandInvoker.invoke("FEAT");}
	                else { if (commandMgr.reponse[0].equals("PWD"))   {commandInvoker.invoke("PWD");}
	                else { if (commandMgr.reponse[0].equals("TYPE"))  {commandInvoker.invoke("TYPE I");}
	                else { if (commandMgr.reponse[0].equals("PASV"))  {commandInvoker.invoke("PASV");}                      
	                else { if (commandMgr.reponse[0].equals("EPRT"))  {commandInvoker.invoke("EPRT");}      
	                else { if (commandMgr.reponse[0].equals("LIST"))  {commandInvoker.invoke("LIST");} 
	                else { if (commandMgr.reponse[0].equals("CWD"))   {commandInvoker.invoke("CWD");} 
	                else { if (commandMgr.reponse[0].equals("CDUP"))  {commandInvoker.invoke("CDUP");} 
	                else { if (commandMgr.reponse[0].equals("RETR"))  {commandInvoker.invoke("RETR");} 
	                else { if (commandMgr.reponse[0].equals("STOR"))  {commandInvoker.invoke("STOR");} 
	                else { if (commandMgr.reponse[0].equals("DELE"))  {commandInvoker.invoke("DELE");}
	                else { if (commandMgr.reponse[0].equals("MKD"))   {commandInvoker.invoke("MKD");}
	                else { if (commandMgr.reponse[0].equals("RMD"))   {commandInvoker.invoke("RMD");}
	                }}}}}}}}}}}}}}}
	                
	                commandMgr.setReponse(commandMgr.getBufferedReader().readLine().split(" "));
                }
                
                if (commandMgr.serverSocketDonnee!=null) {      commandMgr.serverSocketDonnee.close(); }
        
                System.out.println("Server out");
                commandMgr.closeSocket() ;
                System.exit(0);
                
         } catch (IOException e) {
                e.printStackTrace();
        }
	}
}