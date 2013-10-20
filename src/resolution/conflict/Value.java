package resolution.conflict;

import java.util.LinkedList;
import java.util.List;

import struct.Rule;


public class Value {
	
	private List<Rule> rules;
	private List<Rule> newList = new LinkedList<Rule>();
	
	//
	
	public Value(List<Rule> rules){
		this.rules = rules;
		int maxPrior = 0;
		
		//fills possibleConflicts with rules having the same deduced Fact
		for (int i = 0; i < rules.size() - 1; i++){
			for (int j = i + 1; j < rules.size(); j++){
				if (rules.get(i).getDeducedFact().equals(rules.get(j).getDeducedFact())){
					if (rules.get(i).getPriority() == rules.get(j).getPriority()){
						if(rules.get(i).getRequiredFacts().size() > rules.get(j).getRequiredFacts().size()){
							int prior = rules.get(i).getPriority() + 1;
							rules.get(i).setPriority(prior);
						}
						else {
							int prior = rules.get(j).getPriority() + 1;
							rules.get(j).setPriority(prior);
							if (prior > maxPrior){
								maxPrior = prior;
							}
						}
					}
				}
			}
		}
		while( maxPrior >= 0){
			for (Rule r : rules){
				if (r.getPriority() == maxPrior){
					newList.add(r);
				}
			}
			maxPrior--;
		}
	}
	
	public List<Rule> getNewList(){
		return this.newList;
	}
	
}
