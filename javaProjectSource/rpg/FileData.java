package com.green.rpg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileData {
	// ���� ���� �޼���
	void save() throws IOException {
		FileWriter fout = null;
		// ���� �� ���� ��
		String path = "gameData.txt";

		fout = new FileWriter(path);
		// ��帮��Ʈ �����ͼ� temp������ ����
		ArrayList<Unit> temp = Player.getGuildList();
		String gameData = "";
		gameData += Player.money;
		gameData += "\r\n";
		gameData += temp.size();
		gameData += "\r\n";
		// Unit temp = new Unit("ȣ����", 1, 100, 10, 5, 0);
		for (int i = 0; i < temp.size(); i++) {
			gameData += temp.get(i).name;
			gameData += "/";
			gameData += temp.get(i).level;
			gameData += "/";
			gameData += temp.get(i).maxHp;
			gameData += "/";	
			gameData += temp.get(i).att;
			gameData += "/";
			gameData += temp.get(i).def;
			gameData += "/";
			gameData += temp.get(i).exp;
			gameData += "/";
			gameData += temp.get(i).party;
			gameData += "\r\n";
			
			// ���� �������� ������ ","�� �����ڷ� data���� "/"�� ����,��,���� �����ϴ� ������
			// ���� ���� ����
			if (temp.get(i).weapon == null) {
				gameData += temp.get(i).weapon;
			} else {
				Item item = temp.get(i).weapon;
				String weaponData = "";
				weaponData += item.kind;
				weaponData += ",";
				weaponData += item.name;
				weaponData += ",";
				weaponData += item.power;
				weaponData += ",";
				weaponData += item.price;
				gameData += weaponData;

			}
			// �� ���� ����
			gameData += "/";
			if (temp.get(i).armor == null) {
				gameData += temp.get(i).armor;
			} else {
				Item item = temp.get(i).armor;
				String weaponData = "";
				weaponData += item.kind;
				weaponData += ",";
				weaponData += item.name;
				weaponData += ",";
				weaponData += item.power;
				weaponData += ",";
				weaponData += item.price;
				gameData += weaponData;

			}
			// �� ���� ����
			gameData += "/";
			if (temp.get(i).ring == null) {
				gameData += temp.get(i).ring;
			} else {
				Item item = temp.get(i).ring;
				String weaponData = "";
				weaponData += item.kind;
				weaponData += ",";
				weaponData += item.name;
				weaponData += ",";
				weaponData += item.power;
				weaponData += ",";
				weaponData += item.price;
				gameData += weaponData;
			}
			gameData += "\r\n";
		}	
		// ������ ����Ʈ�� ������ ����
		gameData += Player.getItemSize();
		gameData += "\r\n";
		for(int i = 0; i < Player.getItemSize(); i++) {
			Item item = Player.getItemList().get(i);
			
			gameData += item.kind;
			gameData += "/";
			gameData += item.name;
			gameData += "/";
			gameData += item.power;
			gameData += "/";
			gameData += item.price;	
			gameData += "\r\n";
		}	
		System.out.println(gameData);		
		fout.write(gameData, 0, gameData.length());
		fout.close();

	}
	// ���� �ε��ϱ�
	void loadData() throws IOException {
		File file = null;
		FileReader reader = null;
		BufferedReader br = null;
		String path = "gameData.txt";
		// �����ߴ� ���� �� �ҷ�����
		file = new File(path);
		// ������ ������ ���
		if (file.exists()) {
			reader = new FileReader(path);
			br = new BufferedReader(reader);
			// ù��° �� player money�� �о���� 
			String money = br.readLine();
			Player.money = Integer.parseInt(money); 
			System.out.println(Player.money);
			// �ι�° �� �������� �� �о����
			String guildSize = br.readLine();
			int size = Integer.parseInt(guildSize);
			// ������ �ִ� ��帮��Ʈ ��ü ����
			Player.guild.guildList.clear();
			// �������ŭ for�� �ݺ�
			System.out.println(size);
			for(int i=0; i < size; i++) {
				// ����° �� == ���� �Ѹ� �ش��ϴ� ����
				String unitData = br.readLine();
				// "/"�� �����ڷ� ���� �Ѹ� ���� ���� ���� �迭�� ����
				String[] unitArr = unitData.split("/");
				String name = unitArr[0];
				int level = Integer.parseInt(unitArr[1]);
				int maxhp = Integer.parseInt(unitArr[2]);
				int att = Integer.parseInt(unitArr[3]);
				int def = Integer.parseInt(unitArr[4]);
				int exp = Integer.parseInt(unitArr[5]);
				boolean party = Boolean.parseBoolean(unitArr[6]);
				// Unit��ü ����
				Unit temp = new Unit(name ,level ,maxhp ,  att , def ,exp , party );
				// ��帮��Ʈ�� �߰��ϱ�
				Player.guild.guildList.add(temp);
				//==================== item =======================
				// �׹�° �� == �ش� Unit������ ������ ���� ���� 
				String itemData = br.readLine();			
				String itemArr[] = itemData.split("/");	
				// ���� ���� ����
				if(itemArr[0].equals("null")) {
					Player.getGuildList().get(i).weapon = null;						
				}
				else {			
					String[] weapon = itemArr[0].split(",");
					int itemKind = Integer.parseInt(weapon[0]);
					String itemName = weapon[1];
					int itemPower =  Integer.parseInt(weapon[2]);
					int itemPrice =  Integer.parseInt(weapon[3]);
					Item item = new Item();
					// ������ ��ü ����
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					// �ش� �������� ���� ����
					Player.getGuildList().get(i).weapon = item;
				}
				// �� ���� ����
				if(itemArr[1].equals("null")) {
					Player.getGuildList().get(i).armor = null;
				}
				else {
					String[] armor = itemArr[1].split(",");
					int itemKind = Integer.parseInt(armor[0]);	
					String itemName = armor[1];
					int itemPower =  Integer.parseInt(armor[2]);
					int itemPrice =  Integer.parseInt(armor[3]);
					Item item = new Item();
					// ������ ��ü ����
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					// �ش� �������� �� ����
					Player.getGuildList().get(i).armor = item;
				}
				// �� ���� ����
				if(itemArr[2].equals("null")) {
					Player.getGuildList().get(i).ring = null;
				}
				else {
					String[] ring = itemArr[2].split(",");
					int itemKind = Integer.parseInt(ring[0]);
					String itemName = ring[1];
					int itemPower =  Integer.parseInt(ring[2]);
					int itemPrice =  Integer.parseInt(ring[3]);
					Item item = new Item();
					// ������ ��ü ����
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					// �ش� �������� �� ����
					Player.getGuildList().get(i).ring = item;
				}
			}	
			// ���������� �ʰ� �������� ������ load�ϱ�
			//===================== item ============================
			// ��帮��Ʈ ���� �� ù��° �� == ������ ������
			String invenSize = br.readLine();
			System.out.println(invenSize);
			int inSize = Integer.parseInt(invenSize);
			// ������ �ִ� �����۸���Ʈ ��ü ����
			Player.inven.itemList.clear();
			for(int i = 0; i < inSize; i++) {
				String invenDate = br.readLine();
				String [] invenArr = invenDate.split("/");
				int itemKind = Integer.parseInt(invenArr[0]);
				String itemName = invenArr[1];
				int itemPower =  Integer.parseInt(invenArr[2]);
				int itemPrice =  Integer.parseInt(invenArr[3]);
				Item item = new Item();
				// ������ ��ü ����
				item.setItem(itemKind , itemName , itemPower , itemPrice);
				// �����۸���Ʈ�� �߰�
				Player.inven.itemList.add(item);
			}
		}
	}

}
