package model;

public class IdentifierModel{
	private String port;
	private String subPort;
	private int interfaceCod;
	
	public IdentifierModel(int interfaceCod,String port){
		
	}
	
	public String getInterface(){
		if(interfaceCod==0){
			return "FastEthernet";
		}else{
			return "Serial";
		}
		
	}
	public void setInterfaceCod(int interfaceCod){
		if(interfaceCod<=0){
			this.interfaceCod = 0;
		}else{
			this.interfaceCod = 1;
		}
		
	}
	
	public String getPort(){
		return port;
	}
	
	public void setPort(String port){//formato int/int ou int/int/int
		this.port = port;
	}

	public String getSubPort(){
		return subPort;
	}

	public void setSubPort(String subPort){
		this.subPort = subPort;
	}
	
}
