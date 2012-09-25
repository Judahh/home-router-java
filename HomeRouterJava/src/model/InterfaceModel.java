package model;

import enums.IdentifierEnum;

public class InterfaceModel {
	private String ip;
	private String mask;
	private int clockrate;
	private IdentifierModel identifier;
	private boolean shutdown;
	public String getIp() {
		return ip;
	}
	public String getMask() {
		return mask;
	}
	public int getClockrate() {
		return clockrate;
	}
	public IdentifierModel getIdentifier() {
		return identifier;
	}
	public boolean isShutdown() {
		return shutdown;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public void setClockrate(int clockrate) {
		this.clockrate = clockrate;
	}
	public void setIdentifier(IdentifierModel identifier) {
		this.identifier = identifier;
	}
	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	

}
