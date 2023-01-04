package budgetapp.budget;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transaction {
    private LocalDate date;
    public LocalDate getDate() {
        return date;
    }
    private String name;
    public String getName() {
        return name;
    }
    private String category;
    public String getCategory() {
        return category;
    }
    private double amount;
    public double getAmount() {
        return amount;
    }
    public Transaction(String date, String name, String category, double amount) {
        this.date = convertDate(date);
        this.name = name;
        this.category = category;
        this.amount = amount;
    }
    private LocalDate convertDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        return LocalDate.parse(string, formatter);
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = this.date.format(formatter);
        return String.format("%15s | %-30s | %-20s | %10.3f", date, name, category, amount);
    }
}
