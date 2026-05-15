# H Index

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Sorting` `Counting Sort`  
**Time:** O(N log N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);        
        for(int i=n-1;i>=0;i--) if(n-i > citations[i]) return n-i-1;
        return n;
    }
}
```

---

---
## Quick Revision
Given an array of publication citations, find the highest h-index value.
The h-index is solved by sorting citations and iterating from the end to find the largest h such that h papers have at least h citations.

## Intuition
The h-index definition is key: "a scientist has an index h if h of their N papers have at least h citations each, and the other N-h papers have no more than h citations."
If we sort the citations in ascending order, say `[0, 1, 3, 5, 6]`, and we are looking for an h-index.
Let's consider the papers from the most cited to the least cited.
If the most cited paper has 6 citations, it *could* contribute to an h-index of 1.
If the second most cited paper has 5 citations, it *could* contribute to an h-index of 2 (if the first also had at least 2).
If the third most cited paper has 3 citations, it *could* contribute to an h-index of 3 (if the first two also had at least 3).
If the fourth most cited paper has 1 citation, it *cannot* contribute to an h-index of 4, because we only have 4 papers, and this one only has 1 citation.
This suggests iterating from the end of the sorted array. If we are at index `i` (from the end, 0-indexed), we have `n - i` papers considered so far (from most cited to this one). If the citation count at this index `citations[i]` is *less than* the number of papers we've considered (`n - i`), then we cannot achieve an h-index of `n - i` or higher. The maximum possible h-index must be `n - i - 1`.
If we iterate through all papers and the condition `n - i > citations[i]` is never met, it means all `n` papers have at least `n` citations, so the h-index is `n`.

## Algorithm
1. Sort the `citations` array in ascending order.
2. Get the length of the `citations` array, let's call it `n`.
3. Iterate through the sorted `citations` array from right to left (from index `n-1` down to `0`).
4. For each index `i`, calculate the number of papers considered so far from the end: `num_papers = n - i`.
5. Check if the citation count at the current index `citations[i]` is less than `num_papers`.
6. If `citations[i] < num_papers`, it means we cannot achieve an h-index of `num_papers` or higher. The highest possible h-index is `num_papers - 1`. Return `num_papers - 1`.
7. If the loop completes without returning, it means all `n` papers have at least `n` citations. Return `n`.

## Concept to Remember
*   Sorting: Efficiently ordering data to enable linear scans for specific properties.
*   Greedy Approach: Making locally optimal choices (checking from most cited) to achieve a global optimum (the h-index).
*   Array Traversal: Understanding how to iterate through arrays, especially in reverse.

## Common Mistakes
*   Incorrectly calculating the number of papers considered from the end. Forgetting that `n-i` represents the count.
*   Off-by-one errors when returning the h-index, especially when the condition `n-i > citations[i]` is met.
*   Not handling the edge case where all papers have enough citations for the h-index to be `n`.
*   Assuming the input array is already sorted.

## Complexity Analysis
- Time: O(N log N) - due to the sorting step. The subsequent loop is O(N).
- Space: O(1) or O(log N) - depending on the sorting algorithm used by `Arrays.sort()`. In Java, it's typically O(log N) for primitive types due to quicksort/mergesort variations. If an in-place sort like heapsort were guaranteed, it would be O(1).

## Commented Code
```java
class Solution {
    public int hIndex(int[] citations) {
        // Get the total number of publications (citations).
        int n = citations.length;
        
        // Sort the citations array in ascending order. This is crucial for our approach.
        Arrays.sort(citations);        
        
        // Iterate through the sorted citations array from right to left (most cited to least cited).
        // 'i' represents the index of the current paper being considered.
        for(int i = n - 1; i >= 0; i--) {
            // Calculate the number of papers considered so far from the end.
            // If i is n-1, we consider 1 paper. If i is n-2, we consider 2 papers, and so on.
            // So, n-i gives us the count of papers from the most cited up to the current one.
            int numPapersConsidered = n - i;
            
            // Check if the citation count of the current paper (citations[i]) is less than
            // the number of papers we are considering (numPapersConsidered).
            // If citations[i] < numPapersConsidered, it means that we have 'numPapersConsidered' papers,
            // but the current paper (which is the least cited among these 'numPapersConsidered' papers)
            // has fewer citations than 'numPapersConsidered'.
            // This implies that we cannot achieve an h-index of 'numPapersConsidered' or higher.
            // Therefore, the maximum possible h-index must be one less than 'numPapersConsidered'.
            if (numPapersConsidered > citations[i]) {
                // Return numPapersConsidered - 1, which is the highest h-index possible.
                return numPapersConsidered - 1;
            }
        }
        
        // If the loop completes without returning, it means that for all 'i',
        // citations[i] >= (n - i). This implies that all 'n' papers have at least 'n' citations.
        // In this scenario, the h-index is equal to the total number of papers.
        return n;
    }
}
```

## Interview Tips
*   Clearly explain the definition of h-index and how sorting helps.
*   Walk through an example manually to demonstrate your understanding.
*   Be prepared to discuss the time and space complexity of your solution.
*   Consider edge cases like an empty array or an array with all zeros.

## Revision Checklist
- [ ] Understand the h-index definition.
- [ ] Recognize the benefit of sorting citations.
- [ ] Implement the reverse iteration correctly.
- [ ] Calculate `n - i` as the number of papers.
- [ ] Handle the condition `n - i > citations[i]`.
- [ ] Return `n - i - 1` when the condition is met.
- [ ] Return `n` if the loop completes.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [Majority Element](https://leetcode.com/problems/majority-element/)
*   [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)

## Tags
`Array` `Sorting` `Greedy`
