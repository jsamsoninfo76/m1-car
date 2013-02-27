package commandes;
import java.io.IOException;

/**
 * Classe abstraite Command
 * 
 * @author Jeremie Samson - Victor Paumier 
 */
public abstract class Command {
	/**
	 * Manager du design pattern commande
	 */
	protected CommandMgr commandMgr;
	
	/**
	 * Methode executee a chaque process
	 * definie dans les classes qui en heritent
	 */
	abstract void executer() throws IOException;
}
