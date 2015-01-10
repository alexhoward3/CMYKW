package com.esw.cmykw;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esw.Meta;

public class Grid {
	Gem[][] array;
	Sprite box;
	Direction dir;

	Grid(Sprite box) {
		array = new Gem[5][5];
		this.box = box;
		dir = new Direction();

		Random rand = new Random();

		for(int y = 0; y < array.length; y++) {
			for(int x = 0; x < array[y].length; x++) {
				int r = rand.nextInt(2);
				if(r == 0)
					array[x][y] = new Gem(box, 70f, x, y);
				else 
					array[x][y] = new Gem();
			}
		}
	}

	public void drop() {
		drop(dir);
	}

	private void drop(Direction direction) {
		String dir = direction.getDirection();
		if(dir.equals("s")) {
			headSouth();
		} else if(dir.equals("e")) {
			headEast();
		} else if(dir.equals("n")) {
			headNorth();
		} else if(dir.equals("w")) {
			headWest();
		}
	}


	public void headSouth() {
		for(int x = 0; x < array.length;  x++) {
			for(int i = 1; i < array.length; i++) {
				int y = i;
				while(y > 0) {
					if(!array[x][y].isNull()) {
						if(array[x][y-1].isNull()) {
							array[x][y-1] = new Gem(box, array[x][y], x, y-1);
							array[x][y] = new Gem();
						}
					}
					y--;
				}
			}
		}
	}

	public void headEast() {
		for(int y = 0; y < array.length;  y++) {
			for(int i = array.length-2; i >= 0; i--) {
				int x = i;
				while(x < array.length-1) {
					if(!array[x][y].isNull()) {
						if(array[x+1][y].isNull()) {
							array[x+1][y] = new Gem(box, array[x][y], x+1, y);
							array[x][y] = new Gem();
						}
					}
					x++;
				}
			}
		}
	}

	public void headNorth() {
//		boolean mark = true;
//		for(int y = array.length-1; y >= 0; y--) {
//			for(int x = array[y].length-1; x >= 0; x--) {
//				if(!array[x][y].isNull()) {
//					if(array[x][y+1].isNull()) {
//						array[x][y+1] = new Gem(box, array[x][y], x, (y+1));
//						array[x][y] = new Gem();
//						mark = true;
//					} else {
//						mark = false;
//					}
//				}
//			}
//		}

		for(int x = 0; x < array.length;  x++) {
			for(int i = array.length-2; i >= 0; i--) {
				int y = i;
				while(y < array.length-1) {
					if(!array[x][y].isNull()) {
						if(array[x][y+1].isNull()) {
							array[x][y+1] = new Gem(box, array[x][y], x, y+1);
							array[x][y] = new Gem();
						}
					}
					y++;
				}
			}
		}
	}

	public void headWest() {
		for(int y = 0; y < array.length;  y++) {
			for(int i = 1; i < array.length; i++) {
				int x = i;
				while(x > 0) {
					if(!array[x][y].isNull()) {
						if(array[x-1][y].isNull()) {
							array[x-1][y] = new Gem(box, array[x][y], x-1, y);
							array[x][y] = new Gem();
						}
					}
					x--;
				}
			}
		}
	}

	public void rotateLeft() {
		dir.rotateRight();
	}


	public void rotateRight() {
		dir.rotateLeft();
	}

	public void printGrid(String s) {
		Meta.println("DIR: " + dir.getDirection());
		Meta.println("-------------- " + s + " ---------------");
		for(int y = array.length-1; y >= 0 ; y--) {
			for(int x = 0; x < array[y].length; x++) {
				Meta.print("[" + array[x][y].toString() + " " +  x + "," + y + "]");
			}
			Meta.newline();
		}
		Meta.println("------------------------------------");
	}

	public void printArrayForwards(String s) {
		Meta.println("FORWARDS:------------- " + s + " --------------");
		for(int x = 0; x < array.length; x++) {
			for(int y = 0; y < array[x].length; y++) {
				Meta.print("[" + array[x][y].toString() + " " +  x + "," + y + "]");
			}
			Meta.newline();
		}
		Meta.println("------------------------------------------------");
	}

	public void printPosArray() {
		Meta.println("------------------------------------------------");
		for(int y = array.length-1; y >= 0; y--) {
			for(int x = 0; x < array[y].length; x++) {
				Meta.print("[" + x + "," + y + "]");
			}
			Meta.newline();
		}
		Meta.println("------------------------------------------------");
		Meta.newline();
	}

	public void draw(SpriteBatch batch) {
		for(int x = 0; x < array.length; x++) {
			for(int y = 0; y < array.length; y++) {
				if(!array[x][y].isNull())
					array[x][y].draw(batch);
			}
		}
	}
}














































