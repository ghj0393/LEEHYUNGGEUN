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
		
		game_button = new JButton("게임");	
		game_button.setBackground(Color.GRAY);		
		game_button.setForeground(Color.WHITE);
		game_button.setBounds(40, 10, 80, 30);
		game_button.addActionListener(this);		
		add(game_button);
				
		ranking_button = new JButton("랭킹");	
		ranking_button.setBackground(Color.CYAN);		
		ranking_button.setForeground(Color.BLACK);
		ranking_button.setBounds(150, 10, 80, 30);
		ranking_button.addActionListener(this);	
		add(ranking_button);
		
		back_main = new JButton("처음화면");	
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
		//JOptionPane.showMessageDialog(null, "게임 스타트", "게임 스타트", JOptionPane.WARNING_MESSAGE);
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
		
		time = new JLabel("버튼을 누르면 스탑와치 시작!");
		time.setBounds(160, 350, 200, 40);
		time.setOpaque(true);
		time.setHorizontalAlignment(JLabel.CENTER);
		time.setBackground(Color.WHITE);
		add(time);
		
		reset_button = new JButton("다시 시작");
		reset_button.setBounds(290, 280, 90, 40);
		reset_button.setBackground(Color.WHITE);
		reset_button.addActionListener(this);
		add(reset_button);
		
	}
	// 버튼 text값 설정 메서드
	public void setTextOfBtns() {
		for(int y=0; y<BTN_CNT; y++) {
			for(int x=0; x<BTN_CNT; x++) {
				buttons[y][x].setText(front[y][x] + "");
			}
		}
	}
	// 숫자 셔플 메서드
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
	// action실행 시 매서드
	@Override
	public void actionPerformed(ActionEvent e) {
		// 뒤로가기 버튼 클릭 시 
		if(e.getSource() == back_main ) {
			_MainSystem.frame.setContentPane(new Login_Panel());	
			_MainSystem.frame.revalidate();
		}
		// 랭킹 버튼 클릭 시
		else if(e.getSource() == ranking_button ) {	
			// 새로 등록 된 랭킹 저장 후 패널이동
			FileManager.instance.loadRank();
			_MainSystem.frame.setContentPane(new Rank_Panel());
			_MainSystem.frame.revalidate();
		}
		// 게임 버튼 클릭 시
		else if(e.getSource() == game_button ) {
			// StopWatch실행 
			if(gameStart) {
				StopWatch.play = true;
				StopWatch st = new StopWatch();
				st.start();
				gameStart = false;
			}
			gameBtnOn = true;
		}	
		// button 클릭 시 gameNum과 값 비교
		for(int y=0; y<BTN_CNT; y++) {
			for(int x=0; x<BTN_CNT; x++) {
				if(buttons[y][x] == e.getSource()) {
					// 스탑와치가 돌아갈 때만 비교
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
								mainLabel.setText("게임 종료!");
								// 스탑와치 끄기
								StopWatch.play = false;
								gameBtnOn = false;
								// 게임 종료 시 총 소요시간 알림 창 뜨게하기
								JOptionPane.showInternalMessageDialog(null, "총  소요시간은 " + StopWatch.timeText + "초 입니다.");
								// rankData에 저장
								FileManager.instance.loadRankData();
								FileManager.instance.addRankData(Login_Panel.logId, StopWatch.time);
								FileManager.instance.saveRank();
							}
						}
					}
				}
			}
		}
		// 다시 시작하기 버튼 클릭 시
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












