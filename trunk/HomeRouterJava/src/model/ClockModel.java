/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * 
 * @author JH
 */
public class ClockModel {// time,dia,mês,ano
	private TimeModel time;
	private DayModel day;
	private MonthModel month;
	private YearModel year;

	public ClockModel() {
	}

	public DayModel getDay() {
		return day;
	}

	public void setDay(DayModel day) {
		this.day = day;
	}

	public MonthModel getMonth() {
		return month;
	}

	public void setMonth(MonthModel month) {
		this.month = month;
	}

	public TimeModel getTime() {
		return time;
	}

	public void setTime(TimeModel time) {
		this.time = time;
	}

	public YearModel getYear() {
		return year;
	}

	public void setYear(YearModel year) {
		this.year = year;
	}
}
