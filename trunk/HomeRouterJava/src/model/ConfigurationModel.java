package model;

public class ConfigurationModel {
	private String senhaVTY;
	private String senhaEnable;
	private String routerName;
	private String os;

	public ConfigurationModel(String senhaVTY, String senhaEnable, String routerName, String os) {
		super();
		this.senhaVTY = senhaVTY;
		this.senhaEnable = senhaEnable;
		this.routerName = routerName;
		this.os = os;
	}

	public String getSenhaVTY() {
		return senhaVTY;
	}

	public String getSenhaEnable() {
		return senhaEnable;
	}

	public String getRouterName() {
		return routerName;
	}

	public String getOs() {
		return os;
	}

	public void setSenhaVTY(String senhaVTY) {
		this.senhaVTY = senhaVTY;
	}

	public void setSenhaEnable(String senhaEnable) {
		this.senhaEnable = senhaEnable;
	}

	public void setRouterName(String routerName) {
		this.routerName = routerName;
	}

	public void setOs(String os) {
		this.os = os;
	}

}
