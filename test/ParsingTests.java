import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class ParsingTests {

	@Test
	public void testParseFile() throws ExpertException, IOException {
		Engine engine = SystemeExpert.parseFile("res/universe.txt");
		assertNotNull(engine);
	}

}
