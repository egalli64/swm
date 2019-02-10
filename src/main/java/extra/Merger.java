package extra;

import java.util.ArrayList;
import java.util.List;

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

    public static Node merge(Node lhs, Node rhs) {
        if (lhs == null) {
            return rhs;
        }
        if (rhs == null) {
            return lhs;
        }

        List<Node> nodes = new ArrayList<>();

        while (lhs != null && rhs != null) {
            if (lhs.getValue() < rhs.getValue()) {
                nodes.add(lhs);
                lhs = lhs.getNext();
            } else {
                nodes.add(rhs);
                rhs = rhs.getNext();
            }
        }

        Node head = nodes.get(0);
        Node cur = head;
        for (int i = 1; i < nodes.size(); i++) {
            cur.setNext(nodes.get(i));
            cur = cur.getNext();
        }

        cur.setNext(lhs == null ? rhs : lhs);
        return head;
    }
}
