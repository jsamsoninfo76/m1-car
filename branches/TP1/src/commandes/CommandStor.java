package commandes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CommandStor extends Command {

	public CommandStor(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {	
		FileOutputStream fos	 = null;
		commandMgr.socketDonnee  = null;				
		File file = new File(commandMgr.directory + commandMgr.reponse[1]);
		fos = new FileOutputStream(file);
				
		commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();
		commandMgr.dataOutputStreamControl.writeBytes("150 Opening " + commandMgr.directory + commandMgr.reponse[1] + " mode data connection.\n");

		InputStream in = commandMgr.socketDonnee.getInputStream();

		byte buf[] = new byte[1024];
		int nread;
		while ((nread = in.read(buf)) > 0)
		{
			fos.write(buf, 0, nread);
		}
		fos.flush();
		fos.close();
		commandMgr.serverSocketDonnee.close();		
		commandMgr.recepteur.stor();
	}

}
