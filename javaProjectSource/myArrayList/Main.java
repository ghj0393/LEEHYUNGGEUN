package com.����.MyArrayList;

public class Main {
	public static void main(String[] args) {
		
		MyArrayList<UserDTO> userList = new MyArrayList<UserDTO>();
		
		// userList�� ��ü �߰��ϱ�
		userList.add(new UserDTO("qwer", "1111", "������", 27));
		userList.add(new UserDTO("qqqq", "2222", "�ֿ�ö", 33));
		userList.add(new UserDTO("wwww", "3333", "������", 28));
		
		System.out.println("��ü �߰� �� �� ��¹�");
		for(int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getName());
		}
		System.out.println();
		
		// userList�� ��ü �����ϱ�
		userList.add(1, new UserDTO("eeee", "4444", "�����", 28));
		System.out.println("��ü ������ �� ��¹�");
		for(int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getName());
		}
		System.out.println();
		// userList�� ��ü �����ϱ�
		userList.remove(2);
		System.out.println("��ü ������ �� ��¹�");
		for(int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getName());
		}
		System.out.println();
		
		// userList��ü �����ϱ�
		UserDTO user = new UserDTO("rrrr", "5555", "������", 26);
		userList.set(1, user);
		System.out.println("��ü ������ �� ��¹�");
		for(int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getName());
		}
		System.out.println();
		System.out.println("�ε����� �ش� ��ü�� name��������");
		System.out.println(userList.get(0).getName());
		
		System.out.println();
		System.out.println("userList�� ������ ");
		System.out.println(userList.size());
		
		System.out.println();
		// userList��ü ���� �����ϱ�
		userList.clear();	
		System.out.println("userList ��ü ���� ��");
		System.out.println("userListSize = " + userList.size());
		
	}
}






















