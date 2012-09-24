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

/**
 * 
 * @author JH
 */
public class GUISolutionModel {
	private JTextArea Console;
	private JLabel Clock;
	private JLabel Interfaces;
	private JLabel Type;// Master ou Slave
	private JLabel Ios;// Ios e versao

	private JList<String> DynamicEstablishedRoutes;
	private JList<String> StaticEstablishedRoutes;

	private DefaultListModel<String> staticListModel;
	private DefaultListModel<String> dynamicListModel;

	private ArrayList<FastEthernet> FEArray;
	private ArrayList<Serial> SArray;

	private JTabbedPane interfacesPane;

	private JTabbedPane Pane;
	private int PaneIndex;
	
	private RouterHandler vTelnet;

	

	public GUISolutionModel(JTextArea Console, JLabel Clock, JLabel Interfaces, JLabel Type, JLabel Ios, JList DynamicEstablishedRoutes,
			JList StaticEstablishedRoutes, JTabbedPane interfacesPane, JTabbedPane Pane, int PaneIndex) {
		this.Console = Console;
		this.Clock = Clock;
		this.Interfaces = Interfaces;
		this.Type = Type;
		this.Ios = Ios;

		this.Pane = Pane;
		this.PaneIndex = PaneIndex;

		this.interfacesPane = interfacesPane;

		this.DynamicEstablishedRoutes = DynamicEstablishedRoutes;
		this.StaticEstablishedRoutes = StaticEstablishedRoutes;

		staticListModel = new DefaultListModel();
		dynamicListModel = new DefaultListModel();

		FEArray = new ArrayList<FastEthernet>();
		SArray = new ArrayList<Serial>();
	}

	public void setGUIRouterName(String RouterName) {
		Pane.setTitleAt(PaneIndex, RouterName);
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

	public void setType(boolean Master) {
		if (Master) {
			this.Type.setText("Master");
		} else {
			this.Type.setText("Slave");
		}
	}

	public void setIos(String ios) {
		this.Ios.setText(ios);
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

	public void setFastEthernetPortStatus(int index, boolean On) {
		FEArray.get(index).PortStatusjCheckBox.setSelected(On);
	}

	public void setFastEthernetBandwidth(int index, boolean Auto) {
		FEArray.get(index).BandwidthjCheckBox.setSelected(Auto);
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

	public void setFastEthernetTx(int index, int Tx) {
		FEArray.get(index).TxjTextField.setText(Integer.toString(Tx));
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
