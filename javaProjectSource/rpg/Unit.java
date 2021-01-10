package com.green.rpg;

public class Unit implements Comparable<Unit>{
	String name;
	int level;
	int hp;
	int maxHp;
	int att;
	int def;
	int exp;
	boolean party;
	Item weapon;
	Item armor;
	Item ring;
	public Unit(String n, int l, int h, int a, int d, int e) {
		name = n;	level = l;	maxHp = h;	att = a;
		def = d;	exp = e;	hp = maxHp;	party = false;
		weapon = null;	armor = null;	ring = null;
	}
	public Unit(String n, int l, int h, int a, int d, int e , boolean p) {
		name = n;	level = l;	maxHp = h;	att = a;
		def = d;	exp = e;	hp = maxHp;	party = p;
		weapon = null;	armor = null;	ring = null;
	}
	public void setItem(Item w , Item a , Item r) {
		weapon = w; armor= a; ring = r;
	}
	// Unit상태창 출력
	public void printStatus() {
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + level + "]");
		// 링 장착 시 미장착 시
		if(ring != null) {
			System.out.print(" [체력 : " + hp +  " + " + ring.power 
					+ " / " + maxHp +  " + " + ring.power + "]");
		}
		else {
			System.out.print(" [체력 : " + hp + " / " + maxHp + "]");
		}	
		// 무기 장착 시 미장착 시
		if(weapon != null) {
			System.out.print("[공격력 : " + att + " + " + weapon.power + "]");
		}
		else {
			System.out.print("[공격력 : " + att + "]");
		}
		// 방어구 장착 시 미장착 시
		if(armor != null){
			System.out.print(" [방어력 : " + def +  " + " + armor.power + "]");
		}
		else {
			System.out.print(" [방어력 : " + def + "]");
		}
		System.out.println(" [파티중 : " + party + "]");		
	}
	public void printEquitedItem() {
		if (weapon == null) {
			System.out.println("[무기 : 없음 ]");
		} else {
			System.out.println("[무기 : " + weapon.name + "]");
		}
		if (armor == null) {
			System.out.println("[방어구 : 없음 ]");
		} else {
			System.out.println("[방어구 : " + armor.name + "]");
		}
		if (ring == null) {
			System.out.println("[반지 : 없음 ]");
		} else {
			System.out.println("[반지 : " + ring.name + "]");
		}		
	}
	@Override
	public int compareTo(Unit unit) {
		return name.compareTo(unit.name);
	}
}







