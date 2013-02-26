package commandes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;

public class CommandRetr extends Command {

	public CommandRetr(CommandMgr commandMgr){
		this.commandMgr  = commandMgr;
	}
	
	public void executer() throws IOException {	
		commandMgr.dataOutputStreamControl.writeBytes("150 Accepted data connection\n");
		 try {
	            File fichier = new File(commandMgr.directory + "/" + commandMgr.reponse[1]);
	            byte[] mybytearray = new byte[(int) fichier.length()];
	            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fichier));
	            bis.read(mybytearray, 0, mybytearray.length);
	            commandMgr.portPasv = commandMgr.portPasv + 10;
	            commandMgr.serverSocketDonnee = new ServerSocket(commandMgr.portPasv);
	            commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();
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
