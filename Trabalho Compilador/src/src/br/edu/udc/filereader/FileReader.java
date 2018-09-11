package br.edu.udc.filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	private String file = "expressao5.txt";
	
	public String getCode() throws FileNotFoundException{
		Scanner scan = new Scanner(new File(file));
		scan.useDelimiter("\0");
		return scan.next();
	}
}
