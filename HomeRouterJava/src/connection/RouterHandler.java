/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;


import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextArea;
import org.apache.commons.net.telnet.TelnetClient;
/**
 *
 * @author JH
 */
//Classe: RouterHandler
//Esta classe sera responsaver pelo manipulamento commandos a alto nivel
//Ela sera uma Thread para pode fazer varias instancias independentes e assim poder executar este cliente em varios routers distintos
public class RouterHandler {
    private LevelHandler routerLevel;

    public RouterHandler(String host,int port) throws ConnectException, SocketException, IOException {
        this.routerLevel = new LevelHandler(host,port);
    }
    
    public RouterHandler(String host,int port,JTextArea console) throws ConnectException, SocketException, IOException {
        this.routerLevel = new LevelHandler(host,port,console);
    }
    
    //estes metodos serao usados para selecionar a interface e porta que sera usada(antes de entrar no level dela (claro)
    private void setInterface(int interfaceCod){
    	this.routerLevel.getPrompt().getIdentifier().setInterfaceCod(interfaceCod);
    }
    
    private void setInterfacePort(String port){
    	this.routerLevel.getPrompt().getIdentifier().setPort(port);
    }
    
    private void setInterfaceSubPort(String subPort){
    	this.routerLevel.getPrompt().getIdentifier().setSubPort(subPort);
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
