/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import view.Serial;
import view.FastEthernet;
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
	private JLabel Type;// Master ou Slave
	private JLabel Ios;// Ios e versao

	private JList<String> DynamicEstablishedRoutes;
	private JList<String> StaticEstablishedRoutes;

	private ArrayList<FastEthernet> FEArray;
	private ArrayList<Serial> SArray;

	private JTabbedPane Serial;
	private JTabbedPane FastEthernet;

	private JTabbedPane Pane;
	private int PaneIndex;

	public GUISolutionModel(JTextArea Console, JLabel Clock, JLabel Interfaces, JLabel Type, JLabel Ios, JList DynamicEstablishedRoutes,
			JList StaticEstablishedRoutes, JTabbedPane Serial, JTabbedPane FastEthernet, JTabbedPane Pane, int PaneIndex) {
		this.Console = Console;
		this.Clock = Clock;
		this.Interfaces = Interfaces;
		this.Type = Type;
		this.Ios = Ios;

		this.Pane = Pane;
		this.PaneIndex = PaneIndex;

		this.Serial = Serial;
		this.FastEthernet = FastEthernet;

		this.DynamicEstablishedRoutes = DynamicEstablishedRoutes;
		this.StaticEstablishedRoutes = StaticEstablishedRoutes;
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

	public void setIos(String Ios) {
		this.Ios.setText(Ios);
	}

	public void addDynamicRoute(String Route) {
		// this.DynamicEstablishedRoutes.add
	}

	public void removeDynamicRoute(String Route) {

	}

	public void removeDynamicRoute(int RouteIndex) {

	}

	public void addStaticRoute(String Route) {

	}

	public void removeStaticRoute(String Route) {

	}

	public void removeStaticRoute(int RouteIndex) {

	}

	public void addFastEthernetInterface(String port) {
		FastEthernet FE = new FastEthernet();
		FEArray.add(FE);
		this.FastEthernet.add(port, FE);
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
		SArray.add(S);
		this.Serial.add(port, S);
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
}
