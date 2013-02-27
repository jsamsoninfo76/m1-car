package commandes;

import java.io.IOException;

/**
 * Classe CommandPass contenant la methode d'execution du process PASS
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandPass extends Command {

	/**
	 * Public Constructor
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandPass(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	/**
	 * Execute le process PASS : verifie le mot de passe du client
	 */
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
				System.out.println("Numero Socket: " + commandMgr.numeroSocket);
				System.out.println("------------------------");
		}
		else
			commandMgr.dataOutputStreamControl.writeBytes("530 Pass incorrect \n");
		
		commandMgr.recepteur.pass();
	}
}