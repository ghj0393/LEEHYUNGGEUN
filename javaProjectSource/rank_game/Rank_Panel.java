package _rank_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Rank_Panel extends JPanel implements ActionListener{
	JButton backBtn;
	
	Rank_Panel(){
		setLayout(null);
		
		JLabel mainLabel = new JLabel("���߷� ���� ��ŷ");
		mainLabel.setOpaque(true);
		mainLabel.setBounds(100, 20, 200, 30);
		Font font = new Font("", Font.BOLD, 20);
		mainLabel.setFont(font);
		add(mainLabel);
		
		// �ɸ� �ð��� �������� �������� ����
		Collections.sort(FileManager.instance.rankList);
		
		int k= FileManager.instance.rankList.size()-1;
		System.out.println("k = " + k);
		// ��ŷ5�������� �г�â�� ���̰� �ϱ� ���ؼ� list����
		while(k >= 5) {
			FileManager.instance.rankList.remove(k);
			k--;
		}
		// 5�������� ��ŷ ����Ʈ â �����
		for(int i=0; i<FileManager.instance.rankList.size(); i++) {
			JLabel rankIdLabel = new JLabel();
			JLabel rankTimeLabel = new JLabel();
			
			rankIdLabel.setOpaque(true);
			rankTimeLabel.setOpaque(true);
			
			rankIdLabel.setText("[" + (i+1) + "��] " + FileManager.instance.rankList.get(i).id);
			
			// �ش� list�� �ε������� �ɸ� �ð� ��:��:�� �� ����ϱ�
			String timeText = "";
			long time = FileManager.instance.rankList.get(i).time;
			int m = (int)(time / 1000.0 / 60.0);
			int s = (int)(time % (1000.0 * 60) / 1000.0);
			int ms = (int)(time % 1000 / 10.0);
			
			timeText = m + " : " + s + " : " + ms;
			
			rankTimeLabel.setText("��� : " + timeText);
			
			rankIdLabel.setBounds(50, 50 + i*50, 120, 50);
			rankTimeLabel.setBounds(180, 50 + i*50, 170, 50);
			
			font = new Font("", Font.BOLD, 20);
			rankIdLabel.setFont(font);
			rankTimeLabel.setFont(font);
			
			add(rankIdLabel);
			add(rankTimeLabel);
		}
		
		backBtn = new JButton("�ڷ� ����");
		backBtn.setBackground(Color.white);
		backBtn.setBounds(130, 360, 100, 30);
		backBtn.addActionListener(this);
		add(backBtn);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(backBtn == e.getSource()) {
			_MainSystem.frame.setContentPane(new Game_Panel());
			_MainSystem.frame.revalidate();
		}
	}
}













