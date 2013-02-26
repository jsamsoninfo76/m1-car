package commandes;

import java.io.IOException;
import java.net.ServerSocket;

public class CommandPasv extends Command {
	
	public CommandPasv(CommandMgr commandMgr){
		this.commandMgr = commandMgr;
	}
	
	public void executer() throws IOException {
		System.out.println("Port Passif Avant: " + commandMgr.portPasv);
		commandMgr.portPasv++;
	
		System.out.println("Port Passif Apres : " + commandMgr.portPasv);
		commandMgr.setPortPasv(commandMgr.portPasv);
		System.out.println("Port Passif Apres 2 : " + commandMgr.portPasv);
		
		String s=Integer.toHexString(commandMgr.portPasv);
		
		commandMgr.setServerSocketDonnee(new ServerSocket(commandMgr.portPasv));
		
		int i;
		int j;
		
		if (s.length()==3) {
		 i=Integer.parseInt(s.substring(0,1),16);
		 j=Integer.parseInt(s.substring(1),16);
		}
		else {
			 i=Integer.parseInt(s.substring(0,2),16);
			 j=Integer.parseInt(s.substring(2),16);
		}
		commandMgr.dataOutputStreamControl.writeBytes("229 Entering Passive Mode (127,0,0,1,"+i+","+j+") \n");
		commandMgr.recepteur.pasv();
	}
}
