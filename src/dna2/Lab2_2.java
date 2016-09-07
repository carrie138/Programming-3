package dna2;

import java.util.Random;

public class Lab2_2 {

	public static void main(String[] args) {
		
		// add a comment
		// run loop from 1 to 1000
		
		int z = 0;
		int a = 0;
		
		while ( z < 1000 ) {
			z++;
		
		// run from 1 to 3 for 3mer
			
			int y = 0;
			int count = 0;
			
			while ( y < 3 ) {
				y++;
				
				Random random = new Random();
		
				int x=random.nextInt(4);
				
				// change int to characters
				
				String m = new String();
		
				if( x == 0 )
				{
					m=("A");
					count++;
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
				if ( count == 3 ) {
					a = a + 1;
				}
				
			}	
			
			// line spacing
			
			System.out.println("");
			
		}
		System.out.println("Count of AAA : " + a);
	}
}




		
		
		

	


