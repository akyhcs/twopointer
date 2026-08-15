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

----

Sure. Here is the **complete Java code using the two-pointer approach**:

```java
class Solution {

    public Node lowestCommonAncestor(Node p, Node q) {

        Node a = p;
        Node b = q;

        while (a != b) {

            if (a == null) {
                a = q;
            } else {
                a = a.parent;
            }

            if (b == null) {
                b = p;
            } else {
                b = b.parent;
            }
        }

        return a;
    }
}
```

### Let's trace it once

Suppose:

```text
        3
       / \
      5   1
     /
    6
```

And:

```text
p = 6
q = 1
```

Initially:

```text
a = 6
b = 1
```

Then:

```text
a: 6 → 5 → 3 → null → 1 → 3
b: 1 → 3 → null → 6 → 5 → 3
```

Eventually:

```text
a = 3
b = 3
```

So:

```java
while (a != b)
```

becomes false, and:

```java
return a;
```

returns:

```text
3
```

### Complexity

```text
Time  : O(H)
Space : O(1)
```

The important pattern to remember is:

```java
if (a == null)
    a = q;
else
    a = a.parent;
```

and similarly for `b`.

**When one pointer reaches the root (`null`), make it start from the other node.** This equalizes the distance traveled by the two pointers.


That's why the **two-pointer solution works with O(1) extra space**.
-------

Sure. Here is the **complete Java code using the two-pointer approach**:

```java
class Solution {

    public Node lowestCommonAncestor(Node p, Node q) {

        Node a = p;
        Node b = q;

        while (a != b) {

            if (a == null) {
                a = q;
            } else {
                a = a.parent;
            }

            if (b == null) {
                b = p;
            } else {
                b = b.parent;
            }
        }

        return a;
    }
}
```

### Let's trace it once

Suppose:

```text
        3
       / \
      5   1
     /
    6
```

And:

```text
p = 6
q = 1
```

Initially:

```text
a = 6
b = 1
```

Then:

```text
a: 6 → 5 → 3 → null → 1 → 3
b: 1 → 3 → null → 6 → 5 → 3
```

Eventually:

```text
a = 3
b = 3
```

So:

```java
while (a != b)
```

becomes false, and:

```java
return a;
```

returns:

```text
3
```

### Complexity

```text
Time  : O(H)
Space : O(1)
```

The important pattern to remember is:

```java
if (a == null)
    a = q;
else
    a = a.parent;
```

and similarly for `b`.

**When one pointer reaches the root (`null`), make it start from the other node.** This equalizes the distance traveled by the two pointers.
----------

Exactly — **yes, it will travel toward the root again**, and that's actually okay. The important part is that it **doesn't continue forever**.

Let's trace it carefully.

Suppose:

```text
        10
       /  \
      5    20
     / 
    3
   /
  2
```

Let:

```text
p = 2
q = 20
LCA = 10
```

We start:

```text
a = 2
b = 20
```

### First round

```text
a = 2  → 3
b = 20 → 10
```

### Second round

```text
a = 3  → 5
b = 10 → null
```

Now `b` has reached `null`.

So:

```java
b = p;
```

Therefore:

```text
b = 2
```

Yes, **`b` is now going upward from `p` again**.

At the same time, `a` continues:

```text
a: 5 → 10 → null
```

Let's continue:

```text
a = 5
b = 2
```

Then:

```text
a = 10
b = 3
```

Then:

```text
a = null
b = 5
```

Now:

```java
a = q;
```

So:

```text
a = 20
```

Now the paths look like:

```text
a: 2 → 3 → 5 → 10 → null → 20 → 10
b: 20 → 10 → null → 2 → 3 → 5 → 10
```

Eventually:

```text
a = 10
b = 10
```

and the loop stops.

---

### But your question is the important one:

> "If `a = q`, won't it travel to root again?"

**Yes! It does.**

That's not a problem because the algorithm is intentionally making both pointers travel:

```text
p-path + q-path
```

and

```text
q-path + p-path
```

So they have traveled the **same total distance**.

Think of it like two runners:

```text
Runner A:
starts at P → reaches end → starts at Q

Runner B:
starts at Q → reaches end → starts at P
```

They each run:

```text
distance(P → root) + distance(Q → root)
```

Therefore, when their paths merge, they arrive at the merge point at the same time.

### The key thing to understand

Don't think:

> "When `a = q`, I'm restarting the search."

Think:

> **"I'm making `a` complete the distance that `b` had already covered."**

That's the whole trick.

And yes, **this is why the two-pointer solution can feel confusing initially**. Once you see the paths as two linked lists and understand the equal-distance idea, the code becomes much easier to remember.

