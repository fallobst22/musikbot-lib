package de.elite12.musikbot.shared;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.elite12.musikbot.shared.Util.SpotifyPlaylistHelper;

@RunWith(Parameterized.class)
public class UtilgetSPIDTest {

    private String in;
    private String expected;

    public UtilgetSPIDTest(String link, String expe) {
        this.in = link;
        this.expected = expe;
    }

    @Test
    public final void testGetSID() {
        assertEquals(expected, Util.getSPID(in));
    }

    @SuppressWarnings("rawtypes")
    @Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][] { { null, null }, { "", null }, { "asdblub", null },
                { "spotify:user:rocketbeanstv:playlist:6Jp03bPknRPDM04qCzAsC7",
                        new SpotifyPlaylistHelper("rocketbeanstv", "6Jp03bPknRPDM04qCzAsC7") },
                { "https://open.spotify.com/user/rocketbeanstv/playlist/6Jp03bPknRPDM04qCzAsC7",
                        new SpotifyPlaylistHelper("rocketbeanstv", "6Jp03bPknRPDM04qCzAsC7") },
                { "spotify:user:1127622769:playlist:7Isff8LtbeEFmhqNlQGrpE",
                        new SpotifyPlaylistHelper("1127622769", "7Isff8LtbeEFmhqNlQGrpE") } });
    }

}
