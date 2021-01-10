package com.green.rpg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileData {
	// 파일 저장 메서드
	void save() throws IOException {
		FileWriter fout = null;
		// 저장 할 파일 명
		String path = "gameData.txt";

		fout = new FileWriter(path);
		// 길드리스트 가져와서 temp변수에 저장
		ArrayList<Unit> temp = Player.getGuildList();
		String gameData = "";
		gameData += Player.money;
		gameData += "\r\n";
		gameData += temp.size();
		gameData += "\r\n";
		// Unit temp = new Unit("호랑이", 1, 100, 10, 5, 0);
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
			
			// 장착 아이템한 정보는 ","를 구분자로 data저장 "/"는 무기,방어구,링을 구분하는 구분자
			// 무기 장착 여부
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
			// 방어구 장착 여부
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
			// 링 장착 여부
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
		// 아이템 리스트의 사이즈 저장
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
	// 파일 로드하기
	void loadData() throws IOException {
		File file = null;
		FileReader reader = null;
		BufferedReader br = null;
		String path = "gameData.txt";
		// 저장했던 파일 명 불러오기
		file = new File(path);
		// 파일이 존재할 경우
		if (file.exists()) {
			reader = new FileReader(path);
			br = new BufferedReader(reader);
			// 첫번째 줄 player money값 읽어오기 
			String money = br.readLine();
			Player.money = Integer.parseInt(money); 
			System.out.println(Player.money);
			// 두번째 줄 길드사이즈 값 읽어오기
			String guildSize = br.readLine();
			int size = Integer.parseInt(guildSize);
			// 기존에 있던 길드리스트 전체 삭제
			Player.guild.guildList.clear();
			// 길드사이즈만큼 for문 반복
			System.out.println(size);
			for(int i=0; i < size; i++) {
				// 세번째 줄 == 길드원 한명에 해당하는 정보
				String unitData = br.readLine();
				// "/"를 구분자로 길드원 한명에 대한 정보 값들 배열에 저장
				String[] unitArr = unitData.split("/");
				String name = unitArr[0];
				int level = Integer.parseInt(unitArr[1]);
				int maxhp = Integer.parseInt(unitArr[2]);
				int att = Integer.parseInt(unitArr[3]);
				int def = Integer.parseInt(unitArr[4]);
				int exp = Integer.parseInt(unitArr[5]);
				boolean party = Boolean.parseBoolean(unitArr[6]);
				// Unit객체 생성
				Unit temp = new Unit(name ,level ,maxhp ,  att , def ,exp , party );
				// 길드리스트에 추가하기
				Player.guild.guildList.add(temp);
				//==================== item =======================
				// 네번째 줄 == 해당 Unit에대한 아이템 장착 정보 
				String itemData = br.readLine();			
				String itemArr[] = itemData.split("/");	
				// 무기 존재 여부
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
					// 아이템 객체 생성
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					// 해당 길드원한테 무기 장착
					Player.getGuildList().get(i).weapon = item;
				}
				// 방어구 존재 여부
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
					// 아이템 객체 생성
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					// 해당 길드원에게 방어구 장착
					Player.getGuildList().get(i).armor = item;
				}
				// 링 존재 여부
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
					// 아이템 객체 생성
					item.setItem(itemKind , itemName , itemPower , itemPrice);
					// 해당 길드원에게 링 장착
					Player.getGuildList().get(i).ring = item;
				}
			}	
			// 장착중이지 않고 보유중인 아이템 load하기
			//===================== item ============================
			// 길드리스트 끝난 후 첫번째 줄 == 아이템 사이즈
			String invenSize = br.readLine();
			System.out.println(invenSize);
			int inSize = Integer.parseInt(invenSize);
			// 기존에 있던 아이템리스트 전체 삭제
			Player.inven.itemList.clear();
			for(int i = 0; i < inSize; i++) {
				String invenDate = br.readLine();
				String [] invenArr = invenDate.split("/");
				int itemKind = Integer.parseInt(invenArr[0]);
				String itemName = invenArr[1];
				int itemPower =  Integer.parseInt(invenArr[2]);
				int itemPrice =  Integer.parseInt(invenArr[3]);
				Item item = new Item();
				// 아이템 객체 생성
				item.setItem(itemKind , itemName , itemPower , itemPrice);
				// 아이템리스트에 추가
				Player.inven.itemList.add(item);
			}
		}
	}

}
