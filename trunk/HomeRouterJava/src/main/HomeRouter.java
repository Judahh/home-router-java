package main;

import view.ConnectWindow;


public class HomeRouter {

	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run(){
				try {
					new ConnectWindow();
				} finally{
					
				}
			}
		}.start();
	}
}