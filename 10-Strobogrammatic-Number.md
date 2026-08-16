This is **LeetCode 246 — Strobogrammatic Number**. The key idea is actually very similar to the **two-pointer palindrome problems** you've been practicing, except instead of asking:

> `left == right`

we ask:

> **Does `left` rotate into `right`?**

([LeetCode][1])

### 1. First understand the rotation

Only these digits are valid when rotated 180°:

| Digit | Rotates to |
| ----- | ---------- |
| `0`   | `0`        |
| `1`   | `1`        |
| `6`   | `9`        |
| `8`   | `8`        |
| `9`   | `6`        |

So the valid **pairs from outside to inside** are:

```text
00
11
69
88
96
```

Everything else is invalid.

For example:

```text
"69"

6 → 9
9 → 6

        69
       ↙  ↘
      9    6

After rotation + reversal → 69
```

So `"69"` is valid.

---

## 2. Why two pointers?

Think about `"619"`:

```text
6 1 9
↑   ↑
L   R
```

We need to check:

```text
6 → 9   ✅
```

Then move inward:

```text
  1
  ↑
 L/R
```

The middle digit must rotate to itself.

```text
1 → 1   ✅
```

Therefore `"619"` is valid.

This is exactly the same **outside → inside** thinking as palindrome problems.

---

## 3. The important difference from palindrome

For a normal palindrome:

```text
"1221"

1 == 1
2 == 2
```

For a strobogrammatic number:

```text
"6996"

6 → 9
9 → 6
```

So we don't compare the characters directly.

We compare their **rotation mapping**.

---

## 4. Java solution

I'd recommend a `HashMap` first because it makes the idea very clear:

```java
class Solution {
    public boolean isStrobogrammatic(String num) {

        Map<Character, Character> map = new HashMap<>();

        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        int left = 0;
        int right = num.length() - 1;

        while (left <= right) {

            char leftDigit = num.charAt(left);
            char rightDigit = num.charAt(right);

            if (!map.containsKey(leftDigit)) {
                return false;
            }

            if (map.get(leftDigit) != rightDigit) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
```

---

# Let's trace it carefully

Take:

```text
num = "619"
```

Initially:

```text
6 1 9
↑   ↑
L   R
```

### First iteration

```java
leftDigit = '6'
rightDigit = '9'
```

Map says:

```text
6 → 9
```

So:

```text
map.get('6') == '9'
```

✅ Good.

Move:

```java
left++;
right--;
```

Now:

```text
6 1 9
  ↑
 L/R
```

### Second iteration

```text
leftDigit = '1'
rightDigit = '1'
```

Map says:

```text
1 → 1
```

✅ Good.

Move again:

```text
left = 2
right = 0
```

Now:

```text
left > right
```

Loop ends.

```java
return true;
```

---

# What happens with `"962"`?

```text
9 6 2
↑   ↑
L   R
```

We check:

```text
9 → 6
```

That's valid.

Move inward:

```text
9 6 2
  ↑
 L/R
```

Now:

```text
6 → 6 ?
```

But:

```text
6 → 9
```

❌

Therefore:

```java
return false;
```

This matches the example given by LeetCode. ([LeetCode][1])

---

# One subtle point: why `left <= right`?

This is important.

Consider:

```text
"88"
```

We check:

```text
8 → 8
```

Then:

```text
left = 1
right = 0
```

Done.

But consider an **odd-length** number:

```text
"818"

8 1 8
↑   ↑
L   R
```

First:

```text
8 → 8 ✅
```

Then:

```text
  1
  ↑
 L/R
```

We still need to check the middle digit.

```text
1 → 1 ✅
```

That's why:

```java
while (left <= right)
```

and **not**

```java
while (left < right)
```

The middle digit matters.

Only:

```text
0 → 0
1 → 1
8 → 8
```

can be valid in the middle.

For example:

```text
"619"
```

is valid, but:

```text
"696"
```

is not, because the middle `9` would need to become itself, but:

```text
9 → 6
```

❌

---

## The mental model I want you to remember

Don't memorize the code.

Think:

> **"This is a palindrome-like two-pointer problem, but instead of checking whether the two characters are equal, I check whether the left character's 180° rotation equals the right character."**

So:

```text
Palindrome:

left == right
```

versus:

```text
Strobogrammatic:

rotate(left) == right
```

And the rotation table is:

```text
0 → 0
1 → 1
6 → 9
8 → 8
9 → 6
```

That's essentially the entire problem. ([LeetCode][2])

**Time:** `O(n)`
**Space:** `O(1)` — the map has only 5 fixed entries. ([LeetCode][2])

[1]: https://leetcode.com/problems/strobogrammatic-number/?utm_source=chatgpt.com "Strobogrammatic Number - LeetCode"
[2]: https://leetcode.doocs.org/en/lc/246/?utm_source=chatgpt.com "246. Strobogrammatic Number 🔒 - LeetCode Wiki"
