package com.nak.starfire.gfx;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.nak.starfire.Game;

public class Window {

	private JFrame frame;
	private Dimension size;

	public Window(int width, int height, int scale, String title, Game game) {
		size = new Dimension(width * scale, height * scale);
		frame = new JFrame(title);

		frame.setPreferredSize(size);
		frame.setMaximumSize(size);
		frame.setMinimumSize(size);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.add(game);
		frame.setLocationRelativeTo(null);
		frame.setSize(size);
		frame.setResizable(false);
		frame.setVisible(true);

		game.start();

	}
}
