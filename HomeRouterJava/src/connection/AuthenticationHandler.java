/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author JH
 */
public class AuthenticationHandler {

    private int checkCount;
    private int checkPCount;
    private int checkPECount;
    private ConnectionHandler connection;
    private Auth auth;
    private String passwordVTY;
    private String passwordEnable;
    private String user;
    ArrayList<String> arrayAuthValues;

    public AuthenticationHandler(Auth auth, ConnectionHandler connection) {
        this.auth = auth;
        this.passwordVTY = null;
        this.passwordEnable = null;
        this.user = null;
        this.connection = connection;
        this.checkCount = 0;
        this.checkPCount = 0;
        this.checkPECount = 0;
        arrayAuthValuesMaker();
    }

    public AuthenticationHandler(int i, ConnectionHandler connection) {
        this.auth = Auth.values()[i];
        this.passwordVTY = null;
        this.passwordEnable = null;
        this.user = null;
        this.connection = connection;
        this.checkCount = 0;
        this.checkPCount = 0;
        this.checkPECount = 0;
        arrayAuthValuesMaker();
    }

    public Auth getAuthentication() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    private String getPassword(boolean Enable) {
        if (Enable) {
            System.out.println("GET ENABLE");
            return getPasswordB();
        }
        System.out.println("GET DISABLE");
        return getPassword();
    }

    private String getPassword() {
        if (passwordVTY == null) {
            passwordVTY = this.connection.getGuiSol().getPassword();
        }
        return passwordVTY;
    }

    public void setPassword(String password, boolean Enable) {
        if (Enable) {
            System.out.println("SET ENABLE");
            this.passwordEnable = password;
        } else {
            System.out.println("SET DISABLE");
            this.passwordVTY = password;
        }
    }

    public void setPassword(String password) {
        this.passwordVTY = password;
    }

    private String getPasswordB() {
        if (passwordEnable == null) {
            passwordEnable = this.connection.getGuiSol().getEnablePassword();
            //setar pass na interface
        }
        return passwordEnable;
    }

    public void setPasswordB(String passwordB) {
        this.passwordEnable = passwordB;
    }

    public String getUser() {
        if (user == null) {
            user = this.connection.getGuiSol().getUser();
            //setar user na interface
        }
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Auth getAuthenticationPrompt() {
        return this.auth;
    }

    public Auth[] getAuthValues() {
        return Auth.values();
    }

    private void arrayAuthValuesMaker() {
        ArrayList<String> values = new ArrayList<>();
        for (int index = 0; index < Auth.values().length; index++) {
            values.add(getAuth(Auth.values()[index]));
        }
        this.arrayAuthValues = values;
    }

    public ArrayList<String> getArrayAuthValues() {
        return this.arrayAuthValues;
    }

    public enum Auth {

        Login0, Login1, Login2, Login3, Pass0, Pass1, Pass2, Pass3
    }

    public String getAuth() {
        return getAuth(this.auth);
    }

    public String getAuth(Auth auth) {
        switch (auth) {
            case Login0:
                return "Login:";
            case Login1:
                return "login:";
            case Login2:
                return "User:";
            case Login3:
                return "user:";
            case Pass0:
                return "Password:";
            case Pass1:
                return "password:";
            case Pass2:
                return "Pass:";
            case Pass3:
                return "pass:";
            default:
                return "######";
        }
    }

    public boolean isAuth(String stringReceived, int level) {
        System.out.println("IS AUTH?");
        for (int i = 0; i < getAuthValues().length; i++) {
            if (stringReceived.equals(getAuth(getAuthValues()[i]))) {
                if (i < 4) {
                    // -----------Usuario errado ou falta----------------------------------------------------------
                    if (checkCount > 0) {
                        setUser(null);
                    }
                    connection.send(getUser() + "\r\n");
                    checkCount++;
                } else {
                    // -------------senha errada ou falta---------------------------------------------------------
                    if (checkPECount > 0) {
                        setPassword(null, (level > 0));
                    }
                    connection.send(getPassword(level > 0) + "\r\n");
                    if (level > 0) {
                        checkPECount++;
                    } else {
                        checkPCount++;
                    }
                }
                return true;
            }
        }
        this.checkCount = 0;
        this.checkPCount = 0;
        this.checkPECount = 0;
        return false;
    }
}
