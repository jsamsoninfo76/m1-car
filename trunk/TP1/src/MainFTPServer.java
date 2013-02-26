import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import commandes.CommandInvoker;
import commandes.CommandMgr;

public class MainFTPServer {
    public static void main(String[] args) throws IOException {
        int portPasv = 3929;
        int numeroSocket = 0;
        int port = 7000;
        
        
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
            	
            	commandMgr.setReponse(commandMgr.getBufferedReader().readLine());
        	
        		/* ProcessRequest */
        		while(commandMgr.reponse!=null) 
        		{
        			System.out.println("REPONSE : " + commandMgr.reponse);
        			
        			if (commandMgr.reponse.startsWith("USER")) 		{commandInvoker.invoke("USER");}
        			else { if (commandMgr.reponse.startsWith("PASS"))  {commandInvoker.invoke("PASS");} 
        			else { if (commandMgr.reponse.startsWith("SYST"))  {commandInvoker.invoke("SYST");}
        			else { if (commandMgr.reponse.startsWith("FEAT"))  {commandInvoker.invoke("FEAT");}
        			else { if (commandMgr.reponse.startsWith("PWD"))   {commandInvoker.invoke("PWD");}
        			else { if (commandMgr.reponse.startsWith("TYPE I")){commandInvoker.invoke("TYPE I");}
        			else { if (commandMgr.reponse.startsWith("PASV"))  {commandInvoker.invoke("PASV");} 			
        			else { if (commandMgr.reponse.startsWith("EPRT"))  {commandInvoker.invoke("EPRT");}	
        			else { if (commandMgr.reponse.startsWith("LIST"))  {commandInvoker.invoke("LIST");} 
        			else { if (commandMgr.reponse.startsWith("CWD"))   {commandInvoker.invoke("CWD");} 
        			else { if (commandMgr.reponse.startsWith("CDUP"))  {commandInvoker.invoke("CDUP");} 
        			else { if (commandMgr.reponse.startsWith("RETR"))  {commandInvoker.invoke("RETR");} 
        			else { if (commandMgr.reponse.startsWith("STOR"))  {commandInvoker.invoke("STOR");} 
        			else { if (commandMgr.reponse.startsWith("DELE"))  {commandInvoker.invoke("DELE");} 
        			}}}}}}}}}}}}}
        			else commandInvoker.invoke("HELLO");
        			commandMgr.setReponse(commandMgr.getBufferedReader().readLine());
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