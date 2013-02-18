package commandes;

import java.io.DataOutputStream;
import java.io.IOException;


public class CommandeRetr implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;

	public CommandeRetr(Recepteur pRecepteur, DataOutputStream dosControl){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
	}
	
	public void executer(String msg) throws IOException {		
		dosControl.writeBytes("220 I have no idea what i m doing \n");
		recepteur.eprt();
	}

}
