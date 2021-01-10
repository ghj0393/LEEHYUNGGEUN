package com.형근.MyArrayList;

class MyArrayList<T> {
	private Object[] list;
	private int count;
	
	// 추가하기 메서드
	public void add(T data) {
		if(count == 0) {
			list = new Object[count+1];
		}else if(count > 0) {
			Object[] temp = list;
			
			list = new Object[count+1];
			for(int i=0; i<count; i++) {
				list[i] = temp[i];
			}
			temp = null;
		}
		list[count] = data;
		count++;
	}
	// 삽입하는 메서드
	public void add(int idx, T data) {
		if(count == 0) {
			list = new Object[count+1];
		}else if(count > 0) {
			Object[] temp = list;
			list = new Object[count+1];
			
			int j=0;
			for(int i=0; i<count+1; i++) {
				if(i != idx) {
					list[i] = temp[j];
					j++;
				}
			}
			temp = null;
		}
		list[idx] = data;
		count++;
	}
	// 삭제하기 메서드
	public void remove(int idx) {
		if(count == 1) {
			list = null;
		}else if(count > 1) {
			Object[] temp = list;
			
			list = new Object[count-1];
			int k = 0;
			for(int i=0; i<count; i++) {
				if(i != idx) {
					list[k] = temp[i];
					k++;
				}
			}
			temp = null;
		}
		count--;
	}
	// 전체 삭제 메서드
	public void clear() {
		list = null;
		count = 0;
	}
	
	// 해당 인덱스의 값 리턴 메서드
	T get(int idx) {
		return (T)list[idx];		// 형 변환
	}
	// list사이즈 반환
	int size() {
		return count;
	}
	// list수정 메서드
	public void set(int idx, T data) {
		list[idx] = data;
	}
	
}















