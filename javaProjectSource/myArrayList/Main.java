package com.형근.MyArrayList;

public class Main {
	public static void main(String[] args) {
		
		MyArrayList<UserDTO> userList = new MyArrayList<UserDTO>();
		
		// userList에 객체 추가하기
		userList.add(new UserDTO("qwer", "1111", "이형근", 27));
		userList.add(new UserDTO("qqqq", "2222", "최영철", 33));
		userList.add(new UserDTO("wwww", "3333", "서무궁", 28));
		
		System.out.println("객체 추가 한 후 출력문");
		for(int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getName());
		}
		System.out.println();
		
		// userList에 객체 삽입하기
		userList.add(1, new UserDTO("eeee", "4444", "박흥수", 28));
		System.out.println("객체 삽입한 후 출력문");
		for(int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getName());
		}
		System.out.println();
		// userList의 객체 삭제하기
		userList.remove(2);
		System.out.println("객체 삭제한 후 출력문");
		for(int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getName());
		}
		System.out.println();
		
		// userList객체 수정하기
		UserDTO user = new UserDTO("rrrr", "5555", "봉종현", 26);
		userList.set(1, user);
		System.out.println("객체 수정한 후 출력문");
		for(int i=0; i<userList.size(); i++) {
			System.out.println(userList.get(i).getName());
		}
		System.out.println();
		System.out.println("인덱스로 해당 객체의 name가져오기");
		System.out.println(userList.get(0).getName());
		
		System.out.println();
		System.out.println("userList의 사이즈 ");
		System.out.println(userList.size());
		
		System.out.println();
		// userList객체 전부 삭제하기
		userList.clear();	
		System.out.println("userList 전체 삭제 후");
		System.out.println("userListSize = " + userList.size());
		
	}
}






















