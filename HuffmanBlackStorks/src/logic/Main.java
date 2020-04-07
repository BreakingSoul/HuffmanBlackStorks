package logic;

import java.awt.EventQueue;

import gui.Frame;

public class Main {

	public static void main(String[] args) {
		showGUI();		
	}

	public static void showGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}