package sample;

public class Product {
    private String name;
    private String quantity;
    private String description;
    private String price;
    private String onSalePrice;
    private String imageLink;


    public Product(String name, String quantity, String description, String price, String onSalePrice, String imageLink) {
        this.setName(name);
        this.setQuantity(quantity);
        this.setDescription(description);
        this.setPrice(price);
        this.setOnSalePrice(onSalePrice);
        this.setImageLink(imageLink);
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", old=" + getPrice() + ", new=" + getOnSalePrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOnSalePrice() {
        return onSalePrice;
    }

    public void setOnSalePrice(String onSalePrice) {
        this.onSalePrice = onSalePrice;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
