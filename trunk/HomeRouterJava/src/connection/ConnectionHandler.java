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
import javax.swing.JOptionPane;
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
        private int port;
        private String host;
	private GUISolutionModel GuiSol;

	public ConnectionHandler(String host, int port, GUISolutionModel GuiSol) throws ConnectException, SocketException, IOException {
		this.GuiSol = GuiSol;
                this.host=host;
                this.port=port;
		//Connect to the specified server
		this.telnet.connect(host, port);
		// Get input and output stream references
		this.in = telnet.getInputStream();
		this.out = new PrintStream(telnet.getOutputStream());
	}

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
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

        public ArrayList<String> arrayListReadUntil(ArrayList<String> pattern) {
		StringBuffer sb = null;

		try {
			ArrayList<String> Array = new ArrayList<String>();
			char lastChar[] = new char[pattern.size()];
			for (int i = 0; i < pattern.size(); i++) {
				lastChar[i] = pattern.get(i).charAt(pattern.get(i).length() - 1);
			}
			sb = new StringBuffer();

			boolean found = false;
			char ch = (char) in.read();
			//se der pau vc comenta isso
//			while((ch==13)||(ch==10)||(ch==7)){
//				ch = (char) in.read();
//			}
//			System.out.println("ascii "+Integer.valueOf(ch));
                        int j=0;
			System.out.println("Recebido:");
                        this.GuiSol.appendConsole("Recebido:");
			while (!found) {
                                System.out.println("R:"+(int) ch);
				System.out.print(ch);
				this.GuiSol.appendConsole(ch);
				sb.append(ch);
                                if(ch=='!'){
                                    System.out.println();
                                }
				for (int i = pattern.size() - 1; i >= 0; i--) {
					if (ch == lastChar[i]) {
						if (sb.toString().endsWith(pattern.get(i))) {
							Array.add(pattern.get(i));
							Array.add(sb.toString());
							System.out.println();
							return Array;
						}
					}
				}
                                //
                                if(65535==(int)ch){
                                    j++;
                                    if(j>=3){
                                        disconnect();
                                        return null;
                                    }
                                }
                                //
				ch = (char) in.read();
			}
		} catch (Exception e) {
                        GuiSol.showMessageDialog("This connection was closed by server!");
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
                        int j=0;
			System.out.println("Recebido:");
                        this.GuiSol.appendConsole("Recebido:");
			while (!found) {
                                System.out.println("R:"+(int) ch);
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
                                //
                                if(65535==(int)ch){
                                    j++;
                                    if(j>=3){
                                        disconnect();
                                        return null;
                                    }
                                }
                                //
				ch = (char) in.read();
			}
		} catch (Exception e) {
                        GuiSol.showMessageDialog("This connection was closed by server!");
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
                        GuiSol.killTab(host);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
