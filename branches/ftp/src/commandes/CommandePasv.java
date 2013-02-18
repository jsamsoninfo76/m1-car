package commandes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;


public class CommandePasv implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	private int portPasv;
	private ServerSocket serveurSocketDonne;
	
	public CommandePasv(Recepteur pRecepteur, DataOutputStream dosControl, int portPasv){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
		this.portPasv   = portPasv;
	}
	
	public void executer(String msg) throws IOException {
		String s=Integer.toHexString(this.portPasv);
		serveurSocketDonne = new ServerSocket(this.portPasv);
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
		dosControl.writeBytes("229 Entering Passive Mode (127,0,0,1,"+i+","+j+") \n");
		recepteur.pasv();
	}
	
	public ServerSocket getServerSocketDonnee(){
		return serveurSocketDonne;
	}

}
