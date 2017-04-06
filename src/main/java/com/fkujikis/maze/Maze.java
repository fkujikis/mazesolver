package com.fkujikis.maze;

public class Maze {

	private int width;
	private int height;
	
	private MazeCoordinate start;
	private MazeCoordinate end;
	
	private String[][] grid;
	
	public Maze(MazeCoordinate start, MazeCoordinate end, String[][] mazeGrid) {
		// No error checking
		this.width = mazeGrid[0].length;
		this.height = mazeGrid.length;
		this.start = start;
		this.end = end;
		this.grid = mazeGrid;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public MazeCoordinate getStart() {
		return start;
	}

	public MazeCoordinate getEnd() {
		return end;
	}

	public String[][] getGrid() {
		String[][] gridClone = new String[height][];
		for(int i = 0; i < grid.length; i++){
			gridClone[i] = grid[i].clone();
		}
		return gridClone;
	}
	
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("Width: " + width + "\n");
		sb.append("Height: " + height + "\n");
		sb.append("Start X: " + start.getX() + "\n");
		sb.append("Start Y: " + start.getY() + "\n");		
		sb.append("End X: " + end.getX() + "\n");
		sb.append("End Y: " + end.getY() + "\n");
		
		for(int row = 0; row < height; row++){
			for(int column = 0; column < width; column++){
				sb.append(grid[row][column]);
				
				// No space on the last one
				if(column < width - 1) sb.append(" ");
				else continue;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
