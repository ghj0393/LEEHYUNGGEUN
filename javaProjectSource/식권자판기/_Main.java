package �ı����Ǳ�;

import javax.swing.JFrame;

public class _Main {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("�ı����Ǳ� ���α׷�");
		
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new MainPanel());
		
		frame.setVisible(true);
		frame.revalidate();
		
	}
}
