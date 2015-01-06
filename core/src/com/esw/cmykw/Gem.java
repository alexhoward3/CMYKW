package com.esw.cmykw;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esw.Meta;

public class Gem extends Sprite {

	String color;
	boolean isNull;
	
	public Gem() {
		this.color = "null";
		this.isNull = true;
	}
	
	public Gem(String color, int x, int y) {
		this.color = color;
		this.isNull = false;
		this.setCenter(x, y);
	}
	
	public Gem(int color) {
		switch(color) {
			case 0:
				this.color = "cyan";
				break;
			case 1:
				this.color = "magenta";
				break;
			case 2: 
				this.color = "yellow";
				break;
			case 3:
				this.color = "black";
				break;
			case 4:
				this.color = "white";
				break;
			default: 
				this.color = "WTF";
		}
		Meta.println(this.color);
		this.isNull = false;
	}

	public void setColor(String color) {
		this.color = color;
		this.isNull = false;
	}

	@Override
	public String toString() {
		switch(color) {
			case "cyan":
				return "C";
			case "magenta":
				return "M";
			case "yellow":
				return "Y";
			case "black":
				return "K";
			case "white":
				return "W";
			default: 
				return "0";
		}
	}
	
	public boolean isNull() {
		return isNull;
	}

}










