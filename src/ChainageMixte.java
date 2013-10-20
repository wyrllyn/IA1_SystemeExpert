import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ChainageMixte implements Chainage {
	
	public ChainageAvant CA;
	private Conflict conflict;
	
	public void procedure(List<Rule> rules, List<Fact> facts, Fact goal, Set<Fact> possibleFacts){
		boolean dem = false;
		boolean deductible = true;
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
		
		if (facts.contains(goal)) {
			System.out.println("goal is already in base facts");
			dem = true;
		}
		
		while(!dem){		
				CA.procedure(rules, facts, goal, possibleFacts);
			
			if (facts.contains(goal)) {
				System.out.println("goal is already in base facts");
				break;
			}
			
			if(!dem && demandables.contains(goal)){
				System.out.println("add "+goal.getName()+ " in the bf ? 1 for yes, anything for no");
				int val = scanner.nextInt ();
				if(val == 1)
					dem = true;
				else dem = false;
			}
			
			if(dem){
				System.out.println(" dem = true");
				facts.add(goal);
			}
		}
	}

	@Override
	public void setConflict(Conflict conflict) {
		this.conflict = conflict;
		
	}	
}


