package _rank_game;

import _rank_game.Game_Panel;

public class StopWatch extends Thread{
	// ���� ������ ������ ����
	static String timeText;
	
	static long time = 0l;
	long preTime = 0l;
	static boolean play;
	
	public void run() {
		// �޼��� ����� �� ���� �ð��� ���� ����
		preTime = System.currentTimeMillis();
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
				if(Game_Panel.gameStart) {
					Game_Panel.time.setText("��ư�� ������ ��ž��ġ�� ���۵˴ϴ�.");
				}
				// ���� �� ���� ���� �ð��� �����ִ� text
				else {
					Game_Panel.time.setText(timeText);
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}