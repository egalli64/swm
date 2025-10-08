package extra;

import java.util.Iterator;
import java.util.LinkedList;

class Node {
    private int value;
    private Node next;

    protected Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + ", next=" + next + "]";
    }
}

public class Merger {
    public static Node mergeRecursive(Node lhs, Node rhs) {
        if (lhs == null) {
            return rhs;
        }
        if (rhs == null) {
            return lhs;
        }

        Node cur = null;
        if (lhs.getValue() < rhs.getValue()) {
            cur = lhs;
            cur.setNext(mergeRecursive(lhs.getNext(), rhs));
        } else {
            cur = rhs;
            cur.setNext(mergeRecursive(lhs, rhs.getNext()));
        }
        return cur;
    }

    public static Node mergeReversed(Node lhs, Node rhs) {
        if (lhs == null) {
            return rhs;
        }
        if (rhs == null) {
            return lhs;
        }

        LinkedList<Node> nodes = new LinkedList<>();

        while (lhs != null && rhs != null) {
            if (lhs.getValue() < rhs.getValue()) {
                nodes.add(lhs);
                lhs = lhs.getNext();
            } else {
                nodes.add(rhs);
                rhs = rhs.getNext();
            }
        }

        Node tail = lhs == null ? rhs : lhs;
        nodes.removeLast().setNext(tail);

        Iterator<Node> it = nodes.descendingIterator();
        while (it.hasNext()) {
            Node cur = it.next();
            cur.setNext(tail);
            tail = cur;
        }
        return nodes.getFirst();
    }

    public static Node merge(Node lhs, Node rhs) {
        if (lhs == null) {
            return rhs;
        }
        if (rhs == null) {
            return lhs;
        }

        Node head;
        if (lhs.getValue() < rhs.getValue()) {
            head = lhs;
            lhs = lhs.getNext();
        } else {
            head = rhs;
            rhs = rhs.getNext();
        }

        Node cur = head;
        while (lhs != null && rhs != null) {
            if (lhs.getValue() < rhs.getValue()) {
                cur.setNext(lhs);
                lhs = lhs.getNext();
            } else {
                cur.setNext(rhs);
                rhs = rhs.getNext();
            }
            cur = cur.getNext();
        }

        cur.setNext(lhs == null ? rhs : lhs);
        return head;
    }
}
