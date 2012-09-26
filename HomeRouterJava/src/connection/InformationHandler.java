/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import view.FastEthernet;
import view.Serial;
import model.ClockModel;
import model.GUISolutionModel;
import model.IdentifierModel;
import model.InterfaceModel;
import model.RunModel;
import model.TimeModel;

/**
 * 
 * @author JH
 */
// Classe: InformationHandler
// Esta classe sera responsaver por reunir e organizar informacoes recebidas
// pelo router
// Exemplo: quando o metodo checkLevel do LevelHandler encontrar um !,*,% ele
// chamara esta classe para cuidar disso
// e logo apos ele se chamara novamente
//
// Comandos que iniciam (conhecidos):
// -! (como por exemplo o show run)
// -* (como por exemplo o clock)
// -< (como por exemplo o ?)
// -Month (como Jun) (como por exemplo no clock)
// -% (como por exemplo em erros)
// ---More-- (como por exemplo no show run)(enviar:" ")(quando o metodo receber
// isso ele ira enviar um espaco e chamara a si mesmo)
public class InformationHandler{// ---------------------------------------------------------------------------------------------------------------------

	private ClockModel clock;
	private RunModel run;
	private ConnectionHandler connection;
	private GUISolutionModel GuiSol;
	private String version;

	public InformationHandler(String host, int port, GUISolutionModel GuiSol)
			throws ConnectException, SocketException, IOException{
		this.GuiSol=GuiSol;
		this.connection=new ConnectionHandler(host, port, GuiSol);
	}

	public ConnectionHandler getConnection(){
		return connection;
	}

	public GUISolutionModel getGUISol(){
		return GuiSol;
	}

	public void checkInfo(String FirstPartInfo){
		String[] possibilities=new String[getEndInfoPossibilities().size()];
		for(int i=0; i < getEndInfoPossibilities().size(); i++){
			possibilities[i]=getEndInfoPossibilities().get(i);
		}

		if(FirstPartInfo.contains("More")){
			connection.send(" ");
		}
		
		
		ArrayList<String> InfoS=connection.arrayListReadUntil(possibilities);
		
		String fullInfo = FirstPartInfo + InfoS;
		
		parseShowRunInfo(fullInfo);
		//parseInterfaceStatusInfo(fullInfo);

		if(InfoS.get(0).equals("--More--")){
			connection.send(" ");
		}
	}

	// verifica se a interface serial � master ou slave
	public void parseShowControllersInfo(String info){
		// impede que se adicionem interfaces repetidas
		ArrayList<String> interfaces=new ArrayList<String>();
		String[] infoarray=info.split("\\n");
		for(int i=0; i < infoarray.length; i++){
			if(infoarray[i].contains("Serial")){
				if(!interfaces.contains(infoarray[i])
						&& (!infoarray[i].contains("More"))){
					if(infoarray[i + 2].contains("DTE")){

						interfaces.add(infoarray[i] + " is master");
						GuiSol.setSerialType(infoarray[i] + " is master");

					}else{
						interfaces.add(infoarray[i] + " is slave");
						GuiSol.setSerialType(infoarray[i] + " is slave");

					}
				}
			}
		}
		GuiSol.addSerialStatusModel();

	}

	public void parseShowRunInfo(String info){
		String[] infoarray=info.split("\\r");
		// impede que se adicionem interfaces repetidas
		ArrayList<String> interfaces=new ArrayList<String>();
		for(int i=0; i < infoarray.length; i++){
			if(infoarray[i].contains("version")){
				String[] tempversion=infoarray[i].split("version");
				version=tempversion[1].trim();
				GuiSol.setIos("IOS version " + version);
			}

			if(infoarray[i].contains("ip route")){
				String[] temproute=infoarray[i].split(" ");
				GuiSol.addStaticRoute(temproute[2] + " with mask "
						+ temproute[3] + " via " + temproute[4]);

			}

			if(infoarray[i].contains("router rip")){
				int j=i + 1;
				String cache=infoarray[j];
				while(cache.contains("network")){
					String[] temproute2=infoarray[j].split("network");
					GuiSol.addDynamicRoute(temproute2[1].trim());

					j++;
					cache=infoarray[j];
				}

			}

			if(infoarray[i].contains("interface FastEthernet")){

				String[] temparray=infoarray[i].split(" ");
				if(!interfaces.contains(temparray[1])){

					GuiSol.addFastEthernetInterface(temparray[1]
							.substring(temparray[1].lastIndexOf("t") + 1));
				}

			}

			if(infoarray[i].contains("interface Serial")){
				String[] temparray=infoarray[i].split(" ");
				if(!interfaces.contains(temparray[1])){
					interfaces.add(temparray[1]);
					GuiSol.addSerialInterface(temparray[1]
							.substring(temparray[1].lastIndexOf("l") + 1));
				}

			}

		}

		GuiSol.addStaticModel();
		GuiSol.addDynamicModel();

	}

	// status das interfaces

	public void parseInterfaceStatusInfo(String info){
		String[] infoarray=info.split("\\n");

		// impede que se adicionem interfaces repetidas
		ArrayList<String> interfaces=new ArrayList<String>();

		for(int i=infoarray.length - 1; i >= 0; i--){
			if(infoarray[i].contains("interface ")){
				InterfaceModel intmod=new InterfaceModel();
				String[] temparray=infoarray[i].split(" ");
				IdentifierModel identmod=new IdentifierModel();
				if(temparray[1].contains("FastEthernet")){
					identmod.setInterfaceCod(0);
					identmod.setPort(temparray[1].substring(temparray[1]
							.lastIndexOf("t") + 1));

				}else{
					identmod.setInterfaceCod(1);
					identmod.setPort(temparray[1].substring(temparray[1]
							.lastIndexOf("l") + 1));
				}
				if(interfaces.contains(identmod.getInterface()
						+ identmod.getPort())){
					break;
				}else{
					interfaces
							.add(identmod.getInterface() + identmod.getPort());

					intmod.setIdentifier(identmod);

					int j=i + 1;
					if(infoarray[j].contains("ip address")){
						String[] ipmaskarray=infoarray[j].split(" ");
						if(infoarray[j].contains("no ")){
							intmod.setIp(null);
							intmod.setMask(null);
						}else{
							intmod.setIp(ipmaskarray[3]);
							intmod.setMask(ipmaskarray[4]);
						}

					}

					intmod.setShutdown(false);
					while(!infoarray[j].contains("!")){
						if(infoarray[j].contains("shutdown")){
							intmod.setShutdown(true);

						}
						j++;
					}

					String state;
					if(intmod.isShutdown()){
						state=new String("down");
					}else{
						state=new String("up");
					}

					if((intmod.getIp() == null) && (intmod.getMask() == null)){
						GuiSol.addInterfaceStatus(intmod.getIdentifier()
								.getInterface()
								+ " "
								+ intmod.getIdentifier().getPort()
								+ " is "
								+ state + " without IP address");
					}else{
						GuiSol.addInterfaceStatus(intmod.getIdentifier()
								.getInterface()
								+ " "
								+ intmod.getIdentifier().getPort()
								+ " is "
								+ state
								+ " with IP "
								+ intmod.getIp()
								+ " and mask " + intmod.getMask());
					}

				}
			}

		}

		GuiSol.addStatusModel();

	}
	
	//tratar clock

	public void parseClockInfo(String info){
		String[] infoarray=info.split("\\n");

		for(int i=0; i < infoarray.length; i++){
			if((infoarray[i].contains(":")) && (infoarray[i].contains("."))){
				String[] temparray=infoarray[i].split(" ");
				GuiSol.setGUIClock(temparray[4] + " " + temparray[3] + " "
						+ temparray[5] + " " + temparray[0].substring(0, 8));
				break;

			}

		}
	}

	// toda vez q der pau adicionar uma entrada aqui com a ultima linha recebida
	private ArrayList<String> getEndInfoPossibilities(){
		ArrayList<String> possibilities=new ArrayList<>();
		possibilities.add("--More--");
		possibilities.add("Invalid input detected at '^' marker.");
		possibilities.add("end");
		possibilities.add(" transmitter CTS losts");
		possibilities.add("X25 protocol-specific configuration");
		possibilities.add(" Global XOT commands");
		possibilities.add(" Virtual Private Dialup Network");
		possibilities.add(" Configured from console by");
		possibilities
				.add("Unrecognized host or address, or protocol not running");
		possibilities.add(" percent (");
		//possibilities.add(" 1993");
		

		possibilities
				.add("Unknown command or computer name, or unable to find computer address");

		return possibilities;
	}

	public ArrayList<String> getInfoPossibilities(){
		ArrayList<String> possibilities=new ArrayList<>();
		possibilities.add("!");
		possibilities.add("*");
		possibilities.add("<");
		possibilities.add("domain server (");
		possibilities.add("Interface FastEthernet");
		possibilities.add("RX_RING_ENTRIES");
		possibilities.add("status ");
		possibilities.add("Register ");
		possibilities.add("User-defined Address ");
		possibilities.add(" --More--");
		possibilities.add("end");
		possibilities.add("%");
		possibilities.add("Jan");
		possibilities.add("Feb");
		possibilities.add("Mar");
		possibilities.add("Apr");
		possibilities.add("May");
		possibilities.add("Jun");
		possibilities.add("Jul");
		possibilities.add("Aug");
		possibilities.add("Sep");
		possibilities.add("Oct");
		possibilities.add("Nov");
		possibilities.add("Dec");

		return possibilities;
	}
}
