package Command;

import java.io.File;
import java.io.IOException;

public class CommandDele extends Command {

	public CommandDele(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		String rep = commandMgr.directory.getAbsolutePath() + "/" + commandMgr.reponse[1];
		System.out.println("prout");
		File file=new File(rep);
		System.out.println("prout 2");
		
		if (file.exists() && !file.isDirectory()){
			System.out.println("prout 3");
			if (file.delete()){
				System.out.println("prou");
				commandMgr.dataOutputStreamControl.writeBytes("250 \""+rep+"\"Dossier supprimer.\n");
			}
			else{
				System.out.println("prut");
			}
				commandMgr.dataOutputStreamControl.writeBytes("550 \""+rep+"\": Impossible de supprimer.\n");
		}else{
			System.out.println("pout");
			commandMgr.dataOutputStreamControl.writeBytes("550 \""+rep+"\": Non supprimable car un dossier.\n");
		}
		commandMgr.recepteur.dele();
	}
}
