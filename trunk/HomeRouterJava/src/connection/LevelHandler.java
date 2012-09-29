/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import model.GUISolutionModel;

import model.RouterInfoModel;

/**
 *
 * @author JH
 */
public class LevelHandler {

    private int level;
    private int checkCount;
    private int checkPCount;
    private RouterInfoModel routerInfo;
    private AuthenticationHandler auth;
    private InformationHandler info;
    private CommandHandler prompt;
    private String[] msgPossibilities;

    public LevelHandler(String host, int port, GUISolutionModel GuiSol) throws ConnectException, SocketException, IOException {
        auth = new AuthenticationHandler(0, GuiSol);
        prompt = new CommandHandler(0);
        routerInfo = new RouterInfoModel();
        info = new InformationHandler(host, port, GuiSol);
        getAllMsgPossibilities();
        checkCount = 0;
    }

    public int getLevel() {
        this.checkLevel();
        return level;
    }

    public InformationHandler getInfo() {
        return info;
    }

    public void setInfo(InformationHandler info) {
        this.info = info;
    }

    public CommandHandler getPrompt() {
        return prompt;
    }

    public void setPrompt(CommandHandler prompt) {
        this.prompt = prompt;
    }

    public AuthenticationHandler getAuth() {
        return auth;
    }

    public void setAuth(AuthenticationHandler auth) {
        this.auth = auth;
    }

    public ConnectionHandler getConnection() {
        return this.info.getConnection();
    }

    public String getRouterName() {
        return routerInfo.getRouterName();
    }

    public void setRouterName(String routerName) {
        // fazer parte pare ir ate # ou > ou (
        int end = routerName.length() - 1;
        for (int i = routerName.length() - 1; i >= 0; i--) {
            if (routerName.charAt(i) == '(' || routerName.charAt(i) == '#' || routerName.charAt(i) == '>') {
                end = i;
            }
        }
        for (int i = routerName.length() - 1; i >= 0; i--) {
            if (routerName.charAt(i) == '\n' || routerName.charAt(i) == ' ') {
                this.routerInfo.setRouterName(routerName.substring(i + 1, end));
                this.info.getGUISol().setGUIRouterName(this.routerInfo.getRouterName());// mostrar mudanca na GUI
                return;
            }
        }
    }

    public void getAllMsgPossibilities() {
        // String[] possibilities=new
        // String[]{"Login: ","login: ","User: ","user: ","Password: ","password: ","Pass: ","pass: "};
        ArrayList<String> Possibilities = new ArrayList<>();
        // Possibilities.addAll(Arrays.asList(possibilities));
        Possibilities = this.info.getInfoPossibilities();


        for (int i = 0; i < this.auth.getAuthValues().length; i++) {
            Possibilities.add(this.auth.getAuth(this.auth.getAuthValues()[i]));
        }
        for (int i = 0; i < this.prompt.getPromptValues().length; i++) {
            Possibilities.add(this.prompt.getPrompt(this.prompt.getPromptValues()[i]));
        }

        String[] possibilitiesV = new String[Possibilities.size()];

        for (int i = 0; i < possibilitiesV.length; i++) {
            possibilitiesV[i] = Possibilities.get(i);
        }

        this.msgPossibilities = possibilitiesV;
        return;
    }

    public String[] getMsgPossibilities() {
        return this.msgPossibilities;
    }
    
    public boolean checkLevel() {// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        try {
            checkCount++;
            checkPCount++;
            ArrayList<String> arrayReceived = this.info.getConnection().arrayListReadUntil(getMsgPossibilities());
            String Sreceived = arrayReceived.get(0);
            String Mreceived = arrayReceived.get(1);
            // ---------------------------------------------------------------------------------------------------
            // TO DO:tem que ver pergunta ao entrar no config!!
            for (int i = 0; i < this.prompt.getPromptValues().length; i++) {
                if (Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[i]))) {
                    this.prompt.setLevel(i);
                    System.out.println("Level:" + i);
                    this.level = i;

                    if (i > 0 && i < this.prompt.getPromptValues().length - 2) {
                        setRouterName(Mreceived);
                    }

                    if (Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[0]))) {
                        checkLevel();
                    }
                    if (Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[this.prompt.getPromptValues().length - 1]))||Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[this.prompt.getPromptValues().length - 2]))) {
                        sendCommand("\r\n");
                    }
                    if (Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[this.prompt.getPromptValues().length - 3]))) {
                        sendCommand("terminal\r\n");
                    }
                    checkCount = 0;
                    checkPCount = 0;
                    return true;
                }
            }
            // ---------------------------------------------------------------------------------------------------
            // Aqui em baixo ele ira verificar se foi uma informacao se for ele
            // manda para o metodo que a tratara
            // sera algo parecido com:
            for (int i = 0; i < this.info.getInfoPossibilities().size(); i++) {
                if (Sreceived == this.info.getInfoPossibilities().get(i)) {
                    System.out.println("INFO:" + Sreceived);
                    this.info.checkInfo(Sreceived);
                    checkLevel();
                    checkCount = 0;
                    checkPCount = 0;
                    return true;
                }
            }
            // ---------------------------------------------------------------------------------------------------
            for (int i = 0; i < this.auth.getAuthValues().length; i++) {
                if (Sreceived.equals(this.auth.getAuth(this.auth.getAuthValues()[i]))) {
                    if (i < 4) {
                        // -----------Usuario errado ou
                        // falta----------------------------------------------------------
                        // sendCommand(auth.getUser());
                        if (checkCount > 1) {
                            auth.setUser(null);
                        }
                        sendCommand(auth.getUser()+"\r\n");
                        //sendCommand("ciscoUser\r\n");// pegar user por GUI
                        
                    } else {
                        // -------------senha errada ou
                        // falta---------------------------------------------------------
                        // sendCommand(auth.getPassword());
                        if (checkPCount > 1) {
                            auth.setPassword(null,(level>0));
                        }
                        sendCommand(auth.getPassword(level>0)+"\r\n");
                        //sendCommand("cisco\r\n");// pegar pass por GUI
                    }
                    checkCount = 0;
                    checkPCount = 0;
                    return true;
                }
            }
            checkLevel();
        } catch (Exception e) {
            checkCount = 0;
            checkPCount = 0;
            e.printStackTrace();
        }
        checkCount = 0;
        checkPCount = 0;
        return true;
    }

    public boolean checkLevel(String Level) {// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        try {
            checkCount++;
            checkPCount++;
            ArrayList<String> arrayReceived = this.info.getConnection().arrayListReadUntil(getMsgPossibilities());
            String Sreceived = arrayReceived.get(0);
            String Mreceived = arrayReceived.get(1);
            // ---------------------------------------------------------------------------------------------------
            // TO DO:tem que ver pergunta ao entrar no config!!
            for (int i = 0; i < this.prompt.getPromptValues().length; i++) {
                if (Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[i]))) {
                    this.prompt.setLevel(i);
                    System.out.println("Level:" + i);
                    this.level = i;

                    if (Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[0]))) {
                        checkLevel();
                    }
                    if (Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[this.prompt.getPromptValues().length - 1]))||Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[this.prompt.getPromptValues().length - 2]))) {
                        sendCommand("\r\n");
                    }
                    if (Sreceived.equals(this.prompt.getPrompt(this.prompt.getPromptValues()[this.prompt.getPromptValues().length - 3]))) {
                        sendCommand("terminal\r\n");
                    }
                    checkCount = 0;
                    checkPCount = 0;
                    return true;
                }
            }
            // ---------------------------------------------------------------------------------------------------
            // Aqui em baixo ele ira verificar se foi uma informacao se for ele
            // manda para o metodo que a tratara
            // sera algo parecido com:
            for (int i = 0; i < this.info.getInfoPossibilities().size(); i++) {
                if (Sreceived == this.info.getInfoPossibilities().get(i)) {
                    System.out.println("INFO:" + Sreceived);
                    this.info.checkInfo(Sreceived);
                    checkLevel();
                    checkCount = 0;
                    checkPCount = 0;
                    return true;
                }
            }
            // ---------------------------------------------------------------------------------------------------
            for (int i = 0; i < this.auth.getAuthValues().length; i++) {
                if (Sreceived.equals(this.auth.getAuth(this.auth.getAuthValues()[i]))) {
                    if (i < 4) {
                        // -----------Usuario errado ou
                        // falta----------------------------------------------------------
                        // sendCommand(auth.getUser());
                        if (checkCount > 1) {
                            auth.setUser(null);
                        }
                        sendCommand(auth.getUser()+"\r\n");
                        //sendCommand("ciscoUser\r\n");// pegar user por GUI
                        
                    } else {
                        // -------------senha errada ou
                        // falta---------------------------------------------------------
                        // sendCommand(auth.getPassword());
                        if (checkPCount > 1) {
                            auth.setPassword(null,(level>0));
                        }
                        sendCommand(auth.getPassword(level>0)+"\r\n");
                        //sendCommand("cisco\r\n");// pegar pass por GUI
                    }
                    checkCount = 0;
                    checkPCount = 0;
                    return true;
                }
            }
            checkLevel();
        } catch (Exception e) {
            checkCount = 0;
            checkPCount = 0;
            e.printStackTrace();
        }
        checkCount = 0;
        checkPCount = 0;
        return true;
    }

    public void sendCommand(String command) {
        this.info.getConnection().send(command);
        checkLevel();
    }

    private void sendLevel(int Level) {
        sendCommand(this.prompt.getCMD(this.prompt.getPromptValues()[Level]));
    }

    private void riseLevel(int SideLevel) {
        if ((prompt.getLevel() < 3) || (prompt.getLevel() == 6) || (prompt.getLevel() == 9) || (prompt.getLevel() == 23)) {
            sendLevel(prompt.getLevel() + 1);
        } else if (prompt.getLevel() == 3) {
            riseConfigSideLevel(SideLevel);
        } else if (prompt.getLevel() == 17) {
            riseIfSideLevel(SideLevel);
        } else if (prompt.getLevel() == 39) {
            riseServerSideLevel(SideLevel);
        } else if (prompt.getLevel() == 41) {
            riseDlurSideLevel(SideLevel);
        }
    }

    private void riseConfigSideLevel(int SideLevel) {
        if (SideLevel > 3 && SideLevel < 39) {
            if ((SideLevel != 7) && (SideLevel != 10) && (SideLevel != 24)) {
                if ((SideLevel < 18) || (SideLevel > 20)) {
                    sendLevel(SideLevel);
                }
            }
        }
    }

    private void riseIfSideLevel(int SideLevel) {
        if (SideLevel > 17 && SideLevel < 21) {
            sendLevel(SideLevel);
        }
    }

    private void riseServerSideLevel(int SideLevel) {
        if (SideLevel == 40 || SideLevel == 41) {
            sendLevel(SideLevel);
        }
    }

    private void riseDlurSideLevel(int SideLevel) {
        if (SideLevel == 42 || SideLevel == 43) {
            sendLevel(SideLevel);
        }
    }

    private void reduceLevel() {
        if ((prompt.getLevel() != 2) && (prompt.getLevel() > 0)) {
            sendCommand("exit\r\n");
        } else if (prompt.getLevel() == 2) {
            sendCommand("disable\r\n");
        }
    }

    private void reduceLevelUntil(int Level) {
        while (prompt.getLevel() > Level) {
            reduceLevel();
        }
    }

    private void riseLevelUntil(int Level) {
        while (prompt.getLevel() < Level) {
            if ((prompt.getLevel() < 3) || (prompt.getLevel() == 6) || (prompt.getLevel() == 9) || (prompt.getLevel() == 23)) {
                riseLevel(0);
            } else if (prompt.getLevel() == 3) {
                if (Level > 3 && Level < 39) {
                    if ((Level != 7) && (Level != 10) && (Level != 24)) {
                        if ((Level < 18) || (Level > 20)) {
                            riseLevel(Level);
                        } else {
                            riseLevel(17);
                        }
                    } else {
                        riseLevel(Level - 1);
                    }
                } else {
                    reduceLevel();
                }
            } else if (prompt.getLevel() == 17) {
                if (Level > 17 && Level < 21) {
                    riseLevel(Level);
                } else {
                    reduceLevel();
                }
            } else if (prompt.getLevel() == 39) {
                if (Level == 40 || Level == 41) {
                    riseLevel(Level);
                } else {
                    reduceLevel();
                }
            } else if (prompt.getLevel() == 41) {
                if (Level == 42 || Level == 43) {
                    sendLevel(Level);
                } else {
                    reduceLevel();
                }
            }

        }
    }

    public void goToLevel(int Level) {
        while (prompt.getLevel() != Level) {
            reduceLevelUntil(Level);
            riseLevelUntil(Level);
        }
    }
}
