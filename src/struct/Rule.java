package struct;
import java.util.List;



public class Rule {
	
	@Override
	public String toString() {
		return "Rule #" + ruleNumber + ", deducedFact=" + deducedFact
				+ " [requiredFacts=" + requiredFacts + "]";
	}

	private List<Fact> requiredFacts;
	private Fact deducedFact;
	private int ruleNumber;
	private int priority;
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
		priority = 0;
		ruleCount++;
	}



	public List<Fact> getRequiredFacts() {
		return requiredFacts;
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public void setPriority(int prior){
		this.priority = prior;
	}

	public void setRequiredFacts(List<Fact> requiredFacts) {
		this.requiredFacts = requiredFacts;
	}

	public Fact getDeducedFact() {
		return deducedFact;
	}

	public void setDeducedFacts(Fact deducedFact) {
		this.deducedFact = deducedFact;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deducedFact == null) ? 0 : deducedFact.hashCode());
		result = prime * result
				+ ((requiredFacts == null) ? 0 : requiredFacts.hashCode());
		result = prime * result + ruleNumber;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (deducedFact == null) {
			if (other.deducedFact != null)
				return false;
		} else if (!deducedFact.equals(other.deducedFact))
			return false;
		if (requiredFacts == null) {
			if (other.requiredFacts != null)
				return false;
		} else if (!requiredFacts.equals(other.requiredFacts))
			return false;
		if (ruleNumber != other.ruleNumber)
			return false;
		return true;
	}
}
