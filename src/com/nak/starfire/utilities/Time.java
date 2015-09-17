package com.nak.starfire.utilities;

public class Time {

	public static final long SECOND = 1000000000L;
	public static long delta;

	public static long getTime() {
		return System.nanoTime();
	}

	public static long getDelta() {
		return delta;
	}

	public static void setDelta(long delta) {
		Time.delta = delta;
	}

}
