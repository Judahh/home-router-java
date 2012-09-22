package unused;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import connection.ConnectionHandler;
import connection.RouterHandler;

import model.IPModel;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import java.awt.Dimension;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.GUISolutionModel;

public class ConnectWindow extends JFrame implements ActionListener {

	private String ip;
	private int port;
	private String password;

	public ConnectWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConnectWindow.class.getResource("/resources/router icon.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setTitle("Home Router - Connect");
		setResizable(false);

		setSize(310, 200);

		// centralizar tela inicial
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblIpaddress = new JLabel("IP Address");

		lblIpaddress.setBounds(20, 32, 100, 14);
		panel.add(lblIpaddress);

		JLabel lblNewLabel = new JLabel("Port");

		lblNewLabel.setBounds(20, 60, 100, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");

		lblNewLabel_1.setBounds(20, 90, 100, 14);
		panel.add(lblNewLabel_1);

		JButton btnConnect = new JButton("Connect");
		btnConnect.setActionCommand("Connect");
		btnConnect.addActionListener(this);
		btnConnect.setBounds(10, 136, 89, 23);
		panel.add(btnConnect);

		JButton btnClean = new JButton("Clear");
		btnClean.setActionCommand("Clear");
		btnClean.addActionListener(this);
		btnClean.setBounds(205, 136, 89, 23);
		panel.add(btnClean);

		ipaddressTextField = new JTextField();
		ipaddressTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					connect();
				}
			}

		});
		ipaddressTextField.setBounds(130, 31, 164, 20);
		ipaddressTextField.setText("127.0.0.1");
		panel.add(ipaddressTextField);

		passwordTextField = new JTextField();
		passwordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					connect();
				}
			}

		});
		passwordTextField.setBounds(130, 87, 164, 20);
		passwordTextField.setText("cisco");
		panel.add(passwordTextField);

		portTextField = new JTextField();
		portTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					connect();
				}
			}

		});
		portTextField.setText("2001");
		portTextField.setBounds(130, 59, 164, 20);
		panel.add(portTextField);
		portTextField.setColumns(10);

		setVisible(true);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField ipaddressTextField;
	private JTextField passwordTextField;
	private JTextField portTextField;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Connect")) {
			if (validateFields()) {
				connect();

			}

		} else if (arg0.getActionCommand().equals("Clear")) {
			ipaddressTextField.setText("");
			portTextField.setText("");
			passwordTextField.setText("");
		}

	}

	// valida os campos
	private boolean validateFields() {
		ip = ipaddressTextField.getText();
		if (ip.equals("")) {
			JOptionPane.showMessageDialog(this, "Enter an IP address");
			return false;
		}

		String porttemp = portTextField.getText();

		if (porttemp.equals("")) {
			JOptionPane.showMessageDialog(this, "Enter a port number");
			return false;
		}

		try {
			port = Integer.valueOf(porttemp);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Enter a port number");
			return false;
		}

		if ((port < 1) || (port > 65535)) {
			JOptionPane.showMessageDialog(this, "Invalid port");
			return false;
		}

		return true;

	}

	// a conex�o em si
	private void connect() {
		IPModel connection = new IPModel(ip, port, password);

//		try {
			//ConnectionHandler ch = new ConnectionHandler(connection.getIpAddress(), connection.getPort(),new GUISolutionModel(ConsolejTextArea));
			setVisible(false);
			//MainWindow mw = new MainWindow(ch, connection.getIpAddress(), connection.getPort());
//		} catch (UnknownHostException e) {
//
//			e.printStackTrace();
//		} catch (ConnectException e) {
//			JOptionPane.showMessageDialog(this, "Connection refused");
//			e.printStackTrace();
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(this, "Enter an IP address");
//			e.printStackTrace();
//		}

	}

}
