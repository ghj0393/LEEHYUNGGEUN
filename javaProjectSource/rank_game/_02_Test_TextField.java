package _rank_game;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Panel_02 extends JPanel  implements ActionListener{
	JTextField name_tf;
	JButton btn;
	Panel_02() {
		setLayout(null); // 배치관리자 설정 x
		name_tf = new JTextField(5);	
		name_tf.setBounds(110, 100, 200, 30);	
		name_tf.addActionListener(this);			
		add(name_tf);
		
		btn = new JButton();
		btn.setBounds(0,0, 100, 100);
		btn.addActionListener(this);
		add(btn);		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(btn == e.getSource()) {
			JOptionPane.showMessageDialog(null, name_tf.getText(), "텍스트필드",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}

public class _02_Test_TextField {
	static JFrame frame = new JFrame();
	public static void main(String[] args) {
		int WIDTH = 400;
		int HEIGHT = 400;
		frame.setTitle("텍스트필드");
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
		frame.setContentPane(new Panel_02());
		frame.revalidate();

	}
}
