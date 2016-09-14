package Lab3;

import java.util.Calendar;
import java.util.Random;

	public class aa_quiz {
		
		public static void main(String[] args) {
		
			Random random = new Random();
			
			// list of amino acid abbreviations
			String[] SHORT_NAMES = 
			{ 
				"A","R", "N", "D", "C", "Q", "E", 
				"G",  "H", "I", "L", "K", "M", "F", 
				"P", "S", "T", "W", "Y", "V" 
			};

			// list of amino acids
			String[] FULL_NAMES = 
			{
				"alanine", "arginine", "asparagine", 
				"aspartic acid", "cysteine",
				"glutamine",  "glutamic acid",
				"glycine" ,"histidine","isoleucine",
				"leucine",  "lysine", "methionine", 
				"phenylalanine", "proline", 
				"serine","threonine","tryptophan", 
				"tyrosine", "valine"
			};
		
			// find out how many amino acids are in the list
			int listLength = SHORT_NAMES.length;
	
			// get time and add 30 seconds
			Calendar c = Calendar.getInstance();
			long time = c.getTimeInMillis();
			int correct = 0; 
			// play game
			while ((time * 1000) < ((time * 1000) + (1000 * 31))) 
			{
				// choose random amino acid and ask and recieve answer
				int aanum = random.nextInt(listLength);
				String ans = FULL_NAMES[aanum];
				System.out.println("What is the one letter abbreviation for " + ans + " ?");
				String GUESS = System.console().readLine().toUpperCase();
				if ( GUESS == SHORT_NAMES[aanum] ) {
					correct++;
				} else {
					System.out.println("Wrong! Game over!");
					break;
					};
			}
			System.out.println("Number of correct responses: " + correct);
		}
}
	
