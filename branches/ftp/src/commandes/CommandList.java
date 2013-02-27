package commandes;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Classe CommandList contenant la methode d'execution du process LIST
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandList extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandList(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process LIST : liste le contenu du repertoire courant
	 */
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("150 Here comes the directory listing. \n");
		commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();
		commandMgr.outputStreamDonnee  = commandMgr.socketDonnee.getOutputStream();
		commandMgr.dataOutputStreamDonnee = new DataOutputStream(commandMgr.outputStreamDonnee);

		if (commandMgr.directory.listFiles() != null) {
			String laReponse = "";
			for (File file : commandMgr.directory.listFiles()) {
				if (file.isFile()) {
					laReponse = "\053,r,i" + file.length() + ",\011"
							+ file.getName() + "\015\012";
				}
				if (file.isDirectory()) {
					laReponse = "\053m" + file.lastModified() + ",/,\011"
							+ file.getName() + "\015\012";
				}
				commandMgr.dataOutputStreamDonnee.writeBytes(laReponse + "\n");
				commandMgr.dataOutputStreamDonnee.flush();
			}
			commandMgr.dataOutputStreamDonnee.close();
			commandMgr.outputStreamDonnee.close();
			commandMgr.serverSocketDonnee.close();
			commandMgr.dataOutputStreamControl.writeBytes("226 Directory send \n");
		}
		commandMgr.recepteur.list();
	}
}
