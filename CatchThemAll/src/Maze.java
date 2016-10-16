package src;

import java.util.Collections;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;



public class Maze {

	/*
	 * The end point will always be set to be the bottom right corner of the maze.
	 * The coordinates of this point will always be maze[n-1][m-1] where n and m
	 * define the size of the maze in the x and y directions respectively.
	 */
	
	private int x;
	private int y;
	private Cell[][] maze;
	//	used for testing, Have relatively small maze
	private static int sizeLimit = 5;
	private int startPointX;
	private int startPointY;
	private int numPokemons;
	private int numCells;
	private int endPointX;
	private int endPointY;
	private int hatchTime;
	
	public Maze() {
		int x = ThreadLocalRandom.current().nextInt(2, sizeLimit + 1);
		int y = ThreadLocalRandom.current().nextInt(2, sizeLimit + 1);
		this.x = x;
		this.y = y;
		this.hatchTime = ThreadLocalRandom.current().nextInt(1, sizeLimit*2 + 1);
		this.startPointX = ThreadLocalRandom.current().nextInt(0, this.x);
		if(this.startPointX == (x-1)){
			// start point cannot be the same as end point.
			// we are already in the right end of the maze so
			// the height cannot be equal to (y-1) since that is the end cell.
			this.startPointY = ThreadLocalRandom.current().nextInt(0, this.y-1);
		}
		else {
			this.startPointY = ThreadLocalRandom.current().nextInt(0, this.y);
		}
		this.endPointX = this.x - 1;
		this.endPointY = this.y - 1;
		this.numCells = this.x * this.y;
		this.maze = new Cell[this.x][this.y];
		generateCellGrid();
		/*
		 * A pokemon can be present in any cell except the start and end cells.
		 * Every Maze will have at least one pokemon.
		 * These are assumption made by us.
		 */
		this.numPokemons = ThreadLocalRandom.current().nextInt(1, (this.numCells - 2) + 1);
		putPokemons();
		generateMaze(0, 0);

	}
	
	public void generateCellGrid() {
		
		for(int i=0; i<this.x; i++) {
			
			for(int j=0; j<this.y; j++) {
				
				if(i==this.startPointX && j==this.startPointY) {
					this.maze[i][j] = new Cell(true, false);
				} 
				
				else {
					
					if(i==this.endPointX && j==this.endPointY) {
						this.maze[i][j] = new Cell(false, true);
					}
					
					else {
						this.maze[i][j] = new Cell();
						
					}
				}
			}
		}
	}
	
	public void putPokemons() {
		//	Create a random number of pokemons and put in maze cells.
		
		/*
		 * random coordiate generation loop
		 */
		
		// Conditions for placing pokemon
		//	Not start or end
		//	doesnot have a pokemon already
		
		
		for(int i=0; i<numPokemons; i++) {
			// Thread Local generates numbers from 0 till x
			int x = ThreadLocalRandom.current().nextInt(0, this.x);
			int y = ThreadLocalRandom.current().nextInt(0, this.y);
			
			if(this.maze[x][y].canHide()){
				this.maze[x][y].setPokemonHiding(true);
			}
			
			else {
				//	If we could not place a pokemon the counter is decremented
				//	so that we calculate new random coordinates.
				i--;
			}
			
		}
		
		
		for(int i=0; i< x; i++) {
			
			for(int j=0; j<y; j++) {
				
				
			}
		}
		
	}
	
	public void display() {
		for (int i = 0; i < y; i++) {
			// draw the north edge
			for (int j = 0; j < x; j++) {
				System.out.print((maze[j][i].wall & 1) == 0 ? "+---" : "+   ");
			}
			System.out.println("+");
			// draw the west edge
			for (int j = 0; j < x; j++) {
				if(maze[j][i].pokemonHiding) {					// | p "   "  p " 
					System.out.print((maze[j][i].wall & 8) == 0 ? "| p " : "  p ");
				}
				else {
					if(maze[j][i].startCell) {
						System.out.print((maze[j][i].wall & 8) == 0 ? "| s " : "  s ");
					}
					else {
						System.out.print((maze[j][i].wall & 8) == 0 ? "|   " : "    ");
					}
				}
				
			}
			System.out.println("|");
		}
		// draw the bottom line
		for (int j = 0; j < x; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}
	
	private void generateMaze(int cx, int cy) {
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs));
		for (DIR dir : dirs) {
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y)
					&& (maze[nx][ny].wall == 0)) {
				// The path must be declared from both sides of the maze.
				maze[cx][cy].wall |= dir.bit;
				maze[nx][ny].wall |= dir.opposite.bit;
				generateMaze(nx, ny);
			}
		}
	}
 
	private static boolean between(int v, int upper) {
		return (v >= 0) && (v < upper);
	}
	
	public void displayInfo() {
		System.out.println(" Maze Size is " + this.x + " * " + this.y);
		System.out.println(" There are " + this.numPokemons + " Pokemons.");
		System.out.println(" The start node is x = " + (this.startPointX+1) + ", y = " + (this.startPointY+1));
		System.out.println();
	}
 
	private enum DIR {
		N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;
		private DIR opposite;
 
		// use the static initializer to resolve forward references
		static {
			N.opposite = S;
			S.opposite = N;
			E.opposite = W;
			W.opposite = E;
		}
 
		private DIR(int bit, int dx, int dy) {
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}
	};
	
	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.displayInfo();
		maze.display();
	}
	
}
