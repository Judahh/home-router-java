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
public class InformationHandler {// ---------------------------------------------------------------------------------------------------------------------

	private ClockModel clock;
	private RunModel run;
	private ConnectionHandler connection;
	private GUISolutionModel GuiSol;

	private String[] leveltypes;
	private String version;
	private ArrayList<String> staticRoutes;
	private ArrayList<String> dynamicRoutes;

	private int fastethernet; // numero de interfaces fastethernet no router
	private int serial; // numero de interfaces seriais no router

	public InformationHandler(String host, int port, GUISolutionModel GuiSol) throws ConnectException, SocketException, IOException {
		this.GuiSol = GuiSol;
		this.connection = new ConnectionHandler(host, port, GuiSol);
	}

	public ConnectionHandler getConnection() {
		return connection;
	}

	public GUISolutionModel getGUISol() {
		return GuiSol;
	}

	public void checkInfo(String FirstPartInfo) {
		String[] possibilities = new String[getEndInfoPossibilities().size()];
		for (int i = 0; i < getEndInfoPossibilities().size(); i++) {
			possibilities[i] = getEndInfoPossibilities().get(i);
		}

		if (FirstPartInfo.contains("More")) {
			connection.send(" ");
		}

		ArrayList<String> InfoS = connection.arrayListReadUntil(possibilities);

		if (InfoS.get(0).equals("--More--")) {
			connection.send(" ");
		}
	}

	public void parseShowRunInfo(String info) {
		String[] infoarray = info.split("\\r");
		staticRoutes = new ArrayList<String>();
		dynamicRoutes = new ArrayList<String>();
		for (int i = 0; i < infoarray.length; i++) {
			if (infoarray[i].contains("version")) {
				String[] tempversion = infoarray[i].split("version");
				version = tempversion[1].trim();
				GuiSol.setIos("IOS version " + version);
			}

			if (infoarray[i].contains("ip route")) {
				String[] temproute = infoarray[i].split(" ");
				staticRoutes.add(temproute[2] + " with mask " + temproute[3] + " via " + temproute[4]);

			}

			if (infoarray[i].contains("router rip")) {
				int j = i + 1;
				String cache = infoarray[j];
				while (cache.contains("network")) {
					String[] temproute2 = infoarray[j].split("network");
					dynamicRoutes.add(temproute2[1].trim());
					j++;
					cache = infoarray[j];
				}

			}

			if (infoarray[i].contains("interface FastEthernet")) {
				// int j = i+1;
				// while (!infoarray[j].contains("!")){
				// if (infoarray[j].contains("mac")){
				// String temparray[] = infoarray[j].split(" ");
				// GuiSol.setFastEthernetMac(fastethernet, temparray[1]);
				// } else if(infoarray[j].contains("ip address")){
				// String temparray[] = infoarray[j].split(" ");
				//
				// GuiSol.setFastEthernetIp(fastethernet, temparray[2]);
				// GuiSol.setFastEthernetMac(fastethernet, temparray[3]);
				// } else if(infoarray[j].contains("tx-ring")){
				// String temparray[] = infoarray[j].split(" ");
				// GuiSol.setFastEthernetTx(fastethernet, temparray[1].trim());
				//
				// } else if(infoarray[j].contains("speed")){
				// String temparray[] = infoarray[j].split(" ");
				// GuiSol.setFastEthernetBandwidth(fastethernet,
				// temparray[1].trim());
				// } else if (infoarray[j].contains("half-duplex")){
				// GuiSol.setFastEthernetDuplex(fastethernet,"half-duplex");
				// }
				// j++;
				// }
				fastethernet++;
			}

			if (infoarray[i].contains("interface Serial")) {
				serial++;
			}

			// System.out.println("Infoarray" + infoarray[i]);
		}

		// rotas estaticas
		for (String string : staticRoutes) {
			GuiSol.addStaticRoute(string);

		}

		GuiSol.addStaticModel();

		// rotas dinamicas
		for (String string : dynamicRoutes) {
			GuiSol.addDynamicRoute(string);

		}

		GuiSol.addDynamicModel();

		// adicionar fastethernet
		for (int i = 0; i < fastethernet; i++) {

			GuiSol.addFastEthernetInterface(String.valueOf(i) + "/" + String.valueOf(i));

		}

		// adicionar serial
		for (int i = 0; i < serial; i++) {
			GuiSol.addSerialInterface(String.valueOf(i));
		}

	}

	public void parseInterfaceStatusInfo(String info) {
		String[] infoarray = info.split("\\n");

		for (int i = infoarray.length-1; i >= 0; i--) {
			if (infoarray[i].contains("interface ")) {
				InterfaceModel intmod = new InterfaceModel();
				String[] temparray = infoarray[i].split(" ");
				IdentifierModel identmod = new IdentifierModel();
				if (temparray[1].contains("FastEthernet")) {
					identmod.setInterfaceCod(0);
					identmod.setPort(temparray[1].substring(temparray[1].lastIndexOf("t")+1));
				} else {
					identmod.setInterfaceCod(1);
					identmod.setPort(temparray[1].substring(temparray[1].lastIndexOf("l")+1));
				}

				intmod.setIdentifier(identmod);

				int j = i + 1;
				if (infoarray[j].contains("ip address")) {
					String[] ipmaskarray = infoarray[j].split(" ");
					if (infoarray[j].contains("no ")) {
						intmod.setIp(null);
						intmod.setMask(null);
					} else {
						intmod.setIp(ipmaskarray[3]);
						intmod.setMask(ipmaskarray[4]);
					}

				}

				intmod.setShutdown(false);
				while (!infoarray[j].contains("!")) {
					if (infoarray[j].contains("shutdown")) {
						intmod.setShutdown(true);

					}
					j++;
				}
				if (intmod.isShutdown()) {
					if ((intmod.getIp()==null)&&(intmod.getMask()==null)){
						GuiSol.addInterfaceStatus(intmod.getIdentifier().getInterface() + " " + intmod.getIdentifier().getPort()
								+ " is down without IP address");
					} else {
						GuiSol.addInterfaceStatus(intmod.getIdentifier().getInterface() + " " + intmod.getIdentifier().getPort()
								+ " is down with IP " + intmod.getIp() + " and mask " + intmod.getMask());
					}
					
				} else {
					if ((intmod.getIp()==null)&&(intmod.getMask()==null)){
						GuiSol.addInterfaceStatus(intmod.getIdentifier().getInterface() + " " + intmod.getIdentifier().getPort()
								+ " is up without IP address");
					} else {
						GuiSol.addInterfaceStatus(intmod.getIdentifier().getInterface() + " " + intmod.getIdentifier().getPort()
								+ " is up with ip " + intmod.getIp() + " and mask " + intmod.getMask());
					}
					
				}

			}

		}

		GuiSol.addStatusModel();

	}

	// toda vez q der pau adicionar uma entrada aqui com a ultima linha recebida
	private ArrayList<String> getEndInfoPossibilities() {
		ArrayList<String> possibilities = new ArrayList<>();
		possibilities.add("--More--");
		possibilities.add("Invalid input detected at '^' marker.");
		possibilities.add("end");
		possibilities.add(" transmitter CTS losts");
		possibilities.add("X25 protocol-specific configuration");
		possibilities.add(" Global XOT commands");
		possibilities.add(" Virtual Private Dialup Network");
		possibilities.add(" Configured from console by");
		possibilities.add("Unknown command or computer name, or unable to find computer address");

		return possibilities;
	}

	public ArrayList<String> getInfoPossibilities() {
		ArrayList<String> possibilities = new ArrayList<>();
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
