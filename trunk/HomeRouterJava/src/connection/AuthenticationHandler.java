/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.AuthenticationModel;

/**
 *
 * @author JH
 */
public class AuthenticationHandler {

    private int checkCount;
    private int checkPCount;
    private int checkPECount;
    private AuthenticationModel model;

    public AuthenticationHandler(ConnectionHandler connection) {
        this.checkCount = 0;
        this.checkPCount = 0;
        this.checkPECount = 0;
        this.model=new AuthenticationModel(connection);
    }

    public boolean isAuth(String stringReceived, int level) {
        System.out.println("IS AUTH?");
        for (int i = 0; i < this.model.getAuthValues().length; i++) {
            if (stringReceived.equals(this.model.getAuth(this.model.getAuthValues()[i]))) {
                if (i < 4) {
                    // -----------Usuario errado ou falta----------------------------------------------------------
                    if (checkCount > 0) {
                        this.model.setUser(null);
                    }
                    this.model.getConnection().send(this.model.getUser() + "\r\n");
                    checkCount++;
                } else {
                    // -------------senha errada ou falta---------------------------------------------------------
                    if (checkPECount > 0) {
                        this.model.setPassword(null, (level > 0));
                    }
                    this.model.getConnection().send(this.model.getPassword(level > 0) + "\r\n");
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
    
    public ArrayList<String> getArrayAuthValues() {
        return this.model.getArrayAuthValues();
    }
    
    public void setPassword(String password, boolean Enable) {
        this.model.setPassword(password, Enable);
    }
    
    public void setPassword(String password) {
        this.model.setPassword(password);
    }
    
    public void setPasswordB(String passwordB) {
        this.model.setPassword(passwordB);
    }
}
