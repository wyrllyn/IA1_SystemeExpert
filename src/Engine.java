import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


public class Engine {
	
	private List<Rule> rules;
	private List<Fact> initialFacts;
	private List<Fact> facts;
	private TreeSet<Fact> possibleFacts;
	
	public Engine(List<Fact> f){
		initialFacts = f;
		rules = new LinkedList<Rule>();
		facts = new LinkedList<Fact>(f);
		possibleFacts = new TreeSet<Fact>();
	}

	public void addRule(Rule rule) {
		rules.add(rule);
	}
	
	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}
	
	
	public void applyRules() {
		
	}
	
	protected void applyRule(Rule rule) throws ExpertException {
		if (!rules.contains(rule)) {
			throw new ExpertException("Unknown rule:" + rule);
		}
		//TODO:rule application stuff
	}

	public void setPossibleFacts(TreeSet<Fact> possibleFacts) {
		this.possibleFacts = possibleFacts;
	}
}
