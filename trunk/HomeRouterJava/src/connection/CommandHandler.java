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
    
    public boolean isPrompt(String stringReceived) {
        System.out.println("IS LEVEL?");
        for (int i = 0; i < this.model.getPromptValues().length; i++) {
            if (stringReceived.equals(getPrompt(i))) {
                setLevel(i);
                return true;
            }
        }
        return false;
    }
    
    public String getRouterName(){
        return this.model.getRouterInfo().getRouterName();
    }
    
    public String getOs(){
        return this.model.getRouterInfo().getOs();
    }
    
    public void setRouterName(String fullInfo,String level) {
        if(isPrompt(level)){
            System.out.println("YES:"+fullInfo);
            if (getLevel() < getArrayPromptValues().size() - 3){
                int index;
                for (index = fullInfo.length() - level.length(); fullInfo.charAt(index) != '\n'; index--);
                String RouterName = fullInfo.substring(index + 1, fullInfo.length() - level.length());
                this.connection.getGuiSol().setGUIRouterName(RouterName);
                this.model.getRouterInfo().setRouterName(RouterName);
            }
        }
    }
    
    public String getRouterName(String routerName, String level) {
        if(isPrompt(level)){
            System.out.println("YES:"+routerName);
            if (getLevel() < getArrayPromptValues().size() - 3){
                int index;
                for (index = routerName.length() - level.length(); routerName.charAt(index) != '\n'; index--);
                String RouterName = routerName.substring(index + 1, routerName.length() - level.length());
                this.connection.getGuiSol().setGUIRouterName(RouterName);
                this.model.getRouterInfo().setRouterName(RouterName);
                return RouterName;
            }
        }
        return null;
    }
}