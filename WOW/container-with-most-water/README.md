# Container With Most Water

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        int n = height.length;
        int i=0, j=n-1;
        while(i<j){
            ans = Math.max(ans,Math.min(height[i],height[j]) * (j-i));
            if(height[i]<height[j]) i++; else j--;
        }
        return ans;
    }
}

//kitne paani ki guarantee hai abhi -> Math.min(height[i],height[j]) * (j-i)
```

---

---
## Quick Revision
The problem is to find the maximum area of water that can be trapped between two parallel arrays in a container, given an array of heights representing the container's walls.

We solve this problem by using a two-pointer technique, starting from both ends and moving towards each other until we find the optimal positions for the two lines that result in the maximum area.

## Intuition
The key insight here is to realize that as we move the pointers closer together, the height of the water level will increase. However, we need to balance this with the width of the water, which decreases as the pointers move closer. We use the `Math.min` function to take the minimum height between the two pointers because it's the limiting factor for the area.

## Algorithm
1. Initialize two pointers, `i` and `j`, at both ends of the array.
2. Calculate the maximum possible area by taking the minimum height between the current positions (`height[i]` and `height[j]`) multiplied by the distance between them (`j-i`).
3. Move the pointer with the smaller value towards the other end to increase the area (if `height[i] < height[j]`, increment `i; else decrement `j`).
4. Repeat steps 2-3 until `i >= j`.
5. Return the maximum area found.

## Concept to Remember
* Two-pointer technique for traversing an array efficiently.
* Importance of finding the limiting factor (minimum height) when calculating the area.

## Common Mistakes
* Failing to update the area calculation correctly after moving the pointers.
* Not considering the case where `i` and `j` meet or cross each other during iteration.
* Misjudging the minimum height at each step, leading to incorrect area calculations.

## Complexity Analysis
- Time: O(n) - We traverse the array once using two pointers, resulting in a linear time complexity.
- Space: O(1) - Our algorithm uses a constant amount of space for variables and does not depend on the input size.

## Commented Code
```java
class Solution {
    public int maxArea(int[] height) {
        // Initialize result (area) to 0
        int ans = 0;

        // Get length of array
        int n = height.length;

        // Define two pointers at both ends
        int i=0, j=n-1;

        while(i<j){
            // Calculate area by taking minimum height and multiplying with distance
            ans = Math.max(ans, Math.min(height[i], height[j]) * (j-i));

            // Move pointer with smaller value towards other end
            if(height[i] < height[j]) i++; else j--;
        }

        return ans;
    }
}
```

## Interview Tips
* Pay attention to the edge cases and consider what happens when `i` and `j` meet or cross each other.
* Be mindful of the time complexity and ensure your algorithm runs in O(n) for large inputs.
* Practice explaining your thought process and approach during an interview.

## Revision Checklist
- Understand the problem statement correctly.
- Implement two-pointer technique correctly.
- Handle edge cases properly (e.g., when `i` and `j` meet or cross).
- Calculate area correctly using minimum height and distance.

## Similar Problems
* Container With Most Water II (LC 1105)
* Trapping Rain Water (LC 42)
* Container Capacity (LC 402)
