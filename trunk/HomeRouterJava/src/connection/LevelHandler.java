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

    private RouterInfoModel routerInfo;
    private AuthenticationHandler auth;
    private InformationHandler info;
    private CommandHandler prompt;
    private ArrayList<String> msgPossibilities;

    public LevelHandler(String host, int port, GUISolutionModel GuiSol) throws ConnectException, SocketException, IOException {
        routerInfo = new RouterInfoModel();
        ConnectionHandler connection = new ConnectionHandler(host, port, GuiSol);
        prompt = new CommandHandler(connection);
        info = new InformationHandler(connection);
        auth = new AuthenticationHandler(connection);
        getAllMsgPossibilities();
    }

    public int getLevel() {
        return prompt.getLevel();
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

    public void setRouterName(String routerName, String end) {//fazer
        int index;
        for (index = routerName.length() - end.length(); routerName.charAt(index) != '\n'; index--) {
        }
        this.routerInfo.setRouterName(routerName.substring(index + 1, routerName.length() - end.length()));
        this.info.getGUISol().setGUIRouterName(this.routerInfo.getRouterName());
        System.out.println("Router Name:" + this.routerInfo.getRouterName());
    }

    private void getAllMsgPossibilities() {
        ArrayList<String> Possibilities = this.info.getInfoPossibilities();

        Possibilities.addAll(this.auth.getArrayAuthValues());//valores da authenticacao
        Possibilities.addAll(this.prompt.getArrayPromptValues());

        this.msgPossibilities = Possibilities;
    }

    public ArrayList<String> getMsgPossibilities() {
        return this.msgPossibilities;
    }

    public boolean checkLevel() {// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        if (info.isConnected()) {
            try {
                ArrayList<String> arrayReceived = this.info.getConnection().arrayListReadUntil(getMsgPossibilities());
                String Sreceived = arrayReceived.get(0);
                String Mreceived = arrayReceived.get(1);
                if (this.auth.isAuth(Sreceived, prompt.getLevel())) {
                    return true;
                }
                if (this.prompt.isPrompt(Sreceived, Mreceived)) {
                    return true;
                }
                if (this.info.isInfo(Sreceived)) {
                    checkLevel(this.info.checkInformation(Sreceived));
                    return true;
                }
                checkLevel();
            } catch (Exception e) {
                this.info.getGUISol().showMessageDialog("This client can't send data to server!");
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean checkLevel(String Level) {// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        if (info.isConnected()) {
            try {
                System.out.println("Entrou:" + Level + "!!!");
                String Sreceived = Level;
                if (this.auth.isAuth(Sreceived, prompt.getLevel())) {
                    return true;
                }
                if (this.prompt.isPrompt(Sreceived, null)) {
                    return true;
                }
                if (this.info.isInfo(Sreceived)) {
                    checkLevel(this.info.checkInformation(Sreceived));
                    return true;
                }
                checkLevel();
            } catch (Exception e) {
                this.info.getGUISol().showMessageDialog("This client can't send data to server!");
                e.printStackTrace();
            }
        }
        return true;
    }

    public void sendCommand(String command) {
        if (info.isConnected()) {
            this.info.getConnection().send(command);
            checkLevel();
        } else {
            this.info.getConnection().disconnect();
        }
    }

    private void sendLevel(int Level) {
        sendCommand(this.prompt.getCMD(Level));
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
        if(prompt.getLevel() > 45){
            sendCommand("\r\n");
        }else if ((prompt.getLevel() != 2) && (prompt.getLevel() > 0)) {
            sendCommand("exit\r\n");
        } else if (prompt.getLevel() == 2) {
            sendCommand("disable\r\n");
        }
    }

    private void reduceLevelUntil(int Level) {
        while (prompt.getLevel() > Level) {
            if (info.isConnected()) {
                reduceLevel();
            } else {
                return;
            }
        }
    }

    private void riseLevelUntil(int Level) {
        while (prompt.getLevel() < Level) {
            if (info.isConnected()) {
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
            } else {
                return;
            }
        }
    }

    public void goToLevel(int Level) {
        while (prompt.getLevel() != Level) {
            if (info.isConnected()) {
                reduceLevelUntil(Level);
                riseLevelUntil(Level);
            } else {
                return;
            }
        }
    }
}
