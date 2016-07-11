package de.elite12.musikbot.shared;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.elite12.musikbot.shared.Util;

@RunWith(Parameterized.class)
public class UtilgetVIDTest {
	
	private String in;
	private String expected;

	public UtilgetVIDTest(String link, String expe) {
		this.in = link;
		this.expected = expe;
	}

	@Test
	public final void testGetVID() {
		assertEquals(expected, Util.getVID(in));
	}
	
	
	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] {
				 {null,null},
				 {"",null},
				 {"asdblub",null},
				 {"http://www.youtube.com/watch?v=-wtIMTCHWuI","-wtIMTCHWuI"},
				 {"http://www.youtube.com/v/-wtIMTCHWuI?version=3&autohide=1","-wtIMTCHWuI"},
				 {"http://youtu.be/-wtIMTCHWuI","-wtIMTCHWuI"},
				 {"http://www.youtube.com/embed/0zM3nApSvMg?rel=0","0zM3nApSvMg"},
				 {"http://www.youtube.com/watch?v=0zM3nApSvMg&feature=feedrec_grec_index","0zM3nApSvMg"},
				 {"http://www.youtube.com/watch?v=0zM3nApSvMg#t=0m10s","0zM3nApSvMg"},
				 {"http://www.youtube.com/v/0zM3nApSvMg?fs=1&hl=en_US&rel=0","0zM3nApSvMg"},
				 {"http://www.youtube.com/e/dQw4w9WgXcQ","dQw4w9WgXcQ"},
				 {"http://www.youtube.com/?feature=player_embedded&v=dQw4w9WgXcQ","dQw4w9WgXcQ"}});
	}

}
