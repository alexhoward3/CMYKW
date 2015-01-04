package com.esw.cmykw;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esw.Meta;

public class Gem extends Sprite {

	String color;
	
	public Gem() {
		super();
		super.setTexture(setRandomTexture());
	}

	public Gem(Texture texture) {
		super(texture);
	}

	public Gem(Texture texture, String color) {
		this.color = color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	private Texture setRandomTexture() {
		Random random = new Random();
		int color = random.nextInt(5);
		Texture texture;
		
		switch(color) {
			case 0:	{
				texture = new Texture(Gdx.files.internal("images/cyan.png"));
				this.setTexture(texture);
				this.color = "cyan";
				return texture;
			}
			case 1: {
				texture = new Texture(Gdx.files.internal("images/magenta.png"));
				this.setTexture(texture);
				this.color = "magenta";
				return texture;
			}
			case 2: {
				texture = new Texture(Gdx.files.internal("images/yellow.png"));
				this.setTexture(texture);
				this.color = "yellow";
				return texture;
			}
			case 3: {
				texture = new Texture(Gdx.files.internal("images/black.png"));
				this.setTexture(texture);
				this.color = "black";
				return texture;
			}
			case 4: {
				texture = new Texture(Gdx.files.internal("images/white.png"));
				this.setTexture(texture);
				this.color = "white";
				return texture;
			}
			default: {
				Meta.println("Could not create a random color! Something broke horribly!");
				this.color = null;
				return null;
			}
		}
	}

	@Override
	public String toString() {
		switch(color) {
			case "cyan":
				return "[C]";
			case "magenta":
				return "[M]";
			case "yellow":
				return "[Y]";
			case "black":
				return "[K]";
			case "white":
				return "[W]";
			default: 
				return "[0]";
		}
	}

}
