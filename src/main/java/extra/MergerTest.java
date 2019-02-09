package extra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MergerTest {
    @Test
    void mergePlain() {
        Node left = new Node(1, new Node(2, new Node(3)));
        Node right = new Node(2, new Node(3, new Node(4)));
        Node merged = Merger.merge(left, right);

        int cur = merged.getValue();
        assertEquals(cur, 1);
        while((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(cur, 4);
    }

    @Test
    void mergeLeftNull() {
        Node left = null;
        Node right = new Node(2, new Node(3, new Node(4)));
        Node merged = Merger.merge(left, right);

        int cur = merged.getValue();
        assertEquals(cur, 2);
        while((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(cur, 4);
    }

    @Test
    void mergeRightOne() {
        Node left = new Node(1, new Node(2, new Node(3)));
        Node right = new Node(4);
        Node merged = Merger.merge(left, right);

        int cur = merged.getValue();
        assertEquals(cur, 1);
        while((merged = merged.getNext()) != null) {
            int prev = cur;
            cur = merged.getValue();
            assertFalse(cur < prev);
        }
        assertEquals(cur, 4);
    }

}
