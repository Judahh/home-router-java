/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JLabel;
import javax.swing.JList;
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
    
    private JList DynamicEstablishedRoutes;
    private JList StaticEstablishedRoutes;
    
    private JTabbedPane Pane;
    private int PaneIndex;

    public GUISolutionModel(JTextArea Console, JLabel Clock, JLabel Interfaces, JLabel Type, JLabel Ios, JList DynamicEstablishedRoutes,JList StaticEstablishedRoutes, JTabbedPane Pane, int PaneIndex) {
        this.Console = Console;
        this.Clock = Clock;
        this.Interfaces = Interfaces;
        this.Type = Type;
        this.Ios = Ios;
        
        this.Pane = Pane;
        this.PaneIndex = PaneIndex;
        
        this.DynamicEstablishedRoutes=DynamicEstablishedRoutes;
        this.StaticEstablishedRoutes=StaticEstablishedRoutes;
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
    
    public void addDynamicRoute(String Route){
        
    }
    
    public void removeDynamicRoute(String Route){
        
    }
    
    public void removeDynamicRoute(int RouteIndex){
        
    }
    
    public void addStaticRoute(String Route){
        
    }
    
    public void removeStaticRoute(String Route){
        
    }
    
    public void removeStaticRoute(int RouteIndex){
        
    }
}
