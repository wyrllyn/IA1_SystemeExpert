import java.util.List;



public class Rule {
	
	private List<Fact> requiredFacts;
	private Fact deducedFact;
	private int ruleNumber;
	private static int ruleCount = 0;
	
	/**
	 * Creates a Rule. Note: assumes provided facts are valid!
	 * @param rf
	 * @param df
	 * @see Fact
	 */
	public Rule(List<Fact> rf, Fact df){
		requiredFacts = rf;
		deducedFact = df;
		ruleNumber = ruleCount;
		ruleCount++;
	}

	public List<Fact> getRequiredFacts() {
		return requiredFacts;
	}

	public void setRequiredFacts(List<Fact> requiredFacts) {
		this.requiredFacts = requiredFacts;
	}

	public Fact getDeducedFacts() {
		return deducedFact;
	}

	public void setDeducedFacts(Fact deducedFact) {
		this.deducedFact = deducedFact;
	}
}
