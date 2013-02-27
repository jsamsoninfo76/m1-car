package Command;

import java.io.IOException;

public class CommandSyst extends Command {

	public CommandSyst(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("215 " + System.getProperty("os.name") + " \n"); 
		commandMgr.recepteur.syst();
	}
}
