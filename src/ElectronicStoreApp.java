import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;

public class ElectronicStoreApp extends Application{
    private ElectronicStore model;
    private ElectronicStoreView view;


    public void start(Stage primaryStage){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView();
        view.update(model);

        view.getStock().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getAdd().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                model.getCurrentCart().add(view.getStock().getSelectionModel().getSelectedItem());
                model.removeStock(view.getStock().getSelectionModel().getSelectedItem());
                view.getCartLabel().setText("Current Cart ("+model.totalCartPrice()+")");
                view.update(model);
            }
        });

        view.getCurrentCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getRemove().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model.removeFromCart(view.getCurrentCart().getSelectionModel().getSelectedItem());
                model.addStock(view.getCurrentCart().getSelectionModel().getSelectedItem(),1);
                view.update(model);
            }
        });

        view.getCompleteSale().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                model.setNumSales(model.getNumSales()+1);
                model.setRevenue(model.getRevenue()+ model.totalCartPrice());
                System.out.println(model.totalCartPrice());
                model.setMoneyPerSale(model.getRevenue()/ model.getNumSales());
                model.sellCartItems();
                model.setCurrentCart(new ArrayList<Product>());
                view.update(model);


            }
        });

        view.getResetStore().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                model = ElectronicStore.createStore();
                view.update(model);

            }
        });

        primaryStage.setTitle("Electronic Store Application - "+model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }
    public static void main(String [] args){
        launch(args);
    }
}
