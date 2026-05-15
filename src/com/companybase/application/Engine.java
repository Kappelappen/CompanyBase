package com.companybase.application;

import com.companybase.database.Database;
import com.companybase.ui.windows.MainWindow;

public class Engine {

	public static void main(String[] args) {
		
		Engine.config();
		Engine.start();
		
	}
	
	private static void config() {
		
		Database db = new Database();
		db.doCheckDb();
		
	}
	
	private static void start() {
		
		MainWindow mWindow = new MainWindow("CompanyBase");
		mWindow.setVisible(true);
		
	}	
}
