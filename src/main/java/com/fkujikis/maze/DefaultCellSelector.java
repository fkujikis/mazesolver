package com.fkujikis.maze;

import java.util.List;
import java.util.Optional;

public class DefaultCellSelector implements MazeCellSelector {

	@Override
	public MazeCoordinate chooseNextCellToVisit(String[][] mazeGrid, MazeCoordinate end, List<MazeCoordinate> availableCells) {
		Optional<MazeCoordinate> mc = availableCells.stream()
				.filter(x-> 
					MazeConstants.CELL_EMPTY.equals(mazeGrid[x.getY()][x.getX()])
				).findFirst();
		return mc.orElse(null);
	}
}
