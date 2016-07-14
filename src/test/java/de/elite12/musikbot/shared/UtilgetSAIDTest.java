package de.elite12.musikbot.shared;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class UtilgetSAIDTest {

    private String in;
    private String expected;

    public UtilgetSAIDTest(String link, String expe) {
        this.in = link;
        this.expected = expe;
    }

    @Test
    public final void testGetSID() {
        assertEquals(expected, Util.getSAID(in));
    }

    @SuppressWarnings("rawtypes")
    @Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] { { null, null }, { "", null }, { "asdblub", null },
                { "https://open.spotify.com/album/7n3QJc7TBOxXtlYh4Ssll8", "7n3QJc7TBOxXtlYh4Ssll8" },
                { "spotify:album:7n3QJc7TBOxXtlYh4Ssll8", "7n3QJc7TBOxXtlYh4Ssll8" },
                { "spotify:album:0PSrPgwe3rz2r4s7tS9GkH", "0PSrPgwe3rz2r4s7tS9GkH" } });
    }

}
