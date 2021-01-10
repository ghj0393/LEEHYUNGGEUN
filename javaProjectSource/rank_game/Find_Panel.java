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



public class Find_Panel extends JPanel implements ActionListener{
	JButton find_pw_button;
	JButton back_main;
	JButton find_id_button;
	JTextField name_id_tf = new JTextField();
	JTextField mobile_id_tf = new JTextField();
	
	
	JTextField name_pw_tf = new JTextField();
	JTextField mobile_pw_tf = new JTextField();
	JTextField id_pw_tf = new JTextField();
	
	Find_Panel(){
		
		setLayout(null);

		Font font = new Font("Gothic", Font.BOLD, 15);
		JLabel label = new JLabel("ID ã�� ");
		label.setFont(font);
		label.setBounds(150, 20, 80, 30);
		add(label);

		JLabel name1 = new JLabel("�̸� :");
		name1.setBackground(Color.pink);
		name1.setOpaque(true);
		name1.setFont(font);
		name1.setBounds(20, 50, 80, 30);
		add(name1);

		JLabel num1 = new JLabel("��ȭ��ȣ :");
		num1.setBackground(Color.pink);
		name1.setOpaque(true);
		num1.setFont(font);
		num1.setBounds(20, 80, 80, 30);
		add(num1);

		name_id_tf.setBounds(120, 50, 200, 30);
		mobile_id_tf.setBounds(120, 80, 200, 30);
		
		add(name_id_tf);
		add(mobile_id_tf);
	
		find_id_button = new JButton("ID ã��");
		find_id_button.setBackground(Color.YELLOW);
		find_id_button.setFont(font);
		find_id_button.setForeground(Color.RED);
		find_id_button.setBounds(120, 120, 120, 30);
		find_id_button.addActionListener(this);
		add(find_id_button);

		JLabel label2 = new JLabel("PW ã�� ");
		label2.setFont(font);
		label2.setBounds(150, 150, 80, 30);
		add(label2);

		JLabel id = new JLabel("ID :");
		id.setBackground(Color.pink);
		id.setOpaque(true);
		id.setFont(font);
		id.setBounds(20, 180, 80, 30);
		add(id);

		JLabel name2 = new JLabel("�̸� :");
		name2.setBackground(Color.pink);
		name2.setOpaque(true);
		name2.setFont(font);
		name2.setBounds(20, 210, 80, 30);
		add(name2);

		JLabel num2 = new JLabel("��ȭ��ȣ :");
		num2.setBackground(Color.pink);
		num2.setOpaque(true);
		num2.setFont(font);
		num2.setBounds(20, 240, 80, 30);
		add(num2);

		
		id_pw_tf.setBounds(120, 180, 200, 30);
		name_pw_tf.setBounds(120, 210, 200, 30);
		mobile_pw_tf.setBounds(120, 240, 200, 30);
		add(name_pw_tf);
		add(mobile_pw_tf);
		add(id_pw_tf);
		
		
		find_pw_button = new JButton("PW ã��");
		find_pw_button.setBackground(Color.BLUE);
		find_pw_button.setFont(font);
		find_pw_button.setForeground(Color.WHITE);
		find_pw_button.setBounds(120, 280, 120, 30);
		find_pw_button.addActionListener(this);
		add(find_pw_button);

		

		back_main = new JButton("ó��ȭ��");
		back_main.setBackground(Color.GREEN);
		back_main.setFont(font);
		back_main.setForeground(Color.RED);
		back_main.setBounds(120, 320, 120, 30);
		back_main.addActionListener(this);
		add(back_main);		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// �ڷΰ��� ��ư Ŭ�� ��
		if (e.getSource() == back_main) {
			_MainSystem.frame.setContentPane(new Login_Panel());	
			_MainSystem.frame.revalidate();
		} 
		// idã�� ��ư Ŭ�� ��
		else if (e.getSource() == find_id_button) {
			String id = FileManager.instance.findID(name_id_tf.getText(), mobile_id_tf.getText());
			if (id == "") {
				JOptionPane.showMessageDialog(null, "ã�� ���̵� �����ϴ�.", "���̵� ã��", JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "���̵� : " + id, "���̵� ã��", JOptionPane.WARNING_MESSAGE);
			}
		}
		// pwã�� ��ư Ŭ�� ��
		else if(e.getSource() == find_pw_button) {
			String pw = FileManager.instance.findPW(id_pw_tf.getText(), name_pw_tf.getText(), mobile_pw_tf.getText());
			if(pw == "") {
				JOptionPane.showMessageDialog(null, "������ �ٽ� �Է��ϼ���.", "��й�ȣ ã��", JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "��й�ȣ : " + pw, "��й�ȣ ã��", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}











