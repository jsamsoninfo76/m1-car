package commandes;

import java.io.IOException;

public class CommandPass extends Command {

	public CommandPass(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.password = commandMgr.reponse.substring(5); 
		
		if (commandMgr.password.equalsIgnoreCase("anonymous") || commandMgr.password.equalsIgnoreCase("pom") || commandMgr.password.equalsIgnoreCase("jerem"))
			commandMgr.dataOutputStreamControl.writeBytes("230 Pass correct \n");
		else
			commandMgr.dataOutputStreamControl.writeBytes("530 Pass incorrect \n");
		
		commandMgr.recepteur.pass();
	}
}