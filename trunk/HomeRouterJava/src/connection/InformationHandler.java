/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

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
// -Month (como Jun) (como por exemplo no clock)
// -% (como por exemplo em erros)
// ---More-- (como por exemplo no show run)(enviar:" ")(quando o metodo receber isso ele ira enviar um espaco e chamara a si mesmo)

public class InformationHandler {// ---------------------------------------------------------------------------------------------------------------------
	private ClockModel clock;
	private RunModel run;
	private ConnectionHandler connection;
        private GUISolutionModel GuiSol;
	
	public InformationHandler(ConnectionHandler connection,GUISolutionModel GuiSol){
                this.GuiSol=GuiSol;
		this.connection=connection;
	}
}
