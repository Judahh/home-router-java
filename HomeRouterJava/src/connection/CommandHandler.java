/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.util.ArrayList;
import model.CommandModel;
import model.IdentifierModel;

/**
 *
 * @author JH
 */
public class CommandHandler {
    private ConnectionHandler connection;
    private CommandModel model;

    public CommandHandler(ConnectionHandler connection) {
        this.connection=connection;
        this.model=new CommandModel();
    }
    
    public String getPrompt(int level) {
        return this.model.getPrompt(this.model.getPromptValues()[level]);
    }

    public int getLevel() {
        return this.model.getLevel();
    }
    
    public void setLevel(int level) {
        this.model.setLevel(level);
    }
    
    public IdentifierModel getIdentifier() {
        return this.model.getIdentifier();
    }

    public void setIdentifier(IdentifierModel identifier) {
        this.model.setIdentifier(identifier);
    }
    
    public ArrayList<String> getArrayPromptValues() {
        return this.model.getArrayPromptValues();
    }
    
    public String getCMD(int level) {
        return this.model.getCMD(this.model.getPromptValues()[level]);
    }
    
    public boolean isPrompt(String stringReceived, String msgReceived) {
        System.out.println("IS LEVEL?");
        for (int i = 0; i < this.model.getPromptValues().length; i++) {
            if (stringReceived.equals(getPrompt(i))) {
                setLevel(i);
                return true;
            }
        }
        return false;
    }
}