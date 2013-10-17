import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class ParsingTests {

	@Test
	public void testParseFile() throws ExpertException, IOException {
		Engine engine = SystemeExpert.parseFile(SystemeExpert.RES_UNIVERSE_TXT);
		assertNotNull(engine);
	}

}
