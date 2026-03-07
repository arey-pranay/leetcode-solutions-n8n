# Two Sum
---
## Problem Summary
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

## Approach and Intuition
The brute-force approach would be to check every possible pair of numbers in the array. This involves nested loops, where the outer loop iterates from the first element to the second-to-last, and the inner loop iterates from the element after the outer loop's current element to the end. For each pair, we check if their sum equals the target. If it does, we return their indices.

However, this approach has a time complexity of O(n^2), which might be too slow for larger inputs.

A more efficient approach uses a hash map (or dictionary). The intuition here is that for each number `num` in the array, we are looking for a "complement" number such that `num + complement = target`. This means `complement = target - num`.

We can iterate through the array once. For each `num`, we calculate its `complement`. Then, we check if this `complement` already exists in our hash map.
*   If the `complement` is found in the hash map, it means we have found the two numbers that sum up to the target. The index of the `complement` is stored as the value in the hash map, and the current index is the index of `num`. We return these two indices.
*   If the `complement` is not found, we add the current `num` and its index to the hash map. This way, if a future number needs the current `num` as its complement, we can quickly find it.

This approach allows us to find the solution in a single pass.

## Complexity Analysis
- Time: O(n) - We iterate through the array once. For each element, hash map lookups and insertions take average O(1) time.
- Space: O(n) - In the worst case, we might store all `n` elements of the array in the hash map.

## Code Walkthrough
```python
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        num_map = {}  # Initialize an empty hash map to store numbers and their indices
        
        # Iterate through the array with both index and value
        for i, num in enumerate(nums):
            complement = target - num  # Calculate the complement needed
            
            # Check if the complement exists in the hash map
            if complement in num_map:
                # If found, return the index of the complement and the current index
                return [num_map[complement], i]
            
            # If complement is not found, add the current number and its index to the map
            num_map[num] = i
            
        # This part is technically unreachable given the problem constraints (exactly one solution)
        # but good practice to have a return statement outside the loop if there's a possibility
        # of no solution.
        return [] 
```
1.  `num_map = {}`: An empty dictionary `num_map` is created. This dictionary will store the numbers from `nums` as keys and their corresponding indices as values.
2.  `for i, num in enumerate(nums):`: The code iterates through the input list `nums`. `enumerate` provides both the index (`i`) and the value (`num`) for each element.
3.  `complement = target - num`: For the current number `num`, we calculate the value `complement` that, when added to `num`, would equal `target`.
4.  `if complement in num_map:`: We check if this `complement` already exists as a key in our `num_map`.
    *   If `complement` is in `num_map`, it means we have previously encountered a number that is the complement of the current `num`. The index of that complement is stored as `num_map[complement]`. We have found our pair! The function immediately returns a list containing the index of the complement (`num_map[complement]`) and the current index (`i`).
5.  `num_map[num] = i`: If the `complement` is *not* found in `num_map`, it means the current `num` is not the second part of a pair we've seen so far. So, we add the current `num` and its index `i` to the `num_map`. This makes `num` available as a potential complement for future numbers in the array.
6.  `return []`: This line is a fallback. The problem statement guarantees exactly one solution, so this line should ideally never be reached. However, in a more general scenario where a solution might not exist, this would be the return value.

## Interview Tips
*   **Clarify Constraints:** Always ask about constraints like array size, range of numbers, and whether a solution is guaranteed. This helps in choosing the right algorithm.
*   **Brute Force First:** Start by explaining the brute-force O(n^2) solution. This shows you can solve the problem and then prompts you to think about optimization.
*   **Think about Data Structures:** When you need to quickly check for the existence of an element or its related value, think about hash maps (dictionaries).
*   **Explain the "Why":** Clearly articulate *why* the hash map approach is better (time complexity) and *how* it works (storing complements).
*   **Edge Cases (if applicable):** For this specific problem, the "exactly one solution" constraint simplifies things. But for other problems, consider empty arrays, arrays with one element, duplicate numbers, etc.
*   **Code Readability:** Use meaningful variable names (`num_map`, `complement`). Add comments where necessary, especially for complex logic.
*   **Test Cases:** Mentally walk through a few test cases:
    *   `nums = [2, 7, 11, 15], target = 9`
    *   `nums = [3, 2, 4], target = 6`
    *   `nums = [3, 3], target = 6`

## Optimization and Alternatives
*   **Sorting:** Another approach could be to sort the array first. Then, use two pointers, one starting at the beginning and one at the end.
    *   If `nums[left] + nums[right] == target`, you've found the pair. However, you'd need to store the original indices before sorting, which adds complexity.
    *   If `nums[left] + nums[right] < target`, increment `left`.
    *   If `nums[left] + nums[right] > target`, decrement `right`.
    *   **Complexity:** Sorting takes O(n log n). The two-pointer scan takes O(n). So, the overall time complexity is O(n log n). The space complexity depends on the sorting algorithm used (in-place or not), but typically O(1) or O(log n) for standard sorts.
    *   **Trade-off:** The hash map approach is generally preferred for this problem because it achieves O(n) time complexity, which is better than O(n log n). The trade-off is the O(n) space complexity.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the core requirement: finding two numbers that sum to a target.
- [ ] Consider brute-force solution and its time complexity.
- [ ] Think about data structures that enable faster lookups (Hash Map).
- [ ] Develop the hash map approach: store number-index pairs.
- [ ] For each number, calculate its complement.
- [ ] Check if the complement exists in the hash map.
- [ ] If found, return indices. If not, add the current number and its index.
- [ ] Analyze time and space complexity of the hash map approach.
- [ ] Walk through the code with an example.
- [ ] Consider alternative approaches (like sorting + two pointers) and their trade-offs.
- [ ] Practice explaining the solution clearly and concisely.

## Similar Problems
*   3Sum
*   4Sum
*   Subarray Sum Equals K
*   Contains Duplicate II

## Tags
`Array` `Hash Map`