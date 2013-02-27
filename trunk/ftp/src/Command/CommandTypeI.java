package Command;

import java.io.IOException;

public class CommandTypeI extends Command {

	public CommandTypeI(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("200 Switching to Binary mode \n");	
		commandMgr.recepteur.typeI();
	}

}
