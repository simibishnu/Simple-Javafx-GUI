//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.io.*;
import java.util.*;

public class ElectronicStore{
    private String name;
    private List<Product> stock; //Array to hold all products
    private List<Customer> customers;
    private int numSales;
    private double revenue;
    private double moneyPerSale;
    private List<Product> currentCart;

    public ElectronicStore(String initName){
        name = initName;
        stock = new ArrayList<Product>();
        customers = new ArrayList<Customer>();
        currentCart = new ArrayList<Product>();
        numSales = 0;
        revenue = 0.00;
    }

    public boolean registerCustomer(Customer c){
        for(Customer x: customers){
            if(x.getName().equalsIgnoreCase(c.getName())){
                return false;
            }
        }
        customers.add(c);
        return true;
    }

    public String getName(){
        return name;
    }
    public List<Product> getStock(){return stock;}
    public List<Product> getCurrentCart(){return currentCart;}
    public void setCurrentCart(List newCart){currentCart=newCart;}

    public int getNumSales() {return numSales;}
    public void setNumSales(int moreSales) {numSales = moreSales;}
    public double getRevenue(){return revenue;}
    public void setRevenue(double newRevenue) {revenue = newRevenue;}
    public double getMoneyPerSale() {
        return moneyPerSale;
    }
    public void setMoneyPerSale(double newMoneyPerSale) {
        moneyPerSale = newMoneyPerSale;
    }

    public HashMap<Product,Integer> currentCartMap(){
        HashMap<Product,Integer> currentCartMap = new HashMap<Product,Integer>();
        HashSet<Product> uniqueCart = new HashSet<Product>(currentCart);

        for(Product p: uniqueCart){
            int count = 0;
            for(Product x: currentCart){
                if(p.equals(x)){
                    count++;
                }
            }
            currentCartMap.put(p,count);
        }
        return currentCartMap;
    }

    public void removeFromCart(String x){
        for(int i=0; i<currentCart.size(); i++){
            if(x.contains(currentCart.get(i).toString())){
                currentCart.remove(currentCart.get(i));
                break;
            }
        }
    }

    public List<String> uniqueCartString(){
        ArrayList<String> uniqueCartString = new ArrayList<String>();
        for(Map.Entry<Product,Integer> entry: currentCartMap().entrySet()){
            uniqueCartString.add(entry.getValue()+" x "+entry.getKey());
        }
        return uniqueCartString;
    }

    public void removeStock(Product x){
        for(Product p: stock){
            if(p.equals(x)){
                p.setStockQuantity(p.getStockQuantity()-1);
                break;
            }
        }
    }

    public double totalCartPrice(){
        double count = 0;

        for(Product p: currentCart){
            count+=p.getPrice();
        }
        return count;
    }

    public void sellCartItems(){
        for(Map.Entry<Product,Integer> entry: currentCartMap().entrySet()){
            for(Product p: stock){
                if(p.toString().equals(entry.getKey().toString())){
                    p.setSoldQuantity(p.getSoldQuantity()+entry.getValue());
                }
            }
        }
    }
    public boolean addProduct(Product newProduct){
        for(Product p: stock){
            if(p.toString().equalsIgnoreCase(newProduct.toString())){
                return false;
            }
        }
        if(newProduct!=null){
            stock.add(newProduct);
        }
        return true;
    }

    public List<Product> searchProducts(String x){
        List<Product> matchingProducts = new ArrayList<Product>();
        for(Product p: stock){
            if(p.toString().toLowerCase().contains(x.toLowerCase())){
                matchingProducts.add(p);
            }
        }
        return matchingProducts;
    }

    public void addStock(String x, int amount){
        if(amount>0)
            for(Product p: stock){
                if(x.contains(p.toString())){
                    p.setStockQuantity(p.getStockQuantity()+amount);
                }
            }
    }

    public List<Product> getMostPopular(int x){
        List<Product> mostPopular = new ArrayList<Product>();

        Collections.sort(stock, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getSoldQuantity()-o1.getSoldQuantity();
            }
        });

        if(x>stock.size()){
            return stock;
        }

        else if(x<0){
            return mostPopular;
        }

        else{
            for(int i=0; i<x; i++){
                mostPopular.add(stock.get(i));
            }
        }
        return mostPopular;
    }

    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }

}