package extra;

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
    public static Node merge(Node lhs, Node rhs) {
        if (lhs == null) {
            return rhs;
        }
        if (rhs == null) {
            return lhs;
        }

        Node cur = null;
        if (lhs.getValue() < rhs.getValue()) {
            cur = lhs;
            cur.setNext(merge(lhs.getNext(), rhs));
        } else {
            cur = rhs;
            cur.setNext(merge(lhs, rhs.getNext()));
        }
        return cur;
    }
}
