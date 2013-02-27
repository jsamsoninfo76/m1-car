package commandes;

import java.io.IOException;

/**
 * Classe CommandDele contenant la methode d'execution du process DELE
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandDele extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandDele(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process DELE : supprimer un fichier
	 */
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("220 Welcome\n");
		commandMgr.recepteur.hello();
	}
}
