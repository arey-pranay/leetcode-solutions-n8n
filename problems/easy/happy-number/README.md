# Happy Number

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `Math` `Two Pointers`  
**Time:** See complexity section  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> visit = new HashSet<>();
        
        while (!visit.contains(n)) {
            visit.add(n);
            n = getNextNumber(n);
            if (n == 1) {
                return true;
            }
        }
        
        return false;
    }

    private int getNextNumber(int n) {
        int output = 0;
        
        while (n > 0) {
            int digit = n % 10;
            output += digit * digit;
            n = n / 10;
        }
        
        return output;
    }
}
```

---

---
## Problem Summary
A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy numbers.
Return `true` if `n` is a happy number, and `false` if not.

## Approach and Intuition
The core idea is to simulate the process described in the problem. We start with the given number `n` and repeatedly calculate the sum of the squares of its digits. We need a way to detect if the process enters an infinite loop.

An infinite loop occurs if we encounter a number that we have already seen during the calculation. If we see a number again, it means we are stuck in a cycle. If this cycle does not contain `1`, then the number is not happy. If the process reaches `1`, then the number is happy.

To detect cycles, we can use a `HashSet` to store all the numbers we have encountered so far. In each step of the process:
1. Calculate the sum of the squares of the digits of the current number.
2. Check if this new number is already in our `HashSet`.
   - If it is, we've found a cycle, and since it's not `1` (we would have returned `true` earlier), the number is not happy. Return `false`.
   - If it's not, add the new number to the `HashSet` and continue the process with this new number.
3. If the new number is `1`, we've found a happy number. Return `true`.

The `getNextNumber` helper function is responsible for calculating the sum of the squares of the digits for a given number. It does this by repeatedly taking the last digit (`n % 10`), squaring it, adding it to a running `output`, and then removing the last digit (`n / 10`) until the number becomes `0`.

## Complexity Analysis
- Time: O(log n) - The number of steps to reach 1 or a cycle is generally small. For a number `n`, the sum of squares of its digits will be significantly smaller than `n` itself for large `n`. For example, for a 3-digit number like 999, the sum of squares is 3 * 81 = 243. For a 4-digit number like 9999, it's 4 * 81 = 324. The maximum sum of squares for a number with `d` digits is `d * 81`. Since `d` is roughly `log10(n)`, the sum of squares grows much slower than `n`. The values will eventually fall into a range where they either reach 1 or enter a cycle. The number of distinct values encountered before reaching 1 or a cycle is bounded.
- Space: O(log n) - The `HashSet` stores the numbers encountered. Similar to the time complexity, the number of distinct values before reaching 1 or a cycle is bounded and grows logarithmically with `n`.

## Code Walkthrough
1. `isHappy(int n)`:
   - Initializes a `HashSet<Integer>` called `visit` to keep track of numbers encountered.
   - Enters a `while` loop that continues as long as the current number `n` has not been visited (`!visit.contains(n)`).
   - Inside the loop:
     - `visit.add(n)`: Adds the current number `n` to the `visit` set.
     - `n = getNextNumber(n)`: Calls the helper function to calculate the sum of squares of digits for `n` and updates `n` with the result.
     - `if (n == 1)`: Checks if the new `n` is `1`. If it is, the number is happy, so `return true`.
   - If the `while` loop terminates, it means `visit.contains(n)` was true, indicating a cycle was detected. Since the `if (n == 1)` condition inside the loop didn't return `true`, this cycle does not include `1`. Therefore, `return false`.

2. `getNextNumber(int n)`:
   - Initializes `output` to `0`. This will store the sum of squares.
   - Enters a `while` loop that continues as long as `n > 0`.
   - Inside the loop:
     - `int digit = n % 10;`: Gets the last digit of `n`.
     - `output += digit * digit;`: Squares the digit and adds it to `output`.
     - `n = n / 10;`: Removes the last digit from `n`.
   - After the loop finishes (when `n` becomes `0`), `return output`.

## Interview Tips
- **Clarity of Thought:** Explain your approach clearly. Start by describing the problem and then how you plan to solve it.
- **Cycle Detection:** Emphasize the need for cycle detection and why a `HashSet` is a good choice for this.
- **Helper Function:** Discuss the purpose of the `getNextNumber` helper function and how it breaks down the problem.
- **Edge Cases:** Consider edge cases like `n=1` (which should return `true` immediately) or numbers that quickly enter a cycle.
- **Complexity:** Be prepared to explain the time and space complexity, justifying your reasoning.
- **Alternative Approaches:** If time permits, mention alternative cycle detection methods (like Floyd's Cycle-Finding Algorithm, also known as the "tortoise and hare" algorithm) and their trade-offs.

## Optimization and Alternatives
**Floyd's Cycle-Finding Algorithm (Tortoise and Hare):**
Instead of using a `HashSet`, we can use two pointers: a "slow" pointer that moves one step at a time and a "fast" pointer that moves two steps at a time. If there's a cycle, the fast pointer will eventually catch up to the slow pointer.

- **Algorithm:**
    1. Initialize `slow = n` and `fast = getNextNumber(n)`.
    2. While `fast != 1` and `slow != fast`:
        - `slow = getNextNumber(slow)`
        - `fast = getNextNumber(getNextNumber(fast))`
    3. If `fast == 1`, return `true`. Otherwise, return `false`.

- **Complexity:**
    - Time: O(log n) - Similar reasoning as the HashSet approach.
    - Space: O(1) - This is the main advantage over the HashSet approach.

## Revision Checklist
- [ ] Understand the definition of a happy number.
- [ ] Identify the need for cycle detection.
- [ ] Implement the sum of squares of digits calculation correctly.
- [ ] Choose an appropriate data structure (HashSet) or algorithm (Floyd's) for cycle detection.
- [ ] Handle the base case where `n` is already `1`.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
- Linked List Cycle
- Linked List Cycle II
- Detect Cycle in a Directed Graph

## Tags
`Math` `Hash Table` `Two Pointers`

## My Notes
comment
