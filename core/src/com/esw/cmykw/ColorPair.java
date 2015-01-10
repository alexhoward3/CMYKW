package com.esw.cmykw;

import com.badlogic.gdx.graphics.Texture;

public class ColorPair {
	String string;
	Texture texture;
	
	ColorPair(ColorPair colors) {
		this.string = colors.string;
		this.texture = colors.texture;
	}
	
	ColorPair(String string, Texture texture) {
		this.string = string;
		this.texture = texture;
	}
}
