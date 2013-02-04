import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Déclenche les commandes.
 */
public class FTPServer {

    private int numSc;
    private ServerSocket ssc; //Serveur Socket Control
    private Socket sc;		  //Socket Control
    private ServerSocket ssd; //Serveur Socket Donnee
	private Socket sd;		  //Socket Donne
    private OutputStream osControl;
	private OutputStream osDonne;
	private DataOutputStream dosControl;
	private DataOutputStream dosDonne;
	private InputStream is;
	private InputStreamReader isr;
	private BufferedReader br;
	private String Reponse;
	
	// Références vers les commandes
	private Recepteur rec;
	private CommandeHello cmdHello;
	private CommandeUser cmdUser;
	private CommandePass cmdPass;
	private CommandeSyst cmdSyst;
	private CommandeFeat cmdFeat;
	
    public FTPServer(int numSc,ServerSocket ssc,Socket sc) {
    	this.numSc = numSc;
		this.sc = sc;
	}

	public void start() {
		try {

			this.InitialisationDeControl();
			this.InitialisationDeDonne();
			this.InitialisationDesCommandes();
			this.hello();			
			this.processRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void InitialisationDeControl() throws IOException{
		osControl  = sc.getOutputStream();
		dosControl = new DataOutputStream(osControl);
		is         = sc.getInputStream();
		isr        = new InputStreamReader(is);
		br         = new BufferedReader(isr);
	}
	
	private void InitialisationDesCommandes(){
		rec        = new Recepteur();
		cmdHello   = new CommandeHello(rec,dosControl);
		cmdUser    = new CommandeUser(rec,dosControl,Reponse);
		cmdPass    = new CommandePass(rec,dosControl,Reponse);
		cmdSyst    = new CommandeSyst(rec,dosControl);
		cmdFeat    = new CommandeFeat(rec,dosControl);
	}
	
	private void InitialisationDeDonne(){
		ssd      = null;
		sd       = null;
		osDonne  = null;
		dosDonne = null;
		Reponse  = null;
	}
	
	public void processRequest() throws IOException
	{
		this.Reponse = br.readLine();
		System.out.println("Reponse : "+Reponse);
		
		while(Reponse!=null) 
		{
			if (Reponse.startsWith("USER")) {user();}
			else { if (Reponse.startsWith("PASS")) {pass();} 
			else { if (Reponse.startsWith("SYST")) {syst();}
			else { if (Reponse.startsWith("FEAT")) {feat();}}} } 
		/*	else { if (Reponse.startsWith("PWD")) {processPWD(Reponse);} 
			else { if (Reponse.startsWith("TYPE I")) {processTYPE(Reponse);} 
			else { if (Reponse.startsWith("PASV")) {processPASV(Reponse);} 
			else { if (Reponse.startsWith("LIST")) {processLIST(Reponse);} 
			else { if (Reponse.startsWith("CWD")) {processCWD(Reponse);} 
			else { if (Reponse.startsWith("CDUP")) {processCDUP(Reponse);} 
			else { if (Reponse.startsWith("RETR")) {processRETR(Reponse);} 
			else { if (Reponse.startsWith("STOR")) {processSTOR(Reponse);} 
			}}}}}}}}}}}*/
			Reponse=br.readLine();
		}
		
		if (ssd!=null) {	ssd.close(); }
	}
	
	private void hello() throws IOException { cmdHello.executer("Welcome"); }
	private void user()  throws IOException { cmdUser.executer(Reponse); }
	private void pass()  throws IOException { cmdPass.executer(Reponse); }
	private void syst()  throws IOException { cmdSyst.executer(Reponse); }
	private void feat()  throws IOException { cmdFeat.executer(Reponse); }
}