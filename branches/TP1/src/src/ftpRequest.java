import java.io.IOException;
import java.net.Socket;
import commandes.CommandInvoker;
import commandes.CommandMgr;

/**
 * Classe FtpRequest correspondant a un thread qui traite les requètes clients
 * @author Jeremie Samson - Victor Paumier 
 */

public class ftpRequest implements Runnable {
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
	public ftpRequest(Socket server, int port, int num){
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
                commandMgr.setReponse(commandMgr.getBufferedReader().readLine());
                
                /* ProcessRequest */
                while(commandMgr.reponse!=null) 
                {
                        System.out.println("REPONSE : " + commandMgr.reponse);
                        
                        if (commandMgr.reponse.startsWith("USER"))              {commandInvoker.invoke("USER");}
                        else { if (commandMgr.reponse.startsWith("PASS"))  {commandInvoker.invoke("PASS");} 
                        else { if (commandMgr.reponse.startsWith("SYST"))  {commandInvoker.invoke("SYST");}
                        else { if (commandMgr.reponse.startsWith("FEAT"))  {commandInvoker.invoke("FEAT");}
                        else { if (commandMgr.reponse.startsWith("PWD"))   {commandInvoker.invoke("PWD");}
                        else { if (commandMgr.reponse.startsWith("TYPE I")){commandInvoker.invoke("TYPE I");}
                        else { if (commandMgr.reponse.startsWith("PASV"))  {commandInvoker.invoke("PASV");}                     
                        else { if (commandMgr.reponse.startsWith("EPRT"))  {commandInvoker.invoke("EPRT");}     
                        else { if (commandMgr.reponse.startsWith("LIST"))  {commandInvoker.invoke("LIST");} 
                        else { if (commandMgr.reponse.startsWith("CWD"))   {commandInvoker.invoke("CWD");} 
                        else { if (commandMgr.reponse.startsWith("CDUP"))  {commandInvoker.invoke("CDUP");} 
                        else { if (commandMgr.reponse.startsWith("RETR"))  {commandInvoker.invoke("RETR");} 
                        else { if (commandMgr.reponse.startsWith("STOR"))  {commandInvoker.invoke("STOR");} 
                        else { if (commandMgr.reponse.startsWith("DELE"))  {commandInvoker.invoke("DELE");} 
                        }}}}}}}}}}}}}
                        commandMgr.setReponse(commandMgr.getBufferedReader().readLine());
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