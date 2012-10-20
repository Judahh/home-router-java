/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import connection.RouterHandler;

/**
 *
 * @author JH
 */
public class Init extends Thread{

    RouterHandler globalRouterHandler;
    
    public Init(RouterHandler globalRouterHandler) {
        this.globalRouterHandler=globalRouterHandler;
    }
    
    @Override
    public void run() {
        // Essas 6 linhas embaixo usam show controllers e show run
        //globalRouterHandler.getClock();
        globalRouterHandler.showRun();
    }
}
