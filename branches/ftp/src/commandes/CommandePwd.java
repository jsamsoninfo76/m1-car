package commandes;

import java.io.DataOutputStream;
import java.io.IOException;


public class CommandePwd implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	
	public CommandePwd(Recepteur pRecepteur, DataOutputStream dosControl){
		this.recepteur = pRecepteur;
		this.dosControl = dosControl;
	}
	
	public void executer(String dir) throws IOException {
		dosControl.writeBytes("257 \""+dir+ "\"\n");
		recepteur.pwd();
	}

}
