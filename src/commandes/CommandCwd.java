package commandes;

import java.io.File;
import java.io.IOException;

/**
 * Classe CommandCwd contenant la methode d'execution du process CWD
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandCwd extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandCwd(CommandMgr commandMgr) {
		this.commandMgr = commandMgr;
	}

	/**
	 * Execute le process CWD : changer de repertoire
	 */
	public void executer() throws IOException{
		File local = new File(commandMgr.directory.getAbsolutePath()+"/"+commandMgr.reponse[1]);
        File tmp = new File(commandMgr.reponse[1]);
        if(local.exists() && local.isDirectory()) {
        	commandMgr.directory = local;
            commandMgr.dataOutputStreamControl.writeBytes("250 CWD command successful.\n");
        } else if(tmp.exists() && tmp.isDirectory()) {
        	commandMgr.directory = tmp;
            commandMgr.dataOutputStreamControl.writeBytes("250 CWD command successful.\n");
        } else {
        	commandMgr.dataOutputStreamControl.writeBytes("550 "+commandMgr.reponse[1]+": No such file or directory.\n");
        }
		commandMgr.recepteur.cwd();
	}
}
