package de.elite12.musikbot.shared;

import de.elite12.musikbot.shared.util.SongIDParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

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
        assertEquals(expected, SongIDParser.getSAID(in));
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
