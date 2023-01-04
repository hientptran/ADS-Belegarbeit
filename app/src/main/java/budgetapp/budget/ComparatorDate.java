package budgetapp.budget;

import java.util.Comparator;

public class ComparatorDate implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.getDate().compareTo(t2.getDate());
    }
}
