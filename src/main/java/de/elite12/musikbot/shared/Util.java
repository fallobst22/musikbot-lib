package de.elite12.musikbot.shared;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.TrackRequest;
import com.wrapper.spotify.models.Track;

public class Util {
	private static final String clientId = "32f75446eac041a7bc8b35330d289558";
	private static final String clientSecret = "6506ca3b6d494c0f8c7c22b30218e9b4";

	private static final Api api = Api.builder().clientId(clientId)
			.clientSecret(clientSecret).build();

	public static String getVID(String youtubeUrl) {
		if (youtubeUrl != null && youtubeUrl.trim().length() > 0
				&& youtubeUrl.startsWith("http")) {

			String expression = "^.*(?:youtu.be\\/|v\\/|e\\/|u\\/\\w+\\/|embed\\/|v=)([^#\\&\\?]*).*"; // var
			// regExp
			// =
			// /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
			CharSequence input = youtubeUrl;
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(input);
			if (matcher.matches()) {
				String groupIndex1 = matcher.group(1);
				if (groupIndex1 != null && groupIndex1.length() == 11)
					return groupIndex1;
			}
		}
		return null;
	}

	public static String getPID(String youtubeUrl) {
		if (youtubeUrl != null && youtubeUrl.trim().length() > 0
				&& youtubeUrl.startsWith("http")) {

			String expression = "^(?:http|https)\\:\\/\\/www\\.youtube\\.com\\/.*list=([a-zA-Z0-9_-]{10,}).*$";
			CharSequence input = youtubeUrl;
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(input);
			if (matcher.matches()) {
				String groupIndex1 = matcher.group(1);
				if (groupIndex1 != null && groupIndex1.length() > 0)
					return groupIndex1;
			}
		}
		return null;
	}

	public static String getSID(String spotifyUrl) {
		if (spotifyUrl != null && spotifyUrl.trim().length() > 0) {
			String expression = "^(?:spotify:track:|(?:http|https)\\:\\/\\/(?:play|open)\\.spotify\\.com\\/track\\/)([a-zA-Z0-9_]{22})$";
			CharSequence input = spotifyUrl;
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(input);
			if (matcher.matches()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	public static String getSAID(String spotifyUrl) {
		if (spotifyUrl != null && spotifyUrl.trim().length() > 0) {
			String expression = "^(?:spotify:album:|(?:http|https)\\:\\/\\/(?:play|open)\\.spotify\\.com\\/album\\/)([a-zA-Z0-9]{22})$";
			CharSequence input = spotifyUrl;
			Pattern pattern = Pattern.compile(expression,
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(input);
			if (matcher.matches()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	public static Track getTrack(String sid) {
		if (sid == null) {
			return null;
		}
		TrackRequest r = api.getTrack(sid).build();
		try {
			Track t = r.get();
			return t;
		} catch (Exception e) {
			Logger.getLogger(Util.class).error("Error reading Track", e);
			return null;
		}
	}

	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(
					1, 3));
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
}
