package commandes;

import java.io.IOException;

/**
 * Classe CommandSyst contenant la methode d'execution du process SYST
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandSyst extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandSyst(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process SYST : renvoie le système courant
	 */
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("215 " + System.getProperty("os.name") + " \n"); 
		commandMgr.recepteur.syst();
	}
}
