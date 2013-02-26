package commandes;

import java.io.File;
import java.io.IOException;

public class CommandMkd extends Command {

	public CommandMkd(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	void executer() throws IOException {
		 File tmp = new File(commandMgr.reponse[1]);

         if(tmp.mkdir()){
        	 commandMgr.dataOutputStreamControl.writeBytes("257 " +commandMgr.reponse[1]+ " created\n");
         } else {
        	 commandMgr.dataOutputStreamControl.writeBytes("550 directory not created\n");
         }
	}

}
