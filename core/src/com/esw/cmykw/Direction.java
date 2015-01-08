package com.esw.cmykw;

public class Direction {
	String[] dir;
	static int counter;

	Direction() {
		counter = 0;
		dir = new String[4];
		dir[0] = "s";
		dir[1] = "e";
		dir[2] = "n";
		dir[3] = "w";
	}

	String getDirection() {
		return dir[counter];
	}
	
	void rotateLeft() {
		if(counter == 3)
			counter = 0;
		else
			counter++;
	}

	void rotateRight() {
		if(counter == 0)
			counter = 3;
		else
			counter--;
	}

}
