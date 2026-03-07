# Two Sum
---
## Problem Summary
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Approach and Intuition
The core idea is to efficiently find if a "complement" exists for each number in the array. The complement for a number `num` is `target - num`. If we can quickly check if this complement exists in the array and retrieve its index, we can solve the problem.

A brute-force approach would be to iterate through every possible pair of numbers in the array and check if their sum equals the target. This would involve nested loops, leading to O(n^2) time complexity.

To optimize this, we can use a hash map (or dictionary in Python). The hash map will store each number from the array as a key and its index as the value.

We iterate through the array once. For each number `nums[i]`, we calculate its complement: `complement = target - nums[i]`. Then, we check if this `complement` already exists as a key in our hash map.

*   If the `complement` is found in the hash map, it means we have found the two numbers that sum up to the target. We return the current index `i` and the index stored in the hash map for the `complement`.
*   If the `complement` is not found, we add the current number `nums[i]` and its index `i` to the hash map. This way, future numbers can check against it.

This approach ensures that we only iterate through the array once, and hash map lookups are, on average, O(1).

## Complexity Analysis
- Time: O(n) - We iterate through the input array `nums` exactly once. For each element, we perform a hash map lookup and insertion, both of which take O(1) time on average.
- Space: O(n) - In the worst case, we might store all `n` elements of the `nums` array in the hash map if the solution pair is found towards the end of the array.

## Code Walkthrough
```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_map = {}  # Initialize an empty hash map to store number -> index
        
        for i, num in enumerate(nums):  # Iterate through the array with index and value
            complement = target - num  # Calculate the complement needed
            
            if complement in num_map:  # Check if the complement is already in our map
                # If found, we have our pair. Return the current index and the stored index of the complement.
                return [num_map[complement], i]
            
            # If complement is not found, add the current number and its index to the map
            # This makes it available for future elements to check against.
            num_map[num] = i
            
        # According to the problem statement, there will always be exactly one solution,
        # so this part of the code should theoretically never be reached.
        return [] 
```

1.  `num_map = {}`: An empty dictionary `num_map` is created. This dictionary will store numbers from `nums` as keys and their corresponding indices as values.
2.  `for i, num in enumerate(nums):`: The code iterates through the `nums` array. `enumerate` provides both the index (`i`) and the value (`num`) for each element.
3.  `complement = target - num`: For the current number `num`, we calculate the value `complement` that, when added to `num`, would equal `target`.
4.  `if complement in num_map:`: We check if this `complement` already exists as a key in our `num_map`.
    *   If `True`: This means we have previously encountered the number that, when paired with the current `num`, sums up to `target`. We then return a list containing the index of the `complement` (retrieved from `num_map[complement]`) and the current index `i`.
    *   If `False`: The `complement` has not been seen yet.
5.  `num_map[num] = i`: If the `complement` was not found, we add the current number `num` and its index `i` to the `num_map`. This makes `num` available as a potential `complement` for subsequent numbers in the array.
6.  `return []`: This line is a fallback. The problem guarantees exactly one solution, so this line should not be reached in a valid test case.

## Interview Tips
*   **Clarify Constraints:** Always ask about constraints like array size, range of numbers, and whether duplicates are allowed. For this problem, knowing there's exactly one solution is crucial.
*   **Start with Brute Force:** Even if you know the optimal solution, briefly mentioning the O(n^2) brute-force approach shows you can think through different possibilities and understand trade-offs.
*   **Explain the Hash Map Rationale:** Clearly articulate *why* a hash map is suitable. Emphasize its O(1) average time complexity for lookups and insertions, which is key to achieving O(n) overall.
*   **Edge Cases:** Consider what happens if the array is empty, or if the target requires using the same element twice (though this problem disallows it).
*   **Walk Through an Example:** Pick a small example array and target, and verbally walk through how your hash map approach would solve it step-by-step.
*   **Code Readability:** Use meaningful variable names (e.g., `num_map`, `complement`). Add comments where necessary, especially for non-obvious logic.

## Optimization and Alternatives
*   **Sorting (Less Optimal for this specific problem):**
    *   **Approach:** Sort the array first. Then, use two pointers, one starting at the beginning (`left`) and one at the end (`right`).
    *   **Logic:** If `nums[left] + nums[right] == target`, you've found the pair. If the sum is less than `target`, increment `left`. If the sum is greater than `target`, decrement `right`.
    *   **Complexity:**
        *   Time: O(n log n) due to sorting. The two-pointer scan is O(n).
        *   Space: O(1) or O(n) depending on the sorting algorithm used (in-place vs. not).
    *   **Drawback:** This approach modifies the original array or requires extra space to store sorted indices, and it's less efficient time-wise than the hash map approach for this specific problem where we need original indices.
*   **Brute Force (Baseline):**
    *   **Approach:** Use nested loops to check every pair of numbers.
    *   **Complexity:**
        *   Time: O(n^2)
        *   Space: O(1)

The hash map approach is generally preferred for this problem due to its optimal O(n) time complexity and reasonable O(n) space complexity.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the core requirement: finding two numbers that sum to a target.
- [ ] Consider brute-force solutions and their complexity.
- [ ] Think about data structures that can speed up lookups (Hash Map).
- [ ] Develop the hash map approach: store number-index pairs.
- [ ] Iterate through the array, calculate complement, and check hash map.
- [ ] Handle the case where the complement is found.
- [ ] Handle the case where the complement is not found (add current number to map).
- [ ] Analyze time and space complexity of the chosen approach.
- [ ] Write clean, readable code.
- [ ] Test with example cases.
- [ ] Consider alternative approaches and their trade-offs.

## Similar Problems
*   3Sum
*   4Sum
*   Subarray Sum Equals K
*   Contains Duplicate II
*   Pair of Songs With Total Durations Divisible by 60

## Tags
`Array` `Hash Map`