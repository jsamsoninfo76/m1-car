package commandes;

import java.io.IOException;

public class CommandDele extends Command {

	public CommandDele(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("220 Welcome\n");
		commandMgr.recepteur.hello();
	}
}
