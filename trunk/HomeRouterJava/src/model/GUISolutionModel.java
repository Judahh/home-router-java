/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import view.Serial;
import view.FastEthernet;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import connection.RouterHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import view.MainInterface;

/**
 *
 * @author JH
 */
public class GUISolutionModel {

    private JTextArea Console;
    private JList serialStatus;
    private JLabel Clock;
    private JLabel Interfaces;
    private JLabel Type;// Master ou Slave
    private JLabel Ios;// Ios e versao
    private JList<String> DynamicEstablishedRoutes;
    private JList<String> StaticEstablishedRoutes;
    private DefaultListModel<String> staticListModel;
    private DefaultListModel<String> dynamicListModel;
    private DefaultListModel<String> serialStatusListModel;
    private DefaultTableModel statusTableModel;//check
    private ArrayList<FastEthernet> FEArray;
    private ArrayList<Serial> SArray;
    private JTabbedPane interfacesPane;
    private MainInterface MainGUI;
    private int PaneIndex;
    private RouterHandler routerHandler;

    public GUISolutionModel(DefaultTableModel statusTableModel, JTabbedPane interfacesPane, MainInterface MainGUI,
            int PaneIndex) {
        this.MainGUI = MainGUI;
        this.PaneIndex = PaneIndex;

        this.interfacesPane = interfacesPane;

        staticListModel = new DefaultListModel();
        dynamicListModel = new DefaultListModel();
        this.statusTableModel = statusTableModel;
        serialStatusListModel = new DefaultListModel();
    }
    
    public GUISolutionModel(JTextArea Console, JLabel Clock, JLabel Interfaces, JLabel Type, JLabel Ios, JList DynamicEstablishedRoutes,
            JList StaticEstablishedRoutes, JList serialStatus, JTabbedPane interfacesPane, MainInterface MainGUI,
            int PaneIndex) {
        this.Console = Console;
        this.serialStatus = serialStatus;
        this.Clock = Clock;
        this.Interfaces = Interfaces;
        this.Type = Type;
        this.Ios = Ios;

        this.MainGUI = MainGUI;
        this.PaneIndex = PaneIndex;

        this.interfacesPane = interfacesPane;

        this.DynamicEstablishedRoutes = DynamicEstablishedRoutes;
        this.StaticEstablishedRoutes = StaticEstablishedRoutes;

        staticListModel = new DefaultListModel();
        dynamicListModel = new DefaultListModel();
        serialStatusListModel = new DefaultListModel();

        FEArray = new ArrayList<FastEthernet>();
        SArray = new ArrayList<Serial>();
    }
    
    public GUISolutionModel(JTextArea Console, JLabel Clock, JLabel Interfaces, JLabel Type, JLabel Ios, JList DynamicEstablishedRoutes,
            JList StaticEstablishedRoutes, DefaultTableModel statusTableModel, JList serialStatus, JTabbedPane interfacesPane, MainInterface MainGUI,
            int PaneIndex) {
        this.Console = Console;
        this.serialStatus = serialStatus;
        this.Clock = Clock;
        this.Interfaces = Interfaces;
        this.Type = Type;
        this.Ios = Ios;

        this.MainGUI = MainGUI;
        this.PaneIndex = PaneIndex;

        this.interfacesPane = interfacesPane;

        this.DynamicEstablishedRoutes = DynamicEstablishedRoutes;
        this.StaticEstablishedRoutes = StaticEstablishedRoutes;

        staticListModel = new DefaultListModel();
        dynamicListModel = new DefaultListModel();
        this.statusTableModel = statusTableModel;
        serialStatusListModel = new DefaultListModel();

        FEArray = new ArrayList<FastEthernet>();
        SArray = new ArrayList<Serial>();
    }

    public DefaultTableModel getStatusTableModel() {
        return statusTableModel;
    }

    public String getPassword() {
        return JOptionPane.showInputDialog("VTY Password:", "cisco");
    }

    public String getEnablePassword() {
        return JOptionPane.showInputDialog("Enable Password:", "cisco");
    }

    public String getUser() {
        return JOptionPane.showInputDialog("User:", "Cisco");
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this.MainGUI, message, "Information", 1);
    }
    
    public void showWarningDialog(String message) {
        JOptionPane.showMessageDialog(this.MainGUI, message, "Warning", 2);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this.MainGUI, message, "Error!", 0);
    }

    public void killTab(String host) {
        this.MainGUI.killCLI(host, PaneIndex);
    }

    public void setGUIRouterName(String RouterName) {
        this.MainGUI.getjTabbedPane1().setTitleAt(PaneIndex, RouterName);
    }

    public void setGUIClock(String Clock) {
        this.Clock.setText(Clock);
    }

    public void setType(JLabel Type) {
        this.Type = Type;
    }
    
    public void autoVerticalBardown(){
        if(this.Console.getText().length()>1){
            this.Console.setCaretPosition(this.Console.getText().length()-1);
        }
    }
    
    public void appendConsole(String string) {
        if(Console!=null){
            this.Console.append(string);
            autoVerticalBardown();
        }
    }

    public void appendConsole(char character) {
        if(Console!=null){
            this.Console.append(character + "");
            autoVerticalBardown();
        }
    }

    public void setGUIInterfaces(String Iterfaces) {//nao esta sendo usado!--------------------------------------------------------------
        this.Interfaces.setText(Iterfaces);
    }

    public void setSerialType(String type) {
        serialStatusListModel.addElement(type);
    }

    public void setIos(String ios) {
        this.Ios.setText(ios.trim());
    }

    public void addDynamicRoute(String route) {
        dynamicListModel.addElement(route);
    }

    public void addStaticModel() {
        StaticEstablishedRoutes.setModel(staticListModel);
    }

    public void addDynamicModel() {
        DynamicEstablishedRoutes.setModel(dynamicListModel);
    }

    public void addStatusModel(ArrayList<String>Status) {
        if(statusTableModel!=null){
            
//            System.out.println("Valores Antigos:");
//            for (int index = 0;index < statusTableModel.getRowCount(); index++) {
//                System.out.println("V:"+statusTableModel.getValueAt(0, index));
//            }
            
            for (int index = 0;index < statusTableModel.getRowCount(); index++) {
                if (statusTableModel.getValueAt(index, 0).equals(Status.get(0))) {
                    for (int i = 0; i < statusTableModel.getColumnCount() ; i++) {
                        statusTableModel.setValueAt(Status.get(i), index, i);
                        System.out.println("Refresh Valor("+index+","+i+"):"+Status.get(i));
                    }
                    return;
                }else{
                    System.out.println("Valor:"+Status.get(0)+", Nao existe");
                }
            }
            statusTableModel.addRow(new Object[]{Status.get(0), Status.get(1), Status.get(2), Status.get(3), Status.get(4), Status.get(5)});
            System.out.println("Novo Valor:"+Status.get(0));
        }
    }

    public void addSerialStatusModel() {
        if(serialStatus!=null){
            serialStatus.setModel(serialStatusListModel);
        }
    }

    public void addStaticRoute(String route) {
        staticListModel.addElement(route);
    }

    public void addFastEthernetInterface(String port) {
        FastEthernet FE = new FastEthernet();
        FE.setvTelnet(routerHandler);

        FE.setNumber(port);
        FEArray.add(FE);
        this.interfacesPane.add("Fast Ethernet " + port, FE);
    }

    public void setFastEthernetPortStatus(int index, boolean On) {//nao esta sendo usado!--------------------------------------------------------------
        FEArray.get(index).PortStatusjCheckBox.setSelected(On);
    }

    public void setFastEthernetBandwidth(int index, String bw) {//nao esta sendo usado!--------------------------------------------------------------
        if (bw.equals("10")) {
            FEArray.get(index).jRadioButton10mbps.setSelected(true);
            FEArray.get(index).BandwidthjCheckBox.setSelected(false);
        } else if (bw.equals("100")) {
            FEArray.get(index).jRadioButton100mbps.setSelected(true);
            FEArray.get(index).BandwidthjCheckBox.setSelected(false);
        } else if (bw.equalsIgnoreCase("auto")) {
            FEArray.get(index).BandwidthjCheckBox.setSelected(true);
        }
    }

    public void setFastEthernetMac(int index, String MAC) {//nao esta sendo usado!--------------------------------------------------------------
        FEArray.get(index).MacjTextField.setText(MAC);
    }

    public void setFastEthernetIp(int index, String IP) {//nao esta sendo usado!--------------------------------------------------------------
        FEArray.get(index).IpjTextField.setText(IP);
    }

    public void setFastEthernetMask(int index, String Mask) {//nao esta sendo usado!--------------------------------------------------------------
        FEArray.get(index).MaskjTextField.setText(Mask);
    }

    public void setFastEthernetTx(int index, String Tx) {//nao esta sendo usado!--------------------------------------------------------------
        FEArray.get(index).TxjTextField.setText(Tx);
    }

    public void setFastEthernetDuplex(int index, String duplex) {//nao esta sendo usado!--------------------------------------------------------------
        if (duplex.contains("half")) {
            FEArray.get(index).HalfDuplexJRadioButton.setSelected(true);
        }

    }

    public void addSerialInterface(String port) {
        Serial S = new Serial();
        S.setvTelnet(routerHandler);
        S.setNumber(port);

        SArray.add(S);

        this.interfacesPane.add("Serial " + port, S);
    }

    public void setSerialPortStatus(int index, boolean On) {//nao esta sendo usado!--------------------------------------------------------------
        SArray.get(index).PortStatusjCheckBox.setSelected(On);
    }

    public void setSerialClockRate(int index, int item) {//nao esta sendo usado!--------------------------------------------------------------
        SArray.get(index).ClockRatejComboBox.setSelectedIndex(item);
    }

    public void setSerialIp(int index, String IP) {//nao esta sendo usado!--------------------------------------------------------------
        SArray.get(index).IpjTextField.setText(IP);
    }

    public void setSerialMask(int index, String Mask) {//nao esta sendo usado!--------------------------------------------------------------
        SArray.get(index).MaskjTextField.setText(Mask);
    }

    public void setSerialTx(int index, int Tx) {//nao esta sendo usado!--------------------------------------------------------------
        SArray.get(index).TxjTextField.setText(Integer.toString(Tx));
    }

    public RouterHandler getRouterHandler() {
        return routerHandler;
    }

    public void setRouterHandler(RouterHandler routerHandler) {
        this.routerHandler = routerHandler;
    }
}
