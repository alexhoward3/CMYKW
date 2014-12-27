package com.esw.cmyk.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.esw.cmyk.CMYK;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true; 	//Enable vertical sync
		//config.fullscreen = true; 	//PLEASE DO NOT USE YET!!!!
		config.samples = 8; 			//(NOTE: Alex) MSAA settings /*MAY HAVE TO CHANGE*/ 
		config.x = -1; 					//Center window x
		config.y= -1; 					//Center window y
		config.width = 1600; 			//Standard 16 x 9 resolution //TODO (Alex) resolution options
		config.height = 900; 			//Standard 16 x 9 resolution
		config.title = "CMYKW"; 		//Title
		new LwjglApplication(new CMYK(), config);
	}
}
