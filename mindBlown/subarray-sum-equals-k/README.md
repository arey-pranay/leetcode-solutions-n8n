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
The core idea is to leverage prefix sums. If we have a prefix sum `S_i` up to index `i`, and we are looking for a subarray ending at `i` with sum `k`, then the sum of elements *before* this subarray must be `S_i - k`.
If we maintain a count of how many times each prefix sum has occurred so far, we can quickly check if `S_i - k` has appeared previously. If it has, each occurrence of `S_i - k` signifies a valid subarray ending at `i` with sum `k`.

## Algorithm
1. Initialize `count` (the number of subarrays) to 0.
2. Initialize `currentSum` to 0.
3. Create a hash map `prefixSumCount` to store the frequency of each prefix sum encountered.
4. Add an initial entry to `prefixSumCount`: `(0, 1)`. This handles cases where a subarray starting from the beginning of the array sums to `k`.
5. Iterate through each number `num` in the input array `nums`:
    a. Update `currentSum` by adding `num` to it.
    b. Check if `currentSum - k` exists as a key in `prefixSumCount`. If it does, it means there's a previous prefix sum such that the difference between the `currentSum` and that prefix sum is `k`. Add the frequency of `currentSum - k` from `prefixSumCount` to `count`.
    c. Update the frequency of `currentSum` in `prefixSumCount`. If `currentSum` is already a key, increment its value; otherwise, add `currentSum` as a new key with a value of 1.
6. Return `count`.

## Concept to Remember
*   **Prefix Sums:** The sum of elements from the beginning of an array up to a certain index. This technique helps in efficiently calculating sums of subarrays.
*   **Hash Maps (Dictionaries):** Used for efficient lookups (average O(1)) to store and retrieve frequencies of prefix sums.
*   **Cumulative Sums:** A variation of prefix sums where each element stores the sum of all preceding elements.

## Common Mistakes
*   **Not handling the `prefixSumCount.put(0, 1)` case:** This is crucial for subarrays that start from index 0 and sum up to `k`. Without it, these subarrays would be missed.
*   **Incorrectly calculating `currentSum - k`:** Ensuring the logic `currentSum - k` is used to find the *previous* required prefix sum is key.
*   **Overwriting prefix sum counts:** When updating `prefixSumCount`, ensure you are incrementing the count for an existing sum, not just replacing it.
*   **Missing edge cases:** Consider empty arrays or arrays where `k` cannot be formed. The current algorithm handles these gracefully.

## Complexity Analysis
*   **Time:** O(n) - We iterate through the array once. Hash map operations (put, getOrDefault) take average O(1) time.
*   **Space:** O(n) - In the worst case, all prefix sums can be distinct, requiring storage for each in the hash map.

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
        // This is crucial for subarrays that start from the beginning of the array and sum to k.
        hm.put(0, 1);
        // Initialize the current running sum to 0.
        int sum = 0;
        // Iterate through each number in the input array.
        for (int num : nums) {
            // Update the current running sum by adding the current number.
            sum += num;
            // Check if a previous prefix sum exists such that (currentSum - previousPrefixSum) = k.
            // This is equivalent to checking if (currentSum - k) exists as a key in our hash map.
            // If it exists, it means there are 'hm.getOrDefault(sum - k, 0)' subarrays ending at the current index
            // whose sum is k. Add this count to our total answer.
            ans += hm.getOrDefault(sum - k, 0);
            // Update the frequency of the current prefix sum in the hash map.
            // If 'sum' is already a key, increment its count; otherwise, add it with a count of 1.
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        // Return the total count of subarrays whose sum equals k.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the prefix sum intuition clearly:** Start by explaining why prefix sums are useful for subarray sum problems.
*   **Walk through an example:** Use a small array and `k` to trace the algorithm's execution, showing how the hash map is populated and used.
*   **Discuss the `(0, 1)` initialization:** Emphasize its importance for subarrays starting at index 0.
*   **Be prepared to discuss edge cases:** Mention how the algorithm handles empty arrays or cases where no subarray sums to `k`.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Grasp the concept of prefix sums.
- [ ] Understand how the hash map stores frequencies of prefix sums.
- [ ] Recognize the `sum - k` logic for finding previous valid sums.
- [ ] Remember to initialize the hash map with `(0, 1)`.
- [ ] Practice tracing the algorithm with different examples.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Two Sum
*   Subarray Sums Divisible by K
*   Contiguous Array

## Tags
`Array` `Hash Map` `Prefix Sum`
