package com.example.alarmmenager;

public class Alarm {
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	private int state;
	private int uniqueID;

}