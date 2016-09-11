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

			System.out.println(SHORT_NAMES);
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
			long time1 = c.getTimeInMillis();
			int correct = 0; 
			// play game
			while (time1 < (time1 += 1000 * 31)) 
			{
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
			System.out.println("Time up! Number of correct responses: " + correct);
		}
}
	
	
	
			
			
		 

		

		
	

