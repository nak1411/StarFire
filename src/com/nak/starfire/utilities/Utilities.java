package com.nak.starfire.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

	public static void writeFile(ArrayList<Integer> tile, int width, int height, String fileName) {
		try {
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(width + " " + height + "\n");
			for (int y = 0; y < height; y++) {
				for (int x = 1; x < width; x++) {
					bw.write(tile.get(x).toString() + " ");
				}
				bw.write(tile.get(y).toString() + "\n");
			}

			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
