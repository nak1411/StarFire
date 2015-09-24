package com.nak.starfire.gfx;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import com.nak.starfire.Game;

public class Window {

	private JFrame frame;
	private Dimension size;

	public Window(int width, int height, int scale, String title, Game game) {
		
		size = new Dimension(width * scale, height * scale);
		frame = new JFrame(title);

		frame.setPreferredSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.add(game);
		frame.setSize(size);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		showOnScreen(2, frame);
		
		game.start();

	}
	
	public static void showOnScreen( int screen, JFrame frame ) {
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gd = ge.getScreenDevices();
	    if( screen > -1 && screen < gd.length ) {
	        frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
	    } else if( gd.length > 0 ) {
	        frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());
	    } else {
	        throw new RuntimeException( "No Screens Found" );
	    }
	}
}
