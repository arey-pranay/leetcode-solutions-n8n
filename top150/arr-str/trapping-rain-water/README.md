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
        int n = height.length;
        int[] pre = new int[n];
        int[] suf = new int[n]; 
        pre[0] = height[0];
        suf[n-1] = height[n-1];
        int ans = 0;
        for(int i=1;i<n;i++) pre[i] = Math.max(pre[i-1],height[i]);
        for(int i=n-2;i>=0;i--) suf[i] = Math.max(suf[i+1],height[i]);
        for(int i=0;i<n;i++) ans += Math.min(pre[i],suf[i]) - height[i];
        return ans;        
    }
}
```

---

---

## Quick Revision
The problem is to find the total amount of water trapped between bars in a given array of bar heights. We can solve this by using two pointers, one starting from the beginning and one from the end, to track the maximum height on both sides.

## Intuition
This approach works because we can think of the trapped water as the difference between the minimum of the left and right boundaries at each position, minus the actual bar height. By iterating through the array twice (once from left to right and once from right to left), we effectively "cancel out" all the bars that are not part of a trapping region.

## Algorithm
1. Initialize two arrays `pre` and `suf` to store the maximum heights seen so far from the left and right, respectively.
2. Set the first element of `pre` to be equal to the height at index 0, and the last element of `suf` to be equal to the height at index n-1 (where n is the length of the input array).
3. Iterate through the array from left to right, updating each element in `pre` to be the maximum of its previous value and the current bar height.
4. Iterate through the array from right to left, updating each element in `suf` to be the maximum of its next value and the current bar height.
5. Initialize a variable `ans` to 0, which will store the total amount of trapped water.
6. Iterate through the array one last time, adding the minimum of the corresponding elements in `pre` and `suf`, minus the actual bar height, to `ans`.

## Concept to Remember
* The importance of using two pointers to track the maximum heights on both sides.
* How to "cancel out" non-trapping bars by iterating through the array twice.

## Common Mistakes
* Not initializing the first element of `pre` correctly (e.g., setting it to 0 instead of `height[0]`).
* Failing to update the elements in `suf` correctly when iterating from right to left.
* Misunderstanding how to calculate the trapped water at each position.

## Complexity Analysis
- Time: O(n) - We iterate through the array three times, each time taking linear time.
- Space: O(n) - We need to store two additional arrays of length n to keep track of the maximum heights from both sides.

## Commented Code
```java
class Solution {
    public int trap(int[] height) {
        // Initialize variables
        int n = height.length;
        int[] pre = new int[n];  // Store max heights from left
        int[] suf = new int[n];  // Store max heights from right

        // Calculate max heights from left and right
        pre[0] = height[0];
        suf[n-1] = height[n-1];

        for(int i=1;i<n;i++) {
            pre[i] = Math.max(pre[i-1], height[i]);  // Update pre array
        }

        for(int i=n-2;i>=0;i--) {
            suf[i] = Math.max(suf[i+1], height[i]);  // Update suf array
        }

        // Calculate trapped water
        int ans = 0;
        for(int i=0;i<n;i++) {
            ans += Math.min(pre[i], suf[i]) - height[i];  // Add trapped water at each position
        }

        return ans;        
    }
}
```

## Interview Tips
* Make sure to initialize the `pre` and `suf` arrays correctly.
* Double-check your calculations for trapped water at each position.
* Practice iterating through the array twice with two pointers.

## Revision Checklist
- [ ] Understand how to use two pointers to track maximum heights on both sides.
- [ ] Recognize how to "cancel out" non-trapping bars by iterating through the array twice.
- [ ] Review the formula for calculating trapped water at each position.

## Similar Problems
* Container With Most Water (LeetCode 11)
* Trapping Rain Water II (LeetCode 407)

## Tags
`Array`, `Hash Map`, `Two Pointers`, `Trapped Water`
