package commandes;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Classe CommandUser contenant la methode d'execution du process USER
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandUser extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandUser(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process USER : verifie le nom d'utilisateur du client
	 */
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
