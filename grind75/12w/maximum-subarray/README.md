# Maximum Subarray

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Divide and Conquer` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i : nums){
            sum+=i;
            max = Math.max(sum,max);
            if(sum<0)sum = 0;
        }
        return max;
    }
}
```

---

---

## Quick Revision
Find the maximum contiguous subarray within an array of integers. Solve using Kadane's algorithm, which iterates through the array and keeps track of the maximum sum seen so far.

## Intuition
The key insight is that at any point in the array, we either extend the current subarray or start a new one if the sum becomes negative. By tracking the maximum sum seen so far, we can efficiently find the maximum contiguous subarray.

## Algorithm
1. Initialize `max` to store the maximum sum seen so far and `sum` to keep track of the current subarray sum.
2. Iterate through each element in the input array.
3. For each element, add it to the current subarray sum (`sum`).
4. Update `max` with the maximum value between the current sum and the stored maximum sum.
5. If the current sum becomes negative, reset it to 0 to start a new subarray.

## Concept to Remember
* Kadane's algorithm for finding maximum subarrays
* Array iteration techniques

## Common Mistakes
* Failing to initialize `max` with a sufficiently large value (e.g., `Integer.MIN_VALUE`)
* Not resetting `sum` when it becomes negative, leading to incorrect results

## Complexity Analysis
- Time: O(n) - reason: Single pass through the input array
- Space: O(1) - reason: Constant space usage for variables

## Commented Code
```java
class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize max with a sufficiently large value
        int max = Integer.MIN_VALUE;
        
        // Initialize sum to 0, will be used to keep track of the current subarray sum
        int sum = 0;
        
        // Iterate through each element in the input array
        for (int i : nums) {
            // Add the current element to the current subarray sum
            sum += i;
            
            // Update max with the maximum value between the current sum and the stored maximum sum
            max = Math.max(sum, max);
            
            // If the current sum becomes negative, reset it to 0 to start a new subarray
            if (sum < 0) {
                sum = 0;
            }
        }
        
        // Return the maximum contiguous subarray sum
        return max;
    }
}
```

## Interview Tips
* Pay attention to corner cases and edge conditions.
* Use constant space efficiently, as in this solution.
* Clearly explain your thought process behind using Kadane's algorithm.

## Revision Checklist
- [ ] Review Kadane's algorithm for maximum subarrays
- [ ] Understand the importance of initializing `max` with a large value
- [ ] Practice implementing the algorithm from scratch

## Similar Problems
* Maximum Subarray II (LeetCode)
* Contiguous Array (LeetCode)

## Tags
`Array`, `Hash Map`
