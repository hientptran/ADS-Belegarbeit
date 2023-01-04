package budgetapp.budget;

import java.util.Comparator;

public class ComparatorAmount implements Comparator<Transaction> {
@Override
    public int compare(Transaction t1, Transaction t2) {
            return (int) (t1.getAmount() - t2.getAmount());
            }
}
