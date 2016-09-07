package dna;

import java.util.Random;

public class Lab2 {

	public static void main(String[] args) {
		
		// run loop from 1 to 1000
		
		int z = 0;
		
		while ( z < 1000 ) {
			z++;
		
		// run from 1 to 3 for 3mer
			
			int y = 0;
			
			while ( y < 3 ) {
				y++;
		
				Random random = new Random();
		
				int x=random.nextInt(4);
				
				// change int to characters
				
				String m = new String();
		
				if( x == 0 )
				{
					m=("A");
				}
				else if( x == 1 )
				{
					m=("C");
				}
				else if( x == 2 )
				{
					m=("T");
				}
				else if( x == 3 )
				{
					m=("G");
				}
				
				// print 3mer
				
				System.out.print(m);		
			}	
			
			// line spacing
			
			System.out.println("");
		}
	}
}



		
		
		

	


