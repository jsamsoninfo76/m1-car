package commandes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CommandRetr extends Command {

	public CommandRetr(CommandMgr commandMgr){
		this.commandMgr  = commandMgr;
	}
	
	public void executer() throws IOException {	
		FileInputStream fis 	= null;
		commandMgr.socketDonnee = null;
		
		File file = new File(commandMgr.directory + commandMgr.reponse.substring((5)));
		fis = new FileInputStream(file);
		commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();
		commandMgr.dataOutputStreamControl.writeBytes("150 Opening " + commandMgr.directory + commandMgr.reponse.substring(5) + " mode data connection.\n");
		commandMgr.outputStreamDonnee = commandMgr.socketDonnee.getOutputStream();

		byte buf[] = new byte[1024];
		int nread;
		while ((nread = fis.read(buf)) > 0)
		{
			commandMgr.outputStreamDonnee.write(buf, 0, nread);
		}
		if (commandMgr.dataOutputStreamDonnee!=null) {
			commandMgr.dataOutputStreamDonnee.close();
		}
		if (commandMgr.outputStreamDonnee!=null)
			commandMgr.outputStreamDonnee.flush();
		commandMgr.outputStreamDonnee.close();
		commandMgr.serverSocketDonnee.close();
		commandMgr.recepteur.eprt();
	}

}
