//Assignment 4
//Written By: Gabriel Talih 40181253
//For COMP 248 Practice Fall 2020

public class GridPoint {
	
	//Declaring boolean values to determine state of grid point
	private boolean ship;
	private boolean grenade;
	private boolean hit;
	private boolean computerowner;
	
	//Initialization for a grid point
	public GridPoint() {
		ship = false;
		grenade = false;
		hit = false;
		computerowner = false;
	}
	
	//Methods to player and computer ships and grenades, and hits
	public void placePlayerShip() {ship = true;}
	
	public void placePlayerGrenade() {grenade = true;}
	
	public void placeComputerShip() {
		ship = true;
		computerowner = true;
	}
	
	public void placeComputerGrenade() {
		grenade = true;
		computerowner = true;
	}
	
	public void placeHit() {hit = true;}
	
	//Methods to return states about the point
	public boolean isShip() {return ship;}
	
	public boolean isGrenade() {return grenade;}
	
	public boolean isHit() {return hit;}
	
	public boolean isComputer() {return computerowner;}
}