package model;

public class IPModel {
	private String ipAddress;
	private String password;
	private int port;

	public IPModel(String ipAddress, int port, String password) {
		super();
		this.ipAddress = ipAddress;
		this.password = password;
		this.port = port;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getPassword() {
		return password;
	}

	public int getPort() {
		return port;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
