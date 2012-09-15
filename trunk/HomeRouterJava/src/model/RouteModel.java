package model;

public class RouteModel {
	private String ip;
	private String mask;
	private String network;

	public RouteModel(String ip, String mask, String network) {
		super();
		this.ip = ip;
		this.mask = mask;
		this.network = network;
	}

	public String getIp() {
		return ip;
	}

	public String getMask() {
		return mask;
	}

	public String getNetwork() {
		return network;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

}
