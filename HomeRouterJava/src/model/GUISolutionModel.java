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
    
    
    public void setGUIRouterName(String RouterName){
        Pane.setTitleAt(PaneIndex, RouterName);
    }
    
}
