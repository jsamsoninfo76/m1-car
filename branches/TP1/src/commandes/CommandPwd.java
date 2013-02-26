package commandes;

import java.io.DataOutputStream;
import java.io.IOException;


public class CommandPwd extends Command {
	
	public CommandPwd(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("257 \""+commandMgr.directory+ "\"\n");
		commandMgr.recepteur.pwd();
	}

}
