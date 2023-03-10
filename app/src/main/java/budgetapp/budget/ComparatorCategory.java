package budgetapp.budget;

import java.util.Comparator;

public class ComparatorCategory implements Comparator<Transaction> {
    @Override
    public int compare(Transaction t1, Transaction t2) {
        return t1.getCategory().compareTo(t2.getCategory());
    }
}
