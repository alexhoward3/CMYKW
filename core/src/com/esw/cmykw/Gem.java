package com.esw.cmykw;

import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * @author Alex
 * A Gem must be a square!!!
 */
public class Gem extends Box {
	String color;
	float dimension;
	float originX, originY;
	float board;
	
	public Gem(Texture texture, Board board) {
		super(texture);
		this.originX = board.getOriginX();
		this.originY = board.getOriginX();
	}
	
	public Gem(Texture texture, float dimension) {
		super(texture, dimension);
		this.dimension = dimension;
	}
	
	public Gem(Texture texture, float dimension, String color) {
		super(texture);
		this.dimension = dimension;
		this.color = color;
	}
	
	public void setColor(String s) {
		color = s;
	}
	
	public String color() {
		return color;
	}
	
	
}










