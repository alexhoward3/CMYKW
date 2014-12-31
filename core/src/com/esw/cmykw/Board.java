package com.esw.cmykw;

import com.badlogic.gdx.graphics.Texture;

/**
 * 
 * @author Alex
 * A Board must be a square!!!
 */
public class Board extends Box {
	private Gem[] gemArray;
	private float dimension;
	private float originX, originY;
	
	public Board(Texture texture, Gem[] array) {
		super(texture);
		gemArray = array;
		this.originX = super.getOriginX();
		this.originY = super.getOriginY();
	}
	
	public Board(Texture texture, float dimension, Gem[] array) {
		super(texture, dimension);
		this.dimension = dimension;
		gemArray = array;
	}
	
	public void setOrigin(float originX, float originY) {
		this.originX = originX;
		this.originY = originY;
	}
	
}










