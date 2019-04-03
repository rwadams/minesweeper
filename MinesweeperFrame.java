package minesweeper;

import javax.swing.JFrame;
import java.awt.Color;

public class MinesweeperFrame {
	
	public static void main(String args[]){
		JFrame f = new JFrame("Minesweeper...");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBackground(Color.WHITE);
		MinesweeperPanel mp = new MinesweeperPanel();
		f.add(mp);
		f.pack();
		f.setVisible(true);
	}

}