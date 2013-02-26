package autre;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Serveur
 * Classe correspondant au serveur et gérant les connexions des clients
 * <pre>
 *     Serveur.main();
 * </pre>
 * @author Benjamin Digeon
 */
public class Main {
    /**
     * Socket du serveur
     */
    private ServerSocket serveurSocket;

    /**
     * Port du serveur
     */
    private int port;

    /**
     * Ecoute les demandes de connexion sur un port TCP > 1023
     * Donne accès aux fichiers présents dans un répertoire du système de fichier.
     * Délége à l’aide d’un thread le traitement d’une requête entrante à un objet de la
     * classe FtpRequest.
     */
    public static void main(String[] args) {
        new Main(2121).run();
    }

    /**
     * Public Constructeur
     * @param port serveurPort
     */
    public Main(int port) {
        this.port = port;
    }

    /**
     * Run method
     */
    public void run(){
        this.initSocket(this.port);
        while(true)
            this.traiterDemande();
    }

    /**
     * Init socket
     * @param port port
     */
    private void initSocket(int port) {
        try {
            this.serveurSocket = new ServerSocket(port);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Traitement du client dans un thread
     */
    private void traiterDemande(){
        try {
            new FtpRequest(serveurSocket.accept()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
