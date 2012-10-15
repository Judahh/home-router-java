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
import model.*;

import view.FastEthernet;
import view.Serial;

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
    private ConnectionHandler connection;
    private InformationModel model;

    public InformationHandler(ConnectionHandler connection)throws ConnectException, SocketException, IOException {
        this.connection = connection;
        this.model=new InformationModel();
    }

    public ConnectionHandler getConnection() {
        return connection;
    }

    public GUISolutionModel getGUISol() {
        return this.connection.getGuiSol();
    }
    
    public String checkInformation(String FirstPartInfo) {//retornar prompt do router
        System.out.println("IS INFO?");
        if (isConnected()) {
            if (FirstPartInfo.contains("More")) {
                connection.send(" ");
            } else {
                for (int index = 1; index < this.model.getEndInformationPossibilities().size(); index++) {
                    if (FirstPartInfo.contains(this.model.getEndInformationPossibilities().get(index))) {
                        connection.send("\r\n");
                    }
                }
            }
            return parseInformation(FirstPartInfo);
        }
        return null;
    }

    public String checkInformation(boolean more, String FirstPartInfo) {//retornar prompt do router
        System.out.println("IS INFO?");
        if (isConnected()) {
            if (more) {
                connection.send(" ");
            } else {
                for (int index = 1; index < this.model.getEndInformationPossibilities().size(); index++) {
                    if (FirstPartInfo.contains(this.model.getEndInformationPossibilities().get(index))) {
                        connection.send("\r\n");
                    }
                }
            }
            return parseInformation(FirstPartInfo);
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

    private void showDialog(String fullInfo) {
        if (!showInformation(fullInfo)) {
            if (!showWarning(fullInfo)) {
                if (showError(fullInfo)) {
                    //ouve erro!
                }
            }
        }
        System.out.println("---------------------------------");
        System.out.println("FullInfo:");
        System.out.println(fullInfo);
        System.out.println("---------------------------------");
    }
    
    private boolean showError(String fullInfo) {
        if (fullInfo.contains("%")) {
            String error = fullInfo.substring(fullInfo.indexOf('%') + 1);
            String[] errorParts=error.split("%");
            for (int i = 0; i < errorParts.length; i++) {
                System.out.println("Error "+i+":"+errorParts[i]+"-----");
                while((errorParts[i].charAt(errorParts[i].length()-1)=='\n')||(errorParts[i].charAt(errorParts[i].length()-1)=='\r')||(errorParts[i].charAt(errorParts[i].length()-1)=='.')){
                    errorParts[i]=errorParts[i].substring(0, errorParts[i].length()-1);
                }
                this.connection.getGuiSol().showErrorDialog(errorParts[i] + "!");
            }
            return true;
        }
        return false;
    }

    private boolean showWarning(String fullInfo) {
        if (fullInfo.contains("*")) {
            String warning = fullInfo.substring(fullInfo.indexOf('*') + 1);
            warning = warning.replace('*', '~');
            String[] warningParts=warning.split("~");
            for (int i = 0; i < warningParts.length; i++) {
                while((warningParts[i].charAt(warningParts[i].length()-1)=='\n')||(warningParts[i].charAt(warningParts[i].length()-1)=='\r')||(warningParts[i].charAt(warningParts[i].length()-1)=='.')){
                    warningParts[i]=warningParts[i].substring(0, warningParts[i].length()-1);
                }
                this.connection.getGuiSol().showWarningDialog(warningParts[i] + "!");
            }
            return true;
        }
        return false;
    }
    
    private boolean showInformation(String fullInfo) {
        if (fullInfo.contains("!")) {
            String info = fullInfo.substring(fullInfo.indexOf('!') + 1);
            String[] infoParts=info.split("!");
            for (int i = 0; i < infoParts.length; i++) {
                while((infoParts[i].charAt(infoParts[i].length()-1)=='\n')||(infoParts[i].charAt(infoParts[i].length()-1)=='\r')||(infoParts[i].charAt(infoParts[i].length()-1)=='.')){
                    infoParts[i]=infoParts[i].substring(0, infoParts[i].length()-1);
                }
                this.connection.getGuiSol().showMessageDialog(infoParts[i] + "!");
            }
            return true;
        }
        return false;
    }
    
    public String parseInformation(String FirstPartInfo) {
        ArrayList<String> InfoS = connection.arrayListReadUntil(this.model.getEndInformationPossibilities());
        String fullInfo = FirstPartInfo + InfoS.get(1);

        if (InfoS.get(0).contains("More")) {
            return checkInformation(true, fullInfo.split("--More--")[0]);
        }

        if (InfoS.get(0).contains(this.model.getEndInformationPossibilities().get(0))) {
            return checkInformation(false, this.model.getEndInformationPossibilities().get(0));
        }

        String lastInfo = null;
        String routerName = null;
        for (int index = 1; index < this.model.getEndInformationPossibilities().size(); index++) {
            if (InfoS.get(0).contains(this.model.getEndInformationPossibilities().get(index))) {
                routerName = setRouterName(InfoS.get(1), InfoS.get(0), index);
                lastInfo = this.model.getEndInformationPossibilities().get(index);
                index = this.model.getEndInformationPossibilities().size();//saida(depois trocar)-----------------------------------------------------------------
            }
        }

        fullInfo = fullInfo.split(routerName + lastInfo)[0];
        parseClockInformation(fullInfo);
        parseShowRunInformation(fullInfo);
        parseShowIpInterfaceBriefInformation(fullInfo);
        parseShowControllersInformation(InfoS.get(1));

        showDialog(fullInfo);
        return lastInfo;
    }
    
    // verifica se a interface serial ÃƒÂ¯Ã‚Â¿Ã‚Â½ master ou slave
    public void parseShowControllersInformation(String info) {//refazer que tah uma merda
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

    public void parseShowRunInformation(String info) {//refazer que tah uma merda
        String[] infoarray = info.split("\\r");
        // impede que se adicionem interfaces repetidas

        boolean newroutes = false;
        for (int i = 0; i < infoarray.length; i++) {
            if (infoarray[i].contains("version")) {
                String[] tempversion = infoarray[i].split("version");
                this.model.setVersion(tempversion[1].trim());
                this.connection.getGuiSol().setIos("IOS version " + this.model.getVersion());
            }

            if (infoarray[i].contains("ip route")) {
                String[] temproute = infoarray[i].split(" ");
                String route = temproute[2] + " with mask " + temproute[3]
                        + " via " + temproute[4];
                if (!this.model.getStaticRoutes().contains(route)) {
                    this.model.getStaticRoutes().add(route);
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
                    if (!this.model.getDynamicRoutes().contains(temproute2[1].trim())) {
                        this.model.getDynamicRoutes().add(temproute2[1].trim());
                        this.connection.getGuiSol().addDynamicRoute(temproute2[1].trim());
                        newroutes = true;
                    }

                    j++;
                    cache = infoarray[j];
                }

            }

            if (infoarray[i].contains("interface FastEthernet")) {

                String[] temparray = infoarray[i].split(" ");
                if (!this.model.getInterfaceTabs().contains(infoarray[i])) {
                    this.model.getInterfaceTabs().add(infoarray[i]);
                    this.connection.getGuiSol().addFastEthernetInterface(temparray[1].substring(temparray[1].lastIndexOf("t") + 1));

                }

            }

            if (infoarray[i].contains("interface Serial")) {
                String[] temparray = infoarray[i].split(" ");
                if (!this.model.getInterfaceTabs().contains(infoarray[i])) {
                    this.model.getInterfaceTabs().add(infoarray[i]);
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
    
    public void parseShowIpInterfaceBriefInformation(String info) {//usa show ip Interface Brief
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
    public void parseClockInformation(String info) {//refazer que tah uma merda
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
    
    public ArrayList<String> getInformationPossibilities(){
        return this.model.getInformationPossibilities();
    }
    
    public boolean isInformation(String Info){
        return this.model.isInformation(Info);
    }
}
