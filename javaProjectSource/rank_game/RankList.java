package _rank_game;

public class RankList implements Comparable<RankList>{
	String id;
	long time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	@Override
	public int compareTo(RankList rankList) {
		int check = 0;
		if(time - rankList.time > 0) check = 1;
		else if(time - rankList.time < 0) check = -1;
		return	check;
	}
}






