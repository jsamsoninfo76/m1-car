package commandes;

import java.io.IOException;
import java.util.HashMap;

/**
 * Classe CommandInvoker permet d'invoquer les commandes et de les executer
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public class CommandInvoker {
	/**
	 * HashMap contenant les commandes et les classes associees
	 */
	private HashMap<String,Command> commandMap = new HashMap<String,Command>();
	private CommandMgr commandMgr;
	
	/**
	 * Public Constructor
	 * Associe les commandes aux classes correspondante 
	 * @param commandMgr Manager du design pattern commande
	 */
	public CommandInvoker(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
		commandMap.put("CDUP", new CommandCdup(commandMgr));
		commandMap.put("CWD", new CommandCwd(commandMgr));
		commandMap.put("FEAT", new CommandFeat(commandMgr));
		commandMap.put("HELLO", new CommandHello(commandMgr));
		commandMap.put("LIST", new CommandList(commandMgr));
		commandMap.put("PASS", new CommandPass(commandMgr));
		commandMap.put("PASV", new CommandPasv(commandMgr));
		commandMap.put("PWD", new CommandPwd(commandMgr));
		commandMap.put("RETR", new CommandRetr(commandMgr));
		commandMap.put("STOR", new CommandStor(commandMgr));
		commandMap.put("SYST", new CommandSyst(commandMgr));
		commandMap.put("TYPE I", new CommandTypeI(commandMgr));
		commandMap.put("USER", new CommandUser(commandMgr));
		commandMap.put("DELE", new CommandDele(commandMgr));
		commandMap.put("MKD", new CommandMkd(commandMgr));
		commandMap.put("RMD", new CommandMkd(commandMgr));
	}
	
	/**
	 * Enregitre la commande dans l'historique puis l'execute
	 * @param Command nom de la commande a executer
	 */
	public void invoke(String command) throws IOException{
		commandMgr.recordHistory(commandMap.get(command));
		commandMap.get(command).executer();
	}
}
