# Majority Element

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Divide and Conquer` `Sorting` `Counting`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int majorityElement(int[] nums) {
        int curr = nums[0];
        int count = 0;
        for(int num : nums){
            if(count == 0) curr = num;
            if(curr == num) count++;
            else count--;
        }
        return curr;
    }
}
```

---

---
## Quick Revision
Find the majority element in an array where an element appears more than n/2 times.

Solve it using a single pass through the array, keeping track of the current majority element and its count.

## Intuition
The "aha moment" is realizing that if we keep a counter for the current majority element, every time we see this element again, we increment the counter. Every time we see a different element, we decrement the counter. Since the majority element appears more than n/2 times, at some point the counter will become positive and stay there.

## Algorithm
1. Initialize `curr` to the first element of the array and `count` to 0.
2. Iterate through the array. If `count` is 0, set `curr` to the current element.
3. If `curr` matches the current element, increment `count`.
4. Otherwise, decrement `count`.

## Concept to Remember
* Boyer-Moore Majority Vote algorithm
* Single-pass solution for finding majority elements
* Handling edge cases (e.g., when there are multiple majority elements)

## Common Mistakes
* Misunderstanding the problem statement and assuming a single majority element always exists.
* Not initializing `curr` correctly, leading to incorrect results.
* Failing to handle edge cases or assume the input is well-behaved.

## Complexity Analysis
- Time: O(n) - We only need one pass through the array.
- Space: O(1) - We use a constant amount of space for variables.

## Commented Code
```java
class Solution {
    public int majorityElement(int[] nums) {
        // Initialize curr to the first element and count to 0
        int curr = nums[0];
        int count = 0;

        // Iterate through the array
        for (int num : nums) {
            // If count is 0, set curr to the current element
            if (count == 0) {
                curr = num;
            }
            // If curr matches the current element, increment count
            if (curr == num) {
                count++;
            } 
            // Otherwise, decrement count
            else {
                count--;
            }
        }

        // Return the majority element
        return curr;
    }
}
```

## Interview Tips
* Make sure to read the problem carefully and understand what's being asked.
* Be prepared to explain your thought process and solution.
* Practice solving similar problems to improve your skills.

## Revision Checklist
- [ ] Review Boyer-Moore Majority Vote algorithm.
- [ ] Understand edge cases (e.g., multiple majority elements).
- [ ] Write a single-pass solution from scratch.

## Similar Problems
* LeetCode: #169, #287, #327

## Tags
`Array`, `Hash Map`
