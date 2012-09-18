/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.net.telnet.TelnetClient;
/**
 *
 * @author JH
 */
//Classe: TelnetHandler
//Esta classe sera responsaver pelo manipulamento commandos a alto nivel
//Ela sera uma Thread para pode fazer varias instancias independentes e assim poder executar este cliente em varios routers distintos
public class TelnetHandler {
    private LevelHandler routerLevel;

    public TelnetHandler(LevelHandler routerLevel) {
        this.routerLevel = routerLevel;
    }
    
    //funcao exclusiva para testes
    public void goToLevelRouter(int Level){
        routerLevel.goToLevel(Level);
    }
    
//    Prototipo de funcao para enviar comando//----------------------------------------------------------------------------------------------------------------------------------------------------------------
//    public void CMDX(){
//        routerLevel.goToLevel(level x);
//        routerLevel.sendCommand(x);
//    }
    
    public String getRouterName(){
        return routerLevel.getRouterName();
    }
    
    public void sendUserCommand(String command){
        routerLevel.sendCommand(command);
    }
    
    public String getClock(){
        try {
            System.out.println("CLOCK!!!");
            routerLevel.goToLevel(1);
            routerLevel.sendCommand("show clock\r\n");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean Login() {
        routerLevel.checkLevel();
        if(routerLevel.getPrompt().getLevel()>0){
            return true;
        }
        return false;
    }
}
