# Sum Of Gcd Of Formed Pairs

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Two Pointers` `Sorting` `Simulation` `Number Theory`  
**Time:** O(N log N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    private int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }

    public long gcdSum(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(max, A[i]);
            A[i] = gcd(A[i], max);
        }

        Arrays.sort(A);

        long res = 0;        
        for (int i = 0, j = A.length - 1; i < j; i++, j--)
            res += gcd(A[i], A[j]);

        return res;
    }
}
```

---

---
## Quick Revision
The problem asks to calculate the sum of GCDs of all possible pairs formed by elements in an array.
The solution involves a clever transformation of array elements and then pairing the smallest with the largest.

## Intuition
The core idea is to realize that the GCD of a number `x` with any number `y` will always be less than or equal to `x`. If we can somehow transform the array such that each element `A[i]` becomes the GCD of itself and all preceding elements (or a relevant subset), we can simplify the pairing process. The observation that `gcd(a, b, c) = gcd(gcd(a, b), c)` is key. If we iterate through the array and maintain the maximum element seen so far, say `max_so_far`, then `gcd(A[i], max_so_far)` represents the GCD of `A[i]` with all elements that contributed to `max_so_far` in a way that preserves the GCD property. After this transformation, sorting the array and pairing the smallest with the largest element allows us to efficiently sum up the GCDs. The intuition is that `gcd(smallest, largest)` will be the largest possible GCD for any pair involving the smallest element, and by extension, this strategy covers all necessary GCDs in a structured way.

## Algorithm
1. **Precompute GCDs with Maximums:** Iterate through the input array `A`. Maintain a variable `max_val` that stores the maximum element encountered so far. For each element `A[i]`, update it to `gcd(A[i], max_val)`. This step effectively transforms each element `A[i]` into the GCD of itself and all elements that were greater than or equal to it and appeared before it in the original array.
2. **Sort the Transformed Array:** Sort the modified array `A` in ascending order.
3. **Pair and Sum GCDs:** Initialize a result variable `res` to 0. Use two pointers, `i` starting from the beginning of the sorted array (index 0) and `j` starting from the end of the sorted array (index `A.length - 1`).
4. **Iterate and Calculate:** While `i < j`:
    a. Calculate the GCD of `A[i]` and `A[j]`.
    b. Add this GCD to `res`.
    c. Increment `i` and decrement `j`.
5. **Return Result:** Return the final sum `res`.

## Concept to Remember
*   **Greatest Common Divisor (GCD):** Understanding the Euclidean algorithm for calculating GCD is fundamental.
*   **Properties of GCD:** `gcd(a, b, c) = gcd(gcd(a, b), c)` and `gcd(a, b) <= min(a, b)`.
*   **Two-Pointer Technique:** Efficiently iterating through a sorted array from both ends.
*   **Greedy Approach:** The strategy of pairing the smallest with the largest after transformation is a form of greedy selection.

## Common Mistakes
*   **Incorrect GCD Calculation:** Implementing the GCD function incorrectly or not handling the base case `b == 0` properly.
*   **Modifying Array In-Place Incorrectly:** Not understanding that `A[i] = gcd(A[i], max)` relies on `max` being the maximum of *original* preceding elements, not transformed ones. The provided solution correctly uses `max` as the running maximum of original values.
*   **Inefficient Pairing:** Not sorting the array and attempting to pair elements in their original order, which would miss optimal GCD sums.
*   **Integer Overflow:** Forgetting to use a `long` for the result `res` if the sum of GCDs can exceed the maximum value of an `int`.

## Complexity Analysis
- Time: O(N log N) - The dominant factor is sorting the array. The initial pass to compute GCDs takes O(N * log(max_val)) where max_val is the maximum element in the array, due to the GCD computation. However, if we consider the GCD computation as roughly constant time for typical integer sizes, or bounded by log(max_val), the overall time complexity is dominated by sorting.
- Space: O(1) - If we consider the space used by the sorting algorithm in-place (e.g., quicksort or heapsort), or O(log N) to O(N) depending on the specific sorting implementation's auxiliary space. The provided solution modifies the array in-place.

## Commented Code
```java
class Solution {
    // Helper function to compute the Greatest Common Divisor (GCD) of two numbers using the Euclidean algorithm.
    private int gcd(int a, int b) {
        // Base case: if b is 0, then a is the GCD.
        return b == 0 ? a : gcd(b, a % b);
    }

    // Main function to calculate the sum of GCDs of formed pairs.
    public long gcdSum(int[] A) {
        // Initialize max to 0 to keep track of the maximum element encountered so far.
        int max = 0;
        // Iterate through the array to transform each element.
        for (int i = 0; i < A.length; i++) {
            // Update max with the current element if it's larger.
            max = Math.max(max, A[i]);
            // Transform A[i] to be the GCD of its original value and the maximum value seen up to this point.
            // This step is crucial: A[i] becomes gcd(original_A[i], max_of_preceding_elements).
            A[i] = gcd(A[i], max);
        }

        // Sort the transformed array in ascending order.
        // This is necessary for the two-pointer approach to work correctly.
        Arrays.sort(A);

        // Initialize the result variable to 0. Use long to prevent potential integer overflow.
        long res = 0;
        // Initialize two pointers: i from the start and j from the end of the sorted array.
        for (int i = 0, j = A.length - 1; i < j; i++, j--) {
            // Calculate the GCD of the smallest remaining element (A[i]) and the largest remaining element (A[j]).
            // Add this GCD to the total result.
            res += gcd(A[i], A[j]);
        }

        // Return the total sum of GCDs.
        return res;
    }
}
```

## Interview Tips
*   **Explain the Transformation:** Clearly articulate why transforming `A[i]` to `gcd(A[i], max_so_far)` is beneficial and how it relates to the properties of GCD.
*   **Justify Sorting:** Explain why sorting is necessary for the two-pointer approach to guarantee that we are pairing elements in a way that maximizes the sum of GCDs efficiently.
*   **Handle Edge Cases:** Discuss what happens with empty arrays or arrays with a single element (though constraints might prevent this). Mention the use of `long` for the result to avoid overflow.
*   **Walk Through Example:** Use a small example array to demonstrate the transformation step and the two-pointer pairing process.

## Revision Checklist
- [ ] Understand the problem statement and its constraints.
- [ ] Implement the Euclidean algorithm for GCD correctly.
- [ ] Grasp the intuition behind transforming array elements using `gcd(A[i], max_so_far)`.
- [ ] Implement the sorting step.
- [ ] Implement the two-pointer approach for summing GCDs.
- [ ] Ensure the result variable is of type `long`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   GCD of Array
*   Greatest Common Divisor Traversal
*   Sum of Distances in Tree (conceptually related to aggregating information)

## Tags
`Array` `Math` `Greedy` `Sorting` `Two Pointers` `Euclidean Algorithm`
