package Command;

import java.io.File;
import java.io.IOException;

public class CommandMkd extends Command {

	public CommandMkd(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	void executer() throws IOException {
		 String rep = commandMgr.directory.getAbsolutePath() + "/" + commandMgr.reponse[1];	
		 File tmp = new File(rep);

         if(tmp.mkdir()){
        	 commandMgr.dataOutputStreamControl.writeBytes("257 " +commandMgr.reponse[1]+ " created\n");
         } else {
        	 commandMgr.dataOutputStreamControl.writeBytes("550 directory not created\n");
         }
         commandMgr.recepteur.mkd();
	}

}
