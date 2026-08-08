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
We use a HashSet to efficiently check for the presence of consecutive numbers and iterate through the set to find the longest sequence.

## Intuition
The naive approach of sorting and then iterating would be O(N log N). We want to achieve O(N).
The key insight is that we only need to start counting a consecutive sequence from its *beginning*. If a number `x` has `x-1` present in the set, then `x` is not the start of a new consecutive sequence; it's part of a sequence that started earlier. So, we only initiate a count if `num - 1` is *not* in our set. This ensures each consecutive sequence is processed exactly once from its starting element.

## Algorithm
1. Create a HashSet and add all elements from the input array `nums` into it. This allows for O(1) average time complexity for checking element existence.
2. Initialize a variable `max` to 0, which will store the length of the longest consecutive sequence found so far.
3. Iterate through each number `num` in the HashSet.
4. For each `num`, check if `num - 1` is present in the HashSet.
5. If `num - 1` is *not* present in the HashSet, it means `num` is the potential start of a new consecutive sequence.
6. Initialize a `count` variable to 1 and a `curr` variable to `num`.
7. While `curr + 1` is present in the HashSet, increment `count` and `curr`. This extends the current consecutive sequence.
8. After the `while` loop finishes, update `max` with the maximum of `count` and `max`.
9. After iterating through all numbers in the HashSet, return `max`.

## Concept to Remember
*   **Hash Sets:** Efficient O(1) average time complexity for insertion, deletion, and lookup. Crucial for avoiding repeated checks.
*   **Greedy Approach:** Making the locally optimal choice (starting a count only from the sequence's beginning) leads to the globally optimal solution.
*   **Set Properties:** Leveraging the ability to quickly check for the existence of elements.

## Common Mistakes
*   **Sorting:** Initially thinking of sorting the array, which leads to an O(N log N) solution instead of the optimal O(N).
*   **Starting Count from Every Element:** Iterating through the array and starting a count for every element, even if it's not the start of a sequence, leading to redundant computations.
*   **Incorrectly Handling Duplicates:** While the HashSet naturally handles duplicates, one might overthink their impact if not using a set.
*   **Off-by-One Errors:** Mismanaging the `count` or `curr` variables in the `while` loop.

## Complexity Analysis
*   **Time:** O(N) - The first loop to populate the HashSet takes O(N) time. The second loop iterates through the unique elements in the HashSet. Although there's a nested `while` loop, each number is visited at most twice (once in the outer loop and at most once in the inner `while` loop as `curr` or `curr+1`). Therefore, the total time complexity is O(N).
*   **Space:** O(N) - In the worst case, the HashSet will store all N unique elements from the input array.

## Commented Code
```java
class Solution {
    public int longestConsecutive(int[] nums) {
        // Create a HashSet to store all numbers for efficient lookups.
        HashSet<Integer> hs = new HashSet<>();
        // Iterate through the input array and add each number to the HashSet.
        for(int i : nums)hs.add(i);

        // Initialize max to store the length of the longest consecutive sequence found.
        int max = 0;
        // Iterate through each unique number in the HashSet.
        for(int num : hs){
            // Check if the current number is the start of a consecutive sequence.
            // It's a start if the previous number (num - 1) is NOT in the set.
            if(!hs.contains(num-1)){
                // If it's a start, initialize count to 1 for the current number.
                int count = 1;
                // Initialize curr to the current number to start checking for consecutive elements.
                int curr = num;
                // While the next consecutive number (curr + 1) exists in the set,
                // increment the count and move to the next number.
                while(hs.contains(curr+1)){
                    count++;
                    curr++;
                }
                // Update max with the maximum length found so far.
                max = Math.max(count,max);
            }
        }
        // Return the length of the longest consecutive sequence.
        return max;
    }
}
```

## Interview Tips
*   **Explain the HashSet Optimization:** Clearly articulate why using a HashSet is crucial for achieving O(N) time complexity and how it avoids O(N^2) or O(N log N) solutions.
*   **Focus on the "Start of Sequence" Logic:** Emphasize the condition `!hs.contains(num-1)` as the core of the efficient algorithm. Explain why this prevents redundant work.
*   **Walk Through an Example:** Use a small example like `[100, 4, 200, 1, 3, 2]` to demonstrate how the algorithm processes the numbers and finds the longest sequence (1, 2, 3, 4).
*   **Discuss Edge Cases:** Consider empty arrays, arrays with a single element, and arrays with all consecutive numbers.

## Revision Checklist
- [ ] Understand the problem statement: find the longest consecutive sequence.
- [ ] Recognize the need for an O(N) solution.
- [ ] Implement using a HashSet for O(1) lookups.
- [ ] Identify the "start of sequence" condition (`num - 1` not in set).
- [ ] Correctly implement the `while` loop to count consecutive elements.
- [ ] Update the maximum length found.
- [ ] Analyze time and space complexity.
- [ ] Be prepared to explain the logic and trade-offs.

## Similar Problems
*   [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)
*   [Group Anagrams](https://leetcode.com/problems/group-anagrams/)
*   [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

## Tags
`Array` `Hash Map`
