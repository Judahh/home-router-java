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
import view.MainInterface;

/**
 * 
 * @author JH
 */
public class GUISolutionModel {
	private JTextArea Console;
	private JList interfaceStatus;
	private JList serialStatus;
	private JLabel Clock;
	private JLabel Interfaces;
	private JLabel Type;// Master ou Slave
	private JLabel Ios;// Ios e versao

	private JList<String> DynamicEstablishedRoutes;
	private JList<String> StaticEstablishedRoutes;

	private DefaultListModel<String> staticListModel;
	private DefaultListModel<String> dynamicListModel;
	private DefaultListModel<String> statusListModel;
	private DefaultListModel<String> serialStatusListModel;

	private ArrayList<FastEthernet> FEArray;
	private ArrayList<Serial> SArray;

	private JTabbedPane interfacesPane;

        private MainInterface MainGUI;
        
	private int PaneIndex;

	private RouterHandler vTelnet;

	public GUISolutionModel(JTextArea Console, JLabel Clock, JLabel Interfaces, JLabel Type, JLabel Ios, JList DynamicEstablishedRoutes,
			JList StaticEstablishedRoutes, JList interfaceStatus, JList serialStatus, JTabbedPane interfacesPane, MainInterface MainGUI,
			int PaneIndex) {
		this.Console = Console;
		this.interfaceStatus = interfaceStatus;
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
		statusListModel = new DefaultListModel();
		serialStatusListModel = new DefaultListModel();

		FEArray = new ArrayList<FastEthernet>();
		SArray = new ArrayList<Serial>();
	}
        
        public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this.MainGUI,message);
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

	public void appendConsole(String string) {
		this.Console.append(string);
	}

	public void appendConsole(char character) {
		this.Console.append(character + "");
	}

	public void setGUIInterfaces(String Iterfaces) {
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
		// this.DynamicEstablishedRoutes.add
	}

	public void addStaticModel() {
		StaticEstablishedRoutes.setModel(staticListModel);
	}

	public void addDynamicModel() {
		DynamicEstablishedRoutes.setModel(dynamicListModel);
	}

	public void addStatusModel() {
		interfaceStatus.setModel(statusListModel);
	}

	public void addSerialStatusModel() {
		serialStatus.setModel(serialStatusListModel);
	}

	public void addInterfaceStatus(String status) {

		statusListModel.addElement(status);
	}

	public void removeDynamicRoute(String Route) {

	}

	public void removeDynamicRoute(int RouteIndex) {

	}

	public void addStaticRoute(String route) {
		staticListModel.addElement(route);
	}

	public void removeStaticRoute(String Route) {

	}

	public void removeStaticRoute(int RouteIndex) {

	}

	public void addFastEthernetInterface(String port) {
		FastEthernet FE = new FastEthernet();
		FE.setvTelnet(vTelnet);

		FE.setNumber(port);
		FEArray.add(FE);
		this.interfacesPane.add("Fast Ethernet " + port, FE);
	}

	public JTextArea getConsole() {
		return Console;
	}

	public void setConsole(JTextArea console) {
		Console = console;
	}

	public void setFastEthernetPortStatus(int index, boolean On) {
		FEArray.get(index).PortStatusjCheckBox.setSelected(On);
	}

	public void setFastEthernetBandwidth(int index, String bw) {
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

	public void setFastEthernetMac(int index, String MAC) {
		FEArray.get(index).MacjTextField.setText(MAC);
	}

	public void setFastEthernetIp(int index, String IP) {
		FEArray.get(index).IpjTextField.setText(IP);
	}

	public void setFastEthernetMask(int index, String Mask) {
		FEArray.get(index).MaskjTextField.setText(Mask);
	}

	public void setFastEthernetTx(int index, String Tx) {
		FEArray.get(index).TxjTextField.setText(Tx);
	}

	public void setFastEthernetDuplex(int index, String duplex) {
		if (duplex.contains("half")) {
			FEArray.get(index).HalfDuplexJRadioButton.setSelected(true);
		}

	}

	public void addSerialInterface(String port) {
		Serial S = new Serial();
		S.setvTelnet(vTelnet);
		S.setNumber(port);

		SArray.add(S);

		this.interfacesPane.add("Serial " + port, S);
	}

	public void setSerialPortStatus(int index, boolean On) {
		SArray.get(index).PortStatusjCheckBox.setSelected(On);
	}

	public void setSerialClockRate(int index, int item) {
		SArray.get(index).ClockRatejComboBox.setSelectedIndex(item);
	}

	public void setSerialIp(int index, String IP) {
		SArray.get(index).IpjTextField.setText(IP);
	}

	public void setSerialMask(int index, String Mask) {
		SArray.get(index).MaskjTextField.setText(Mask);
	}

	public void setSerialTx(int index, int Tx) {
		SArray.get(index).TxjTextField.setText(Integer.toString(Tx));
	}

	public RouterHandler getvTelnet() {
		return vTelnet;
	}

	public void setvTelnet(RouterHandler vTelnet) {
		this.vTelnet = vTelnet;
	}

}
