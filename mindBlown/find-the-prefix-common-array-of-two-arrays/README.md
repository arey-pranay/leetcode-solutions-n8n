# Find The Prefix Common Array Of Two Arrays

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Bit Manipulation`  
**Time:** O(n)  
**Space:** O(n)

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
Given two arrays A and B of the same length, find an array where each element is the count of common elements in the prefixes of A and B up to that index.
This is solved by iterating through the arrays, maintaining counts of elements seen so far, and updating the common count at each prefix.

## Intuition
The core idea is to efficiently track which numbers have appeared in *both* prefixes of A and B as we extend them. For each index `i`, we need to know how many numbers from `A[0...i]` are also present in `B[0...i]`. A naive approach would be to compare prefixes at each step, which is slow. We can optimize this by using a frequency map (or an array if the numbers are within a known range) to keep track of elements encountered. When an element appears for the second time (meaning it has now appeared in both arrays' prefixes), we increment our common count.

## Algorithm
1. Initialize an integer array `ans` of the same length as `A` to store the results.
2. Initialize an integer array `freq` of size `n+1` (assuming elements are 1-indexed up to `n`) to store the frequency of each number encountered across both arrays. Initialize all elements to 0.
3. Initialize an integer variable `commonCount` to 0. This will track the number of common elements found so far.
4. Iterate from `i = 0` to `n-1` (where `n` is the length of `A`):
    a. Increment the frequency of `A[i]` in the `freq` array: `freq[A[i]]++`.
    b. If `freq[A[i]]` becomes 2, it means `A[i]` has now appeared in both prefixes (once in A, and it must have appeared in B at some point for its count to reach 2). Increment `commonCount`.
    c. Increment the frequency of `B[i]` in the `freq` array: `freq[B[i]]++`.
    d. If `freq[B[i]]` becomes 2, it means `B[i]` has now appeared in both prefixes. Increment `commonCount`.
    e. Store the current `commonCount` in `ans[i]`.
5. Return the `ans` array.

## Concept to Remember
*   Prefix Sums/Arrays: Understanding how to process data incrementally based on previous results.
*   Frequency Counting: Using auxiliary data structures (like hash maps or arrays) to efficiently track occurrences of elements.
*   Two Pointers/Simultaneous Iteration: Processing two arrays in lockstep to compare corresponding elements or prefixes.

## Common Mistakes
*   Incorrectly handling the `commonCount` update: Only incrementing `commonCount` when an element's frequency *becomes* 2, not every time it's encountered.
*   Off-by-one errors in array indexing or frequency array size.
*   Not considering that an element might appear multiple times within a single array's prefix before appearing in the other. The `freq` array correctly handles this by only incrementing `commonCount` when the count *reaches* 2.
*   Using a less efficient method like sorting substrings or comparing full prefixes at each step.

## Complexity Analysis
- Time: O(n) - We iterate through the arrays once. Array lookups and updates are O(1).
- Space: O(n) - We use an auxiliary `freq` array of size `n+1` and an `ans` array of size `n`.

## Commented Code
```java
class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        // Get the length of the input arrays. Both arrays are guaranteed to have the same length.
        int n = A.length;
        // Initialize the result array 'ans' with the same length as A and B.
        // This array will store the count of common elements for each prefix.
        int ans[] = new int[n];
        // Initialize a frequency array 'freq'. The size is n+1 because the problem statement implies
        // elements are positive integers up to n. This array will store how many times each number
        // has appeared across both A and B's prefixes processed so far.
        int[] freq = new int[n+1];
        // Initialize 'commonCount' to 0. This variable will keep track of the total number of
        // elements that have appeared in *both* prefixes A[0...i] and B[0...i] at the current index i.
        int commonCount = 0;
        // Iterate through the arrays from the first element (index 0) up to the last element (index n-1).
        for(int i = 0; i < n; i++){
            // Process the element A[i] from the first array.
            // Increment its count in the frequency array.
            freq[A[i]]++;
            // Check if the count of A[i] has just become 2.
            // If it was 0 and now 1, it means A[i] appeared in A's prefix.
            // If it was 1 and now 2, it means A[i] has now appeared in B's prefix as well (since we increment freq[B[i]] later).
            // So, if freq[A[i]] is now 2, it signifies a new common element.
            if(freq[A[i]] == 2) {
                commonCount++; // Increment the total count of common elements.
            }

            // Process the element B[i] from the second array.
            // Increment its count in the frequency array.
            freq[B[i]]++;
            // Check if the count of B[i] has just become 2.
            // Similar logic as above: if freq[B[i]] is now 2, it means B[i] has now appeared in A's prefix as well.
            // This condition ensures we only count an element as common when it has appeared in *both* arrays' prefixes.
            if(freq[B[i]] == 2) {
                commonCount++; // Increment the total count of common elements.
            }
            
            // Store the current total count of common elements for the prefix up to index i.
            ans[i] = commonCount;
        }
        // Return the array containing the prefix common counts.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain your frequency counting approach and why it's more efficient than brute-force comparison of prefixes.
*   Walk through an example manually to demonstrate how `commonCount` is updated.
*   Be prepared to discuss the constraints on the input array elements (e.g., if they can be negative or very large, a hash map might be better than an array for `freq`).
*   If asked about alternative solutions, mention the sorting-substring approach and explain its higher time complexity.

## Revision Checklist
- [ ] Understand the problem: count common elements in prefixes.
- [ ] Identify the need for efficient tracking of seen elements.
- [ ] Implement frequency counting using an array or hash map.
- [ ] Correctly update the `commonCount` only when an element's frequency reaches 2.
- [ ] Handle edge cases and array indexing.
- [ ] Analyze time and space complexity.

## Similar Problems
*   128. Longest Consecutive Sequence
*   239. Sliding Window Maximum
*   3. Longest Substring Without Repeating Characters
*   219. Contains Duplicate II

## Tags
`Array` `Hash Map`
