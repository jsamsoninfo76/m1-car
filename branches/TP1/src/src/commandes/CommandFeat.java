package commandes;

import java.io.IOException;

public class CommandFeat extends Command {
	
	public CommandFeat(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.dataOutputStreamControl.writeBytes("211-Lesfeatures\n");
		commandMgr.dataOutputStreamControl.writeBytes("Feature1\n");
		commandMgr.dataOutputStreamControl.writeBytes("Feature2\n");	
		commandMgr.dataOutputStreamControl.writeBytes("Feature3\n");
		commandMgr.dataOutputStreamControl.writeBytes("211 EndFeature\n");
		commandMgr.recepteur.feat();
	}
}
