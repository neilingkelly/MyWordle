# MyWordle
This game mimics the popular word game Wordle.

The program selects a random 5 letter word from an included dictionary.
The player has 6 guesses to guess the correct word.
Each play, the program will tell you if you guessed any correct letters and if they are in the correct spot.
Letters in the correct spot are displayed in UPPERCASE.
Letters that appear in the word but are not yet in the correct spot are displayed in lowercase.
The program will also display the alphabet each turn, removing letters determined not to be in the word.
Use the new information after each turn to formulate a new guess.
If the guessed word is not in the dictionary it will not deduct a turn, but tell you to guess another word.
Game terminates after a correct guess or 6 incorrect guesses.
Happy playing!

# Code Techniques Implemented
Loops
Arrays
File Reading
Output Formatting

# Things I Learned
The most challenging part of creating this game was configuring the logic.
Many loops were required to cycle through looking for correct letters and correct placement.
Dealing with duplicate letters in guesses that only appear once in word and not flagging them both as in word.
