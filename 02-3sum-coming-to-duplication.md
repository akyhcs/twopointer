This is probably the **best question** you've asked so far.

Because you're not asking about the code anymore.

You're asking:

> **"How did someone even realize duplicate solutions could happen?"**

This is exactly how experienced programmers think.

The answer is: **they don't think about duplicates first. They discover them while dry-running the algorithm.**

Let me show you.

---

# Step 1: Write the simplest algorithm first

Imagine you're designing the algorithm from scratch.

You think:

> I'll sort the array.

```text
[-4, -1, -1, 0, 1, 2]
```

Then:

> I'll fix one element.

```text
-4  -1  -1   0   1   2
 ^
```

Then:

> I'll use two pointers.

Nothing about duplicates yet.

---

# Step 2: Dry run your algorithm

Move to the next iteration.

```text
i = 1

-4  -1  -1   0   1   2
     ^
```

Suppose your algorithm finds

```text
[-1,0,1]
```

Great.

---

Next iteration.

```text
i = 2

-4  -1  -1   0   1   2
         ^
```

Ask yourself:

> What will my algorithm do now?

Exactly the same thing.

It will again search

```text
0 ... 2
```

It will again find

```text
[-1,0,1]
```

Then you realize

> "Wait... I already found this answer."

That is the moment you discover the bug.

---

# Notice something important

Nobody sits and thinks

> "Hmm... duplicate handling..."

Instead they think

```text
Let's test my algorithm.

↓

Oops...

Same answer again.

↓

Why?

↓

Because I'm starting from the same value.

↓

Skip repeated values.
```

That's how algorithms are developed.

---

# Another example

Suppose you wrote this algorithm.

```java
Arrays.sort(nums);

for(...)
{
    ...
}
```

You submit it.

LeetCode says

```text
Wrong Answer

Expected

[
 [-1,-1,2],
 [-1,0,1]
]

Your Output

[
 [-1,-1,2],
 [-1,0,1],
 [-1,0,1]
]
```

Now you investigate.

> Why did I get two identical triplets?

Then you look at your iterations.

```text
Iteration 2

i = first -1

↓

Found

[-1,0,1]
```

Later

```text
Iteration 3

i = second -1

↓

Found

[-1,0,1]
```

Now the solution becomes obvious.

Skip the second `-1`.

---

# This is actually debugging

Most interview algorithms are invented like this.

```text
Write simple solution

↓

Test on examples

↓

Find a bug

↓

Add one condition

↓

Test again

↓

Find another bug

↓

Add another condition
```

That's exactly how the duplicate checks were born.

---

# The same thing happens for the left pointer

Suppose after finding

```text
[-2,0,2]
```

you simply do

```java
left++;
right--;
```

Then you dry run.

```text
-2   0   0   0   2
 ^        L   R
```

You notice

> Wait...

Left is pointing to another `0`.

If I continue,

I'll again produce

```text
[-2,0,2]
```

That's another bug.

So you add

```java
while(nums[left] == nums[left-1])
    left++;
```

Notice the pattern?

The condition wasn't invented first.

The bug was discovered first.

Then the condition was added.

---

# This is how senior engineers think

Whenever they write code, they ask:

```text
What can go wrong?
```

Not

```text
What code should I write?
```

For this problem, their thought process is:

```text
I have an algorithm.

↓

Let me dry run it.

↓

Oops.

Duplicate answers.

↓

Why?

↓

Repeated values.

↓

Skip repeated values.
```

---

# This is the biggest DSA lesson I can give you

**Don't study the final code first.**

Study the **mistakes** that forced the programmer to write each line.

For every line in an optimized solution, ask:

1. **What bug would appear if this line didn't exist?**
2. **Can I create a small input that exposes that bug?**
3. **Does this line fix exactly that bug?**

For the duplicate check:

```java
if (i > 0 && nums[i] == nums[i - 1])
    continue;
```

your mental model shouldn't be:

> "This skips duplicates."

It should be:

> "I wrote the algorithm without this line. During a dry run, I noticed the same triplet was produced twice because I started from the same value twice. This line fixes that bug."

That's the mindset that helps you derive algorithms instead of memorizing them.
