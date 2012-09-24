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

		ArrayList<String> InfoS = connection.arrayListReadUntil(possibilities);
//
//		String FullInfo = FirstPartInfo + InfoS;
//		if (FirstPartInfo == "%") {
//			JOptionPane.showInputDialog(InfoS);
//		}
//		if (FirstPartInfo == "!") {
//			if (InfoS.get(1).contains("interface") || InfoS.get(1).contains("Interface")) {
//				if (InfoS.get(1).contains("FastEthernet")) {
//					int i = InfoS.get(1).indexOf("FastEthernet");
//					for (int j = i; j < InfoS.get(1).length(); j++) {
//						System.out.println(InfoS.get(1).charAt(j));
//
//					}
//				}
//			}
//		}

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
//				int j = i+1;				
//				while (!infoarray[j].contains("!")){
//					if (infoarray[j].contains("mac")){
//						String temparray[] = infoarray[j].split(" ");
//						GuiSol.setFastEthernetMac(fastethernet, temparray[1]);
//					} else if(infoarray[j].contains("ip address")){
//						String temparray[] = infoarray[j].split("ip address");
//						temparray[1].trim();
//						
//						GuiSol.setFastEthernetIp(fastethernet, temparray[1].substring(0, infoarray[j].indexOf(" ")));
//						GuiSol.setFastEthernetMac(fastethernet, temparray[1].substring(infoarray[j].indexOf(" ")));
//					} else if(infoarray[j].contains("tx-ring")){
//						String temparray[] = infoarray[j].split(" ");
//						GuiSol.setFastEthernetTx(fastethernet, temparray[1].trim());
//						
//					} else if(infoarray[j].contains("speed")){
//						String temparray[] = infoarray[j].split(" ");
//						GuiSol.setFastEthernetBandwidth(fastethernet, temparray[1].trim());
//					} else if (infoarray[j].contains("half-duplex")){
//						GuiSol.setFastEthernetDuplex(fastethernet,"half-duplex");
//					} 
//					j++;
//				}
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

			GuiSol.addFastEthernetInterface(String.valueOf(i));

		}

		// adicionar serial
		for (int i = 0; i < serial; i++) {
			GuiSol.addSerialInterface(String.valueOf(i));
		}

	}

	private ArrayList<String> getEndInfoPossibilities() {
		ArrayList<String> possibilities = new ArrayList<>();
		possibilities.add("--More--");
		possibilities.add("Invalid input detected at '^' marker.");
		possibilities.add("end");
		possibilities.add("Unknown command or computer name, or unable to find computer address");

		return possibilities;
	}

	public ArrayList<String> getInfoPossibilities() {
		ArrayList<String> possibilities = new ArrayList<>();
		possibilities.add("!");
		possibilities.add("*");
		possibilities.add("<");
		possibilities.add("domain server (");
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
