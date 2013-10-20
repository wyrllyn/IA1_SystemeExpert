import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;


public class MainTests {
	
	private String args[] = new String[] {
			"-file",
			SystemeExpert.DEFAULT_FILE_URL,
			"-strategy",
			"arriere",
			"-logLevel",
			"rules"
	};
	
	@Test
	public void testParseFile() throws ExpertException, IOException {
		Engine engine = SystemeExpert.parseFile(SystemeExpert.DEFAULT_FILE_URL);
		assertNotNull(engine);
	}
	
	@Test
	public void testMain() throws ExpertException, IOException {
		SystemeExpert.main(args);
	}

}
