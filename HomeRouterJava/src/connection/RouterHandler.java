/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import model.GUISolutionModel;
import model.MonthModel;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.net.telnet.TelnetClient;

/**
 * 
 * @author JH
 */
// Classe: RouterHandler
// Esta classe sera responsaver pelo manipulamento commandos a alto nivel
// Ela sera uma Thread para pode fazer varias instancias independentes e assim
// poder executar este cliente em varios routers distintos
public class RouterHandler {
	private LevelHandler routerLevel;

	public RouterHandler(String host, int port, GUISolutionModel GuiSol) throws ConnectException, SocketException, IOException {
		this.routerLevel = new LevelHandler(host, port, GuiSol);
	}

	// estes metodos serao usados para selecionar a interface e porta que sera
	// usada(antes de entrar no level dela (claro)
	private void setInterface(int interfaceCod) {
		this.routerLevel.getPrompt().getIdentifier().setInterfaceCod(interfaceCod);
	}

	private void setInterfacePort(String port) {
		this.routerLevel.getPrompt().getIdentifier().setPort(port);
	}

	private void setInterfaceSubPort(String subPort) {
		this.routerLevel.getPrompt().getIdentifier().setSubPort(subPort);
	}

	// funcao exclusiva para testes
	public void goToLevelRouter(int Level) {
		routerLevel.goToLevel(Level);
	}

	// Prototipo de funcao para enviar
	// comando//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// public void CMDX(){
	// routerLevel.goToLevel(level x);
	// routerLevel.sendCommand(x);
	// }

	public int getLevel() {
		return this.routerLevel.getLevel();
	}

	// usar isso como base, copicolar l[ogica

	public void setRouterName(String name) {
		this.goToLevelRouter(3);
		this.routerLevel.sendCommand("hostname " + name + "\r\n");
	}

	public String getRouterName() {
		return routerLevel.getRouterName();
	}

	public void setVLanConfig(String number, String name) {
		this.goToLevelRouter(2);
		this.routerLevel.sendCommand("vlan " + number + " name " + name + "\r\n");
	}

	// seta data e hora do router
	public void setDateTime(String date, String time) {
		this.goToLevelRouter(2);
		String[] dates = date.split("/");

		MonthModel m = new MonthModel();
		dates[1] = m.getValues()[Integer.valueOf(dates[1]) - 1];
		this.routerLevel.sendCommand("clock set " + time + " " + dates[1] + " " + dates[0] + " " + dates[2] + "\r\n");

	}

	public void setStaticRoute(String ip, String mask, String nexthop) {
		this.goToLevelRouter(3);
		this.routerLevel.sendCommand("ip route " + ip + " " + mask + " " + nexthop + "\r\n");

	}

	public void setDynamicRoute(String ip) {
		this.goToLevelRouter(3);
		this.routerLevel.sendCommand("router rip\r\n");
		this.routerLevel.sendCommand("network " + ip + "\r\n");
	}
	
	//método para pingar um IP
	public void ping(String ip){
		this.goToLevelRouter(2);
		this.routerLevel.sendCommand("ping "+ip+"\r\n");
		
	}
	
	//setar level usando combobox
	
	public void setLevel(int level){
		this.goToLevelRouter(level);
	}

	public void sendUserCommand(String command) {
		routerLevel.sendCommand(command);
	}

	public String getClock() {
		try {
			System.out.println("CLOCK!!!");
			routerLevel.goToLevel(1);
			routerLevel.sendCommand("show clock\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean Login() {
		routerLevel.checkLevel();
		if (routerLevel.getPrompt().getLevel() > 0) {
			return true;
		}
		return false;
	}

	public void disconnect() {
		this.routerLevel.getInfo().getConnection().disconnect();
	}
}
