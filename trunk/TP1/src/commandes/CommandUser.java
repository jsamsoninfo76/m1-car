package commandes;

import java.io.IOException;

public class CommandUser extends Command {

	public CommandUser(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.user = commandMgr.reponse[1];
		
		if (commandMgr.user.equalsIgnoreCase("anonymous") || commandMgr.user.equalsIgnoreCase("pom") || commandMgr.user.equalsIgnoreCase("jerem"))
			commandMgr.dataOutputStreamControl.writeBytes("331 User correct \n");
		else
			commandMgr.dataOutputStreamControl.writeBytes("530 User incorrect \n");
		
		commandMgr.recepteur.user();
	}
}
