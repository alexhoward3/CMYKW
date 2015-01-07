package com.esw.cmykw;

import com.esw.Meta;

public class Test {
	public static void main(String[] args) {
		String[][] array = { { "A", "B", "C", "D", "E" },
				{ "F", "G", "H", "I", "J" }, { "K", "L", "M", "N", "O" },
				{ "P", "Q", "R", "S", "T" }, { "U", "V", "W", "X", "Y" } };

		printArray(array, "Start");
		rotateLeft(array);
		printArray(array, "Left");
		rotateRight(array);
		printArray(array, "Right");
	}

	public static void rotateLeft(String[][] a) {
		int n = a.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				String tmp = a[i][j];
				a[i][j] = a[j][n - i - 1];
				a[j][n - i - 1] = a[n - i - 1][n - j - 1];
				a[n - i - 1][n - j - 1] = a[n - j - 1][i];
				a[n - j - 1][i] = tmp;
			}
		}
	}

	public static void rotateRight(String[][] a) {
		int n = a.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = i; j < n - i - 1; j++) {
				String tmp = a[i][j];
				a[i][j] = a[n - j - 1][i];
				a[n - j - 1][i] = a[n - i - 1][n - j - 1];
				a[n - i - 1][n - j - 1] = a[j][n - i - 1];
				a[j][n - i - 1] = tmp;
			}
		}
	}

	public static void printArray(String[][] array, String s) {
		Meta.println("------------- " + s + " --------------");
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[x].length; y++) {
				Meta.print("[" + array[x][y] + "]");
			}
			Meta.newline();
		}
		Meta.println("------------------------------------------------");
	}
}



































