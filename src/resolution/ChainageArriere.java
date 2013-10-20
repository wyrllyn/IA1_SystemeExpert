package resolution;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import logging.EventType;
import main.SystemeExpert;

import resolution.conflict.Conflict;
import resolution.conflict.Premisses;
import struct.Fact;
import struct.Rule;



public class ChainageArriere implements Chainage {
	private Conflict conflict;
	
	public boolean demo(Fact goal, List<Fact> facts, List<Rule> rules, Set<Fact> possibleFacts){
		boolean dem = false;
		Scanner scanner = new Scanner (System.in);

		//list demandable
		List<Fact> demandables = new LinkedList<Fact>();
		
		// initialization of demandables
		for (Fact possible : possibleFacts){
			boolean present = false;
			for (Rule r : rules){
				if (possible == r.getDeducedFact())
					present = true;
			}
			if (!present)
				demandables.add(possible);
		}
		SystemeExpert.log(EventType.INFO, "This is our goal:" + goal);
		
		// first case
		if (facts.contains(goal)) {
			SystemeExpert.log(EventType.FACT_TESTING, "goal is already in base facts");
			dem = true;
		}
		
		// second case
		int i = 0;
		do {
			List<Rule> myRules = null;
			if (conflict == Conflict.PREMISSES) {
				Premisses u = new Premisses(facts, rules);
				myRules = u.getToReturn();
			} else {
				myRules = rules;
			}
			Rule r = myRules.get(i);
			SystemeExpert.log(EventType.INFO, "deduces fact test " +r.getDeducedFact());
			if (!dem && r.getDeducedFact().equals(goal)){
				SystemeExpert.log(EventType.RULE_TESTING, "This rule achieves our goal:" + r);
				dem = verif(r.getRequiredFacts(),facts, myRules, possibleFacts);
			}
			i++;
		} while (dem == false && i < rules.size());
		
		// third case
			
		if(!dem && demandables.contains(goal)){
			System.out.println("add "+goal.getName()+ " in the bf ? 1 for yes, anything for no");
			int val = scanner.nextInt ();
			if(val == 1)
				dem = true;
			else dem = false;
		}
		
		if(dem && !facts.contains(goal)){
			SystemeExpert.log(EventType.RULE_APPLICATION, "Adding goal:" + goal);
			facts.add(goal);
		}
		
		return dem;
	}
	
	public boolean verif(List<Fact> goals, List<Fact> bf, List<Rule> rules, Set<Fact> possibleFacts){
		boolean ver = true;
		
		int i = 0;
		do {
			Fact f = goals.get(i);
			if (ver)
				ver = demo(f, bf, rules, possibleFacts);
			i++;
		} while (ver == true && i < goals.size());
		return ver;
	}

	@Override
	public void procedure(List<Rule> rules, List<Fact> facts, Fact goal,
			Set<Fact> possibleFacts) {
		demo(goal,
				facts,
				rules,
				possibleFacts);
	}

	@Override
	public void setConflict(Conflict conflict) {
		this.conflict = conflict;
	}
}
