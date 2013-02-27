package commandes;

import java.io.File;
import java.io.IOException;

/**
 * Classe CommandMkd contenant la methode d'execution du process MKD
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandMkd extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandMkd(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process MKD : creer un repertoire
	 */
	void executer() throws IOException {
		 String rep = commandMgr.directory.getAbsolutePath() + "/" + commandMgr.reponse[1];	
		 File tmp = new File(rep);

         if(tmp.mkdir()){
        	 commandMgr.dataOutputStreamControl.writeBytes("257 " +commandMgr.reponse[1]+ " created\n");
         } else {
        	 commandMgr.dataOutputStreamControl.writeBytes("550 directory not created\n");
         }
         commandMgr.recepteur.mkd();
	}

}
