package main;

import unused.ConnectWindow;
import view.MainInterface;


public class HomeRouter {

	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run(){
				try {
                                    MainInterface main = new MainInterface();
                                    main.startInterface();
                                    //new ConnectWindow();
				} finally{
					
				}
			}
		}.start();
	}
}