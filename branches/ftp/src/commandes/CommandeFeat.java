package commandes;

import java.io.DataOutputStream;
import java.io.IOException;


public class CommandeFeat implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	
	public CommandeFeat(Recepteur pRecepteur, DataOutputStream dosControl){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
	}
	
	public void executer(String Reponse) throws IOException {
		dosControl.writeBytes("211-Lesfeatures\n");
		dosControl.writeBytes("Feature1\n");
		dosControl.writeBytes("Feature2\n");	
		dosControl.writeBytes("Feature3\n");
		dosControl.writeBytes("211 EndFeature\n");
		recepteur.feat();
	}
}
