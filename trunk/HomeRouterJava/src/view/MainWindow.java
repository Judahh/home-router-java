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
import javax.swing.JTextArea;

public class MainWindow extends JFrame implements ActionListener {

	public MainWindow(TelnetClient telnet, String ip, int port) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		setTitle("HomeRouter");
		setVisible(true);
		setSize(800, 435);
		setLocation(200, 200);

		JTabbedPane mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainTabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(mainTabbedPane, BorderLayout.CENTER);

		JPanel interfacesPanel = new JPanel();
		mainTabbedPane.addTab("Interfaces", null, interfacesPanel, null);
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
		fe0IpAddressTextField.setBounds(192, 97, 100, 20);
		fastEthernet0Panel.add(fe0IpAddressTextField);
		fe0IpAddressTextField.setColumns(12);

		fe0SubnetMaskTextField = new JTextField();
		fe0SubnetMaskTextField.setBounds(192, 132, 100, 20);
		fastEthernet0Panel.add(fe0SubnetMaskTextField);
		fe0SubnetMaskTextField.setColumns(12);

		JButton fe0BtnApply = new JButton("Apply");
		fe0BtnApply.setBounds(36, 285, 89, 23);
		fe0BtnApply.setActionCommand("fe0Apply");
		fe0BtnApply.addActionListener(this);
		fastEthernet0Panel.add(fe0BtnApply);

		JButton fe0BtnClear = new JButton("Clear");
		fe0BtnClear.setBounds(195, 285, 89, 23);
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
		s0IpAddressTextField.setBounds(192, 97, 100, 20);
		serial0Panel.add(s0IpAddressTextField);
		s0IpAddressTextField.setColumns(12);

		s0SubnetMaskTextField = new JTextField();
		s0SubnetMaskTextField.setBounds(192, 132, 100, 20);
		serial0Panel.add(s0SubnetMaskTextField);
		s0SubnetMaskTextField.setColumns(12);

		JButton s0BtnApply = new JButton("Apply");
		s0BtnApply.setBounds(36, 285, 89, 23);
		s0BtnApply.setActionCommand("s0Apply");
		s0BtnApply.addActionListener(this);
		serial0Panel.add(s0BtnApply);

		JButton s0BtnClear = new JButton("Clear");
		s0BtnClear.setBounds(195, 285, 89, 23);
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
		s1IpAddressTextField.setBounds(192, 97, 100, 20);
		serial1Panel.add(s1IpAddressTextField);
		s1IpAddressTextField.setColumns(12);

		s1SubnetMaskTextField = new JTextField();
		s1SubnetMaskTextField.setBounds(192, 132, 100, 20);
		serial1Panel.add(s1SubnetMaskTextField);
		s1SubnetMaskTextField.setColumns(12);

		JButton s1BtnApply = new JButton("Apply");
		s1BtnApply.setBounds(36, 285, 89, 23);
		s1BtnApply.setActionCommand("s1Apply");
		s1BtnApply.addActionListener(this);
		serial1Panel.add(s1BtnApply);

		JButton s1BtnClear = new JButton("Clear");
		s1BtnClear.setBounds(195, 285, 89, 23);
		s1BtnClear.setActionCommand("s1Clear");
		s1BtnClear.addActionListener(this);
		serial1Panel.add(s1BtnClear);

		JSeparator s1Separator = new JSeparator();
		s1Separator.setBounds(42, 46, 290, 2);
		serial1Panel.add(s1Separator);

		// switching panel

		JPanel switchingPanel = new JPanel();
		mainTabbedPane.addTab("Switching", null, switchingPanel, null);
		switchingPanel.setLayout(null);

		JTabbedPane swtichingTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		swtichingTabbedPane.setBounds(0, 11, 789, 343);
		switchingPanel.add(swtichingTabbedPane);

		JPanel vlanPanel = new JPanel();
		swtichingTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>VLAN Config</body></html>", null,
				vlanPanel, null);
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
		vlanNumberTextField.setBounds(192, 65, 100, 20);
		vlanPanel.add(vlanNumberTextField);
		vlanNumberTextField.setColumns(12);

		vlanNameTextField = new JTextField();
		vlanNameTextField.setBounds(192, 100, 100, 20);
		vlanPanel.add(vlanNameTextField);
		vlanNameTextField.setColumns(12);

		JButton vlanBtnApply = new JButton("Apply");
		vlanBtnApply.setBounds(36, 285, 89, 23);
		vlanBtnApply.setActionCommand("vlanApply");
		vlanBtnApply.addActionListener(this);
		vlanPanel.add(vlanBtnApply);

		JButton vlanBtnClear = new JButton("Clear");
		vlanBtnClear.setBounds(195, 285, 89, 23);
		vlanBtnClear.setActionCommand("vlanClear");
		vlanBtnClear.addActionListener(this);
		vlanPanel.add(vlanBtnClear);

		JSeparator vlanSeparator = new JSeparator();
		vlanSeparator.setBounds(42, 46, 290, 2);
		vlanPanel.add(vlanSeparator);

		// routing panel

		JPanel routingPanel = new JPanel();
		mainTabbedPane.addTab("Routing", null, routingPanel, null);
		routingPanel.setLayout(null);

		JTabbedPane routingTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		routingTabbedPane.setBounds(0, 11, 789, 343);
		routingPanel.add(routingTabbedPane);

		// static routing panel

		JPanel staticRoutingPanel = new JPanel();
		routingTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Static</body></html>", null,
				staticRoutingPanel, null);
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
		staticRoutingNetworkTextField.setBounds(192, 65, 100, 20);
		staticRoutingPanel.add(staticRoutingNetworkTextField);
		staticRoutingNetworkTextField.setColumns(12);

		staticRoutingMaskTextField = new JTextField();
		staticRoutingMaskTextField.setBounds(192, 100, 100, 20);
		staticRoutingPanel.add(staticRoutingMaskTextField);
		staticRoutingMaskTextField.setColumns(12);

		staticRoutingNextHopTextField = new JTextField();
		staticRoutingNextHopTextField.setBounds(192, 135, 100, 20);
		staticRoutingPanel.add(staticRoutingNextHopTextField);
		staticRoutingNextHopTextField.setColumns(12);

		JButton staticRoutingBtnApply = new JButton("Apply");
		staticRoutingBtnApply.setBounds(36, 285, 89, 23);
		staticRoutingBtnApply.setActionCommand("staticRoutingApply");
		staticRoutingBtnApply.addActionListener(this);
		staticRoutingPanel.add(staticRoutingBtnApply);

		JButton staticRoutingBtnClear = new JButton("Clear");
		staticRoutingBtnClear.setBounds(195, 285, 89, 23);
		staticRoutingBtnClear.setActionCommand("staticRoutingClear");
		staticRoutingBtnClear.addActionListener(this);
		staticRoutingPanel.add(staticRoutingBtnClear);

		JScrollPane staticRoutingScrollPane = new JScrollPane();
		staticRoutingScrollPane.setBounds(380, 65, 288, 211);
		staticRoutingPanel.add(staticRoutingScrollPane);

		JList staticEstablishedRoutesList = new JList();
		staticRoutingScrollPane.setViewportView(staticEstablishedRoutesList);

		JLabel staticRoutingTitle2Label = new JLabel("Established Routes");
		staticRoutingTitle2Label.setBounds(457, 11, 154, 24);
		staticRoutingTitle2Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		staticRoutingPanel.add(staticRoutingTitle2Label);

		JSeparator staticRoutingHorizontalSeparator = new JSeparator();
		staticRoutingHorizontalSeparator.setBounds(42, 46, 290, 2);
		staticRoutingPanel.add(staticRoutingHorizontalSeparator);

		JSeparator staticRoutingVerticalSeparator = new JSeparator();
		staticRoutingVerticalSeparator.setOrientation(SwingConstants.VERTICAL);
		staticRoutingVerticalSeparator.setBounds(359, 21, 2, 300);
		staticRoutingPanel.add(staticRoutingVerticalSeparator);

		JButton staticRoutingBtnRemove = new JButton("Remove");
		staticRoutingBtnRemove.setBounds(380, 285, 89, 23);
		staticRoutingPanel.add(staticRoutingBtnRemove);

		JSeparator staticRoutingHorizontal2Separator = new JSeparator();
		staticRoutingHorizontal2Separator.setBounds(380, 46, 290, 2);
		staticRoutingPanel.add(staticRoutingHorizontal2Separator);

		// dynamic routing panel

		JPanel dynamicRoutingPanel = new JPanel();
		routingTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Dynamic</body></html>", null,
				dynamicRoutingPanel, null);
		dynamicRoutingPanel.setLayout(null);

		JLabel dynamicRoutingTitleLabel = new JLabel("RIP Routing");
		dynamicRoutingTitleLabel.setBounds(137, 11, 124, 24);
		dynamicRoutingTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		dynamicRoutingPanel.add(dynamicRoutingTitleLabel);

		JLabel dynamicRoutingNetworkLabel = new JLabel("Network");
		dynamicRoutingNetworkLabel.setBounds(42, 68, 73, 14);
		dynamicRoutingPanel.add(dynamicRoutingNetworkLabel);

		dynamicRoutingNetworkTextField = new JTextField();
		dynamicRoutingNetworkTextField.setBounds(192, 65, 100, 20);
		dynamicRoutingPanel.add(dynamicRoutingNetworkTextField);
		dynamicRoutingNetworkTextField.setColumns(12);

		JButton dynamicRoutingBtnApply = new JButton("Apply");
		dynamicRoutingBtnApply.setBounds(36, 285, 89, 23);
		dynamicRoutingBtnApply.setActionCommand("dynamicRoutingApply");
		dynamicRoutingBtnApply.addActionListener(this);
		dynamicRoutingPanel.add(dynamicRoutingBtnApply);

		JButton dynamicRoutingBtnClear = new JButton("Clear");
		dynamicRoutingBtnClear.setBounds(195, 285, 89, 23);
		dynamicRoutingBtnClear.setActionCommand("dynamicRoutingClear");
		dynamicRoutingBtnClear.addActionListener(this);
		dynamicRoutingPanel.add(dynamicRoutingBtnClear);

		JScrollPane dynamicRoutingScrollPane = new JScrollPane();
		dynamicRoutingScrollPane.setBounds(380, 65, 288, 211);
		dynamicRoutingPanel.add(dynamicRoutingScrollPane);

		JList dynamicEstablishedRoutesList = new JList();
		dynamicRoutingScrollPane.setViewportView(dynamicEstablishedRoutesList);

		JLabel dynamicRoutingTitle2Label = new JLabel("Established Routes");
		dynamicRoutingTitle2Label.setBounds(457, 11, 154, 24);
		dynamicRoutingTitle2Label.setFont(new Font("Tahoma", Font.BOLD, 14));
		dynamicRoutingPanel.add(dynamicRoutingTitle2Label);

		JSeparator dynamicRoutingHorizontalSeparator = new JSeparator();
		dynamicRoutingHorizontalSeparator.setBounds(42, 46, 290, 2);
		dynamicRoutingPanel.add(dynamicRoutingHorizontalSeparator);

		JSeparator dynamicRoutingVerticalSeparator = new JSeparator();
		dynamicRoutingVerticalSeparator.setOrientation(SwingConstants.VERTICAL);
		dynamicRoutingVerticalSeparator.setBounds(359, 21, 2, 300);
		dynamicRoutingPanel.add(dynamicRoutingVerticalSeparator);

		JButton dynamicRoutingBtnRemove = new JButton("Remove");
		dynamicRoutingBtnRemove.setBounds(380, 285, 89, 23);
		dynamicRoutingPanel.add(dynamicRoutingBtnRemove);

		JSeparator dynamicRoutingHorizontal2Separator = new JSeparator();
		dynamicRoutingHorizontal2Separator.setBounds(380, 46, 290, 2);
		dynamicRoutingPanel.add(dynamicRoutingHorizontal2Separator);

		// settings panel

		JPanel settingsPanel = new JPanel();
		mainTabbedPane.addTab("Settings", null, settingsPanel, null);
		settingsPanel.setLayout(null);

		JTabbedPane settingsTabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		settingsTabbedPane.setBounds(0, 11, 789, 343);
		settingsPanel.add(settingsTabbedPane);

		JPanel globalSettingsPanel = new JPanel();
		settingsTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Global Settings</body></html>", null, globalSettingsPanel, null);
		globalSettingsPanel.setLayout(null);

		JLabel globalSettingsTitleLabel = new JLabel("Global Settings");
		globalSettingsTitleLabel.setBounds(137, 11, 124, 24);
		globalSettingsTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		globalSettingsPanel.add(globalSettingsTitleLabel);

		JLabel globalSettingsNetworkLabel = new JLabel("Hostname");
		globalSettingsNetworkLabel.setBounds(42, 68, 73, 14);
		globalSettingsPanel.add(globalSettingsNetworkLabel);

		JLabel globalSettingsMaskLabel = new JLabel("Router Date");
		globalSettingsMaskLabel.setBounds(42, 103, 73, 14);
		globalSettingsPanel.add(globalSettingsMaskLabel);

		JLabel globalSettingsNextHopLabel = new JLabel("Router Time");
		globalSettingsNextHopLabel.setBounds(42, 138, 73, 14);
		globalSettingsPanel.add(globalSettingsNextHopLabel);

		globalSettingsHostnameTextField = new JTextField();
		globalSettingsHostnameTextField.setBounds(192, 65, 100, 20);
		globalSettingsPanel.add(globalSettingsHostnameTextField);
		globalSettingsHostnameTextField.setColumns(12);

		globalSettingsRouterDateTextField = new JTextField();
		globalSettingsRouterDateTextField.setToolTipText("Use format \"DD/MM/YYYY\"");
		globalSettingsRouterDateTextField.setBounds(192, 100, 100, 20);
		globalSettingsPanel.add(globalSettingsRouterDateTextField);
		globalSettingsRouterDateTextField.setColumns(12);

		globalSettingsRouterTimeTextField = new JTextField();
		globalSettingsRouterTimeTextField.setToolTipText("Use format \"HH:mm:ss\"");
		globalSettingsRouterTimeTextField.setBounds(192, 135, 100, 20);
		globalSettingsPanel.add(globalSettingsRouterTimeTextField);
		globalSettingsRouterTimeTextField.setColumns(12);

		JButton globalSettingsBtnApply = new JButton("Apply");
		globalSettingsBtnApply.setBounds(36, 285, 89, 23);
		globalSettingsBtnApply.setActionCommand("globalSettingsApply");
		globalSettingsBtnApply.addActionListener(this);
		globalSettingsPanel.add(globalSettingsBtnApply);

		JButton globalSettingsBtnClear = new JButton("Clear");
		globalSettingsBtnClear.setBounds(195, 285, 89, 23);
		globalSettingsBtnClear.setActionCommand("globalSettingsClear");
		globalSettingsBtnClear.addActionListener(this);
		globalSettingsPanel.add(globalSettingsBtnClear);

		JSeparator globalSettingsHorizontalSeparator = new JSeparator();
		globalSettingsHorizontalSeparator.setBounds(42, 46, 290, 2);
		globalSettingsPanel.add(globalSettingsHorizontalSeparator);
		
		//passwords panel
		
		JPanel passwordsPanel = new JPanel();
		settingsTabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Password Settings</body></html>", null, passwordsPanel, null);
		passwordsPanel.setLayout(null);
		
		JLabel passwordsTitleLabel = new JLabel("Password Settings");
		passwordsTitleLabel.setBounds(107, 11, 154, 24);
		passwordsTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordsPanel.add(passwordsTitleLabel);

		JLabel passwordsConsolePasswordLabel = new JLabel("Console Password");
		passwordsConsolePasswordLabel.setBounds(42, 68, 93, 14);
		passwordsPanel.add(passwordsConsolePasswordLabel);

		JLabel passwordsVTYPasswordLabel = new JLabel("VTY Password");
		passwordsVTYPasswordLabel.setBounds(42, 103, 73, 14);
		passwordsPanel.add(passwordsVTYPasswordLabel);		
		
		JLabel passwordsEnablePasswordLabel = new JLabel("Enable Password");
		passwordsEnablePasswordLabel.setBounds(42, 138, 83, 14);
		passwordsPanel.add(passwordsEnablePasswordLabel);	
		
		JLabel passwordsEnableSecretPasswordLabel = new JLabel("Enable Secret Password");
		passwordsEnableSecretPasswordLabel.setBounds(42, 173, 123, 14);
		passwordsPanel.add(passwordsEnableSecretPasswordLabel);	

		passwordsConsolePasswordTextField = new JTextField();
		passwordsConsolePasswordTextField.setBounds(192, 65, 100, 20);
		passwordsPanel.add(passwordsConsolePasswordTextField);
		passwordsConsolePasswordTextField.setColumns(12);

		passwordsVTYPasswordTextField = new JTextField();		
		passwordsVTYPasswordTextField.setBounds(192, 100, 100, 20);
		passwordsPanel.add(passwordsVTYPasswordTextField);
		passwordsVTYPasswordTextField.setColumns(12);
		
		passwordsEnablePasswordTextField = new JTextField();		
		passwordsEnablePasswordTextField.setBounds(192, 135, 100, 20);
		passwordsPanel.add(passwordsEnablePasswordTextField);
		passwordsEnablePasswordTextField.setColumns(12);
		
		passwordsEnableSecretPasswordTextField = new JTextField();		
		passwordsEnableSecretPasswordTextField.setBounds(192, 170, 100, 20);
		passwordsPanel.add(passwordsEnableSecretPasswordTextField);
		passwordsEnableSecretPasswordTextField.setColumns(12);
		

		JButton passwordsBtnApply = new JButton("Apply");
		passwordsBtnApply.setBounds(36, 285, 89, 23);
		passwordsBtnApply.setActionCommand("passwordsApply");
		passwordsBtnApply.addActionListener(this);
		passwordsPanel.add(passwordsBtnApply);

		JButton passwordsBtnClear = new JButton("Clear");
		passwordsBtnClear.setBounds(195, 285, 89, 23);
		passwordsBtnClear.setActionCommand("passwordsClear");
		passwordsBtnClear.addActionListener(this);
		passwordsPanel.add(passwordsBtnClear);

		JSeparator passwordsHorizontalSeparator = new JSeparator();
		passwordsHorizontalSeparator.setBounds(42, 46, 290, 2);
		passwordsPanel.add(passwordsHorizontalSeparator);
		
		//connectivity panel
		
		JPanel connectivityPanel = new JPanel();
		mainTabbedPane.addTab("Connectivity", null, connectivityPanel, null);
		connectivityPanel.setLayout(null);
		
		// cli panel
		
		JPanel cliPanel = new JPanel();
		mainTabbedPane.addTab("CLI", null, cliPanel, null);
		cliPanel.setLayout(null);
		
		JScrollPane cliScrollPane = new JScrollPane();
		cliScrollPane.setBounds(0, 11, 789, 303);
		cliPanel.add(cliScrollPane);
		
		JTextArea cliTextArea = new JTextArea();
		cliScrollPane.setViewportView(cliTextArea);
		
		cliSendCommandTextField = new JTextField();
		cliSendCommandTextField.setToolTipText("Press Enter to send a command to Router");
		cliSendCommandTextField.setBounds(10, 319, 608, 20);
		cliPanel.add(cliSendCommandTextField);
		cliSendCommandTextField.setColumns(12);
		
		
		// help panel
		
		JPanel helpPanel = new JPanel();
		mainTabbedPane.addTab("Help", null, helpPanel, null);
		helpPanel.setLayout(null);

		// painel de baixo

		JPanel infoPanel = new JPanel();
		getContentPane().add(infoPanel, BorderLayout.SOUTH);

		JLabel connected = new JLabel();
		connected.setText("Connected on router 'Router' using IOS version 12.4 at " + ip + " on port " + port);
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
	private JTextField globalSettingsHostnameTextField;
	private JTextField globalSettingsRouterDateTextField;
	private JTextField globalSettingsRouterTimeTextField;
	private JTextField passwordsConsolePasswordTextField;
	private JTextField passwordsVTYPasswordTextField;
	private JTextField passwordsEnablePasswordTextField;
	private JTextField passwordsEnableSecretPasswordTextField;
	private JTextField cliSendCommandTextField;

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

		case "staticRoutingClear":
			staticRoutingNetworkTextField.setText("");
			staticRoutingMaskTextField.setText("");
			staticRoutingNextHopTextField.setText("");

		case "dynamicRoutingClear":
			dynamicRoutingNetworkTextField.setText("");

		case "globalSettingsClear":
			globalSettingsHostnameTextField.setText("");
			globalSettingsRouterDateTextField.setText("");
			globalSettingsRouterTimeTextField.setText("");
			
		case "passwordsClear":
			passwordsConsolePasswordTextField.setText("");
			passwordsVTYPasswordTextField.setText("");
			passwordsEnablePasswordTextField.setText("");
			passwordsEnableSecretPasswordTextField.setText("");		

		}

	}
}
