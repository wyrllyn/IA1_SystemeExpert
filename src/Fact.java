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
	
	public Fact(String n){
		name = n;
		if (possibleFacts == null) {
			possibleFacts = new ArrayList<String>();
			possibleFacts.add("stuff"); //TODO:moar facts
		}
		
	}

}
