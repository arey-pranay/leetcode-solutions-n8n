# Minimum Common Value

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Two Pointers` `Binary Search`  
**Time:** O(m + n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int i =0;
        int j =0;
        while(i<m && j<n){
            if(nums1[i]==nums2[j]){
                return nums1[i];
            } else if(nums1[i] < nums2[j]) i++;
            else j++;
        }
        return -1;
    }
}
```

---

---
## Quick Revision
Find the smallest integer that exists in both sorted arrays.
Use a two-pointer approach to efficiently find the first common element.

## Intuition
Since both arrays are sorted, we can use two pointers, one for each array, starting at the beginning. If the elements at the pointers are equal, we've found our minimum common value. If one element is smaller than the other, we advance the pointer of the smaller element. This is because the smaller element cannot be the common value (as it's smaller than the current element in the other array), and any subsequent elements in its array might match the current element in the other array. This process guarantees we find the *minimum* common value first.

## Algorithm
1. Initialize two pointers, `i` to 0 for `nums1` and `j` to 0 for `nums2`.
2. Get the lengths of `nums1` and `nums2`, let's call them `m` and `n` respectively.
3. While `i` is less than `m` AND `j` is less than `n`:
    a. If `nums1[i]` is equal to `nums2[j]`, return `nums1[i]` (this is the minimum common value).
    b. If `nums1[i]` is less than `nums2[j]`, increment `i`.
    c. If `nums1[i]` is greater than `nums2[j]`, increment `j`.
4. If the loop finishes without finding a common element, return -1.

## Concept to Remember
*   **Two Pointers:** A technique where two pointers traverse a data structure (like an array) simultaneously, often to find pairs or common elements.
*   **Sorted Arrays:** The property of sorted arrays is crucial for the efficiency of the two-pointer approach.
*   **Greedy Approach:** At each step, we make the locally optimal choice (advancing the pointer of the smaller element) which leads to the globally optimal solution (finding the minimum common value).

## Common Mistakes
*   Not handling the case where no common element exists (returning -1).
*   Incorrectly advancing pointers (e.g., advancing both when they are not equal).
*   Assuming arrays are not sorted and trying a brute-force or hash-map approach, which is less efficient for this specific problem.
*   Off-by-one errors in loop conditions or pointer increments.

## Complexity Analysis
- Time: O(m + n) - In the worst case, one pointer might traverse its entire array while the other stays put, or both pointers traverse their arrays completely.
- Space: O(1) - We only use a few variables for pointers and lengths, which is constant extra space.

## Commented Code
```java
class Solution {
    public int getCommon(int[] nums1, int[] nums2) {
        // Get the length of the first array
        int m = nums1.length;
        // Get the length of the second array
        int n = nums2.length;
        // Initialize pointer for nums1
        int i = 0;
        // Initialize pointer for nums2
        int j = 0;
        // Loop while both pointers are within their respective array bounds
        while (i < m && j < n) {
            // If the elements at the current pointers are equal, we found the minimum common value
            if (nums1[i] == nums2[j]) {
                // Return this common value
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                // If the element in nums1 is smaller, it cannot be the common value with nums2[j]
                // So, advance the pointer for nums1 to check the next element
                i++;
            } else { // nums1[i] > nums2[j]
                // If the element in nums2 is smaller, it cannot be the common value with nums1[i]
                // So, advance the pointer for nums2 to check the next element
                j++;
            }
        }
        // If the loop finishes without finding any common element, return -1
        return -1;
    }
}
```

## Interview Tips
*   Clearly explain the two-pointer strategy and why it works for sorted arrays.
*   Walk through an example manually to demonstrate the pointer movement.
*   Mention the edge case of empty arrays or arrays with no common elements.
*   Be prepared to discuss why this approach is better than using a hash set for this specific problem (due to sorted input).

## Revision Checklist
- [ ] Understand the problem statement: find the smallest common element in two sorted arrays.
- [ ] Recall the two-pointer technique for sorted arrays.
- [ ] Implement the pointer advancement logic correctly.
- [ ] Handle the case where no common element is found.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Intersection of Two Arrays
*   Intersection of Two Arrays II
*   Two Sum
*   Container With Most Water

## Tags
`Array` `Two Pointers` `Binary Search`
