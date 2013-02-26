import java.net.ServerSocket;
import java.net.Socket;

/**
 * DŽclenche les commandes.
 */
public class FTPServer {
	
   public FTPServer(int numSocket,ServerSocket serveurSocketControl,Socket socketControl, int portPasv) {
 //   	this.numSocket = numSocket;
   // 	this.serverSocketControl = serveurSocketControl;
	//	this.socketControl = socketControl;
	//	this.portPasv = portPasv;
	}
/*
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
		osControl  = socketControl.getOutputStream();
		dosControl = new DataOutputStream(osControl);
		is         = socketControl.getInputStream();
		isr        = new InputStreamReader(is);
		br         = new BufferedReader(isr);
	}

	private void InitialisationDeDonne() throws IOException{
		this.serverSocketDonnee = new ServerSocket(this.portPasv); 
		socketDonnee       = null;
		osDonne  = null;
		dosDonne = null;
		Reponse  = null;
		dir      = "/Users/Spider/ftp/";
	}
	
	private void InitialisationDesCommandes(){
		rec        = new Recepteur();
		cmdHello   = new CommandeHello(rec,dosControl);
		cmdUser    = new CommandeUser(rec,dosControl,Reponse);
		cmdPass    = new CommandePass(rec,dosControl,Reponse);
		cmdSyst    = new CommandeSyst(rec,dosControl);
		cmdFeat    = new CommandeFeat(rec,dosControl);
		cmdPwd     = new CommandePwd(rec,dosControl);
		cmdTypeI   = new CommandeTypeI(rec,dosControl);
		cmdPasv    = new CommandePasv(rec,dosControl,portPasv);
		cmdEprt    = new CommandeEprt(rec,dosControl);
		cmdList    = new CommandeList(rec,dosControl,osDonne,dosDonne,socketDonnee);
		cmdCwd     = new CommandeCwd(rec,dosControl,dir);
		cmdCdup    = new CommandeCdup(rec,dosControl);
		cmdRetr    = new CommandeRetr(rec,dosControl);
		cmdStor    = new CommandeStor(rec,dosControl);
	}
	
	public void processRequest() throws IOException
	{
		this.Reponse = br.readLine();
		System.out.println("Reponse : "+Reponse);
		
		while(Reponse!=null) 
		{
			if (Reponse.startsWith("USER")) {user();}
			else { if (Reponse.startsWith("PASS"))  {pass();} 
			else { if (Reponse.startsWith("SYST"))  {syst();}
			else { if (Reponse.startsWith("FEAT"))  {feat();}
			else { if (Reponse.startsWith("PWD"))   {pwd();}
			else { if (Reponse.startsWith("TYPE I")){typeI();}
			else { if (Reponse.startsWith("PASV"))  {pasv();} 				//PSV pour localhost
			else { if (Reponse.startsWith("EPRT"))  {eprt();}				//Pour localhost
			else { if (Reponse.startsWith("LIST"))  {list();} 
			else { if (Reponse.startsWith("CWD"))   {cwd();} 
			else { if (Reponse.startsWith("CDUP"))  {cdup();} 
			else { if (Reponse.startsWith("RETR"))  {retr();} 
			else { if (Reponse.startsWith("STOR"))  {stor();} 
			}}}}}}}}}}}}
			Reponse=br.readLine();
		}
		
		if (serverSocketDonnee!=null) {	serverSocketDonnee.close(); }
	}
	
	private void hello() throws IOException  { cmdHello.executer(Reponse);}
	private void user()  throws IOException  { cmdUser.executer(Reponse); }
	private void pass()  throws IOException  { cmdPass.executer(Reponse); }
	private void syst()  throws IOException  { cmdSyst.executer(Reponse); }
	private void feat()  throws IOException  { cmdFeat.executer(Reponse); }
	private void pwd()   throws IOException  { cmdPwd.executer(dir);      }
	private void typeI() throws IOException  { cmdTypeI.executer(Reponse);}
	private void pasv()  throws IOException  { cmdPasv.executer(Reponse); serverSocketDonnee = cmdPasv.getServerSocketDonnee(); }
	private void eprt()  throws IOException  { cmdEprt.executer(Reponse); }
	private void list()  throws IOException  { cmdList.setDir(dir); cmdList.setServerSocketDonne(serverSocketDonnee); cmdList.executer(Reponse); }
	private void cwd()   throws IOException  { cmdCwd.executer(Reponse); dir = cmdCwd.getDir(); }
	private void cdup()  throws IOException  { cmdCdup.executer(dir); dir = cmdCdup.getDir();}
	private void retr()  throws IOException  { cmdRetr.executer(Reponse); }
	private void stor()  throws IOException  { cmdStor.executer(Reponse); }*/
}