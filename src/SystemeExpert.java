import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


public class SystemeExpert {
	
	
	public static void main(String args[]) throws ExpertException{
		List<Fact> baseFacts = new LinkedList<Fact>();
		baseFacts.add(new Fact("slave"));
		baseFacts.add(new Fact("poste_resp"));
		
	}
	
	public static Engine readFile(String fileURL) throws ExpertException, IOException {
		FileInputStream fis = new FileInputStream(fileURL);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine().trim();
		boolean readBF = false;
		boolean readGoal = false;
		boolean readRules = false;
		List<Fact> baseFacts = new LinkedList<Fact>();
		List<Fact> goals = new LinkedList<Fact>();
		List<Rule> rules = new LinkedList<Rule>();
		while (line != null) {
			if (ends(line)) {
				readBF = false;
				readGoal = false;
				readRules = false;
			} else if (optionalString("BF:", line)) {
				readBF = true;
			} else if (readBF) {
				baseFacts.add(new Fact(line));
			} else if (optionalString("Goal", line)) {
				readGoal = true;
			} else if (readGoal) {
				goals.add(new Fact(line));
			} else if (optionalString("#Rules", line)) {
				readRules = true;
			} else if (readRules && requiredString("$Name:", line)) {
				readRule(rules, br);
				continue; // skip the readLine()
			}
			
			line = br.readLine().trim();
		}
		//TODO: validate?
		
		return null;
	}

	private static void readRule(List<Rule> rules, BufferedReader br)
			throws IOException, ExpertException {
		boolean readIf = false;
		boolean readThen = false;
		String line = br.readLine().trim();
		while (line != null
				&& !optionalString("$Rule:", line)
				&& !ends(line)) {
			if (!readIf && !readThen && requiredString("IF", line)) {
				readIf = true;
				//truncate + save fact
			} else if (readIf && optionalString("THEN", line)) {
				readIf = false;
				readThen = true;
				//stuff fact
			} else if ((readIf || readThen) && requiredString("AND", line)) {
				
			}
		}
	}

	private static boolean requiredString(String string, String line)
			throws ExpertException {
		if (!optionalString(string, line))
			throw new ExpertException("Expected String not found:" + string);
		else
			return true;
	}

	private static boolean ends(String line) {
		return line.startsWith("#End");
	}
	
	private static boolean optionalString(String string, String line) {
		if (!line.startsWith(string))
			return false;
		else
			return true;
	}
}
