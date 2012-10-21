/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author JH
 */
public class InformationModel {
    private boolean showPossibleCommands;
    private boolean help;
    private ClockModel clock;
    private RunModel run;
    private String version;
    private ArrayList<String> interfaceTabs;
    private ArrayList<String> staticRoutes;
    private ArrayList<String> dynamicRoutes;
    private ArrayList<String> endInfoPossibilities;
    private ArrayList<String> infoPossibilities;
    private ArrayList<String> allInfoPossibilities;

    public InformationModel() {
        this.clock =new ClockModel();
        this.run =new RunModel();
        this.version =new String();
        this.interfaceTabs =new ArrayList<>();
        this.staticRoutes = new ArrayList<String>();
        this.dynamicRoutes = new ArrayList<String>();
        this.endInfoPossibilities = new ArrayList<String>();
        this.infoPossibilities = new ArrayList<String>();
        this.allInfoPossibilities = new ArrayList<String>();
        informationPossibilitiesMaker();
        endInformationPossibilitiesMaker();
        getAllInformationPossibilitiesMaker();
    }

    public ArrayList<String> getAllInfoPossibilities() {
        return allInfoPossibilities;
    }

    public void setAllInfoPossibilities(ArrayList<String> allInfoPossibilities) {
        this.allInfoPossibilities = allInfoPossibilities;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public ArrayList<String> getInfoPossibilities() {
        return infoPossibilities;
    }

    public void setInfoPossibilities(ArrayList<String> infoPossibilities) {
        this.infoPossibilities = infoPossibilities;
    }

    public boolean isShowPossibleCommands() {
        return showPossibleCommands;
    }

    public void setShowPossibleCommands(boolean showPossibleCommands) {
        this.showPossibleCommands = showPossibleCommands;
    }

    public ClockModel getClock() {
        return clock;
    }

    public void setClock(ClockModel clock) {
        this.clock = clock;
    }

    public ArrayList<String> getDynamicRoutes() {
        return dynamicRoutes;
    }

    public void setDynamicRoutes(ArrayList<String> dynamicRoutes) {
        this.dynamicRoutes = dynamicRoutes;
    }

    public ArrayList<String> getEndInfoPossibilities() {
        return endInfoPossibilities;
    }

    public void setEndInfoPossibilities(ArrayList<String> endInfoPossibilities) {
        this.endInfoPossibilities = endInfoPossibilities;
    }

    public ArrayList<String> getInterfaceTabs() {
        return interfaceTabs;
    }

    public void setInterfaceTabs(ArrayList<String> interfaceTabs) {
        this.interfaceTabs = interfaceTabs;
    }

    public RunModel getRun() {
        return run;
    }

    public void setRun(RunModel run) {
        this.run = run;
    }

    public ArrayList<String> getStaticRoutes() {
        return staticRoutes;
    }

    public void setStaticRoutes(ArrayList<String> staticRoutes) {
        this.staticRoutes = staticRoutes;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public ArrayList<String> getInformationPossibilities() {
        return this.infoPossibilities;
    }
    
    public ArrayList<String> getEndInformationPossibilities() {
        return this.endInfoPossibilities;
    }

    private void informationPossibilitiesMaker() {
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
    
    private void getAllInformationPossibilitiesMaker() {
        ArrayList<String> possibilities= new ArrayList<>();
        possibilities.add("--More--");
        AuthenticationModel AuthModel = new AuthenticationModel();
        possibilities.addAll(AuthModel.getArrayAuthValues());//valores da authenticacao
        CommandModel CMDModel = new CommandModel();
        possibilities.addAll(CMDModel.getArrayPromptValues());
        this.allInfoPossibilities = possibilities;
    }

    public ArrayList<String> getAllInformationPossibilities() {
        return this.allInfoPossibilities;
    }
    
    private void endInformationPossibilitiesMaker() {
        ArrayList<String> possibilities = new ArrayList<>();
        possibilities.add("--More--");
        CommandModel CMDModel = new CommandModel();
        possibilities.addAll(CMDModel.getArrayPromptValues());
        this.endInfoPossibilities = possibilities;
    }

    public boolean isInformation(String stringReceived) {
        for (int i = 0; i < getEndInformationPossibilities().size(); i++) {
            if (stringReceived.equals(getEndInformationPossibilities().get(i))) {
                return true;
            }
        }
        return false;
    }
}
