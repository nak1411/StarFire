package com.nak.starfire.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.nak.starfire.Game;

public class Utilities {
	
	//Centers stuff to the screen.  Useful for player entity.
	//NOTE: Currently set for 32x32 sprites.
	public static int xCenter = (Game.WIDTH * Game.SCALE / 2) - 12;
	public static int yCenter = (Game.HEIGHT * Game.SCALE / 2) - 32;

	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
