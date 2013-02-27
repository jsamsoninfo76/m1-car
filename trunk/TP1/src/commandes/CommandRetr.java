package commandes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
		commandMgr.dataOutputStreamControl.writeBytes("150 Accepted data connection\n");
		 try {
	            File fichier = new File(commandMgr.reponse[1]);
	            byte[] mybytearray = new byte[(int) fichier.length()];
	            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fichier));
	            bis.read(mybytearray, 0, mybytearray.length);
	            OutputStream os = commandMgr.socketDonnee.getOutputStream();
	            os.write(mybytearray, 0, mybytearray.length);
	            os.flush();
	            os.close();
	            commandMgr.dataOutputStreamControl.writeBytes("226 File successfully transferred\n");
	        } catch (IOException e){
	            e.printStackTrace();
	            System.exit(0);
	        }
	}

}
