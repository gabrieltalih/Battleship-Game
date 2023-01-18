//Assignment 4
//Written By: Gabriel Talih 40181253
//For COMP 248 Practice Fall 2020

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
	
	//Declaring the 2D array used for this class
	//In addition, declaring int for printing later,
	//and initializing two array lists to be used later when checking where a coordinate fits index wise
	private GridPoint[][] GridBoard;
	private int i;
	private ArrayList<String> Rows = new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8"));
	private ArrayList<String> Columns = new ArrayList<>(Arrays.asList("A","B","C","D","E","F","G","H"));
	
	//Initializing a grid
	public Grid(int x,int y){
		
		GridBoard = new GridPoint[x][y];
		
		for (int i = 0; i < x; i++) {
			
			for (int j = 0; j < y; j++) {
				
				GridBoard[i][j] = new GridPoint();
			}
		}
	}
	
	//Two methods private methods used to read user/computer input for x and y coordinates, made not case sensitive
	private int getRow(String position) {return Rows.indexOf(position.substring(1));}
	
	private int getColumn(String position) {return Columns.indexOf(position.substring(0,1).toUpperCase());}
	
	//Making a random coordinate by retrieving a random index from the lists
	public String getRandomPoint() {return Columns.get((int)(Math.random()*GridBoard[0].length)) + Rows.get((int)(Math.random()*GridBoard.length));}
	
	//Instead of calling two methods, we're using the first two in here to compact calling a position
	public GridPoint getGridPoint(String position) {return GridBoard[getRow(position)][getColumn(position)];}
	
	//Check if index of coordinate for row and column is -1 in list, as if that's the case it's invalid
	public boolean isInvalid(String position) {return getRow(position)==-1 || getColumn(position)==-1;}
	
	//Check if position has ship or grenade
	public boolean isOccupied(String position) {return getGridPoint(position).isShip() || getGridPoint(position).isGrenade();}
	
	//Since use of code for displaying a ship or grenade in the show board methods is repeated,
	//method is created to reduce redundant repeated code
	private void showPoint(int a, int b) {
		
		if (GridBoard[a][b].isShip()) {
			if (GridBoard[a][b].isComputer())
				System.out.print(" S");
			else
				System.out.print(" s");
		}
		else{
			if (GridBoard[a][b].isComputer())
				System.out.print(" G");
			else
				System.out.print(" g");
		}
	}
	
	//Method to show current state of the board,
	//includes the corresponding letters and numbers for better readability for user
	public void showCurrentBoard() {
		
		System.out.print("\n           A B C D E F G H");
		
		for (int a = 0; a < GridBoard.length; a++) {
			
			i = a+1;
			System.out.print("\n         " + i);
			
			for (int b = 0; b < GridBoard[0].length; b++) {
				
				if (GridBoard[a][b].isHit()) {
					if (GridBoard[a][b].isShip() || GridBoard[a][b].isGrenade())
						showPoint(a,b);
					else
						System.out.print(" *");
				}
				else System.out.print(" _");
			}
		}
	}
	
	//Method to show final board with no hits but all ships and grenades,
	//includes the corresponding letters and numbers for better readability for user
	public void showFinalBoard() {
		
		System.out.print("\n           A B C D E F G H");
		
		for (int a = 0; a <	GridBoard.length; a++) {
			
			i = a+1;
			System.out.print("\n         " + i);
			
			for (int b = 0; b < GridBoard[0].length; b++) {
				
				if (GridBoard[a][b].isShip() || GridBoard[a][b].isGrenade())
					showPoint(a,b);
				else
					System.out.print(" _");
			}
		}
	}
}