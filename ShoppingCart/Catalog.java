import java.util.ArrayList;

public class Catalog {
    private String name;
    private ArrayList<Item> catalog;

    public Catalog(String name){
        this.name = name;
        catalog = new ArrayList<Item>();
    }

    //Add item to catalog list
    public void add(Item item){
        catalog.add(item);
    }

    //Size getter
    public int size(){
        return catalog.size();
    }

    //Item at index getter
    public Item get(int index){
        return catalog.get(index);
    }

    //Name getter
    public String getName(){
        return name;
    }
}
