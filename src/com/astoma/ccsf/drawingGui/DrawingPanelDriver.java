package com.astoma.ccsf.drawingGui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingPanelDriver {

	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JPanel panel = new DrawingPanel();
				JFrame frame = new JFrame("Drawing Board");
				frame.setSize(400, 400);
				frame.getContentPane().add(panel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
