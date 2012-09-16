package connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import model.IPModel;

public class TelnetClient {

	private IPModel ipmodel;
	private Socket socket;
	private BufferedWriter writer;
	private BufferedReader reader;

	public TelnetClient(IPModel ipmodel) {
		super();
		this.ipmodel = ipmodel;
	}

	public IPModel getIpmodel() {
		return ipmodel;
	}

	public void setIpmodel(IPModel ipmodel) {
		this.ipmodel = ipmodel;
	}

	public void connect() throws IOException, UnknownHostException {

		socket = new Socket(ipmodel.getIpAddress(), ipmodel.getPort());
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		sendMessage("\r\n");	
		

	}

	public void sendMessage(String message) throws IOException {
		writer.write(message + "\r\n");
		writer.flush();

	}

	public String readMessage() throws IOException {
		String answer = reader.readLine();
		return answer;
	}

	public void disconnect() {

	}

}
