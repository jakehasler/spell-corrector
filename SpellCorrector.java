package spell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import spell.ISpellCorrector.NoSimilarWordFoundException;

public class SpellCorrector  implements ISpellCorrector {

	// Store dictionary on the load
	private Trie theTrie = new Trie();
		
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
		sc.useDelimiter("[^a-zA-Z]+");
		
		int i = 0;
		while(i < 10) {
			theTrie.root.buildTree(sc.next(), 0, theTrie.root);
			i++;
		}
		
		Node project = theTrie.root.exists("project", 0, theTrie.root);
		System.out.println("project f: " + project.getCount());
		System.out.println("Word Count: " + theTrie.getWordCount());
		System.out.println("Node Count: " + theTrie.getNodeCount());
		System.out.println("HashCode: " + theTrie.hashCode());	
		
	}

	public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {	

		theTrie.runFunctions(inputWord, theTrie.bigSet);
		if(theTrie.bestWord == "" && theTrie.runningFreq == 0) {
			theTrie.runFunctions(inputWord, theTrie.finalSet);
		}
		
		
		if(theTrie.bestWord == "" && theTrie.runningFreq == 0) {
			return inputWord;
		}
		else {
			return theTrie.bestWord;
		}
		
		
	}
	
}
