package commandes;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Classe CommandPasv contenant la methode d'execution du process PASV
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandPasv extends Command {
	
	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandPasv(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process PASV : passe en mode passif
	 */
	public void executer() throws IOException {
		commandMgr.portPasv++;
		commandMgr.setPortPasv(commandMgr.portPasv);
		
		String s=Integer.toHexString(commandMgr.portPasv);
		commandMgr.setServerSocketDonnee(new ServerSocket(commandMgr.portPasv));

		
		int i;
		int j;
		
		if (s.length()==3) {
		 i=Integer.parseInt(s.substring(0,1),16);
		 j=Integer.parseInt(s.substring(1),16);
		}
		else {
			 i=Integer.parseInt(s.substring(0,2),16);
			 j=Integer.parseInt(s.substring(2),16);
		}
		commandMgr.dataOutputStreamControl.writeBytes("229 Entering Passive Mode (127,0,0,1,"+i+","+j+") \n");
		commandMgr.recepteur.pasv();
	}
}
