package commandes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CommandRetr extends Command {

	public CommandRetr(CommandMgr commandMgr) {
		this.commandMgr = commandMgr;
	}

	public void executer() throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		commandMgr.socketDonnee = null;
		fis = new FileInputStream(commandMgr.directory + "/" + commandMgr.reponse[1]);
		commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();

		commandMgr.dataOutputStreamControl.writeBytes("150 Opening " + commandMgr.directory.getAbsolutePath()
				+ commandMgr.reponse[1] + " mode data connection.\n");
		commandMgr.outputStreamDonnee = commandMgr.socketDonnee.getOutputStream();
		
		byte buf[] = new byte[1024];
		int nread;
		System.out.println("ici");
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
