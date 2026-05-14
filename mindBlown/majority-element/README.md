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
This can be solved efficiently using the Boyer-Moore Voting Algorithm.

## Intuition
The core idea is that if an element is the majority element, it will "outvote" all other elements combined. Imagine pairing up every occurrence of the majority element with an occurrence of a different element. Since the majority element appears more than n/2 times, there will always be at least one occurrence of the majority element left over after all possible pairings. The Boyer-Moore algorithm simulates this pairing process. When we encounter the current candidate, we increment its count. When we encounter a different element, we decrement the count. If the count drops to zero, it means the current candidate has been "cancelled out" by other elements, so we pick a new candidate. Because the majority element has more occurrences, it will eventually become the candidate and its count will remain positive until the end.

## Algorithm
1. Initialize a `candidate` variable to store the potential majority element and a `count` variable to 0.
2. Iterate through the input array `nums`.
3. If `count` is 0, set the current element as the new `candidate`.
4. If the current element is equal to the `candidate`, increment `count`.
5. If the current element is not equal to the `candidate`, decrement `count`.
6. After iterating through the entire array, the `candidate` variable will hold the majority element.
7. Return the `candidate`.

## Concept to Remember
*   **Boyer-Moore Voting Algorithm:** An efficient algorithm for finding the majority element in an array.
*   **In-place processing:** The algorithm operates directly on the input array without requiring significant extra space.
*   **Cancellation property:** The algorithm relies on the idea that occurrences of the majority element can cancel out occurrences of other elements.

## Common Mistakes
*   **Not handling the `count == 0` case correctly:** Forgetting to update the `candidate` when `count` becomes zero will lead to incorrect results.
*   **Assuming the array is always non-empty:** While LeetCode constraints usually guarantee this, in a real-world scenario, an empty array should be considered.
*   **Returning `count` instead of `candidate`:** The algorithm's goal is to find the element itself, not its final count.
*   **Not understanding why it works:** Simply memorizing the algorithm without grasping the intuition can lead to errors in variations of the problem.

## Complexity Analysis
- Time: O(n) - The algorithm iterates through the array once.
- Space: O(1) - The algorithm uses only a constant amount of extra space for variables like `curr` and `count`.

## Commented Code
```java
class Solution {
    public int majorityElement(int[] nums) {
        // Initialize the current candidate for the majority element.
        // We can start with the first element as an initial guess.
        int curr = nums[0];
        // Initialize a counter to track the occurrences of the current candidate.
        int count = 0;
        // Iterate through each number in the input array.
        for(int num : nums){
            // If the count is zero, it means the previous candidate has been "cancelled out".
            // So, we pick the current number as the new candidate.
            if(count == 0) curr = num;
            // If the current number matches the candidate, increment the count.
            if(curr == num) count++;
            // If the current number does not match the candidate, decrement the count.
            // This simulates cancelling out an occurrence of the candidate with a different number.
            else count--;
        }
        // After iterating through the entire array, 'curr' will hold the majority element.
        // This is guaranteed because the majority element appears more than n/2 times.
        return curr;
    }
}
```

## Interview Tips
*   **Explain the intuition first:** Before diving into the code, clearly articulate the "cancellation" idea behind the Boyer-Moore algorithm.
*   **Walk through an example:** Use a small array (e.g., `[2, 2, 1, 1, 1, 2, 2]`) to demonstrate how the `curr` and `count` variables change step-by-step.
*   **Discuss edge cases:** Mention what would happen with an empty array or an array with only one element.
*   **Be prepared to justify O(1) space:** Explain why no additional data structures are needed.

## Revision Checklist
- [ ] Understand the problem statement: find element appearing > n/2 times.
- [ ] Recall the Boyer-Moore Voting Algorithm.
- [ ] Trace the algorithm with a sample input.
- [ ] Analyze time and space complexity.
- [ ] Be able to explain the intuition behind the algorithm.

## Similar Problems
*   Majority Element II
*   Majority Number (LintCode)

## Tags
`Array` `Hash Map` `Divide and Conquer` `Bit Manipulation`
