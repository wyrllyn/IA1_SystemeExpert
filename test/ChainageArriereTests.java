import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class ChainageArriereTests {
	private Engine engine;
	private ChainageArriere ca;
	
	@Before
	public void setup() throws ExpertException, IOException {
		engine = SystemeExpert.parseFile(SystemeExpert.RES_UNIVERSE_TXT);
		ca = new ChainageArriere();
	}
	
	@After
	public void teardown() {
		for (Fact f : engine.getFacts()) {
			System.out.println(f);
		}
	}
	
	@Ignore
	@Test
	public void test() throws ExpertException, IOException {
		engine = SystemeExpert.parseFile(SystemeExpert.RES_UNIVERSE_TXT);
		ca = new ChainageArriere();
		ca.demo(new Fact("accepte"),
				engine.getFacts(),
				engine.getRules(),
				engine.getPossibleFacts());
		assertTrue(true);//lol no error
	}
	
	@Test
	public void testWithPony() {
		Pony pony = new Pony(engine.getRules());
		engine.setRules(pony.getNewList());
		ca.demo(new Fact("accepte"),
				engine.getFacts(),
				engine.getRules(),
				engine.getPossibleFacts());
	}
}
