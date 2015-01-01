package com.esw.cmykw;

public class GridSquare {
	
	boolean occupied;
	Gem gem;
	Board board;
	int x, y;
	float originX, originY;
	
	public GridSquare() {
		this.occupied = false;
	}
	
	public GridSquare(Gem gem) {
		this.occupied = true;
	}
	
	public GridSquare(Gem gem, int x, int y, Board board) {
		this.occupied = true;
		this.gem = gem;
		this.gem.setCenter(x, y);
		this.x = x;
		this.y = y;
	}
	
	public void set() {
		this.occupied = true;
	}
	
	public void unset() {
		this.occupied = false;
	}
	
	public void setGem(Gem gem) {
		this.gem = gem;
	}
	
	public Gem getGem() {
		return this.gem;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}

	public void setOriginX(float x) {
		this.originX = x;
	}
	
	public float getOriginX() {
		return this.originX;
	}
	
	public void setOriginY(float y) {
		this.originY = y;
	}
	
	public float getOriginY() {
		return this.originY;
	}

}










