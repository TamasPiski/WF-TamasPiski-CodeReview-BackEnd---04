package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class Main extends Application {

    //Observable list of product objects
    public static ObservableList<Product> products = FXCollections.observableArrayList(
            new Product("Pfeffer", "1 Stück", "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe,\n besonders wenn er länger mitgekocht wird. ", "3,49", "2,79", "sample/images/pfeffer.jpg"),
            new Product("Schafmilchkäse", "200 Gramm Packung", "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus,\n wie man eine Werbebeschreibung schreibt.", "2,59", "1,99" , "sample/images/cheese.jpg"),
            new Product("Vöslauer", "1.5 Liter Flasche" , "Spritziges Vöslauer Mineralwasser.", "0,75", "0,49", "sample/images/voslauer.jpg"),
            new Product("Zucker", "500 Gramm Paket" , "Natürliches Gelieren wird durch Apfelpektin unterstützt,  welches im richtigen Verhältnis\n mit Zitronensäure und Kristallzucker abgemischt wurde.", "1,39", "0,89", "sample/images/zucker.jpg")
    );

    // TextFields
    public static TextField productField = new TextField();
    public static TextField quantityField = new TextField();
    public static TextField oldPriceField = new TextField();
    public static TextField newPriceField = new TextField();

    // Labels
    public static Label productLabel = new Label("Prod. Name");
    public static Label quantityLabel = new Label("Quantity");
    public static Label oldPriceLabel = new Label("Old price: ");
    public static Label newPriceLabel = new Label("New price: ");
    public static Label eurLabel1 = new Label("EUR");
    public static Label eurLabel2 = new Label("EUR");
    public static Label descriptionTitleLabel = new Label("Description");
    public static Label descriptionLabel = new Label("");

    // Buttons
    public static Button update = new Button("Update");
    public static Button report = new Button("Report");


    @Override
    public void start(Stage primaryStage) throws Exception{

        // Creating listView
        ListView<Product> list = new ListView<>();
        list.getItems().addAll(products);

        // Setting fields uneditable
        productField.setDisable(true);
        productField.setStyle("-fx-opacity: 1;");
        quantityField.setDisable(true);
        quantityField.setStyle("-fx-opacity: 1;");

        // Image
        ImageView image = new ImageView();
        image.setFitHeight(300);
        image.setFitWidth(300);

        // Horizontal boxes
        HBox oldPriceBox = new HBox(oldPriceField, eurLabel1);
        HBox newPriceBox = new HBox(newPriceField, eurLabel2);

        // Vertical boxes
        VBox labels = new VBox(productLabel, quantityLabel, oldPriceLabel, newPriceLabel);
        VBox desc = new VBox(image, descriptionTitleLabel, descriptionLabel);
        VBox fields = new VBox(productField, quantityField, oldPriceBox, newPriceBox);

        HBox hBox1 = new HBox(labels, fields);
        VBox vBox1 = new VBox(hBox1, desc);

        // Styling
        labels.setPadding(new Insets(15, 12, 15,12));
        labels.setSpacing(10);
        fields.setPadding(new Insets(15,260,10,12));
        desc.setPadding(new Insets(10,0,10,10));
        descriptionTitleLabel.setStyle("-fx-font-size: 1.5em");

        // Main box
        HBox main = new HBox(vBox1, update, report, list);

        // List item show
        list.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            productField.setText(newValue.getName());
            quantityField.setText(newValue.getQuantity());
            oldPriceField.setText(newValue.getPrice());
            newPriceField.setText(newValue.getOnSalePrice());
            image.setImage(new Image(newValue.getImageLink()));
            descriptionLabel.setText(newValue.getDescription());
        });

        // Update function
        update.setOnAction(actionEvent ->  {
            int selectedIdx = list.getSelectionModel().getSelectedIndex();
            if(selectedIdx != -1) {
                // Get values
                String name = productField.getText();
                String quantity = quantityField.getText();
                String oldPrice = oldPriceField.getText();
                String newPrice = newPriceField.getText();
                // Set values
                list.getItems().get(selectedIdx).setName(name);
                list.getItems().get(selectedIdx).setQuantity(quantity);
                list.getItems().get(selectedIdx).setPrice(oldPrice);
                list.getItems().get(selectedIdx).setOnSalePrice(newPrice);
                list.refresh();
            }
        });

        // Report button function
        report.setOnAction(actionEvent -> {
            // FileWriter
            File file = new File("report.txt");

            // Creating file
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Creating printWriter object
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            printWriter.println("Product list: \n");

            // Looping through the products and writing them into the file
            for(Product product : products) {
                printWriter.println("Name: " + product.getName() + "  Quantity: " + product.getQuantity() + "Description: " + product.getDescription() + " " + "Instead of the old price " + product.getPrice() + " EUR the on-sale price is " + product.getOnSalePrice() + " EUR");
            }

            // Closing printWriter
            printWriter.close();
        });

        // Creating the scene
        Scene scene = new Scene(main, 890, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
