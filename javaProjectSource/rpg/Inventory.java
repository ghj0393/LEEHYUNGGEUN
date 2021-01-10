package com.green.rpg;

import java.util.ArrayList;

public class Inventory {
	ArrayList<Item> itemList = new ArrayList<>();
	// 메인메뉴--3.인벤토리 선택 시
	public void inventoryMenu() {
		while (true) {
			System.out.println("============ [인벤메뉴] =============");
			System.out.println("[1.착용] [2.판매] [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 0)
				break;
			if (sel == 1) {
				equipMenu();
			}
			if(sel == 2) {
				sellMenu();
			}
		}
	}
	public void equipMenu() {
		// 길드원 목록 출력
		Player.guild.printAllUnitStaus();
		System.out.println("아이템 착용할 길드원을 선택하세요 ");
		int selUnit = MainGame.scan.nextInt();			
		while (true) {
			// 해당 길드원 상태창 출력
			Player.guild.printUnitStaus(selUnit - 1);
			// 해당 길드원이 장착한 아이템 리스트 출력
			Player.guild.printUnitItem(selUnit - 1);
			printItemList();
			System.out.println("착용할 아이템 번호를 입력하세요 [0.뒤로가기]");
			int selEquip = MainGame.scan.nextInt();
			if(selEquip == 0) break;
			// 아이템 보여주는 목록을 (i+1)로 했기에 -1해주기
			selEquip -= 1;
			// 무기 종류 확인 무기--1번
			if (itemList.get(selEquip).kind == Item.WEAPON) {
				// 장비를 끼고 있을 경우 아이템 리스트에 다시 추가 한 후 새로운 장비 덮어씌우기 
				if(Player.getGuildUnit(selUnit - 1).weapon != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).weapon);
				}			
				Player.getGuildUnit(selUnit - 1).weapon = itemList.get(selEquip);					
			} 
			// 무기 종류 확인--방어구2번
			else if (itemList.get(selEquip).kind == Item.ARMOR) {
				// 장비를 끼고 있을 경우 아이템 리스트에 다시 추가 한 후 새로운 장비 덮어씌우기 
				if(Player.getGuildUnit(selUnit - 1).armor != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).armor);
				}						
				Player.getGuildUnit(selUnit - 1).armor = itemList.get(selEquip);
			}
			// 무기 종류 확인--링3번
			else if (itemList.get(selEquip).kind == Item.RING) {
				// 장비를 끼고 있을 경우 아이템 리스트에 다시 추가 한 후 새로운 장비 덮어씌우기 
				if(Player.getGuildUnit(selUnit - 1).ring != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).ring);
				}	
				Player.getGuildUnit(selUnit - 1).ring = itemList.get(selEquip);
			}
			// 착용한 아이템 아이템 리스트에서 삭제하기
			itemList.remove(selEquip);
		}
	}
	// 아이템리스트 출력 메서드
	public void printItemList() {
		System.out.println("============ [아이템리스트] ==============");
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print("[이름 : " + itemList.get(i).name + "]");
			System.out.print("[능력 : " + itemList.get(i).power + "]");
			System.out.print("[가격 : " + itemList.get(i).price + "]");
			System.out.println("");
		}
	}
	// 판매 메서드
	public void sellMenu() {
		while (true) {
			// 아이템리스트 출력
			printItemList();
			System.out.println("[골드 : " + Player.money + "]");
			System.out.println("판매할 아이템 번호를 입력하세요. (50 % 세금) [0.뒤로가기]");
			int selSell = MainGame.scan.nextInt();
			if(selSell == 0)
				break;
			System.out.println(itemList.get(selSell-1).name + "을 판매합니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
			// 50%의 값으로 아이템 판매
			Player.money += (itemList.get(selSell-1).price / 2);
			itemList.remove(selSell - 1);		
		}
	}
	// 아이템 구매시 리스트에 추가 메서드
	public void addItem(Item item) {
		itemList.add(item);
	}

}
