package _rank_game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {
	File file = null; // 파일 존재 체크여부
	FileWriter fout = null; // 쓰기
	FileReader reader = null; // 읽기
	BufferedReader br = null; // 한줄씩 읽기
	final String path = "userdata.txt"; // 경로
	final String rank = "rankdata.text";
	String data = ""; // 데이타
	String rankData = "";
	boolean isLoad = false;
	ArrayList <UserInfo> userManager = null;
	ArrayList<RankList> rankList = null;
	
	public static FileManager instance = new FileManager();
	
	private FileManager() {
		userManager = new ArrayList<>();
		rankList = new ArrayList<>();
	}
	public void addUser(UserInfo user) {
		userManager.add(user);	
		addData(user);
		saveData();
	}
	private void addData(UserInfo user) {
		int lastIndex = userManager.size() - 1;
		UserInfo temp = userManager.get(lastIndex);
		data += temp.name;
		data += "/";
		data += temp.age + "";
		data += "/";
		data += temp.mobile;
		data += "/";
		data += temp.id;
		data += "/";
		data += temp.pw;
		data += "\n";
		System.out.println("== save ==\n" + data);
	}
	
	public void addRankData(String logId, long time) {
		rankData += logId;
		rankData += "/";
		rankData += time;
		rankData += "\n";
	}
	
	public void loadRankData() {
		file = new File(rank);
		if (!file.exists())
			return;		
		try {
			rankList.clear();
			reader = new FileReader(rank);
			br = new BufferedReader(reader);
			isLoad = false;
			rankData = "";
			while (true) {
				String rankTxt = br.readLine();
				if (rankTxt == null)
					break;
				rankData += rankTxt;
				rankData += "\n";
				
				isLoad = true;
			}		
			reader.close();
			br.close();
			if (isLoad) {
				System.out.println("== loadRank1 ==\n" + rankData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadRank() {
		file = new File(rank);
		if (!file.exists())
			return;		
		try {
			rankList.clear();
			System.out.println("rankListSize = " + rankList.size());
			file = new File(rank);
			reader = new FileReader	(rank);
			br = new BufferedReader(reader);
			while (true) {
				String rankTxt = br.readLine();
				if (rankTxt == null)
					break;
				
				String rankInfo[] = rankTxt.split("/");
				
				RankList temp = new RankList();
				temp.id = rankInfo[0];
				temp.time = Long.parseLong(rankInfo[1]);
				rankList.add(temp);
			}
			System.out.println("rankListSize = " + rankList.size());
			reader.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveRank() {
		try {
			fout = new FileWriter(rank);
			fout.write(rankData);
			fout.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveData() {	
		try {
			fout = new FileWriter(path);
			fout.write(data);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void printAllUser() {
		for(int i = 0; i < userManager.size(); i++) {
			System.out.print(userManager.get(i).name + " ");
			System.out.print(userManager.get(i).age + " ");
			System.out.print(userManager.get(i).mobile + " ");
			System.out.print(userManager.get(i).id + " ");
			System.out.println(userManager.get(i).pw);
		}
	}
	
	public void loadUser(String userTxt) {
		String userinfo[] = userTxt.split("/");
		for(int i = 0; i < userinfo.length; i++) {
		//	System.out.println(userinfo[i]);
		}
		
		UserInfo temp = new UserInfo();
		temp.name = userinfo[0];
		temp.age = Integer.parseInt(userinfo[1]);
		temp.mobile = userinfo[2];
		temp.id = userinfo[3];
		temp.pw = userinfo[4];
		userManager.add(temp);
		printAllUser();
	}
	public void loadData() {
		file = new File(path);
		if (!file.exists())
			return;		
		try {
			reader = new FileReader(path);
			br = new BufferedReader(reader);
			isLoad = false;
			data = "";
			while (true) {
				String userTxt = br.readLine();
				if (userTxt == null)
					break;
				data += userTxt;
				data += "\n";
				
				loadUser(userTxt);
				isLoad = true;
			}		
			reader.close();
			br.close();
			if (isLoad) {
				System.out.println("== load ==\n" + data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	String login(String id , String pw) {
		
		String check_id = "";	
		for(int i = 0; i < userManager.size(); i++){
			if(id.equals(userManager.get(i).id) && 
					pw.equals( userManager.get(i).pw)) {
				check_id = userManager.get(i).id;
				break;
			}
		}	
		return check_id;
	}
	
	String findID(String name , String mobile) {
		String id = "";			
		for(int i = 0; i < userManager.size(); i++){
			if(name.equals(userManager.get(i).name) && 
			   mobile.equals( userManager.get(i).mobile)) {
				id = userManager.get(i).id;
				break;
			}
		}	
		return id;
	}
	
	String findPW(String id, String name, String mobile) {
		String pw = "";
		for(int i=0; i<userManager.size(); i++) {
			if(id.equals(userManager.get(i).id)
					&& name.equals(userManager.get(i).name)
					&& mobile.equals(userManager.get(i).mobile)) {
				pw = userManager.get(i).pw;
				break;
			}
		}
		return pw;
	}
	
}
















