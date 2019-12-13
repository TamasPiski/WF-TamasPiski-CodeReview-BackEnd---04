package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.jfr.Event;

import java.io.FileInputStream;


public class Main extends Application {

    //Observable list of product objects
    public static ObservableList<Product> products = FXCollections.observableArrayList(
            new Product("Pfeffer", "1 Stück", "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, \n besonders wenn er länger mitgekocht wird. ", "3,49", "2,79", "/images/pfeffer.jpg"),
            new Product("Schafmilchkäse", "200 Gramm Packung", "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus, \n wie man eine Werbebeschreibung schreibt.", "2,59", "1,99" , "/images/cheese.jpg"),
            new Product("Vöslauer", "1.5 Liter Flasche" , "Spritziges Vöslauer Mineralwasser.", "0,75", "0,49", "/images/voslauer.jpg"),
            new Product("Zucker", "500 Gramm Paket" , "Natürliches Gelieren wird durch Apfelpektin unterstützt,  welches im richtigen Verhältnis \n mit Zitronensäure und Kristallzucker abgemischt wurde.", "1,39", "0,89", "/images/zucker.jpg")
    );


    // Textfields
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



    @Override
    public void start(Stage primaryStage) throws Exception{

        // Creating listview
        ListView<Product> list = new ListView<>();
        list.getItems().addAll(products);

        //Setting fields uneditable
        productField.setDisable(true);
        productField.setStyle("-fx-opacity: 1;");
        quantityField.setDisable(true);
        quantityField.setStyle("-fx-opacity: 1;");

        // Horizontal boxes
        HBox oldprice = new HBox(oldPriceField, eurLabel1);
        HBox newprice = new HBox(newPriceField, eurLabel2);

        //Vertical boxes
        VBox labels = new VBox(productLabel, quantityLabel, oldPriceLabel, newPriceLabel);
        VBox desc = new VBox(descriptionTitleLabel, descriptionLabel);
        VBox fields = new VBox(productField, quantityField, oldprice, newprice);

        HBox hbox1 = new HBox(labels, fields);
        VBox vBox1 = new VBox(hbox1, desc);

        //Image

        //Styling
        labels.setPadding(new Insets(15, 12, 15,12));
        labels.setSpacing(10);
        fields.setPadding(new Insets(15,260,10,12));
        desc.setPadding(new Insets(10,0,10,10));
        descriptionTitleLabel.setStyle("-fx-font-size: 1.5em");

        // Main box
        HBox main = new HBox(vBox1, list);

        list.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            productField.setText(newValue.getName());
            quantityField.setText(newValue.getQuantity());
            oldPriceField.setText(newValue.getPrice());
            newPriceField.setText(newValue.getOnSalePrice());
            descriptionLabel.setText(newValue.getDescription());
        });

        Scene scene = new Scene(main, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
