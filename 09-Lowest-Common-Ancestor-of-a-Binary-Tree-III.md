 the **two-pointer approach is the most elegant solution** for Binary Tree III.

The key is to realize that because every node has a `parent`, each node gives us a linked-list-like path upward.

```text
        3
       / \
      5   1
     /
    6
```

Suppose:

```text
p = 6
q = 1
```

Their parent paths are:

```text
p: 6 → 5 → 3 → null

q: 1 → 3 → null
```

We use two pointers:

```java
Node a = p;
Node b = q;
```

Then:

```java
while (a != b) {

    if (a == null)
        a = q;
    else
        a = a.parent;

    if (b == null)
        b = p;
    else
        b = b.parent;
}

return a;
```

### Why switch them?

This is the clever part.

`a` travels:

```text
6 → 5 → 3 → null → 1 → 3
```

`b` travels:

```text
1 → 3 → null → 6 → 5 → 3
```

Both pointers have now traveled the **same total distance**.

So they eventually meet at:

```text
3
```

which is the LCA.

### Think of it exactly like this

```text
Path from p:
p → parent → parent → ... → root

Path from q:
q → parent → parent → ... → root
```

These two paths eventually **merge**.

So this problem can be treated almost exactly like:

> **Find the intersection point of two linked lists.**

That's why the **two-pointer solution works with O(1) extra space**.

