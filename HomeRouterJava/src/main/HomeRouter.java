package main;

import view.MainView;



public class HomeRouter {

	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run(){
				try {
					new MainView();
				} finally{
					
				}
			}
		}.start();
	}
}