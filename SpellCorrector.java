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
		
		File file = new File(dictionaryFileName);
	
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
		while(sc.hasNext()) {
			//while(i < 1) {
			theTrie.add(sc.next());
			i++;
		}
		
//		System.out.println("Word Count: " + theTrie.getWordCount());
//		System.out.println("Node Count: " + theTrie.getNodeCount());
//		System.out.println("HashCode: " + theTrie.hashCode());	
//		System.out.println(theTrie.toString());
		
	}

	public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {	
	
		if(inputWord != "") {
			String lowered = inputWord.toLowerCase();
			Node found = theTrie.find(lowered);
			if(found != null) {
				return lowered;
			}
			theTrie.runFunctions(lowered, theTrie.bigSet);
			if(theTrie.bestWord == "" && theTrie.runningFreq == 0) {
				theTrie.runFunctions(lowered, theTrie.finalSet);
			}
			else return theTrie.bestWord;
			
			if(theTrie.bestWord == "" || theTrie.runningFreq == 0) {
				throw new NoSimilarWordFoundException();
			}
			else {
				return theTrie.bestWord;
			}	
		}
		else {
			throw new NoSimilarWordFoundException();
		}
		
		
		
	}
	
}
