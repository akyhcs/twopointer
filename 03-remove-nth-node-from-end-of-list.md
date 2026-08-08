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



-------------------

**`left` will point to the previous node of the node to be removed.** ✅

That's the key detail of this implementation.

Let's walk through it.

Suppose:

```text
1 → 2 → 3 → 4 → 5 → null
n = 2
```

We want to remove `4`.

### Before the `while`

Both start at `dummy`:

```text
dummy → 1 → 2 → 3 → 4 → 5 → null
  ↑
 left/right
```

We move `right` `n` times:

```text
dummy → 1 → 2 → 3 → 4 → 5 → null
  ↑           ↑
 left        right
```

So the gap between them is `2`.

---

### Now execute:

```java
while (right.next != null) {
    left = left.next;
    right = right.next;
}
```

#### Iteration 1

```text
dummy → 1 → 2 → 3 → 4 → 5 → null
         ↑               ↑
        left            right
```

#### Iteration 2

```text
dummy → 1 → 2 → 3 → 4 → 5 → null
             ↑               ↑
            left            right
```

Now:

```java
right.next == null
```

so the loop stops.

Therefore:

```text
left → 3
right → 5
```

And the target is:

```text
left.next → 4
```

So:

```java
left.next = left.next.next;
```

means:

```text
3.next = 4.next
```

which gives:

```text
1 → 2 → 3 → 5
```

### The important mental model

At the end of this loop:

```text
left
 ↓
3 → 4 → 5 → null
    ↑
   target
```

So:

> **`left` = previous node**
> **`left.next` = node to remove**
> **`left.next.next` = node after the target**

That's why the deletion is:

```java
left.next = left.next.next;
```

This distinction is probably the **most important thing to remember** for this problem.

