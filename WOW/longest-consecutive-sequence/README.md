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
        for(int num:nums)hs.add(num);
        int max = 0;
        for(int num : hs){
          if(!hs.contains(num-1)){
            int count=1;
            int curr = num;
            while(hs.contains(curr+1)){curr++; count++;}
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
We use a HashSet to store numbers and then iterate through them, checking for consecutive sequences starting from numbers that are the beginning of a sequence.

## Intuition
The core idea is to efficiently check for the existence of consecutive numbers. If we have a number `x`, we want to know if `x+1`, `x+2`, etc., exist. A naive approach of sorting would be O(N log N). However, if we can check for the existence of a number in O(1) time, we can do better. A HashSet provides this O(1) average time complexity for lookups.

The crucial optimization is to only start counting a sequence if the current number `num` is the *start* of a sequence. How do we know if `num` is the start? If `num - 1` is *not* present in our set. If `num - 1` *is* present, then `num` is part of a longer sequence that starts at `num - 1` (or even earlier), and we'll eventually count it when we process that earlier number. This prevents redundant counting and ensures each consecutive sequence is counted only once from its starting element.

## Algorithm
1. Create a `HashSet` to store all the numbers from the input array `nums`. This allows for O(1) average time complexity for checking the presence of a number.
2. Initialize a variable `max` to 0, which will store the length of the longest consecutive sequence found so far.
3. Iterate through each number `num` in the `HashSet`.
4. For each `num`, check if `num - 1` is present in the `HashSet`.
   - If `num - 1` is *not* present, it means `num` is the potential start of a new consecutive sequence.
   - Initialize a `count` variable to 1 (for the current number `num`).
   - Initialize a `curr` variable to `num`.
   - While `curr + 1` is present in the `HashSet`:
     - Increment `curr` by 1.
     - Increment `count` by 1.
   - After the `while` loop finishes, `count` holds the length of the consecutive sequence starting at `num`.
   - Update `max` to be the maximum of `max` and `count`.
5. After iterating through all numbers in the `HashSet`, return `max`.

## Concept to Remember
*   **Hash Sets for Efficient Lookups:** Using a hash set (like `HashSet` in Java) provides average O(1) time complexity for insertion and checking membership, which is key to optimizing this problem.
*   **Identifying Sequence Starts:** The strategy of only starting a count when `num - 1` is not present is crucial for avoiding redundant work and achieving optimal time complexity.
*   **Iterating Through Unique Elements:** By first storing all numbers in a set, we automatically handle duplicates and iterate only through unique elements, simplifying the logic.

## Common Mistakes
*   **Sorting the Array:** While sorting works, it leads to an O(N log N) time complexity, which is not optimal. The problem can be solved in O(N) time.
*   **Redundant Counting:** Not checking if `num - 1` exists before starting a count can lead to counting the same sequence multiple times, making the algorithm inefficient.
*   **Off-by-One Errors:** Incorrectly handling the `count` or `curr` variables in the `while` loop can lead to incorrect sequence lengths.
*   **Not Handling Empty Input:** The code should ideally handle cases where the input array `nums` is empty or null.

## Complexity Analysis
*   **Time:** O(N) - The first loop to populate the `HashSet` takes O(N) time. The second loop iterates through each unique number in the `HashSet`. Although there's a nested `while` loop, each number is visited at most twice (once in the outer loop and at most once in the inner `while` loop as `curr` increments). Therefore, the total time complexity is O(N).
*   **Space:** O(N) - In the worst case, all numbers in the input array are unique, and the `HashSet` will store all N elements.

## Commented Code
```java
class Solution {
    public int longestConsecutive(int[] nums) {
        // Create a HashSet to store all numbers from the input array.
        // This allows for O(1) average time complexity for checking if a number exists.
        HashSet<Integer> hs = new HashSet<>();
        
        // Iterate through the input array and add each number to the HashSet.
        for(int num:nums)hs.add(num);
        
        // Initialize 'max' to 0. This variable will store the length of the longest consecutive sequence found.
        int max = 0;
        
        // Iterate through each unique number present in the HashSet.
        for(int num : hs){
          // Check if the current number 'num' is the start of a consecutive sequence.
          // It's the start if 'num - 1' is NOT present in the HashSet.
          if(!hs.contains(num-1)){
            // If 'num' is the start, initialize 'count' to 1 (for the current number itself).
            int count=1;
            // Initialize 'curr' to the current number 'num'. This variable will help us traverse the sequence.
            int curr = num;
            
            // While the next consecutive number (curr + 1) exists in the HashSet,
            // it means the sequence continues.
            while(hs.contains(curr+1)){
              // Move to the next number in the sequence.
              curr++;
              // Increment the count of consecutive numbers.
              count++;
            }
            
            // After finding the full length of the current consecutive sequence,
            // update 'max' if this sequence is longer than any found so far.
            max = Math.max(max,count);
          }
        }
        // Return the length of the longest consecutive sequence found.
        return max;
    }
}
```

## Interview Tips
*   **Explain the HashSet Optimization:** Clearly articulate *why* a `HashSet` is used and how it achieves O(1) lookups, contrasting it with sorting.
*   **Emphasize the "Start of Sequence" Check:** This is the most critical part of the O(N) solution. Explain how `!hs.contains(num - 1)` prevents redundant work and ensures each sequence is counted only once.
*   **Walk Through an Example:** Use a small example array (e.g., `[100, 4, 200, 1, 3, 2]`) to trace the algorithm's execution, showing how `max` is updated.
*   **Discuss Edge Cases:** Be prepared to discuss what happens with an empty array, an array with one element, or an array with all duplicate elements.

## Revision Checklist
- [ ] Understand the problem statement: find the longest consecutive sequence.
- [ ] Recognize the inefficiency of sorting (O(N log N)).
- [ ] Identify the need for O(1) lookups (HashSet).
- [ ] Implement the HashSet population.
- [ ] Implement the logic to identify the *start* of a sequence (`!hs.contains(num - 1)`).
- [ ] Implement the inner `while` loop to count the sequence length.
- [ ] Implement the `max` update logic.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty array, single element).

## Similar Problems
*   [229. Majority Element II](https://leetcode.com/problems/majority-element-ii/) (Uses Hash Map for counting)
*   [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) (This problem)
*   [202. Happy Number](https://leetcode.com/problems/happy-number/) (Uses HashSet to detect cycles)
*   [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) (Sliding window with HashSet)

## Tags
`Array` `Hash Map`
