package com.fkujikis.maze;

import java.util.List;

public class MazeWriter {
	
	private Maze maze;
	private String[][] mazeGrid;
	private List<MazeCoordinate> solutionPath;
	
	public MazeWriter(Maze maze, List<MazeCoordinate> solutionPath) {
		super();
		this.maze = maze;
		this.solutionPath = solutionPath;
		this.mazeGrid = maze.getGrid();
	}
	
	public void writeMaze(){
		writeValuesToGrid();
		printGird();
	}
	
	private void writeValuesToGrid(){
		// Convert passage / wall values
		for(int row = 0; row < maze.getHeight(); row++){
			for(int col = 0; col < maze.getWidth(); col++){
				switch(mazeGrid[row][col]) {
					case MazeConstants.INPUT_MAZE_WALL:
						mazeGrid[row][col] = MazeConstants.MAZE_WALL;
						break;
					case MazeConstants.INPUT_MAZE_PASSAGE:
						mazeGrid[row][col] = MazeConstants.MAZE_PASSAGE;
						break;
				}
			}
		}
		
		// Add in the solution path
		solutionPath.stream()
		.forEach(x-> 
			writeCoordinateValue(x, MazeConstants.MAZE_PATH)
		);
		
		// Mark start and end
		writeCoordinateValue(maze.getStart(), MazeConstants.MAZE_START);
		writeCoordinateValue(maze.getEnd(), MazeConstants.MAZE_END);
	}
	
	private void printGird(){
		for(int row = 0; row < maze.getHeight(); row++){
			for(int col = 0; col < maze.getWidth(); col++){
				System.out.print(mazeGrid[row][col]);
			}
			System.out.println();
		}
	}
	
	private void writeCoordinateValue(MazeCoordinate c, String value){
		mazeGrid[c.getY()][c.getX()] = value;
	}
	

}
