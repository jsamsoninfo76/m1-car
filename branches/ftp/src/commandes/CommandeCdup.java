package commandes;

import java.io.DataOutputStream;
import java.io.IOException;


public class CommandeCdup implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	private String dir;
	
	public CommandeCdup(Recepteur pRecepteur, DataOutputStream dosControl){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
	}
	
	public void executer(String dir) throws IOException {		
		int avantDerniereoccuranceDuSlash=0;
		int dernieroccuranceDuSlash=0;
		for(int i=0;i<dir.length();i++){
			if (dir.charAt(i)=='/') { avantDerniereoccuranceDuSlash=dernieroccuranceDuSlash;
			dernieroccuranceDuSlash=i;}
		}
		this.dir=dir.substring(0,avantDerniereoccuranceDuSlash)+"/";
		dosControl.writeBytes("250 Cdup OK !!! \n");
		recepteur.cdup();
	}
	
	public String getDir(){
		return this.dir;
	}

}
