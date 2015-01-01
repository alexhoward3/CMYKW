package com.esw.cmykw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * 
 * @author Alex
 * A Gem must be a square!!!
 */
public class Gem extends Sprite {
	String color;
	float dimension;
	float originX, originY;
	Board board;
	Texture texture;
	
	public Gem(Texture texture, float dimension) {
		super(texture);
		this.texture = texture;
		super.setSize(dimension, dimension);
		this.dimension = dimension;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void setOrigin(float originX, float originY) {
		this.originX = originX;
		this.originY = originY;
	}
	
	public void setColor(String s) {
		color = s;
	}
	
	public String color() {
		return color;
	}
	
	
}










