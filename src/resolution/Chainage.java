package resolution;
import java.util.List;
import java.util.Set;

import resolution.conflict.Conflict;
import struct.Fact;
import struct.Rule;



public interface Chainage {
	public void procedure(List<Rule> rules, List<Fact> facts,  Fact goal, Set<Fact> possibleFacts);

	public void setConflict(Conflict conflict);
}
