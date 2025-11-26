import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RejestrWydatkow {
    private static final String FILE_NAME = "wydatki.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Dodaj nowy wydatek");
            System.out.println("2. Pokaż historię wydatków");
            System.out.println("3. Oblicz sumę wydatków");
            System.out.println("4. Wyjdź");
            System.out.print("Wybierz opcję: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy wybór.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> dodajWydatek(scanner);
                    case 2 -> pokazHistorie();
                    case 3 -> obliczSume();
                    case 4 -> {
                        System.out.println("Do zobaczenia!");
                        return;
                    }
                    default -> System.out.println("Nieprawidłowy wybór.");
                }
            } catch (IOException e) {
                System.out.println("Błąd pliku: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Błędne dane: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Błąd: " + e.getMessage());
            } finally {
                System.out.println("Operacja zakończona pomyślnie.");
            }
        }
    }

    private static void dodajWydatek(Scanner scanner) throws IOException {
        System.out.print("Podaj opis: ");
        String opis = scanner.nextLine();

        System.out.print("Podaj kategorię: ");
        String kategoria = scanner.nextLine();

        System.out.print("Podaj kwotę: ");
        double kwota;
        try {
            kwota = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Kwota musi być liczbą.");
        }

        if (kwota <= 0) throw new IllegalArgumentException("Kwota musi być większa od zera!");

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DayOfWeek dzien = now.getDayOfWeek();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write("[" + now.format(formatter) + "] - Kategoria: " + kategoria +
                    " - Kwota: " + kwota + " - Opis: " + opis +
                    " (Dzień tygodnia: " + dzien + ")\n");
        }
        System.out.println("Wydatek został dodany.");
    }

    private static void pokazHistorie() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Brak wydatków do wyświetlenia.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("\n--- Historia wydatków ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private static void obliczSume() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Brak wydatków.");
            return;
        }
        double suma = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Kwota:")) {
                    String[] parts = line.split("Kwota:");
                    String kwotaStr = parts[1].split("-")[0].trim();
                    suma += Double.parseDouble(kwotaStr);
                }
            }
        }
        double zaokraglona = Math.round(suma * 100.0) / 100.0;
        System.out.println("Łączna suma wydatków: " + zaokraglona + " zł");
    }
}
