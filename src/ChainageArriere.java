import java.util.LinkedList;
import java.util.List;


public class ChainageArriere {
	
	public boolean demo(Fact goal, List<Fact> facts, List<Rule> rules){
		boolean dem = false;
		char var = ' ';
		//list demandable
		List<Fact> demandables = new LinkedList<Fact>();
		
		// initialization of demandables
		//TODO
		
		// first case
		if (facts.contains(goal))
			dem = true;
		
		// second case
		for (Rule r : rules){
			if (r.getDeducedFacts() == goal && !dem){
				dem = verif(r.getRequiredFacts(),facts, rules);
			}
		}
		
		// third case
			
		if(!dem && demandables.contains(goal)){
				System.out.println("add "+goal.getName()+ "in the bf ? (y/n) if you don't know, choose n");
				//TODO : get value
				if(var == 'y')
					dem = true;
				else dem = false;
		}
		
		if(dem){
			facts.add(goal);
		}
		
		return dem;
	}
	
	public boolean verif(List<Fact> goals, List<Fact> bf, List<Rule> rules){
		boolean ver = true;
	
		for(Fact f : goals){
			if(ver){
				ver = demo(f, bf, rules);
			}
		}
		return ver;
	}
}
