/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import connection.CommandHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
public class InformationHandler {// ---------------------------------------------------------------------------------------------------------------------

    private ClockModel clock;
    private RunModel run;
    private ConnectionHandler connection;
    private String version;
    private ArrayList<String> interfaceTabs;
    private ArrayList<String> staticRoutes;
    private ArrayList<String> dynamicRoutes;
    private ArrayList<String> endInfoPossibilities;
    private ArrayList<String> infoPossibilities;

    public InformationHandler(ConnectionHandler connection)
            throws ConnectException, SocketException, IOException {
        this.connection = connection;
        this.interfaceTabs = new ArrayList<String>();
        this.staticRoutes = new ArrayList<String>();
        this.dynamicRoutes = new ArrayList<String>();
        infoPossibilitiesMaker();
        endInfoPossibilitiesMaker();
    }

    public ConnectionHandler getConnection() {
        return connection;
    }

    public GUISolutionModel getGUISol() {
        return this.connection.getGuiSol();
    }
    
    public String checkInfo(String FirstPartInfo) {//retornar prompt do router
        System.out.println("IS INFO?");
        if (isConnected()) {
            if (FirstPartInfo.contains("More")) {
                connection.send(" ");
            } else {
                for (int index = 1; index < getEndInfoPossibilities().size(); index++) {
                    if (FirstPartInfo.contains(getEndInfoPossibilities().get(index))) {
                        connection.send("\r\n");
                    }
                }
            }

            ArrayList<String> InfoS = connection.arrayListReadUntil(getEndInfoPossibilities());
            String fullInfo = FirstPartInfo + InfoS.get(1);

            if (InfoS.get(0).contains("More")) {
                return checkInfo(true, fullInfo.split("--More--")[0]);
            }

            if (InfoS.get(0).contains(getEndInfoPossibilities().get(0))) {
                return checkInfo(false, getEndInfoPossibilities().get(0));
            }

            String lastInfo = null;
            String routerName = null;
            for (int index = 1; index < getEndInfoPossibilities().size(); index++) {
                if (InfoS.get(0).contains(getEndInfoPossibilities().get(index))) {
                    routerName = setRouterName(InfoS.get(1), InfoS.get(0), index);
                    lastInfo = getEndInfoPossibilities().get(index);
                    index = getEndInfoPossibilities().size();//saida(depois trocar)-----------------------------------------------------------------
                }
            }

            fullInfo = fullInfo.split(routerName + lastInfo)[0];
            parseClockInfo(fullInfo);
            parseShowRunInfo(fullInfo);
            parseInterfaceStatusInfo(fullInfo);
            parseShowControllersInfo(InfoS.get(1));

            if (!showInfo(fullInfo)) {
                if (showError(fullInfo)) {
                    //ouve erro!
                }
            }

            System.out.println("---------------------------------");
            System.out.println("FullInfo:");
            System.out.println(fullInfo);
            System.out.println("---------------------------------");

            return lastInfo;
        }
        return null;
    }

    public String checkInfo(boolean more, String FirstPartInfo) {//retornar prompt do router
        System.out.println("IS INFO?");
        if (isConnected()) {
            if (more) {
                connection.send(" ");
            } else {
                for (int index = 1; index < getEndInfoPossibilities().size(); index++) {
                    if (FirstPartInfo.contains(getEndInfoPossibilities().get(index))) {
                        connection.send("\r\n");
                    }
                }
            }

            ArrayList<String> InfoS = connection.arrayListReadUntil(getEndInfoPossibilities());
            String fullInfo = FirstPartInfo + InfoS.get(1);

            if (InfoS.get(0).contains("More")) {
                return checkInfo(true, fullInfo.split("--More--")[0]);
            }

            if (InfoS.get(0).contains(getEndInfoPossibilities().get(0))) {
                return checkInfo(false, getEndInfoPossibilities().get(0));
            }

            String lastInfo = null;
            String routerName = null;
            for (int index = 1; index < getEndInfoPossibilities().size(); index++) {
                if (InfoS.get(0).contains(getEndInfoPossibilities().get(index))) {
                    routerName = setRouterName(InfoS.get(1), InfoS.get(0), index);
                    lastInfo = getEndInfoPossibilities().get(index);
                    index = getEndInfoPossibilities().size();//saida(depois trocar)-----------------------------------------------------------------
                }
            }

            fullInfo = fullInfo.split(routerName + lastInfo)[0];
            parseClockInfo(fullInfo);
            parseShowRunInfo(fullInfo);
            parseInterfaceStatusInfo(fullInfo);
            parseShowControllersInfo(InfoS.get(1));

            if (!showInfo(fullInfo)) {
                if (showError(fullInfo)) {
                    //ouve erro!
                }
            }

            System.out.println("---------------------------------");
            System.out.println("FullInfo:");
            System.out.println(fullInfo);
            System.out.println("---------------------------------");

            return lastInfo;
        }
        return null;
    }

    public boolean isConnected() {
        if (this.connection.isConnected()) {
            return true;
        }
        return false;
    }

    public String setRouterName(String routerName, String end, int index) {
        if (isConnected()) {
            CommandHandler CMDHandler = new CommandHandler(connection);
            if ((index > 0) && (index < CMDHandler.getArrayPromptValues().size() - 3)) {
                for (index = routerName.length() - end.length(); routerName.charAt(index) != '\n'; index--) {
                }
                String RouterName = routerName.substring(index + 1, routerName.length() - end.length());
                this.connection.getGuiSol().setGUIRouterName(RouterName);
                return RouterName;
            }
        }
        return null;
    }

    private boolean showError(String fullInfo) {
        if (fullInfo.contains("%")) {
            String error = fullInfo.substring(fullInfo.indexOf('%') + 1);
            while((error.charAt(error.length()-1)=='\n')||(error.charAt(error.length()-1)=='\r')||(error.charAt(error.length()-1)=='.')){
                error=error.substring(0, error.length()-1);
            }
            this.connection.getGuiSol().showErrorDialog(error + "!");
            return true;
        }
        return false;
    }

    private boolean showInfo(String fullInfo) {
        if (fullInfo.contains("!")) {
            String info = fullInfo.substring(fullInfo.indexOf('!') + 1);
            while((info.charAt(info.length()-1)=='\n')||(info.charAt(info.length()-1)=='\r')||(info.charAt(info.length()-1)=='.')){
                info=info.substring(0, info.length()-1);
            }
            if (!(info.contains("!") || info.contains("end"))) {
                this.connection.getGuiSol().showMessageDialog(info + "!");
                return true;
            }
        }
        return false;
    }

    // verifica se a interface serial ÃƒÂ¯Ã‚Â¿Ã‚Â½ master ou slave
    public void parseShowControllersInfo(String info) {//refazer que tah uma merda
        // impede que se adicionem interfaces repetidas
        ArrayList<String> interfaces = new ArrayList<String>();
        String[] infoarray = info.split("\\r");
        for (int i = 0; i < infoarray.length; i++) {
            if (infoarray[i].contains(" Serial")) {
                if (!infoarray[i].contains("More")) {
                    if ((i + 2) < infoarray.length - 1) {
                        if ((infoarray[i + 2].contains("DTE"))) {
                            if (!interfaces.contains(infoarray[i].trim() + " is master")) {
                                interfaces.add(infoarray[i].trim() + " is master");
                                this.connection.getGuiSol().setSerialType(infoarray[i].trim()
                                        + " is master");
                            }
                        } else {
                            if (!interfaces.contains(infoarray[i].trim() + " is slave")) {
                                interfaces.add(infoarray[i].trim() + " is slave");
                                this.connection.getGuiSol().setSerialType(infoarray[i].trim() + " is slave");
                            }
                        }
                    }
                }
            }
        }
        this.connection.getGuiSol().addSerialStatusModel();
    }

    public void parseShowRunInfo(String info) {//refazer que tah uma merda
        String[] infoarray = info.split("\\r");
        // impede que se adicionem interfaces repetidas

        boolean newroutes = false;
        for (int i = 0; i < infoarray.length; i++) {
            if (infoarray[i].contains("version")) {
                String[] tempversion = infoarray[i].split("version");
                version = tempversion[1].trim();
                this.connection.getGuiSol().setIos("IOS version " + version);
            }

            if (infoarray[i].contains("ip route")) {
                String[] temproute = infoarray[i].split(" ");
                String route = temproute[2] + " with mask " + temproute[3]
                        + " via " + temproute[4];
                if (!staticRoutes.contains(route)) {
                    staticRoutes.add(route);
                    this.connection.getGuiSol().addStaticRoute(temproute[2] + " with mask "
                            + temproute[3] + " via " + temproute[4]);
                    newroutes = true;
                }

            }

            if (infoarray[i].contains("router rip")) {
                int j = i + 1;
                String cache = infoarray[j];
                while (cache.contains("network")) {
                    String[] temproute2 = infoarray[j].split("network");
                    if (!dynamicRoutes.contains(temproute2[1].trim())) {
                        dynamicRoutes.add(temproute2[1].trim());
                        this.connection.getGuiSol().addDynamicRoute(temproute2[1].trim());
                        newroutes = true;
                    }

                    j++;
                    cache = infoarray[j];
                }

            }

            if (infoarray[i].contains("interface FastEthernet")) {

                String[] temparray = infoarray[i].split(" ");
                if (!interfaceTabs.contains(infoarray[i])) {
                    interfaceTabs.add(infoarray[i]);
                    this.connection.getGuiSol().addFastEthernetInterface(temparray[1].substring(temparray[1].lastIndexOf("t") + 1));

                }

            }

            if (infoarray[i].contains("interface Serial")) {
                String[] temparray = infoarray[i].split(" ");
                if (!interfaceTabs.contains(infoarray[i])) {
                    interfaceTabs.add(infoarray[i]);
                    this.connection.getGuiSol().addSerialInterface(temparray[1].substring(temparray[1].lastIndexOf("l") + 1));

                }

            }

        }
        if (newroutes) {
            this.connection.getGuiSol().addStaticModel();
            this.connection.getGuiSol().addDynamicModel();
        }

    }

    // status das interfaces
//    public void parseInterfaceStatusInfo(String info) {//refazer que tah uma merda (usando show ip Interface Brief)
//        String[] infoarray = info.split("\\r");
//
//        // impede que se adicionem interfaces repetidas
//        ArrayList<String> interfaces = new ArrayList<String>();
//
//        for (int i = 0; i < infoarray.length; i++) {
//            if (infoarray[i].contains("interface ")) {
//                InterfaceModel intmod = new InterfaceModel();
//                String[] temparray = infoarray[i].split(" ");
//                IdentifierModel identmod = new IdentifierModel();
//                if (temparray[1].contains("FastEthernet")) {
//                    identmod.setInterfaceCod(0);
//                    identmod.setPort(temparray[1].substring(temparray[1].lastIndexOf("t") + 1));
//
//                } else {
//                    identmod.setInterfaceCod(1);
//                    identmod.setPort(temparray[1].substring(temparray[1].lastIndexOf("l") + 1));
//                }
//                if (interfaces.contains(identmod.getInterface()
//                        + identmod.getPort())) {
//                    break;
//                } else {
//                    interfaces.add(identmod.getInterface() + identmod.getPort());
//
//                    intmod.setIdentifier(identmod);
//
//                    int j = i + 1;
//                    if (infoarray[j].contains("ip address")) {
//                        String[] ipmaskarray = infoarray[j].split(" ");
//                        if (infoarray[j].contains("no ")) {
//                            intmod.setIp(null);
//                            intmod.setMask(null);
//                        } else {
//                            intmod.setIp(ipmaskarray[3]);
//                            intmod.setMask(ipmaskarray[4]);
//                        }
//
//                    }
//
//                    intmod.setShutdown(false);
//                    while (!infoarray[j].contains("!")) {
//                        if (infoarray[j].contains("More")) {
//                            j++;
//                            break;
//                        }
//                        if (infoarray[j].contains("shutdown")) {
//                            intmod.setShutdown(true);
//
//                        }
//                        j++;
//                    }
//
//                    String state;
//                    if (intmod.isShutdown()) {
//                        state = new String("down");
//                    } else {
//                        state = new String("up");
//                    }
//
//                    if ((intmod.getIp() == null) && (intmod.getMask() == null)) {
//                        this.connection.getGuiSol().addInterfaceStatus(intmod.getIdentifier().getInterface()
//                                + " "
//                                + intmod.getIdentifier().getPort()
//                                + " is "
//                                + state + " without IP address");
//                    } else {
//                        this.connection.getGuiSol().addInterfaceStatus(intmod.getIdentifier().getInterface()
//                                + " "
//                                + intmod.getIdentifier().getPort()
//                                + " is "
//                                + state
//                                + " with IP "
//                                + intmod.getIp()
//                                + " and mask " + intmod.getMask());
//                    }
//
//                }
//            }
//
//        }
//
//    }
    
    public void parseInterfaceStatusInfo(String info) {//usa show ip Interface Brief
        String[] infoarray = info.split("\r\n");
        ArrayList<String>[] infoMatrix = new ArrayList[infoarray.length];
        for (int i = 0; i < infoarray.length; i++) {

            infoMatrix[i] = new ArrayList<>();
            String pattern = "\\s+";
            Pattern splitter = Pattern.compile(pattern);
            String[] array = splitter.split(infoarray[i]);
            if (i == 0) {
                if ((!"Interface".equals(array[0])) || (!"IP-Address".equals(array[1])) || (!"OK?".equals(array[2])) || (!"Method".equals(array[3])) || (!"Status".equals(array[4])) || (!"Protocol".equals(array[5]))) {
                    for (int j = 0; j < array.length; j++) {
                        System.out.println("D:" + array[j] + "---");
                    }
                    return;
                }
            }
            for (int j = 0; j < array.length; j++) {
                if (infoMatrix[i].size() > 0) {
                    if ("administratively".equals(infoMatrix[i].get(infoMatrix[i].size() - 1))) {
                        infoMatrix[i].set(infoMatrix[i].size() - 1, infoMatrix[i].get(infoMatrix[i].size() - 1) + " " + array[j]);
                    } else {
                        infoMatrix[i].add(array[j]);
                    }
                } else {
                    infoMatrix[i].add(array[j]);
                }
                System.out.println("ArrayChild[" + (infoMatrix[i].size() - 1) + "]:" + infoMatrix[i].get(infoMatrix[i].size() - 1));

                if ((infoMatrix[i].size() == 6)) {
                    if (i > 0) {
                        this.connection.getGuiSol().addStatusModel(infoMatrix[i]);
                    }
                }
            }
        }

    }

    // tratar clock
    public void parseClockInfo(String info) {//refazer que tah uma merda
        String[] infoarray = info.split("\\r");
        for (int i = 0; i < infoarray.length; i++) {
            if ((infoarray[i].contains(" UTC "))
                    && (!infoarray[i].contains("configuration"))) {
                String[] temparray = infoarray[i].split(" ");

                this.connection.getGuiSol().setGUIClock(temparray[4] + " " + temparray[3] + " "
                        + temparray[5] + " " + temparray[0]);
                break;

            }
        }

    }

    private void endInfoPossibilitiesMaker() {
        ArrayList<String> possibilities = new ArrayList<>();
        possibilities.add("--More--");
        CommandHandler CMDHandler = new CommandHandler(connection);
        possibilities.addAll(CMDHandler.getArrayPromptValues());
//        for(int i = 1; i < getInfoPossibilities().size(); i++) {
//            possibilities.add(getInfoPossibilities().get(i));
//        }
        this.endInfoPossibilities = possibilities;
    }

    // toda vez q der pau adicionar uma entrada aqui com a ultima linha recebida
    private ArrayList<String> getEndInfoPossibilities() {//trocar por as possibilidades de prompt do router + --More--
        return this.endInfoPossibilities;
    }

    private void infoPossibilitiesMaker() {
        ArrayList<String> possibilities = new ArrayList<>();
        possibilities.add("!");
        possibilities.add("*");
        possibilities.add("<");
        possibilities.add("domain server (");
        possibilities.add("Interface");
        possibilities.add("RX_RING_ENTRIES");
        possibilities.add("status");
        possibilities.add("Register");
        possibilities.add("User-defined Address");
        possibilities.add("--More--");
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

        this.infoPossibilities = possibilities;
    }

    public ArrayList<String> getInfoPossibilities() {
        return this.infoPossibilities;
    }

    public boolean isInfo(String stringReceived) {
        // ---------------------------------------------------------------------------------------------------
        // TO DO:tem que ver pergunta ao entrar no config!!
        for (int i = 0; i < getInfoPossibilities().size(); i++) {
            if (stringReceived.equals(getInfoPossibilities().get(i))) {
                System.out.println("INFO:" + stringReceived);
                return true;
            }
        }
        return false;
    }
}
