public class Square 
{
	//These values are used to denote what type of Square this is in the maze
	final static int EMPTY = 0; //an empty space
	final static int WALL  = 1; //a wall
	final static int START = 2; //the start 
	final static int EXIT  = 3; //the exit

	//These values indicate the status of a particular Square while the maze is being solved, for the GUI
	final static char WORKING      = 'o'; //currently on the work list (the stack)
	final static char EXPLORED     = '.'; //done, already explored
	final static char ON_EXIT_PATH = 'x'; //on the exit path, used in a later lab
	final static char UNKNOWN      = '_'; //not known / visited yet (empty cells only)

	private int  row, col; //r, c location in the maze
	private int  type;     //type of this square, e.g. empty, wall, etc.
	private char status;   //the status of a room being explored, shown by the GUI

	public Square(int row, int col, int type){
		this.row = row;
		this.col = col;
		this.type = type;
	}

	public String toString(){
		String str = "";
		if(type == EMPTY){
			if(status == WORKING){
				str = "o";
			}
			else if(status == EXPLORED){
				str = ".";
			}
			else if(status == ON_EXIT_PATH){
				str = "x";
			}
			else{
				str = "_";
			}
		}
		else if(type == WALL){
			str += "#";
		}
		else if(type == START){
			str = "S";
		}
		else{
			str = "E";
		}
		return str;
	}

	public boolean equals(Object obj){
		Square s = (Square) obj;
		return (this.row == s.getRow() && this.col == s.getCol());
	}

	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}

	public int getType(){
		return type;
	}

	public char getStatus(){
		return status;
	}

	public void setStatus(char status){
		this.status = status;
	}

	public void reset(){
		setStatus(UNKNOWN);
	}
}