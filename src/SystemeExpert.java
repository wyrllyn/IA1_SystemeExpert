import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class SystemeExpert {
	private static List<Event> traceLog;
	private static Set<Fact> possibleFacts;
	protected enum LogLevel {
		ALL,
		RULES_ONLY
	}
	private static LogLevel logLevel = LogLevel.ALL;
	
	protected enum Strategy {
		CHAINAGE_AVANT,
		CHAINAGE_ARRIERE
	}
	private static Strategy strategy;
	
	public static void log(EventType type, String comment) {
		if (traceLog == null)
			traceLog = new LinkedList<Event>();
		Event event = new Event(type, comment);
		traceLog.add(event);
		switch (logLevel) {
		case RULES_ONLY:
			if (type != EventType.RULE_APPLICATION) {
				break;
			}
		case ALL:
			System.out.println(event);
		}
	}
	
	public static void main(String args[])
			throws ExpertException, IOException{
		String fileURL = "res/universe.txt";
		for (int i = 0; i < args.length; i++) {
			try {
				if (args[i] == "-file") {
					fileURL = args[++i];
				} else if (args[i] == "-strategy") {
					setStrategy(args[++i]);
				} else if (args[i] == "-logLevel") {
					setLogLevel(args[++i]);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("Insuficient number of arguments");
			}
		}
		Engine engine = parseFile(fileURL);
		
	}
	
	private static void setStrategy(String strat) {
		if (strat.equals("avant")) {
			strategy = Strategy.CHAINAGE_AVANT;
		} else if (strat.equals("arriere")) {
			strategy = Strategy.CHAINAGE_ARRIERE;
		} else {
			strategy = Strategy.CHAINAGE_AVANT;
			System.err.println("Invalid strategy, defaulting to " + strategy);
		}
	}

	private static void setLogLevel(String level) {
		if (level.equals("all")) {
			logLevel = LogLevel.ALL;
		} else if (level.equals("rules")) {
			logLevel = LogLevel.RULES_ONLY;
		} else {
			logLevel = LogLevel.ALL;
			System.err.println("Invalid log level, defaulting to " + logLevel);
			System.err.println("Valid entries: all, rules");
		}
		
	}

	public static Engine parseFile(String fileURL)
			throws ExpertException, IOException {
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
		possibleFacts = new HashSet<Fact>();
		log(EventType.PARSING_FILE, fileURL);
		while (line != null) {
			line = line.trim();
			if (ends(line)) {
				readBF = false;
				readGoal = false;
				readRules = false;
			} else if (optionalString("#BF", line)) {
				readBF = true;
				log(EventType.PARSING_BF, "");
			} else if (readBF) {
				baseFacts.add(new Fact(line));
				log(EventType.PARSING_BF, line);
			} else if (optionalString("#Goal", line)) {
				readGoal = true;
				log(EventType.PARSING_GOAL, "");
			} else if (readGoal) {
				goals.add(new Fact(line));
				log(EventType.PARSING_GOAL, line);
			} else if (optionalString("#Rules", line)) {
				readRules = true;
				log(EventType.PARSING_RULE_LIST, "");
			} else if (readRules && requiredString("$Rule:", line)) {
				readRules = parseRule(rules, br);
				continue; // skip the readLine()
			}
			
			line = br.readLine();
		}
		log(EventType.PARSING_FILE, "Parsing done");
		Engine engine = new Engine(baseFacts);
		engine.setRules(rules);
		engine.setGoals(goals);
		engine.setPossibleFacts(possibleFacts);
		System.out.println(engine);
		//TODO: validate?
		return engine;
	}

	private static boolean parseRule(List<Rule> rules, BufferedReader br)
			throws IOException, ExpertException {
		boolean readIf = false;
		boolean readThen = false;
		String line = br.readLine().trim();
		List<Fact> rf = new LinkedList<Fact>();
		Fact df = null;
		log(EventType.PARSING_RULE, "Begin");
		while (line != null
				&& !optionalString("$Rule:", line)
				&& !ends(line)) {
			if (!readIf && !readThen && requiredString("IF", line)) {
				readIf = true;
				log(EventType.PARSING_FACT, line);
				stuffFact(line, rf, readIf, "IF");
			} else if (readIf && optionalString("THEN", line)) {
				readIf = false;
				readThen = true;
				log(EventType.PARSING_FACT, line);
				df = stuffFact(line, rf, readIf, "THEN");
			} else if ((readIf || readThen) && optionalString("AND", line)) {
				log(EventType.PARSING_FACT, line);
				stuffFact(line, rf, readIf, "AND");
			}
			line = br.readLine().trim();
		}
		log(EventType.PARSING_RULE, "End");
		//TODO:check if everything is OK
		rules.add(new Rule(rf, df));
		return !ends(line);
	}

	private static Fact stuffFact(String line, List<Fact> rf,
			boolean readIf, String toRemove) throws ExpertException {
		line = line.replace(toRemove, "").trim();
		Fact fact = new Fact(line);
		if (readIf)
			rf.add(fact);
		possibleFacts.add(fact);
		return fact;
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

	public static final String RES_UNIVERSE_TXT = "res/universe.txt";
}
