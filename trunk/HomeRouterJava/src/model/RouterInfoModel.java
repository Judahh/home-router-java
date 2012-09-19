package model;

public class RouterInfoModel{
	private String routerName;
	private String os;

	public RouterInfoModel() {
	}
	
	public RouterInfoModel(String routerName, String os) {
		this.routerName = routerName;
		this.os = os;
	}
	
	public String getRouterName() {
		return routerName;
	}

	public String getOs() {
		return os;
	}
	
	public void setRouterName(String routerName) {
		this.routerName = routerName;
	}

	public void setOs(String os) {
		this.os = os;
	}
}
