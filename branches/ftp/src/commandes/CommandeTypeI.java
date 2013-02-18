package commandes;

import java.io.DataOutputStream;
import java.io.IOException;


public class CommandeTypeI implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	
	public CommandeTypeI(Recepteur pRecepteur, DataOutputStream dosControl){
		this.recepteur = pRecepteur;
		this.dosControl = dosControl;
	}
	
	public void executer(String msg) throws IOException {
		dosControl.writeBytes("200 Switching to Binary mode \n");	
		recepteur.typeI();
	}

}
