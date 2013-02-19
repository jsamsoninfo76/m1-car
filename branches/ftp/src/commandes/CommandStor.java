package commandes;

import java.io.IOException;

public class CommandStor extends Command {

	public CommandStor(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {		
		commandMgr.dataOutputStreamControl.writeBytes("220 I have no idea what i m doing \n");
		commandMgr.recepteur.eprt();
	}

}
