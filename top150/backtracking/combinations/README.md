# Combinations

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Backtracking`  
**Time:** O(C(n, k)  
**Space:** O(k)

---

## Solution (java)

```java
class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        func(new ArrayList<>(),n,k,0);
        return ans;
    }
    public void func(List<Integer> temp , int n , int k  , int curr){
        if(temp.size()==k){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i=curr+1; i<=n; i++){        
            temp.add(i);
            func(temp,n,k,i);
            temp.remove(temp.size()-1);
        }
    }
}
```

---

---
## Quick Revision
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
This problem is solved using a recursive backtracking approach.

## Intuition
The core idea is to build combinations incrementally. At each step, we decide whether to include a number or not. If we decide to include a number, we add it to our current combination and then recursively explore further possibilities. If we decide not to include it, we move to the next number. This process naturally leads to a tree-like exploration where each path from the root to a leaf represents a valid combination. The "aha moment" comes when realizing that we can prune branches of this search tree early: if the current combination plus the remaining available numbers cannot possibly form a combination of size `k`, we stop exploring that path.

## Algorithm
1. Initialize an empty list `ans` to store all valid combinations.
2. Define a recursive helper function `func` that takes:
    - `temp`: The current combination being built (a list of integers).
    - `n`: The upper bound of the numbers to choose from.
    - `k`: The desired size of each combination.
    - `curr`: The starting number for the current level of recursion. This ensures we don't reuse numbers and maintain order.
3. Base Case: If the size of `temp` equals `k`, it means we have formed a valid combination. Add a copy of `temp` to `ans` and return.
4. Recursive Step: Iterate through numbers from `curr + 1` to `n`.
    - For each number `i`:
        - Add `i` to the `temp` list.
        - Recursively call `func` with the updated `temp`, `n`, `k`, and `i` (to ensure subsequent numbers are greater than `i`).
        - Backtrack: Remove `i` from `temp` to explore other possibilities.
5. The initial call to `func` will be with an empty `temp` list, `n`, `k`, and `0` as the starting `curr` value.
6. Return `ans`.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Recursion:** A method of solving a problem where the solution depends on solutions to smaller instances of the same problem.
*   **Combinatorics:** The mathematical study of counting, arrangement, and combination of objects. This problem directly relates to finding combinations (order doesn't matter).

## Common Mistakes
*   **Not copying the `temp` list:** When adding `temp` to `ans`, forgetting to create a `new ArrayList<>(temp)` will result in `ans` containing references to the same list, which will be modified during backtracking, leading to incorrect results.
*   **Incorrect starting point for recursion:** Not passing `i` as the `curr` parameter in the recursive call can lead to duplicate combinations or combinations with numbers out of order.
*   **Off-by-one errors in loop bounds:** Incorrectly setting the loop condition (e.g., `i <= n` vs. `i < n`) can lead to missing combinations or including invalid numbers.
*   **Not backtracking properly:** Failing to remove the last added element from `temp` after the recursive call returns.

## Complexity Analysis
- Time: O(C(n, k) * k) - The number of combinations is C(n, k). For each combination, we perform O(k) work (adding to list, copying list). The recursion tree can be larger, but the effective work is bounded by generating and copying valid combinations.
- Space: O(k) - This is for the recursion depth and the `temp` list, which stores at most `k` elements. The `ans` list can store up to O(C(n, k) * k) elements, but this is typically considered output space and not auxiliary space.

## Commented Code
```java
class Solution {
    // List to store all valid combinations found.
    List<List<Integer>> ans;

    // Main function to initiate the combination generation.
    public List<List<Integer>> combine(int n, int k) {
        // Initialize the answer list.
        ans = new ArrayList<>();
        // Start the recursive helper function.
        // temp: current combination being built (initially empty).
        // n: the upper limit of numbers to choose from.
        // k: the desired size of each combination.
        // curr: the starting number for the current recursive call (initially 0, so we start picking from 1).
        func(new ArrayList<>(), n, k, 0);
        // Return the list of all combinations.
        return ans;
    }

    // Recursive helper function to generate combinations.
    public void func(List<Integer> temp, int n, int k, int curr) {
        // Base case: If the current combination has reached the desired size k.
        if (temp.size() == k) {
            // Add a copy of the current combination to the answer list.
            // A new ArrayList is created to avoid modifying this combination later during backtracking.
            ans.add(new ArrayList<>(temp));
            // Stop exploring this path as a valid combination is found.
            return;
        }

        // Recursive step: Iterate through possible numbers to add to the current combination.
        // Start from curr + 1 to ensure numbers are in increasing order and no duplicates.
        // Iterate up to n.
        for (int i = curr + 1; i <= n; i++) {
            // Add the current number 'i' to the temporary combination.
            temp.add(i);
            // Recursively call func to build the rest of the combination.
            // Pass 'i' as the new 'curr' to ensure the next number chosen is greater than 'i'.
            func(temp, n, k, i);
            // Backtrack: Remove the last added number 'i' from the temporary combination.
            // This allows exploration of other possibilities by not including 'i' at this position.
            temp.remove(temp.size() - 1);
        }
    }
}
```

## Interview Tips
*   **Explain Backtracking Clearly:** Articulate the concept of "explore, then backtrack" and how it applies to building solutions incrementally.
*   **Trace an Example:** Walk through a small example (e.g., n=4, k=2) to demonstrate how the `temp` list and `ans` list evolve.
*   **Discuss Pruning (Implicitly):** While this specific code doesn't have explicit pruning for "not enough numbers left," mention that in similar problems, you might add a check like `if (n - i + 1 < k - temp.size()) break;` to optimize.
*   **Handle Edge Cases:** Be prepared to discuss what happens if `k=0`, `k=n`, or `k > n`.

## Revision Checklist
- [ ] Understand the problem statement: generate combinations of k numbers from 1 to n.
- [ ] Recognize backtracking as a suitable approach.
- [ ] Implement the recursive helper function correctly.
- [ ] Ensure the base case for `temp.size() == k` is handled.
- [ ] Implement the loop for choosing numbers from `curr + 1` to `n`.
- [ ] Correctly add and remove elements from the `temp` list (backtracking).
- [ ] Crucially, add a *copy* of `temp` to the result list.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Subsets
*   Permutations
*   Combination Sum
*   Letter Combinations of a Phone Number

## Tags
`Backtracking` `Recursion` `Array`
