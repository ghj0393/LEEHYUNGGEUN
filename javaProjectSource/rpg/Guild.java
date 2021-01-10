package com.green.rpg;

import java.util.ArrayList;
import java.util.Collections;

public class Guild {
	final int PARTY_SIZE = 4;
	ArrayList<Unit> guildList = new ArrayList<>();
	Unit [] partyList;
	// ó�� player����
	public void setGuild() {
		Unit temp = new Unit("ȣ����", 1, 100, 10, 5, 0);
		guildList.add(temp);
		temp = new Unit("������", 1, 80, 7, 3, 0);
		guildList.add(temp);
		temp = new Unit("�罿", 1, 50, 3, 1, 0);
		guildList.add(temp);
		temp = new Unit("�δ���", 1, 70, 5, 2, 0);
		guildList.add(temp);
		temp = new Unit("����", 1, 200, 4, 8, 0);
		guildList.add(temp);
		temp = new Unit("����", 1, 120, 11, 7, 0);
		guildList.add(temp);
		for (int i = 0; i < 4; i++) {
			guildList.get(i).party = true;
		}
		partyList = new Unit[PARTY_SIZE];
		// ����party�� true�� ��� ��Ƽ ��Ͽ� ������Ű��
		int n = 0;
		for(int i = 0; i < guildList.size(); i++) {
			if(guildList.get(i).party == true) {
				partyList[n] = guildList.get(i);
				n += 1;
			}
		}	
	}
	public Unit getGuildUnit(int num) {
		return guildList.get(num);
	}
	// ���θ޴�--������--�����
	// ������ ���
	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[��� : " + Player.money + "]");
		System.out.println("============= [����] =================");
		for (int i = 0; i < guildList.size(); i++) {
			System.out.print("[" + (i + 1) + "��]");
			// Unit����â ���
			printUnitStaus(i);
			System.out.println("");
		}
		System.out.println("=================================");
	}
	// Unit�ε��� �޾Ƽ� �ش�Unit������ �Ѱ��༭ Unit�� ���� ����â ���
	public void printUnitStaus(int num) {
		guildList.get(num).printStatus();
	}
	// Unit�ε��� �޾Ƽ� �ش�Unit������ �Ѱ��༭ Unit�� ���� ������â ���
	public void printUnitItem(int num) {
		guildList.get(num).printEquitedItem();
	}
	// ���� �߰� �޼���
	public void buyUnit() {
		if (Player.money < 5000)
			return;
		String[] n1 = { "��", "��", "��", "��", "��", "��", "��"};
		String[] n2 = { "��", "��", "��", "��", "��", "��", "��"};
		String[] n3 = { "��", "��", "��", "��", "��", "��", "ö"};
		// �̸�, hp, ���ݷ�, ����, ü�� ���� ����
		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0);

		System.out.println("=====================================");
		System.out.print("[�̸� : " + name + "]");
		System.out.print(" [���� : " + 1 + "]");
		System.out.print(" [ü�� : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print("[���ݷ� : " + att + "]");
		System.out.println(" [���� : " + def + "]");
		System.out.println("������ �߰��մϴ�.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ��帮��Ʈ�� �߰� �� 5000�� ��� ����
		guildList.add(temp);
		Player.money -= 5000;
	}
	// ���� ���� �޼���
	public void removeUnit() {
		// ���� ��� ���
		printAllUnitStaus();
		System.out.println("������ ��ȣ�� �Է��ϼ��� ");
		int sel = MainGame.scan.nextInt();
		// ������ 1������ �����ֱ� ������ �ε����� ������� sel-1����
		if (guildList.get(sel - 1).party) {
			System.out.println("��Ƽ���� ����� �����Ҽ� �����ϴ�.");
		} else {
			System.out.println("=================================");
			System.out.print("[�̸� : " + guildList.get(sel - 1).name + "]");
			System.out.println("������ �����մϴ�.");
			System.out.println("=================================");
			guildList.remove(sel - 1);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	// ��Ƽ�� ��� ��� �޼���
	public void printParty() {
		System.out.println("================ [��Ƽ��] ===============");
		for(int i = 0; i < PARTY_SIZE; i++) {
			System.out.print("[" + (i + 1) + "��]");
			System.out.print(" [�̸� : " + partyList[i].name + "]");
			System.out.print(" [���� : " + partyList[i].level + "]");
			System.out.print(" [ü�� : " + partyList[i].hp);
			System.out.println(" / " + partyList[i].maxHp + "]");
			System.out.print("[���ݷ� : " + partyList[i].att + "]");
			System.out.print(" [���� : " + partyList[i].def + "]");
			System.out.println(" [��Ƽ�� : " + guildList.get(i).party + "]");
			System.out.println("");	
		}
		System.out.println("=====================================");
	}
	// ��Ƽ�� ��ü �޼���
	public void partyChange() {
		while(true) {
			// ��Ƽ�� ��� ���
			printParty();
			System.out.println("��ü�� ��ȣ�� �Է��ϼ��� ");
			int partyNum = MainGame.scan.nextInt();
			// ���� ��ü ��� ���
			printAllUnitStaus();
			System.out.println("������ ��ȣ�� �Է��ϼ��� ");
			int guildNum = MainGame.scan.nextInt();
			
			// ��ü ������ ��Ƽ���̰ų� �ڱ��ڽ��� ��
			if(guildList.get(guildNum-1).party == true) {
				System.out.println("�ش� ������ �̹� ��Ƽ�� �������Դϴ�.");
				continue;
			}
			// ��Ƽ �������� �ƴ� �� ���� ���� false�� ��
			else {
				// �ش� Unit��Ƽ�� ������Ű��
				partyList[partyNum - 1].party = false;
				// �ش� Unit��Ƽ�� ������Ű��
				guildList.get(guildNum - 1).party = true;
				System.out.println("====================================");
				System.out.print("[�̸� : " + partyList[partyNum - 1].name + "]");
				System.out.print("���� ");
				System.out.print("[�̸� : " + guildList.get(guildNum - 1).name + "]");
				System.out.println("���� ��ü �մϴ�. ");
				System.out.println("====================================");
				
				int n = 0;
				for(int i = 0; i < guildList.size(); i++) {
					// party��  true�� �� ��Ƽ�� ����
					if(guildList.get(i).party) {
						partyList[n] = guildList.get(i);
						n += 1;
					}
				}		
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	// ���� �̸������� �����ϱ�
	public void sortUnit() {
		Collections.sort(guildList);
	}
	// ���θ޴� -- ������
	public void guildMenu() {
		while (true) {
			System.out.println("=============== [������] ================");
			System.out.println("[1.�����] [2.�����߰�] [3.��������]\n"
					+ "[4.��Ƽ����ü] [5.����]  [0.�ڷΰ���]");
			int sel = MainGame.scan.nextInt();
			// 1.�����
			if (sel == 1) {
				printAllUnitStaus();
			} 
			// 2.�����߰�
			else if (sel == 2) {
				buyUnit();
			} 
			// 3.��������
			else if (sel == 3) {
				removeUnit();
			} 
			// 4.��Ƽ����ü
			else if (sel == 4) {
				partyChange();
			} 
			// 5.����
			else if(sel == 5) {
				sortUnit();
			} 
			// 0.�ڷΰ���
			else if(sel == 0){
				break;
			}
		}
	}

}
