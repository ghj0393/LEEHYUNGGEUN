package snakegame;

import javax.swing.JFrame;

public class SnakeGame {
	public static void main(String[] args) {

		JFrame frame = new JFrame("스네이크 게임");
		
		frame.setSize(1000, 600);
		frame.setLocation(100, 100);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		MyPanel mp = new MyPanel();
		frame.add(mp);
		
		frame.revalidate();
		
	}

}