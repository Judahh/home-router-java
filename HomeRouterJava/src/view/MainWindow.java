package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import connection.TelnetClient;

import javax.swing.border.EtchedBorder;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class MainWindow extends JFrame implements ActionListener {

	public MainWindow(TelnetClient telnet, String ip, int port) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		setTitle("HomeRouter");
		setVisible(true);
		setSize(800, 435);
		setLocation(200, 200);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel interfacesPanel = new JPanel();
		tabbedPane.addTab("Interfaces", null, interfacesPanel, null);
		interfacesPanel.setLayout(null);

		interfacesTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		interfacesTabbedPane.setBounds(0, 11, 789, 343);
		interfacesPanel.add(interfacesTabbedPane);

		// fast ethernet 0 panel
		fastEthernet0Panel = new JPanel();
		interfacesTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Fast Ethernet 0</body></html>",
				null, fastEthernet0Panel, null);
		fastEthernet0Panel.setLayout(null);

		JLabel fe0TitleLabel = new JLabel("Fast Ethernet 0");
		fe0TitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		fe0TitleLabel.setBounds(137, 11, 124, 24);
		fastEthernet0Panel.add(fe0TitleLabel);

		JLabel fe0PortStatusLabel = new JLabel("Port Status");
		fe0PortStatusLabel.setBounds(42, 65, 73, 14);
		fastEthernet0Panel.add(fe0PortStatusLabel);

		JLabel fe0IpAddressLabel = new JLabel("IP Address");
		fe0IpAddressLabel.setBounds(42, 100, 73, 14);
		fastEthernet0Panel.add(fe0IpAddressLabel);

		JLabel fe0SubnetMaskLabel = new JLabel("Subnet Mask");
		fe0SubnetMaskLabel.setBounds(42, 135, 83, 14);
		fastEthernet0Panel.add(fe0SubnetMaskLabel);

		JCheckBox fe0PortStatusCheckbox = new JCheckBox("On");
		fe0PortStatusCheckbox.setBounds(188, 61, 97, 23);
		fastEthernet0Panel.add(fe0PortStatusCheckbox);

		fe0IpAddressTextField = new JTextField();
		fe0IpAddressTextField.setBounds(192, 97, 86, 20);
		fastEthernet0Panel.add(fe0IpAddressTextField);
		fe0IpAddressTextField.setColumns(10);

		fe0SubnetMaskTextField = new JTextField();
		fe0SubnetMaskTextField.setBounds(192, 132, 86, 20);
		fastEthernet0Panel.add(fe0SubnetMaskTextField);
		fe0SubnetMaskTextField.setColumns(10);

		JButton fe0BtnApply = new JButton("Apply");
		fe0BtnApply.setBounds(36, 285, 89, 23);
		fe0BtnApply.setActionCommand("fe0Apply");
		fe0BtnApply.addActionListener(this);
		fastEthernet0Panel.add(fe0BtnApply);

		JButton fe0BtnClear = new JButton("Clear");
		fe0BtnClear.setBounds(188, 285, 89, 23);
		fe0BtnClear.setActionCommand("fe0Clear");
		fe0BtnClear.addActionListener(this);
		fastEthernet0Panel.add(fe0BtnClear);

		// serial 0 panel

		serial0Panel = new JPanel();
		interfacesTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Serial 0</body></html>", null,
				serial0Panel, null);
		serial0Panel.setLayout(null);

		JLabel s0TitleLabel = new JLabel("Serial 0");
		s0TitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		s0TitleLabel.setBounds(137, 11, 124, 24);
		serial0Panel.add(s0TitleLabel);

		JLabel s0PortStatusLabel = new JLabel("Port Status");
		s0PortStatusLabel.setBounds(42, 65, 73, 14);
		serial0Panel.add(s0PortStatusLabel);

		JLabel s0IpAddressLabel = new JLabel("IP Address");
		s0IpAddressLabel.setBounds(42, 100, 73, 14);
		serial0Panel.add(s0IpAddressLabel);

		JLabel s0SubnetMaskLabel = new JLabel("Subnet Mask");
		s0SubnetMaskLabel.setBounds(42, 135, 83, 14);
		serial0Panel.add(s0SubnetMaskLabel);

		JCheckBox s0PortStatusCheckbox = new JCheckBox("On");
		s0PortStatusCheckbox.setBounds(188, 61, 97, 23);
		serial0Panel.add(s0PortStatusCheckbox);

		s0IpAddressTextField = new JTextField();
		s0IpAddressTextField.setBounds(192, 97, 86, 20);
		serial0Panel.add(s0IpAddressTextField);
		s0IpAddressTextField.setColumns(10);

		s0SubnetMaskTextField = new JTextField();
		s0SubnetMaskTextField.setBounds(192, 132, 86, 20);
		serial0Panel.add(s0SubnetMaskTextField);
		s0SubnetMaskTextField.setColumns(10);

		JButton s0BtnApply = new JButton("Apply");
		s0BtnApply.setBounds(36, 285, 89, 23);
		s0BtnApply.setActionCommand("s0Apply");
		s0BtnApply.addActionListener(this);
		serial0Panel.add(s0BtnApply);

		JButton s0BtnClear = new JButton("Clear");
		s0BtnClear.setBounds(188, 285, 89, 23);
		s0BtnClear.setActionCommand("s0Clear");
		s0BtnClear.addActionListener(this);
		serial0Panel.add(s0BtnClear);

		// serial 1 panel

		serial1Panel = new JPanel();
		interfacesTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Serial 1</body></html>", null,
				serial1Panel, null);
		serial1Panel.setLayout(null);

		JLabel s1TitleLabel = new JLabel("Serial 1");
		s1TitleLabel.setBounds(137, 11, 124, 24);
		s1TitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		serial1Panel.add(s1TitleLabel);

		JLabel s1PortStatusLabel = new JLabel("Port Status");
		s1PortStatusLabel.setBounds(42, 65, 73, 14);
		serial1Panel.add(s1PortStatusLabel);

		JLabel s1IpAddressLabel = new JLabel("IP Address");
		s1IpAddressLabel.setBounds(42, 100, 73, 14);
		serial1Panel.add(s1IpAddressLabel);

		JLabel s1SubnetMaskLabel = new JLabel("Subnet Mask");
		s1SubnetMaskLabel.setBounds(42, 135, 83, 14);
		serial1Panel.add(s1SubnetMaskLabel);

		JCheckBox s1PortStatusCheckbox = new JCheckBox("On");
		s1PortStatusCheckbox.setBounds(188, 61, 97, 23);
		serial1Panel.add(s1PortStatusCheckbox);

		s1IpAddressTextField = new JTextField();
		s1IpAddressTextField.setBounds(192, 97, 86, 20);
		serial1Panel.add(s1IpAddressTextField);
		s1IpAddressTextField.setColumns(10);

		s1SubnetMaskTextField = new JTextField();
		s1SubnetMaskTextField.setBounds(192, 132, 86, 20);
		serial1Panel.add(s1SubnetMaskTextField);
		s1SubnetMaskTextField.setColumns(10);

		JButton s1BtnApply = new JButton("Apply");
		s1BtnApply.setBounds(36, 285, 89, 23);
		s1BtnApply.setActionCommand("s1Apply");
		s1BtnApply.addActionListener(this);
		serial1Panel.add(s1BtnApply);

		JButton s1BtnClear = new JButton("Clear");
		s1BtnClear.setBounds(188, 285, 89, 23);
		s1BtnClear.setActionCommand("s1Clear");
		s1BtnClear.addActionListener(this);
		serial1Panel.add(s1BtnClear);

		JPanel switchingPanel = new JPanel();
		tabbedPane.addTab("Switching", null, switchingPanel, null);

		JPanel routingPanel = new JPanel();
		tabbedPane.addTab("Routing", null, routingPanel, null);

		JPanel settingsPanel = new JPanel();
		tabbedPane.addTab("Settings", null, settingsPanel, null);

		// painel de baixo

		JPanel infoPanel = new JPanel();
		getContentPane().add(infoPanel, BorderLayout.SOUTH);

		JLabel connected = new JLabel();
		connected.setText("Connected at " + ip + " on port " + port);
		infoPanel.add(connected);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane interfacesTabbedPane;
	private JPanel fastEthernet0Panel;
	private JPanel serial0Panel;
	private JPanel serial1Panel;
	private JTextField fe0IpAddressTextField;
	private JTextField fe0SubnetMaskTextField;
	private JTextField s0IpAddressTextField;
	private JTextField s0SubnetMaskTextField;
	private JTextField s1IpAddressTextField;
	private JTextField s1SubnetMaskTextField;

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String action = arg0.getActionCommand();

		switch (action) {

		case "fe0Clear":
			fe0IpAddressTextField.setText("");
			fe0SubnetMaskTextField.setText("");

		case "s0Clear":
			s0IpAddressTextField.setText("");
			s0SubnetMaskTextField.setText("");

		case "s1Clear":
			s1IpAddressTextField.setText("");
			s1SubnetMaskTextField.setText("");

		

		}

	}
}
