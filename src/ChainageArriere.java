import java.util.LinkedList;
import java.util.List;


public class ChainageArriere {
	
	public boolean demo(Fact goal, List<Fact> facts, List<Rule> rules){
		boolean dem = false;
		//list demandable
		List<Fact> demandables = new LinkedList<Fact>();
		
		// initialization of demandables
		//TODO
		
		// first case
		if (facts.contains(goal))
			dem = true;
		
		// second case
		//TODO modifier
		for (Rule r : rules){
			if (r.getDeducedFacts() == goal){
				while(!dem){	
					dem = verif(r.getRequiredFacts(),facts, rules);
				}
			}
		}
		
		// third case
			
		if(!dem && demandables.contains(goal)){
						//TODO
		}
		
		if(dem){
			facts.add(goal);
		}
		
		return dem;
	}
	
	public boolean verif(List<Fact> goals, List<Fact> bf, List<Rule> rules){
		boolean ver = true;
		
		//TODO modifier cette grossiere erreur
	
		for(Fact f : goals){
			while(ver){
				ver = demo(f, bf, rules);
			}
		}
		return ver;
	}
}
