package commandes;

import java.io.IOException;

/**
 * Classe CommandPwd contenant la methode d'execution du process PWD
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandPwd extends Command {
	
	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandPwd(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process PWD : affiche le repertoire courant
	 */
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("257 \""+commandMgr.directory+ "\"\n");
		commandMgr.recepteur.pwd();
	}

}
