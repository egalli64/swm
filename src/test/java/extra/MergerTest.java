package extra;

import static org.junit.Assert.*;
import org.junit.Test;

public class MergerTest {
    @Test
    public void mergePlain() {
        Node left = new Node(1, new Node(2, new Node(3)));
        Node right = new Node(2, new Node(3, new Node(4)));
        Node merged = Merger.merge(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeReversedPlain() {
        Node left = new Node(1, new Node(2, new Node(3)));
        Node right = new Node(2, new Node(3, new Node(4)));
        Node merged = Merger.mergeReversed(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeRecursivePlain() {
        Node left = new Node(1, new Node(2, new Node(3)));
        Node right = new Node(2, new Node(3, new Node(4)));
        Node merged = Merger.mergeRecursive(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeLeftNull() {
        Node left = null;
        Node right = new Node(2, new Node(3, new Node(4)));
        Node merged = Merger.merge(left, right);

        int cur = merged.getValue();
        assertEquals(2, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeReversedLeftNull() {
        Node left = null;
        Node right = new Node(2, new Node(3, new Node(4)));
        Node merged = Merger.mergeReversed(left, right);

        int cur = merged.getValue();
        assertEquals(2, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeRecursiveLeftNull() {
        Node left = null;
        Node right = new Node(2, new Node(3, new Node(4)));
        Node merged = Merger.mergeRecursive(left, right);

        int cur = merged.getValue();
        assertEquals(2, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeRightOne() {
        Node left = new Node(1, new Node(2, new Node(3)));
        Node right = new Node(4);
        Node merged = Merger.merge(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeReversedRightOne() {
        Node left = new Node(1, new Node(2, new Node(3)));
        Node right = new Node(4);
        Node merged = Merger.mergeReversed(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeRecursiveRightOne() {
        Node left = new Node(1, new Node(2, new Node(3)));
        Node right = new Node(4);
        Node merged = Merger.mergeRecursive(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeReversedLeftOne() {
        Node right = new Node(1, new Node(2, new Node(3)));
        Node left = new Node(4);
        Node merged = Merger.mergeReversed(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeLeftOne() {
        Node right = new Node(1, new Node(2, new Node(3)));
        Node left = new Node(4);
        Node merged = Merger.merge(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }

    @Test
    public void mergeRecursiveLeftOne() {
        Node right = new Node(1, new Node(2, new Node(3)));
        Node left = new Node(4);
        Node merged = Merger.mergeRecursive(left, right);

        int cur = merged.getValue();
        assertEquals(1, cur);
        while ((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(4, cur);
    }
}
