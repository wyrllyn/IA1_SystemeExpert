import java.util.LinkedList;
import java.util.List;


public class SystemeExpert {
	
	
	public static void main(String args[]) throws ExpertException{
		List<Fact> baseFacts = new LinkedList<Fact>();
		baseFacts.add(new Fact("slave"));
		baseFacts.add(new Fact("poste_resp"));
		
		Engine engine = new Engine(baseFacts);
		Fact dyna = new Fact("");
		Fact ada = new Fact("");
		Fact lead = new Fact("");
		Fact neerl = new Fact("");
		Fact accept = new Fact("");
		Fact facile = new Fact("");
		
		//engine.addRule(rule);
	}

}
