package resolution;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import logging.EventType;
import main.SystemeExpert;

import resolution.conflict.Conflict;
import resolution.conflict.Premisses;
import struct.Fact;
import struct.Rule;


public class ChainageAvant implements Chainage {
	
	private Conflict conflict;

	@Override
	public void procedure(List<Rule> rules, List<Fact> facts,  Fact goal, Set<Fact> possibleFacts){
			
		boolean inf = true;
		@SuppressWarnings("unused")
		int nbInf = 0;
		
		while (inf){
			inf = false;
			
			// for each rules
			LinkedList<Rule> toRemove = new LinkedList<Rule>();
			SystemeExpert.log(EventType.RULE_TESTING, "Starting rule tests");
			List<Rule> myRules = null;
			if (conflict == Conflict.PREMISSES) {
				Premisses u = new Premisses(facts, rules);
				myRules = u.getToReturn();
			} else {
				myRules = rules;
			}
			for (Rule r : myRules) {
				SystemeExpert.log(EventType.RULE_TESTING,
						"Rule being tested:" + r);
				boolean dec = true;
				for(Fact f : r.getRequiredFacts()){
					SystemeExpert.log(EventType.FACT_TESTING,
							" is '"+f.getName()+"' in the bf ?");
					if(!facts.contains(f)){
						dec = false;
						SystemeExpert.log(EventType.FACT_TESTING, "\tNegative");
					}
					else {
						SystemeExpert.log(EventType.FACT_TESTING, "\tAffirmative");
					}
					if (!dec)
						break;
				}
				if (dec){
					facts.add(r.getDeducedFact());
					SystemeExpert.log(EventType.RULE_APPLICATION, 
							" new fact in the bf : "+r.getDeducedFact());
					toRemove.add(r);
					SystemeExpert.log(EventType.HOUSE_KEEPING,
							"Rule was applied => tagging it for removal");
					inf = true;
					nbInf++;
				}
			}
			
			SystemeExpert.log(EventType.HOUSE_KEEPING, "Removing rules");
			for (Rule r : toRemove) {
				rules.remove(r);
			}
		}
	}

	@Override
	public void setConflict(Conflict conflict) {
		this.conflict = conflict;
	}

}
