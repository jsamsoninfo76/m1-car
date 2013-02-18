package commandes;

import java.io.DataOutputStream;
import java.io.IOException;

public class CommandeCwd implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	public String dir;

	public CommandeCwd(Recepteur pRecepteur, DataOutputStream dosControl, String dir) {
		this.recepteur = pRecepteur;
		this.dosControl = dosControl;
		this.dir = dir;
	}

	public void executer(String reponse) throws IOException {
		if (reponse.substring(4).startsWith("/")) {
			dir = reponse.substring(4) + "/";
		} else {
			dir += reponse.substring(4) + "/";
		}
		dosControl.writeBytes("250 Changement de dir ->" + getDir() + " \n");
		recepteur.cwd();
	}
	
	public String getDir(){
		return dir;
	}

}
