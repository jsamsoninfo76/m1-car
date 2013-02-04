import java.io.DataOutputStream;
import java.io.IOException;

public class CommandePass implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	private String pass;
	
	public CommandePass(Recepteur pRecepteur, DataOutputStream dosControl, String pass){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
		this.pass       = pass;
	}
	
	public void executer(String Reponse) throws IOException {
		pass = Reponse.substring(5); 
		dosControl.writeBytes("230 Vous etes authentifier " + pass + " \n");
		recepteur.pass();
	}
}
