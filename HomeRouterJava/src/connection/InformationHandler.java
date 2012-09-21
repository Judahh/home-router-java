/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.ArrayList;
import model.ClockModel;
import model.GUISolutionModel;
import model.RunModel;

/**
 *
 * @author JH
 */
// Classe: InformationHandler
// Esta classe sera responsaver por reunir e organizar informacoes recebidas
// pelo router
// Exemplo: quando o metodo checkLevel do LevelHandler encontrar um !,*,% ele chamara esta classe para cuidar disso
// e logo apos ele se chamara novamente
//
// Comandos que iniciam (conhecidos):
// -! (como por exemplo o show run)
// -* (como por exemplo o clock)
// -< (como por exemplo o ?)
// -Month (como Jun) (como por exemplo no clock)
// -% (como por exemplo em erros)
// ---More-- (como por exemplo no show run)(enviar:" ")(quando o metodo receber isso ele ira enviar um espaco e chamara a si mesmo)
public class InformationHandler {// ---------------------------------------------------------------------------------------------------------------------

    private ClockModel clock;
    private RunModel run;
    private ConnectionHandler connection;
    private GUISolutionModel GuiSol;

    public InformationHandler(String host, int port, GUISolutionModel GuiSol) throws ConnectException, SocketException, IOException {
        this.GuiSol = GuiSol;
        this.connection = new ConnectionHandler(host, port, GuiSol);
    }

    public ConnectionHandler getConnection() {
        return connection;
    }

    public GUISolutionModel getGUISol() {
        return GuiSol;
    }
    
    public void checkInfo(String FirstPartInfo){
        String[] possibilities=new String[getEndInfoPossibilities().size()];
        for(int i=0;i<getEndInfoPossibilities().size();i++){
            possibilities[i]=getEndInfoPossibilities().get(i);
        }
        
        ArrayList<String> InfoS=connection.arrayListReadUntil(possibilities);
        if(InfoS.get(0).equals("--More--")){
            connection.send(" ");
        }
    }
    
    private ArrayList<String> getEndInfoPossibilities() {
        ArrayList<String> possibilities = new ArrayList<>();
        possibilities.add("--More--");
        possibilities.add("Invalid input detected at '^' marker.");
        possibilities.add("end");

        return possibilities;
    }

    public ArrayList<String> getInfoPossibilities() {
        ArrayList<String> possibilities = new ArrayList<>();
        possibilities.add("!");
        possibilities.add("*");
        possibilities.add("<");
        possibilities.add("domain server (");
        possibilities.add("end");
        possibilities.add("%");
        possibilities.add("Jan");
        possibilities.add("Feb");
        possibilities.add("Mar");
        possibilities.add("Apr");
        possibilities.add("May");
        possibilities.add("Jun");
        possibilities.add("Jul");
        possibilities.add("Aug");
        possibilities.add("Sep");
        possibilities.add("Oct");
        possibilities.add("Nov");
        possibilities.add("Dec");

        return possibilities;
    }
}
