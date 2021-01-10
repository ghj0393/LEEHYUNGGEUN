package _1to50;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//JPanel을 상속해야 add()와 setLayout사용가능 ActionListener를 implements해야 actionPerformed(ActionEvent e)를 사용가능
class MainPanel extends JPanel implements ActionListener{
	// 재시작을 보여줄 button
	JButton restart;
	// 1 to 50 버튼 배열
	JButton[][] btns;
	// 버튼 앞면 배열
	int[][] front;
	// 버튼 뒷면 배열
	int[][] back;
	// gameNum을 보여줄 label
	JLabel mainLabel;
	// 타임어택을 보여줄 label
	static JLabel time;
	JLabel title;
	JLabel subTitle;
	
	final int SIZE = 5;
	int gameNum;
	// StopWatch 클래스에서 run실행 중 게임 시작과 전 메세지를 표시하기 위한 변수
	static boolean gameStart = true;
	// MyPanel 생성자 --> 레이아웃 초기화 및 메서드 실행
	MainPanel(){
		setLayout(null);
		basicSetting();
//		numShuffle();
		setTextOfBtns();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {Thread.sleep(10);}catch(InterruptedException e) {}
		repaint();
	}
	// MainPanel 객체 생성시 기본세팅 메서드
	public void basicSetting() {
		gameNum = 1;
		// gameNum을 보여줄 label
		mainLabel = new JLabel("NEXT NUM = " + gameNum);
		mainLabel.setFont(new Font("", Font.BOLD, 15));
		mainLabel.setBounds(50, 10, 150, 50);
		mainLabel.setOpaque(true);
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		mainLabel.setBackground(Color.WHITE);
		add(mainLabel);
		// 타이어택을 보여줄 label
		time = new JLabel("버튼을 누르면 스탑와치가 시작됩니다.");
		time.setBounds(300, 10, 270, 50);
		time.setFont(new Font("", Font.BOLD, 15));
		time.setOpaque(true);
		time.setHorizontalAlignment(JLabel.CENTER);
		time.setBackground(Color.WHITE);
		add(time);
		
		title = new JLabel();
		title.setFont(new Font("", Font.BOLD, 30));
		title.setText("[ 1 to 50 ]");
		title.setOpaque(true);
		title.setBounds(50, 80, 150, 50);
		add(title);
		
		subTitle = new JLabel();
		subTitle.setText("1부터 50까지 순서대로 클릭하세요.");
		subTitle.setOpaque(true);
		subTitle.setBounds(210, 90, 250, 50);
		add(subTitle);
		// 재시작을 보여줄 button
		restart = new JButton("다시 시작");
		restart.setBounds(400, 670, 150, 50);
		restart.setBackground(Color.WHITE);
		restart.addActionListener(this);
		add(restart);
		
		// 버튼 배열생성
		btns = new JButton[SIZE][SIZE];
		front = new int[SIZE][SIZE];
		back = new int[SIZE][SIZE];
		// 버튼 객체 생성 후 기본 셋팅설정
		Font font = new Font("", Font.BOLD, 20);
		for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
				btns[y][x] = new JButton();
				btns[y][x].setText("");
				btns[y][x].setFont(font);
				btns[y][x].setBounds(50+100*x, 150+100*y, 100, 100);
				btns[y][x].addActionListener(this);
				add(btns[y][x]);
			}
		}
		// 앞, 뒤 값 넣기
		int num = 0;
		for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
				front[y][x] = num + 1;
				back[y][x] = num + 26;
				num++;
			}
		}
	}
	// num셔플
	public void numShuffle() {
		Random ran = new Random();
		for(int i=0; i<100; i++) {
			int y = ran.nextInt(SIZE);
			int x = ran.nextInt(SIZE);
			JButton temp = btns[0][0];
			btns[0][0] = btns[y][x];
			btns[y][x] = temp;
			temp = null;
		}
	}
	// 해당 버튼에 표시될 text설정
	public void setTextOfBtns() {
		for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
				// text는 string이기 때문에 빈 문자열 "" 을 더해준다.
				btns[y][x].setText(front[y][x] + "");
			}
		}
	}
	// Action시 수행되는 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		// 클릭시 gameNum과 정답이 같을 경우 실행
		for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
				// 버튼의 위치와 내가 클릭한 위치가 같을 때 실행
				if(btns[y][x] == e.getSource()) {
					// 해당 버튼 위치의 text값과 gameNum의 값이 같은지 비교
					if(btns[y][x].getText().equals(gameNum + "")) {
						if(1 <= gameNum && gameNum <= 25) {
							btns[y][x].setText(back[y][x] + "");
						}else {
							btns[y][x].setText("");
						}
						gameNum++;
						if(gameNum <= 50) {
							mainLabel.setText("NEXT NUM = " + gameNum);
						}
						// 게임종료 시 스탑 워치를 멈추기 위해서 play를 false로 변환
						else if(gameNum > 50) {
							mainLabel.setText("게임 종료!");
							StopWatch.play = false;
						}
					}
				}
			}
		}
		// action 시작 시 StopWatch 클래스의 시간재는 play 변수를 ture로 변경
		if(gameStart) {
			StopWatch.play = true;
			// StopWatch 객체 생성후
			StopWatch st = new StopWatch();
			// StopWatch의 run메서드 실행시키기 (run메서드는 변수 play가 ture일 때 작동, && Thread를 상속하고 있기 때문에 start메서드로 run메서드 실행
			st.start();
			// 한번 작동 시켰으니 원상태로 돌려놓기
			gameStart = false;
		}
		// restart button클릭 시 작동
		if(restart == e.getSource()) {
			// StopWatch 클래스의 run메서드 충족조건인 play를 false로 변환
			StopWatch.play = false;
			// 다시 재가동 시키기
			gameStart = true;
			// 1 to 50 초기화시키기
			gameNum = 1;
			mainLabel.setText("NEXT NUM = " + gameNum);
//			numShuffle();
			// 1 to 50 num값들 초기화 시키기
			MainPanel.time.setText("버튼을 누르면 스탑와치가 시작됩니다.");
			setTextOfBtns();
		}
	}
	
}