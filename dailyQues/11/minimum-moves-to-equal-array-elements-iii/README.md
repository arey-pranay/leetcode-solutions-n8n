# Minimum Moves To Equal Array Elements Iii

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Math`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int minMoves(int[] nums) {
        int max = 0, sum=0;
        for(int num : nums){sum+=num; max=Math.max(max,num);}
        return nums.length*max-sum;
    }
}
```

---

---
## Quick Revision
Find the minimum number of increments required to make all elements in an array equal.
Track the maximum element and sum, then compute `max * length - sum`.

## Intuition
The "aha moment" is realizing that incrementing $n-1$ elements by 1 is mathematically equivalent to decrementing a single element by 1 in terms of relative differences. Instead of simulating the increments, we can think of it as bringing all elements up to the maximum value found in the array. Every element needs to reach the maximum value, so the total number of moves is the sum of differences between the maximum element and each individual element: $\sum (max - nums[i]) = (max \times n) - sum$.

## Algorithm
1. Initialize two variables `max` to 0 and `sum` to 0 to keep track of the maximum value and the total sum of the array.
2. Iterate through each element in the array `nums`.
3. Add the current element to `sum` and update `max` to be the maximum of `max` and the current element.
4. Calculate the total moves using the formula: `(nums.length * max) - sum`.
5. Return the calculated result.

## Concept to Remember
- Mathematical reduction: Transforming an operation that is hard to simulate into a direct arithmetic formula.
- Invariance: Recognizing that relative distances between elements matter more than their absolute values.
- Single-pass optimization: Accumulating multiple aggregates (sum and max) simultaneously to avoid multiple traversals.

## Common Mistakes
- Trying to simulate the increment operations directly, leading to Time Limit Exceeded (TLE).
- Forgetting to handle edge cases like an array with a single element or all elements already being equal.
- Integer overflow when calculating the product of `nums.length` and `max` or the total `sum`.
- Misinterpreting the problem statement and attempting to decrement elements instead of incrementing.

## Complexity Analysis
- Time: O(n) - We traverse the array of length `n` exactly once to find the sum and maximum value.
- Space: O(1) - We only use a constant amount of extra memory for primitive variables.

## Commented Code
```java
class Solution {
    public int minMoves(int[] nums) {
        // Initialize max to store the largest element and sum to store the total sum of all elements
        int max = 0, sum = 0;
        
        // Iterate through every number in the input array
        for(int num : nums) {
            // Add the current number to the running total sum
            sum += num; 
            // Update max if the current number is greater than the previously recorded maximum
            max = Math.max(max, num);
        }
        
        // Calculate the total moves: (target max value * number of elements) - current sum
        return nums.length * max - sum;
    }
}
```

## Interview Tips
- Start by explaining the brute force simulation approach, then explain why it is inefficient and how the mathematical shortcut improves it.
- Clearly articulate the logic behind transforming the increment operations into distance calculations from the maximum element.
- Be prepared to discuss potential integer overflow issues in languages with fixed-size integers and how you would mitigate them.

## Revision Checklist
- [ ] Understand why incrementing $n-1$ elements is equivalent to decrementing 1 element.
- [ ] Derive the formula `max * length - sum`.
- [ ] Implement the single-pass solution efficiently.
- [ ] Consider edge cases such as negative numbers or identical elements.

## Similar Problems
- Minimum Moves to Equal Array Elements II
- Array Partition
- Maximum Product of Three Numbers

## Tags
`Array` `Math`
