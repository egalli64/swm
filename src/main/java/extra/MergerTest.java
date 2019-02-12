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
		assertEquals(1, cur);
		while ((merged = merged.getNext()) != null) {
			int prev = cur;
			cur = merged.getValue();
			assertFalse(cur < prev);
		}
		assertEquals(4, cur);
	}

	@Test
	void mergeReversedPlain() {
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
	void mergeRecursivePlain() {
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
	void mergeLeftNull() {
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
	void mergeReversedLeftNull() {
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
	void mergeRecursiveLeftNull() {
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
	void mergeRightOne() {
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
	void mergeReversedRightOne() {
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
	void mergeRecursiveRightOne() {
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
	void mergeReversedLeftOne() {
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
	void mergeLeftOne() {
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
	void mergeRecursiveLeftOne() {
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
