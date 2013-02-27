package commandes;

import java.io.IOException;

/**
 * Classe CommandHello contenant la methode d'execution du process HELLO
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandHello extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandHello(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process HELLO : affiche un message pour confirmer connexion
	 */
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("220 Welcome\n");
		commandMgr.recepteur.hello();
	}
}
