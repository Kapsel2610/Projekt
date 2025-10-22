public class Clothing extends Product implements Discountable {
    String size;

    public Clothing(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public void applyDiscount(double percent) {
        price = price - (price * percent / 100);
    }

    @Override
    public void showInfo() {
        System.out.println("Ubranie: " + name + " (rozmiar: " + size + "), cena: " + price + " zł");
    }
}