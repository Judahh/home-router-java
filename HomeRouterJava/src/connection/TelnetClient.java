package connection;

import model.IPModel;

public class TelnetClient {	
	
	private IPModel ipmodel;

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
	
	public boolean connect(){
		return false;
	}

}
