/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import model.ClockModel;
import model.RunModel;

/**
 * 
 * @author JH
 */

// Classe: InformationHandler
// Esta classe sera responsaver por reunir e organizar informacoes recebidas
// pelo router
// comandos que iniciam (conhecidos):
// -! (como por exemplo o show run)
// -* (como por exemplo o clock)
// -Month (como Jun) (como por exemplo no clock)
// -% (como por exemplo em erros)
// ---More-- (como por exemplo no show run)(enviar:" ")

public class InformationHandler {// ---------------------------------------------------------------------------------------------------------------------
	private ClockModel clock;
	private RunModel run;
}
