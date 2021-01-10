package _1to50;

import java.awt.Font;

public class StopWatch extends Thread{
	// ���� ������ ������ ����
	static String timeText;
	
	long time = 0l;
	long preTime = 0l;
	static boolean play;
	
	public void run() {
		// �޼��� ����� �� ���� �ð��� ���� ����
		preTime = System.currentTimeMillis();
		Font font = new Font("", Font.BOLD, 15);
		while(play) {
			try {
				sleep(10);
				// Thread ������ ���� ���� �ð��� �޼��� ����� ������ �ð��� ���� time������ ����
				time = System.currentTimeMillis() - preTime;
				// ���� �ð��� ��, ��, �ʷ� ����ϱ�
				int m = (int)(time / 1000.0 / 60.0);
				int s = (int)(time % (1000.0 * 60) / 1000.0);
				int ms = (int)(time % 1000 / 10.0);
				
				timeText = m + " : " + s + " : " + ms;
				// ���� �� �����ִ� �޼���
				if(MainPanel.gameStart) {
					MainPanel.time.setText("��ư�� ������ ��ž��ġ�� ���۵˴ϴ�.");
				}
				// ���� �� ���� ���� �ð��� �����ִ� text
				else {
					MainPanel.time.setText(timeText);
				}
				MainPanel.time.setFont(font);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}