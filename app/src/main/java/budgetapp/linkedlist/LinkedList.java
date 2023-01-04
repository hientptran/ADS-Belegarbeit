package budgetapp.linkedlist;

import java.util.Comparator;

//Singly linked list
public class LinkedList<Item> implements Listable<Item> {
    public Node head;
    public int size;
    public class Node {
        public Item data;
        public Node next;
        public Node(Item data) {
            this.data = data;
            this.next = null;
        }
    }
    @Override
    public void add(Item data) {
        /*  Create new node and set next as null */
        Node node = new Node(data);
        node.next = null;
        /*  if the list is empty set head as the new node */
        if (head == null) {
            head = node;
        }
        /*  else traverse the list until the last node
            and change the next node of the last node to the new node */
        else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
        size++;
    }
    @Override
    public void add(Item data, int index) {
        if (index > size || index < 0) {
            System.out.print("Invalid index!\n");
        }
        else {
            /* if index is 0 add element to the beginning of list and set head as new element */
            if (index == 0) {
                Node newNode = new Node(data);
                newNode.next = head;
                head = newNode;
            }
            /* Traverse the list until the specified index */
            else {
                int i = 0;
                Node node = head;
                while (i < index - 1) {
                    node = node.next;
                    i++;
                }
                Node newNode = new Node(data);
                newNode.next = node.next;
                node.next = newNode;
            }
            size++;
        }
    }
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    @Override
    public Item remove(int index) {
        if (index > size - 1 || index < 0) {
            System.out.print("Invalid index!\n");
            return null;
        }
        else {
            if (index == 0) {
                Node result = head;
                head = head.next;
                return result.data;
            }
            else {
                int i = 0;
                Node node = head;
                while (i < index - 1) {
                    node = node.next;
                    i++;
                }
                Node result = node.next;
                node.next = node.next.next;
                size--;
                return result.data;
            }
        }
    }
    @Override
    public void set(int index, Item data) {
        if (index > size - 1 || index < 0) {
            System.out.print("Invalid index!\n");
        }
        else {
            if (index == 0) {
                Node node = new Node(data);
                node.next = head.next;
                head = node;
            } else {
                int i = 0;
                Node node = head;
                while (i < index - 1) {
                    node = node.next;
                    i++;
                }
                Node result = node.next;
                result.data = data;
            }
        }
    }
    @Override
    public Item get(int index) {
        Item result = null;
        if (index > size || index < 0) {
            System.out.print("Invalid index!\n");
        }
        else {
            if (index == 0) {
                result = head.data;
            } else {
                int i = 0;
                Node node = head;
                while (i < index) {
                    node = node.next;
                    i++;
                }
                result = node.data;
            }
        }
        return result;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printAll() {
        Node node = head;
        int i = 0;
        while (node.next != null) {
            System.out.printf("%3d| ", i);
            System.out.print(node.data + "\n");
            node = node.next;
            i++;
        }
        System.out.printf("%3d| ", i);
        System.out.print(node.data);
    }
    @Override
    public boolean contains(Item data) {
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (node.data.equals(data)) {
                return true;
            } else {
                node = node.next;
            }
        }
        return false;
    }
    public Node clone() {
        Node current = head;    // used to iterate over the original list
        Node clone = null;      // head of the new list
        Node tail = null;       // last node in the new list

        while (current != null) {
            if (clone == null) {
                clone = new Node(current.data);
                tail = clone;
            }
            else {
                tail.next = new Node(current.data);
                tail = tail.next;
                tail.next = null;
            }
            current = current.next;
        }
        return clone;
    }
    private Node partitionLast(Node start, Node end, Comparator<Item> comparator) {
        if (start == end || start == null || end == null)
            return start;

        Node pivot_prev = start;
        Node current = start;
        Item pivot = end.data;
        while (start != end) {
            if (comparator.compare(start.data, pivot) < 0) {
                pivot_prev = current;
                Item temp = current.data;
                current.data = start.data;
                start.data = temp;
                current = current.next;
            }
            start = start.next;
        }

        Item temp = current.data;
        current.data = pivot;
        end.data = temp;
        return pivot_prev;
    }
    public void quicksort(Node start, Node end, Comparator<Item> comparator) {
        if(start == null || start == end|| start == end.next )
            return;
        Node pivot_prev = partitionLast(start, end, comparator);
        quicksort(start, pivot_prev, comparator);
        if (pivot_prev != null && pivot_prev == start)
            quicksort(pivot_prev.next, end, comparator);
        else if (pivot_prev != null
                && pivot_prev.next != null)
            quicksort(pivot_prev.next.next, end, comparator);
    }

}
