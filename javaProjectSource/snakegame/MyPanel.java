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
	// �� ũ�⿡ ���� ����
	final int SIZE = 9;
	// �� ȭ�� �迭
	JButton[][] map;
	// ���� ���� �迭 ����, ������, ����, �Ʒ���
	JButton[] dir;
	// ���� �� ���� �迭 
	int[][] data;
	// ���� ������ 
	int snakeSize;
	// ���� ����� ���� �ε��� ���� �迭
	int[] x;
	int[] y;
	// ���� ����� ���� �� ���� �迭
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
	// ���� �ε����� �� ����
	void setNumber() {
		for(int i=0; i<snakeSize; i++) {
			x[i] = i;
			snake[i] = i + 1;
			data[y[i]][x[i]] = snake[i];
		}
	}
	
	// ȭ�� �ʱ�ȭ �޼���
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
		
		// ���� �Ӹ� == �Ķ� 
		map[y[0]][x[0]].setBackground(Color.BLUE);
		// ���� ���� == ����
		for(int i=1; i<snakeSize; i++) {
			map[y[i]][x[i]].setBackground(Color.RED);
		}		
	}
	// �� �̵� �޼���
	void setButton() {
		// �̵���ư
		Font font = new Font("���� ���", Font.BOLD, 30);
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
		// ����
		dir[0].setText("��");
		// �Ʒ���
		dir[1].setText("��");
		// ������
		dir[2].setText("��");
		// ����
		dir[3].setText("��");		
	}
	
	// �� �۾� �޼���
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
		// ���� ���� ������ �۾�
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
		// ���� ���� ������ �۾�
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
	
	// Action�� ���� �޼���
	@Override
	public void actionPerformed(ActionEvent e) {
		// �̵��� ���� �̾Ƴ���
		int idx = 0;
		for(int i=0; i<4; i++) {
			if(e.getSource() == dir[i]) {
				idx = i;
			}
		}
		// �̵��� �ε��� �ӽ� ���� ����
		int yy = 0;
		int xx = 0;
		// �������� �̵�
		if(idx == 0) {
			xx = x[0] - 1;
			yy = y[0];
		}
		// �������� �̵�
		else if(idx == 2) {
			xx = x[0] + 1;
			yy = y[0];
		}
		// �Ʒ������� �̵�
		else if(idx == 1) {
			xx = x[0];
			yy = y[0] + 1;
		}
		// ���������� �̵�
		else if(idx == 3) {
			xx = x[0];
			yy = y[0] - 1;
		}
		
		// ����ó��
		if(SIZE <= xx || xx < 0 || SIZE <= yy || yy < 0) return;
		//�ڽ��� ���� ����� ��
		if(data[yy][xx] != 0) return;
		
		// ���� �������� ������ ��
		if(map[yy][xx].getText().equals("O")) {
			// map�� ǥ�õ� text�� �ʱ�ȭ
			map[yy][xx].setText("");
			// ���� ������ ������ ���� �ε��� �� �ӽ� ����
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
		
		// ���� �������� ������ ��
		if(map[yy][xx].getText().equals("X")) {
			// ���� ������� �� ��������� ���� ����
			if(snakeSize == 1) {
				System.out.println("��������!");
				System.exit(0);
			}
			// map�� ǥ�õ� text�� �ʱ�ȭ
			map[yy][xx].setText("");
			// ���� ������ ���Ҹ� ���� �ε��� �� �ӽ� ����
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
		
		// �ʿ� ��µ� ���� �� �ʱ�ȭ
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				data[i][j] = 0;
			}
		}
		// ������ ��� �ε����� ��ĭ�� ���ܾ���
		for(int i=snakeSize-1; i>0; i--) {
			y[i] = y[i - 1];
			x[i] = x[i - 1];
		}
		// ���� ������ �Ӹ� ����
		x[0] = xx;
		y[0] = yy;
		
		for(int i=0; i<snakeSize; i++) {
			data[y[i]][x[i]] = snake[i];
		}
		// ȭ�� �ʱ�ȭ �޼���
		setSnake();
	}
}
