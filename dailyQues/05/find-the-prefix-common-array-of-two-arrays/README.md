# Find The Prefix Common Array Of Two Arrays

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Bit Manipulation`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int ans[] = new int[n];
        int[] freq = new int[n+1];
        int done = 0;
        for(int i = 0;i<n;i++){
            freq[A[i]]++;
            freq[B[i]]++;
            if(freq[A[i]] > 1) done++;
            if(A[i] != B[i]) if(freq[B[i]] > 1) done++;
            ans[i] = done;
        }
        return ans;
        
    }
}

// 1 3 2 4
// 3 1 2 4

//   sb1.append(A[i]);
//             sb2.append(B[i]);
//             sortSb(sb1);
//             sortSb(sb2);
//             System.out.println(sb1 + " " + sb2);
//             if(sb1.toString().equals(sb2.toString())) ans[i] = sb1.length();
//             else{
//                 if(i==0) ans[i] = 0;
//                 else ans[i] = ans[i-1];
//             }
```

---

---
## Quick Revision
Given two arrays A and B of the same length, find an array `ans` where `ans[i]` is the count of numbers that appear in both `A[0...i]` and `B[0...i]`.
This is solved by iterating through the arrays, maintaining frequency counts of elements seen so far in both arrays, and updating the common count at each prefix.

## Intuition
The core idea is to efficiently track which numbers have appeared in the prefixes of both arrays. For each prefix `i`, we need to know how many numbers are present in `A[0...i]` AND `B[0...i]`. A naive approach would be to sort and compare prefixes at each step, which is too slow. A better approach is to use frequency counts. As we extend the prefix by one element at a time, we update the counts of the new elements from `A[i]` and `B[i]`. If an element's count becomes 2 (meaning it has now appeared in both arrays), we increment our "common count" for that prefix.

## Algorithm
1. Initialize an integer array `ans` of the same length as `A` (and `B`) to store the results.
2. Initialize an integer array `freq` of size `n + 1` (where `n` is the length of `A` and `B`) to store the frequency of each number encountered so far. This array will act as a frequency map.
3. Initialize an integer variable `commonCount` to 0. This variable will track the number of common elements for the current prefix.
4. Iterate from `i = 0` to `n - 1`:
    a. Increment the frequency of `A[i]` in the `freq` array: `freq[A[i]]++`.
    b. If `freq[A[i]]` becomes 2, it means `A[i]` has now appeared in both arrays (since it was just added to `A` and must have already been in `B` or will be added to `B` in this iteration). Increment `commonCount`.
    c. Increment the frequency of `B[i]` in the `freq` array: `freq[B[i]]++`.
    d. If `B[i]` is different from `A[i]` (to avoid double counting if `A[i] == B[i]`) AND `freq[B[i]]` becomes 2, it means `B[i]` has now appeared in both arrays. Increment `commonCount`.
    e. Set `ans[i]` to the current `commonCount`.
5. Return the `ans` array.

## Concept to Remember
*   **Prefix Sum/Array:** Understanding how to process arrays incrementally, where each step depends on the previous one.
*   **Frequency Mapping (Hash Maps/Arrays):** Efficiently counting occurrences of elements.
*   **Two Pointers/Simultaneous Iteration:** Processing two arrays in lockstep.
*   **In-place Updates:** Modifying data structures as you iterate.

## Common Mistakes
*   **Double Counting:** Incorrectly incrementing `commonCount` when `A[i] == B[i]` and both become 2. The logic needs to handle this edge case.
*   **Off-by-One Errors:** Incorrectly sizing the `freq` array or loop bounds.
*   **Inefficient Prefix Comparison:** Trying to sort and compare substrings or sub-arrays at each step, leading to O(N^2 log N) or O(N^3) complexity.
*   **Not Handling the `A[i] != B[i]` Condition:** Forgetting to check if `A[i]` and `B[i]` are different before checking `freq[B[i]] > 1` to avoid double counting.

## Complexity Analysis
- Time: O(N) - The code iterates through the arrays `A` and `B` once. Each operation inside the loop (array access, increment, comparison) takes constant time.
- Space: O(N) - An auxiliary array `freq` of size `N+1` is used to store frequencies. The `ans` array also takes O(N) space.

## Commented Code
```java
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        // Get the length of the input arrays. Both are guaranteed to have the same length.
        int n = A.length;
        // Initialize the result array 'ans' with the same length as the input arrays.
        // ans[i] will store the count of common elements in A[0...i] and B[0...i].
        int ans[] = new int[n];
        // Initialize a frequency array 'freq'. The size is n+1 because the problem constraints
        // usually imply numbers are within a certain range (e.g., 1 to n).
        // This array will store the count of each number encountered so far across both arrays.
        int[] freq = new int[n+1];
        // Initialize 'commonCount' to 0. This variable will keep track of the number of elements
        // that have appeared in both A and B up to the current prefix index 'i'.
        int commonCount = 0;
        // Iterate through the arrays from the first element (index 0) up to the last element (index n-1).
        for(int i = 0; i < n; i++){
            // Increment the frequency of the current element from array A.
            freq[A[i]]++;
            // Check if the frequency of A[i] has just become 2.
            // If it's 2, it means this number has now appeared in both A and B (since we just added it to A,
            // and if it was already present in B, its count would be 1, making it 2 now).
            if(freq[A[i]] == 2) {
                // If it's a new common element, increment the commonCount.
                commonCount++;
            }
            // Increment the frequency of the current element from array B.
            freq[B[i]]++;
            // Check if the frequency of B[i] has just become 2.
            // We only do this check if A[i] is NOT equal to B[i].
            // This is to avoid double-counting if A[i] and B[i] are the same number.
            // If A[i] == B[i], the first 'if' statement would have already handled the commonCount increment
            // if the number's frequency became 2.
            if(A[i] != B[i]) {
                if(freq[B[i]] == 2) {
                    // If B[i] is a new common element (and different from A[i]), increment commonCount.
                    commonCount++;
                }
            }
            // Store the current 'commonCount' in the result array at index 'i'.
            // This represents the number of common elements in A[0...i] and B[0...i].
            ans[i] = commonCount;
        }
        // Return the final result array.
        return ans;
    }
}
```

## Interview Tips
1.  **Clarify Constraints:** Ask about the range of numbers in the arrays. This confirms if a frequency array of size `n+1` is appropriate or if a `HashMap` is needed for larger/sparse ranges.
2.  **Explain the Frequency Logic:** Clearly articulate why incrementing the `commonCount` only when a frequency hits 2 is the correct approach. Emphasize avoiding double-counting.
3.  **Walk Through an Example:** Use a small example (like the one provided in the comments) to trace the execution of your algorithm step-by-step, showing how `freq` and `commonCount` change.
4.  **Discuss Alternatives (Briefly):** Mention that a `HashMap` could be used instead of an array for `freq` if the number range was very large or sparse, but explain why the array is more efficient here.

## Revision Checklist
- [ ] Understand the problem statement: count common elements in prefixes.
- [ ] Recognize the need for efficient tracking of seen elements.
- [ ] Implement frequency counting using an array or hash map.
- [ ] Handle the case where `A[i] == B[i]` to avoid double counting.
- [ ] Ensure correct initialization of `freq` and `commonCount`.
- [ ] Verify loop bounds and array indexing.
- [ ] Analyze time and space complexity.

## Similar Problems
*   128. Longest Consecutive Sequence
*   242. Valid Anagram
*   387. First Unique Character in a String
*   202. Happy Number
*   219. Contains Duplicate II

## Tags
`Array` `Hash Map`
