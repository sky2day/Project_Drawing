package com.astoma.ccsf.drawingGui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author A.Stoma Dec 17, 2016
 */

public class DrawingPanel extends JPanel {
	private JRadioButton blueMarker, redMarker, greenMarker, eraserButton;
	private JButton clearButton;
	private JPanel buttonsPanel;
	private ButtonGroup group;
	private Marker marker;
	private boolean markerIsOn = false;
	private boolean resetIsClicked = false;
	// constructor
	public DrawingPanel() {

		marker = new Marker();
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.LIGHT_GRAY);
		this.addMouseListener(new MouseAdapter() {
			// count number of clicks to determine marker on/off
			int clickCount = 0;

			public void mouseClicked(MouseEvent e) {
				clickCount += e.getClickCount();
				if (clickCount % 2 == 1) {
					markerIsOn = true;
				} else {
					markerIsOn = false;
				}
			}
		});

		this.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				if (markerIsOn) {
					moveMarker(e.getX(), e.getY());
				}
			}
		});
		// create radio buttons (Marker color)
		blueMarker = new JRadioButton("Blue", true);
		blueMarker.setForeground(Color.BLUE);
		redMarker = new JRadioButton("Red");
		redMarker.setForeground(Color.RED);
		greenMarker = new JRadioButton("Green");
		greenMarker.setForeground(Color.GREEN);
		eraserButton = new JRadioButton("Eraser");
		eraserButton.setBackground(Color.LIGHT_GRAY);

		clearButton = new JButton();
		this.add(clearButton);
		clearButton.setText("Clear");
		clearButton.addActionListener(new ButtonListener());
		// add action listeners
		blueMarker.addActionListener(new ButtonListener());
		redMarker.addActionListener(new ButtonListener());
		greenMarker.addActionListener(new ButtonListener());
		eraserButton.addActionListener(new ButtonListener());

		group = new ButtonGroup();
		group.add(blueMarker);
		group.add(redMarker);
		group.add(greenMarker);
		group.add(eraserButton);

		// create panel containing buttons
		buttonsPanel = new JPanel();
		buttonsPanel.setSize(230, 20);
		buttonsPanel.setBackground(Color.WHITE);
		buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		this.add(buttonsPanel);

		buttonsPanel.add(blueMarker);
		buttonsPanel.add(redMarker);
		buttonsPanel.add(greenMarker);
		buttonsPanel.add(eraserButton);
		buttonsPanel.add(clearButton);
	}

	private void moveMarker(int x, int y) {
		// current marker state
		final int CURR_X = marker.getX();
		final int CURR_Y = marker.getY();
		final int OFFSET = 0;
		if ((CURR_X != x) || (CURR_Y != y)) {
			marker.setX(x);
			marker.setY(y);
			repaint(marker.getX(), marker.getY(), marker.getW() + OFFSET,
					marker.getH() + OFFSET);
		}
	}

	// put drawing code inside paintComponenet method
	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		Graphics2D g2 = (Graphics2D) pen;
		if(resetIsClicked == false){
		g2.setColor(marker.getMarkerColor());
		g2.drawOval(marker.getX(), marker.getY(), marker.getW(), marker.getH());
		g2.fillOval(marker.getX(), marker.getY(), marker.getW(), marker.getH());
	}else{

		marker.setX(0);
		marker.setY(0);
		repaint();
		resetIsClicked = false;
	}
		}

	public class Marker {
		private int markerX = 0;
		private int markerY = 0;
		private int markerW = 3;
		private int markerH = 3;
		private Color markerColor = Color.BLUE;

		public int getX() {
			return markerX;
		}

		public void setX(int x) {
			this.markerX = x;
		}

		public int getY() {
			return markerY;
		}

		public void setY(int y) {
			this.markerY = y;
		}

		public int getW() {
			return markerW;
		}

		public void setW(int w) {
			this.markerW = w;
		}

		public int getH() {
			return markerH;
		}

		public void setH(int h) {
			this.markerH = h;
		}

		public Color getMarkerColor() {
			return this.markerColor;
		}

		public void setMarkerColor(Color color) {
			this.markerColor = color;
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == clearButton) {
				resetIsClicked = true;
			}
			
			if (redMarker.isSelected()) {
				marker.setMarkerColor(Color.RED);
			} else if (blueMarker.isSelected()) {
				marker.setMarkerColor(Color.BLUE);
			} else if (greenMarker.isSelected()) {
				marker.setMarkerColor(Color.GREEN);
			} else if (eraserButton.isSelected()){
				marker.setMarkerColor(Color.LIGHT_GRAY);
			}

		}
	}

}