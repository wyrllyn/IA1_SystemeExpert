import static org.junit.Assert.*;

import java.io.IOException;

import main.ExpertException;
import main.SystemeExpert;

import org.junit.Test;

import resolution.ChainageAvant;
import resolution.Engine;
import resolution.conflict.Conflict;
import struct.Fact;



public class ChainageAvantTests {

	@Test
	public void testProcedure() throws ExpertException, IOException {
		Engine engine = SystemeExpert.parseFile(SystemeExpert.DEFAULT_FILE_URL);
		ChainageAvant ca = new ChainageAvant();
		ca.procedure(engine.getRules(), engine.getFacts(), engine.getGoal(), engine.getPossibleFacts());
		assertTrue(engine.getFacts().contains(new Fact("accepte")));
	}
	
	@Test
	public void testWithUnicorn() throws ExpertException, IOException {
		Engine engine = SystemeExpert.parseFile(SystemeExpert.DEFAULT_FILE_URL);
		ChainageAvant ca = new ChainageAvant();
		ca.setConflict(Conflict.PREMISSES);
		ca.procedure(engine.getRules(), engine.getFacts(), engine.getGoal(), engine.getPossibleFacts());
		assertTrue(engine.getFacts().contains(new Fact("accepte")));
	}

}
