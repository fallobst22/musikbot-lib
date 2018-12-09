package de.elite12.musikbot.shared;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class UtilgetSIDTest {

	private String in;
	private String expected;
	
	public UtilgetSIDTest(String link, String expe) {
		this.in = link;
		this.expected = expe;
	}

	@Test
	public final void testGetSID() {
		assertEquals(expected, SongIDParser.getSID(in)); // TODO
	}
	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] {
				 {null,null},
				 {"",null},
				 {"asdblub",null},
				 {"https://play.spotify.com/track/0BCPKOYdS2jbQ8iyB56Zns","0BCPKOYdS2jbQ8iyB56Zns"},
				 {"https://play.spotify.com/track/60a0Rd6pjrkxjPbaKzXjfq","60a0Rd6pjrkxjPbaKzXjfq"},
				 {"https://play.spotify.com/track/5sNESr6pQfIhL3krM8CtZn","5sNESr6pQfIhL3krM8CtZn"},
				 {"http://play.spotify.com/track/60a0Rd6pjrkxjPbaKzXjfq","60a0Rd6pjrkxjPbaKzXjfq"},
				 {"http://play.spotify.com/track/5sNESr6pQfIhL3krM8CtZn","5sNESr6pQfIhL3krM8CtZn"},
				 {"spotify:track:5xioIP2HexKl3QsI8JDlG8","5xioIP2HexKl3QsI8JDlG8"},
				 {"http://open.spotify.com/track/5pqF0qKSQCEq5mt5CFEXoq","5pqF0qKSQCEq5mt5CFEXoq"},
				 {"https://open.spotify.com/track/6tBdTwcyyGq1HU3PXgZK82?si=rnSOfIimRraXvum_lzM3fw","6tBdTwcyyGq1HU3PXgZK82"}});
	}

}
