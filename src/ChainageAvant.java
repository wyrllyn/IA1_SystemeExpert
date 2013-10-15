import java.util.LinkedList;
import java.util.List;

public class ChainageAvant {
	
	public void procedure(List<Rule> rules, List<Fact> bf, List<Fact> facts){
		
		//clone of rules => some rules will be removed, a clone is safer
		List<Rule> copyRules = new LinkedList<Rule>();
		for(Rule r:rules){
			copyRules.add((Rule) r.clone());
		}
		
		boolean inf = true;
		int nbInf = 0;
		
		while (inf){
			inf = false;
			
			// for each rules
			for (Rule r : copyRules) {
				boolean dec = true;
					for(Fact f : r.getRequiredFacts()){
						while(dec){
							if(!facts.contains(f))
								dec = false;
						}
					}
					if (dec){
						bf.add(r.getDeducedFacts());
						copyRules.remove(r);
						inf = true;
						nbInf++;
					}
			}
		}
	}

}
