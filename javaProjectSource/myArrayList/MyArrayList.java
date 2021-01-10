package com.����.MyArrayList;

class MyArrayList<T> {
	private Object[] list;
	private int count;
	
	// �߰��ϱ� �޼���
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
	// �����ϴ� �޼���
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
	// �����ϱ� �޼���
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
	// ��ü ���� �޼���
	public void clear() {
		list = null;
		count = 0;
	}
	
	// �ش� �ε����� �� ���� �޼���
	T get(int idx) {
		return (T)list[idx];		// �� ��ȯ
	}
	// list������ ��ȯ
	int size() {
		return count;
	}
	// list���� �޼���
	public void set(int idx, T data) {
		list[idx] = data;
	}
	
}















