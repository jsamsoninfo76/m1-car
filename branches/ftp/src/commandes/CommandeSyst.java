package commandes;

import java.io.DataOutputStream;
import java.io.IOException;


public class CommandeSyst implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	
	public CommandeSyst(Recepteur pRecepteur, DataOutputStream dosControl){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
	}
	
	public void executer(String Reponse) throws IOException {
		dosControl.writeBytes("215 " + System.getProperty("os.name") + " \n"); 
		recepteur.syst();
	}
}
