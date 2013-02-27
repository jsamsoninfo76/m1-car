package commandes;

import java.io.IOException;

/**
 * Classe CommandFeat contenant la methode d'execution du process FEAT
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandFeat extends Command {
	
	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandFeat(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process FEAT : affiche les feactures du serveur
	 */
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("211-Lesfeatures\n");
		commandMgr.dataOutputStreamControl.writeBytes("Feature1\n");
		commandMgr.dataOutputStreamControl.writeBytes("Feature2\n");	
		commandMgr.dataOutputStreamControl.writeBytes("Feature3\n");
		commandMgr.dataOutputStreamControl.writeBytes("211 EndFeature\n");
		commandMgr.recepteur.feat();
	}
}
