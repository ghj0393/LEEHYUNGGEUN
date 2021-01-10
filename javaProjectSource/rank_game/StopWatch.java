package _rank_game;

import _rank_game.Game_Panel;

public class StopWatch extends Thread{
	// 매초 단위를 보여줄 변수
	static String timeText;
	
	static long time = 0l;
	long preTime = 0l;
	static boolean play;
	
	public void run() {
		// 메서드 실행시 그 현재 시간을 구한 변수
		preTime = System.currentTimeMillis();
		while(play) {
			try {
				sleep(10);
				// Thread 실행중 매초 현재 시간과 메서드 실행시 저장한 시간을 빼서 time변수에 저장
				time = System.currentTimeMillis() - preTime;
				// 현재 시간을 시, 분, 초로 계산하기
				int m = (int)(time / 1000.0 / 60.0);
				int s = (int)(time % (1000.0 * 60) / 1000.0);
				int ms = (int)(time % 1000 / 10.0);
				
				timeText = m + " : " + s + " : " + ms;
				// 시작 전 보여주는 메세지
				if(Game_Panel.gameStart) {
					Game_Panel.time.setText("버튼을 누르면 스탑와치가 시작됩니다.");
				}
				// 시작 후 매초 마다 시간을 보여주는 text
				else {
					Game_Panel.time.setText(timeText);
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}