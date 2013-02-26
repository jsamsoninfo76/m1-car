package commandes;

import java.io.IOException;
import java.util.HashMap;

public class CommandInvoker {
	private HashMap<String,Command> commandMap = new HashMap<String,Command>();
	private CommandMgr commandMgr;
	
	public CommandInvoker(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
		commandMap.put("CDUP", new CommandCdup(commandMgr));
		commandMap.put("CWD", new CommandCwd(commandMgr));
		commandMap.put("FEAT", new CommandFeat(commandMgr));
		commandMap.put("HELLO", new CommandHello(commandMgr));
		commandMap.put("LIST", new CommandList(commandMgr));
		commandMap.put("PASS", new CommandPass(commandMgr));
		commandMap.put("PASV", new CommandPasv(commandMgr));
		commandMap.put("EPRT", new CommandEprt(commandMgr));
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
	public void invoke(String command) throws IOException{
		commandMgr.recordHistory(commandMap.get(command));
		commandMap.get(command).executer();
	}
}
