package Command;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

public class CommandUser extends Command {

	public CommandUser(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.user = commandMgr.reponse[1];
		
		Boolean find = false ;
        
        Iterator<Entry<String, String>> iterator = commandMgr.userPass.entrySet().iterator();
        while (iterator.hasNext() && !find) {
                if (commandMgr.user.equalsIgnoreCase((String) iterator.next().getKey())){
                        commandMgr.dataOutputStreamControl.writeBytes("331 User correct \n");
                        find = true ;
                }
        }
        
        if(!find)
                commandMgr.dataOutputStreamControl.writeBytes("530 User incorrect \n");

		commandMgr.recepteur.user();
	}
}
