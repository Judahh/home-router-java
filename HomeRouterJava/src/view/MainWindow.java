package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import connection.TelnetClient;

import java.awt.Toolkit;

public class MainWindow extends JFrame implements ActionListener {
	
	
	public MainWindow(TelnetClient telnet, String ip, int port) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
		setTitle("HomeRouter");
		setVisible(true);
		setSize(800, 435);	
		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel interfacesPanel = new JPanel();
		tabbedPane.addTab("Interfaces", null, interfacesPanel, null);

		JPanel switchingPanel = new JPanel();
		tabbedPane.addTab("Switching", null, switchingPanel, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Routing", null, panel_3, null);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Status", null, panel_4, null);
		
		JPanel infoPanel = new JPanel();
		getContentPane().add(infoPanel, BorderLayout.SOUTH);
		
		JLabel connected = new JLabel();
		connected.setText("Connected at "+ip+" on port "+port);
		infoPanel.add(connected);
		
		
		
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
