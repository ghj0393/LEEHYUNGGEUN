package _1to50;

import javax.swing.JFrame;

public class _Main {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("1 to 50");
		frame.setSize(620, 800);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setContentPane(new MainPanel());
		frame.revalidate();
	}
}
