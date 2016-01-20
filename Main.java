package spell;

import java.io.IOException;

import spell.ISpellCorrector.NoSimilarWordFoundException;

/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 * @throws spell.SpellCorrector.NoSimilarWordFoundException 
	 */
	@SuppressWarnings("null")
	public static void main(String[] args) throws NoSimilarWordFoundException, IOException, spell.SpellCorrector.NoSimilarWordFoundException {
		
		String dictionaryFileName = args[0];
		String inputWord = args[1];

		/**
		 * Create an instance of your corrector here
		 */
		ISpellCorrector corrector = new SpellCorrector();
		
		corrector.useDictionary(dictionaryFileName);
		String suggestion = "";
		
		try {
			suggestion = corrector.suggestSimilarWord(inputWord);
		} catch (spell.ISpellCorrector.NoSimilarWordFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(suggestion);
	}

}
