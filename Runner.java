import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Runner {
	private static String[] HIDDEN_WORDLIST = {"EUPHORIA", "TRANQUILITY", "SERENITY", "MAJESTIC", "ENCHANTED", "RADIANCE"};
	private static ArrayList<String> characterGuesses = new ArrayList<>();
	private static String hiddenWord;
	private static String answer;
	private static char input;
	private static int guessCounter;
	
	
	public int getGuessCounter() {
		return guessCounter;
	}

	public static void setGuessCounter(int Counter) {
		guessCounter = Counter;
	}
	
	public static void setHiddenWord(String word) {
		hiddenWord = word;
	}
	
	public String getHiddenWord() {
		return hiddenWord;
	}
	
	public static void setAnswer(String word) {
		answer = word.replaceAll(".", "-");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create new instance of random number & select random word from String
		Random number = new Random();
		int limit = HIDDEN_WORDLIST.length;
		int random_number = number.nextInt(limit);
		//System.out.println(HIDDEN_WORDLIST[random_number]);
		setHiddenWord(HIDDEN_WORDLIST[random_number]);
		//System.out.println(hiddenWord);
		//Set guessCounter = 8 (Starting value)
		setGuessCounter(8);
		//System.out.println(guessCounter);
		
		//convert all the letters in the string to dash and save it under answer
		setAnswer(hiddenWord);
		//System.out.println(answer);
		
		//Print initial message
		System.out.println("Welcome to Hangman");
		System.out.println("The word now looks like this: "+answer);
		System.out.println("You have "+guessCounter+" guesses left.");
		
		//Creates scanner class for scanner
		Scanner scanner = new Scanner(System.in);
		//Checks if user-input is correctly transferred to input 
		//System.out.println(input);
		
		//
		while ( (guessCounter > 0) && (!(hiddenWord.equals(answer) ))) {
			input = InputCharacter.processInput(scanner);
			//Checks if user-input is correctly transferred to input variable 
			//System.out.println(input);
			String inputChar=Character.toString(input);
			
			//Checks if user has input the previous message or not. Print error message & stop this iteration of the while loop.
			if (characterGuesses.contains(inputChar.toUpperCase())) {
				System.out.println("Thank you, you have previously input this character. Please try again with another character");
				System.out.println("You still have "+guessCounter+" guesses left.");
				continue;
			}
			
			if (hiddenWord.contains(inputChar.toUpperCase())) {
				//Adds input to characterGuesses ArrayList for subsequent checking
				characterGuesses.add(inputChar.toUpperCase());
				//System.out.println(characterGuesses.toString());
				//Finds location where the input is present in the hiddenWord and adds it to 
				ArrayList<Integer> positions = new ArrayList<>();
				for (int i=0; i<hiddenWord.length(); i++) {
					if (hiddenWord.charAt(i) == inputChar.toUpperCase().charAt(0)) {
						positions.add(i);
					}
				}
				//System.out.println(positions.toString());
				//Replaces location where input is present in hiddenWord at answer variable
				StringBuilder updatedAnswer = new StringBuilder(answer);
				for (int position : positions) {
					updatedAnswer.setCharAt(position, hiddenWord.charAt(position));
				}	
					answer = updatedAnswer.toString();
					System.out.println(answer);
					
				if (hiddenWord.equals(answer)) {
					System.out.println("That guess is now correct");
					System.out.println("You guessed the word: "+answer);
					System.out.println("You win!");
					break;
				}	
				if (!hiddenWord.equals(answer)) {
				System.out.println("That guess is now correct");
				System.out.println("The word now looks like this: "+answer);
				System.out.println("You have "+guessCounter+" attempts left");
				continue;
				}
				
			} else {
				//Adds input to characterGuesses ArrayList for subsequent checking
				characterGuesses.add(inputChar.toUpperCase());
				guessCounter -=1;
				if (guessCounter>1) {
					System.out.println("There are no "+input+"'s in the word.");
					System.out.println("The word now looks like this: "+answer);
					System.out.println("You have "+guessCounter+" attempts left");
					continue;
				}
				else if (guessCounter ==1) {
					System.out.println("There are no "+input+"'s in the word.");
					System.out.println("The word now looks like this: "+answer);
					System.out.println("You have only "+guessCounter+" attempt left");
					continue;
				}
				else {
					System.out.println("There are no "+input+"'s in the word.");
					System.out.println("You're completely hung.");
					System.out.println("The word was: "+hiddenWord);
					System.out.println("You lose");
					System.out.println(characterGuesses.toString());
					break;
				}
			}
			
			
		}
		scanner.close();
	}

}
