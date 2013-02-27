package Command;

import java.io.File;
import java.io.IOException;

public class CommandCdup extends Command {

	public CommandCdup(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	void executer() throws IOException {		
        if(commandMgr.directory.getParentFile().exists() && commandMgr.directory.getParentFile().isDirectory()) {
        	commandMgr.directory = commandMgr.directory.getParentFile();
        	commandMgr.dataOutputStreamControl.writeBytes("250 CWD command successful.\n");
        } else {
        	commandMgr.dataOutputStreamControl.writeBytes("550 : No such file or directory.\n");
        }
	}
}
