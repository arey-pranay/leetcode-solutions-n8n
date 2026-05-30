# Two Sum

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i =0;i<nums.length;i++){
            if(hm.containsKey(target-nums[i])) return new int[]{i,hm.get(target-nums[i])}; 
            else hm.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
}
```

---

---
## Quick Revision
Given an array of integers and a target integer, find two numbers in the array that add up to the target.
This is solved efficiently using a hash map to store seen numbers and their indices.

## Intuition
The core idea is that for any number `nums[i]`, we are looking for a "complement" number that equals `target - nums[i]`. If we've already seen this complement, we've found our pair. A hash map is perfect for quickly checking if we've seen a number before and retrieving its index.

## Algorithm
1. Initialize an empty hash map (e.g., `HashMap<Integer, Integer>`) to store numbers and their indices.
2. Iterate through the input array `nums` from index `i = 0` to `nums.length - 1`.
3. For each element `nums[i]`:
    a. Calculate the `complement` needed: `complement = target - nums[i]`.
    b. Check if the `complement` already exists as a key in the hash map.
    c. If it exists, it means we have found the two numbers. Return a new array containing the current index `i` and the index of the complement (retrieved from the hash map: `hm.get(complement)`).
    d. If the `complement` does not exist, add the current number `nums[i]` as a key and its index `i` as the value to the hash map. This prepares for future iterations.
4. If the loop finishes without finding a pair, return an indicator that no solution was found (e.g., `new int[]{-1, -1}`).

## Concept to Remember
*   **Hash Maps (Dictionaries):** Efficient key-value storage for O(1) average time lookups, insertions, and deletions.
*   **Complementary Search:** The strategy of looking for `target - current_element` to find the required pair.
*   **In-place Modification vs. Auxiliary Space:** Understanding the trade-offs between using extra space for efficiency.

## Common Mistakes
*   **Returning the same element twice:** Not checking if the complement's index is the same as the current index if duplicates are allowed and the target is twice a number. (Though this specific problem guarantees exactly one solution and doesn't mention duplicates being an issue for the *same* index).
*   **Incorrectly handling the order of operations:** Adding the current number to the map *before* checking for its complement can lead to finding the element itself as its own complement.
*   **Not handling the case where no solution exists:** The problem statement usually guarantees a solution, but in general, it's good practice to consider this.
*   **Off-by-one errors in loops or index retrieval.**

## Complexity Analysis
*   **Time:** O(n) - We iterate through the array once. Hash map operations (containsKey, get, put) take O(1) on average.
*   **Space:** O(n) - In the worst case, we might store all `n` elements of the array in the hash map.

## Commented Code
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Create a HashMap to store numbers and their corresponding indices.
        // Key: number from the array, Value: index of that number.
        HashMap<Integer, Integer> hm = new HashMap<>();

        // Iterate through the input array 'nums' using a for loop.
        // 'i' represents the current index.
        for (int i = 0; i < nums.length; i++) {
            // Calculate the 'complement' value needed to reach the 'target'.
            // If nums[i] + complement = target, then complement = target - nums[i].
            int complement = target - nums[i];

            // Check if the 'complement' already exists as a key in our HashMap.
            // This means we have previously encountered the number that, when added to nums[i], equals the target.
            if (hm.containsKey(complement)) {
                // If the complement is found, we have our two numbers.
                // Return a new integer array containing:
                // 1. The index of the complement (retrieved from the HashMap using hm.get(complement)).
                // 2. The current index 'i'.
                // The problem guarantees exactly one solution, so we can return immediately.
                return new int[]{hm.get(complement), i};
            }
            // If the complement is NOT found in the HashMap,
            // add the current number 'nums[i]' and its index 'i' to the HashMap.
            // This makes it available for future iterations to find as a complement.
            hm.put(nums[i], i);
        }

        // If the loop completes without finding a pair (which shouldn't happen based on problem constraints,
        // but is good practice for general cases), return an indicator that no solution was found.
        return new int[]{-1, -1};
    }
}
```

## Interview Tips
*   **Explain your thought process:** Start by describing the brute-force approach (nested loops) and then explain why a hash map is a better solution for time complexity.
*   **Clarify constraints:** Ask if there can be duplicate numbers, if the same element can be used twice, or if there's always exactly one solution. This problem statement usually specifies these.
*   **Walk through an example:** Use a small example array and target to trace your algorithm's execution step-by-step, showing how the hash map is populated and checked.
*   **Discuss trade-offs:** Mention the space-time trade-off: we use extra space (hash map) to achieve better time complexity.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Recall the brute-force O(n^2) approach.
- [ ] Identify the need for faster lookups (hash map).
- [ ] Implement the hash map approach correctly, ensuring the order of operations (check then put).
- [ ] Handle edge cases or problem constraints (e.g., guaranteed solution).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and its optimizations.

## Similar Problems
*   3Sum
*   4Sum
*   Two Sum II - Input Array Is Sorted
*   Two Sum III - Data structure design

## Tags
`Array` `Hash Map`
