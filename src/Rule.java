import java.util.List;



public class Rule {
	
	private List<Fact> requiredFacts;
	private List<Fact> deducedFacts;
	
	/**
	 * Creates a Rule. Note: assumes provided facts are valid!
	 * @param rf
	 * @param df
	 * @see Fact
	 */
	public Rule(List<Fact> rf, List<Fact> df){
		requiredFacts = rf;
		deducedFacts = df;
	}

	public List<Fact> getRequiredFacts() {
		return requiredFacts;
	}

	public void setRequiredFacts(List<Fact> requiredFacts) {
		this.requiredFacts = requiredFacts;
	}

	public List<Fact> getDeducedFacts() {
		return deducedFacts;
	}

	public void setDeducedFacts(List<Fact> deducedFacts) {
		this.deducedFacts = deducedFacts;
	}
}
