package commandes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Classe CommandStor contenant la methode d'execution du process STOR
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandStor extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandStor(CommandMgr commandMgr) {
		this.commandMgr = commandMgr;
	}

	/**
	 * Execute le process STOR : recupere un fichier du client
	 */
	public void executer() throws IOException {
		FileOutputStream fos = null;
		commandMgr.socketDonnee = null;
		File file = new File(commandMgr.directory + "/"+ commandMgr.reponse[1]);
		fos = new FileOutputStream(file);
		commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();
		commandMgr.dataOutputStreamControl.writeBytes("150 Opening " + commandMgr.directory.getAbsolutePath()
				+ commandMgr.reponse[1] + " mode data connection.\n");

		InputStream in = commandMgr.socketDonnee.getInputStream();

		byte buf[] = new byte[1024];
		int nread;
		while ((nread = in.read(buf)) > 0) {
			fos.write(buf, 0, nread);
		}
		fos.flush();
		fos.close();
		commandMgr.serverSocketDonnee.close();
		commandMgr.recepteur.stor();
		commandMgr.dataOutputStreamControl.writeBytes("226 File successfully transferred\n");
	}

}
