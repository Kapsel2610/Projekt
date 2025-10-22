public class Product {

    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void showInfo() {
        System.out.println("Produkt: " + name + ", cena: " + price + " zł");
    }
}