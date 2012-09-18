/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author JH
 */
public class YearModel {
    private int year;//1993-2035

    public YearModel() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year<1993){
            this.year =1993;
            return;
        }
        if(year>2035){
            this.year =2035;
            return;
        }
        this.year = year;
    }
    
    public void setYear(String day) {
        this.year = Integer.parseInt(day);
    }
}
