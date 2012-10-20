/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import connection.ConnectionHandler;
import java.util.ArrayList;

/**
 *
 * @author JH
 */
public class AuthenticationModel {
    private Auth auth;
    private String passwordVTY;
    private String passwordEnable;
    private String user;
    ArrayList<String> arrayAuthValues;
    private ConnectionHandler connection;

    public AuthenticationModel() {//EXCLUSIVO PARA USO DO INFORMATIONMODEL
        this.auth = Auth.values()[0];
        this.passwordVTY = null;
        this.passwordEnable = null;
        this.user = null;
        this.connection = null;
        arrayAuthValuesMaker();
    }
    
    public AuthenticationModel(Auth auth, ConnectionHandler connection) {
        this.auth = auth;
        this.passwordVTY = null;
        this.passwordEnable = null;
        this.user = null;
        this.connection = connection;
        arrayAuthValuesMaker();
    }
    
    public AuthenticationModel(ConnectionHandler connection) {
        this.auth = Auth.values()[0];
        this.passwordVTY = null;
        this.passwordEnable = null;
        this.user = null;
        this.connection = connection;
        arrayAuthValuesMaker();
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
    
    public Auth getAuthentication() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getPassword(boolean Enable) {
        if (Enable) {
            System.out.println("GET ENABLE");
            return getPasswordB();
        }
        System.out.println("GET DISABLE");
        return getPassword();
    }

    public String getPassword() {
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

    public String getPasswordB() {
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

    public ConnectionHandler getConnection() {
        return connection;
    }
}
