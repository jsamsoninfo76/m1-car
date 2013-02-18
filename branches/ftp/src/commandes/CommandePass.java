package commandes;

import java.io.DataOutputStream;
import java.io.IOException;

public class CommandePass implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	private String Pass;
	
	public CommandePass(Recepteur pRecepteur, DataOutputStream dosControl, String Pass){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
		this.Pass       = Pass;
	}
	
	public void executer(String Reponse) throws IOException {
		this.Pass = Reponse.substring(5); 
		
		if (Pass.equalsIgnoreCase("anonymous") || Pass.equalsIgnoreCase("pom") || Pass.equalsIgnoreCase("jerem"))
			dosControl.writeBytes("230 Pass correct \n");
		else
			dosControl.writeBytes("530 Pass incorrect \n");
		
		recepteur.pass();
	}
}