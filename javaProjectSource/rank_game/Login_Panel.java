package _rank_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public  class Login_Panel  extends JPanel implements ActionListener{
	// ��ŷ������ ������ �� �ش� ������ ���̵� ����
	static String logId = "";
	// id,pwã�� ��ư
	JButton find_button = null;
	// �α��� ��ư
	JButton login_button = null;
	// ȸ������ ��ư
	JButton join_button = null;
	// id,pw�Է� textField
	JTextField id_tf = null;
	JTextField pw_tf = null;
	// ������
	Login_Panel(){
			
		setLayout(null); // ��ġ������ ���� x 
		
		Font font = new Font("", Font.BOLD, 50); // �۾�ü ����

		JLabel label = new JLabel("�α���");
		label.setFont(font); // �۾�ü ����
		label.setBackground(Color.PINK);
		label.setOpaque(true);
		label.setBounds(100, 20, 160, 80); // x , y , w , h
		add(label);

		font = new Font("", Font.BOLD, 15);

		JLabel id = new JLabel(" ID :");
		id.setBackground(Color.PINK);
		id.setOpaque(true);
		id.setFont(font);
		id.setBounds(20, 150, 50, 40);
		add(id);

		JLabel pw = new JLabel(" PW :");
		pw.setBackground(Color.PINK);
		pw.setOpaque(true);
		pw.setFont(font);
		pw.setBounds(20, 200, 50, 40);
		add(pw);

		login_button = new JButton("�α���");
		login_button.setBackground(Color.RED);
		login_button.setForeground(Color.WHITE);
		login_button.setFont(font);
		login_button.setBounds(260, 140, 80, 100);
		login_button.addActionListener(this);
		add(login_button);

		join_button = new JButton("ȸ������");
		join_button.setBackground(Color.YELLOW);
		join_button.setFont(font);
		join_button.setBounds(60, 280, 120, 30);
		join_button.addActionListener(this);
		add(join_button);

		find_button = new JButton("ID/PW ã��");
		find_button.setBackground(Color.BLACK);
		find_button.setForeground(Color.WHITE);
		find_button.setFont(font);
		find_button.setBounds(200, 280, 120, 30);
		find_button.addActionListener(this);
		add(find_button);

		id_tf = new JTextField(5);
		id_tf.setText("���̵� �Է��ϼ��� ");
		id_tf.setBounds(80, 155, 170, 30);
		id_tf.addActionListener(this);
		add(id_tf);

		pw_tf = new JTextField(5);
		pw_tf.setBounds(80, 205, 170, 30);
		pw_tf.addActionListener(this);
		add(pw_tf);
	}
	// action�� ���� �޼���
	@Override
	public void actionPerformed(ActionEvent e) {
		// id,pwã�� ��ư Ŭ�� ��
		if (e.getSource() == find_button) {
			
			_MainSystem.frame.setContentPane(new Find_Panel());	
			_MainSystem.frame.revalidate();
		}
		// ȸ������ ��ư Ŭ�� ��
		else if (e.getSource() == join_button) {
			Join_Panel join_panel = new Join_Panel();
			_MainSystem.frame.setContentPane(join_panel);	
			_MainSystem.frame.revalidate();
		}
		// �α��� ��ư Ŭ�� ��
		else if (e.getSource() == login_button) {
			logId = FileManager.instance.login(id_tf.getText(), pw_tf.getText());
			System.out.println(logId);
			if(logId.equals("")) {
				JOptionPane.showMessageDialog(null, 
						"id �� pw �� Ȯ���ϼ���", 
						"�α��ν���",
						JOptionPane.WARNING_MESSAGE);
			}
			// �α��� ���� �� Game_Panel�� �̵�
			else {
				_MainSystem.frame.setContentPane(new Game_Panel());	
				_MainSystem.frame.revalidate();
			}		
		}	
	}
}
