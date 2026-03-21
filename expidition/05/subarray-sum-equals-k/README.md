# Subarray Sum Equals K

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Prefix Sum`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans=0;
        HashMap<Integer,Integer> hm = new HashMap<>();
        hm.put(0,1);
        int sum = 0;  
        for(int num : nums){
            sum+=num;
            ans += hm.getOrDefault(sum-k,0); // hum sum-k chhod skte hai
            hm.put(sum,hm.getOrDefault(sum,0)+1);
        }
        return ans;
    }
}

```

---

---
## Quick Revision
Given an array of integers `nums` and an integer `k`, find the total number of continuous subarrays whose sum equals `k`.
This problem is efficiently solved using a hash map to store prefix sums and their frequencies.

## Intuition
The core idea is to leverage prefix sums. If we have a prefix sum `P[i]` (sum of elements from index 0 to `i`), and we are looking for a subarray ending at index `i` with sum `k`, then we need to find a previous prefix sum `P[j]` (where `j < i`) such that `P[i] - P[j] = k`. Rearranging this, we get `P[j] = P[i] - k`.

So, as we iterate through the array and calculate the current prefix sum `current_sum`, we can check if `current_sum - k` has been seen before as a prefix sum. If it has, it means there exists a subarray ending at the current index whose sum is `k`. The number of times `current_sum - k` has been seen before tells us how many such subarrays exist.

We initialize the hash map with `(0, 1)` to handle cases where a subarray starting from the beginning of the array itself sums up to `k`. For example, if `nums = [3, 4, 7]` and `k = 7`, when `current_sum` becomes 7, `current_sum - k` is 0. The `(0, 1)` entry correctly accounts for the subarray `[3, 4]`.

## Algorithm
1. Initialize `count` to 0. This will store the number of subarrays with sum `k`.
2. Initialize `current_sum` to 0. This will store the prefix sum up to the current element.
3. Create a hash map `prefix_sum_counts` to store the frequency of each prefix sum encountered so far.
4. Add an entry `(0, 1)` to `prefix_sum_counts`. This handles cases where a subarray starting from index 0 sums to `k`.
5. Iterate through each number `num` in the input array `nums`:
    a. Add `num` to `current_sum`.
    b. Check if `current_sum - k` exists as a key in `prefix_sum_counts`.
    c. If it exists, add its corresponding value (frequency) to `count`. This is because each occurrence of `current_sum - k` as a prefix sum signifies a valid subarray ending at the current index.
    d. Update the frequency of `current_sum` in `prefix_sum_counts`. If `current_sum` is already a key, increment its value; otherwise, add it with a value of 1.
6. Return `count`.

## Concept to Remember
*   **Prefix Sums:** The cumulative sum of elements up to a certain index in an array. Useful for efficiently calculating sums of subarrays.
*   **Hash Maps (Dictionaries):** Data structures that store key-value pairs, allowing for O(1) average time complexity for lookups, insertions, and deletions. Crucial for tracking frequencies of prefix sums.
*   **Two Pointers (Not directly used here, but a common subarray technique):** While not the optimal approach for this specific problem due to negative numbers, it's a related technique for subarray problems.
*   **Complementary Counting:** Finding the count of something by looking for its "complement" (e.g., finding `P[j]` such that `P[i] - P[j] = k` is equivalent to finding `P[j] = P[i] - k`).

## Common Mistakes
*   **Not handling the initial `(0, 1)` case:** Forgetting to add `(0, 1)` to the hash map can lead to missing subarrays that start from the beginning of the array.
*   **Incorrectly updating the hash map:** Updating the hash map *before* checking for `current_sum - k` can lead to counting the current element as a separate subarray if `k` is 0.
*   **Integer Overflow:** If the sums can become very large, consider using a `long` for `current_sum` and the hash map keys. (Though for typical LeetCode constraints, `int` is usually sufficient).
*   **Brute-force approach:** Trying all possible subarrays (O(n^2) or O(n^3)) without using prefix sums and hash maps, which is too slow for larger inputs.

## Complexity Analysis
*   **Time:** O(n) - We iterate through the array once. Hash map operations (get, put) take O(1) on average.
*   **Space:** O(n) - In the worst case, all prefix sums could be distinct, requiring the hash map to store up to `n` entries.

## Commented Code
```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        // Initialize the answer (count of subarrays) to 0.
        int ans = 0;
        // Create a HashMap to store the frequency of prefix sums encountered so far.
        // Key: prefix sum, Value: count of occurrences of that prefix sum.
        HashMap<Integer, Integer> hm = new HashMap<>();
        // Initialize the map with a prefix sum of 0 occurring once.
        // This is crucial for subarrays that start from the beginning of the array.
        // If sum-k is 0, it means the subarray from index 0 to current index sums to k.
        hm.put(0, 1);
        // Initialize the current running sum to 0.
        int sum = 0;
        // Iterate through each number in the input array.
        for (int num : nums) {
            // Update the running sum by adding the current number.
            sum += num;
            // Check if a previous prefix sum exists such that (current_sum - previous_prefix_sum) = k.
            // This is equivalent to checking if (current_sum - k) exists in our map.
            // If it exists, it means there are 'hm.getOrDefault(sum - k, 0)' subarrays ending at the current index that sum to k.
            // Add this count to our total answer.
            ans += hm.getOrDefault(sum - k, 0);
            // Update the frequency of the current running sum in the hash map.
            // If 'sum' is already a key, increment its count; otherwise, add it with a count of 1.
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        // Return the total count of subarrays that sum to k.
        return ans;
    }
}
```

## Interview Tips
1.  **Explain the Prefix Sum Idea First:** Before diving into code, clearly articulate the prefix sum concept and how `P[i] - P[j] = k` leads to `P[j] = P[i] - k`. This shows your understanding of the core logic.
2.  **Justify the `(0, 1)` Initialization:** Be prepared to explain *why* `hm.put(0, 1)` is necessary. Use an example like `nums = [3, 4, 7], k = 7` to illustrate.
3.  **Trace an Example:** Walk through a small example (e.g., `nums = [1, 1, 1], k = 2`) step-by-step, showing how `sum`, `sum - k`, and the `hm` change, and how `ans` is updated.
4.  **Discuss Edge Cases:** Mention how the solution handles empty arrays (though constraints usually prevent this), arrays with negative numbers, and cases where `k` is 0.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Grasp the prefix sum concept.
- [ ] Understand the role of the hash map for storing prefix sum frequencies.
- [ ] Know why `hm.put(0, 1)` is essential.
- [ ] Be able to explain the time and space complexity.
- [ ] Practice tracing the algorithm with different examples.
- [ ] Consider edge cases like negative numbers and `k=0`.

## Similar Problems
*   Two Sum
*   Subarray Sums Divisible by K
*   Contiguous Array
*   Maximum Size Subarray Sum Equals k

## Tags
`Array` `Hash Map` `Prefix Sum`
