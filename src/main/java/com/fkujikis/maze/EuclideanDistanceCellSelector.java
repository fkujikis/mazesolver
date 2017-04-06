package com.fkujikis.maze;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Uses minimum Euclidean distance between available cells and the end cell as the selection criteria.
 */
public class EuclideanDistanceCellSelector implements MazeCellSelector {

	@Override
	public MazeCoordinate chooseNextCellToVisit(String[][] mazeGrid, MazeCoordinate end, List<MazeCoordinate> availableCells) {
		
		List<MazeCoordinate> coords = availableCells.stream()
		.filter(x-> 
			MazeConstants.CELL_EMPTY.equals(mazeGrid[x.getY()][x.getX()])
		).sorted(new Comparator<MazeCoordinate>() {
			@Override
			public int compare(MazeCoordinate c1, MazeCoordinate c2) {

				double distanceC1 = calculateDistance(c1, end);
				double distanceC2 = calculateDistance(c2, end);
				
				if(distanceC1 < distanceC2)
					return -1;
				if(distanceC1 > distanceC2)
					return 1;
				
				return 0;
			}
		}).collect(Collectors.toList());
		
		if(coords.isEmpty()) return null;
		return coords.get(0);
	}
	
	private double calculateDistance(MazeCoordinate c1, MazeCoordinate c2){
		double xDiff = c2.getX() - c1.getX();
		double yDiff = c2.getY() - c1.getY();
		double distanceSquared = xDiff * xDiff + yDiff * yDiff;
		double distance = Math.sqrt(distanceSquared);
		
		return distance;
	}



}
