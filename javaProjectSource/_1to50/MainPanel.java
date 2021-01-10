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

//JPanel�� ����ؾ� add()�� setLayout��밡�� ActionListener�� implements�ؾ� actionPerformed(ActionEvent e)�� ��밡��
class MainPanel extends JPanel implements ActionListener{
	// ������� ������ button
	JButton restart;
	// 1 to 50 ��ư �迭
	JButton[][] btns;
	// ��ư �ո� �迭
	int[][] front;
	// ��ư �޸� �迭
	int[][] back;
	// gameNum�� ������ label
	JLabel mainLabel;
	// Ÿ�Ӿ����� ������ label
	static JLabel time;
	JLabel title;
	JLabel subTitle;
	
	final int SIZE = 5;
	int gameNum;
	// StopWatch Ŭ�������� run���� �� ���� ���۰� �� �޼����� ǥ���ϱ� ���� ����
	static boolean gameStart = true;
	// MyPanel ������ --> ���̾ƿ� �ʱ�ȭ �� �޼��� ����
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
	// MainPanel ��ü ������ �⺻���� �޼���
	public void basicSetting() {
		gameNum = 1;
		// gameNum�� ������ label
		mainLabel = new JLabel("NEXT NUM = " + gameNum);
		mainLabel.setFont(new Font("", Font.BOLD, 15));
		mainLabel.setBounds(50, 10, 150, 50);
		mainLabel.setOpaque(true);
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		mainLabel.setBackground(Color.WHITE);
		add(mainLabel);
		// Ÿ�̾����� ������ label
		time = new JLabel("��ư�� ������ ��ž��ġ�� ���۵˴ϴ�.");
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
		subTitle.setText("1���� 50���� ������� Ŭ���ϼ���.");
		subTitle.setOpaque(true);
		subTitle.setBounds(210, 90, 250, 50);
		add(subTitle);
		// ������� ������ button
		restart = new JButton("�ٽ� ����");
		restart.setBounds(400, 670, 150, 50);
		restart.setBackground(Color.WHITE);
		restart.addActionListener(this);
		add(restart);
		
		// ��ư �迭����
		btns = new JButton[SIZE][SIZE];
		front = new int[SIZE][SIZE];
		back = new int[SIZE][SIZE];
		// ��ư ��ü ���� �� �⺻ ���ü���
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
		// ��, �� �� �ֱ�
		int num = 0;
		for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
				front[y][x] = num + 1;
				back[y][x] = num + 26;
				num++;
			}
		}
	}
	// num����
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
	// �ش� ��ư�� ǥ�õ� text����
	public void setTextOfBtns() {
		for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
				// text�� string�̱� ������ �� ���ڿ� "" �� �����ش�.
				btns[y][x].setText(front[y][x] + "");
			}
		}
	}
	// Action�� ����Ǵ� �޼���
	@Override
	public void actionPerformed(ActionEvent e) {
		// Ŭ���� gameNum�� ������ ���� ��� ����
		for(int y=0; y<SIZE; y++) {
			for(int x=0; x<SIZE; x++) {
				// ��ư�� ��ġ�� ���� Ŭ���� ��ġ�� ���� �� ����
				if(btns[y][x] == e.getSource()) {
					// �ش� ��ư ��ġ�� text���� gameNum�� ���� ������ ��
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
						// �������� �� ��ž ��ġ�� ���߱� ���ؼ� play�� false�� ��ȯ
						else if(gameNum > 50) {
							mainLabel.setText("���� ����!");
							StopWatch.play = false;
						}
					}
				}
			}
		}
		// action ���� �� StopWatch Ŭ������ �ð���� play ������ ture�� ����
		if(gameStart) {
			StopWatch.play = true;
			// StopWatch ��ü ������
			StopWatch st = new StopWatch();
			// StopWatch�� run�޼��� �����Ű�� (run�޼���� ���� play�� ture�� �� �۵�, && Thread�� ����ϰ� �ֱ� ������ start�޼���� run�޼��� ����
			st.start();
			// �ѹ� �۵� �������� �����·� ��������
			gameStart = false;
		}
		// restart buttonŬ�� �� �۵�
		if(restart == e.getSource()) {
			// StopWatch Ŭ������ run�޼��� ���������� play�� false�� ��ȯ
			StopWatch.play = false;
			// �ٽ� �簡�� ��Ű��
			gameStart = true;
			// 1 to 50 �ʱ�ȭ��Ű��
			gameNum = 1;
			mainLabel.setText("NEXT NUM = " + gameNum);
//			numShuffle();
			// 1 to 50 num���� �ʱ�ȭ ��Ű��
			MainPanel.time.setText("��ư�� ������ ��ž��ġ�� ���۵˴ϴ�.");
			setTextOfBtns();
		}
	}
	
}