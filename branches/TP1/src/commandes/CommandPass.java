package commandes;

import java.io.IOException;

public class CommandPass extends Command {

	public CommandPass(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		commandMgr.password = commandMgr.reponse[1]; 
		
		if (commandMgr.password.equalsIgnoreCase(commandMgr.userPass.get(commandMgr.user))) 
		{
				commandMgr.dataOutputStreamControl.writeBytes("230 Pass correct \n");
				System.out.println("-------- INFO -----------");
				System.out.println("User: " + commandMgr.user);
				System.out.println("Pass: " + commandMgr.password);
				System.out.println("Port: " + commandMgr.port);
				System.out.println("Port Passif: " + commandMgr.portPasv);
				System.out.println("Numéro Socket: " + commandMgr.numeroSocket);
				System.out.println("------------------------");
		}
		else
			commandMgr.dataOutputStreamControl.writeBytes("530 Pass incorrect \n");
		
		commandMgr.recepteur.pass();
	}
}