package commandes;

import java.io.File;
import java.io.IOException;

/**
 * Classe CommandRmd contenant la methode d'execution du process RMD
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandRmd extends Command {
	
	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandRmd(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process RMD : supprime un repertoire
	 */
	public void executer() throws IOException {
		 File tmp = new File(commandMgr.reponse[1]);
         if(tmp.exists() && tmp.isDirectory()) {
             if(tmp.delete()){
            	 commandMgr.dataOutputStreamControl.writeBytes("250 "+commandMgr.reponse[1]+" deleted\n");
             } else {
            	 commandMgr.dataOutputStreamControl.writeBytes("550 File unavaillable\n");
             }
         } else {
        	 commandMgr.dataOutputStreamControl.writeBytes("550 directory not found\n");
         }
	}
}
