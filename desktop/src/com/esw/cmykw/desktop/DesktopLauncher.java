package com.esw.cmykw.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.esw.Meta;
import com.esw.cmykw.CMYKW;

@SuppressWarnings("unused")
public class DesktopLauncher {
	
	private static short AA = 8;
	private static String resolution;
	
	//ARGUMENT STRING: [fullscreen] [vsync] [MSAA samples] [resolution]
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		if(arg.length < 4) {
			Meta.println("Fatal Error: Not enough arguments");
			System.exit(1);
		}
		
		for(int i = 0; i < arg.length; i++) {
			Meta.println(arg[i]);
		}
		
		if(Integer.parseInt(arg[1]) == 1) {
			config.vSyncEnabled = true; 	//Enable vertical sync
		} else {
			config.vSyncEnabled = false;
		}
		
		config.samples = Integer.parseInt(arg[2]); 			//(NOTE: Alex) MSAA settings /*MAY HAVE TO CHANGE*/
		
		config.x = -1; 					//Center window x
		config.y= -1; 					//Center window y
		
		resolution = arg[3];
		switch(resolution) {
		
		case "2560x1600": 	config.width = 2560;	//Standard 16 x 10 resolution
							config.height = 1600;	//Standard 16 x 10 resolution
							break;
		
		case "1680x1050": 	config.width = 1680;	//Standard 16 x 10 resolution
							config.height = 1050;	//Standard 16 x 10 resolution
							break;
				
		case "1920x1080": 	config.width = 1920;	//Standard 16 x 9 resolution		
							config.height = 1080;	//Standard 16 x 9 resolution
							break;
		
		case "1280x720": 	config.width = 1280;	//Standard 16 x 9 resolution
							config.height = 720;	//Standard 16 x 9 resolution
							break;
		
		case "1600x900":	config.width = 1600; 	//Standard 16 x 9 resolution
							config.height = 900; 	//Standard 16 x 9 resolution
							break;
		
		case "800x450": 	config.width = 800;		//Non-Standard 16 x 9 resolution
							config.height = 450;	//Non-Standard 16 x 9 resolution
							break;
		
		case "400x225": 	config.width = 400;		//Non-Standard 16 x 9 resolution
							config.height = 225;	//Non-Standard 16 x 9 resolution
							break;
				
		default: 	config.width = 1600;
					config.height = 900;
		}
		
		//TODO (Alex) Fix fullscreen
//		if(Integer.parseInt(arg[0]) == 1) {
//			config.fullscreen = true; 	//PLEASE DO NOT USE YET!!!!
//		} else {
//			config.fullscreen = false;
//		}
		
		config.title = "CMYKW"; 		//Title
		new LwjglApplication(new CMYKW(config.width, config.height), config);
	}
}
