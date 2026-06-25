# Count Subarrays With Majority Element I

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Divide and Conquer` `Segment Tree` `Merge Sort` `Counting` `Prefix Sum`  
**Time:** O(n^2)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            for (int j = i; j < n; ++j) {
                cnt += (nums[j] == target ? 1 : -1);
                if (cnt > 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

---

---

## Quick Revision
Count the number of subarrays in an array where a given target element is more than half of the elements. This problem can be solved by iterating through each subarray and checking if the majority element (target) is present.

## Intuition
The "aha moment" for this problem is realizing that we don't need to explicitly check every subarray. We can simply count the occurrences of the target element in each starting position, as any subarray with the target as a majority will have been counted in its preceding positions.

## Algorithm
1. Iterate through the input array `nums`.
2. For each starting index `i`, initialize a counter `cnt` to 0.
3. Iterate through the rest of the array from index `i` to the end, incrementing `cnt` by 1 when encountering the target element and decrementing it otherwise.
4. If `cnt` is positive at any point, increment the answer `ans`.
5. Return the total count of subarrays with the target as a majority.

## Concept to Remember
* Majority Element: an element that appears more than n/2 times in an array (where n is the length of the array)
* Subarray: a contiguous sequence of elements within the input array

## Common Mistakes
* Failing to consider the majority element's presence in each subarray individually
* Not keeping track of the correct count for each starting position
* Miscounting or misunderstanding the occurrences of the target element

## Complexity Analysis
- Time: O(n^2) - We have two nested loops that iterate through the input array.
- Space: O(1) - We use a constant amount of space to store counters and indices.

## Commented Code
```java
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        // Initialize answer variable to 0
        int ans = 0;

        // Iterate through each starting index in the array
        for (int i = 0; i < nums.length; ++i) {
            // Reset counter for current subarray
            int cnt = 0;

            // Iterate through rest of array from current starting index
            for (int j = i; j < nums.length; ++j) {
                // Increment counter if element is target, decrement otherwise
                cnt += (nums[j] == target ? 1 : -1);

                // If majority element is present, increment answer
                if (cnt > 0) {
                    ++ans;
                }
            }
        }

        // Return total count of subarrays with target as a majority
        return ans;
    }
}
```

## Interview Tips
* Make sure to clearly understand the problem and the constraints before diving into code.
* Take advantage of simple, brute-force approaches when dealing with smaller input sizes or easier problems.
* Be prepared to explain your thought process and reasoning behind your solution.

## Revision Checklist
- [ ] Understand what a majority element is and how it applies to this problem.
- [ ] Review the algorithm for iterating through each starting position in the array.
- [ ] Double-check that the counter logic correctly tracks occurrences of the target element.

## Similar Problems
* Majority Element (LeetCode)
* Subarray Bitwise XOR (LeetCode)

## Tags
`Array` `Hash Map`
