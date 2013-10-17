import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ChainageArriere {
	
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
		System.out.println("This is our goal:" + goal);
		
		// first case
		if (facts.contains(goal)) {
			System.out.println("goal is already in base facts");
			dem = true;
		}
		
		// second case
		int i = 0;
		do {
			Rule r = rules.get(i);
			if (!dem && r.getDeducedFact() == goal){
				System.out.println("This rule achieves our goal:" + r);
				dem = verif(r.getRequiredFacts(),facts, rules, possibleFacts);
			}
			i++;
		} while (dem == false && i < rules.size());
		
		// third case
			
		if(!dem && demandables.contains(goal)){
			System.out.println("add "+goal.getName()+ "in the bf ? 1 for yes, anything for no");
			int val = scanner.nextInt ();
			if(val == 1)
				dem = true;
			else dem = false;
		}
		
		if(dem){
			System.out.println(" dem = true");
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
}
