import java.util.*;
import java.io.*;

public class Maze{
    Square[][] maze;
    Square start, exit;

    public Maze(){

    }

    public boolean loadMaze(String fileName){
        try{
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            int row = sc.nextInt();
            int col = sc.nextInt();
            maze = new Square[row][col];

            for(int r = 0; r < maze.length; r++){
                for(int c = 0; c < maze[r].length; c++){
                    int type = sc.nextInt();
                    maze[r][c] = new Square(r, c, type);

                    //Check if square is start or exit
                    if(maze[r][c].getType() == 2){
                        start = maze[r][c];
                    }
                    else if(maze[r][c].getType() == 3){
                        exit = maze[r][c];
                    }
                }
            }
            sc.close();
            return true;
        } catch(IOException e){
            System.out.println("File not found" + e.getMessage());
            return false;
        }

    }

    public List<Square> getNeighbors(Square s){
        List<Square> neighbors = new ArrayList<Square>();
        int row = s.getRow();
        int col = s.getCol();

        //Check exceptions
        if(row != 0){
            neighbors.add(maze[row - 1][col]);
        }
        if(col != maze[row].length - 1){
            neighbors.add(maze[row][col + 1]);
        }
        if(row != maze.length - 1){
            neighbors.add(maze[row + 1][col]);
        }
        if(col != 0){
            neighbors.add(maze[row][col - 1]);
        }

        return neighbors;
    }

    public Square getStart(){
        return start;
    }

    public Square getExit(){
        return exit;
    }

    public void reset(){
        for(int r = 0; r < maze.length; r++){
            for(int c = 0; c < maze[r].length; c++){
                maze[r][c].reset();
            }
        }
    }

    public String toString(){
        String str = "";
        for(int r = 0; r < maze.length; r++){
            for(int c = 0; c < maze[r].length; c++){
                str += maze[r][c].toString() + " ";
            }
            str += "\n";
        }
        return str;
    }
}