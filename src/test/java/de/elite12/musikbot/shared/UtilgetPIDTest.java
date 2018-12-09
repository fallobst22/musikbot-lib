package de.elite12.musikbot.shared;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.elite12.musikbot.shared.SongIDParser;

@RunWith(Parameterized.class)
public class UtilgetPIDTest {
	
	private String in;
	private String expected;

	public UtilgetPIDTest(String link, String expe) {
		this.in = link;
		this.expected = expe;
	}

	@Test
	public final void testGetPID() {
		assertEquals(expected, SongIDParser.getPID(in));
	}
	
	
	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] {
				 {null,null},
				 {"",null},
				 {"asdblub",null},
				 {"https://www.youtube.com/watch?v=OZq_T-EAg2M&list=PL6D4C31FFA7EBABB5","PL6D4C31FFA7EBABB5"},
				 {"http://www.youtube.com/watch?v=OZq_T-EAg2M&feature=share&list=PL6D4C31FFA7EBABB5&index=1","PL6D4C31FFA7EBABB5"},
				 {"https://www.youtube.com/playlist?list=PL6D4C31FFA7EBABB5","PL6D4C31FFA7EBABB5"},
				 {"https://www.youtube.com/playlist?list=LLOxHw7X2HJxnljTc6Vmz1Mw","LLOxHw7X2HJxnljTc6Vmz1Mw"},
				 {"https://www.youtube.com/watch?v=zOEcv7h4xvw&list=LLOxHw7X2HJxnljTc6Vmz1Mw","LLOxHw7X2HJxnljTc6Vmz1Mw"},
				 {"https://www.youtube.com/playlist?list=PL2DcFzGCdNxEvB051ZfCS-LBczfVK_YPg","PL2DcFzGCdNxEvB051ZfCS-LBczfVK_YPg"},
				 {"http://www.youtube.com/playlist?list=UU9YLd0REiXxLqQQH_CpJKZQ","UU9YLd0REiXxLqQQH_CpJKZQ"}});
	}

}
