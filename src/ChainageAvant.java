import java.util.LinkedList;
import java.util.List;

public class ChainageAvant {
	
	public void procedure(List<Rule> rules, List<Fact> facts){
		
			
		boolean inf = true;
		int nbInf = 0;
		
		while (inf){
			inf = false;
			
			// for each rules
			for (Rule r : rules) {
				boolean dec = true;
					for(Fact f : r.getRequiredFacts()){
						while(dec){
							if(!facts.contains(f))
								dec = false;
						}
					}
					if (dec){
						facts.add(r.getDeducedFacts());
						rules.remove(r);
						inf = true;
						nbInf++;
					}
			}
		}
	}

}
