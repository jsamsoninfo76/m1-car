package commandes;

import java.io.IOException;

/**
 * Classe CommandCdup contenant la methode d'execution du process CDUP
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandCdup extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandCdup(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process CDUP : acceder au repertoire de niveau superieur
	 */
	void executer() throws IOException {		
        if(commandMgr.directory.getParentFile().exists() && commandMgr.directory.getParentFile().isDirectory()) {
        	commandMgr.directory = commandMgr.directory.getParentFile();
        	commandMgr.dataOutputStreamControl.writeBytes("250 CWD command successful.\n");
        } else {
        	commandMgr.dataOutputStreamControl.writeBytes("550 : No such file or directory.\n");
        }
	}
}
