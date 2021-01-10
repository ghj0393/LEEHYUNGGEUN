package 식권자판기;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorListener;

public class MainPanel extends JPanel implements ActionListener {

	JLabel adminLabel;			// 관리자 헤더
	JLabel userLabel;			// 사용자 헤더
	JLabel ticketPriceLabel;	// 식권 가격 보여주기
	JLabel ticketStock;			// 식권 재고 보여주기
	JButton buyBtn;				// user식권 구매 버튼
	JButton addBtn;				// admin식권 충전 버튼
	
	int[] moneys  = {50000, 10000, 5000, 1000, 500, 100};
	int[] charges = {    5,     4,    3,    2,   1,   0};
	int[] myCharges;		// user가 지불할 금액별 수량 담는 배열
	int adminTicketCount;	// admin 식권 재고  변수
	int addTicketCount;		// admin 식권 충전 버튼 변수
	int ticketCount;		// user 식권 수량 버튼 변수
	int ticketPrice = 3500;	// 티켓 가격
		
	ItemPanel[] adminItems; // admin 금액별 패널 창
	ItemPanel[] userItems;  // user 금액별 패널 창
	ItemPanel buyTicket;  	// user 식권 구매 패널 창
	ItemPanel addTicket;	// admin 식권 충전 패널 창
	// 생성자 기본셋팅
	public MainPanel() {
		setLayout(null);
		Font font = new Font("맑은 고딕", Font.BOLD, 50);
		setFont(font);
		basicSettingOfAdmin();
		basicSettingOfUser();
		add(new ItemPanel());
	}
	// paintCompenent기본셋팅
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {Thread.sleep(10);}catch(InterruptedException e) {}
		repaint();
	}
	// admin기본셋팅
	public void basicSettingOfAdmin() {
		adminLabel = new JLabel("관리자");
		adminLabel.setHorizontalAlignment(JLabel.CENTER);
		adminLabel.setBounds(100, 50, 250, 100);
		adminLabel.setFont(getFont());
		// Label은 배경색을 적용할 때 setOpaque(true)가 필요함
		// 단, Panel은 setOpaque(true)가 필요없음
		adminLabel.setOpaque(true);
		adminLabel.setBackground(Color.PINK);
		add(adminLabel);
		
		adminItems = new ItemPanel[6];
		for(int i=0; i<6; i++) {
			adminItems[i] = new ItemPanel(0);
			adminItems[i].moneyText.setText(moneys[i] + "원");
			adminItems[i].countText.setText(charges[i] + "");
			adminItems[i].plusBtn.addActionListener(this);
			adminItems[i].minusBtn.addActionListener(this);
			
			adminItems[i].setBounds(50, 200 + (i * 60), 340, 50);
			
			add(adminItems[i]);
		}
		// 충전할 식권 조절 버튼
		addTicket = new ItemPanel(2);
		addTicket.ticketText.setText("충전할 식권 수");
		addTicket.countText.setText("0");
		addTicket.plusBtn.addActionListener(this);
		addTicket.minusBtn.addActionListener(this);
		addTicket.setBounds(50, 560, 340, 50);
		add(addTicket);
		// 식권 재고 라벨
		ticketStock = new JLabel("식권 재고 = " + adminTicketCount + "개");
		ticketStock.setOpaque(true);	
		ticketStock.setFont(new Font("", Font.BOLD, 30));
		ticketStock.setBackground(Color.WHITE);
		ticketStock.setBounds(50, 620, 340, 50);
		add(ticketStock);
		// 식권 충전 버튼
		addBtn = new JButton("충전하기");
		addBtn.setBounds(50, 680, 340, 50);
		addBtn.setFont(new Font("", Font.BOLD, 30));
		addBtn.setBackground(Color.WHITE);
		addBtn.addActionListener(this);
		add(addBtn);
		
	}
	// user기본셋팅
	public void basicSettingOfUser() {
		userLabel = new JLabel("사용자");
		userLabel.setHorizontalAlignment(JLabel.CENTER);
		userLabel.setBounds(450, 50, 250, 100);
		userLabel.setFont(getFont());
		userLabel.setOpaque(true);
		userLabel.setBackground(Color.PINK);
		add(userLabel);
		
		myCharges = new int[6];
		userItems = new ItemPanel[6];
		for(int i=0; i<6; i++) {
			userItems[i] = new ItemPanel(1);
			userItems[i].moneyText.setText(moneys[i] + "원");
			userItems[i].countText.setText(myCharges[i] + "");
			userItems[i].plusBtn.addActionListener(this);
			userItems[i].minusBtn.addActionListener(this);
			
			userItems[i].setBounds(420, 200 + (i * 60), 340, 50);
			
			add(userItems[i]);
		}
		// 식권 구매 수량 조절 버튼
		buyTicket = new ItemPanel(2);
		buyTicket.ticketText.setText("식권 수량");
		buyTicket.countText.setText("0");
		buyTicket.plusBtn.addActionListener(this);
		buyTicket.minusBtn.addActionListener(this);
		buyTicket.setBounds(420, 560, 340, 50);
		add(buyTicket);
		// 식권 가격 라벨
		ticketPriceLabel = new JLabel("식권 가격 = 3500");
		ticketPriceLabel.setOpaque(true);
		ticketPriceLabel.setFont(new Font("", Font.BOLD, 30));
		ticketPriceLabel.setBackground(Color.WHITE);
		ticketPriceLabel.setBounds(420, 620, 340, 50);
		add(ticketPriceLabel);
		// 식권 구매 버튼
		buyBtn = new JButton("구매하기");
		buyBtn.setBounds(420, 680, 340, 50);
		buyBtn.setFont(new Font("", Font.BOLD, 30));
		buyBtn.setBackground(Color.WHITE);
		buyBtn.addActionListener(this);
		add(buyBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// admin 충전할 금액별 버튼 입력 시
		adminAddCharges(arg0);
		// admin 식권 충전 수량 조절 시
		adminTicketControl(arg0);
		// admin충전하기 버튼 눌렀을 때
		adminAddBtnControl(arg0);
		// user 지불 할 금액별 버튼 입력 시
		userPayCharges(arg0);
		// user 식권 구매 수량 조절 시
		userTicketControl(arg0);
		// user 구매 버튼 입력 시
		userCalculation(arg0);
	}
	
	// admin 충전할 금액별 버튼 입력 시
	public void adminAddCharges(ActionEvent arg0) {
		for(int i=0; i<6; i++) {
			if(adminItems[i].plusBtn == arg0.getSource()) {
				charges[i] += 1;
				adminItems[i].countText.setText(charges[i] + "");
			}
			if(adminItems[i].minusBtn == arg0.getSource()) {
				if(charges[i] > 0) {
					charges[i] -= 1;
					adminItems[i].countText.setText(charges[i] + "");
				}
			}
		}
	}
	// admin 식권 충전 수량 조절 시
	public void adminTicketControl(ActionEvent arg0) {
		if(addTicket.plusBtn == arg0.getSource()) {
			addTicketCount += 1;
			addTicket.countText.setText(addTicketCount + "");
		}else if(addTicket.minusBtn == arg0.getSource()) {
			if(addTicketCount > 0) {
				addTicketCount -= 1;
				addTicket.countText.setText(addTicketCount + "");
			}
		}
	}
	// admin충전하기 버튼 눌렀을 때
	public void adminAddBtnControl(ActionEvent arg0) {
		if(addBtn == arg0.getSource()) {
			adminTicketCount += Integer.parseInt(addTicket.countText.getText());
			ticketStock.setText("식권 재고 = " + adminTicketCount + "개");
			addTicketCount = 0;
			addTicket.countText.setText(addTicketCount + "");
		}
	}
	// user 지불 할 금액별 버튼 입력 시
	public void userPayCharges(ActionEvent arg0) {
		for(int i=0; i<6; i++) {
			if(userItems[i].plusBtn == arg0.getSource()) {
				myCharges[i] += 1;
				userItems[i].countText.setText(myCharges[i] + "");
			}
			if(userItems[i].minusBtn == arg0.getSource()) {
				if(myCharges[i] > 0) {
					myCharges[i] -= 1;
					userItems[i].countText.setText(myCharges[i] + "");
				}
			}
		}
	}
	// user 식권 구매 수량 조절 시
	public void userTicketControl(ActionEvent arg0) {
		if(buyTicket.plusBtn == arg0.getSource()) {
			ticketCount += 1;
			buyTicket.countText.setText(ticketCount + "");
		}else if(buyTicket.minusBtn == arg0.getSource()) {
			if(ticketCount > 0) {
				ticketCount -= 1;
				buyTicket.countText.setText(ticketCount + "");
			}
		}
	}
	// user 구매 버튼 입력 시
	public void userCalculation(ActionEvent arg0) {
		if(buyBtn == arg0.getSource()) {
			// 식권 재고가 0이 아닐 때 구매 가능
			if(adminTicketCount != 0) {
				// 구매하는 식권 수량이 1개 이상이면서 티켓 수량 이하일 때
				if(ticketCount > 0 && ticketCount <= adminTicketCount) {
					int totalTicketPrice = ticketCount * ticketPrice;
					System.out.println("총 티겟 가격은 " + totalTicketPrice + "원 입니다.");
					// user가 지불한 금액 구하기
					int payMoney = 0;
					for(int i=0; i<myCharges.length; i++) {
						payMoney += myCharges[i]*moneys[i];
					}
					System.out.println("지불한 총 금액은 " + payMoney +"원 입니다.");
					// 지불한 금액 - 구입하는 티켓 값으로 잔돈 구하기
					int charge = payMoney - totalTicketPrice;
					if(charge < 0) {
						System.out.println("금액이 부족합니다.");
					}
					// 지불금액이 티켓 값보다 많을 경우 거래 가능
					else if(charge >= 0) {
						int tempCharge = charge;
						int[] tempCharges = new int[charges.length];
						for(int i=0; i<tempCharges.length; i++) {
							tempCharges[i] = charges[i];
						}
						// admin이 잔돈을 거슬러 줄 수 있는지 계산
						for(int i=0; i<charges.length; i++) {
							while(true) {
								if(moneys[i] <= tempCharge && tempCharges[i] > 0) {
									tempCharge -= moneys[i];
									tempCharges[i] -= 1;
								}else {
									break;
								}
							}
						}
						// 잔돈을 거슬러 줄 수 있을 때
						if(tempCharge == 0) {
							for(int i=0; i<tempCharges.length; i++) {
								charges[i] = tempCharges[i];
								charges[i] += myCharges[i];
								myCharges[i] = 0;
							}
							System.out.println("잔돈은 " + charge + "원 입니다.");
							// 정보 갱신
							for(int i=0; i<adminItems.length; i++) {
								adminItems[i].countText.setText(charges[i] + "");
							}
							for(int i=0; i<userItems.length; i++) {
								userItems[i].countText.setText(myCharges[i] + "");
							}
							// 식권 재고 수량 줄이기
							adminTicketCount = adminTicketCount - ticketCount;
							ticketStock.setText("식권 재고 = " + adminTicketCount + "개");
							ticketCount = 0;
							buyTicket.countText.setText(ticketCount + "");
						}else {
							System.out.println("잔돈이 부족합니다.");
						}
					}
				}
			}else {
				System.out.println("식권 재고가 부족합니다.");
			}
			System.out.println();
		}
	}
}
