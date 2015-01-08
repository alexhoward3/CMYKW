package com.esw.cmykw;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

		ColorPair[] colors = new ColorPair[5];
		colors[0] = new ColorPair("cyan", new Texture(Gdx.files.internal("images/cyan.png")));
		colors[1] = new ColorPair("magenta", new Texture(Gdx.files.internal("images/magenta.png")));
		colors[2] = new ColorPair("yellow", new Texture(Gdx.files.internal("images/yellow.png")));
		colors[3] = new ColorPair("black", new Texture(Gdx.files.internal("images/black.png")));
		colors[4] = new ColorPair( "white", new Texture(Gdx.files.internal("images/white.png")));

		Random rand = new Random();

		for(int y = 0; y < array.length; y++) {
			for(int x = 0; x < array[y].length; x++) {
				float xpos = getCenterX(x); //These need to be switched in order for the gems to print correctly
				float ypos = getCenterY(y); //These need to be switched in order for the gems to print correctly
				int r = rand.nextInt(2);
				if(r == 0)
					array[x][y] = new Gem(colors[rand.nextInt(5)], 25f, xpos, ypos);
				else 
					array[x][y] = new Gem();
			}
		}
	}

	public float getCenterX(int x) {
		float xpos = this.box.getBoundingRectangle().getX();
		switch(x) {
			case 0:
				xpos = xpos + additive(1);
				return xpos;
			case 1:
				xpos = xpos + additive(3);
				return xpos;
			case 2:
				xpos = xpos + additive(5);
				return xpos;
			case 3:
				xpos = xpos + additive(7);
				return xpos;
			case 4:
				xpos = xpos + additive(9);
				return xpos;
			default: 
				return 0.0f;
		}
	}

	public float getCenterY(int y) {
		float ypos = box.getBoundingRectangle().getY();
		switch(y) {
			case 0:
				ypos = ypos + additive(1);
				return ypos;
			case 1:
				ypos = ypos + additive(3);
				return ypos;
			case 2:
				ypos = ypos + additive(5);
				return ypos;
			case 3:
				ypos = ypos + additive(7);
				return ypos;
			case 4:
				ypos = ypos + additive(9);
				return ypos;
			default:
				return 0.0f;
		}
	}

	private float additive(int times) {
		return ((box.getWidth() * 0.1f) * times);
	}

	public void drop() {
		drop(dir);
	}

	private void drop(Direction direction) {
		boolean mark = true;
		String dir = direction.getDirection();
		do {
			if(dir.equals("s")) {
				mark = headSouth();
			} else if(dir.equals("e")) {
				mark = headEast();
			} else if(dir.equals("n")) {
				mark = headNorth();
			} else if(dir.equals("w")) {
				mark = headWest();
			}
		} while(mark);
	}


	public boolean headSouth() {
		boolean mark = true;
		for(int y = 1; y < array.length; y++) {
			for(int x = 0; x < array[y].length; x++) {
				if(!array[x][y].isNull()) {
					if(array[x][y-1].isNull()) {
						array[x][y-1] = new Gem(array[x][y], getCenterX(x), getCenterY(y-1));
						array[x][y] = new Gem();
						mark = true;
					} else {
						mark = false;
					}
				}
			}
		}
		
		//TODO! Check the columns!!! 
		return mark;
	}

	public boolean headEast() {
		boolean mark = true;
		for(int y = 0; y < array.length; y++) {
			for(int x = array[y].length-2; x >= 0; x--) {
				if(!array[x][y].isNull()) {
					if(array[x+1][y].isNull()) {
						array[x+1][y] = new Gem(array[x][y], getCenterX(x+1), getCenterY(y));
						array[x][y] = new Gem();
						mark = true;
					} else {
						mark = false;
					}
				}
			}
		}
		//TODO Check the columns!!!
		return mark;
	}

	public boolean headNorth() {
		boolean mark = true;
		for(int y = array.length-1; y >= 0; y--) {
			for(int x = array[y].length-1; x >= 0; x--) {
				if(!array[x][y].isNull()) {
					if(array[x][y+1].isNull()) {
						array[x][y+1] = new Gem(array[x][y], getCenterX(x), getCenterY(y+1));
						array[x][y] = new Gem();
						mark = true;
					} else {
						mark = false;
					}
				}
			}
		}
		//TODO Check the columns!!!
		return mark;
	}

	public boolean headWest() {
		boolean mark = true;
		for(int y = 0; y < array.length; y++) {
			for(int x = 1; x < array[y].length; x++) {
				if(!array[x][y].isNull()) {
					if(array[x-1][y].isNull()) {
						array[x-1][y] = new Gem(array[x][y], getCenterX(x-1), getCenterY(y));
						array[x][y] = new Gem();
						mark = true;
					} else {
						mark = false;
					}
				}
			}
		}
		//TODO Check the columns!!!
		return mark;
	}

	// NOTE THE ARRAY TRANSFORMATION CODE IS UNNECESSARY!!!
	public void rotateLeft() {
		dir.rotateRight();
//		int n = array.length;
//		for (int i = 0; i < n / 2; i++) {
//			for (int j = i; j < n - i - 1; j++) {
//				Gem tmp = array[i][j];
//				array[i][j] = array[j][n - i - 1];
//				array[j][n - i - 1] = array[n - i - 1][n - j - 1];
//				array[n - i - 1][n - j - 1] = array[n - j - 1][i];
//				array[n - j - 1][i] = tmp;
//			}
//		}
	}
	
	// NOTE THE ARRAY TRANSFORMATION CODE IS UNNECESSARY!!!
	public void rotateRight() {
		dir.rotateLeft();
//		int n = array.length;
//		for (int i = 0; i < n / 2; i++) {
//			for (int j = i; j < n - i - 1; j++) {
//				Gem tmp = array[i][j];
//				array[i][j] = array[n - j - 1][i];
//				array[n - j - 1][i] = array[n - i - 1][n - j - 1];
//				array[n - i - 1][n - j - 1] = array[j][n - i - 1];
//				array[j][n - i - 1] = tmp;
//			}
//		}
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














































