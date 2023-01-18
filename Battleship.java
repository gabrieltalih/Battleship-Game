//Assignment 4
//Written By: Gabriel Talih 40181253
//For COMP 248 Practice Fall 2020

import java.util.*;

public class Battleship {	
	
	public static void main(String[] args) {
		
		//Declaring position string to store user and computer input for position
		//Initializing variables used later in checks for when the game runs
		String position;
		int humanshipsalive = 6;
		int computershipsalive = 6;
		boolean turn = true;
		boolean skiphuman = false;
		boolean skipcomputer = false;
		boolean gameover = false;
		
		//Initializing grid
		Grid Board = new Grid(8,8);
		
		System.out.println("Hi, let's play Battleship!\n");
		System.out.println("Make sure to type in you coordinates in the form X#, where X is a letter");
		System.out.println("from A to H inclusive, while # is a number from 1 to 8 inclusive.\n");
		
		//For this for loop, when k = 0, user inputs and places items, when k = 1, computer inputs and places items
		for (int k = 0; k <= 1; k++) {
			
			//For this first nested for loop, used to change the amount the next nested for loop is run
			//since when l = 0, it is run 6 times for the 6 ships, while if k = 1,it is run 4 times for the 4 grenades
			for (int l = 0; l <= 1; l++) {
				
				//Nested loop ran to input coordinates of ships and grenades, includes if statements based on the the other two for loops
				//depending on whether a computer or person is inputting ships or grenades
				for (int m = 1; m <= 6 - 2*l; m++) {
					
					//Do while loop to check if user/computer input is correct, displays error message and asked to input again if user input is wrong
					do {
						
						if(k == 0) {
							
							//Asks user to input coordinates of ship or grenade
							if(l == 0)
								System.out.print("Enter the coordinates of your ship #" + m + ": ");
							else
								System.out.print("Enter the coordinates of your grenade #" + m + ": ");
							
							Scanner pos= new Scanner(System.in);
							position = pos.nextLine();
							
							if(Board.isInvalid(position))
								System.out.println("Sorry, coordinates are invalid, try again.");
							else if (Board.isOccupied(position))
								System.out.println("Sorry, coordinates already used, try again.");
						}
						//Computer inputs random coordinate
						else 
							position = Board.getRandomPoint();
						
					}	while (Board.isInvalid(position) || Board.isOccupied(position));
					
					//Places ship or grenade
					if (k == 0) {
						if(l == 0)
							Board.getGridPoint(position).placePlayerShip();
						else
							Board.getGridPoint(position).placePlayerGrenade();
					}
					else {
						if(l == 0)
							Board.getGridPoint(position).placeComputerShip();
						else
							Board.getGridPoint(position).placeComputerGrenade();
					}
				}
				
				if (k == 0)
					System.out.println();
			}
		}
		
		System.out.println("Ok, the computer has randomly placed his ships and grenades. Let's play!\n\n");
		
		//Do while loop for checking when game is over
		do{
			
			//When turn variable is true, it is user turn, otherwise it is computer turn
			if(turn) {
				
				//Do while loop to check if rocket position is on the board (doesn't matter if it is repeated)
				do {
					
					System.out.print("Position of your rocket: ");
					Scanner pos= new Scanner(System.in);
					position = pos.nextLine();
					
					if(Board.isInvalid(position)) 
						System.out.println("Sorry, coordinates are invalid, try again.");
					
				}	while(Board.isInvalid(position));
			}
			else {
				position = Board.getRandomPoint();
				System.out.println("Position of my rocket: "+ position);
			}
			
			//Checks position is already hit
			if(!Board.getGridPoint(position).isHit()) {
				
				Board.getGridPoint(position).placeHit();
				
				//If ship hit, running count of user/computer ship is decreased
				if(Board.getGridPoint(position).isShip()) {
					
					System.out.print("ship hit.");
					
					if(Board.getGridPoint(position).isComputer())
						computershipsalive--;
					else
						humanshipsalive--;
				}
				//If grenade hit, respective skip turn is set true depending on who shot
				else if (Board.getGridPoint(position).isGrenade()) {
					
					System.out.print("boom! grenade.");
					
					if (turn)
						skiphuman = true;
					else
						skipcomputer = true;
				}
				//Otherwise nothing happens
				else
					System.out.print("nothing.");
			}
			//If position is already hit, it is stated to user
			else 
				System.out.println("position already called.");
			
			//checks if game is still playing
			if (humanshipsalive != 0 && computershipsalive != 0) {
				
				System.out.println("\n");
				Board.showCurrentBoard();
				System.out.println("\n\n");
				
				//checks if skip turn is on and player with skip turn is playing, and if both are happening, player gets a second turn
				//Otherwise turn is switched
				if (skiphuman && !turn)
					skiphuman = false;
				else if (skipcomputer && turn)
					skipcomputer = false;
				else
					turn = !turn;
			}
			//Endgame condition if human wins
			else if (computershipsalive == 0) {
				
				gameover = true;
				System.out.println(" You win!\n");
				Board.showFinalBoard();
			}
			//Endgame condition if computer wins
			else{
				
				gameover = true;
				System.out.println(" You lose!\n");
				Board.showFinalBoard();
			}
			
		}	while(!gameover);
	}
}