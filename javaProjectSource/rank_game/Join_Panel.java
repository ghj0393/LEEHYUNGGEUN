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

public class Join_Panel  extends JPanel implements ActionListener{
	JTextField name_tf;
	JTextField age_tf;
	JTextField mobile_tf;
	JTextField id_tf;
	JTextField pw_tf;
	JButton join_membership_button;
	JButton back_button;

	Join_Panel(){
		setLayout(null);

		Font font = new Font("", Font.BOLD, 50);
		JLabel label = new JLabel("회원 가입");
		label.setFont(font);
		label.setBounds(80, 20, 250, 70);
		add(label);

		font = new Font("Gothic", Font.BOLD, 15);
		JLabel name = new JLabel("이름 :");
		name.setBackground(Color.pink);
		name.setOpaque(true);
		name.setFont(font);
		name.setBounds(20, 100, 80, 30);
		add(name);

		JLabel age = new JLabel("나이 :");
		age.setBackground(Color.pink);
		age.setOpaque(true);
		age.setFont(font);
		age.setBounds(20, 130, 80, 30);
		add(age);
		
		JLabel num = new JLabel("전화번호 :");
		num.setBackground(Color.pink);
		num.setOpaque(true);
		num.setFont(font);
		num.setBounds(20, 160, 80, 30);
		add(num);

		JLabel id = new JLabel("ID :");
		id.setBackground(Color.pink);
		id.setOpaque(true);
		id.setFont(font);
		id.setBounds(20, 190, 80, 30);
		add(id);

		JLabel pw = new JLabel("PW :");
		pw.setBackground(Color.pink);
		pw.setOpaque(true);
		pw.setFont(font);
		pw.setBounds(20, 220, 80, 30);
		add(pw);

		name_tf = new JTextField(5);
		age_tf = new JTextField(5);
		mobile_tf = new JTextField(5);
		id_tf = new JTextField(5);
		pw_tf = new JTextField(5);
		name_tf.setBounds(110, 100, 200, 30);
		age_tf.setBounds(110, 130, 200, 30);
		mobile_tf.setBounds(110, 160, 200, 30);
		id_tf.setBounds(110, 190, 200, 30);
		pw_tf.setBounds(110, 220, 200, 30);
		
		name_tf.addActionListener(this);
		age_tf.addActionListener(this);
		mobile_tf.addActionListener(this);
		id_tf.addActionListener(this);
		pw_tf.addActionListener(this);
			
		add(name_tf);
		add(age_tf);
		add(mobile_tf);
		add(id_tf);
		add(pw_tf);

		join_membership_button = new JButton("가입");	
		join_membership_button.setBackground(Color.BLUE);		
		join_membership_button.setForeground(Color.WHITE);
		join_membership_button.setBounds(60, 280, 120, 30);
		join_membership_button.addActionListener(this);
		join_membership_button.setFont(font);
		add(join_membership_button);
		
		back_button = new JButton("처음화면");
		back_button.setBackground(Color.GREEN);
		back_button.setForeground(Color.RED);		
		back_button.setBounds(200, 280, 120, 30);	
		back_button.setFont(font);
		back_button.addActionListener(this);		
		add(back_button);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==back_button ) {
			_MainSystem.frame.setContentPane(new Login_Panel());	
			_MainSystem.frame.revalidate();
		}
		else if(e.getSource() ==join_membership_button ) {
			if (name_tf.getText().equals("")  ||
				age_tf.getText().equals("")   ||
				mobile_tf.getText().equals("")||
				id_tf.getText().equals("")    ||
				pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, 
						"모든필드를 입력하세요", 
						"빈 필드 존재",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			int check = -1;
			for(int i=0; i<FileManager.instance.userManager.size(); i++) {
				if(id_tf.getText().equals(FileManager.instance.userManager.get(i).id)) {
					check = 1;
				}
			}
			if(check == 1) {
				JOptionPane.showMessageDialog(null, "중복된 ID입니다.");
				id_tf.setText("");
				id_tf.requestFocus();
			}else {
				UserInfo user = new UserInfo();
				user.name = name_tf.getText();
				user.age = Integer.parseInt(age_tf.getText());
				user.mobile = mobile_tf.getText();
				user.id = id_tf.getText();
				user.pw = pw_tf.getText();	
				
				FileManager.instance.addUser(user);
				JOptionPane.showMessageDialog(null, "가입을 축하드립니다.");
				
				name_tf.setText("");
				age_tf.setText("");
				mobile_tf.setText("");
				id_tf.setText("");
				pw_tf.setText("");
				name_tf.requestFocus();
			}
		}
	}
}





