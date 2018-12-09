package de.elite12.musikbot.shared;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongIDParser {
    

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
}
