# Subsets

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Backtracking` `Bit Manipulation`  
**Time:** O(N * 2^N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums){
        func(nums,0,new ArrayList<>());
        return ans;
    }
    public void func(int[] nums, int i, List<Integer> curr){           
        if(i==nums.length) {ans.add(new ArrayList<>(curr)); return;}
        func(nums,i+1,curr);
        curr.add(nums[i]);
        func(nums,i+1,curr);
        curr.remove(curr.size()-1);
    }
}
```

---

---
## Quick Revision
Given a set of distinct integers, return all possible subsets (the power set).
This is solved using recursion with backtracking, exploring choices of including or excluding each element.

## Intuition
For each element in the input array, we have two choices: either include it in the current subset or exclude it. This binary choice for every element naturally leads to a tree-like exploration. At each step of the recursion, we decide for the current element `nums[i]`. If we decide to include `nums[i]`, we add it to our temporary subset and proceed to the next element. If we decide to exclude `nums[i]`, we simply move to the next element without adding it. When we've considered all elements (reached the end of the array), the current temporary subset is a valid subset, and we add it to our final result. The backtracking step (removing the element after exploring the "include" path) is crucial to explore other possibilities.

## Algorithm
1. Initialize an empty list of lists `ans` to store all subsets.
2. Define a recursive helper function `func(nums, index, current_subset)`.
3. **Base Case:** If `index` reaches the end of the `nums` array (`index == nums.length`), it means we have made a decision for every element. Add a *copy* of `current_subset` to `ans` and return.
4. **Recursive Step (Exclude):** Make a recursive call to `func` for the next element (`index + 1`) without adding `nums[index]` to `current_subset`. This represents the choice of *not* including the current element.
5. **Recursive Step (Include):**
    a. Add `nums[index]` to `current_subset`.
    b. Make a recursive call to `func` for the next element (`index + 1`) with the updated `current_subset`. This represents the choice of *including* the current element.
    c. **Backtrack:** Remove `nums[index]` from `current_subset`. This is essential to undo the change made in step 5a so that the `current_subset` is in the correct state for other branches of the recursion.
6. Call the `func` initially with `nums`, starting index `0`, and an empty `ArrayList` for `current_subset`.
7. Return `ans`.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems.
*   **Backtracking:** A general algorithmic technique for finding all (or some) solutions to computational problems, notably constraint satisfaction problems, that incrementally builds candidates to the solutions, and abandons a candidate ("backtracks") as soon as it determines that the candidate cannot possibly be completed to a valid solution.
*   **Power Set:** The set of all subsets of a given set. For a set with `n` elements, the power set has `2^n` subsets.

## Common Mistakes
*   **Not copying the `current_subset`:** When adding `current_subset` to `ans` in the base case, if you add the list directly, all subsequent modifications to `current_subset` will affect the lists already added to `ans`. You must add a *new* `ArrayList` created from `current_subset`.
*   **Forgetting to backtrack:** If you don't remove the element after the "include" recursive call, the `current_subset` will retain elements from previous branches, leading to incorrect subsets.
*   **Incorrect base case:** Not properly identifying when all elements have been processed.
*   **Off-by-one errors in indexing:** Mismanaging the `index` parameter in recursive calls.

## Complexity Analysis
- Time: O(N * 2^N) - For each of the 2^N subsets, we might iterate through up to N elements to copy it into the result list. The recursion itself explores 2^N paths, and at each leaf, we do a copy operation.
- Space: O(N) - This is primarily due to the recursion depth, which can go up to N. The `current_subset` also takes O(N) space in the worst case. The output `ans` can take O(N * 2^N) space, but this is usually not counted towards auxiliary space complexity.

## Commented Code
```java
class Solution {
    // Initialize a list to store all the generated subsets.
    List<List<Integer>> ans = new ArrayList<>();

    // The main function that initiates the subset generation process.
    public List<List<Integer>> subsets(int[] nums){
        // Start the recursive helper function from index 0 with an empty current subset.
        func(nums,0,new ArrayList<>());
        // Return the list containing all subsets.
        return ans;
    }

    // Recursive helper function to generate subsets.
    // nums: the input array of distinct integers.
    // i: the current index we are considering in the nums array.
    // curr: the current subset being built.
    public void func(int[] nums, int i, List<Integer> curr){
        // Base case: If we have considered all elements in the array (i.e., reached the end).
        if(i==nums.length) {
            // Add a *copy* of the current subset to the answer list.
            // This is crucial: if we don't copy, future modifications to 'curr' will affect this added list.
            ans.add(new ArrayList<>(curr));
            // Stop this recursive branch.
            return;
        }

        // Recursive call 1: Exclude the current element nums[i].
        // We move to the next element (i+1) without adding nums[i] to the current subset.
        func(nums,i+1,curr);

        // Recursive call 2: Include the current element nums[i].
        // Add the current element to the current subset.
        curr.add(nums[i]);
        // Now, make a recursive call for the next element (i+1) with the updated current subset.
        func(nums,i+1,curr);
        // Backtrack: Remove the last added element (nums[i]) from the current subset.
        // This is essential to explore other possibilities where nums[i] is not included in subsequent subsets.
        curr.remove(curr.size()-1);
    }
}
```

## Interview Tips
*   **Explain the "Include/Exclude" choice:** Clearly articulate that for each element, you have two fundamental decisions: to include it or not. This is the core of the recursive logic.
*   **Emphasize the copy operation:** When asked about the base case, make sure to highlight why `new ArrayList<>(curr)` is necessary to avoid modifying already collected subsets.
*   **Trace an example:** Walk through a small input like `[1, 2]` to demonstrate how the recursion unfolds, showing the calls, additions, and removals. This helps the interviewer follow your logic.
*   **Discuss backtracking:** Explain that the `curr.remove()` step is the backtracking mechanism that allows exploration of different branches of the decision tree.

## Revision Checklist
- [ ] Understand the problem: generate all subsets (power set).
- [ ] Identify the core decision: include or exclude each element.
- [ ] Implement recursion with a base case for reaching the end of the array.
- [ ] Ensure a *copy* of the current subset is added to the results.
- [ ] Implement the backtracking step (removing the element).
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with examples.

## Similar Problems
*   Subsets II
*   Combinations
*   Permutations
*   Combination Sum

## Tags
`Array` `Backtracking` `Recursion` `Bit Manipulation`
