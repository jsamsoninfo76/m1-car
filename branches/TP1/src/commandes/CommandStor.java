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
		File fichier = new File(commandMgr.directory.getAbsolutePath()+"/"+commandMgr.reponse[1]);
        if(fichier.isDirectory()) {
        	commandMgr.dataOutputStreamControl.writeBytes("550 Cannot STOR\n");
        } else {
            fichier.createNewFile();
            FileOutputStream out = new FileOutputStream(fichier);
            InputStream is = commandMgr.socketDonnee.getInputStream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            is.close();
            out.flush();
            out.close();
            commandMgr.dataOutputStreamControl.writeBytes("226 File successfully transferred\n");
        }
		commandMgr.recepteur.stor();
	}

}
