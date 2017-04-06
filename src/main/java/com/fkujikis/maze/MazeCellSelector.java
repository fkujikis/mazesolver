package com.fkujikis.maze;

import java.util.List;

public interface MazeCellSelector {

	/**
	 * Implements selection criteria for choosing the next cell to visit while solving the maze.
	 * 
	 * @param mazeGrid - the maze grid.
	 * @param end - end cell / goal of the maze.
	 * @param cells - available cells to choose from.
	 * @return the coordinates of the next cell to probe, or else null.
	 */
	public MazeCoordinate chooseNextCellToVisit(String[][] mazeGrid, MazeCoordinate end, List<MazeCoordinate> cells);
	
}
