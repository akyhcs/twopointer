Sure. Since you already understand the two-pointer idea, here's the clean **Java solution** using a dummy node:

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val;
 *         this.next = next;
 *     }
 * }
 */

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Dummy node handles the case where we need to remove the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode left = dummy;
        ListNode right = dummy;

        // Create a gap of n nodes
        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        // Move both pointers until right reaches the last node
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }

        // left is now the node BEFORE the node we want to remove
        left.next = left.next.next;

        return dummy.next;
    }
}
```

### One important detail

Notice that we start both pointers at the **dummy**:

```text
dummy → 1 → 2 → 3 → 4 → 5
  ↑
left
right
```

This is particularly useful for:

```text
head → 1 → 2 → 3
n = 3
```

We need to remove `1`.

Without a dummy node, there is no "previous node" of `1`.

With the dummy:

```text
dummy → 1 → 2 → 3
   ↑     ↑
 left   target
```

So we can simply do:

```java
left.next = left.next.next;
```

which becomes:

```text
dummy → 2 → 3
```

and finally:

```java
return dummy.next;
```

returns `2`.

**Time:** `O(L)`
**Space:** `O(1)`
