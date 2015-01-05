package com.esw.cmykw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esw.Meta;

public class Gem extends Sprite {

	String color;
	
	public Gem() {
		this.color = "null";
	}

	public Gem(Texture texture) {
		super(texture);
		this.color = "";
	}
	
	public Gem(Texture texture, float size) {
		super(texture);
		super.setSize(size, size);
		this.color = "";
	}

	public Gem(Texture texture, float size, String color) {
		super(texture);
		this.setSize(size, size);
		this.color = color;
	}
	
	public Gem(Texture texture, float size, int color) {
		super(texture);
		this.setSize(size, size);
		if(color == 0) {
			this.color = "cyan";
		} else if(color == 1) {
			this.color = "magenta";
		} else if(color == 2) {
			this.color = "yellow";
		} else if(color == 3) {
			this.color = "black";
		} else if(color ==4) {
			this.color = "white";
		} else {
			this.color = "WTF";
		}
		Meta.println(this.color);
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		switch(color) {
			case "cyan":
				return "[C]";
			case "magenta":
				return "[M]";
			case "yellow":
				return "[Y]";
			case "black":
				return "[K]";
			case "white":
				return "[W]";
			default: 
				return "[0]";
		}
	}

}
