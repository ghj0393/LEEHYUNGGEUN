package _rank_game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class _MainSystem {
	static JFrame frame =new JFrame();
	
	public static void main(String[] args) {
		int WIDTH = 400;
		int HEIGHT = 450;
		frame.setTitle("회원가입");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		//===============================
		 // ----시작 위치를 바탕화면의 중앙으로 바꾸는 코드
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize(); // 모니터 사이즈를 가져온다.
		frame.setLocation(screenSize.width/2 - WIDTH/2 , screenSize.height/2 - HEIGHT/2 );
		// ----시작 위치를 바탕화면의 중앙으로 바꾸는 코드	
		
		// userData로드하기
		FileManager.instance.loadData();
		// rankData로드하기
		FileManager.instance.loadRankData();
		// login_panel로 이동하기
		Login_Panel login_panel = new Login_Panel();
		frame.setContentPane(login_panel);	
		frame.revalidate();

	}
}
