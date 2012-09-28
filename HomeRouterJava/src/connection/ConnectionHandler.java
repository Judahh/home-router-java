/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import model.GUISolutionModel;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * 
 * @author JH
 */
public class ConnectionHandler {
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	GUISolutionModel GuiSol;

	public ConnectionHandler(String host, int port, GUISolutionModel GuiSol) throws ConnectException, SocketException, IOException {
		this.GuiSol = GuiSol;
		// Connect to the specified server
		telnet.connect(host, port);
		// Get input and output stream references
		in = telnet.getInputStream();
		out = new PrintStream(telnet.getOutputStream());

	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

	public TelnetClient getTelnet() {
		return telnet;
	}

	public void setTelnet(TelnetClient telnet) {
		this.telnet = telnet;
	}

	private String readUntil(String pattern) {
		StringBuffer sb = null;
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			sb = new StringBuffer();

			boolean found = false;
			char ch = (char) in.read();

			while (!found) {
				System.out.print(ch);
                                this.GuiSol.appendConsole(ch);
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			sb.setLength(0);
			e.printStackTrace();
		}
		return null;
	}

	private String readUntil(String[] pattern) {

		StringBuffer sb = null;

		try {
			char lastChar[] = new char[pattern.length];
			for (int i = 0; i < pattern.length; i++) {
				lastChar[i] = pattern[i].charAt(pattern[i].length() - 1);
			}
			sb = new StringBuffer();

			boolean found = false;
			char ch = (char) in.read();

			while (!found) {
				System.out.print(ch);
				this.GuiSol.appendConsole(ch);
				sb.append(ch);
				for (int i = pattern.length - 1; i >= 0; i--) {
					if (ch == lastChar[i]) {
						if (sb.toString().endsWith(pattern[i])) {
							return pattern[i];
						}
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			sb.setLength(0);
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> arrayListReadUntil(String[] pattern) {

		StringBuffer sb = null;

		try {
			ArrayList<String> Array = new ArrayList<String>();
			char lastChar[] = new char[pattern.length];
			for (int i = 0; i < pattern.length; i++) {
				lastChar[i] = pattern[i].charAt(pattern[i].length() - 1);
			}
			sb = new StringBuffer();

			boolean found = false;
			char ch = (char) in.read();
			//se der pau vc comenta isso
//			while((ch==13)||(ch==10)||(ch==7)){
//				ch = (char) in.read();
//			}
//			System.out.println("ascii "+Integer.valueOf(ch));
			System.out.println("Recebido:");
                        this.GuiSol.appendConsole("Recebido:");
			while (!found) {
				System.out.print(ch);
				this.GuiSol.appendConsole(ch);
				sb.append(ch);
                                if(ch=='!'){
                                    System.out.println();
                                }
				for (int i = pattern.length - 1; i >= 0; i--) {
					if (ch == lastChar[i]) {
						if (sb.toString().endsWith(pattern[i])) {
							Array.add(pattern[i]);
							Array.add(sb.toString());
							System.out.println();
							return Array;
						}
					}
				}
				ch = (char) in.read();
				
			}
		} catch (Exception e) {
			sb.setLength(0);
			e.printStackTrace();
		}
		return null;
	}

	private void write(String value) {
		try {
                    out.print(value);
                    out.flush();
                    out.flush();
		} catch (Exception e) {
                    out.flush();
                    e.printStackTrace();
		}
	}

	public void send(String value) {
		
		write(value);
		System.out.println("Enviado:" + value);
                this.GuiSol.appendConsole("Enviado:" + value);
		System.out.println();
	}

	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
