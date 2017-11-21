package hangManConsole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class game {
	
	private String mysteryWord = "";
	private String userWord = "";
	private String charUsed;
	private static int numTurn;
	private static int chanceLeft;
	private boolean isWon = false;
	
	public game() {
		mysteryWord = getRandomWord();
		
		//initialize userWord
		for(int i = 0; i < mysteryWord.length(); i++)
			userWord += "*";
		
		numTurn = 0;
		chanceLeft = 0;
		charUsed = "";
		isWon = false;
	}

	//helper method to count how many words in a text file
	private int getNumberWordFile() {
		
		int numberOfWords = 0;	
		BufferedReader br = null;
		
		try {
		 br = new BufferedReader(new FileReader("word.txt"));
         String line;
         while ((line = br.readLine()) != null) {
             numberOfWords++;
         }
         br.close();
		}
		catch(IOException e) {
			System.out.println("Could not open the file.");
		}      
		return numberOfWords;
	}
	
	// get a random word from a text file and use it as a guess word
	//@param (String): for user to choose which category they wish to play with
	public String getRandomWord() {
	
		String randomWord = "";
		//try to open a text
		//call number of words to get range for random number
		
		//user random to get a world
		Random rand = new Random();

		//50 is the maximum and the 1 is our minimum 
//		int  n = rand.nextInt(50) + 1;
		
		int  n = rand.nextInt(getNumberWordFile()) + 1;
		
		System.out.println("random number: " + n);
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader("word.txt"));
			
			String s;
			//read n-1 times and last iteration will be used to stored in randomWord
			for(int i = 1; i<n; i++) {
				s = br.readLine();
				
			}
			randomWord = br.readLine();
			
			br.close();
			
		}
		catch(IOException e) {
			System.out.println("Could not get a random words from file");
		}

		System.out.println(randomWord);
		
		//return a string of mystery word
		return randomWord;		
	}

	//check if a character contains in the randomWord
	public boolean ContainsWord(char userGuess) {
		
		//increment the number of turns
		numTurn++;
		String str1, str2;
		str1 = str2 = "";
		String temp = userWord;
		//loop through the randomWorld and check if it contains the character
		for(int i = 0; i<mysteryWord.length(); i++) {
			if(userGuess == (mysteryWord.toLowerCase()).charAt(i)) {
				
				//0 1 2 3 4 5
				//p e a n u t
				
				System.out.println(temp.length() + " " + mysteryWord.length());
				str1 += temp.substring(0, i) + userGuess;
			
				str2 += temp.substring(i, mysteryWord.length());
				userWord = str1 + str2; 
//				System.out.println(test);
//				System.out.println(temp.substring(i, mysteryWord.length()));
//				System.out.println(temp);
				return true;
			}
		}
		
//		Seed
//		Butter
//		sandwich
//		woman

		
		charUsed += userGuess;
		chanceLeft++;
		return false;
	}
	
	
	//check if player reach their maximum limit (7 chances)
	public boolean isDead() {
		
		if(chanceLeft == 7)
			return true;
		
		return false;
	}
	
	//check if user won the game or not
	public boolean getWin() {
		if(isWon || userWord == mysteryWord)
		return true;
		
		return false;
	}
	
	//check if user inputed the right answers
	//@param (String):
	public boolean checkAnswer(String userGuess) {
		
		//increment the number of turns
		numTurn++;
				
		//if the user guess is the same as getRandomWord then it is true
		if(mysteryWord.toLowerCase() == userGuess.toLowerCase()) {
			isWon = true;
  		return true;
		}
		
		chanceLeft++;
		return false;
	}
	
	//display the state of the game
	public void display() {
		
		System.out.println("========== #" + (numTurn+1) + " Current turn ==========");
			
		stateOfMan();
		
		if(numTurn >0)
		System.out.println("Below are the characted used\n"+charUsed);
				
	}
	
	public void stateOfMan() {
		String state1 = " ________";
		String state2 = "\n|";
		String state3 = "\n|";
		String state4 = "\n|";
		String state5 = "\n|"; 
		String state6 = "\n|";
		String state7 = "\n---------";
				
		System.out.println("you word you have guessed so far:\n" + userWord);
		
		
		System.out.println("Here's the state of your little man :O \n");
		switch(chanceLeft) {
		
//		    |
//		  [x.x]
//		   \|/
//		    |
//		   / \
		
		//rope
		case 1 : state1 += "\n|    | ";
		break;
		
		//head
		case 2 : state1 += "\n|    | ";state2 += "   [X]";
		break;
		
		//body
		case 3 : state1 += "\n|    | ";state2 += "   [X]"; state3 += "    | ";
		break;
		
		//arm1
		case 4 : state1 += "\n|    | ";state2 += "   [X]"; state3 += "   \\| "; 
		break;
		
		//arm2
		case 5 : state1 += "\n|    | ";state2 += "   [X]"; state3 += "   \\|/ "; 
		break;
		
		//leg1
		case 6 : state1 += "\n|    | ";state2 += "   [X]"; state3 += "   \\|/ "; state4 = "|  /"; 
		break;
		
		//leg2
		case 7 : System.out.println("Oh no he is dead! Prepare the funeral.");
			state1 += "\n|    | ";state2 += "   [X]"; state3 += "   \\|/ "; state4 = "\n|   / \\"; 
		
		}
		
		String finalState = state1 + state2 + state3 + state4 + state5 + state6 + state7;
		
		System.out.println(finalState);
	}
}