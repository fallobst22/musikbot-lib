package de.elite12.musikbot.shared;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GetSAIDTest {

	private String in;
	private String expected;
	
	public GetSAIDTest(String link, String expe) {
		this.in = link;
		this.expected = expe;
	}

	@Test
	public final void testGetSID() {
		assertEquals(expected, Util.getSAID(in)); // TODO
	}
	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] {
				 {null,null},
				 {"",null},
				 {"asdblub",null},
				 {"https://play.spotify.com/track/0BCPKOYdS2jbQ8iyB56Zns",null},
				 {"http://open.spotify.com/album/3qW0KwoP8BdFzqZSDh1ScI","3qW0KwoP8BdFzqZSDh1ScI"},
				 {"spotify:album:3qW0KwoP8BdFzqZSDh1ScI","3qW0KwoP8BdFzqZSDh1ScI"}});
	}

}
