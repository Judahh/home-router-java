/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * 
 * @author JH
 */
public class TimeModel {
	private int hour;
	private int min;
	private int sec;
	private int miliSec;

	public TimeModel() {
	}

	public int getHour() {
		return hour;
	}

	private void setHour(String hour) {
		this.hour = Integer.parseInt(hour);
	}

	public int getMin() {
		return min;
	}

	private void setMin(String min) {
		this.min = Integer.parseInt(min);
	}

	public int getSec() {
		return sec;
	}

	private void setSec(String sec) {
		this.sec = Integer.parseInt(sec);
	}

	public int getMiliSec() {
		return miliSec;
	}

	private void setMiliSec(String miliSec) {
		this.miliSec = Integer.parseInt(miliSec);
	}

	public boolean checkIsTime(String time) {
		if ((time.charAt(2) == ':') && (time.charAt(5) == ':') && (time.charAt(8) == '.')) {
			setHour(time.substring(0, 2));
			setMin(time.substring(3, 5));
			setSec(time.substring(6, 8));
			setMiliSec(time.substring(9, 12));
			return true;
		}
		return false;
	}

	public boolean checkIsTime2(String time) {
		if ((time.charAt(2) == ':') && (time.charAt(5) == ':')) {
			setHour(time.substring(0, 2));
			setMin(time.substring(3, 5));
			setSec(time.substring(6, 8));
			return true;
		}
		return false;
	}
}
