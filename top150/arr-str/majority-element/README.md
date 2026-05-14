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
Given an array of size n, find the element that appears more than n/2 times.
This problem can be solved efficiently using Boyer-Moore Voting Algorithm.

## Intuition
The core idea is that if an element is the majority element, it will outnumber all other elements combined. We can maintain a `candidate` element and a `count`. When we see the `candidate`, we increment the `count`. When we see a different element, we decrement the `count`. If the `count` drops to zero, it means the current `candidate` has been "canceled out" by other elements, so we pick a new `candidate` from the current element. Because the majority element appears more than n/2 times, it's guaranteed to be the final `candidate` remaining.

## Algorithm
1. Initialize a `candidate` variable to store the potential majority element.
2. Initialize a `count` variable to 0.
3. Iterate through the input array `nums`.
4. If `count` is 0, set the current element as the new `candidate`.
5. If the current element is equal to the `candidate`, increment `count`.
6. Otherwise (if the current element is different from the `candidate`), decrement `count`.
7. After iterating through the entire array, the `candidate` variable will hold the majority element.
8. Return the `candidate`.

## Concept to Remember
*   **Majority Element Property:** An element appearing more than n/2 times will always be the final candidate in a pairwise cancellation process.
*   **Boyer-Moore Voting Algorithm:** A linear time, constant space algorithm for finding the majority element.
*   **Pairwise Cancellation:** The decrementing of the count represents canceling out one instance of the candidate with one instance of a different element.

## Common Mistakes
*   Assuming the array is always non-empty.
*   Not handling the `count == 0` case correctly, leading to incorrect candidate selection.
*   Returning the `count` instead of the `candidate` element.
*   Forgetting that the problem guarantees a majority element exists, simplifying the need for a second pass to verify.

## Complexity Analysis
- Time: O(n) - The algorithm iterates through the array once.
- Space: O(1) - The algorithm uses only a few variables (`curr`, `count`), regardless of the input size.

## Commented Code
```java
class Solution {
    public int majorityElement(int[] nums) {
        // Initialize the candidate for the majority element. We can start with the first element.
        int curr = nums[0];
        // Initialize a counter to track the occurrences of the current candidate.
        int count = 0;
        // Iterate through each number in the input array.
        for(int num : nums){
            // If the count is zero, it means the previous candidate has been "canceled out".
            // So, we set the current number as the new candidate.
            if(count == 0) curr = num;
            // If the current number matches the candidate, increment the count.
            if(curr == num) count++;
            // If the current number is different from the candidate, decrement the count.
            // This represents canceling out one occurrence of the candidate with one occurrence of a different number.
            else count--;
        }
        // After iterating through the entire array, 'curr' will hold the majority element.
        return curr;
    }
}
```

## Interview Tips
*   Clearly explain the intuition behind the Boyer-Moore Voting Algorithm.
*   Walk through an example on a whiteboard to demonstrate how the `count` and `candidate` change.
*   Mention that this algorithm works because the majority element's count is greater than the sum of all other elements' counts.
*   Be prepared to discuss why a second pass to verify the candidate is not needed given the problem constraints.

## Revision Checklist
- [ ] Understand the problem statement: find element appearing > n/2 times.
- [ ] Recall Boyer-Moore Voting Algorithm.
- [ ] Implement the algorithm with `candidate` and `count`.
- [ ] Handle the `count == 0` condition correctly.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition.

## Similar Problems
*   Majority Element II (LeetCode 229)
*   Find the Duplicate Number (LeetCode 287) - conceptually similar in using properties of counts/occurrences.

## Tags
`Array` `Hash Map` `Divide and Conquer` `Bit Manipulation` `Boyer-Moore Voting Algorithm`
