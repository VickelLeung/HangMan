package hangManConsole;

import java.util.Scanner;

public class run {

	//loop the game
	public static void gameLoop(game g) {
		
		boolean isWin = false;
		
		while(!isWin) {
			
			if(g.isDead())
				break;
			
			g.display();
			
			Scanner kb = new Scanner(System.in);
			
			System.out.println("press 1 to choose one character or press 2 to guess the word");
			
			int v_choice;
			
			do {
				
				v_choice = kb.nextInt();
								
			}while(v_choice != 1 || v_choice != 2);
			
			if(v_choice == 1){
			
				System.out.println("Please enter one character to guess");
				String userChar = kb.next();
				userChar.toLowerCase();
				g.ContainsWord(userChar.charAt(0));
				
			}
			else if (v_choice == 2)
							
			//if won the game
			if(g.getWin()) {
				isWin = true;
				System.out.println("Congratulation you won the game.");
			}
		}		
	}
	
	public static void main(String[] args) {
		
		//for user category
//		System.out.println("Please choose a category of words you wish to play with from the list below");
//		System.out.println("Enter the number corresponding to the category. ie: '1' will selecte [Sports] ");
//		System.out.println("1) Sports"
//				+ "2) Pokemon"
//				+ "3) Food"
//				+ "3) meme");
//		Scanner kb = new Scanner(System.in);
//		String userInput = kb.next();
		
		game g = new game();
		
		gameLoop(g);			
	}		
}