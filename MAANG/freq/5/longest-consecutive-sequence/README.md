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
This problem can be solved efficiently using a HashSet to store numbers and then iterating to find sequence starts.

## Intuition
The core idea is to avoid redundant checks. If we encounter a number `x`, and `x-1` is also present, then `x` cannot be the *start* of a consecutive sequence. The actual start must be `x-1` or something smaller. Therefore, we only need to initiate a sequence check when we find a number `x` for which `x-1` is *not* in our set. Once we find such a start, we can greedily extend the sequence by checking for `x+1`, `x+2`, and so on, until the sequence breaks.

## Algorithm
1. Create a `HashSet` to store all the numbers from the input array `nums`. This allows for O(1) average time complexity for checking the presence of a number.
2. Initialize a variable `max` to 0, which will store the length of the longest consecutive sequence found so far.
3. Iterate through each number `num` in the `HashSet`.
4. For each `num`, check if `num - 1` is present in the `HashSet`.
   - If `num - 1` is *not* present, it means `num` is a potential start of a consecutive sequence.
   - Initialize a `count` to 1 (for the current number `num`).
   - Initialize a `currentNum` to `num`.
   - While `currentNum + 1` is present in the `HashSet`:
     - Increment `currentNum`.
     - Increment `count`.
   - After the `while` loop finishes, `count` holds the length of the consecutive sequence starting at `num`.
   - Update `max` to be the maximum of `max` and `count`.
5. After iterating through all numbers in the `HashSet`, return `max`.

## Concept to Remember
*   **Hash Sets for Efficient Lookups:** Using a `HashSet` provides average O(1) time complexity for checking if an element exists, which is crucial for optimizing the search for consecutive numbers.
*   **Identifying Sequence Starts:** The key optimization is to only start counting a sequence when you find its *smallest* element (i.e., a number `x` where `x-1` is not present). This prevents redundant counting of the same sequence multiple times.
*   **Greedy Approach:** Once a sequence start is identified, we greedily extend it by checking for subsequent consecutive numbers.

## Common Mistakes
*   **Sorting the Array:** While sorting the array (O(N log N)) and then iterating to find consecutive elements is a valid approach, it's less efficient than the HashSet method (O(N)).
*   **Starting Sequence Checks from Every Number:** Iterating through the array and checking for `num+1`, `num+2`, etc., for *every* number, without first checking if `num-1` exists, leads to O(N^2) complexity.
*   **Incorrectly Handling Duplicates:** The HashSet naturally handles duplicates by storing each unique number only once. If not using a set, duplicate handling would be an additional concern.
*   **Off-by-One Errors:** Mismanaging the `count` or `currentNum` variables in the inner `while` loop can lead to incorrect sequence lengths.

## Complexity Analysis
*   **Time:** O(N) - The first loop to populate the HashSet takes O(N) time. The second loop iterates through the unique numbers in the HashSet. Although there's a nested `while` loop, each number is visited at most twice (once when it's `num` in the outer loop, and at most once when it's `curr+1` in the inner loop). Therefore, the total time complexity remains O(N).
*   **Space:** O(N) - In the worst case, the HashSet will store all N unique numbers from the input array.

## Commented Code
```java
class Solution {
    public int longestConsecutive(int[] nums) {
        // Create a HashSet to store all numbers for efficient O(1) average time lookups.
        HashSet<Integer> hs = new HashSet<>();
        // Iterate through the input array and add each number to the HashSet.
        for(int num:nums)hs.add(num);

        // Initialize 'max' to store the length of the longest consecutive sequence found so far.
        int max = 0;

        // Iterate through each unique number present in the HashSet.
        for(int num : hs){
          // Check if the current number 'num' is the start of a consecutive sequence.
          // It's a start if 'num - 1' is NOT present in the HashSet.
          if(!hs.contains(num-1)){
            // If it's a start, initialize the count for this sequence to 1 (for 'num' itself).
            int count=1;
            // Initialize 'curr' to the current number to start extending the sequence.
            int curr = num;
            // While the next consecutive number ('curr + 1') is present in the HashSet...
            while(hs.contains(curr+1)){
              // ...move to the next number in the sequence.
              curr++;
              // ...and increment the count for this sequence.
              count++;
            }
            // After finding the full length of the current consecutive sequence,
            // update 'max' if this sequence is longer than any found previously.
            max = Math.max(max,count);
          }
        }
        // Return the maximum length found.
        return max;
    }
}
```

## Interview Tips
*   **Explain the HashSet Optimization:** Clearly articulate *why* using a HashSet is beneficial and how it avoids O(N^2) complexity.
*   **Focus on the "Start of Sequence" Logic:** Emphasize the condition `!hs.contains(num - 1)` as the critical insight for efficiency.
*   **Walk Through an Example:** Use a small example array (e.g., `[100, 4, 200, 1, 3, 2]`) to trace the algorithm's execution step-by-step, showing how `max` is updated.
*   **Discuss Edge Cases:** Consider cases like an empty array, an array with a single element, or an array with all consecutive numbers.

## Revision Checklist
- [ ] Understand the problem statement: find the longest consecutive sequence.
- [ ] Recognize the inefficiency of sorting (O(N log N)).
- [ ] Understand the benefit of using a HashSet for O(1) average lookups.
- [ ] Implement the logic to identify the *start* of a sequence (`num - 1` not present).
- [ ] Implement the greedy extension of a sequence (`num + 1`, `num + 2`, ...).
- [ ] Correctly track and update the maximum sequence length.
- [ ] Analyze time and space complexity.
- [ ] Be prepared to explain the algorithm and its optimizations.

## Similar Problems
*   [229. Majority Element II](https://leetcode.com/problems/majority-element-ii/) (Uses Hash Map/Set for counting)
*   [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) (This problem)
*   [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/) (Uses Hash Map/Set for tracking elements within a window)
*   [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) (Sliding window, uses Set for tracking characters)

## Tags
`Array` `Hash Map` `Set`
