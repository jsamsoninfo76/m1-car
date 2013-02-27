package commandes;

import java.io.File;
import java.io.IOException;

/**
 * Classe CommandDele contenant la methode d'execution du process DELE
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandDele extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandDele(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process DELE : supprimer un fichier
	 */
	public void executer() throws IOException {
		String rep = commandMgr.directory.getAbsolutePath() + "/" + commandMgr.reponse[1];
		File file=new File(rep);
		
		if (file.exists() && !file.isDirectory()){
			if (file.delete()){
				System.out.println("prou");
				commandMgr.dataOutputStreamControl.writeBytes("250 \""+rep+"\"Dossier supprimer.\n");
			}
			else{
			}
				commandMgr.dataOutputStreamControl.writeBytes("550 \""+rep+"\": Impossible de supprimer.\n");
		}else{
			commandMgr.dataOutputStreamControl.writeBytes("550 \""+rep+"\": Non supprimable car un dossier.\n");
		}
		commandMgr.recepteur.dele();
	}

}
