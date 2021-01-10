package _rank_game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game_Panel extends JPanel implements ActionListener{
	JButton game_button;
	JButton ranking_button;
	JButton reset_button;
	JButton back_main;
	JButton[][] buttons;
	int[][] front;
	int[][] back;
	JLabel mainLabel;
	static JLabel time;
	
	boolean gameBtnOn;
	static boolean gameStart = true;
	int gameNum;
	static int BTN_CNT = 5;
	static int BTN_SIZE = 50;
	Game_Panel(){

		setLayout(null);
		
		game_button = new JButton("����");	
		game_button.setBackground(Color.GRAY);		
		game_button.setForeground(Color.WHITE);
		game_button.setBounds(40, 10, 80, 30);
		game_button.addActionListener(this);		
		add(game_button);
				
		ranking_button = new JButton("��ŷ");	
		ranking_button.setBackground(Color.CYAN);		
		ranking_button.setForeground(Color.BLACK);
		ranking_button.setBounds(150, 10, 80, 30);
		ranking_button.addActionListener(this);	
		add(ranking_button);
		
		back_main = new JButton("ó��ȭ��");	
		back_main.setBackground(Color.RED);		
		back_main.setForeground(Color.WHITE);
		back_main.setBounds(260, 10, 100, 30);
		back_main.addActionListener(this);		
		add(back_main);
				
		setGamePanel();
//		numShuffle();
		setTextOfBtns();
	}
	void setGamePanel() {	
		//JOptionPane.showMessageDialog(null, "���� ��ŸƮ", "���� ��ŸƮ", JOptionPane.WARNING_MESSAGE);
		gameNum = 1;
		
		buttons = new JButton[BTN_CNT][BTN_CNT];
		front = new int[BTN_CNT][BTN_CNT];
		back = new int[BTN_CNT][BTN_CNT];
		for (int y = 0; y < BTN_CNT; y++) {
			for (int x = 0; x < BTN_CNT; x++) {			
				buttons[y][x] = new JButton();								
				buttons[y][x].setLocation(30 + x * BTN_SIZE, 70 +  
						y * BTN_SIZE);
				buttons[y][x].setSize(BTN_SIZE , BTN_SIZE);
				buttons[y][x].addActionListener(this);
				add(buttons[y][x]);
			}		
		}
		
		int num = 0;
		for(int y=0; y<BTN_CNT; y++) {
			for(int x=0; x<BTN_CNT; x++) {
				front[y][x] = num + 1;
				back[y][x] = num + 26;
				num++;
			}
		}
		
		mainLabel = new JLabel("NEXT NUM = " + gameNum);
		mainLabel.setBounds(40, 350, 110, 40);
		mainLabel.setOpaque(true);
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		mainLabel.setBackground(Color.WHITE);
		add(mainLabel);
		
		time = new JLabel("��ư�� ������ ��ž��ġ ����!");
		time.setBounds(160, 350, 200, 40);
		time.setOpaque(true);
		time.setHorizontalAlignment(JLabel.CENTER);
		time.setBackground(Color.WHITE);
		add(time);
		
		reset_button = new JButton("�ٽ� ����");
		reset_button.setBounds(290, 280, 90, 40);
		reset_button.setBackground(Color.WHITE);
		reset_button.addActionListener(this);
		add(reset_button);
		
	}
	// ��ư text�� ���� �޼���
	public void setTextOfBtns() {
		for(int y=0; y<BTN_CNT; y++) {
			for(int x=0; x<BTN_CNT; x++) {
				buttons[y][x].setText(front[y][x] + "");
			}
		}
	}
	// ���� ���� �޼���
	public void numShuffle() {
		Random ran = new Random();
		for(int i=0; i<100; i++) {
			int y = ran.nextInt(BTN_CNT);
			int x = ran.nextInt(BTN_CNT);
			JButton temp = buttons[0][0];
			buttons[0][0] = buttons[y][x];
			buttons[y][x] = temp;
			temp = null;
		}
	}
	// action���� �� �ż���
	@Override
	public void actionPerformed(ActionEvent e) {
		// �ڷΰ��� ��ư Ŭ�� �� 
		if(e.getSource() == back_main ) {
			_MainSystem.frame.setContentPane(new Login_Panel());	
			_MainSystem.frame.revalidate();
		}
		// ��ŷ ��ư Ŭ�� ��
		else if(e.getSource() == ranking_button ) {	
			// ���� ��� �� ��ŷ ���� �� �г��̵�
			FileManager.instance.loadRank();
			_MainSystem.frame.setContentPane(new Rank_Panel());
			_MainSystem.frame.revalidate();
		}
		// ���� ��ư Ŭ�� ��
		else if(e.getSource() == game_button ) {
			// StopWatch���� 
			if(gameStart) {
				StopWatch.play = true;
				StopWatch st = new StopWatch();
				st.start();
				gameStart = false;
			}
			gameBtnOn = true;
		}	
		// button Ŭ�� �� gameNum�� �� ��
		for(int y=0; y<BTN_CNT; y++) {
			for(int x=0; x<BTN_CNT; x++) {
				if(buttons[y][x] == e.getSource()) {
					// ��ž��ġ�� ���ư� ���� ��
					if(gameBtnOn) {
						if(buttons[y][x].getText().equals(gameNum + "")) {
							if(1 <= gameNum && gameNum <= 25) {
								buttons[y][x].setText(back[y][x] + "");
							}else {
								buttons[y][x].setText("");
							}
							gameNum++;
							if(gameNum <= 50) {
								mainLabel.setText("NEXT NUM = " + gameNum);
							}else if(gameNum > 50) {
								mainLabel.setText("���� ����!");
								// ��ž��ġ ����
								StopWatch.play = false;
								gameBtnOn = false;
								// ���� ���� �� �� �ҿ�ð� �˸� â �߰��ϱ�
								JOptionPane.showInternalMessageDialog(null, "��  �ҿ�ð��� " + StopWatch.timeText + "�� �Դϴ�.");
								// rankData�� ����
								FileManager.instance.loadRankData();
								FileManager.instance.addRankData(Login_Panel.logId, StopWatch.time);
								FileManager.instance.saveRank();
							}
						}
					}
				}
			}
		}
		// �ٽ� �����ϱ� ��ư Ŭ�� ��
		if(reset_button == e.getSource()) {
			StopWatch.play = false;
			gameStart = true;
			gameNum = 1;
			mainLabel.setText("NEXT NUM = " + gameNum);
//			numShuffle();
			setTextOfBtns();
		}
		
	}
}












