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
		setLayout(null); // ��ġ������ ���� x
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
			JOptionPane.showMessageDialog(null, name_tf.getText(), "�ؽ�Ʈ�ʵ�",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}

public class _02_Test_TextField {
	static JFrame frame = new JFrame();
	public static void main(String[] args) {
		int WIDTH = 400;
		int HEIGHT = 400;
		frame.setTitle("�ؽ�Ʈ�ʵ�");
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
		frame.setContentPane(new Panel_02());
		frame.revalidate();

	}
}
