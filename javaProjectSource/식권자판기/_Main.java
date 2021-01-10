package 식권자판기;

import javax.swing.JFrame;

public class _Main {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("식권자판기 프로그램");
		
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new MainPanel());
		
		frame.setVisible(true);
		frame.revalidate();
		
	}
}
