/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author JH
 */
public class GUISolutionModel {
    private JTextArea Console;
    private JLabel Clock;
    private JLabel Interfaces;
    private JLabel Type;//Master ou Slave
    private JLabel Ios;//Ios e versao
    private JTabbedPane Pane;
    private int PaneIndex;

    public GUISolutionModel(JTextArea Console, JLabel Clock, JLabel Interfaces, JLabel Type, JLabel Ios, JTabbedPane Pane, int PaneIndex) {
        this.Console = Console;
        this.Clock = Clock;
        this.Interfaces = Interfaces;
        this.Type = Type;
        this.Ios = Ios;
        this.Pane = Pane;
        this.PaneIndex = PaneIndex;
    }
    
    public void setGUIRouterName(String RouterName){
        Pane.setTitleAt(PaneIndex, RouterName);
    }
    
    public void setGUIClock(String Clock){
        this.Clock.setText(Clock);
    }
    
    public void appendConsole(String string){
        this.Console.append(string);
    }
    
    public void appendConsole(char character){
        this.Console.append(character+"");
    }
    
    public void setGUIInterfaces(String Iterfaces){
        this.Interfaces.setText(Iterfaces);
    }
    
    public void setType(boolean Master){
        if(Master){
            this.Type.setText("Master");
        }else{
            this.Type.setText("Slave");
        }
    }
    
    public void setIos(String Ios){
        this.Ios.setText(Ios);
    }
}
