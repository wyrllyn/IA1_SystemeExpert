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
				System.out.println("one rule is tested");
				boolean dec = true;
					for(Fact f : r.getRequiredFacts()){
						while(dec){
							System.out.println(" is "+f.getName()+" in the bf ?");
							if(!facts.contains(f)){
								dec = false;
								System.out.println("---------" + f.getName()+"  isn't in the bf");
							}
							else System.out.println("---------" + f.getName()+" is in the bf");
						}
					}
					if (dec){
						facts.add(r.getDeducedFacts());
						System.out.println(" new fact in the bf : "+r.getDeducedFacts());
						rules.remove(r);
						System.out.println(" rule was applied => delete");
						inf = true;
						nbInf++;
					}
			}
		}
	}

}
