import java.util.Scanner;

public class InputCharacter {
	//This methods reads a character that the user inputs and prints it on the console
	
	public static char processInput(Scanner scanner) {
		System.out.println("Your guess: ");
		
		char inputChar = scanner.next().charAt(0);
		//System.out.println(inputChar);
		return inputChar;
	}
}
