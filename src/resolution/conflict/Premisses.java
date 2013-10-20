package resolution.conflict;

import java.util.LinkedList;
import java.util.List;

import struct.Fact;
import struct.Rule;


public class Premisses {
	@SuppressWarnings("unused")
	private List<Fact> bf;
	@SuppressWarnings("unused")
	private List<Rule> rules;
	private List<Rule> toReturn = new LinkedList<Rule>();
	
	public Premisses(List<Fact> bf, List<Rule> rules){
		this.bf = bf;
		this.rules = rules;
		
		for (Rule r : rules){
			r.setPriority(0);
			// if the deduced fact is already in the bf, then ignore the rule
			if (bf.contains(r.getDeducedFact()))
				break;
			// for each facts, apply a new priority value
			for (Fact rf : r.getRequiredFacts()){
				for (int i = 0; i < bf.size() ; i++){
					if (bf.get(i).equals(rf)){
						int prior = r.getPriority() + i;
						r.setPriority(prior);
					}
				}
			}
		}
		
		int maxPrior = 0;
		// the highest priority is the one with the most recent facts in the bf
		for (Rule r : rules){
			if (r.getPriority() > maxPrior){
				maxPrior = r.getPriority();
			}
		}
		for (int currentPrio = maxPrior; currentPrio >= 0; currentPrio--) {
			for (Rule r : rules) {
				if (r.getPriority() == currentPrio && !toReturn.contains(r)) {
					toReturn.add(r);
				}
			}
		}
	}
	
	public List<Rule> getToReturn(){
		return toReturn;
	}
}
