package commandes;

import java.io.IOException;

public class CommandRetr extends Command {

	public CommandRetr(CommandMgr commandMgr){
		this.commandMgr  = commandMgr;
	}
	
	public void executer() throws IOException {		
		commandMgr.dataOutputStreamControl.writeBytes("220 I have no idea what i m doing \n");
		commandMgr.recepteur.eprt();
	}

}
