package com.esw.cmykw;

import java.util.Random;

import com.esw.Meta;

public class Array {
	boolean occupied;
	Gem[][] gemArray;

	public Array() {
		Random rand = new Random();
		gemArray = new Gem[5][5];
		for(int x = 0; x < gemArray.length; x++) {
			for(int y = 0; y < gemArray[x].length; y++) {
				if(rand.nextInt(2) == 1)
					gemArray[x][y] = new Gem(getRandomColor(), x, y);
				else 
					gemArray[x][y] = new Gem();
			}
		}
	}

	public String getRandomColor() {
		String[] colors = new String[5];
		colors[0] = "cyan";
		colors[1] = "magenta";
		colors[2] = "yellow";
		colors[3] = "black";
		colors[4] = "white";
		Random rand = new Random();
		return colors[rand.nextInt(5)];
	}

	public void drop() {
		
	}

	// NOTE This code is actually backwards because of the fact that the origin is
	// in the bottom left instead of the top left
	// To use this function "normally" it should be rotateRight() [clockwise]
	public void rotateLeft() {
		int n = gemArray.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				Gem tmp = gemArray[i][j];
				gemArray[i][j] = gemArray[n - j - 1][i];
				gemArray[n - j - 1][i] = gemArray[n - i - 1][n - j - 1];
				gemArray[n - i - 1][n - j - 1] = gemArray[j][n - i - 1];
				gemArray[j][n - i - 1] = tmp;
			}
		}
	}

	// NOTE This code is actually backwards because of the fact that the origin is
	// in the bottom left instead of the top left
	// To use this function "normally" it should be rotateLeft() [counterclockwise]
	public void rotateRight() {
		int n = gemArray.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				Gem tmp = gemArray[i][j];
				gemArray[i][j] = gemArray[j][n - i - 1];
				gemArray[j][n - i - 1] = gemArray[n - i - 1][n - j - 1];
				gemArray[n - i - 1][n - j - 1] = gemArray[n - j - 1][i];
				gemArray[n - j - 1][i] = tmp;
			}
		}
	}

	public void printArray(String s) {
		Meta.println("------------- " + s + " --------------");
		for(int x = gemArray.length-1; x >= 0 ; x--) {
			for(int y = 0; y < gemArray[x].length; y++) {
				if(!gemArray[x][y].isNull()) {
					Meta.print("[" + gemArray[x][y].toString() + " " +  x + "," + y + "]");
				} else {
					Meta.print("[0 " + x + "," + y + "]");
				}
			}
			Meta.newline();
		}
		Meta.println("------------------------------------------------");
	}
	
	public void printArrayForwards(String s) {
		Meta.println("FORWARDS:------------- " + s + " --------------");
		for(int x = 0; x < gemArray.length; x++) {
			for(int y = 0; y < gemArray[x].length; y++) {
				if(!gemArray[x][y].isNull()) {
					Meta.print("[" + gemArray[x][y].toString() + " " +  x + "," + y + "]");
				} else {
					Meta.print("[0 " + x + "," + y + "]");
				}
			}
			Meta.newline();
		}
		Meta.println("------------------------------------------------");
	}

	public void printPosArray() {
		Meta.println("------------------------------------------------");
		for(int y = gemArray.length-1; y >= 0; y--) {
			for(int x = 0; x < gemArray[y].length; x++) {
				Meta.print("[" + x + "," + y + "]");
			}
			Meta.newline();
		}
		Meta.println("------------------------------------------------");
		Meta.newline();
	}
	
	public Gem getGem(int x, int y) {
		return gemArray[x][y];
	}
	
	public Gem[][] getArray() {
		return gemArray;
	}
}








