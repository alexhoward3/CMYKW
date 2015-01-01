package com.esw.cmykw;

public class Grid {
	GridSquare[][] grid;
	Board board;
	
	Grid(Board board) {
		this.board = board;
		grid = new GridSquare[5][5];
		create();
	}
	
	public void create() {
		
		for(int i = 0; i < 5; i++) {
			float x = board.getBoundingRectangle().getX();
			for(int j = 0; j < 5; j++) {
				float y = board.getBoundingRectangle().getY();
			}
		}
	}
	
	private float additive(int times) {
		return ((board.getDimension() * 0.1f) * times);
	}
	
	public void add(Gem gem, int x, int y) {
		grid[x][y].setGem(gem);
	}
}










