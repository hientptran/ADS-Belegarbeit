package budgetapp.budget;

import budgetapp.linkedlist.LinkedList;
import com.opencsv.CSVWriter;
import budgetapp.console.Console;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TransactionList extends LinkedList<Transaction> {
    private final Console c = new Console();
    private final String separator1 = ("\n---|-----------------|--------------------------------|----------------------|-------------");
    private final String separator2 = ("\n-------------------------------------------------------------------------------------------");
    /* ----------------BALANCE-------------------*/
    private double balance;
    /* ----------------CATEGORIES----------------*/
    static LinkedList<String> categories = new LinkedList<>();
    /* Load default categories from file */
    public void importDefaultCategories() throws FileNotFoundException {
        File input = new File("categories.csv");
        Scanner s = new Scanner(input);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            categories.add(line);
        }
    }
    public void createCategory(String data) {
        categories.add(data);
    }
    public String addCategory() {
        String data = c.readString("Enter new category: ");
        if (!categories.contains(data)) {
            createCategory(data);
            c.printString("Category added!");
        } else {
            c.printString("Category exists!");
        }
        return data;
    }
    public void showStatistics() {
        c.printString(separator2);
        LinkedList<String>.Node cNode = categories.head;
        LinkedList<String> stats = new LinkedList<>();
        while (cNode != null) {
            Node node = head;
            String currCategory = cNode.data;
            double currBalance = 0;
            while (node != null) {
                if (node.data.getCategory().toLowerCase().contains(currCategory.toLowerCase())) {
                    currBalance += node.data.getAmount();
                }
                node = node.next;
            }
            stats.add(String.format("%20s | %20.3f", currCategory, currBalance));
            cNode = cNode.next;
        }
        stats.printAll();
        c.printString(separator2);
    }

    /* ----------------TRANSACTIONS----------------*/
    /* ----------------basics----------------------*/
    public void addTransaction() {
        Transaction newTransaction = inputTransaction();
        /* Update budget */
        balance += newTransaction.getAmount();
        super.add(newTransaction);
        c.printString("Transaction added!");
    }
    public void removeTransaction() {
        printAll();
        int choice = c.readInt("Choose a transaction to remove: ", 0, size()-1);
        Node node = head;
        for (int i = 0; i < choice; i++) {
            node = node.next;
        }
        Transaction t = node.data;
        /* Update transaction list and budget */
        //setBalance(getBalance() - t.getAmount());
        balance -= t.getAmount();
        super.remove(choice);
        c.printString("Transaction removed!");
    }
    public void editTransaction() {
        printAll();
        int choice = c.readInt("\nChoose a transaction to edit: ", 0, size()-1);
        Node node = head;
        for (int i = 0; i < choice; i++) {
            node = node.next;
        }
        Transaction t = node.data;
        c.printString("\nOriginal transaction: ");
        System.out.println(t);
        Transaction newTransaction = inputTransaction();
        balance = balance - t.getAmount() + newTransaction.getAmount();
        super.set(choice, newTransaction);
        c.printString("Transaction edited!");
    }
    public Transaction inputTransaction() {
        String date = c.readString("Enter date (yyyy-MM-dd) [default : today]", "^(19|20)\\d\\d[- \\/.](0[1-9]|1[012])[- \\/.](0[1-9]|[12][0-9]|3[01])$");
        if (date.equals("")){
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = localDate.format(formatter);
        }
        String name = c.readString("Enter name:");
        String category = "";
        if (categories.size() != 0) {
            categories.printAll();
        }
        int add = categories.size();
        System.out.printf("\n%3d| Add category\n", add);
        int categoryChoice = c.readInt("Enter/add category:", 0, categories.size());
        if (categoryChoice >= 0 && categoryChoice < add) {
            category = categories.get(categoryChoice);
        }
        else if (categoryChoice == categories.size()){
            category = addCategory();
        }
        double amount = c.readDouble("Enter amount:");
        return new Transaction(date, name, category, amount);
    }
    @Override
    public void printAll() {
        String header = String.format("\n ID| %15s | %-30s | %-20s | %10s", "Date", "Name", "Category", "Amount");
        if (head != null) {
            System.out.println(separator1 + header + separator1);
            super.printAll();
            System.out.println(separator1);
            System.out.printf("%80s: %.3f", "SUM", balance);
            System.out.println(separator2);
        } else {
            System.out.println(separator2);
            System.out.print("\nNO TRANSACTIONS AVAILABLE :(");
            System.out.println(separator2);
        }
    }
    /* ----------------sort-------------------------*/
    public TransactionList sortByAmountAscending() {
        Node headClone = super.clone();
        Node endClone = headClone;
        while (endClone.next != null) {
             endClone = endClone.next;
        }
        ComparatorAmount comparator = new ComparatorAmount();
        super.quicksort(headClone, endClone, comparator);
        TransactionList sorted = new TransactionList();
        sorted.head = headClone;
        sorted.balance = balance;
        return sorted;
    }
    public TransactionList sortByDateAscending() {
        Node headClone = super.clone();
        Node endClone = headClone;
        while (endClone.next != null) {
            endClone = endClone.next;
        }
        ComparatorDate comparator = new ComparatorDate();
        super.quicksort(headClone, endClone, comparator);
        TransactionList sorted = new TransactionList();
        sorted.head = headClone;
        sorted.balance = balance;
        return sorted;
    }
    public TransactionList sortByNameAscending() {
        Node headClone = super.clone();
        Node endClone = headClone;
        while (endClone.next != null) {
            endClone = endClone.next;
        }
        ComparatorName comparator = new ComparatorName();
        super.quicksort(headClone, endClone, comparator);
        TransactionList sorted = new TransactionList();
        sorted.head = headClone;
        sorted.balance = balance;
        return sorted;
    }
    public TransactionList sortByCategoryAscending() {
        Node headClone = super.clone();
        Node endClone = headClone;
        while (endClone.next != null) {
            endClone = endClone.next;
        }
        ComparatorCategory comparator = new ComparatorCategory();
        super.quicksort(headClone, endClone, comparator);
        TransactionList sorted = new TransactionList();
        sorted.head = headClone;
        sorted.balance = balance;
        return sorted;
    }
    /* ----------------search-----------------------*/
    public static boolean isNumeric(String string) {
        if (string == null) {
            return false;
        }
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public TransactionList searchByName() {
        String query = c.readString("Enter name to search: ");
        TransactionList result = new TransactionList();
        Node node = head;
        while (node.next != null) {
            if (node.data.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(node.data);
                result.balance += node.data.getAmount();
            }
            node = node.next;
        }
        if (node.data.getName().toLowerCase().contains(query.toLowerCase())) {
            result.add(node.data);
            result.balance += node.data.getAmount();
        }
        return result;
    }
    public TransactionList searchByCategory() {
        categories.printAll();
        TransactionList result = new TransactionList();
        String categoryChoice = c.readString("\n Enter category to search: ");
        if (isNumeric(categoryChoice)) {
            int numericChoice = Integer.parseInt(categoryChoice);
            if (0 <= numericChoice && numericChoice <= categories.size) {
                String query = categories.get(numericChoice);
                Node node = head;
                while (node.next != null) {
                    if (node.data.getCategory().toLowerCase().contains(query.toLowerCase())) {
                        result.add(node.data);
                        result.balance += node.data.getAmount();
                    }
                    node = node.next;
                }
                if (node.data.getCategory().toLowerCase().contains(query.toLowerCase())) {
                    result.add(node.data);
                    result.balance += node.data.getAmount();
                }
            }
        }
        else {
            Node node = head;
            while (node.next != null) {
                if (node.data.getCategory().toLowerCase().contains(categoryChoice.toLowerCase())) {
                    result.add(node.data);
                    result.balance += node.data.getAmount();
                }
                node = node.next;
            }
            if (node.data.getCategory().toLowerCase().contains(categoryChoice.toLowerCase())) {
                result.add(node.data);
                result.balance += node.data.getAmount();
            }
        }
        return result;
    }
    public TransactionList searchByDate() {
        String query = c.readString("Enter date to search (yyyy-MM-dd) [default : today]: ", "^(19|20)\\d\\d[- \\/.](0[1-9]|1[012])[- \\/.](0[1-9]|[12][0-9]|3[01])$");
        if (query.equals("")){
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            query = localDate.format(formatter);
        }
        TransactionList result = new TransactionList();
        Node node = head;
        while (node.next != null) {
            if (LocalDate.parse(query).isEqual(node.data.getDate())) {
                result.add(node.data);
                result.balance += node.data.getAmount();
            }
            node = node.next;
        }
        if (LocalDate.parse(query).isEqual(node.data.getDate())) {
            result.add(node.data);
            result.balance += node.data.getAmount();
        }
        return result;
    }
    /* ----------------file i/o---------------------*/
    public void importTransactions() throws IOException {
        File input = new File("budget.csv");
        importDefaultCategories();
        Scanner s = new Scanner(input);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(",");
            String date = parts[0];
            String name = parts[1];
            String category = parts[2];
            if (!categories.contains(category)) {
                createCategory(category);
            }
            double amount = Double.parseDouble(parts[3]);
            Transaction t = new Transaction(date, name, category, amount);
            balance += t.getAmount();
            add(t);
        }
    }
    public void saveTransactions() {
        try {
            FileWriter output = new FileWriter("budget.csv");
            String[][] data = getRows();
            CSVWriter writer = new CSVWriter(output,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.RFC4180_LINE_END);
            for (String[] row : data) {
                if (row != null) {
                    writer.writeNext(row);
                }
            }
            c.printString("Transactions saved!");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String[][] getRows() {
        String[][] rows = new String[size()][4];
        Node node = head;
        int i = 0;
        while (node.next != null) {
            rows[i][0] = dateToString(node.data.getDate());
            rows[i][1] = node.data.getName();
            rows[i][2] = node.data.getCategory();
            rows[i][3] = String.valueOf(node.data.getAmount());
            node = node.next;
            i++;
        }
        rows[i][0] = dateToString(node.data.getDate());
        rows[i][1] = node.data.getName();
        rows[i][2] = node.data.getCategory();
        rows[i][3] = String.valueOf(node.data.getAmount());
        return rows;
    }
    public String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }


}
