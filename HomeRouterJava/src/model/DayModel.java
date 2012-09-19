/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * 
 * @author JH
 */
public class DayModel {
	private int day;// 1-31

	public DayModel() {
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		if (day < 1) {
			this.day = 1;
			return;
		}
		if (day > 31) {
			this.day = 31;
			return;
		}
		this.day = day;
	}

	public void setDay(String day) {
		if (day.charAt(0) == ' ') {
			this.day = Integer.parseInt(day.substring(1));
			return;
		}
		this.day = Integer.parseInt(day);
	}
}
