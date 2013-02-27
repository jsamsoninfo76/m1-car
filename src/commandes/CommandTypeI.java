package commandes;

import java.io.IOException;

/**
 * Classe CommandTypeI contenant la methode d'execution du process TYPEI
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandTypeI extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandTypeI(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process TYPEI : passe en mode binaire
	 */
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("200 Switching to Binary mode \n");	
		commandMgr.recepteur.typeI();
	}

}
