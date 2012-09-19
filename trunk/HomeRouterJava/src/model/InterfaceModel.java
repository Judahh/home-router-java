package model;

import enums.IdentifierEnum;

public class InterfaceModel {
	private String ip;
	private int clockrate;
	private IdentifierModel identifier;
	private boolean shutdown;

	public InterfaceModel(String ip, IdentifierModel identifier, int clockrate, boolean shutdown) {
		super();
		this.ip = ip;
		this.clockrate = clockrate;
		this.shutdown = shutdown;
		this.identifier = identifier;
	}

	public String getIp() {
		return ip;
	}

	public int getClockrate() {
		return clockrate;
	}

	public String getPort() {
		return identifier.getPort();
	}

	public boolean isShutdown() {
		return shutdown;
	}

	public IdentifierModel getIdentifier() {
		return identifier;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setClockrate(int clockrate) {
		this.clockrate = clockrate;
	}

	public void setPort(String port) {
		this.identifier.setPort(port);
	}

	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	public void setIdent(IdentifierModel identifier) {
		this.identifier = identifier;
	}

}
