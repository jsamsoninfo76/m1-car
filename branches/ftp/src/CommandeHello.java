import java.io.DataOutputStream;
import java.io.IOException;


public class CommandeHello implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	
	public CommandeHello(Recepteur pRecepteur, DataOutputStream dosControl){
		this.recepteur = pRecepteur;
		this.dosControl = dosControl;
	}
	
	public void executer(String msg) throws IOException {
		dosControl.writeBytes("220 " + msg + " \n");	
		recepteur.hello();
	}

}
