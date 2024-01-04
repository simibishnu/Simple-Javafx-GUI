import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.*;

public class ElectronicStoreView extends Pane{
    private TextField numSales, revenue, moneyPerSale;
    private ListView<Product> popularItems, stock;
    private ListView<String> currentCart;
    private Button resetStore, add,remove,completeSale;
    private Label cartLabel;
    public ListView<Product> getStock(){return stock;}
    public ListView<String> getCurrentCart(){return currentCart;}
    public Button getAdd(){return add;}
    public Button getRemove(){return remove;}
    public Button getCompleteSale(){return completeSale;}
    public Label getCartLabel(){return cartLabel;}
    public Button getResetStore(){return resetStore;}
    public ElectronicStoreView(){
        // Create the labels
        Label storeLabel = new Label("Store Summary:");
        storeLabel.relocate(40, 20);
        Label stockLabel = new Label("Store Stock");
        stockLabel.relocate(310, 20);
        cartLabel = new Label("Current Cart ($0.00)");
        cartLabel.relocate(595, 20);
        Label numLabel = new Label("# Sales:");
        numLabel.relocate(35,45);
        Label revenueLabel = new Label("Revenue:");
        revenueLabel.relocate(25,78);
        Label perSaleLabel = new Label("$/Sale:");
        perSaleLabel.relocate(35,113);
        Label popItemsLabel = new Label("Most Popular Items:");
        popItemsLabel.relocate(35,145);

        numSales = new TextField();
        numSales.setPrefSize(90,10);
        numSales.relocate(90,42);

        revenue = new TextField();
        revenue.setPrefSize(90,10);
        revenue.relocate(90,75);

        moneyPerSale = new TextField();
        moneyPerSale.setPrefSize(90,10);
        moneyPerSale.relocate(90,110);

        popularItems = new ListView<Product>();
        popularItems.setPrefSize(170,170);
        popularItems.relocate(10,170);

        stock = new ListView<Product>();
        stock.setPrefSize(295,300);
        stock.relocate(189,40);

        currentCart = new ListView<String>();
        currentCart.setPrefSize(295,300);
        currentCart.relocate(490,40);

        resetStore = new Button("Reset Store");
        resetStore.setPrefSize(125,40);
        resetStore.relocate(30,345);

        add = new Button("Add");
        add.setPrefSize(125,40);
        add.relocate(265,345);

        remove = new Button("Remove From Cart");
        remove.setPrefSize(135,40);
        remove.relocate(490,345);
        completeSale = new Button("Complete Sale");
        completeSale.setPrefSize(135,40);
        completeSale.relocate(650,345);

        // Add all the components to the Pane
        getChildren().addAll(storeLabel, stockLabel, cartLabel,numLabel,numSales,revenueLabel,revenue,perSaleLabel,moneyPerSale,popItemsLabel,popularItems,resetStore,stock,currentCart,add,remove,completeSale);

        setPrefSize(800, 400);
    }

    public void update(ElectronicStore model){
        List<Product> stockList = new ArrayList<Product>();

        for(Product p: model.getStock()) {
            if (p.getStockQuantity() > 0) {
                stockList.add(p);
            }
        }

        int selectedIndex1 = stock.getSelectionModel().getSelectedIndex();
        stock.setItems(FXCollections.observableArrayList(stockList));
        stock.getSelectionModel().select(selectedIndex1);
        add.setDisable(stock.getSelectionModel().getSelectedIndex()<0);

        completeSale.setDisable(model.uniqueCartString().size()<1);

        int selectedIndex2 = currentCart.getSelectionModel().getSelectedIndex();
        currentCart.setItems(FXCollections.observableArrayList(model.uniqueCartString()));
        currentCart.getSelectionModel().select(selectedIndex2);
        remove.setDisable(currentCart.getSelectionModel().getSelectedIndex()<0);

        popularItems.setItems(FXCollections.observableArrayList(model.getMostPopular(3)));

        numSales.setText(Integer.toString(model.getNumSales()));
        revenue.setText(Double.toString(model.getRevenue()));
        moneyPerSale.setText(Double.toString(model.getMoneyPerSale()));
        cartLabel.setText("Current Cart ("+model.totalCartPrice()+")");
    }

}
