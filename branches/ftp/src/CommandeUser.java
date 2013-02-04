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
		dosControl.writeBytes("331 Le mdp svp \n");
		recepteur.user();
	}
}
