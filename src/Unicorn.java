import java.util.List;


public class Unicorn {
	private List<Fact> bf;
	private List<Rule> rules;
	private Rule toApply;
	
	public Unicorn(List<Fact> bf, List<Rule> rules){
		this.bf = bf;
		this.rules = rules;
		
		for (Rule r : rules){
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
				toApply = r;
			}
		}
	}
	
	public Rule getToApply(){
		return toApply;
	}
}
