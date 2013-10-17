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
	
	public List<Rule> getRules() {
		return rules;
	}
	
	public List<Fact> getInitialFacts() {
		return initialFacts;
	}
	
	@Override
	public String toString() {
		String s = "Engine [rules=";
		for (Rule r : rules) {
			s += "\n\t" + r;
		}
		s += "\ninitialFacts=";
		for (Fact f : initialFacts) {
			s += "\n\t" + f;
		}
		/*s += "\npossibleFacts=";
		for (Fact f : possibleFacts) {
			s += "\n\t" + f;
		}*/
		s += "\n]";
		return s;
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

	public List<Fact> getFacts() {
		return facts;
	}
}
