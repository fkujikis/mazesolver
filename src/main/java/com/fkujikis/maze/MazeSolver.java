package com.fkujikis.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class MazeSolver {

	private Maze maze;
	private String[][] grid;
	private boolean solved = false;
	private MazeCellSelector cellSelector;
	
	/**
	 * 
	 * @param selector - cell selection strategy to use.
	 * @param m - the maze.
	 */
	public MazeSolver(MazeCellSelector selector, Maze m){
		this.maze = m;
		this.grid = m.getGrid();
		this.cellSelector = selector;
	}
	
	/**
	 * Solve the maze!
	 * 
	 * @param m the maze to solve.
	 * @return list containing the coordinate path from start to end.
	 */
	public List<MazeCoordinate> solve(){
		
		Stack<MazeCoordinate> path = new Stack<MazeCoordinate>();

		solveMaze(maze.getStart(), maze.getEnd(), path);
		
		if(solved)
			return new ArrayList<MazeCoordinate>(path);
		else
			return new ArrayList<MazeCoordinate>();
	}

	// Basically depth first search
	// - visit a cell through each iteration
	// - possible optimisations - greedy search based on euclidean / some other distance
	// - might have to unroll recursive to iterative approach for large files
	private void solveMaze(MazeCoordinate c, MazeCoordinate end, Stack<MazeCoordinate> path){
	
		// Cease all recursion
		if(solved) return;
		
		// Visit the current coordinate
		grid[c.getY()][c.getX()] = MazeConstants.CELL_VISITED;
		path.push(c);

		if(c.equals(end)){
			solved = true;
			return;
		}
		
		List<MazeCoordinate> adjacentCells = getAdjacentCells(c);
		MazeCoordinate toVisit = cellSelector.chooseNextCellToVisit(grid, maze.getEnd(), adjacentCells);
		while(toVisit != null && !solved){
			solveMaze(toVisit, end, path);
			toVisit = cellSelector.chooseNextCellToVisit(grid, maze.getEnd(), adjacentCells);
		}
		
		if(!solved){
			// remove everything from the path until we reach the current coordinate
			for(MazeCoordinate mc = path.pop(); !c.equals(mc););
		}
	}
	
	/**
	 * Find adjacent unvisited cells : N, S, E, W (not diagonally)
	 */
	private List<MazeCoordinate> getAdjacentCells(MazeCoordinate c){
		
		// Define search boundaries
		int minX = getMinAdjacentIndex(c.getX());
		int maxX = getMaxAdjacentIndex(c.getX(), maze.getWidth());
		int minY = getMinAdjacentIndex(c.getY());
		int maxY = getMaxAdjacentIndex(c.getY(), maze.getHeight());
		
		// Possible cells
		List<MazeCoordinate> adjacentCells = new ArrayList<MazeCoordinate>();
		adjacentCells.add(new MazeCoordinate(minX, c.getY()));
		adjacentCells.add(new MazeCoordinate(maxX, c.getY()));
		adjacentCells.add(new MazeCoordinate(c.getX(), minY));
		adjacentCells.add(new MazeCoordinate(c.getX(), maxY));
		
		// Unvisited cells
		List<MazeCoordinate> unvisitedCells = adjacentCells.stream()
			.filter(x -> 
				MazeConstants.CELL_EMPTY.equals(grid[x.getY()][x.getX()])	
			).collect(Collectors.toList());

		return unvisitedCells;
	}
	
	private int getMaxAdjacentIndex(int currentIndex, int size){
		if(currentIndex == size - 1)
			return size - 1;
		else
			return currentIndex + 1;
	}
	
	private int getMinAdjacentIndex(int currentIndex){
		if(currentIndex == 0)
			return 0;
		else
			return currentIndex - 1;
	}
}
