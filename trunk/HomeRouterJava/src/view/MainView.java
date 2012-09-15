package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;

public class MainView extends JFrame implements ActionListener {
	
	private ConnectWindow cw;
	public MainView() {
		
		setResizable(false);
		setTitle("HomeRouter");
		setVisible(true);
		setSize(800, 435);
		setName("Main window");
		

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setEnabled(false);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Interfaces", null, panel_1, null);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Switching", null, panel_2, null);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Routing", null, panel_3, null);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Status", null, panel_4, null);
		
		cw = new ConnectWindow();
		
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
