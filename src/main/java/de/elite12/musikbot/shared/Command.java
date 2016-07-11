package de.elite12.musikbot.shared;

import java.io.Serializable;

public class Command implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2638416315216366043L;
	/**
	 * 
	 */

	public static final int WRONG_COMMAND_SECQUENCE = -2;
	public static final int INVALID = -1;
	public static final int EMPTY = 0;
	public static final int STOP = 1;
	@Deprecated
	public static final int START = 2;
	public static final int PAUSE = 3;
	@Deprecated
	public static final int RESUME = 4;
	public static final int REQUEST_SONG = 5;
	public static final int SONG_FINISHED = 6;
	public static final int NO_SONG_AVAILABLE = 7;
	public static final int SONG = 8;
	@Deprecated
	public static final int VOLUME = 9;
	public static final int PLAYBACK_ERROR = 10;

	public static final int REQUEST_AUTH = 100;
	public static final int AUTH = 101;

	private int command = 0;
	private Object data = null;

	public Command(int cmd) {
		this.command = cmd;
	}

	public Command(int cmd, Object value) {
		this.command = cmd;
		this.data = value;
	}

	public int getCmd() {
		return this.command;
	}

	public Object getdata() {
		return this.data;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Command c = (Command) obj;
		return (this.getCmd() == c.getCmd())
				&& (this.getdata() != null ? this.getdata().equals(c.getdata())
						: c.getdata() == null);
	}

	@Override
	public String toString() {
		return "Command: " + this.command + " with Data: " + this.data + " @"
				+ Integer.toHexString(this.hashCode());
	}

	@Override
	public int hashCode() {
		int hc = 17;
		int hashMultiplier = 59;

		hc = hc * hashMultiplier + this.command;
		if (this.data != null) {
			hc = hc * hashMultiplier + this.data.hashCode();
		} else {
			hc = hc * hashMultiplier + 0;
		}

		return hc;
	}
}
