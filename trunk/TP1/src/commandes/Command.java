package commandes;
import java.io.IOException;

public abstract class Command {
	protected CommandMgr commandMgr;
	
	abstract void executer() throws IOException;
}
