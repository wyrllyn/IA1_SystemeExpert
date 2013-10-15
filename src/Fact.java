import java.util.ArrayList;
import java.util.List;


public class Fact {
	
	private String name;
	
	private static List<String> possibleFacts = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Fact(String n) throws ExpertException{
		if (possibleFacts == null) {
			possibleFacts = new ArrayList<String>();
			possibleFacts.add("slave");
			possibleFacts.add("bonne_adaptabilite");
			possibleFacts.add("leadership");
			possibleFacts.add("anglais_parle");
			possibleFacts.add("neerl_parle");
			possibleFacts.add("langue_facile");
			possibleFacts.add("poste_resp");
			possibleFacts.add("dynamique");
			possibleFacts.add("accepte");
		}
		
		if (possibleFacts.contains(n)){
			name = n;
		}
		else throw new ExpertException(" invalid fact");
			
	}

}
