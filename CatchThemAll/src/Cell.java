package src;

public class Cell {
	
	public int wall;
	boolean pokemonHiding;
	boolean startCell;
	boolean endCell;
	
	public Cell() {
		this.wall = 0;
		this.pokemonHiding = false;
		this.startCell = false;
		this.endCell = false;
	}
	
	public Cell(boolean startCell, boolean endCell) {
		this.wall = 0;
		this.pokemonHiding = false;
		this.startCell = startCell;
		this.endCell = endCell;
	}

	public int getWall() {
		return wall;
	}

	public void setWall(int wall) {
		this.wall = wall;
	}

	public boolean isPokemonHiding() {
		return pokemonHiding;
	}
	
	public boolean canHide() {
		if(this.startCell || this.endCell || this.pokemonHiding)
			return false;
		return true;
		
	}

	public void setPokemonHiding(boolean pokemonHiding) {
		this.pokemonHiding = pokemonHiding;
	}
	
}
