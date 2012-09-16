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
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JList;

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
		
		JSeparator fe0Separator = new JSeparator();
		fe0Separator.setBounds(42, 46, 290, 2);
		fastEthernet0Panel.add(fe0Separator);

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
		
		JSeparator s0Separator = new JSeparator();
		s0Separator.setBounds(42, 46, 290, 2);
		serial0Panel.add(s0Separator);

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
		
		JSeparator s1Separator = new JSeparator();
		s1Separator.setBounds(42, 46, 290, 2);
		serial1Panel.add(s1Separator);
		
		//switching panel

		JPanel switchingPanel = new JPanel();
		tabbedPane.addTab("Switching", null, switchingPanel, null);
		switchingPanel.setLayout(null);
		
		JTabbedPane swtichingTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		swtichingTabbedPane.setBounds(0, 11, 789, 343);
		switchingPanel.add(swtichingTabbedPane);
		
		JPanel vlanPanel = new JPanel();
		swtichingTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>VLAN Config</body></html>", null, vlanPanel, null);
		vlanPanel.setLayout(null);
		
		JLabel vlanTitleLabel = new JLabel("VLAN Configuration");
		vlanTitleLabel.setBounds(107, 11, 154, 24);
		vlanTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		vlanPanel.add(vlanTitleLabel);
		
		JLabel vlanNumberLabel = new JLabel("VLAN Number");
		vlanNumberLabel.setBounds(42, 68, 73, 14);
		vlanPanel.add(vlanNumberLabel);

		JLabel vlanNameLabel = new JLabel("VLAN Name");
		vlanNameLabel.setBounds(42, 103, 73, 14);
		vlanPanel.add(vlanNameLabel);
		
		vlanNumberTextField = new JTextField();
		vlanNumberTextField.setBounds(192, 65, 86, 20);
		vlanPanel.add(vlanNumberTextField);
		vlanNumberTextField.setColumns(10);

		vlanNameTextField = new JTextField();
		vlanNameTextField.setBounds(192, 100, 86, 20);
		vlanPanel.add(vlanNameTextField);
		vlanNameTextField.setColumns(10);
		
		JButton vlanBtnApply = new JButton("Apply");
		vlanBtnApply.setBounds(36, 285, 89, 23);
		vlanBtnApply.setActionCommand("vlanApply");
		vlanBtnApply.addActionListener(this);
		vlanPanel.add(vlanBtnApply);

		JButton vlanBtnClear = new JButton("Clear");
		vlanBtnClear.setBounds(188, 285, 89, 23);
		vlanBtnClear.setActionCommand("vlanClear");
		vlanBtnClear.addActionListener(this);
		vlanPanel.add(vlanBtnClear);
		
		JSeparator vlanSeparator = new JSeparator();
		vlanSeparator.setBounds(42, 46, 290, 2);
		vlanPanel.add(vlanSeparator);
		
		//routing panel

		JPanel routingPanel = new JPanel();
		tabbedPane.addTab("Routing", null, routingPanel, null);
		routingPanel.setLayout(null);
		
		JTabbedPane routingTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		routingTabbedPane.setBounds(0, 11, 789, 343);
		routingPanel.add(routingTabbedPane);
		
		//static routing panel
		
		JPanel staticRoutingPanel = new JPanel();
		routingTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Static</body></html>", null, staticRoutingPanel, null);
		staticRoutingPanel.setLayout(null);
		
		JLabel staticRoutingTitleLabel = new JLabel("Static Routes");
		staticRoutingTitleLabel.setBounds(137, 11, 124, 24);
		staticRoutingTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		staticRoutingPanel.add(staticRoutingTitleLabel);
		
		JLabel staticRoutingNetworkLabel = new JLabel("Network");
		staticRoutingNetworkLabel.setBounds(42, 68, 73, 14);
		staticRoutingPanel.add(staticRoutingNetworkLabel);

		JLabel staticRoutingMaskLabel = new JLabel("Mask");
		staticRoutingMaskLabel.setBounds(42, 103, 73, 14);
		staticRoutingPanel.add(staticRoutingMaskLabel);
		
		JLabel staticRoutingNextHopLabel = new JLabel("Next Hop");
		staticRoutingNextHopLabel.setBounds(42, 138, 73, 14);
		staticRoutingPanel.add(staticRoutingNextHopLabel);
		
		staticRoutingNetworkTextField = new JTextField();
		staticRoutingNetworkTextField.setBounds(192, 65, 86, 20);
		staticRoutingPanel.add(staticRoutingNetworkTextField);
		staticRoutingNetworkTextField.setColumns(10);

		staticRoutingMaskTextField = new JTextField();
		staticRoutingMaskTextField.setBounds(192, 100, 86, 20);
		staticRoutingPanel.add(staticRoutingMaskTextField);
		staticRoutingMaskTextField.setColumns(10);
		
		staticRoutingNextHopTextField = new JTextField();
		staticRoutingNextHopTextField.setBounds(192, 135, 86, 20);
		staticRoutingPanel.add(staticRoutingNextHopTextField);
		staticRoutingNextHopTextField.setColumns(10);
		
		JButton staticRoutingBtnApply = new JButton("Apply");
		staticRoutingBtnApply.setBounds(36, 285, 89, 23);
		staticRoutingBtnApply.setActionCommand("staticRoutingApply");
		staticRoutingBtnApply.addActionListener(this);
		staticRoutingPanel.add(staticRoutingBtnApply);

		JButton staticRoutingBtnClear = new JButton("Clear");
		staticRoutingBtnClear.setBounds(188, 285, 89, 23);
		staticRoutingBtnClear.setActionCommand("staticRoutingClear");
		staticRoutingBtnClear.addActionListener(this);
		staticRoutingPanel.add(staticRoutingBtnClear);
		
		JScrollPane staticRoutingScrollPane = new JScrollPane();
		staticRoutingScrollPane.setBounds(390, 65, 288, 211);
		staticRoutingPanel.add(staticRoutingScrollPane);
		
		JList staticEstablishedRoutesList = new JList();
		staticRoutingScrollPane.setViewportView(staticEstablishedRoutesList);
		
		JLabel staticRoutingTitle2Label = new JLabel("Established Routes");
		staticRoutingTitle2Label.setBounds(467, 11, 154, 24);
		staticRoutingTitle2Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		staticRoutingPanel.add(staticRoutingTitle2Label);
		
		JSeparator staticRoutingHorizontalSeparator = new JSeparator();
		staticRoutingHorizontalSeparator.setBounds(42, 46, 290, 2);
		staticRoutingPanel.add(staticRoutingHorizontalSeparator);
		
		JSeparator staticRoutingVerticalSeparator = new JSeparator();
		staticRoutingVerticalSeparator.setOrientation(SwingConstants.VERTICAL);
		staticRoutingVerticalSeparator.setBounds(369, 21, 2, 300);
		staticRoutingPanel.add(staticRoutingVerticalSeparator);
		
		JButton staticRoutingBtnRemove = new JButton("Remove");
		staticRoutingBtnRemove.setBounds(390, 285, 89, 23);
		staticRoutingPanel.add(staticRoutingBtnRemove);
		
		JSeparator staticRoutingHorizontal2Separator = new JSeparator();
		staticRoutingHorizontal2Separator.setBounds(390, 46, 290, 2);
		staticRoutingPanel.add(staticRoutingHorizontal2Separator);
		
		
		//dynamic routing panel
		
		JPanel dynamicRoutingPanel = new JPanel();
		routingTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Dynamic</body></html>", null, dynamicRoutingPanel, null);
		dynamicRoutingPanel.setLayout(null);

		JLabel dynamicRoutingTitleLabel = new JLabel("RIP Routing");
		dynamicRoutingTitleLabel.setBounds(137, 11, 124, 24);
		dynamicRoutingTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		dynamicRoutingPanel.add(dynamicRoutingTitleLabel);
		
		JLabel dynamicRoutingNetworkLabel = new JLabel("Network");
		dynamicRoutingNetworkLabel.setBounds(42, 68, 73, 14);
		dynamicRoutingPanel.add(dynamicRoutingNetworkLabel);		
		
		dynamicRoutingNetworkTextField = new JTextField();
		dynamicRoutingNetworkTextField.setBounds(192, 65, 86, 20);
		dynamicRoutingPanel.add(dynamicRoutingNetworkTextField);
		dynamicRoutingNetworkTextField.setColumns(10);		
		
		JButton dynamicRoutingBtnApply = new JButton("Apply");
		dynamicRoutingBtnApply.setBounds(36, 285, 89, 23);
		dynamicRoutingBtnApply.setActionCommand("dynamicRoutingApply");
		dynamicRoutingBtnApply.addActionListener(this);
		dynamicRoutingPanel.add(dynamicRoutingBtnApply);

		JButton dynamicRoutingBtnClear = new JButton("Clear");
		dynamicRoutingBtnClear.setBounds(188, 285, 89, 23);
		dynamicRoutingBtnClear.setActionCommand("dynamicRoutingClear");
		dynamicRoutingBtnClear.addActionListener(this);
		dynamicRoutingPanel.add(dynamicRoutingBtnClear);
		
		JScrollPane dynamicRoutingScrollPane = new JScrollPane();
		dynamicRoutingScrollPane.setBounds(390, 65, 288, 211);
		dynamicRoutingPanel.add(dynamicRoutingScrollPane);
		
		JList dynamicEstablishedRoutesList = new JList();
		dynamicRoutingScrollPane.setViewportView(dynamicEstablishedRoutesList);
		
		JLabel dynamicRoutingTitle2Label = new JLabel("Established Routes");
		dynamicRoutingTitle2Label.setBounds(467, 11, 154, 24);
		dynamicRoutingTitle2Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		dynamicRoutingPanel.add(dynamicRoutingTitle2Label);
		
		JSeparator dynamicRoutingHorizontalSeparator = new JSeparator();
		dynamicRoutingHorizontalSeparator.setBounds(42, 46, 290, 2);
		dynamicRoutingPanel.add(dynamicRoutingHorizontalSeparator);
		
		JSeparator dynamicRoutingVerticalSeparator = new JSeparator();
		dynamicRoutingVerticalSeparator.setOrientation(SwingConstants.VERTICAL);
		dynamicRoutingVerticalSeparator.setBounds(369, 21, 2, 300);
		dynamicRoutingPanel.add(dynamicRoutingVerticalSeparator);
		
		JButton dynamicRoutingBtnRemove = new JButton("Remove");
		dynamicRoutingBtnRemove.setBounds(390, 285, 89, 23);
		dynamicRoutingPanel.add(dynamicRoutingBtnRemove);
		
		JSeparator dynamicRoutingHorizontal2Separator = new JSeparator();
		dynamicRoutingHorizontal2Separator.setBounds(390, 46, 290, 2);
		dynamicRoutingPanel.add(dynamicRoutingHorizontal2Separator);
		
		//settings panel
		
		JPanel settingsPanel = new JPanel();
		tabbedPane.addTab("Settings", null, settingsPanel, null);
		settingsPanel.setLayout(null);
		
		JTabbedPane settingsTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		settingsTabbedPane.setBounds(0, 11, 789, 343);
		settingsPanel.add(settingsTabbedPane);

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
	private JTextField vlanNumberTextField;
	private JTextField vlanNameTextField;
	private JTextField staticRoutingNetworkTextField;
	private JTextField staticRoutingMaskTextField;
	private JTextField staticRoutingNextHopTextField;
	private JTextField dynamicRoutingNetworkTextField;

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
			
		case "vlanClear":
			
			vlanNumberTextField.setText("");
			vlanNameTextField.setText("");
		

		}

	}
}
