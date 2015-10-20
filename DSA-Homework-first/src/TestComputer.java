import static org.junit.Assert.*;
import org.junit.Test;

public class TestComputer {

	@Test
	public void testInitialization() {
		Computer computer = new Computer();
		assertEquals(0, computer.getProcSpeed());
		String[] files = {"sth", "sthaa"};
		Computer computer1 = new Computer("haha", 1000.0, files);
		assertEquals(files, computer1.getFiles());
		assertEquals(1000.0, computer1.getProcSpeed());
		assertEquals("haha", computer1.getType());
		Computer computer2 = computer1;
		assertEquals(files, computer2.getFiles());
		assertEquals(1000.0, computer2.getProcSpeed());
		assertEquals("haha", computer2.getType());
	}
	@Test
	public void testGettersSetters(){
		String[] files = {"sth", "sthaa"};
		Computer computer1 = new Computer("haha", 1000.0, files);
	}
}
