import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class ChainageArriereTests {

	@Test
	public void test() throws ExpertException, IOException {
		Engine engine = SystemeExpert.parseFile(SystemeExpert.RES_UNIVERSE_TXT);
		ChainageArriere ca = new ChainageArriere();
		ca.verif(engine.getGoals(),
				engine.getInitialFacts(),
				engine.getRules(),
				engine.getPossibleFacts());
		for (Fact f : engine.getFacts()) {
			System.out.println(f);
		}
		assertTrue(true);//lol no error
	}

}
