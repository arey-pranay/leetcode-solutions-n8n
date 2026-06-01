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
        for(int i :nums) hs.add(i);
        int max = 0;
        for(int num : hs){
            if(!hs.contains(num-1)){
                int curr = num;
                int count = 1;
                while(hs.contains(curr+1)){
                    curr++;
                    count++;
                }
                max = Math.max(max,count);
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
We use a HashSet to store numbers and then iterate through them, checking for consecutive sequences starting from numbers that are not preceded by a smaller consecutive number.

## Intuition
The naive approach would be to sort the array and then iterate, which takes O(N log N) time. However, we can do better. If we have a number `x`, and we want to check if it's part of a consecutive sequence, we only need to know if `x-1` exists. If `x-1` *doesn't* exist, then `x` *could be the start of a new consecutive sequence*. This is the key insight. If `x-1` exists, then `x` is part of a sequence that started earlier, and we don't need to re-evaluate it from `x`. By using a HashSet, we can check for the existence of `x-1` and `x+1` in O(1) time on average.

## Algorithm
1. Create a HashSet and add all elements from the input array `nums` into it. This allows for O(1) average time complexity for checking element existence.
2. Initialize a variable `max` to 0, which will store the length of the longest consecutive sequence found so far.
3. Iterate through each number `num` in the HashSet.
4. For each `num`, check if `num - 1` is present in the HashSet.
5. If `num - 1` is *not* present in the HashSet, it means `num` is the potential start of a new consecutive sequence.
6. Initialize a `currentNum` to `num` and a `currentStreak` to 1.
7. While `currentNum + 1` is present in the HashSet, increment `currentNum` and `currentStreak`. This extends the current consecutive sequence.
8. After the `while` loop finishes, update `max` with the maximum of `max` and `currentStreak`.
9. After iterating through all numbers in the HashSet, return `max`.

## Concept to Remember
*   **Hash Sets:** Efficient O(1) average time complexity for insertion, deletion, and lookup. Crucial for quickly checking the existence of consecutive numbers.
*   **Greedy Approach:** By only starting a sequence count when we find a number that *isn't* preceded by a smaller consecutive number, we ensure each consecutive sequence is counted exactly once from its smallest element.
*   **Time-Space Tradeoff:** Using extra space (HashSet) to achieve better time complexity.

## Common Mistakes
*   **Sorting First:** Implementing a solution that sorts the array first (O(N log N)) when an O(N) solution is possible.
*   **Re-counting Sequences:** Iterating through all numbers and, for each, checking for both `num-1` and `num+1` without the optimization of only starting from the "beginning" of a sequence. This leads to redundant checks and potentially O(N^2) complexity.
*   **Off-by-One Errors:** Incorrectly handling the `count` or `currentNum` updates in the `while` loop.
*   **Empty Input:** Not considering the edge case where the input array is empty.

## Complexity Analysis
*   **Time:** O(N) - The first loop to populate the HashSet takes O(N) time. The second loop iterates through each number in the HashSet. Although there's a nested `while` loop, each number is visited at most twice (once in the outer loop and at most once in the inner `while` loop as part of a sequence). Therefore, the total time complexity is O(N).
*   **Space:** O(N) - In the worst case, the HashSet will store all N unique elements from the input array.

## Commented Code
```java
import java.util.HashSet; // Import the HashSet class for efficient element storage and lookup.

class Solution {
    public int longestConsecutive(int[] nums) {
        // Create a HashSet to store all numbers from the input array.
        // This allows for O(1) average time complexity for checking if a number exists.
        HashSet<Integer> hs = new HashSet<>();
        // Iterate through each number in the input array 'nums'.
        for(int i :nums) {
            // Add the current number 'i' to the HashSet.
            hs.add(i);
        }

        // Initialize 'max' to 0. This variable will store the length of the longest consecutive sequence found.
        int max = 0;

        // Iterate through each unique number 'num' present in the HashSet.
        for(int num : hs){
            // Check if the number 'num - 1' exists in the HashSet.
            // If 'num - 1' does NOT exist, it means 'num' is the potential start of a new consecutive sequence.
            if(!hs.contains(num-1)){
                // Initialize 'curr' to the current number 'num'. This will be used to traverse the sequence.
                int curr = num;
                // Initialize 'count' to 1, as 'num' itself is the first element of this potential sequence.
                int count = 1;

                // While the next consecutive number ('curr + 1') is present in the HashSet:
                while(hs.contains(curr+1)){
                    // Move to the next number in the sequence.
                    curr++;
                    // Increment the count for the current consecutive sequence.
                    count++;
                }
                // After finding the full length of the current consecutive sequence,
                // update 'max' if the current sequence's length ('count') is greater than the current 'max'.
                max = Math.max(max,count);
            }
        }
        // Return the maximum length of any consecutive sequence found.
        return max;
    }
}
```

## Interview Tips
*   **Explain the HashSet Optimization:** Clearly articulate *why* using a HashSet is beneficial and how it leads to O(N) time complexity by enabling O(1) lookups.
*   **Focus on the "Start of Sequence" Logic:** Emphasize the condition `!hs.contains(num-1)` as the core idea that prevents redundant work and ensures each sequence is counted only once.
*   **Walk Through an Example:** Use a small example array (e.g., `[100, 4, 200, 1, 3, 2]`) to trace the algorithm's execution, showing how `max` is updated.
*   **Discuss Edge Cases:** Mention handling an empty input array (which should return 0).

## Revision Checklist
- [ ] Understand the problem statement: find the longest consecutive sequence.
- [ ] Recognize that sorting is O(N log N) and aim for O(N).
- [ ] Implement using a HashSet for O(1) average lookups.
- [ ] Identify the "start of sequence" condition (`num - 1` not present).
- [ ] Correctly implement the inner `while` loop to count the sequence length.
- [ ] Update the maximum length found.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases like an empty input array.

## Similar Problems
*   [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) (Can be solved with similar set-based thinking or Floyd's cycle-finding)
*   [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) (This is the problem itself)
*   [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) (Uses a sliding window and a set/map)

## Tags
`Array` `Hash Map` `Set`
