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
	// 랭킹데이터 저장할 때 해당 유저의 아이디 저장
	static String logId = "";
	// id,pw찾기 버튼
	JButton find_button = null;
	// 로그인 버튼
	JButton login_button = null;
	// 회원가입 버튼
	JButton join_button = null;
	// id,pw입력 textField
	JTextField id_tf = null;
	JTextField pw_tf = null;
	// 생성자
	Login_Panel(){
			
		setLayout(null); // 배치관리자 설정 x 
		
		Font font = new Font("", Font.BOLD, 50); // 글씨체 설정

		JLabel label = new JLabel("로그인");
		label.setFont(font); // 글씨체 적용
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

		login_button = new JButton("로그인");
		login_button.setBackground(Color.RED);
		login_button.setForeground(Color.WHITE);
		login_button.setFont(font);
		login_button.setBounds(260, 140, 80, 100);
		login_button.addActionListener(this);
		add(login_button);

		join_button = new JButton("회원가입");
		join_button.setBackground(Color.YELLOW);
		join_button.setFont(font);
		join_button.setBounds(60, 280, 120, 30);
		join_button.addActionListener(this);
		add(join_button);

		find_button = new JButton("ID/PW 찾기");
		find_button.setBackground(Color.BLACK);
		find_button.setForeground(Color.WHITE);
		find_button.setFont(font);
		find_button.setBounds(200, 280, 120, 30);
		find_button.addActionListener(this);
		add(find_button);

		id_tf = new JTextField(5);
		id_tf.setText("아이디를 입력하세요 ");
		id_tf.setBounds(80, 155, 170, 30);
		id_tf.addActionListener(this);
		add(id_tf);

		pw_tf = new JTextField(5);
		pw_tf.setBounds(80, 205, 170, 30);
		pw_tf.addActionListener(this);
		add(pw_tf);
	}
	// action시 실행 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		// id,pw찾기 버튼 클릭 시
		if (e.getSource() == find_button) {
			
			_MainSystem.frame.setContentPane(new Find_Panel());	
			_MainSystem.frame.revalidate();
		}
		// 회원가입 버튼 클릭 시
		else if (e.getSource() == join_button) {
			Join_Panel join_panel = new Join_Panel();
			_MainSystem.frame.setContentPane(join_panel);	
			_MainSystem.frame.revalidate();
		}
		// 로그인 버튼 클릭 시
		else if (e.getSource() == login_button) {
			logId = FileManager.instance.login(id_tf.getText(), pw_tf.getText());
			System.out.println(logId);
			if(logId.equals("")) {
				JOptionPane.showMessageDialog(null, 
						"id 와 pw 를 확인하세요", 
						"로그인실패",
						JOptionPane.WARNING_MESSAGE);
			}
			// 로그인 성공 시 Game_Panel로 이동
			else {
				_MainSystem.frame.setContentPane(new Game_Panel());	
				_MainSystem.frame.revalidate();
			}		
		}	
	}
}
