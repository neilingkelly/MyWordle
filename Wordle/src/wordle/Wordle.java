//Code by Kelly Neiling
//mimics the popular game wordle

package wordle;

import java.util.*;
import java.io.*;

public class Wordle 
{
	public static void main(String[] args) throws java.io.IOException
	{
		//declare variables
		String word, guess = "";
		boolean[] isInWord = new boolean[5];
		int guessNumber = 0, wordCount = 0, wordLength = 5;
		Scanner input = new Scanner(System.in);
		char[] wordArray = new char[wordLength]; //holds the correct word
		char[] guessArray = new char[wordLength]; //holds each guess
		char[] guessFeedback = {'_', '_', '_', '_', '_'}; //what we send back to user after each guess with correct letters filled in
		int[] freqWordArray = new int[wordLength];
		int[] freqGuessArray = new int[wordLength];
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		//word bank gets pulled from a text file and stored in the wordBank arraylist
		ArrayList<String> wordBank = new ArrayList<String>();
		File wordFile = new File("5LetterDict.txt");
		Scanner readWordFile = new Scanner(wordFile);
		while (readWordFile.hasNext())
		{
			wordBank.add(readWordFile.next());
			++wordCount;
		}
		readWordFile.close();
		
		//generate a random number to select a random word from word bank
		int rand = (int)(Math.random()*wordCount); //returns random int to correlate with a word bank entry
		word = wordBank.get(rand).toUpperCase(); 
		//word = "IMAGE"; //for debugging
		
		//turn word into an array of characters
		for (int i = 0; i < wordLength; ++i)
		{
			wordArray[i] = word.charAt(i);
		}
		
		//get frequency of each letter in word
		for (int i = 0; i < wordLength; ++i)
		{
			freqWordArray[i] = 1;
			for (int j = i + 1; j < wordLength; ++j)
			{
				if (wordArray[i] == wordArray[j])
				{
					freqWordArray[i]++;
				}
			}
		}
		
		//print game intro
		System.out.println("Guess a 5 letter word. If you get a correct letter in the correct spot,\nit will be " +
				"in all caps. If you guess a letter that appears in the word\nbut is in the wrong spot, " +
				"it will be expressed in lower case.\nYou have 6 guesses.\n");
		for (int i = 0; i < 5; ++i) 
		{
			System.out.print("_ ");
		}
		System.out.print("\t");
		for (int i = 0; i < 26; ++i) 
		{ 
			System.out.print(alphabet[i] + " "); 
		}
		 
		System.out.println();
		//System.out.println(word); //for debugging
		
		for (int n = 0; n < 6; ++n)
		{
			//reset isInWord array to all false defaults for every guess
			for (int y = 0; y < isInWord.length; ++y)
			{
				isInWord[y] = false;
			}
			guessNumber = n + 1;
			//get user guess
			System.out.print("\nGuess " + guessNumber + ": ");
			
			//save user guess into variable
			guess = input.nextLine().toUpperCase();
			
			//make sure user enters a five letter word
			String regex = "^[A-Za-z]{5}$";
			while (!(guess.matches(regex)))
			{
				System.out.println("\nPlease enter a 5 letter word!");
				System.out.print("Guess " + guessNumber + ": ");
				guess = input.nextLine().toUpperCase();
			}
			
			while (!wordBank.contains(guess.toLowerCase()))
			{
				System.out.println("\nThat word is not in our selected dictionary. Guess another word.\n");
				System.out.print("Guess " + guessNumber + ": ");
				guess = input.nextLine().toUpperCase();
			}
			
			//turn user guess into an array of characters
			for (int i = 0; i < wordLength; ++i)
			{
				guessArray[i] = guess.charAt(i);
			}
			
			//check frequency of each letter in guess array as it comes (freq thus far)
			for (int i = wordLength - 1; i > -1; --i)
			{
				freqGuessArray[i] = 1;
				for (int j = i - 1; j > -1; --j)
				{
					if (guessArray[i] == guessArray[j])
					{
						freqGuessArray[i]++;
					}
				}
			}
				
			for (int i = 0; i < wordLength; ++i) //loop thru guess array comparing it to each letter in word array 1:1
			{
				if (guessArray[i] == wordArray[i]) //if there's a match in the same index of both
				{
					guessFeedback[i] = guessArray[i]; //set that letter (as a capital letter) in the guess feedback word array
					//remove it if it was somewhere else temporarily (as lower case hint)	
					for (int j = i - 1; j > -1; --j) 
					{ 
						if (Character.toLowerCase(guessFeedback[i]) == guessFeedback[j]) 
						{ 
							guessFeedback[j] = '_';
						} 
					}
				}
				else //no matches in the same index
				{
					for (int j = 0; j < wordLength; ++j) //loop thru word array
					{
						//if it's found anywhere in the word and isn't already filled with an uppercase letter
						//and the frequencies allow it
						if (guessArray[i] == wordArray[j] && !Character.isUpperCase(guessFeedback[i]) && freqGuessArray[i] <= freqWordArray[j]) 
						{
							if (freqGuessArray[i] == freqWordArray[j])
							{
								for (int k = i - 1; k > -1; --k)
								{
									if ((guessFeedback[i] != '_') && (guessFeedback[i] == guessFeedback[k] || Character.toUpperCase(guessFeedback[i]) == guessFeedback[k]))
									{
										guessFeedback[i] = '_';
									}
									else
									{
										guessFeedback[i] = Character.toLowerCase(guessArray[i]); //set it as a lower case letter in guess feedback array
										j = wordLength; //end loop
									}
								}
								if (guessFeedback[i] == '_' && i == 0)
								{
									guessFeedback[i] = Character.toLowerCase(guessArray[i]); //set it as a lower case letter in guess feedback array
									j = wordLength; //end loop
								}
							}
						}
						else
						{
							guessFeedback[i] = '_';
						}
					}
				}
			}
			
			//track which letters in guess array are in word array
			for (int y = 0; y < wordLength; ++y) //loop thru guess array
			{
				for (int x = 0; x < wordLength; ++x) //loop thru word array
				{
					//if the letter is in the word more than or equal to the number of times it's in the guess
					if (wordArray[x] == guessArray[y] && freqWordArray[x] >= freqGuessArray[y]) 
					{
						isInWord[y] = true; //set boolean value of isInWord array
						x = wordLength; //exit loop
					}	
				}
			}
			
			for (int y = 0; y < wordLength; ++y) //loop thru isInWord array
			{
				if (isInWord[y] == false) //if that letter isn't in the word
				{
					for (int m = 0; m < 26; ++m) //loop thru alphabet
					{
						if (alphabet[m] == guessArray[y]) //find the right alphabet index
						{
							alphabet[m] = ' '; //set it to blank space
							m = 26; //exit loop
						}
					}	
				}
			}
			
			//print out the guess
			for (int i = 0; i < wordLength; ++i)
			{
				System.out.print(guessFeedback[i] + " ");
			}
			System.out.print("\t");
			for (int j = 0; j < 26; ++j)
			{
				System.out.print(alphabet[j] + " ");
			}
			System.out.println();
			
			//convert to string for comparison
			guess = String.valueOf(guessFeedback).toUpperCase();
			
			//if it matches
			if (guess.equals(word))
			{
				System.out.print("\nCongrats you guessed the word! " + word);
				n = 6; //end loop
			}
			
			//print correct word if it wasn't guessed in 6 guesses
			if ((guessNumber == 6) && !(guess.equals(word)))
			{
				System.out.print("Too many guesses! The correct word was " + word);
				System.out.println();
			}
		}
		
		input.close();
	}

}