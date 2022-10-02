import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{
	String fileName;

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	
	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		this.fileName = fileName;
		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/**reset the simulation (the reset button in the GUI) */
	public void reset(){
		//Stop timer
		timer.stop();

		//Clear all existing grid values
		for(int r = 0; r < SIZE; r++){
			for(int c = 0; c < SIZE; c++){
				grid[r][c].setAliveNow(false);
				grid[r][c].setAliveNext(false);
			}
		}

		//No file provided
		if(fileName == null){
			for (int r = 0; r < SIZE; r++ )
			{
				for (int c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		//File provided
		else{
			try{
				Scanner input = new Scanner(new File(fileName));
				int numInitialCells = input.nextInt();
				int r, c;
				for (int count=0; count<numInitialCells; count++)
				{
					r = input.nextInt();
					c = input.nextInt();
					grid[r][c].setAliveNow(true);
				}
			input.close();
			} catch (IOException ioe){
				System.out.println("Unable to find file " + ioe.getMessage());
			}	
		}

		//Reset color to blue
		myView.setRandomColor(false);
		
		myView.updateView(grid);
	}

	/**toggle random colors in simulation*/
	public void toggleColor(){
		myView.setRandomColor(!myView.getRandomColor());
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}

	/**helper method that returns occupied neighbors given a coordinate */
	private ArrayList<Coordinate> aliveneighbors(int row, int col){
		//Boundary vars
		int startRow = row - 1;
		int endRow = row + 2;
		int startCol = col - 1;
		int endCol = col + 2;

		//Checking neighbor boundary exceptions
		if(row == 0){
			startRow = row;
		}
		else if(row == (SIZE - 1)){
			endRow = row + 1;
		}

		if(col == 0){
			startCol = col;
		}
		else if(col == (SIZE - 1)){
			endCol = col + 1;
		}

		//Finding neighbors
		ArrayList<Coordinate> neighbors = new ArrayList<Coordinate>();
		for(int r = startRow; r < endRow; r++){
			for(int c = startCol; c < endCol; c++){
				if(!(r == row && c == col)){
					if(grid[r][c].isAliveNow()){
						neighbors.add(new Coordinate(r, c));
					}
				}
			}
		}

		return neighbors;
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		//Set alive next values
		for(int r = 0; r < SIZE; r++){
			for(int c = 0; c < SIZE; c++){
				ArrayList<Coordinate> neighbors = aliveneighbors(r, c);

				if(grid[r][c].isAliveNow()){
					if(neighbors.size() == 2 || neighbors.size() == 3){
						grid[r][c].setAliveNext(true);
					}
					else{
						grid[r][c].setAliveNext(false);
					}
				}
				else{
					if(neighbors.size() == 3){
						grid[r][c].setAliveNext(true);
					}
				}
			}
		}

		//Use alive next values to set alive now values
		for(int r = 0; r < SIZE; r++){
			for(int c = 0; c < SIZE; c++){
				if(grid[r][c].isAliveNext()){
					grid[r][c].setAliveNow(true);
				}
				else{
					grid[r][c].setAliveNow(false);
				}
			}
		}
	}
}

