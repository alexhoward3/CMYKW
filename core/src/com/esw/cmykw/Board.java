package com.esw.cmykw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
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
	}

	public Board(Texture texture, float dimension) {
		super(texture);
		super.setSize(dimension, dimension);
		this.dimension = dimension;
		super.setOrigin(this.getWidth()/2, this.getHeight()/2);
		this.rotation = super.getRotation();
	}

	public void create(Rectangle bounds) {
		Meta.println("" + bounds.getX() + " " + bounds.getY());
		Texture texture = new Texture(Gdx.files.internal("images/placeholder.png"));
		
		for(int i = 0; i < gemArray.length; i++) {
			
			float x = bounds.getX();
			float y = bounds.getY();
			
			gemArray[i] = new Gem(texture, 70f);
			gemArray[i].setOrigin(this.getOriginX(), this.getOriginY());
			switch(i) {
			case 0: 
				x = x + additive(1);
				y = y + additive(1);
				gemArray[i].setCenter(x, y);
				break;
			case 1:
				x = x + additive(1);
				y = y + additive(3);
				gemArray[i].setCenter(x, y);
				break;
			case 2:
				x = x + additive(1);
				y = y + additive(5);
				gemArray[i].setCenter(x, y);
				break;
			case 3:
				x = x + additive(1);
				y = y + additive(7);
				gemArray[i].setCenter(x, y);
				break;
			case 4:
				x = x + additive(1);
				y = y + additive(9);
				gemArray[i].setCenter(x, y);
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
			case 20:
			case 21:
			case 22:
			case 23:
			case 24:
			}
		}
	}
	
	public float additive(int times) {
		return ((this.dimension * 0.10f) * times);
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

	public void rotateLeft() {
		rotatingLeft = true;
		super.rotate(ROT_CON);
		this.rotation += ROT_CON;
		if(this.rotation >= 90 || this.rotation <= -90) {
			this.rotation = 0;
			rotatingLeft = false;
		}
	}

	public void rotateRight() {
		rotatingRight = true;
		super.rotate(-ROT_CON);
		this.rotation += ROT_CON;
		if(this.rotation >= 90 || this.rotation <= -90) {
			this.rotation = 0;
			rotatingRight = false;
		}
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










