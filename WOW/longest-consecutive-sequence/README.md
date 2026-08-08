# Longest Consecutive Sequence

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Union-Find`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for(int i : nums)hs.add(i);
        int max = 0;
        for(int num : hs){
            if(!hs.contains(num-1)){
                int count = 1;
                int curr = num;
                while(hs.contains(curr+1)){
                    count++;
                    curr++;
                }
                max = Math.max(count,max);
            }
        }
        return max;
    }
}
```

---

---
## Quick Revision
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
Solve by using a HashSet to efficiently check for consecutive numbers and avoid redundant checks.

## Intuition
The core idea is to efficiently check if a number is part of a consecutive sequence. If we iterate through the numbers, for each number `x`, we want to know if `x+1`, `x+2`, etc., exist. A naive approach would involve sorting, but that's O(N log N). Using a HashSet allows O(1) average time lookups.

The "aha moment" comes from realizing we only need to start counting a sequence from its *beginning*. If we encounter a number `x` and `x-1` is *also* in the set, then `x` is not the start of a new consecutive sequence; it's part of a sequence that started earlier. Therefore, we only initiate a count when we find a number `num` for which `num-1` is *not* present in the set. This ensures each consecutive sequence is counted exactly once, from its starting element.

## Algorithm
1. Create a `HashSet` and add all elements from the input array `nums` into it. This allows for O(1) average time complexity for checking the existence of an element.
2. Initialize a variable `max` to 0, which will store the length of the longest consecutive sequence found so far.
3. Iterate through each number `num` in the `HashSet`.
4. For each `num`, check if `num - 1` exists in the `HashSet`.
   - If `num - 1` is *not* in the `HashSet`, it means `num` is the potential start of a new consecutive sequence.
   - Initialize a `count` variable to 1 (for the current number `num`).
   - Initialize a `curr` variable to `num`.
   - While `curr + 1` exists in the `HashSet`:
     - Increment `count`.
     - Increment `curr` to check for the next consecutive number.
   - After the `while` loop finishes, `count` holds the length of the consecutive sequence starting from `num`.
   - Update `max` to be the maximum of `max` and `count`.
5. After iterating through all numbers in the `HashSet`, return `max`.

## Concept to Remember
*   **Hash Sets**: Efficient O(1) average time complexity for insertion, deletion, and lookup. Crucial for quickly checking the presence of numbers.
*   **Greedy Approach**: By only starting a sequence count from its smallest element (i.e., a number `x` where `x-1` is not present), we ensure each sequence is processed exactly once.
*   **Time-Space Tradeoff**: Using extra space (HashSet) to achieve better time complexity.

## Common Mistakes
*   **Sorting the array**: While sorting works, it leads to an O(N log N) time complexity, which is less optimal than the O(N) HashSet approach.
*   **Starting sequence count from every number**: Iterating through every number and checking for `num+1`, `num+2`, etc., without the `num-1` check, leads to redundant counting of the same sequence multiple times.
*   **Handling empty input**: The code should gracefully handle an empty input array (though the provided solution implicitly does this by returning 0 if `nums` is empty).
*   **Integer Overflow**: For very large integer ranges, though not typically an issue with standard LeetCode constraints for this problem.

## Complexity Analysis
*   **Time**: O(N) - The first loop to populate the HashSet takes O(N) time. The second loop iterates through each unique number in the HashSet. Although there's a nested `while` loop, each number is visited at most twice (once when it's `num` and potentially once when it's `curr+1`). Therefore, the total time complexity remains O(N).
*   **Space**: O(N) - In the worst case, all elements are unique and will be stored in the HashSet.

## Commented Code
```java
class Solution {
    public int longestConsecutive(int[] nums) {
        // Create a HashSet to store all numbers from the input array.
        // This allows for O(1) average time complexity for checking if a number exists.
        HashSet<Integer> hs = new HashSet<>();
        
        // Iterate through the input array and add each number to the HashSet.
        for(int i : nums)hs.add(i);
        
        // Initialize 'max' to 0. This variable will store the length of the longest consecutive sequence found.
        int max = 0;
        
        // Iterate through each number 'num' present in the HashSet.
        for(int num : hs){
            // Check if the current number 'num' is the start of a consecutive sequence.
            // It's the start if 'num - 1' is NOT present in the HashSet.
            if(!hs.contains(num-1)){
                // If 'num' is the start, initialize 'count' to 1 (for the current number itself).
                int count = 1;
                // Initialize 'curr' to the current number 'num' to start checking for subsequent consecutive numbers.
                int curr = num;
                
                // While the next consecutive number (curr + 1) exists in the HashSet:
                while(hs.contains(curr+1)){
                    // Increment the count of the current consecutive sequence.
                    count++;
                    // Move to the next number in the sequence.
                    curr++;
                }
                // After finding the full length of the consecutive sequence starting at 'num',
                // update 'max' if the current sequence length ('count') is greater than the maximum found so far.
                max = Math.max(count,max);
            }
        }
        // Return the length of the longest consecutive sequence found.
        return max;
    }
}
```

## Interview Tips
*   **Clarify Constraints**: Ask about the range of numbers, potential for duplicates, and whether the input array can be empty.
*   **Explain the HashSet Rationale**: Clearly articulate why a HashSet is chosen over sorting (time complexity) and how it enables efficient lookups.
*   **Walk Through the `num-1` Check**: Emphasize the importance of the `!hs.contains(num-1)` condition to avoid redundant work and ensure each sequence is counted only once.
*   **Consider Edge Cases**: Discuss how an empty array or an array with a single element would be handled.

## Revision Checklist
- [ ] Understand the problem statement: find the longest consecutive sequence.
- [ ] Recognize the inefficiency of sorting (O(N log N)).
- [ ] Identify the benefit of using a HashSet for O(1) average lookups.
- [ ] Implement the algorithm: populate HashSet, iterate, check `num-1`, count sequence, update max.
- [ ] Analyze time complexity: O(N).
- [ ] Analyze space complexity: O(N).
- [ ] Practice explaining the `num-1` optimization.
- [ ] Consider edge cases like empty input.

## Similar Problems
*   [229. Majority Element II](https://leetcode.com/problems/majority-element-ii/) (Uses HashMap for counting, related to frequency)
*   [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) (This problem itself)
*   [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) (Uses HashMap/HashSet for proximity checks)
*   [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) (Sliding window, uses Set for uniqueness)

## Tags
`Array` `Hash Map`
