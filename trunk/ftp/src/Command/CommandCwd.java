package Command;

import java.io.File;
import java.io.IOException;

public class CommandCwd extends Command {

	public CommandCwd(CommandMgr commandMgr) {
		this.commandMgr = commandMgr;
	}

	public void executer() throws IOException{
		File local = new File(commandMgr.directory.getAbsolutePath()+"/"+commandMgr.reponse[1]);
        File tmp = new File(commandMgr.reponse[1]);
        if(local.exists() && local.isDirectory()) {
        	commandMgr.directory = local;
            commandMgr.dataOutputStreamControl.writeBytes("250 CWD command successful.\n");
        } else if(tmp.exists() && tmp.isDirectory()) {
        	commandMgr.directory = tmp;
            commandMgr.dataOutputStreamControl.writeBytes("250 CWD command successful.\n");
        } else {
        	commandMgr.dataOutputStreamControl.writeBytes("550 "+commandMgr.reponse[1]+": No such file or directory.\n");
        }
		commandMgr.recepteur.cwd();
	}
}
