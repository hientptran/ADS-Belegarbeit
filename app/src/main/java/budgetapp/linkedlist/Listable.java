package budgetapp.linkedlist;

public interface Listable<Item> {

    /* add item to the end of the list */
    void add(Item item);

    /* add item at the specified index */
    void add(Item item, int index);

    /* clear all elements in the list */
    void clear();

    /* remove element at the specified index and return removed item */
    Item remove(int index);

    /* replace element at specified index with the new element*/
    void set(int index, Item item);
    Item get(int index);
    /* return the size of the list */
    int size();
    /*  print all elements of the list */
    void printAll();
    /*  check if list contains element */
    boolean contains(Item data);
}

