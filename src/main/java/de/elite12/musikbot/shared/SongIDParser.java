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

    public static String getSPID(String spotifyUrl) {
        if (spotifyUrl != null && spotifyUrl.trim().length() > 0) {
            String expression = "^(?:spotify:playlist:|(?:http|https)\\:\\/\\/(?:play|open)\\.spotify\\.com\\/playlist\\/)([a-zA-Z0-9]{22})(?:.*)$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(spotifyUrl);
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return null;
    }
}
