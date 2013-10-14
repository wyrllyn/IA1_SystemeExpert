import java.util.LinkedList;
import java.util.List;


public class Engine {
	
	private List<Rule> rules;
	private List<Fact> initialFacts;
	private List<Fact> facts;
	
	public Engine( List<Fact> f){
		initialFacts = f;
		rules = new LinkedList<Rule>();
		facts = new LinkedList<Fact>(f);
	}

	public void addRule(Rule rule) {
		rules.add(rule);
	}
	
	
	public void applyRules() {
		
	}
	
	protected void applyRule(Rule rule) throws ExpertException {
		if (!rules.contains(rule)) {
			throw new ExpertException("Unknown rule:" + rule);
		}
		//TODO:rule application stuff
	}
}
