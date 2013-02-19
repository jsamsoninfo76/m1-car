package commandes;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class CommandList extends Command {

	public CommandList(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("150 Here comes the directory listing. \n");
		if (commandMgr.socketDonnee == null) commandMgr.socketDonnee = commandMgr.serverSocketDonnee.accept();
		if (commandMgr.outputStreamDonnee == null) commandMgr.outputStreamDonnee  = commandMgr.socketDonnee.getOutputStream();
		if (commandMgr.dataOutputStreamDonnee == null) commandMgr.dataOutputStreamDonnee = new DataOutputStream(commandMgr.outputStreamDonnee);
		System.out.println("dos");

		File pwd = new File(commandMgr.directory);
		if (pwd.listFiles() != null) {
			String laReponse = "";
			for (File file : pwd.listFiles()) {
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
