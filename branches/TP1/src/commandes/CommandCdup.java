package commandes;

import java.io.IOException;

public class CommandCdup extends Command {

	public CommandCdup(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	void executer() throws IOException {		
		int avantDerniereoccuranceDuSlash=0;
		int dernieroccuranceDuSlash=0;
		for(int i=0;i<commandMgr.directory.length();i++){
			if (commandMgr.directory.charAt(i)=='/') { avantDerniereoccuranceDuSlash=dernieroccuranceDuSlash;
			dernieroccuranceDuSlash=i;}
		}
		commandMgr.directory=commandMgr.directory.substring(0,avantDerniereoccuranceDuSlash)+"/";
		commandMgr.dataOutputStreamControl.writeBytes("250 Cdup OK !!! \n");
		commandMgr.recepteur.cdup();
	}
}
