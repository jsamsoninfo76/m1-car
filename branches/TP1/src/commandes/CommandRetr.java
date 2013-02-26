package commandes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CommandRetr extends Command {

	public CommandRetr(CommandMgr commandMgr){
		this.commandMgr  = commandMgr;
	}
	
	public void executer() throws IOException {	
		/*FileInputStream fis 	= null;
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
		commandMgr.recepteur.eprt();*/
		commandMgr.dataOutputStreamControl.writeBytes("150 Accepted data connection\n");
		
        //Envoi du fichier au client
        try {
            File fichier = new File(commandMgr.directory + commandMgr.reponse.substring((5)));
            System.out.println(commandMgr.directory + commandMgr.reponse.substring((5)));
            byte[] mybytearray = new byte[(int) fichier.length()];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fichier));
            bis.read(mybytearray, 0, mybytearray.length);
            
            commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();
            commandMgr.outputStreamDonnee = commandMgr.socketDonnee.getOutputStream();
            
            commandMgr.outputStreamDonnee.write(mybytearray, 0, mybytearray.length);
            commandMgr.outputStreamDonnee.flush();
            commandMgr.outputStreamDonnee.close();
            commandMgr.dataOutputStreamControl.writeBytes("226 File successfully transferred\n");
        } catch (IOException e){
          
        }
	}

}
