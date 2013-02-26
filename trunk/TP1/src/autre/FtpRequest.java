package autre;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.SocketAddress;
import java.util.Hashtable;
import java.util.Map;

/**
 * FtpRequest
 * Classe correspondant a un thread dédié au client et gérant le traitement des requetes du client.
 * <pre>
 *     new FtpRequest(serveurSocket.accept()).start();
 * </pre>
 * @author Benjamin Digeon
 */
public class FtpRequest extends Thread{

    /**
     * Socket de canal de commande
     */
    private Socket socketCmd;
    /**
     * Socket de canal de données
     */
    private Socket socketData;
    /**
     * Check si la connexion est toujours active
     */
    private Boolean connexionActive;
    /**
     * Repertoire courant
     */
    private File repertoireCourant;
    /**
     * Si l'utilisateur est correctement loggé
     */
    private Boolean logged;
    /**
     * Username de l'utilisateur
     */
    private String currentuser;
    /**
     * Map contenant tout les utilisateurs ainsi que leur password
     */
    private Map userAndPassword;

    /**
     * Public constructor
     * @param socket socket
     */
    public FtpRequest(Socket socket) {
        this.socketCmd = socket;
        this.connexionActive = true;
        File tmp = new File("");
        this.repertoireCourant = tmp.getAbsoluteFile();
        this.logged = false;
        this.currentuser = null;
        this.userAndPassword = new Hashtable();
        this.userAndPassword.put("anonymous","anon@localhost");
        this.userAndPassword.put("ben","tropico");
    }

    /**
     * Run thread method
     */
    public void run() {
        //Envoi du message d'acceuil
        sendMessageCmd("220 Hello :) \n");

        while (connexionActive) {
            //Traitement des requetes
            try {
                InputStream is = socketCmd.getInputStream();
                processRequest(readLine(is));
            } catch (IOException e) {
                e.printStackTrace();
                connexionActive = false;
            }
        }
    }

    /**
     * Read line of inputStream
     * @param inputStream inputstream
     * @return String of line of Inputstream
     * @throws IOException
     */
    private String readLine(InputStream inputStream)throws IOException{
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
        return rd.readLine();
    }

    /**
     * Effectue des traitements généraux concernant une requête entrante et déléguant le traitement des commandes
     * @param request Requete
     */
    private void processRequest(String request) {
        if(request != null) {
            System.out.println("<< "+request);
            String[] parseRqt = parseLine(request);
            String cmd = parseRqt[0];

            if(cmd.equals("USER")) {
                processUSER(parseRqt[1]);
            } else if (cmd.equals("PASS")){
                processPASS(parseRqt[1]);
            }else if(logged) {
                if (cmd.equals("RETR")){
                    processRETR(parseRqt[1]);
                } else if (cmd.equals("STOR")){
                    processSTOR(parseRqt[1]);
                } else if (cmd.equals("LIST")){
                    processLIST();
                } else if (cmd.equals("QUIT")){
                    processQUIT();
                } else if (cmd.equals("PWD")){
                    processPWD();
                } else if (cmd.equals("CWD")){
                    processCWD(parseRqt[1]);
                } else if (cmd.equals("CDUP")){
                    processCDUP();
                } else if (cmd.equals("PORT")) {
                    processPORT(parseRqt[1]);
                } else if (cmd.equals("MKD")) {
                    processMKD(parseRqt[1]);
                } else if (cmd.equals("RMD")) {
                    processRMD(parseRqt[1]);
                } else if (cmd.equals("PASV")) {
                    processPASV();
                } else if (cmd.equals("TYPE")) {
                    processTYPE(parseRqt[1]);
                } else {
                    sendMessageCmd("502 Command not implemented \n");
                }
            } else {
                sendMessageCmd("332 Need account for login.\n");
            }
        }
    }

    /**
     * Permet de parser la requete envoyée par le client en commande
     * @param str requete du client
     * @return commande
     */
    private String[] parseLine(String str) {
        return str.split(" ");
    }

    /**
     * Send message to client
     * @param message message
     */
    private void sendMessageCmd(String message) {
        System.out.println(">> "+message);
        try {
            OutputStream os = socketCmd.getOutputStream();
            os.write(message.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            connexionActive = false;
        }
    }

    /**
     * USER username : utilisé pour l’authentification de l’utilisateur
     * @param username username
     */
    private void processUSER(String username) {

        if(userAndPassword.containsKey(username)) {
            sendMessageCmd("331 User name okay, need password.\n");
            this.currentuser = username;
        } else {
            sendMessageCmd("332 Need account for login.\n");
        }
    }

    /**
     * PASS password : utilisé pour le mot de passe de l’utilisateur
     * @param password password
     */
    private void processPASS(String password) {
        if(this.currentuser != null && userAndPassword.get(this.currentuser).equals(password)) {
            sendMessageCmd("230 User logged in, proceed.\n");
            this.logged = true;
        } else {
            //Password NOK
            sendMessageCmd("332 Need account for login.\n");
        }
    }

    /**
     * RETR filename : utilisé pour prendre un fichier du répertoire distant et le déposer dans le répertoire local
     * @param filename filename
     */
    private void processRETR(String filename) {
        sendMessageCmd("150 Accepted data connection\n");
        //Envoi du fichier au client
        try {
            File fichier = new File(filename);
            byte[] mybytearray = new byte[(int) fichier.length()];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fichier));
            bis.read(mybytearray, 0, mybytearray.length);
            OutputStream os = socketData.getOutputStream();
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
            os.close();
            sendMessageCmd("226 File successfully transferred\n");
        } catch (IOException e){
            e.printStackTrace();
            connexionActive = false;
        }
    }

    /**
     * STOR filename : utilisé pour déposer un fichier venant du répertoire local dans le répertoire distant
     * @param filename filename
     */
    private void processSTOR(String filename) {
        if(currentuser.equals("anonymous")) {
            //Read only
            sendMessageCmd("550 Read only for this user");
        } else {
            sendMessageCmd("150 Accepted data connection\n");
            //Reception du fichier
            try {
                File fichier = new File(repertoireCourant.getAbsolutePath()+"/"+filename);
                if(fichier.isDirectory()) {
                    sendMessageCmd("550 Cannot STOR\n");
                } else {
                    fichier.createNewFile();
                    FileOutputStream out = new FileOutputStream(fichier);
                    InputStream is = socketData.getInputStream();

                    int read = 0;
                    byte[] bytes = new byte[1024];

                    while ((read = is.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }

                    is.close();
                    out.flush();
                    out.close();
                    sendMessageCmd("226 File successfully transferred\n");
                }
            } catch (IOException e){
                e.printStackTrace();
                connexionActive = false;
            }
            sendMessageCmd("250 Requesting file action okay, completed\n");
        }
    }

    /**
     * LIST : permet à l’utilisateur de demander l’envoi de la liste des fichiers du répertoire courant
     * NB : Pour plus de facilité utilisation de la commande ls -la ce qui pose un problème de compatibilité
     * Sur les architectures n'implementant pas la commande ls (par exemple Windows)
     */
    private void processLIST() {

        try {
            java.lang.Runtime rt = java.lang.Runtime.getRuntime();
            java.lang.Process p = rt.exec("ls -la "+repertoireCourant.getAbsolutePath());
            p.waitFor();
            java.io.InputStream is = p.getInputStream();
            OutputStream os = this.socketData.getOutputStream();
            java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));

            sendMessageCmd("125 List started OK\n");

            String s = null;
            while ((s = reader.readLine()) != null) {
                os.write(s.getBytes("UTF8"));
                os.write("\n".getBytes("UTF8"));
            }

            is.close();
            os.flush();
            os.close();

            sendMessageCmd("250 List command successful\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
            connexionActive = false;
        } catch (IOException e) {
            e.printStackTrace();
            connexionActive = false;
        }
    }

    /**
     * QUIT : permet à l’utilisateur de terminer la session FTP en cours
     */
    private void processQUIT() {
        sendMessageCmd("221 Service closing control connection\n");
        connexionActive = false;
    }

    /**
     * PWD : permet à l’utilisateur de connaître la valeur du répertoire de travail distant.
     */
    private void processPWD() {
         sendMessageCmd("257 "+repertoireCourant.getAbsolutePath()+"\n");
    }

    /**
     * CWD directory : permet à l’utilisateur de changer de répertoire de travail distant.
     */
    private void processCWD(String repertoire) {
        File local = new File(repertoireCourant.getAbsolutePath()+"/"+repertoire);
        File tmp = new File(repertoire);
        if(local.exists() && local.isDirectory()) {
            repertoireCourant = local;
            sendMessageCmd("250 CWD command successful.\n");
        } else if(tmp.exists() && tmp.isDirectory()) {
            repertoireCourant = tmp;
            sendMessageCmd("250 CWD command successful.\n");
        } else {
            sendMessageCmd("550 "+repertoire+": No such file or directory.\n");
        }
    }

    /**
     * CDUP : équivalent à cd ../
     */
    private void processCDUP() {
        File tmp = repertoireCourant.getParentFile();
        if(tmp.exists() && tmp.isDirectory()) {
            repertoireCourant = tmp;
            sendMessageCmd("250 CWD command successful.\n");
        } else {
            sendMessageCmd("550 : No such file or directory.\n");
        }
    }

    /**
     * MKD : Make directory
     * @param repertoire directory name
     */
    private void processMKD(String repertoire) {
        if(currentuser.equals("anonymous")) {
            //Read only
            sendMessageCmd("550 Read only for this user");
        } else {
            File tmp = new File(repertoire);
            if(tmp.mkdir()){
                sendMessageCmd("257 "+repertoire+" created\n");
            } else {
                sendMessageCmd("550 directory not created\n");
            }
        }
    }

    /**
     * RMD : Remove directory
     * @param repertoire directory name
     */
    private void processRMD(String repertoire) {
        if(currentuser.equals("anonymous")) {
            //Read only
            sendMessageCmd("550 Read only for this user");
        } else {
            File tmp = new File(repertoire);
            if(tmp.exists() && tmp.isDirectory()) {
                if(tmp.delete()){
                    sendMessageCmd("250 "+repertoire+" deleted\n");
                } else {
                    sendMessageCmd("550 File unavaillable\n");
                }
            } else {
                sendMessageCmd("550 directory not found\n");
            }
        }
    }

    /**
     * PORT : active mode
     * @param infos ip et port sous la forme ip1,ip2,ip3,ip4,port1,port2
     */
    private void processPORT(String infos) {

        String [] arg = parseAdresseClient(infos);
        String ipadr = arg[0];
        int port = Integer.parseInt(arg[1]);

        try {
            this.socketData = new Socket(ipadr,port);
            sendMessageCmd("200 PORT command successful\n");
        } catch (IOException e) {
            e.printStackTrace();
            connexionActive = false;
        }
    }

    /**
     * PASV : Passive mode
     */
    private void processPASV() {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            SocketAddress adress = serverSocket.getLocalSocketAddress();
            String adresse = parseAdresseServeur(adress.toString());
            sendMessageCmd("227 Entering passive mode ("+adresse+").\n");
            this.socketData = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            connexionActive = false;
        }
    }

    /**
     * TYPE : Sets the type of file to be transferred
     */
    private void processTYPE(String type) {
        if(type.equals("I")) {
            sendMessageCmd("200 TYPE is now 8-bit binary.\n");
        } else if(type.equals("A")) {
            sendMessageCmd("200 TYPE is now ASCII text.\n");
        } else {
            sendMessageCmd("551 Requested action aborted. Page type unknown.\n");
        }
    }

    /**
     * Parse adresse serveur socket
     * @param adresse Adresse serveur ip:port
     * @return Adresse serveur ip1,ip2,ip3,ip4,port1,port2 avec port_TCP = port1 * 256 + port2
     */
    private String parseAdresseServeur(String adresse) {
        String tab[] = adresse.split(":");
        String tabtmp[] = tab[0].split("/");
        String tabIp[] = tabtmp[0].split("[.]");
        Integer port = Integer.parseInt(tab[1]);
        int port1 = port / 256;
        int port2 = port % 256;
        return tabIp[0]+","+tabIp[1]+","+tabIp[2]+","+tabIp[3]+","+port1+","+port2;
    }

    /**
     * Parse adresse client socket
     * @param adresse ip1,ip2,ip3,port1,port2 avec port_TCP = port1 * 256 + port2
     * @return [0]-> IpAdresse, [1]-> port
     */
    private String[] parseAdresseClient(String adresse) {
        String[] tab = adresse.split(",");
        String[] ret = new String[2];
        ret[0] = tab[0]+"."+tab[1]+"."+tab[2]+"."+tab[3];
        Integer port = (Integer.parseInt(tab[4]) * 256) + Integer.parseInt(tab[5]);
        ret[1] = port.toString();
        return ret;
    }
}