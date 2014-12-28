package com.esw.cmykw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Box extends Sprite {
	
	private float rotation;
	private boolean isRotating;
	public boolean isRotatingRight;
	public boolean isRotatingLeft;
	
	public Box() {
		super();
		rotation = super.getRotation();
	}
	
	public Box(Texture texture) {
		super(texture);
		rotation = super.getRotation();
	}
	
	@Override
	public void rotate(float degrees) {
		isRotating = true;
		super.rotate(degrees);
		rotation += degrees;
		if(rotation >= 90 || rotation <= -90) {
			resetRotation();
			isRotating = false;
		}
	}
	
	public void rotateLeft(float degrees) {
		isRotatingLeft = true;
		this.rotate(degrees);
		rotation += degrees;
		if(rotation >= 90 || rotation <= -90) {
			resetRotation(); 
			isRotatingLeft = false;
		}
	}
	
	public void rotateRight(float degrees) {
		isRotatingRight = true;
		this.rotate(degrees);
		rotation += degrees;
		if(rotation >= 90 || rotation <= -90) {
			resetRotation();
			isRotatingRight = false;
		}
	}
	
	@Override
	public float getRotation() {
		return rotation;
	}
	
	public void resetRotation() {
		rotation = 0;
	}
	
	public boolean isRotating() {
		return isRotating;
	}
}










