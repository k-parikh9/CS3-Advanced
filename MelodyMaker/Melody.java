import java.util.*;

public class Melody {
    private Queue<Note> notes;

    public Melody(Queue<Note> song){
        notes = song;
    }

    public double getTotalDuration(){
        double total = 0;
        boolean repeat = false;
        boolean endRepeat = false;

        for(int i = 0; i < notes.size(); i++){
            Note n = notes.poll();

            //Check if note is repeat
            if(n.isRepeat()){
                repeat = !repeat;
                endRepeat = true;
            }

            if(repeat || endRepeat){
                total += 2 * n.getDuration();
            }
            else{
                total += n.getDuration();
            }

            endRepeat = false;
            notes.offer(n);
        }
        return total;
    }

    public String toString(){
        String str = "";
        for(int i = 0; i < notes.size(); i++){
            Note n = notes.poll();
            str += n.toString() + "\n";
            notes.offer(n);
        }
        return str;
    }

    public void changeTempo(double tempo){
        for(int i = 0; i < notes.size(); i++){
            Note n = notes.poll();
            n.setDuration(tempo * n.getDuration());
            notes.offer(n);
        }
    }

    public void reverse(){
        Stack<Note> temp = new Stack<Note>();
        while(!notes.isEmpty()){
            temp.push(notes.poll());
        }
        while(!temp.isEmpty()){
            notes.offer(temp.pop());
        }
    }

    public void append(Melody other){
        Queue<Note> temp = other.getNotes();
        for(int i = 0; i < temp.size(); i++){
            Note n = temp.poll();
            notes.offer(n);
            temp.offer(n);
        }
    }

    public void play(){
        boolean repeat = false;
        boolean endRepeat = false;
        Queue<Note> temp = new LinkedList<Note>();

        for(int i = 0; i < notes.size(); i++){
            Note n = notes.poll();
            n.play();

            //Check if repeat and update accordingly
            if(n.isRepeat()){
                repeat = !repeat;
                endRepeat = true;
            }
            if(repeat || endRepeat){
                temp.offer(n);
            }
           

            //Play repeated melody if repeat is over
            if(!repeat && endRepeat){
                while(!temp.isEmpty()){
                    temp.poll().play();
                }
            }

            endRepeat = false;
            notes.offer(n);
        }
    }

    public Queue<Note> getNotes(){
        return notes;
    }
}
