# Trapping Rain Water

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Dynamic Programming` `Stack` `Monotonic Stack`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int n = height.length;
        int[] maxL = new int[n];
        int[] maxR = new int[n];
        maxL[0] = height[0];
        maxR[n-1] = height[n-1];
        for(int i=1;i<n;i++) maxL[i] = Math.max(maxL[i-1],height[i]);
        for(int i=n-2;i>=0;i--) maxR[i] = Math.max(maxR[i+1],height[i]);
        for(int i=0;i<n;i++) ans +=  Math.min(maxL[i], maxR[i])-height[i];
        return ans;
    }
}

// [0,1,0,2,1,0,1,3,2,1,2,1]
```

---

---
## Quick Revision
Trapping Rain Water is a problem where we need to find the amount of water that can be trapped between given bars in a bar graph.
We solve this problem by maintaining two arrays, one for the maximum height to the left and one for the maximum height to the right.

## Intuition
The key insight here is that the amount of water trapped at each position is equal to the minimum of the maximum heights from both sides minus the actual height. This works because if we have a peak in between two bars, then all the water will be trapped and will not flow out through either side.

## Algorithm
1. Initialize an array `maxL` to store the maximum height to the left for each position.
2. Initialize an array `maxR` to store the maximum height to the right for each position.
3. Fill up `maxL` by iterating from left to right, and fill up `maxR` by iterating from right to left.
4. Iterate over the entire array and add the difference between `min(maxL[i], maxR[i])` and `height[i]` to the answer.

## Concept to Remember
* Arrays can be used to store intermediate results efficiently.
* The concept of prefix sum and suffix sum can be applied here.
* We need to use two pointers, one from left to right and one from right to left.

## Common Mistakes
* Not considering the edge cases properly (e.g., `maxL[0]` and `maxR[n-1]`).
* Not updating `ans` correctly when iterating over the array.
* Not using space-efficient data structures for storing intermediate results.

## Complexity Analysis
- Time: O(n) - reason: We are making three passes through the entire array.
- Space: O(n) - reason: We need to store two arrays of size n each.

## Commented Code
```java
class Solution {
    public int trap(int[] height) {
        // Initialize answer and length variables
        int ans = 0;
        int n = height.length;

        // Initialize maxL array from left to right
        int[] maxL = new int[n];
        maxL[0] = height[0];

        // Fill up maxL by iterating over the array from left to right
        for(int i=1;i<n;i++) {
            // Update maxL[i] as the maximum of maxL[i-1] and height[i]
            maxL[i] = Math.max(maxL[i-1], height[i]);
        }

        // Initialize maxR array from right to left
        int[] maxR = new int[n];
        maxR[n-1] = height[n-1];

        // Fill up maxR by iterating over the array from right to left
        for(int i=n-2;i>=0;i--) {
            // Update maxR[i] as the maximum of maxR[i+1] and height[i]
            maxR[i] = Math.max(maxR[i+1], height[i]);
        }

        // Iterate over the entire array to calculate trapped water
        for(int i=0;i<n;i++) {
            // Add difference between min(maxL[i], maxR[i]) and height[i] to answer
            ans += Math.min(maxL[i], maxR[i])-height[i];
        }
        
        return ans;
    }
}
```

## Interview Tips
* Make sure you understand the problem clearly before starting to code.
* Use space-efficient data structures for storing intermediate results.
* Consider edge cases properly and handle them correctly.

## Revision Checklist
- [ ] Understand the problem statement clearly
- [ ] Use two arrays, `maxL` and `maxR`, for efficient calculation
- [ ] Iterate over the array three times for prefix sum, suffix sum, and final result

## Similar Problems
* Container With Most Water (LeetCode 11)
* Cup Cakes Sort (LeetCode 1356)
* Trapping Rain Water II (LeetCode 671)

## Tags
`Array` `Hash Map`
