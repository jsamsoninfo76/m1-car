package commandes;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class CommandeList implements Commande {

	private Recepteur recepteur;
	private DataOutputStream dosControl;
	private OutputStream osDonne;
	private DataOutputStream dosDonne;
	private ServerSocket serverSocketDonnee;
	private Socket socketDonnee;
	private String dir;

	public CommandeList(Recepteur pRecepteur, DataOutputStream dosControl, OutputStream osDonne, 
			DataOutputStream dosDonne, Socket socketDonnee){
		this.recepteur  = pRecepteur;
		this.dosControl = dosControl;
		this.osDonne    = osDonne;
		this.dosDonne   = dosDonne;
		this.socketDonnee = socketDonnee;
	}
	
	public void executer(String msg) throws IOException {
		dosControl.writeBytes("150 Here comes the directory listing. \n");
		if (socketDonnee == null) socketDonnee = serverSocketDonnee.accept();
		if (osDonne == null) osDonne  = socketDonnee.getOutputStream();
		if (dosDonne == null) dosDonne = new DataOutputStream(osDonne);
		System.out.println("dos");

		File pwd = new File(dir);
		if (pwd.listFiles() != null) {
			String laReponse = "";
			for (File file : pwd.listFiles()) {
				if (file.isFile()) {
					laReponse = "\053,r,i" + file.length() + ",\011"
							+ file.getName() + "\015\012";
				}
				if (file.isDirectory()) {
					laReponse = "\053m" + file.lastModified() + ",/,\011"
							+ file.getName() + "\015\012";
				}
				dosDonne.writeBytes(laReponse + "\n");
				dosDonne.flush();
			}
			dosDonne.close();
			osDonne.close();
			serverSocketDonnee.close();
			dosControl.writeBytes("226 Directory send \n");
		}
		recepteur.list();
	}
	
	public void setDir(String dir){
		this.dir = dir;
	}

	public void setServerSocketDonne(ServerSocket serverSocketDonnee) {
		this.serverSocketDonnee = serverSocketDonnee;
	}

}
