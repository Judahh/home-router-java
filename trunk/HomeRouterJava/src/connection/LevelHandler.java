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
    private InformationHandler info;
    private ArrayList<String> msgPossibilities;

    public LevelHandler(String host, int port, GUISolutionModel GuiSol, AuthenticationHandler auth) throws ConnectException, SocketException, IOException {
        ConnectionHandler connection = new ConnectionHandler(host, port, GuiSol);
        info = new InformationHandler(connection, auth);
    }
    
    public LevelHandler(String host, int port, GUISolutionModel GuiSol) throws ConnectException, SocketException, IOException {
        ConnectionHandler connection = new ConnectionHandler(host, port, GuiSol);
        info = new InformationHandler(connection);
    }

    public boolean isHelp() {
        return this.info.isHelp();
    }

    public void setHelp(boolean help) {
        this.info.setHelp(help);
    }
    
    public boolean isShowPossibleCommands() {
        return this.info.isShowPossibleCommands();
    }

    public void setShowPossibleCommands(boolean showPossibleCommands) {
        this.info.setShowPossibleCommands(showPossibleCommands);
    }
    
    public int getLevel() {
        return info.getPrompt().getLevel();
    }

    public InformationHandler getInfo() {
        return info;
    }

    public void setInfo(InformationHandler info) {
        this.info = info;
    }

    public CommandHandler getPrompt() {
        return info.getPrompt();
    }

    public AuthenticationHandler getAuth() {
        return info.getAuth();
    }

    public void setAuth(AuthenticationHandler auth) {
        info.setAuth(auth);
    }

    public ConnectionHandler getConnection() {
        return this.info.getConnection();
    }

    public String getRouterName() {
        return this.info.getPrompt().getRouterName();
    }
    
    public boolean checkLevel() {// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        if (info.isConnected()) {
            try {
                this.info.checkInformation();
                return true;
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
        sendCommand(this.getPrompt().getCMD(Level));
    }

    private void riseLevel(int SideLevel) {
        if ((getPrompt().getLevel() < 3) || (getPrompt().getLevel() == 6) || (getPrompt().getLevel() == 9) || (getPrompt().getLevel() == 23)) {
            sendLevel(getPrompt().getLevel() + 1);
        } else if (getPrompt().getLevel() == 3) {
            riseConfigSideLevel(SideLevel);
        } else if (getPrompt().getLevel() == 17) {
            riseIfSideLevel(SideLevel);
        } else if (getPrompt().getLevel() == 39) {
            riseServerSideLevel(SideLevel);
        } else if (getPrompt().getLevel() == 41) {
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
        if(getPrompt().getLevel() > 44){
            sendCommand("\r\n");
        }else if ((getPrompt().getLevel() != 2) && (getPrompt().getLevel() > 0)) {
            sendCommand("exit\r\n");
        } else if (getPrompt().getLevel() == 2) {
            sendCommand("disable\r\n");
        }
    }

    private void reduceLevelUntil(int Level) {
        while (getPrompt().getLevel() > Level) {
            if (info.isConnected()) {
                reduceLevel();
            } else {
                return;
            }
        }
    }

    private void riseLevelUntil(int Level) {
        while (getPrompt().getLevel() < Level) {
            if (info.isConnected()) {
                if ((getPrompt().getLevel() < 3) || (getPrompt().getLevel() == 6) || (getPrompt().getLevel() == 9) || (getPrompt().getLevel() == 23)) {
                    riseLevel(0);
                } else if (getPrompt().getLevel() == 3) {
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
                } else if (getPrompt().getLevel() == 17) {
                    if (Level > 17 && Level < 21) {
                        riseLevel(Level);
                    } else {
                        reduceLevel();
                    }
                } else if (getPrompt().getLevel() == 39) {
                    if (Level == 40 || Level == 41) {
                        riseLevel(Level);
                    } else {
                        reduceLevel();
                    }
                } else if (getPrompt().getLevel() == 41) {
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
        while (getPrompt().getLevel() != Level) {
            if (info.isConnected()) {
                reduceLevelUntil(Level);
                riseLevelUntil(Level);
            } else {
                return;
            }
        }
    }
}
