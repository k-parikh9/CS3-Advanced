import java.util.*;

public abstract class MazeSolver {
    protected Maze maze;
    private boolean solved, solvable;

    public MazeSolver(Maze maze){
        this.maze = maze;
        this.makeEmpty();
        this.add(maze.getStart());
    }

    public abstract void makeEmpty();

    public abstract boolean isEmpty();

    public abstract void add(Square s);

    public abstract Square next();

    public abstract Square peekNext();

    public boolean isSolved(){
        solved = false;
        solvable = true;

        //Check if maze is solved
        if(peekNext().equals(maze.getExit())){
            solved = true;
        }

        //Check if maze is unsolvable
        if(isEmpty()){
            solvable = false;
        }

        return (solved || !solvable);
    }

    public void step(){
        //Stop if maze is solved/unsolvable
        if(isSolved()){
            return;
        }
        
        Square n = next();
        List<Square> neighbors = maze.getNeighbors(n);
        for(Square s : neighbors){
            if(s.getType() != Square.WALL && s.getStatus() != Square.EXPLORED){
                add(s);
                s.setStatus(Square.WORKING);
            }
        }
        n.setStatus(Square.EXPLORED);
    }

    public String getPath(){
        if(solved){
            return "Maze is solved";
        }
        else if(!solvable){
            return "Maze is unsolvable";
        }
        else{
            return "Maze is not yet solved";
        }
    }

    public void solve(){
        while(!isSolved()){
            step();
        }
    }
}
