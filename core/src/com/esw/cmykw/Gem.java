package com.esw.cmykw;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Gem extends Sprite {
	private boolean isNull;
	ColorPair colorPair;
	float size, x, y;
	Sprite box;

	Gem() {
		isNull = true;
		colorPair = new ColorPair("0", null);
	}

	Gem(Sprite box, float size, int x, int y) {
		this.isNull = false;
		this.box = box;
		this.colorPair = new ColorPair(Colors.getRandomColor());
		this.size = size;
		this.x = x;
		this.y = y;
		Sprite sprite = new Sprite(colorPair.texture);
		super.set(sprite);
		super.setSize(size, size);
		super.setCenter(centerX(x), centerY(y));
	}
	
	Gem(Sprite box, Gem gem, float size, int x, int y) {
		this.isNull = false;
		this.box = box;
		this.colorPair = gem.colorPair;
		this.size = size;
		this.x = x;
		this.y = y;
		Sprite sprite = new Sprite(colorPair.texture);
		super.set(sprite);
		super.setSize(size, size);
		super.setCenter(centerX(x), centerY(y));
	}

	Gem(Sprite box, Gem gem, int x, int y) {
		this(box, gem, gem.size, x, y);
	}

	public void setNull(boolean b) {
		this.isNull = b;
	}

	public boolean isNull() {
		return isNull;
	}
	
	public float centerX(int x) {
		float xpos = this.box.getBoundingRectangle().getX();
		switch(x) {
			case 0:
				xpos = xpos + additive(1);
				return xpos;
			case 1:
				xpos = xpos + additive(3);
				return xpos;
			case 2:
				xpos = xpos + additive(5);
				return xpos;
			case 3:
				xpos = xpos + additive(7);
				return xpos;
			case 4:
				xpos = xpos + additive(9);
				return xpos;
			default: 
				return 0.0f;
		}
	}

	public float centerY(int y) {
		float ypos = box.getBoundingRectangle().getY();
		switch(y) {
			case 0:
				ypos = ypos + additive(1);
				return ypos;
			case 1:
				ypos = ypos + additive(3);
				return ypos;
			case 2:
				ypos = ypos + additive(5);
				return ypos;
			case 3:
				ypos = ypos + additive(7);
				return ypos;
			case 4:
				ypos = ypos + additive(9);
				return ypos;
			default:
				return 0.0f;
		}
	}

	private float additive(int times) {
		return ((box.getWidth() * 0.1f) * times);
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

class Colors {
	
	static ColorPair[] colors;

	static ColorPair getRandomColor() {
		Random rand = new Random();
		colors = new ColorPair[5];
		colors[0] = new ColorPair("cyan", new Texture(Gdx.files.internal("images/cyan.png")));
		colors[1] = new ColorPair("magenta", new Texture(Gdx.files.internal("images/magenta.png")));
		colors[2] = new ColorPair("yellow", new Texture(Gdx.files.internal("images/yellow.png")));
		colors[3] = new ColorPair("black", new Texture(Gdx.files.internal("images/black.png")));
		colors[4] = new ColorPair( "white", new Texture(Gdx.files.internal("images/white.png")));
		return colors[rand.nextInt(5)];
	}
}
















































