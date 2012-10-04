package model;

/**
 * 
 * @author JH
 */
public class IdentifierModel {
	private String port;
	private String subPort;
	private int interfaceCod;

	public IdentifierModel(int interfaceCod, String port) {

	}
	
	public IdentifierModel() {

	}

	public String getInterface() {//ver porta de voz
		if (interfaceCod <= 0) {
                    return "Ethernet";
                }
                switch(interfaceCod){
                    case 1:
                        return "FastEthernet";
                    case 2:
                        return "GigabitEthernet";
                    case 3:
                        return "TenGigabitEthernet";
                    case 4:
                        return "POS";
                    case 5:
                        return "ATM";
                }
		return "Serial";
	}

	public void setInterfaceCod(int interfaceCod) {
		if (interfaceCod <= 0) {
                    this.interfaceCod = 0;
		}if (interfaceCod > 6) {
                    this.interfaceCod = 6;
                }else{
                    this.interfaceCod = interfaceCod;
		}

	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {// formato int/int ou int/int/int
		this.port = port;
	}

	public String getSubPort() {
		return subPort;
	}

	public void setSubPort(String subPort) {
		this.subPort = subPort;
	}

}
