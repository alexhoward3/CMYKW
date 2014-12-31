package com.esw.cmykw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esw.Meta;

/**
 * 
 * @author Alex
 * A Box is always square
 */
@SuppressWarnings("unused")
public class Box extends Sprite {
	
	private float rotation, dimension;
	private boolean isRotating;
	private Texture texture;
	
	public boolean rotateRight, rotateLeft;
	
	public Box(Texture texture) {
		super(texture);
		this.texture = texture;
		this.rotation = super.getRotation();
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public Box(Texture texture, float dimension) {
		super(texture);
		super.setSize(dimension, dimension);
		this.texture = texture;
		this.rotation = super.getRotation();
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public void setSize(float dimension) {
		super.setSize(dimension, dimension);
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public float getSize() {
		if(this.getHeight() != this.getWidth()) {
			Meta.println("Something went seriously wrong!\n"
					+ "Box dimensions are not the same: " + 
					this.getHeight() + "x" + this.getHeight());
			return 0.0f;
		}
		return dimension;
	}
	
	public void rotateLeft(float degrees) {
		rotateLeft = true;
		isRotating = true;
		super.rotate(degrees);
		rotation += degrees;
		//Meta.println("Left rotation: " + rotation); //DEBUG
		if(rotation >= 90 || rotation <= -90) {
			resetRotation(); 
			rotateLeft = false;
			isRotating = false;
		}
	}
	
	public void rotateRight(float degrees) {
		rotateRight = true;
		isRotating = true;
		super.rotate(degrees);
		rotation += degrees;
		//Meta.println("Right rotation: " + rotation); //DEBUG
		if(rotation >= 90 || rotation <= -90) {
			resetRotation();
			rotateRight = false;
			isRotating = false;
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










