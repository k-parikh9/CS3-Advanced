import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<ItemOrder> cart;

    public ShoppingCart(){
        cart = new ArrayList<ItemOrder>();
    }

    public void add(ItemOrder newOrder){
            for(int i = cart.size() - 1; i >= 0; i--){
                if(newOrder.equals(cart.get(i))){
                    cart.remove(i);
                }
            }
        cart.add(newOrder);
    }

    public double getTotal(){
        double total = 0;
        for(int i = 0; i < cart.size(); i++){
            total += cart.get(i).getPrice();
        }
        return total;
    }
}
