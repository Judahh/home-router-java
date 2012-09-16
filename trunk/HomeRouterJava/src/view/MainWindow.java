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
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;

public class MainWindow extends JFrame implements ActionListener {

	private JPanel FastEthernet0Panel;
	private JPanel Serial0Panel;

	public MainWindow(TelnetClient telnet, String ip, int port) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setResizable(false);
		setTitle("HomeRouter");
		setVisible(true);
		setSize(800, 435);
		setLocation(200, 200);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel interfacesPanel = new JPanel();
		tabbedPane.addTab("Interfaces", null, interfacesPanel, null);
		GridBagLayout gbl_interfacesPanel = new GridBagLayout();
		gbl_interfacesPanel.columnWidths = new int[] { 111, 410 };
		gbl_interfacesPanel.rowHeights = new int[] { 353 };
		gbl_interfacesPanel.columnWeights = new double[] { 0.0, 1.0 };
		gbl_interfacesPanel.rowWeights = new double[] { 1.0 };
		interfacesPanel.setLayout(gbl_interfacesPanel);

		JPanel interfacesLeftPanel = new JPanel();
		interfacesLeftPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_interfacesLeftPanel = new GridBagConstraints();
		gbc_interfacesLeftPanel.anchor = GridBagConstraints.WEST;
		gbc_interfacesLeftPanel.fill = GridBagConstraints.VERTICAL;
		gbc_interfacesLeftPanel.insets = new Insets(0, 0, 0, 5);
		gbc_interfacesLeftPanel.gridx = 0;
		gbc_interfacesLeftPanel.gridy = 0;
		interfacesPanel.add(interfacesLeftPanel, gbc_interfacesLeftPanel);
		interfacesLeftPanel.setSize(200, 200);

		JButton btnSerial0 = new JButton("Serial 0");
		btnSerial0.addActionListener(this);
		btnSerial0.setActionCommand("Serial0");
		interfacesLeftPanel.setLayout(new GridLayout(12, 1, 0, 0));

		JButton btnFastEthernet = new JButton("Fast Ethernet 0");
		btnFastEthernet.addActionListener(this);
		btnFastEthernet.setActionCommand("FastEthernet");
		btnFastEthernet.setHorizontalAlignment(SwingConstants.LEFT);
		interfacesLeftPanel.add(btnFastEthernet);
		interfacesLeftPanel.add(btnSerial0);

		JButton btnSerial1 = new JButton("Serial 1");		
		btnSerial1.addActionListener(this);
		btnSerial1.setActionCommand("Serial1");
		interfacesLeftPanel.add(btnSerial1);

		FastEthernet0Panel = new JPanel();
		FastEthernet0Panel.setLayout(null);
		GridBagConstraints gbc_FastEthernet0Panel = new GridBagConstraints();
		gbc_FastEthernet0Panel.fill = GridBagConstraints.BOTH;
		gbc_FastEthernet0Panel.gridx = 1;
		gbc_FastEthernet0Panel.gridy = 0;
		interfacesPanel.add(FastEthernet0Panel, gbc_FastEthernet0Panel);

		lblFE0PortStatus = new JLabel("Port Status");
		lblFE0PortStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFE0PortStatus.setBounds(31, 66, 77, 17);
		FastEthernet0Panel.add(lblFE0PortStatus);

		FE0chckbxOn = new JCheckBox("On");
		FE0chckbxOn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FE0chckbxOn.setBounds(233, 64, 97, 23);
		FastEthernet0Panel.add(FE0chckbxOn);

		lblFE0IpAddress = new JLabel("IP Address");
		lblFE0IpAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFE0IpAddress.setBounds(31, 106, 77, 14);
		FastEthernet0Panel.add(lblFE0IpAddress);

		FE0IPTextField = new JTextField();
		FE0IPTextField.setBounds(233, 104, 86, 20);
		FastEthernet0Panel.add(FE0IPTextField);
		FE0IPTextField.setColumns(10);

		lblFE0SubnetMask = new JLabel("Subnet Mask");
		lblFE0SubnetMask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFE0SubnetMask.setBounds(31, 146, 86, 14);
		FastEthernet0Panel.add(lblFE0SubnetMask);

		FE0MaskTextField = new JTextField();
		FE0MaskTextField.setBounds(233, 144, 86, 20);
		FastEthernet0Panel.add(FE0MaskTextField);
		FE0MaskTextField.setColumns(10);
		
		lblFastEthernet = new JLabel("Fast Ethernet 0");
		lblFastEthernet.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFastEthernet.setBounds(136, 25, 125, 17);
		FastEthernet0Panel.add(lblFastEthernet);

		Serial0Panel = new JPanel();
		Serial0Panel.setLayout(null);
		
		
		GridBagConstraints gbc_Serial0Panel = new GridBagConstraints();
		gbc_Serial0Panel.fill = GridBagConstraints.BOTH;
		gbc_Serial0Panel.gridx = 1;
		gbc_Serial0Panel.gridy = 0;
		interfacesPanel.add(Serial0Panel, gbc_Serial0Panel);

		lblS0PortStatus = new JLabel("Port Status");
		lblS0PortStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblS0PortStatus.setBounds(31, 28, 77, 17);
		Serial0Panel.add(lblS0PortStatus);

		S0chckbxOn = new JCheckBox("On");
		S0chckbxOn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		S0chckbxOn.setBounds(233, 26, 97, 23);
		Serial0Panel.add(S0chckbxOn);

		lblS0IpAddress = new JLabel("IP Address");
		lblS0IpAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblS0IpAddress.setBounds(31, 68, 77, 14);
		Serial0Panel.add(lblS0IpAddress);

		S0IPTextField = new JTextField();
		S0IPTextField.setBounds(233, 66, 86, 20);
		Serial0Panel.add(S0IPTextField);
		S0IPTextField.setColumns(10);

		lblS0SubnetMask = new JLabel("Subnet Mask");
		lblS0SubnetMask.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblS0SubnetMask.setBounds(31, 108, 86, 14);
		Serial0Panel.add(lblS0SubnetMask);

		s0MaskTextField = new JTextField();
		s0MaskTextField.setBounds(233, 106, 86, 20);
		Serial0Panel.add(s0MaskTextField);
		s0MaskTextField.setColumns(10);

		JPanel switchingPanel = new JPanel();
		tabbedPane.addTab("Switching", null, switchingPanel, null);

		JPanel routingPanel = new JPanel();
		tabbedPane.addTab("Routing", null, routingPanel, null);

		JPanel settingsPanel = new JPanel();
		tabbedPane.addTab("Settings", null, settingsPanel, null);

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

	private JTextField S0IPTextField;
	private JTextField FE0IPTextField;

	private JTextField s0MaskTextField;
	private JTextField FE0MaskTextField;
	private JLabel lblFE0PortStatus;
	private JCheckBox FE0chckbxOn;
	private JLabel lblFE0IpAddress;
	private JLabel lblFE0SubnetMask;
	private JLabel lblS0PortStatus;
	private JCheckBox S0chckbxOn;
	private JLabel lblS0IpAddress;
	private JLabel lblS0SubnetMask;
	private JLabel lblFastEthernet;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("FastEthernet")) {
			FastEthernet0Panel.setVisible(true);
			Serial0Panel.setVisible(false);
		} else if (arg0.getActionCommand().equals("Serial0")){
			FastEthernet0Panel.setVisible(false);
			Serial0Panel.setVisible(true);
		}

	}
}
