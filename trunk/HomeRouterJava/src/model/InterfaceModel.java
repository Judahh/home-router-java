package model;

import enums.IdentifierEnum;

public class InterfaceModel {
	private String ip;
	private int clockrate;
	private String port;
	private boolean shutdown;
	private IdentifierEnum ident;

	public InterfaceModel(String ip, int clockrate, String port, boolean shutdown, IdentifierEnum ident) {
		super();
		this.ip = ip;
		this.clockrate = clockrate;
		this.port = port;
		this.shutdown = shutdown;
		this.ident = ident;
	}

	public String getIp() {
		return ip;
	}

	public int getClockrate() {
		return clockrate;
	}

	public String getPort() {
		return port;
	}

	public boolean isShutdown() {
		return shutdown;
	}

	public IdentifierEnum getIdent() {
		return ident;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setClockrate(int clockrate) {
		this.clockrate = clockrate;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	public void setIdent(IdentifierEnum ident) {
		this.ident = ident;
	}

}
