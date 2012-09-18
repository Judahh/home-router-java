/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author JH
 */
public class MonthModel {
    private int month;

    public MonthModel() {
    }

    public int getMonth() {
        return month;
    }

    private void setMonth(int month) {
        this.month = month;
    }
    
    private void setMonth(String month) {
        for (int i = 0; i < getValues().length; i++) {
            if(month == getValues()[i]){
                if(i<12){
                    this.month = i+1;
                    return;
                }
                this.month = i-11;
                return;
            }
        }
    }
    
    public String[] getValues(){
        int c=12;
        String[] values=new String[24];
        
        values[0]="Jan";
        values[1]="Feb";
        values[2]="Mar";
        values[3]="Apr";
        values[4]="May";
        values[5]="Jun";
        values[6]="Jul";
        values[7]="Aug";
        values[8]="Sep";
        values[9]="Oct";
        values[10]="Nov";
        values[11]="Dec";
        
        values[0+c]="January";
        values[1+c]="February";
        values[2+c]="March";
        values[3+c]="April";
        values[4+c]="May";
        values[5+c]="June";
        values[6+c]="July";
        values[7+c]="August";
        values[8+c]="September";
        values[9+c]="October";
        values[10+c]="November";
        values[11+c]="December";
        
        return values;
    }
}
