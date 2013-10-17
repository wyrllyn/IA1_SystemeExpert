import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class ChainageAvantTests {

	@Test
	public void testProcedure() throws ExpertException, IOException {
		Engine engine = SystemeExpert.parseFile(SystemeExpert.RES_UNIVERSE_TXT);
		ChainageAvant ca = new ChainageAvant();
		ca.procedure(engine.getRules(), engine.getFacts());
		assertTrue(engine.getFacts().contains(new Fact("accepte")));
	}

}
