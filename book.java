public class Book extends Product implements Discountable {
    String author;

    public Book(String name, double price, String author) {
        super(name, price);
        this.author = author;
    }

    @Override
    public void applyDiscount(double percent) {
        price = price - (price * percent / 100);
    }

    @Override
    public void showInfo() {
        System.out.println("Książka: " + name + " (" + author + "), cena: " + price + " zł");
    }
}