Yes — this is **LeetCode 408: Valid Word Abbreviation**. ([LeetCode][1])

The important thing about this problem is that it is mainly a **two-pointer/string parsing problem**. Don't overcomplicate it.

### Problem idea

Given:

```text
word = "internationalization"
abbr = "i12iz4n"
```

We need to check whether `abbr` correctly represents `word`.

For example:

```text
word = "substitution"
abbr = "s10n"
```

Break the abbreviation:

```text
s + 10 + n
```

`10` means **skip 10 characters** in `word`.

---

## How I would think about it in an interview

Use two pointers:

```text
i → word
j → abbr
```

At every step, look at `abbr[j]`.

### Case 1: It's a letter

Then it must exactly match:

```java
word.charAt(i) == abbr.charAt(j)
```

If it matches:

```text
i++
j++
```

---

### Case 2: It's a digit

Now we need to read the **entire number**.

For example:

```text
abbr = "i12iz4n"
       ^
```

We shouldn't process `1` and `2` separately.

We need:

```text
12
```

Then move `i` forward by 12.

So:

```java
int num = 0;

while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
    num = num * 10 + (abbr.charAt(j) - '0');
    j++;
}

i += num;
```

The key line is:

```java
num = num * 10 + digit;
```

For `"12"`:

```text
num = 0

read 1:
num = 0 * 10 + 1
    = 1

read 2:
num = 1 * 10 + 2
    = 12
```

---

## One important edge case

**Leading zero is invalid.**

For example:

```text
word = "apple"
abbr = "a02e"
```

`02` should not be accepted.

So before reading the number:

```java
if (abbr.charAt(j) == '0') {
    return false;
}
```

---

## Java solution

```java
public boolean validWordAbbreviation(String word, String abbr) {

    int i = 0;
    int j = 0;

    while (i < word.length() && j < abbr.length()) {

        // Letter
        if (Character.isLetter(abbr.charAt(j))) {

            if (word.charAt(i) != abbr.charAt(j)) {
                return false;
            }

            i++;
            j++;
        }

        // Number
        else {

            // Leading zero is invalid
            if (abbr.charAt(j) == '0') {
                return false;
            }

            int num = 0;

            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                num = num * 10 + (abbr.charAt(j) - '0');
                j++;
            }

            i += num;
        }
    }

    return i == word.length() && j == abbr.length();
}
```

### Walk through `"substitution"` + `"s10n"`

```text
word: s u b s t i t u t i o n
      ^
abbr: s 1 0 n
      ^
```

`'s'` → matches:

```text
i = 1
j = 1
```

Now `abbr[j]` is `1`, so we know a number is starting.

Read:

```text
10
```

Move `i` by 10:

```text
i = 11
```

Now:

```text
word[11] = 'n'
abbr[j] = 'n'
```

Match.

Finally:

```text
i == word.length()
j == abbr.length()
```

Therefore:

```text
true
```

### The main pattern to remember

This problem teaches a very useful pattern:

```text
while (j < abbreviation.length()) {

    if (letter) {
        compare
        move both
    }
    else {
        parse the complete number
        move word pointer by that number
    }
}
```

The **most important thinking step** is recognizing that a sequence of digits represents **one number**, not individual operations.

[1]: https://leetcode.com/problems/valid-word-abbreviation/description/ "Valid Word Abbreviation - LeetCode"
-------
Yes. In Java, **you don't need an explicit type cast** here.

```java
int digit = abbr.charAt(j) - '0';
```

This works because both:

```java
abbr.charAt(j)
```

and

```java
'0'
```

are `char`s.

When you perform arithmetic on `char`s, Java **promotes them to `int`** automatically.

### Example

Suppose:

```java
char c = '7';
```

Internally:

```text
'7' → 55
'0' → 48
```

So:

```java
c - '0'
```

becomes:

```text
55 - 48 = 7
```

Therefore:

```java
int digit = abbr.charAt(j) - '0';
```

is perfectly valid.

You **don't** need:

```java
int digit = (int)(abbr.charAt(j) - '0');
```

In fact, that cast is redundant.

And this is why we use:

```java
num = num * 10 + (abbr.charAt(j) - '0');
```

to convert each digit character into its numeric value.
-----------------------------------

return i == word.length() && j == abbr.length(); what will happen if i directly return true ?

If you mean replacing:

```java
return i == word.length() && j == abbr.length();
```

with:

```java
return true;
```

then **it would be wrong**.

The reason is that reaching the end of the `while` loop does **not necessarily mean the abbreviation is valid**.

### Example 1

```text
word = "apple"
abbr = "a4"
```

Processing:

```text
'a' → matches
'4' → skip 4 characters
```

Now:

```text
i = 5   // end of word
j = 2   // end of abbr
```

Valid → `true`.

---

### But consider:

```text
word = "apple"
abbr = "a5"
```

Processing:

```text
'a' → matches
'5' → skip 5
```

Now:

```text
i = 6
j = 2
```

Notice:

```text
i > word.length()
```

The abbreviation tried to skip **more characters than exist**.

The loop stops because:

```java
i < word.length()
```

is now false.

If you simply did:

```java
return true;
```

you would incorrectly say this is valid.

---

### That's why we check both

```java
return i == word.length() && j == abbr.length();
```

It means:

> **Did we consume exactly the entire word AND exactly the entire abbreviation?**

For:

```text
word = "apple"
abbr = "a5"
```

we get:

```text
i = 6
word.length() = 5

j = 2
abbr.length() = 2
```

So:

```java
i == word.length()   // false
j == abbr.length()   // true

false && true
= false
```

Therefore:

```text
"a5" → invalid
```

### The important idea

Don't think:

> "The loop ended, therefore it's valid."

Think:

> **"The loop ended. Now I need to verify that both strings were consumed exactly."**

That's why the final condition is important.

