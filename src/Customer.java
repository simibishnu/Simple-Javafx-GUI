import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class Customer implements Serializable{
    private String name;
    private ArrayList<Product> purchasedProducts;

    public Customer(String Name) {
        name = Name;
        purchasedProducts = new ArrayList<Product>();
    }

    public String toString() {
        return name + " who has spent $"+amountPurchased();
    }
    public String getName() {
        return name;
    }

    public double amountPurchased(){
        double moneySpent=0;
        for(Product p: purchasedProducts){
            moneySpent+=p.getPrice();
        }
        return moneySpent;
    }

    public ArrayList<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void printPurchaseHistory() {
        HashSet<Product> uniqueProducts = new HashSet<Product>(purchasedProducts);
        List<Product> purchaseHistory = new ArrayList<Product>(uniqueProducts);

        for (Product p : purchaseHistory) {
            int count = 0;
            for (Product x : purchasedProducts) {
                if (p.equals(x)) {
                    count++;
                }
            }
            System.out.println(count+"x "+p);
        }
    }


}

