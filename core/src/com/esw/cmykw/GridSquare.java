package com.esw.cmykw;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GridSquare {

	private boolean occupied;
	private Sprite box;
	private Gem gem;
	private float centerX, centerY;

	/**
	 * 
	 * @param box 	The Sprite that is used as the backing board
	 * @param gem 	A singular gem held within a point in the box
	 * @param x 	The x position in the array that holds all the GridSquares
	 * @param y		The y position in the array that holds all the GridSquares
	 */
	GridSquare(Sprite box, Gem gem, int x, int y) {
		if(gem.color.equals("null"))
			this.occupied = false;
		else 
			this.occupied = true;
		this.box = box;
		this.gem = gem;
		this.centerX = setCenterX(x);
		this.centerY = setCenterY(y);

		this.gem.setCenter(centerX, centerY);
		this.gem.setOriginCenter();
	}

	public float setCenterX(int x) {
		float xpos = this.box.getBoundingRectangle().getX();
		switch(x) {
			case 0:
				xpos = xpos + additive(9);
				return xpos;
			case 1:
				xpos = xpos + additive(7);
				return xpos;
			case 2:
				xpos = xpos + additive(5);
				return xpos;
			case 3:
				xpos = xpos + additive(3);
				return xpos;
			case 4:
				xpos = xpos + additive(1);
				return xpos;
			default: 
				return 0.0f;
		}

	}

	public float setCenterY(int y) {
		float ypos = box.getBoundingRectangle().getY();
		switch(y) {
			case 0:
				ypos = ypos + additive(9);
				return ypos;
			case 1:
				ypos = ypos + additive(7);
				return ypos;
			case 2:
				ypos = ypos + additive(5);
				return ypos;
			case 3:
				ypos = ypos + additive(3);
				return ypos;
			case 4:
				ypos = ypos + additive(1);
				return ypos;
			default:
				return 0.0f;
		}
	}

	public float getCenterX() {
		return this.centerX;
	}

	public float getCenterY() {
		return this.centerY;
	}

	private float additive(int times) {
		return ((box.getWidth() * 0.1f) * times);
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setGem(Gem gem) {
		this.occupied = true;
		this.gem = gem;
		this.gem.setCenter(centerX, centerY);
		this.gem.setOriginCenter();
	}

	public Gem getGem() {
		if(this.occupied) return gem;
		return null;
	}

	public void removeGem() {
		this.occupied = false;
		this.gem = null;
	}

}










