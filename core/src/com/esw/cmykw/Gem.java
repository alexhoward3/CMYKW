package com.esw.cmykw;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Gem extends Sprite {
	private boolean isNull;
	ColorPair colorPair;
	float size, x, y;

	Gem() {
		isNull = true;
		colorPair = new ColorPair("0", null);
	}

	Gem(ColorPair colorPair, float size, float x, float y) {
		this.isNull = false;
		this.colorPair = colorPair;
		this.size = size;
		this.x = x;
		this.y = y;
		Sprite sprite = new Sprite(colorPair.texture);
		super.set(sprite);
		super.setSize(size, size);
		super.setCenter(x, y);
	}
	
	Gem(Gem gem, float x, float y) {
		this(gem.colorPair, gem.size, x, y);
	}

	public void setNull(boolean b) {
		this.isNull = b;
	}

	public boolean isNull() {
		return isNull;
	}

	@Override
	public String toString() {
		switch(colorPair.string) {
			case "cyan":
				return "C";
			case "magenta":
				return "M";
			case "yellow":
				return "Y";
			case "black":
				return "B";
			case "white":
				return "W";
			default:
				return "0";
		}
	}
}
