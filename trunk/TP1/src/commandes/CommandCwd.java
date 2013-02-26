package commandes;

import java.io.IOException;

public class CommandCwd extends Command {

	public CommandCwd(CommandMgr commandMgr) {
		this.commandMgr = commandMgr;
	}

	public void executer() throws IOException{
		if (commandMgr.reponse.substring(4).startsWith("/")) {
			commandMgr.directory = commandMgr.reponse.substring(4) + "/";
		} else {
			commandMgr.directory += commandMgr.reponse.substring(4) + "/";
		}
		commandMgr.dataOutputStreamControl.writeBytes("250 Directory changed to " + commandMgr.directory + " \n");
		commandMgr.recepteur.cwd();
	}
}
