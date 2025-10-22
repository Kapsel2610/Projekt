public class Main {
    public static void main(String[] args) {
        // Tworzymy produkty
        Book book1 = new Book("Wiedźmin", 59.99, "Andrzej Sapkowski");
        Clothing shirt = new Clothing("Koszulka", 49.99, "M");

        // Produkty przed zniżką
        System.out.println("=== Przed zniżką ===");
        book1.showInfo();
        shirt.showInfo();

        // Zastosowanie zniżek
        book1.applyDiscount(10);   // 10% zniżki
        shirt.applyDiscount(20);   // 20% zniżki

        // Produkty po zniżce
        System.out.println("\n=== Po zniżce ===");
        book1.showInfo();
        shirt.showInfo();
    }
}