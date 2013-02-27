package Command;

import java.io.IOException;

public class CommandHello extends Command {

	public CommandHello(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("220 Welcome\n");
		commandMgr.recepteur.hello();
	}
}
