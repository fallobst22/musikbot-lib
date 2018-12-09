package de.elite12.musikbot.shared;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;

public class Util {
    private static final String clientId = "32f75446eac041a7bc8b35330d289558";
    private static final String clientSecret = "6506ca3b6d494c0f8c7c22b30218e9b4";

    private static final SpotifyApi api = new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret).build();

    private static boolean authorized = false;

    private static synchronized void check() {
        if (authorized) {
            return;
        }
        
        ClientCredentialsRequest request = api.clientCredentials().build();
        try {
            ClientCredentials c = request.execute();
            api.setAccessToken(c.getAccessToken());
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Util.authorized = false;
                }
            }, (long) (c.getExpiresIn() * 1000 * 0.8));
            authorized = true;
        } catch (IOException | SpotifyWebApiException e) {
            Logger.getLogger(Util.class).error("Error refreshing Token", e);
        }
    }

    public static String getVID(String youtubeUrl) {
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http")) {

            String expression = "^.*(?:youtu.be\\/|v\\/|e\\/|u\\/\\w+\\/|embed\\/|v=)([^#\\&\\?]*).*"; // var
            // regExp
            // =
            // /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String groupIndex1 = matcher.group(1);
                if (groupIndex1 != null && groupIndex1.length() == 11) {
                    return groupIndex1;
                }
            }
        }
        return null;
    }

    public static String getPID(String youtubeUrl) {
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http")) {

            String expression = "^(?:http|https)\\:\\/\\/www\\.youtube\\.com\\/.*list=([a-zA-Z0-9_-]{10,}).*$";
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String groupIndex1 = matcher.group(1);
                if (groupIndex1 != null && groupIndex1.length() > 0) {
                    return groupIndex1;
                }
            }
        }
        return null;
    }

    public static String getSID(String spotifyUrl) {
        if (spotifyUrl != null && spotifyUrl.trim().length() > 0) {
            String expression = "^(?:spotify:track:|(?:http|https)\\:\\/\\/(?:play|open)\\.spotify\\.com\\/track\\/)([a-zA-Z0-9_]{22})(?:.*)$";
            CharSequence input = spotifyUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    public static String getSAID(String spotifyUrl) {
        if (spotifyUrl != null && spotifyUrl.trim().length() > 0) {
            String expression = "^(?:spotify:album:|(?:http|https)\\:\\/\\/(?:play|open)\\.spotify\\.com\\/album\\/)([a-zA-Z0-9]{22})(?:.*)$";
            CharSequence input = spotifyUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    public static class SpotifyPlaylistHelper {
        public String user;
        public String pid;

        public SpotifyPlaylistHelper(String u, String p) {
            user = u;
            pid = p;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (pid == null ? 0 : pid.hashCode());
            result = prime * result + (user == null ? 0 : user.hashCode());
            return result;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            SpotifyPlaylistHelper other = (SpotifyPlaylistHelper) obj;
            if (pid == null) {
                if (other.pid != null) {
                    return false;
                }
            } else if (!pid.equals(other.pid)) {
                return false;
            }
            if (user == null) {
                if (other.user != null) {
                    return false;
                }
            } else if (!user.equals(other.user)) {
                return false;
            }
            return true;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "https://open.spotify.com/user/" + user + "/playlist/" + pid;
        }
    }

    public static SpotifyPlaylistHelper getSPID(String spotifyUrl) {
        if (spotifyUrl != null && spotifyUrl.trim().length() > 0) {
            String expression = "^(?:spotify:user:([\\S\\d]+):playlist:|(?:http|https)\\:\\/\\/(?:play|open)\\.spotify\\.com\\/user\\/([\\S\\d]+)\\/playlist\\/)([a-zA-Z0-9]{22})(?:.*)$";
            CharSequence input = spotifyUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return new SpotifyPlaylistHelper(matcher.group(1) == null ? matcher.group(2) : matcher.group(1),
                        matcher.group(3));
            }
        }
        return null;
    }

    public static Track getTrack(String sid) {
        if (sid == null || sid.isEmpty()) {
            return null;
        }
        check();
        GetTrackRequest r = api.getTrack(sid).build();
        try {
            Track t = r.execute();
            //if (!t.getAvailableMarkets().contains("DE") && !t.getAvailableMarkets().isEmpty()) {
            //    t = null;
            //}
            return t;
        } catch (IOException | SpotifyWebApiException e) {
            Logger.getLogger(Util.class).error("Error reading Track", e);
            return null;
        }
    }
    
    public static Track getTrackRaw(String sid) throws SpotifyWebApiException, IOException {
        if (sid == null || sid.isEmpty()) {
            return null;
        }
        check();
        GetTrackRequest r = api.getTrack(sid).build();
        Track t = r.execute();
        return t;
    }

    public static Album getAlbum(String sid) {
        if (sid == null || sid.isEmpty()) {
            return null;
        }
        check();
        GetAlbumRequest r = api.getAlbum(sid).build();
        try {
            Album t = r.execute();
            return t;
        } catch (IOException | SpotifyWebApiException e) {
            Logger.getLogger(Util.class).error("Error reading Album", e);
            return null;
        }
    }

    public static Playlist getPlaylist(String uid, String sid) {
        if (sid == null || uid == null || sid.isEmpty() || uid.isEmpty()) {
            return null;
        }
        check();
        
        //TODO: Change according to new Playlist format
        //This has been rolled back by spotify
        //https://developer.spotify.com/community/news/2018/06/12/changes-to-playlist-uris/
        @SuppressWarnings("deprecation")
		GetPlaylistRequest r = api.getPlaylist(uid, sid).build();
        try {
            Playlist t = r.execute();
            return t;
        } catch (IOException | SpotifyWebApiException e) {
            Logger.getLogger(Util.class).error("Error reading Playlist", e);
            return null;
        }
    }

    public static Track getTrackfromPlaylist(String uid, String sid, int id) {
        if (sid == null || uid == null || sid.isEmpty() || uid.isEmpty()) {
            return null;
        }
        check();
        //TODO: s.o.
        @SuppressWarnings("deprecation")
        GetPlaylistsTracksRequest r = api.getPlaylistsTracks(uid, sid).limit(1).offset(id).build();
        try {
        	Paging<PlaylistTrack> t = r.execute();
            return t.getItems()[0].getTrack();
        } catch (IOException | SpotifyWebApiException | ArrayIndexOutOfBoundsException e) {
            Logger.getLogger(Util.class).error("Error reading Playlist", e);
            return null;
        }
    }

    public static Integer getPlaylistlength(String uid, String sid) {
        if (sid == null || uid == null || sid.isEmpty() || uid.isEmpty()) {
            return null;
        }
        check();
        
        return Util.getPlaylist(uid, sid).getTracks().getTotal();
    }

    public static String hex(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString(array[i] & 0xFF | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static String md5Hex(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return hex(md.digest(message.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
}
