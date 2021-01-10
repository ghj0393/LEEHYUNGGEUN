package com.green.rpg;

import java.util.ArrayList;
import java.util.Collections;

public class Guild {
	final int PARTY_SIZE = 4;
	ArrayList<Unit> guildList = new ArrayList<>();
	Unit [] partyList;
	// 처음 player셋팅
	public void setGuild() {
		Unit temp = new Unit("호랑이", 1, 100, 10, 5, 0);
		guildList.add(temp);
		temp = new Unit("강아지", 1, 80, 7, 3, 0);
		guildList.add(temp);
		temp = new Unit("사슴", 1, 50, 3, 1, 0);
		guildList.add(temp);
		temp = new Unit("두더지", 1, 70, 5, 2, 0);
		guildList.add(temp);
		temp = new Unit("돼지", 1, 200, 4, 8, 0);
		guildList.add(temp);
		temp = new Unit("사자", 1, 120, 11, 7, 0);
		guildList.add(temp);
		for (int i = 0; i < 4; i++) {
			guildList.get(i).party = true;
		}
		partyList = new Unit[PARTY_SIZE];
		// 유닛party가 true일 경우 파티 목록에 참가시키기
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
	// 메인메뉴--길드관리--길드목록
	// 길드원들 출력
	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[골드 : " + Player.money + "]");
		System.out.println("============= [길드원] =================");
		for (int i = 0; i < guildList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			// Unit상태창 출력
			printUnitStaus(i);
			System.out.println("");
		}
		System.out.println("=================================");
	}
	// Unit인덱스 받아서 해당Unit정보를 넘겨줘서 Unit에 대한 상태창 출력
	public void printUnitStaus(int num) {
		guildList.get(num).printStatus();
	}
	// Unit인덱스 받아서 해당Unit정보를 넘겨줘서 Unit에 대한 아이템창 출력
	public void printUnitItem(int num) {
		guildList.get(num).printEquitedItem();
	}
	// 길드원 추가 메서드
	public void buyUnit() {
		if (Player.money < 5000)
			return;
		String[] n1 = { "박", "이", "김", "최", "유", "지", "오"};
		String[] n2 = { "명", "기", "종", "민", "재", "석", "광"};
		String[] n3 = { "수", "자", "민", "수", "석", "민", "철"};
		// 이름, hp, 공격력, 방어력, 체력 랜덤 셋팅
		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0);

		System.out.println("=====================================");
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + 1 + "]");
		System.out.print(" [체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print("[공격력 : " + att + "]");
		System.out.println(" [방어력 : " + def + "]");
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 길드리스트에 추가 및 5000원 비용 차감
		guildList.add(temp);
		Player.money -= 5000;
	}
	// 길드원 삭제 메서드
	public void removeUnit() {
		// 길드원 목록 출력
		printAllUnitStaus();
		System.out.println("삭제할 번호를 입력하세요 ");
		int sel = MainGame.scan.nextInt();
		// 길드원을 1번부터 보여주기 때문에 인덱스를 맞출려고 sel-1적용
		if (guildList.get(sel - 1).party) {
			System.out.println("파티중인 멤버는 삭제할수 없습니다.");
		} else {
			System.out.println("=================================");
			System.out.print("[이름 : " + guildList.get(sel - 1).name + "]");
			System.out.println("길드원을 삭제합니다.");
			System.out.println("=================================");
			guildList.remove(sel - 1);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	// 파티원 목록 출력 메서드
	public void printParty() {
		System.out.println("================ [파티원] ===============");
		for(int i = 0; i < PARTY_SIZE; i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + partyList[i].name + "]");
			System.out.print(" [레벨 : " + partyList[i].level + "]");
			System.out.print(" [체력 : " + partyList[i].hp);
			System.out.println(" / " + partyList[i].maxHp + "]");
			System.out.print("[공격력 : " + partyList[i].att + "]");
			System.out.print(" [방어력 : " + partyList[i].def + "]");
			System.out.println(" [파티중 : " + guildList.get(i).party + "]");
			System.out.println("");	
		}
		System.out.println("=====================================");
	}
	// 파티원 교체 메서드
	public void partyChange() {
		while(true) {
			// 파티원 목록 출력
			printParty();
			System.out.println("교체할 번호를 입력하세요 ");
			int partyNum = MainGame.scan.nextInt();
			// 길드원 전체 목록 출력
			printAllUnitStaus();
			System.out.println("참가할 번호를 입력하세요 ");
			int guildNum = MainGame.scan.nextInt();
			
			// 교체 길드원이 파티중이거나 자기자신일 때
			if(guildList.get(guildNum-1).party == true) {
				System.out.println("해당 길드원은 이미 파티에 참여중입니다.");
				continue;
			}
			// 파티 참가중이 아닐 때 참가 가능 false일 때
			else {
				// 해당 Unit파티원 해제시키기
				partyList[partyNum - 1].party = false;
				// 해당 Unit파티원 참가시키기
				guildList.get(guildNum - 1).party = true;
				System.out.println("====================================");
				System.out.print("[이름 : " + partyList[partyNum - 1].name + "]");
				System.out.print("에서 ");
				System.out.print("[이름 : " + guildList.get(guildNum - 1).name + "]");
				System.out.println("으로 교체 합니다. ");
				System.out.println("====================================");
				
				int n = 0;
				for(int i = 0; i < guildList.size(); i++) {
					// party가  true일 때 파티에 참가
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
	// 길드원 이름순으로 정렬하기
	public void sortUnit() {
		Collections.sort(guildList);
	}
	// 메인메뉴 -- 길드관리
	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ================");
			System.out.println("[1.길드목록] [2.길드원추가] [3.길드원삭제]\n"
					+ "[4.파티원교체] [5.정렬]  [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			// 1.길드목록
			if (sel == 1) {
				printAllUnitStaus();
			} 
			// 2.길드원추가
			else if (sel == 2) {
				buyUnit();
			} 
			// 3.길드원삭제
			else if (sel == 3) {
				removeUnit();
			} 
			// 4.파티원교체
			else if (sel == 4) {
				partyChange();
			} 
			// 5.정렬
			else if(sel == 5) {
				sortUnit();
			} 
			// 0.뒤로가기
			else if(sel == 0){
				break;
			}
		}
	}

}
