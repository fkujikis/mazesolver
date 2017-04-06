package com.fkujikis.maze;

public interface MazeConstants {
	
	// File parameters
	public static final int LINE_INDEX_DIMENSIONS = 0;
	public static final int LINE_INDEX_START = 1;
	public static final int LINE_INDEX_END = 2;
	public static final int LINE_INDEX_MAZE = 3;
	
	public static final int INDEX_WIDTH = 0;
	public static final int INDEX_HEIGHT = 1;
	
	public static final int INDEX_START_X = 0;
	public static final int INDEX_START_Y = 1;
	
	public static final int INDEX_END_X = 0;
	public static final int INDEX_END_Y = 1;

	public static final String INPUT_MAZE_WALL = "1";
	public static final String INPUT_MAZE_PASSAGE = "0";
	public static final String DELIMITER = " ";
	
	// Solving
	public static final String CELL_EMPTY = "0";
	public static final String CELL_VISITED = "+";
	
	// Output parameters
	public static final String MAZE_START = "S";
	public static final String MAZE_PATH = "X";
	public static final String MAZE_WALL = "#";
	public static final String MAZE_PASSAGE = " ";
	public static final String MAZE_END = "E";
}
