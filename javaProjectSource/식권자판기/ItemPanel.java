package 식권자판기;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ItemPanel extends JPanel {

	JLabel moneyText;
	JLabel countText;
	JLabel ticketText;
	
	JButton plusBtn;
	JButton minusBtn;
	
	ItemPanel(){}
	public ItemPanel(int num){
		setLayout(null);
		// 0 == admin
		if(num == 0) {
			setBackground(Color.YELLOW);
			adminBasicSetting();
		}
		// 1 == user
		else if(num == 1) {
			setBackground(Color.LIGHT_GRAY);
			userBasicSetting();
		}
		// 2 == buyTicket, addTicket
		else if(num == 2) {
			setBackground(Color.LIGHT_GRAY);
			ticketBasicSetting();
		}
	}
	// admin식권 충전, 구매 버튼 셋팅
	public void ticketBasicSetting() {
		Font font = new Font("", Font.BOLD, 25);
		
		ticketText = new JLabel();
		ticketText.setOpaque(true);
		ticketText.setBackground(Color.WHITE);
		ticketText.setFont(font);
		ticketText.setBounds(10, 0, 180, 50);
		add(ticketText);
		
		countText = new JLabel();
		countText.setOpaque(true);
		countText.setBackground(Color.GRAY);
		countText.setHorizontalAlignment(JLabel.CENTER);
		countText.setFont(font);
		countText.setBounds(190, 0, 50, 50);
		add(countText);
		
		plusBtn = new JButton("+");
		plusBtn.setFont(font);
		plusBtn.setBounds(240, 0, 50, 50);
		plusBtn.setBackground(Color.WHITE);
		add(plusBtn);
		
		minusBtn = new JButton("-");
		minusBtn.setFont(font);
		minusBtn.setBounds(290, 0, 50, 50);
		minusBtn.setBackground(Color.WHITE);
		add(minusBtn);
	}
	// user지불 금액 잔액별  패널셋팅
	public void userBasicSetting() {
		Font font = new Font("", Font.BOLD, 25);
		
		moneyText = new JLabel();
		moneyText.setOpaque(true);
		moneyText.setBackground(Color.CYAN);
		moneyText.setFont(font);
		moneyText.setBounds(10, 0, 180, 50);
		add(moneyText);
		
		countText = new JLabel();
		countText.setOpaque(true);
		countText.setBackground(Color.MAGENTA);
		countText.setHorizontalAlignment(JLabel.CENTER);
		countText.setFont(font);
		countText.setBounds(190, 0, 50, 50);
		add(countText);
		
		plusBtn = new JButton("+");
		plusBtn.setFont(font);
		plusBtn.setBounds(240, 0, 50, 50);
		plusBtn.setBackground(Color.WHITE);
		add(plusBtn);
		
		minusBtn = new JButton("-");
		minusBtn.setFont(font);
		minusBtn.setBounds(290, 0, 50, 50);
		minusBtn.setBackground(Color.WHITE);
		add(minusBtn);
	}
	// admin지불 금액 잔액별 패널셋팅
	public void adminBasicSetting() {
		Font font = new Font("", Font.BOLD, 25);
		
		moneyText = new JLabel();
		moneyText.setOpaque(true);
		moneyText.setBackground(Color.GREEN);
		moneyText.setFont(font);
		moneyText.setBounds(10, 0, 180, 50);
		add(moneyText);
		
		countText = new JLabel();
		countText.setOpaque(true);
		countText.setBackground(Color.RED);
		countText.setHorizontalAlignment(JLabel.CENTER);
		countText.setFont(font);
		countText.setBounds(190, 0, 50, 50);
		add(countText);
		
		plusBtn = new JButton("+");
		plusBtn.setFont(font);
		plusBtn.setBounds(240, 0, 50, 50);
		plusBtn.setBackground(Color.WHITE);
		add(plusBtn);
		
		minusBtn = new JButton("-");
		minusBtn.setFont(font);
		minusBtn.setBounds(290, 0, 50, 50);
		minusBtn.setBackground(Color.WHITE);
		add(minusBtn);
	}
}
