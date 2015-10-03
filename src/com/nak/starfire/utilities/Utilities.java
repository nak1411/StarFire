package com.nak.starfire.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utilities {

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

	public static void writeFile(int[][] tile, int width, int height, String fileName) {
		try {
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(width + " " + height + "\n");
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					bw.write(String.valueOf(tile[x][y]) + " ");
				}
				bw.write("\n");
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
