import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import commandes.CommandInvoker;
import commandes.CommandMgr;

public class MainFTPServer {
    public static void main(String[] args) throws IOException {
        int portPasv = 3929;
        int numeroSocket = 0;
        int port = 2121;
        
        
     // Création socket de control
        ServerSocket serverSocketControl = new ServerSocket(port);
        
        System.out.println("----- FTP-SERVER---- -----");
        System.out.println("****************************");
        System.out.println("Server Started...");
        System.out.println("Listen on " + port);
        System.out.println("Waiting for connections...");
        System.out.println("-");
        
        // Création du serveur socket
        try {
            while (true)
			{
            	CommandMgr commandMgr = new CommandMgr();
            	CommandInvoker commandInvoker = new CommandInvoker(commandMgr);
             	
            	commandMgr.setServerSocketControl(serverSocketControl);
            	commandMgr.setPort(port);
            	
            	numeroSocket++;
            	commandMgr.setNumeroSocket(numeroSocket);
            	
            	portPasv++;
            	commandMgr.setPortPasv(portPasv);
            	
            	Socket socketControl = serverSocketControl.accept();
            	commandMgr.setSocketControl(socketControl);
            	
            	commandMgr.init();
            			
            	commandInvoker.invoke("HELLO");
            	
            	commandMgr.setReponse(commandMgr.getBufferedReader().readLine().split(" "));
        	
        		/* ProcessRequest */
        		while(commandMgr.reponse!=null) 
        		{
        			for (int i=0 ; i<commandMgr.reponse.length ; i++)
        				System.out.print("REPONSE : " + commandMgr.reponse[i]);
        			System.out.println();
        			
        			if (commandMgr.reponse[0].equals("USER")) 		{commandInvoker.invoke("USER");}
        			else { if (commandMgr.reponse[0].equals("PASS"))  {commandInvoker.invoke("PASS");} 
        			else { if (commandMgr.reponse[0].equals("SYST"))  {commandInvoker.invoke("SYST");}
        			else { if (commandMgr.reponse[0].equals("FEAT"))  {commandInvoker.invoke("FEAT");}
        			else { if (commandMgr.reponse[0].equals("PWD"))   {commandInvoker.invoke("PWD");}
        			else { if (commandMgr.reponse[0].equals("TYPE"))  {commandInvoker.invoke("TYPE I");}
        			else { if (commandMgr.reponse[0].equals("PASV"))  {commandInvoker.invoke("PASV");} 			
        			else { if (commandMgr.reponse[0].equals("EPRT"))  {commandInvoker.invoke("EPRT");}	
        			else { if (commandMgr.reponse[0].equals("LIST"))  {commandInvoker.invoke("LIST");} 
        			else { if (commandMgr.reponse[0].equals("CWD"))   {commandInvoker.invoke("CWD");} 
        			else { if (commandMgr.reponse[0].equals("CDUP"))  {commandInvoker.invoke("CDUP");} 
        			else { if (commandMgr.reponse[0].equals("RETR"))  {commandInvoker.invoke("RETR");} 
        			else { if (commandMgr.reponse[0].equals("STOR"))  {commandInvoker.invoke("STOR");} 
        			else { if (commandMgr.reponse[0].equals("DELE"))  {commandInvoker.invoke("DELE");}
        			else { if (commandMgr.reponse[0].equals("MKD"))   {commandInvoker.invoke("MKD");}
        			else { if (commandMgr.reponse[0].equals("RMD"))   {commandInvoker.invoke("RMD");}
        			}}}}}}}}}}}}}}}
        			
        			commandMgr.setReponse(commandMgr.getBufferedReader().readLine().split(" "));
        		}
        		
        		if (commandMgr.serverSocketDonnee!=null) {	commandMgr.serverSocketDonnee.close(); }
            	
				System.out.println("Server out");
				System.exit(0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}