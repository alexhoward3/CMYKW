package com.esw;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Meta {
	public static void blank() {
		System.out.print("");
	}
	
	public static void newline() {
		System.out.print("\n");
	}
	
	public static void print(String s) {
		System.out.print(s);
	}
	
	public static void print(int i) {
		System.out.print(i);
	}
	
	public static void print(boolean b) {
		System.out.print(b);
	}
	
	public static void println(String s) {
		System.out.println(s);
	}
	
	public static void println(int i) {
		System.out.println(i);
	}
	
	public static void println(boolean b) {
		System.out.println(b);
	}
	
	public static void debug() {
		System.out.println("DEBUG");
	}
	
	public static void debug(String s) {
		System.out.println("DEBUG : " + s);
	}
	
	public static void debug(int i) {
		System.out.println("DEBUG : " + i);
	}
	
	public static void debugln() {
		System.out.println("****************DEBUG****************");
	}
	
	public static void printList(ArrayList alist) {
		Meta.println("\n-----------------BEGIN ARRAYLIST-----------------------\n");
		if(!alist.isEmpty()) {
			for(int i = 0; i < alist.size(); i++) {
				Meta.println(alist.get(i).toString());
			}
		}
		Meta.println("\n------------------END  ARRAYLIST-----------------------\n");
	}

	public static void printFile(File f) {
		Meta.println("\n---------------BEGIN " + f.getName() + "---------------\n");
		try {
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				Meta.println(line);
			}
			scan.close();
		} catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
		Meta.println("------------------END " + f.getName() + "----------------\n");
	}
	
	public static String getValueOf(Object obj) {
		return obj.toString();
	}
}
