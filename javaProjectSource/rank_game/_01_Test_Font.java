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
		setLayout(null); // 배치관리자 설정 x
		Font font = new Font("", Font.BOLD, 50); // 글씨체 설정
		JLabel label = new JLabel("레이블");
		label.setFont(font); // 글씨체 적용
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
		frame.setTitle("폰트 + 레이블");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		// ===============================
		// ----시작 위치를 바탕화면의 중앙으로 바꾸는 코드
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize(); // 모니터 사이즈를 가져온다.
		frame.setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2);
		// ----시작 위치를 바탕화면의 중앙으로 바꾸는 코드
		FileManager.instance.loadData();
		frame.setContentPane(new Panel_01());
		frame.revalidate();

	}
}
