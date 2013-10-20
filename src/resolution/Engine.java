package resolution;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import resolution.conflict.Conflict;
import resolution.conflict.Value;
import struct.Fact;
import struct.Rule;



public class Engine {
	
	private List<Rule> rules;
	private List<Fact> initialFacts;
	private List<Fact> facts;
	private Set<Fact> possibleFacts;
	private List<Fact> goals;
	private Fact goal;
	
	public Engine(List<Fact> f){
		initialFacts = f;
		rules = new LinkedList<Rule>();
		facts = new LinkedList<Fact>(f);
		goals = new LinkedList<Fact>();
		possibleFacts = new HashSet<Fact>();
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
		s += "\npossibleFacts=";
		for (Fact f : possibleFacts) {
			s += "\n\t" + f;
		}
		s += "\n]";
		return s;
	}

	public void setPossibleFacts(Set<Fact> possibleFacts2) {
		this.possibleFacts = possibleFacts2;
	}

	public List<Fact> getFacts() {
		return facts;
	}

	public void setGoals(List<Fact> goals) {
		this.goals = goals;
		goal = goals.get(0);
	}
	
	public List<Fact> getGoals() {
		return goals;
	}

	public Set<Fact> getPossibleFacts() {
		return possibleFacts;
	}

	public void go(Strategy strategy, Conflict conflict) {
		Chainage ca = null;
		switch (strategy) {
		case CHAINAGE_AVANT:
			ca = new ChainageAvant();
			break;
		case CHAINAGE_ARRIERE:
			ca = new ChainageArriere();
			break;
		case CHAINAGE_MIXTE:
			ca = new ChainageMixte();
		}
		
		switch (conflict) {
		case PREMISSES: //unicorn
			break;
		case VALUE: //pony
			Value pony = new Value(rules);
			rules = pony.getNewList();
			break;
		}
		ca.setConflict(conflict);
		
		ca.procedure(this.rules, this.facts, goal, possibleFacts);
	}

	public Fact getGoal() {
		return goal;
	}
}
