package com.fkujikis.maze;

import java.io.IOException;
import java.util.List;


public class Main 
{
    public static void main(String[] args)
    {
        if(args.length != 1){
        	System.out.println(args);
        	System.out.println("A single input parameter containing the input file path must be present.");
        	return;
        }
        
        try {
			Maze m = new MazeReader(args[0]).read();
			System.out.println("Maze read!\n");
			System.out.println(m);
			System.out.println("Solving maze...\n");
			
			// Could use dependency injection
			//MazeSolver ms = new MazeSolver(new DefaultCellSelector(), m);
			MazeSolver ms = new MazeSolver(new EuclideanDistanceCellSelector(), m);
			
			long startTime = System.nanoTime();
			
			List<MazeCoordinate> solution = ms.solve();
			
			long endTime = System.nanoTime();
			
			long durationMs = (endTime - startTime) / 1000000;
			System.out.println("Execution time: " + durationMs + " ms.\n");
			
			if(solution.isEmpty()){
				System.out.println("!!! The maze could not be solved.");
			} else {
				MazeWriter writer = new MazeWriter(m, solution);
				writer.writeMaze();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
