public class Item {
    private String name;
    private double price;
    private int bulkQty;
    private double bulkPrice;

    //2-param constructor
    public Item(String name, double price){
        this(name, price, 0, price);
    }

    //4-param constructor
    public Item(String name, double price, int bulkQty, double bulkPrice){
        if(price < 0 || bulkQty < 0 || bulkPrice < 0){
            throw new IllegalArgumentException("error");
        }
        this.name = name;
        this.price = price;
        this.bulkQty = bulkQty;
        this.bulkPrice = bulkPrice;
    }

    //Check price for given quantity
    public double priceFor(int quantity){
        if(quantity < 0){
            throw new IllegalArgumentException("error");
        }
        
        if(quantity >= bulkQty){
            return quantity * bulkPrice;
        }
        return quantity * price;
    }

    //Name getter
    private String getName(){
        return name;
    }

    @Override
    public boolean equals(Object obj){
        Item i = (Item) obj;
        return this.name.equals(i.getName());
    }

    @Override
    public String toString(){
        String desc = name + ", $" + price;

        //Check for bulk price
        if(bulkPrice != price){
            desc += " (" + bulkQty + " for " + bulkPrice + ")";
        }

        return desc;
    }
}
