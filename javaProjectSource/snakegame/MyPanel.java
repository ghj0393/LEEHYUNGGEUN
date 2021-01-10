package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel implements ActionListener{
	// 맵 크기에 대한 변수
	final int SIZE = 9;
	// 맵 화면 배열
	JButton[][] map;
	// 방향 저장 배열 왼쪽, 오른쪽, 위쪽, 아래쪽
	JButton[] dir;
	// 뱀의 값 저장 배열 
	int[][] data;
	// 뱀의 사이즈 
	int snakeSize;
	// 뱀의 사이즈에 대한 인덱스 저장 배열
	int[] x;
	int[] y;
	// 뱀의 사이즈에 대한 값 저장 배열
	int[] snake;
	
	public MyPanel() {
		setLayout(null);
		
		map = new JButton[SIZE][SIZE];
		dir = new JButton[4];
		data = new int[SIZE][SIZE];
		
		snakeSize = 4;
		
		x = new int[snakeSize];
		y = new int[snakeSize];
		snake = new int[snakeSize];
		
		setNumber();
		setMap();
		setSnake();
		setButton();
	}
	// 뱀의 인덱스와 값 셋팅
	void setNumber() {
		for(int i=0; i<snakeSize; i++) {
			x[i] = i;
			snake[i] = i + 1;
			data[y[i]][x[i]] = snake[i];
		}
	}
	
	// 화면 초기화 메서드
	void setSnake() {
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				map[i][j].setBackground(Color.WHITE);
				if(map[i][j].getText().equals("O")) {
					map[i][j].setHorizontalAlignment(JButton.CENTER);
					map[i][j].setBackground(Color.YELLOW);
				}
				if(map[i][j].getText().equals("X")) {
					map[i][j].setHorizontalAlignment(JButton.CENTER);
					map[i][j].setBackground(Color.GREEN);
				}
			}
		}
		
		// 뱀의 머리 == 파랑 
		map[y[0]][x[0]].setBackground(Color.BLUE);
		// 뱀의 몸통 == 빨강
		for(int i=1; i<snakeSize; i++) {
			map[y[i]][x[i]].setBackground(Color.RED);
		}		
	}
	// 뱀 이동 메서드
	void setButton() {
		// 이동버튼
		Font font = new Font("맑은 고딕", Font.BOLD, 30);
		for(int i=0; i<4; i++) {
			dir[i] = new JButton();
			dir[i].setSize(100, 100);
			if(i < 3) {
				dir[i].setLocation(550 + 100*(i+1), 400);
			}else {
				dir[i].setLocation(550 + 100*2, 300);
			}
			dir[i].setFont(font);
			dir[i].addActionListener(this);
			
			add(dir[i]);
		}
		// 왼쪽
		dir[0].setText("◀");
		// 아래쪽
		dir[1].setText("▼");
		// 오른쪽
		dir[2].setText("▶");
		// 위쪽
		dir[3].setText("▲");		
	}
	
	// 맵 작업 메서드
	void setMap() {
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				map[i][j] = new JButton();
				map[i][j].setSize(50, 50);
				map[i][j].setLocation(50*(j+1), 50*(i+1));
				
				map[i][j].addActionListener(this);
				
				add(map[i][j]);
			}
		}
		// 꼬리 증가 아이템 작업
		Random ran = new Random();
		for(int i=0; i<5; i++) {
			int y = ran.nextInt(SIZE);
			int x= ran.nextInt(SIZE);
			if(data[y][x] == 0) {
				map[y][x].setText("O");
			}else {
				i--;
			}
		}
		// 꼬리 감소 아이템 작업
		for(int i=0; i<5; i++) {
			int y = ran.nextInt(SIZE);
			int x = ran.nextInt(SIZE);
			if(data[y][x] == 0 && !(map[y][x].getText().equals("O"))) {
				map[y][x].setText("X");
			}else {
				i--;
			}
		}
	}
	
	// Action시 실행 메서드
	@Override
	public void actionPerformed(ActionEvent e) {
		// 이동한 방향 뽑아내기
		int idx = 0;
		for(int i=0; i<4; i++) {
			if(e.getSource() == dir[i]) {
				idx = i;
			}
		}
		// 이동한 인덱스 임시 저장 변수
		int yy = 0;
		int xx = 0;
		// 왼쪽으로 이동
		if(idx == 0) {
			xx = x[0] - 1;
			yy = y[0];
		}
		// 위쪽으로 이동
		else if(idx == 2) {
			xx = x[0] + 1;
			yy = y[0];
		}
		// 아래쪽으로 이동
		else if(idx == 1) {
			xx = x[0];
			yy = y[0] + 1;
		}
		// 오른쪽으로 이동
		else if(idx == 3) {
			xx = x[0];
			yy = y[0] - 1;
		}
		
		// 예외처리
		if(SIZE <= xx || xx < 0 || SIZE <= yy || yy < 0) return;
		//자신의 몸에 닿았을 때
		if(data[yy][xx] != 0) return;
		
		// 증가 아이템을 만났을 때
		if(map[yy][xx].getText().equals("O")) {
			// map에 표시된 text값 초기화
			map[yy][xx].setText("");
			// 뱀의 사이즈 증가를 위해 인덱스 값 임시 저장
			int[] tempY = y;
			int[] tempx = x;
			
			y = new int[snakeSize + 1];
			x = new int[snakeSize + 1];
			snake = new int[snakeSize + 1];
			
			for(int i=0; i<snakeSize; i++) {
				y[i] = tempY[i];
				x[i] = tempx[i];
			}
			for(int i=0; i<snakeSize+1; i++) {
				snake[i] = i+1;
			}
			snakeSize = snakeSize + 1;
		}
		
		// 감소 아이템을 만났을 때
		if(map[yy][xx].getText().equals("X")) {
			// 뱀의 사이즈보다 더 적어질경우 강제 종료
			if(snakeSize == 1) {
				System.out.println("게임종료!");
				System.exit(0);
			}
			// map에 표시된 text값 초기화
			map[yy][xx].setText("");
			// 뱀의 사이즈 감소를 위해 인덱스 값 임시 저장
			int[] tempY = y;
			int[] tempx = x;
			
			y = new int[snakeSize - 1];
			x = new int[snakeSize - 1];
			snake = new int[snakeSize - 1];
			
			for(int i=0; i<snakeSize-1; i++) {
				y[i] = tempY[i];
				x[i] = tempx[i];
			}
			for(int i=0; i<snakeSize-1; i++) {
				snake[i] = i+1;
			}
			snakeSize = snakeSize - 1;
		}
		
		// 맵에 출력된 기존 뱀 초기화
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				data[i][j] = 0;
			}
		}
		// 몸통의 경우 인덱스를 한칸씩 땡겨야함
		for(int i=snakeSize-1; i>0; i--) {
			y[i] = y[i - 1];
			x[i] = x[i - 1];
		}
		// 뱀의 움직인 머리 셋팅
		x[0] = xx;
		y[0] = yy;
		
		for(int i=0; i<snakeSize; i++) {
			data[y[i]][x[i]] = snake[i];
		}
		// 화면 초기화 메서드
		setSnake();
	}
}
