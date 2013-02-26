package commandes;

import java.io.File;
import java.io.IOException;

public class CommandRmd extends Command {
	
	public CommandRmd(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		 File tmp = new File(commandMgr.reponse[1]);
         if(tmp.exists() && tmp.isDirectory()) {
             if(tmp.delete()){
            	 commandMgr.dataOutputStreamControl.writeBytes("250 "+commandMgr.reponse[1]+" deleted\n");
             } else {
            	 commandMgr.dataOutputStreamControl.writeBytes("550 File unavaillable\n");
             }
         } else {
        	 commandMgr.dataOutputStreamControl.writeBytes("550 directory not found\n");
         }
	}
}
