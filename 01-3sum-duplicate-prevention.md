

```java
class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
```

You said you understand everything until here.

Now let's look at **just the next line**.

---

# Line 1

```java
if (i > 0 && nums[i] == nums[i - 1])
    continue;
```

## First Question

Before reading the line, ask yourself:

> **What problem might occur if I don't write this line?**

Suppose after sorting we have

```text
[-4, -1, -1, 0, 1, 2]
```

The loop starts.

### First iteration

```text
i = 0

-4  -1  -1   0   1   2
 ^
```

We search for triplets starting with `-4`.

Everything is fine.

---

### Second iteration

```text
i = 1

-4  -1  -1   0   1   2
     ^
```

We search for triplets starting with the first `-1`.

Suppose we find

```text
[-1,0,1]
```

Great.

---

### Third iteration

```text
i = 2

-4  -1  -1   0   1   2
         ^
```

Notice something.

The value is again

```text
-1
```

If we start searching again...

We'll again find

```text
[-1,0,1]
```

So we'll produce duplicate answers.

---

## So what are we really checking?

Not

> Is the index same?

We're checking

> **Have I already processed this value before?**

If yes,

Don't do the same work again.

---

## Why `i > 0`?

Imagine `i = 0`.

Then

```java
nums[i - 1]
```

becomes

```java
nums[-1]
```

Does index `-1` exist?

No.

Java throws

```text
ArrayIndexOutOfBoundsException
```

So first we ask

```java
i > 0
```

Only then compare with the previous element.

---

## Why `continue`?

Think about what we want.

Do we want to stop the whole program?

No.

Do we want to stop this iteration?

Yes.

So

```java
continue;
```

means

> Ignore this `i`.

Go to the next value.

---

## Read the line like English

```java
if (i > 0 && nums[i] == nums[i - 1])
```

means

> "If this isn't the first element, and the current value is the same as the previous value..."

```java
continue;
```

means

> "...don't process it again."

---

# The programmer's intention

Whenever you see this line, don't memorize it.

Think:

> **"I have already explored all triplets starting with this number."**

Therefore,

```java
if (i > 0 && nums[i] == nums[i - 1])
```

is really saying

```text
Have I already processed this value?

YES

↓

Skip it.
```

---

# A good habit for reading DSA code

For every line, ask these three questions:

### Question 1

**What problem is this line preventing?**

Here:

Duplicate triplets.

---

### Question 2

**What would happen if I delete this line?**

Answer:

The algorithm still works, but the output contains duplicate triplets.

---

### Question 3

**Can I explain this line in plain English?**

Here:

> "If I've already used this value as the first element, don't use it again."

---

This is the mindset interviewers look for. They don't expect you to memorize code—they expect you to understand the purpose of each line.

---

I suggest we continue in the **same style** for the next lines:

```java
int left = i + 1;
int right = nums.length - 1;
```

These two lines look simple, but they encode the entire two-pointer strategy. Understanding *why* `left` starts at `i + 1` and *why* `right` starts at the end of the array is the key to understanding the rest of the algorithm.




I completely agree. In fact, I think this is a **better** way to document DSA code.

Don't explain every line in detail. Instead, use the template:

1. **Why was this line added?**
2. **What problem does it prevent?**
3. **What happens if we remove it?**

Let's do exactly that for these two `while` loops.

---

# Line

```java
while (left < right && nums[left] == nums[left - 1])
    left++;
```

## First Question

Before reading the line, ask yourself:

> **What problem might occur if I don't write this line?**

Suppose the sorted array is

```text
[-2, 0, 0, 0, 2]
```

Current pointers:

```text
-2   0   0   0   2
 ^    L       R
 i
```

Current sum:

```text
-2 + 0 + 2 = 0
```

We found a valid triplet.

```text
[-2,0,2]
```

So we add it to the result.

---

Now according to our algorithm we do

```java
left++;
right--;
```

Pointers become

```text
-2   0   0   0   2
 ^        L   R
 i
```

Notice something.

The new `left` is again

```text
0
```

If we continue searching...

We'll again produce

```text
[-2,0,2]
```

Exactly the same answer.

---

## So what are we really checking?

Not

> Is this the next index?

We're checking

> **Is this the same value that I just processed?**

If yes,

Don't generate the same triplet again.

---

## Why `left < right`?

Suppose

```text
left == right
```

There are no two numbers left to form a triplet.

So the search is over.

---

## Why `nums[left] == nums[left - 1]`?

Think about what happened.

We already used

```text
0
```

Now after doing `left++`

We're standing on another

```text
0
```

Using it again would generate the same triplet.

---

## Why `left++`?

We want to skip all duplicate values.

So keep moving until we reach a **new** number.

---

## Read the line like English

```java
while(left < right && nums[left] == nums[left - 1])
```

means

> "As long as there are elements left to search, and the current left value is the same as the previous left value..."

```java
left++;
```

means

> "...skip it because I've already used this value."

---

# The programmer's intention

Whenever you see this line, don't memorize it.

Think:

> **"I have already created all triplets using this left value."**

Therefore,

```java
while(left < right && nums[left] == nums[left - 1])
```

is really saying

```text
Have I already used this left value?

YES

↓

Skip it.
```

---

# A good habit for reading DSA code

### Question 1

**What problem is this line preventing?**

Duplicate triplets.

---

### Question 2

**What happens if I delete this line?**

The algorithm still works.

But you'll get duplicate answers like

```text
[-2,0,2]
[-2,0,2]
[-2,0,2]
```

---

### Question 3

**Can I explain it in plain English?**

> "I've already used this left value. Skip all identical values."

---

# Exactly the same idea for the right pointer

---

# Line

```java
while (left < right && nums[right] == nums[right + 1])
    right--;
```

## First Question

Before reading the line, ask yourself:

> **What problem might occur if I don't write this line?**

Suppose the sorted array is

```text
[-2, 0, 2, 2, 2]
```

Current pointers:

```text
-2   0   2   2   2
 ^    L           R
 i
```

Current sum:

```text
-2 + 0 + 2 = 0
```

We found

```text
[-2,0,2]
```

Store it.

Now move both pointers.

```java
left++;
right--;
```

Pointers become

```text
-2   0   2   2   2
 ^        L   R
 i
```

Notice something.

The new `right` is again

```text
2
```

If we continue searching...

We'll again produce

```text
[-2,0,2]
```

Exactly the same answer.

---

## So what are we really checking?

Not

> Did the pointer move?

We're checking

> **Am I standing on the same value again?**

If yes,

Skip it.

---

## Why `nums[right] == nums[right + 1]`?

Notice the comparison.

We're comparing the current value with the one we **just left**.

If they're equal,

We've already processed this value.

---

## Why `right--`?

Move left until we reach a different value.

---

## Read the line like English

```java
while(left < right && nums[right] == nums[right + 1])
```

means

> "As long as there are still elements to search, and the current right value is the same as the value I just processed..."

```java
right--;
```

means

> "...skip it because it would generate the same triplet."

---

# The programmer's intention

Whenever you see this line, think:

> **"I have already created all triplets using this right value."**

Therefore,

```java
while(left < right && nums[right] == nums[right + 1])
```

really means

```text
Have I already used this right value?

YES

↓

Skip it.
```

---

I like the documentation format you're building. If you keep this structure for every important line:

* **Why was this line added?**
* **What problem does it prevent?**
* **What happens if I remove it?**
* **Read it like English**
* **The programmer's intention**

you'll end up with interview notes that help you reconstruct the solution from first principles instead of memorizing it. That's a much more effective way to study DSA.
