package com.esw.cmykw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esw.Meta;

@SuppressWarnings("unused")
public class Box extends Sprite {
	
	private float rotation;
	private boolean isRotating;
	public boolean isRotatingRight;
	public boolean isRotatingLeft;
	private Texture texture;
	private String color;
	
	public Box() {
		super();
		rotation = super.getRotation();
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public Box(Texture texture) {
		super(texture);
		this.texture = texture;
		this.rotation = super.getRotation();
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public Box(Texture texture, String color) {
		super(texture);
		this.texture = texture;
		this.color = color;
		this.rotation = super.getRotation();
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public Box(Texture texture, float width, float height) {
		super(texture);
		super.setSize(width, height);
		this.texture = texture;
		this.rotation = super.getRotation();
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		this.setOrigin(this.getWidth()/2, this.getHeight()/2);
	}
	
	public void rotateLeft(float degrees) {
		isRotatingLeft = true;
		isRotating = true;
		super.rotate(degrees);
		rotation += degrees;
		//Meta.println("Left rotation: " + rotation); //DEBUG
		if(rotation >= 90 || rotation <= -90) {
			resetRotation(); 
			isRotatingLeft = false;
			isRotating = false;
		}
	}
	
	public void rotateRight(float degrees) {
		isRotatingRight = true;
		isRotating = true;
		super.rotate(degrees);
		rotation += degrees;
		//Meta.println("Right rotation: " + rotation); //DEBUG
		if(rotation >= 90 || rotation <= -90) {
			resetRotation();
			isRotatingRight = false;
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
	
	public void setColor(String s) {
		color = s;
	}
	
	public String color() {
		return color;
	}
}










