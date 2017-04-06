package com.fkujikis.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MazeReader {
	
	private String filepath;
	private List<String[]> arrayFile;
	
	public MazeReader(String filepath){
		this.filepath = filepath;
	}
	
	public Maze read() throws IOException {
		
	//	INPUT:
	//	<WIDTH> <HEIGHT><CR>
	//	<START_X> <START_Y><CR>		(x,y) location of the start. (0,0) is upper left and (width-1,height-1) is lower right
	//	<END_X> <END_Y><CR>		(x,y) location of the end
	//	<HEIGHT> rows where each row has <WIDTH> {0,1} integers space delimited

		readFileAsLineArrays();
		
		MazeCoordinate start = new MazeCoordinate(
				readParameter(MazeConstants.LINE_INDEX_START, MazeConstants.INDEX_START_X),
				readParameter(MazeConstants.LINE_INDEX_START, MazeConstants.INDEX_START_Y));
		MazeCoordinate end = new MazeCoordinate(
				readParameter(MazeConstants.LINE_INDEX_END, MazeConstants.INDEX_END_X),
				readParameter(MazeConstants.LINE_INDEX_END, MazeConstants.INDEX_END_Y));
		
		String[][] mazeGrid = readGrid();
		
		return new Maze(start, end, mazeGrid);
	}
	
	private void readFileAsLineArrays() throws IOException{
		arrayFile = new ArrayList<String[]>();
		Files.lines(Paths.get(filepath))
			.forEach(x -> 
				arrayFile.add(x.split(MazeConstants.DELIMITER))
			);
	}
	
	private int readParameter(int lineIndex, int paramIndex){
		return Integer.parseInt(arrayFile.get(lineIndex)[paramIndex]);
	}
	
	private String[][] readGrid() {
		int height = readParameter(MazeConstants.LINE_INDEX_DIMENSIONS, MazeConstants.INDEX_HEIGHT);
		String[][] grid = new String[height][];
		
		int fileIndex = MazeConstants.LINE_INDEX_MAZE;
		int gridIndex = 0;
		
		while(gridIndex < height){
			grid[gridIndex++] = arrayFile.get(fileIndex++);
		}
	
		return grid;
	}
	
}
