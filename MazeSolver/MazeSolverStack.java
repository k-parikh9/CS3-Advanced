public class MazeSolverStack extends MazeSolver{
    private MyStack stack;

    public MazeSolverStack(Maze maze){
        super(maze);
    }

    @Override
    public void makeEmpty(){
        stack = new MyStack();
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public void add(Square s){
        stack.push(s);
    }

    @Override
    public Square next(){
        System.out.println(stack);
        return stack.pop();
    }

    @Override
    public Square peekNext(){
        return stack.peek();
    }
}
