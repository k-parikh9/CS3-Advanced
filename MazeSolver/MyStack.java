import java.util.EmptyStackException;

public class MyStack implements StackADT{
    private Square[] stack;
    private int size;

    public MyStack(){
        this(7);
    }

    public MyStack(int initCap){
        stack = new Square[initCap];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Square peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return stack[size - 1];
    }

    public Square pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Square temp = stack[size - 1];
        size--;
        return temp;
    }

    public void push(Square item){
        if(size == stack.length){
            doubleCapacity();
        }
        stack[size] = item;
        size++;
    }

    public int size(){
        return size;
    }

    public void clear(){
        for(int i = 0; i < size; i++){
            stack[i] = null;
        }
    }

    private void doubleCapacity(){
        Square[] bigger = new Square[2 * size];
        for(int i = 0; i < size; i++){
            bigger[i] = stack[i];
        }
        stack = bigger;
    }

    @Override
    public String toString(){
        String str = "";
        for(int i = size - 1; i >= 0; i--){
            str += stack[i];
            if(i == size - 1){
                str += "       <----- TOP";
            }
            str += "\n";
        }
        str += "-------";
        return str;
    }
}
