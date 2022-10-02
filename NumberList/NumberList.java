/**
 * Write a description of class NumberList here.
 *
 * @author Krish Parikh
 * @version 08/10/22
 */
public class NumberList
{
    //PIV's
    private Integer[] list;
    private int size;
    
    public NumberList(){
        list = new Integer[2];
        size = 0;
    }
    
    //Size getter
    public int size(){
        return size;
    }
    
    //Check empty
    public boolean isEmpty(){
        return size == 0;
    }
    
    //Formatted string of list
    public String toString(){
        //Edge case
        if(size == 0){
            return "[]";
        }

        //Normal cases
        String text = "[";
        for(int i = 0; i < size - 1; i++){
            text += list[i] + ", ";
        }
        text += list[size - 1] + "]";
        return text;
    }
    
    //Doubles capacity of list
    public void doubleCapacity(){
        Integer[] temp = new Integer[size * 2];
        for(int i = 0; i < size; i++){
            temp[i] = list[i];
        }
        list = temp;
    }
    
    //Adds val to list array at index
    public void add(int index, Integer val){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        //Continously check capacity until it fits the requirements
        while(list.length <= size){
            doubleCapacity();
        }
        
        //Making room
        for(int i = size; i > index; i--){
            list[i] = list[i - 1];
        }

        //Add element and increment
        list[index] = val;
        size++;
    }

    //Adds val to end of list
    public boolean add(Integer val){
        this.add(size, val);
        return true;
    }

    //Gets element at index
    public Integer get(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        return list[index];
    }

    //Sets element at index to val. Returns replaced element
    public Integer set(int index, Integer val){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        Integer temp = list[index];
        list[index] = val;
        return temp;
    }

    //Removes and returns element at index
    public Integer remove(int index){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        Integer element = list[index];
        for(int i = index; i < size - 1; i++){
            list[i] = list[i + 1];
        }
        size--;
        return element;
    }
}