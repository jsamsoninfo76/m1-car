package Command;

import java.io.File;
import java.io.IOException;

public class CommandRmd extends Command {
	
	public CommandRmd(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		String rep = commandMgr.directory.getAbsolutePath() + "/" + commandMgr.reponse[1];	
		 File tmp = new File(rep);
         if(tmp.exists() && tmp.isDirectory()) {
             if(tmp.delete()){
            	 commandMgr.dataOutputStreamControl.writeBytes("250 "+commandMgr.reponse[1]+" deleted\n");
             } else {
            	 commandMgr.dataOutputStreamControl.writeBytes("550 File unavaillable\n");
             }
         } else {
        	 commandMgr.dataOutputStreamControl.writeBytes("550 directory not found\n");
         }
        commandMgr.recepteur.rmd();
	}
}
