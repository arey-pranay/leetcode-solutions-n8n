# Minimum Moves To Equal Array Elements

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int minMoves(int[] nums) {
        // incrementing n-1 numbers is same as decrementing 1. so we need to make all equal to min
        int min = Integer.MAX_VALUE, ans = 0;
        for(int num : nums) min = Math.min(min,num);
        for(int num : nums) ans += num-min;

        // another thing is to return arrSum - (n*min)
        return ans;
    }
}
```

---

---
## Quick Revision
Find the minimum number of moves to make all array elements equal by incrementing $n-1$ elements by 1 in each move.
Decrementing one element by 1 in each move is mathematically equivalent, so we find the minimum element and sum the differences between each element and the minimum.

## Intuition
The "aha moment" in this problem comes from shifting perspectives. The problem states we can increment $n-1$ elements by 1. Thinking about adding to $n-1$ elements to reach a common maximum can be very complex because elements keep growing. However, relative differences are what matter. Incrementing $n-1$ elements by 1 is functionally identical to *decrementing the single remaining element by 1*. By inverting our goal from "raising everything up to the maximum" to "lowering everything down to the minimum," the target value becomes fixed (the minimum element in the original array). Each element must be reduced down to this minimum value, meaning the number of moves needed for any element is simply `element - minimum_element`.

## Algorithm
1. Initialize a variable `min` to store the minimum value in the array, setting it initially to `Integer.MAX_VALUE`.
2. Initialize an accumulator variable `ans` to 0 to keep track of the total moves.
3. Iterate through the array once to find the minimum value (`min`).
4. Iterate through the array a second time, calculating the difference between each element and `min`, and add this difference to `ans`.
5. Return the final value of `ans` as the minimum total moves required.

## Concept to Remember
- **Relative Difference**: Transforming operations to simplify the state space (relative change is invariant).
- **Mathematical Equivalence**: Showing that an operation on a subset ($n-1$) is the inverse of an operation on the complement (1 element).
- **Linear Scan Optimization**: Solving problems efficiently in $O(N)$ time by pre-calculating necessary invariants (like the minimum element).

## Common Mistakes
- Trying to simulate the actual increments and updates, leading to a Time Limit Exceeded (TLE) error or overflow.
- Confusing this problem with "Minimum Moves to Equal Array Elements II" which uses the median and requires sorting.
- Failing to handle arrays with duplicate minimum values or negative numbers.
- Assuming the target value needs to be dynamically adjusted instead of fixed at the absolute array minimum.

## Complexity Analysis
- Time: O(N) - We iterate through the array twice: once to find the minimum value and once to calculate the sum of differences, where N is the number of elements in the array.
- Space: O(1) - Only a few primitive variables (`min`, `ans`, loop counters) are used, requiring constant extra space.

## Commented Code
```java
class Solution {
    public int minMoves(int[] nums) {
        // Initialize min to max possible value to find the smallest element, and ans to 0 for tracking moves
        int min = Integer.MAX_VALUE, ans = 0;
        
        // Loop through every number in the array to locate the absolute minimum value
        for(int num : nums) min = Math.min(min, num);
        
        // Loop through every number again to accumulate how many decrements are needed to reach the minimum
        for(int num : nums) ans += num - min;

        // Return the accumulated total moves which mathematically equals arrSum - (n * min)
        return ans;
    }
}
```

## Interview Tips
- Start by explaining the symmetry: incrementing $n-1$ elements is the same as decrementing 1 element. Interviewers love this mathematical insight.
- Mention why sorting is not required here (unlike finding the median for absolute differences with $+/- 1$), making this an $O(N)$ solution instead of $O(N \log N)$.
- Walk through a small manual example (e.g., `[1, 2, 3]`) to demonstrate how the inverted perspective works clearly.
- Be prepared to discuss variant problems where operations change, such as incrementing only 2 elements at a time.

## Revision Checklist
- [ ] Understand why incrementing $n-1$ elements equals decrementing 1 element.
- [ ] Recognize why the target state must be the minimum element.
- [ ] Implement the two-pass linear scan approach without sorting.
- [ ] Analyze the $O(N)$ time complexity and $O(1)$ space complexity.
- [ ] Distinguish this problem from "Minimum Moves to Equal Array Elements II" (median vs minimum).

## Similar Problems
- Minimum Moves to Equal Array Elements II
- Minimum Operations to Make Array Equal
- Array Partition
- Widest Vertical Area Between Two Points Containing No Points

## Tags
`Array` `Math` `Simulation`
