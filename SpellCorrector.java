package spell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import spell.ISpellCorrector.NoSimilarWordFoundException;

public class SpellCorrector {

	// Store dictionary on the load
	
	Node root = new Node();
	
	
	
	public void readFile(String fileName) {
		
		File file = new File("src/editor/" + fileName);
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(file);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(fr);	
		Scanner sc = new Scanner(br);
		
		
	}
	
	@SuppressWarnings("serial")
	public static class NoSimilarWordFoundException extends Exception {
	
	}
	
	
	public void useDictionary(String dictionaryFileName) throws IOException {
		
		File file = new File("src/spell/" + dictionaryFileName);
		
		FileReader fr = null;
		
		try {
			fr = new FileReader(file);
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(fr);	
		Scanner sc = new Scanner(br);
		
		root.buildTree(sc.next(), 0, root);
		
		//System.out.println(sc.next());
		
		
	}
	
	public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
		
		return "Test";
	}
	
}
