package commandes;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Classe CommandRetr contenant la methode d'execution du process RETR
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandRetr extends Command {

	/**
     * Public Constructor
     * @param commandMgr Manager du design pattern commande
     */
    public CommandRetr(CommandMgr commandMgr){
            this.commandMgr  = commandMgr;
    }
    
    /**
     * Execute le process RETR : envoie un fichier au client
     */
	public void executer() throws IOException {
		
		commandMgr.socketDonnee = null;
		FileInputStream fis = new FileInputStream(commandMgr.directory + "/" + commandMgr.reponse[1]);
		commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();

		commandMgr.dataOutputStreamControl.writeBytes("150 Opening " + commandMgr.directory.getAbsolutePath()
				+ commandMgr.reponse[1] + " mode data connection.\n");
		commandMgr.outputStreamDonnee = commandMgr.socketDonnee.getOutputStream();
		
		byte buf[] = new byte[1024];
		int nread;
		
		while ((nread = fis.read(buf)) > 0) {
			commandMgr.outputStreamDonnee.write(buf, 0, nread);
		}
		if (commandMgr.dataOutputStreamDonnee != null) {
			commandMgr.dataOutputStreamDonnee.close();
		}
		if (commandMgr.outputStreamDonnee != null){
			commandMgr.outputStreamDonnee.flush();
		}

		commandMgr.outputStreamDonnee.close();
		commandMgr.serverSocketDonnee.close();
		
		commandMgr.dataOutputStreamControl.writeBytes("226 File successfully transferred\n");
		commandMgr.recepteur.retr();

	}

}
