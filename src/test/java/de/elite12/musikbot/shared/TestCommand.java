package de.elite12.musikbot.shared;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestCommand {

	private Integer input;
	public TestCommand(Integer in) {
		this.input = in;
	}

	
	@Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] {
				 {Integer.MIN_VALUE},
				 {-5},
				 {-1},
				 {0},
				 {1},
				 {5},
				 {Integer.MAX_VALUE}});
	}
	@Test
	public final void testCommandInt() {
		Command c = new Command(this.input);
		assertEquals(this.input, new Integer(c.getCmd()));
		assertNull(c.getdata());
	}
	@Test
	public final void testCommandequal() {
		assertEquals(new Command(this.input), new Command(this.input));
	}

	/*@Test
	public final void testCommandIntObject() {
		fail("Not yet implemented");
	}
	*/
}
