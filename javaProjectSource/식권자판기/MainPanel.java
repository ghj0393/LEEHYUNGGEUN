package �ı����Ǳ�;

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

	JLabel adminLabel;			// ������ ���
	JLabel userLabel;			// ����� ���
	JLabel ticketPriceLabel;	// �ı� ���� �����ֱ�
	JLabel ticketStock;			// �ı� ��� �����ֱ�
	JButton buyBtn;				// user�ı� ���� ��ư
	JButton addBtn;				// admin�ı� ���� ��ư
	
	int[] moneys  = {50000, 10000, 5000, 1000, 500, 100};
	int[] charges = {    5,     4,    3,    2,   1,   0};
	int[] myCharges;		// user�� ������ �ݾ׺� ���� ��� �迭
	int adminTicketCount;	// admin �ı� ���  ����
	int addTicketCount;		// admin �ı� ���� ��ư ����
	int ticketCount;		// user �ı� ���� ��ư ����
	int ticketPrice = 3500;	// Ƽ�� ����
		
	ItemPanel[] adminItems; // admin �ݾ׺� �г� â
	ItemPanel[] userItems;  // user �ݾ׺� �г� â
	ItemPanel buyTicket;  	// user �ı� ���� �г� â
	ItemPanel addTicket;	// admin �ı� ���� �г� â
	// ������ �⺻����
	public MainPanel() {
		setLayout(null);
		Font font = new Font("���� ���", Font.BOLD, 50);
		setFont(font);
		basicSettingOfAdmin();
		basicSettingOfUser();
		add(new ItemPanel());
	}
	// paintCompenent�⺻����
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {Thread.sleep(10);}catch(InterruptedException e) {}
		repaint();
	}
	// admin�⺻����
	public void basicSettingOfAdmin() {
		adminLabel = new JLabel("������");
		adminLabel.setHorizontalAlignment(JLabel.CENTER);
		adminLabel.setBounds(100, 50, 250, 100);
		adminLabel.setFont(getFont());
		// Label�� ������ ������ �� setOpaque(true)�� �ʿ���
		// ��, Panel�� setOpaque(true)�� �ʿ����
		adminLabel.setOpaque(true);
		adminLabel.setBackground(Color.PINK);
		add(adminLabel);
		
		adminItems = new ItemPanel[6];
		for(int i=0; i<6; i++) {
			adminItems[i] = new ItemPanel(0);
			adminItems[i].moneyText.setText(moneys[i] + "��");
			adminItems[i].countText.setText(charges[i] + "");
			adminItems[i].plusBtn.addActionListener(this);
			adminItems[i].minusBtn.addActionListener(this);
			
			adminItems[i].setBounds(50, 200 + (i * 60), 340, 50);
			
			add(adminItems[i]);
		}
		// ������ �ı� ���� ��ư
		addTicket = new ItemPanel(2);
		addTicket.ticketText.setText("������ �ı� ��");
		addTicket.countText.setText("0");
		addTicket.plusBtn.addActionListener(this);
		addTicket.minusBtn.addActionListener(this);
		addTicket.setBounds(50, 560, 340, 50);
		add(addTicket);
		// �ı� ��� ��
		ticketStock = new JLabel("�ı� ��� = " + adminTicketCount + "��");
		ticketStock.setOpaque(true);	
		ticketStock.setFont(new Font("", Font.BOLD, 30));
		ticketStock.setBackground(Color.WHITE);
		ticketStock.setBounds(50, 620, 340, 50);
		add(ticketStock);
		// �ı� ���� ��ư
		addBtn = new JButton("�����ϱ�");
		addBtn.setBounds(50, 680, 340, 50);
		addBtn.setFont(new Font("", Font.BOLD, 30));
		addBtn.setBackground(Color.WHITE);
		addBtn.addActionListener(this);
		add(addBtn);
		
	}
	// user�⺻����
	public void basicSettingOfUser() {
		userLabel = new JLabel("�����");
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
			userItems[i].moneyText.setText(moneys[i] + "��");
			userItems[i].countText.setText(myCharges[i] + "");
			userItems[i].plusBtn.addActionListener(this);
			userItems[i].minusBtn.addActionListener(this);
			
			userItems[i].setBounds(420, 200 + (i * 60), 340, 50);
			
			add(userItems[i]);
		}
		// �ı� ���� ���� ���� ��ư
		buyTicket = new ItemPanel(2);
		buyTicket.ticketText.setText("�ı� ����");
		buyTicket.countText.setText("0");
		buyTicket.plusBtn.addActionListener(this);
		buyTicket.minusBtn.addActionListener(this);
		buyTicket.setBounds(420, 560, 340, 50);
		add(buyTicket);
		// �ı� ���� ��
		ticketPriceLabel = new JLabel("�ı� ���� = 3500");
		ticketPriceLabel.setOpaque(true);
		ticketPriceLabel.setFont(new Font("", Font.BOLD, 30));
		ticketPriceLabel.setBackground(Color.WHITE);
		ticketPriceLabel.setBounds(420, 620, 340, 50);
		add(ticketPriceLabel);
		// �ı� ���� ��ư
		buyBtn = new JButton("�����ϱ�");
		buyBtn.setBounds(420, 680, 340, 50);
		buyBtn.setFont(new Font("", Font.BOLD, 30));
		buyBtn.setBackground(Color.WHITE);
		buyBtn.addActionListener(this);
		add(buyBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// admin ������ �ݾ׺� ��ư �Է� ��
		adminAddCharges(arg0);
		// admin �ı� ���� ���� ���� ��
		adminTicketControl(arg0);
		// admin�����ϱ� ��ư ������ ��
		adminAddBtnControl(arg0);
		// user ���� �� �ݾ׺� ��ư �Է� ��
		userPayCharges(arg0);
		// user �ı� ���� ���� ���� ��
		userTicketControl(arg0);
		// user ���� ��ư �Է� ��
		userCalculation(arg0);
	}
	
	// admin ������ �ݾ׺� ��ư �Է� ��
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
	// admin �ı� ���� ���� ���� ��
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
	// admin�����ϱ� ��ư ������ ��
	public void adminAddBtnControl(ActionEvent arg0) {
		if(addBtn == arg0.getSource()) {
			adminTicketCount += Integer.parseInt(addTicket.countText.getText());
			ticketStock.setText("�ı� ��� = " + adminTicketCount + "��");
			addTicketCount = 0;
			addTicket.countText.setText(addTicketCount + "");
		}
	}
	// user ���� �� �ݾ׺� ��ư �Է� ��
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
	// user �ı� ���� ���� ���� ��
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
	// user ���� ��ư �Է� ��
	public void userCalculation(ActionEvent arg0) {
		if(buyBtn == arg0.getSource()) {
			// �ı� ��� 0�� �ƴ� �� ���� ����
			if(adminTicketCount != 0) {
				// �����ϴ� �ı� ������ 1�� �̻��̸鼭 Ƽ�� ���� ������ ��
				if(ticketCount > 0 && ticketCount <= adminTicketCount) {
					int totalTicketPrice = ticketCount * ticketPrice;
					System.out.println("�� Ƽ�� ������ " + totalTicketPrice + "�� �Դϴ�.");
					// user�� ������ �ݾ� ���ϱ�
					int payMoney = 0;
					for(int i=0; i<myCharges.length; i++) {
						payMoney += myCharges[i]*moneys[i];
					}
					System.out.println("������ �� �ݾ��� " + payMoney +"�� �Դϴ�.");
					// ������ �ݾ� - �����ϴ� Ƽ�� ������ �ܵ� ���ϱ�
					int charge = payMoney - totalTicketPrice;
					if(charge < 0) {
						System.out.println("�ݾ��� �����մϴ�.");
					}
					// ���ұݾ��� Ƽ�� ������ ���� ��� �ŷ� ����
					else if(charge >= 0) {
						int tempCharge = charge;
						int[] tempCharges = new int[charges.length];
						for(int i=0; i<tempCharges.length; i++) {
							tempCharges[i] = charges[i];
						}
						// admin�� �ܵ��� �Ž��� �� �� �ִ��� ���
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
						// �ܵ��� �Ž��� �� �� ���� ��
						if(tempCharge == 0) {
							for(int i=0; i<tempCharges.length; i++) {
								charges[i] = tempCharges[i];
								charges[i] += myCharges[i];
								myCharges[i] = 0;
							}
							System.out.println("�ܵ��� " + charge + "�� �Դϴ�.");
							// ���� ����
							for(int i=0; i<adminItems.length; i++) {
								adminItems[i].countText.setText(charges[i] + "");
							}
							for(int i=0; i<userItems.length; i++) {
								userItems[i].countText.setText(myCharges[i] + "");
							}
							// �ı� ��� ���� ���̱�
							adminTicketCount = adminTicketCount - ticketCount;
							ticketStock.setText("�ı� ��� = " + adminTicketCount + "��");
							ticketCount = 0;
							buyTicket.countText.setText(ticketCount + "");
						}else {
							System.out.println("�ܵ��� �����մϴ�.");
						}
					}
				}
			}else {
				System.out.println("�ı� ��� �����մϴ�.");
			}
			System.out.println();
		}
	}
}
