package _rank_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Panel_01 extends JPanel {
	Panel_01() {
		setLayout(null); // ��ġ������ ���� x
		Font font = new Font("", Font.BOLD, 50); // �۾�ü ����
		JLabel label = new JLabel("���̺�");
		label.setFont(font); // �۾�ü ����
		label.setBackground(Color.PINK);
		label.setOpaque(true);
		label.setBounds(100, 20, 160, 80); // x , y , w , h
		add(label);
	}
}

public class _01_Test_Font {
	static JFrame frame = new JFrame();

	public static void main(String[] args) {
		int WIDTH = 400;
		int HEIGHT = 400;
		frame.setTitle("��Ʈ + ���̺�");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		// ===============================
		// ----���� ��ġ�� ����ȭ���� �߾����� �ٲٴ� �ڵ�
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize(); // ����� ����� �����´�.
		frame.setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2);
		// ----���� ��ġ�� ����ȭ���� �߾����� �ٲٴ� �ڵ�
		FileManager.instance.loadData();
		frame.setContentPane(new Panel_01());
		frame.revalidate();

	}
}
