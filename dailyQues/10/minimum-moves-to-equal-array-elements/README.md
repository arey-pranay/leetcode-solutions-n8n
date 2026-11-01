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
Find the minimum number of increments required to make all elements in an array equal by incrementing $n-1$ elements at a time.
By shifting perspectives, incrementing $n-1$ elements is mathematically identical to decrementing 1 element, so we simply sum the difference between each element and the minimum element.

## Intuition
At first glance, the problem seems to suggest we should repeatedly pick $n-1$ elements and add 1 to them until all elements match. However, simulating this approach is too slow and can lead to integer overflow or infinite loops if not carefully managed. 

The "aha moment" comes from relative thinking. Incrementing $n-1$ elements by 1 is functionally equivalent to decrementing a single element by 1 relative to the others. Our goal is to make all elements equal. Instead of raising everything up to the maximum element (which keeps growing), we can lower all elements down to the minimum element in the array. The total number of moves needed to equalize the array is therefore the sum of the differences between each element and the minimum element.

## Algorithm
1. Initialize a variable `min` to `Integer.MAX_VALUE` to track the smallest element in the array.
2. Initialize an accumulator variable `ans` to 0 to store the total number of moves.
3. Iterate through the array to find and store the minimum value (`min`).
4. Iterate through the array a second time, calculating the difference `num - min` for each element and adding it to `ans`.
5. Return `ans` as the final minimum number of moves.

## Concept to Remember
- **Mathematical Invariance:** Transforming an operation on a subset ($n-1$ elements) into its inverse operation on a single element.
- **Relative Comparison:** Solving optimization problems by focusing on relative distances to a fixed baseline (the minimum element) rather than absolute values.

## Common Mistakes
- Attempting to simulate the process by repeatedly picking $n-1$ elements, which leads to a Time Limit Exceeded (TLE) error ($O(K \cdot N)$ where $K$ is the number of moves).
- Misinterpreting the problem and trying to increment elements up to the maximum value instead of decrementing down to the minimum value.
- Forgetting to handle edge cases such as an array with all identical elements (where the output should correctly be 0).
- Integer overflow issues if the array values and sizes are extremely large (though standard `int` typically suffices for standard constraints).

## Complexity Analysis
- Time: $O(N)$ - We iterate through the array twice: once to find the minimum element and once to calculate the sum of differences.
- Space: $O(1)$ - We only use a constant amount of extra memory for tracking the minimum value and the answer accumulator.

## Commented Code
```java
class Solution {
    public int minMoves(int[] nums) {
        // Initialize min to the maximum possible integer to find the smallest array element
        int min = Integer.MAX_VALUE, ans = 0;
        // Loop through the array to find the absolute minimum value present
        for(int num : nums) min = Math.min(min, num);
        // Loop through the array again to sum the difference between each element and the min
        for(int num : nums) ans += num - min;

        // Return the total accumulated moves required to equalize all elements
        return ans;
    }
}
```

## Interview Tips
- Start by explaining the brute-force simulation approach, then immediately point out its inefficiency to showcase your algorithmic thinking.
- Clearly articulate the mathematical transformation: "Incrementing $n-1$ elements is the same as decrementing 1 element."
- Mention that this problem relies on relative differences, which eliminates the need to track which specific elements are being modified.
- Be prepared to discuss follow-up variations, such as *Minimum Moves to Equal Array Elements II*, where you can increment or decrement by 1 (which requires finding the median instead of the minimum).

## Revision Checklist
- [ ] Understand why incrementing $n-1$ elements equals decrementing 1 element.
- [ ] Know why the target value must be the minimum element in the array.
- [ ] Able to write the two-pass linear time solution cleanly.
- [ ] Remember the time and space complexities ($O(N)$ and $O(1)$).

## Similar Problems
- Minimum Moves to Equal Array Elements II
- Minimum Operations to Make Array Equal
- Array Partition

## Tags
`Array` `Math`
