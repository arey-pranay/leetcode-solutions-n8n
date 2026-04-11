# Combination Sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Backtracking`  
**Time:** O(N * 2^T)  
**Space:** O(T)

---

## Solution (java)

```java
class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        Arrays.sort(candidates);
        func(candidates,target,0,new ArrayList<>());
        return ans;
    }
    public void func(int[] arr, int needed, int index, List<Integer> temp){
        if(needed < 0) return;
        if(needed == 0){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i=index;i<arr.length;i++){
            temp.add(arr[i]);
            func(arr,needed-arr[i],i,temp);
            temp.remove(temp.size()-1);       
        }
        return;
    }
}
```

---

---
## Quick Revision
Find all unique combinations of numbers from a given array that sum up to a target value.
This is solved using a recursive backtracking approach.

## Intuition
The core idea is to explore all possible combinations by trying to include each candidate number. If including a number leads to a sum greater than the target, we backtrack. If it leads to a sum equal to the target, we've found a valid combination. To avoid duplicate combinations and ensure uniqueness, we process candidates in a sorted order and only consider candidates from the current index onwards in recursive calls.

## Algorithm
1. Initialize an empty list `ans` to store all valid combinations.
2. Sort the `candidates` array. This helps in avoiding duplicate combinations and allows for an optimization where we only consider elements from the current index onwards.
3. Define a recursive helper function `func` that takes the `candidates` array, the remaining `needed` sum, the current `index` to start considering candidates from, and a temporary list `temp` to build the current combination.
4. **Base Cases for `func`:**
    a. If `needed` becomes negative, it means the current path has exceeded the target sum, so return.
    b. If `needed` is exactly 0, it means the current `temp` list forms a valid combination. Add a copy of `temp` to `ans` and return.
5. **Recursive Step for `func`:**
    a. Iterate through the `candidates` array starting from the current `index` (`i` from `index` to `arr.length - 1`).
    b. For each candidate `arr[i]`:
        i. Add `arr[i]` to the `temp` list.
        ii. Recursively call `func` with the updated `needed` sum (`needed - arr[i]`), the *same* `index` (`i`) (because we can reuse the same candidate multiple times), and the modified `temp` list.
        iii. **Backtrack:** Remove the last added element (`arr[i]`) from `temp` to explore other possibilities.
6. After the initial call to `func` completes, return the `ans` list.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Recursion:** A method of solving a problem where the solution depends on smaller instances of the same problem.
*   **Handling Duplicates:** Sorting the input and using the `index` parameter in the recursive call to ensure that we only consider elements from the current position onwards prevents generating duplicate combinations.

## Common Mistakes
*   Not sorting the `candidates` array, leading to duplicate combinations in the output.
*   Incorrectly updating the `index` in the recursive call. If the `index` is incremented (`i+1`) instead of staying the same (`i`), it prevents reusing the same candidate multiple times.
*   Forgetting to backtrack (i.e., not removing the element from `temp` after the recursive call), which corrupts the `temp` list for subsequent explorations.
*   Not creating a new `ArrayList` when adding `temp` to `ans`. This results in `ans` containing references to the same `temp` list, which will be empty or incorrect by the end of the execution.

## Complexity Analysis
*   **Time:** O(N * 2^T), where N is the number of candidates and T is the target. In the worst case, we might explore all possible combinations. The `2^T` comes from the fact that for each unit of the target, we have roughly two choices (include the current number or not, though this is a simplification). The `N` factor comes from the loop within each recursive call and potentially copying the `temp` list.
*   **Space:** O(T) for the recursion depth and the `temp` list, and O(N * 2^T) in the worst case for storing the `ans` list. The dominant factor is usually the output storage.

## Commented Code
```java
class Solution {
    // This list will store all the valid combinations found.
    List<List<Integer>> ans;

    // The main function that initiates the combination sum process.
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Initialize the answer list.
        ans = new ArrayList<>();
        // Sort the candidates array. This is crucial for handling duplicates and for the algorithm's logic.
        Arrays.sort(candidates);
        // Start the recursive backtracking process.
        // candidates: the array of numbers to choose from.
        // target: the remaining sum we need to achieve.
        // 0: the starting index in the candidates array to consider.
        // new ArrayList<>(): an empty list to build the current combination.
        func(candidates, target, 0, new ArrayList<>());
        // Return the list of all valid combinations.
        return ans;
    }

    // The recursive helper function to find combinations.
    public void func(int[] arr, int needed, int index, List<Integer> temp) {
        // Base case 1: If the needed sum becomes negative, this path is invalid.
        if (needed < 0) {
            // Stop exploring this path.
            return;
        }
        // Base case 2: If the needed sum is exactly 0, we have found a valid combination.
        if (needed == 0) {
            // Add a *copy* of the current temporary list to the answer list.
            // A copy is essential because 'temp' will be modified later during backtracking.
            ans.add(new ArrayList<>(temp));
            // Stop exploring this path as we've reached the target.
            return;
        }

        // Recursive step: Iterate through the candidates array starting from the current index.
        for (int i = index; i < arr.length; i++) {
            // Include the current candidate 'arr[i]' in the temporary combination.
            temp.add(arr[i]);
            // Recursively call 'func' to find combinations for the remaining sum.
            // arr: the candidates array.
            // needed - arr[i]: the new remaining sum needed.
            // i: the current index. We pass 'i' (not 'i+1') because we can reuse the same candidate multiple times.
            // temp: the updated temporary list with arr[i] added.
            func(arr, needed - arr[i], i, temp);
            // Backtrack: Remove the last added candidate from the temporary list.
            // This allows us to explore other combinations by not including arr[i] in subsequent steps of this branch.
            temp.remove(temp.size() - 1);
        }
        // The function implicitly returns after the loop finishes.
        return;
    }
}
```

## Interview Tips
*   Clearly explain the backtracking approach and why sorting is important.
*   Walk through a small example (e.g., `candidates = [2,3,6,7]`, `target = 7`) to demonstrate how the recursion and backtracking work.
*   Pay close attention to the base cases and the recursive step, especially how the `index` is handled to allow for repeated use of candidates.
*   Be prepared to discuss the time and space complexity, and justify your reasoning.

## Revision Checklist
- [ ] Understand the problem statement: find all unique combinations summing to target.
- [ ] Recognize the need for a recursive/backtracking approach.
- [ ] Implement sorting of candidates to handle duplicates.
- [ ] Define clear base cases for the recursion (target met, target exceeded).
- [ ] Implement the recursive step: explore including a candidate.
- [ ] Ensure correct handling of the `index` for reusing candidates.
- [ ] Implement backtracking by removing the last added element.
- [ ] Correctly copy the temporary list when adding to the final answer.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Combination Sum II
*   Combination Sum III
*   Subsets
*   Permutations
*   Factorial Numbers (similar recursive structure)

## Tags
`Array` `Backtracking` `Recursion`
