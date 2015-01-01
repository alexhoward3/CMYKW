package com.esw.cmykw;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esw.Meta;

/**
 * 
 * @author Alex
 * A Board must be a square!!!
 */
public class Board extends Sprite {

	private final float ROT_CON = 10f;

	private Gem[] gemArray = new Gem[25];
	private byte count = 0;
	private float dimension, rotation;
	private boolean rotatingLeft, rotatingRight;
	Texture texture;

	public Board(Texture texture) {
		super(texture);
		this.texture = texture;
		super.setOrigin(this.getWidth()/2, this.getHeight()/2);
		this.rotation = super.getRotation();
		if(super.getWidth() == super.getHeight())
			this.dimension = super.getHeight();
	}

	public Board(Texture texture, float dimension) {
		super(texture);
		super.setSize(dimension, dimension);
		this.dimension = dimension;
		super.setOrigin(this.getWidth()/2, this.getHeight()/2);
		this.rotation = super.getRotation();
	}
	

	public float getDimension() {
		return dimension;
	}

	public boolean isRotating() {
		return rotatingRight || rotatingLeft;
	}

	public boolean isRotatingLeft() {
		return rotatingLeft;
	}

	public void rotatingLeft(boolean rotating) {
		rotatingLeft = rotating;
	}

	public boolean isRotatingRight() {
		return rotatingRight;
	}

	public void rotatingRight(boolean rotating) {
		rotatingRight = rotating;
	}

	public void resetRotation() {
		this.rotation = 0;
	}

	public void add(Gem gem) {
		gemArray[count] = gem;
		count++;
	}

	public void add(Gem gem, int x) {
		gemArray[count] = gem;
		count++;
	}

	public void remove(Gem gem) {
		gemArray[count] = gem;
		count--;
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
//		for(int i = 0; i < gemArray.length; i++) {
//			if(gemArray[i] != null) {
//				gemArray[i].draw(batch);
//			}
//		}
	}


}










