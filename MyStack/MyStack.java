import java.util.EmptyStackException;
import java.util.Arrays;

public class MyStack {
    private Integer[] stack;
    private int size;

    public MyStack(){
        this(7);
    }

    public MyStack(int initCap){
        stack = new Integer[initCap];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Integer peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return stack[size - 1];
    }

    public Integer pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Integer temp = stack[size - 1];
        size--;
        return temp;
    }

    public void push(Integer item){
        if(size == stack.length){
            doubleCapacity();
        }
        stack[size] = item;
        size++;
    }

    private void doubleCapacity(){
        Integer[] bigger = new Integer[2 * size];
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
