import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class ChainageAvantTests {

	@Test
	public void testProcedure() throws ExpertException, IOException {
		Engine engine = SystemeExpert.parseFile(SystemeExpert.DEFAULT_FILE_URL);
		ChainageAvant ca = new ChainageAvant();
		ca.procedure(engine.getRules(), engine.getFacts(), engine.getGoal(), engine.getPossibleFacts());
		assertTrue(engine.getFacts().contains(new Fact("accepte")));
	}

}
