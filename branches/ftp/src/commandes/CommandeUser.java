package commandes;

import java.io.DataOutputStream;
import java.io.IOException;


public class CommandeUser implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	private String User;
	
	public CommandeUser(Recepteur pRecepteur, DataOutputStream dosControl, String user){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
		this.User       = user;
	}
	
	public void executer(String Reponse) throws IOException {
		this.User = Reponse.substring(5);
		
		if (User.equalsIgnoreCase("anonymous") || User.equalsIgnoreCase("pom") || User.equalsIgnoreCase("jerem"))
			dosControl.writeBytes("331 User correct \n");
		else
			dosControl.writeBytes("530 User incorrect \n");
		
		recepteur.user();
	}
}
