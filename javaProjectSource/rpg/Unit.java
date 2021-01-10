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
	// Unit����â ���
	public void printStatus() {
		System.out.print("[�̸� : " + name + "]");
		System.out.print(" [���� : " + level + "]");
		// �� ���� �� ������ ��
		if(ring != null) {
			System.out.print(" [ü�� : " + hp +  " + " + ring.power 
					+ " / " + maxHp +  " + " + ring.power + "]");
		}
		else {
			System.out.print(" [ü�� : " + hp + " / " + maxHp + "]");
		}	
		// ���� ���� �� ������ ��
		if(weapon != null) {
			System.out.print("[���ݷ� : " + att + " + " + weapon.power + "]");
		}
		else {
			System.out.print("[���ݷ� : " + att + "]");
		}
		// �� ���� �� ������ ��
		if(armor != null){
			System.out.print(" [���� : " + def +  " + " + armor.power + "]");
		}
		else {
			System.out.print(" [���� : " + def + "]");
		}
		System.out.println(" [��Ƽ�� : " + party + "]");		
	}
	public void printEquitedItem() {
		if (weapon == null) {
			System.out.println("[���� : ���� ]");
		} else {
			System.out.println("[���� : " + weapon.name + "]");
		}
		if (armor == null) {
			System.out.println("[�� : ���� ]");
		} else {
			System.out.println("[�� : " + armor.name + "]");
		}
		if (ring == null) {
			System.out.println("[���� : ���� ]");
		} else {
			System.out.println("[���� : " + ring.name + "]");
		}		
	}
	@Override
	public int compareTo(Unit unit) {
		return name.compareTo(unit.name);
	}
}







