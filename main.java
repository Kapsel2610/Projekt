public class Main {
    public static void main(String[] args) {
     
        Book book1 = new Book("Wiedźmin", 59.99, "Andrzej Sapkowski");
        Clothing shirt = new Clothing("Koszulka", 49.99, "M");

        
        System.out.println("=== Przed zniżką ===");
        book1.showInfo();
        shirt.showInfo();

      
        book1.applyDiscount(10);   
        shirt.applyDiscount(20);  

    
        System.out.println("\n=== Po zniżce ===");
        book1.showInfo();
        shirt.showInfo();
    }

}

