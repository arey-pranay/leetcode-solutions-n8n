# Permutations

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Backtracking`  
**Time:** O(N * N!)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        func(new ArrayList<>(),nums.length,nums);
        return ans;
    }
    public void func(List<Integer> temp , int n , int[] nums){
        if(temp.size()==n){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i=0; i<n; i++){ 
            if(temp.contains(nums[i]))continue;
            temp.add(nums[i]);
            func(temp,n,nums);
            temp.remove(temp.size()-1);
        }
    }
}

// class Solution {
//     List<List<Integer>> ans;
//     public List<List<Integer>> permute(int[] nums) {
//         ans = new ArrayList<>();
//         func(new LinkedHashSet<>(),nums.length,nums);
//         return ans;
//     }
//     public void func(LinkedHashSet<Integer> temp , int n , int[] nums){
//         if(temp.size()==n){
//             ans.add(new ArrayList<>(temp));
//             return;
//         }
//         for(int i=0; i<n; i++){ 
//             if(temp.contains(nums[i]))continue;
//             temp.add(nums[i]);
//             func(temp,n,nums);
//             temp.remove(nums[i]);
//         }
//     }
// }
```

---

---
## Quick Revision
Given a collection of distinct integers, return all possible permutations.
This problem is solved using a recursive backtracking approach.

## Intuition
The core idea is to build permutations step by step. At each step, we try to add an unused number from the input array to our current permutation. If the current permutation is complete (its size equals the input array's size), we've found a valid permutation. If not, we recursively explore further possibilities by adding another unused number. This process naturally explores all branches of possibilities, ensuring we find every permutation. The "aha moment" is realizing that this systematic exploration, where we add an element, recurse, and then *backtrack* (remove the element to try other options), covers all combinations.

## Algorithm
1. Initialize an empty list `ans` to store all generated permutations.
2. Define a recursive helper function `func` that takes:
    - `temp`: A list representing the current permutation being built.
    - `n`: The total number of elements in the input array `nums`.
    - `nums`: The input array of distinct integers.
3. **Base Case:** If the size of `temp` equals `n`, it means a complete permutation has been formed. Add a *copy* of `temp` to `ans` and return.
4. **Recursive Step:** Iterate through each number `nums[i]` in the input array `nums`.
    a. **Check for Duplicates:** If `nums[i]` is already present in `temp`, skip it to avoid duplicate elements within a single permutation.
    b. **Explore:** If `nums[i]` is not in `temp`, add `nums[i]` to `temp`.
    c. **Recurse:** Call `func` recursively with the updated `temp`.
    d. **Backtrack:** After the recursive call returns, remove the last added element (`nums[i]`) from `temp`. This is crucial for exploring other possibilities.
5. Call the `func` initially with an empty `temp` list.
6. Return `ans`.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Recursion:** A method of solving a problem where the solution depends on solutions to smaller instances of the same problem.
*   **State Management:** Carefully managing the state (e.g., the current permutation `temp`) during recursion and backtracking is vital.

## Common Mistakes
*   **Not copying `temp` when adding to `ans`:** If `temp` is added directly, subsequent modifications to `temp` during backtracking will alter the permutations already added to `ans`.
*   **Incorrectly handling duplicates:** Forgetting to check if an element is already in the current permutation can lead to invalid permutations with repeated numbers.
*   **Missing the backtracking step:** Failing to remove the last added element from `temp` after the recursive call will prevent exploration of alternative branches.
*   **Using a mutable data structure for `temp` without proper copying:** If `temp` is not copied correctly, the original `temp` might be modified unexpectedly.

## Complexity Analysis
*   **Time:** O(N * N!) - For each of the N! permutations, we iterate through N elements to build it, and the `temp.contains()` operation takes O(N) in the worst case for a List. If a Set is used for `temp`, `contains` is O(1) on average, leading to O(N * N!).
*   **Space:** O(N) - This is due to the recursion depth (which can go up to N) and the space used by the `temp` list to store the current permutation. The `ans` list can store N! permutations, each of size N, leading to O(N * N!) space for the output, but typically, space complexity refers to auxiliary space.

## Commented Code
```java
class Solution {
    // List to store all the generated permutations.
    List<List<Integer>> ans;

    // Main function to initiate the permutation generation.
    public List<List<Integer>> permute(int[] nums) {
        // Initialize the answer list.
        ans = new ArrayList<>();
        // Start the recursive helper function with an empty temporary list,
        // the total number of elements, and the input array.
        func(new ArrayList<>(), nums.length, nums);
        // Return the list of all permutations.
        return ans;
    }

    // Recursive helper function to generate permutations.
    public void func(List<Integer> temp, int n, int[] nums) {
        // Base case: If the temporary list has reached the size of the input array,
        // it means a complete permutation has been formed.
        if (temp.size() == n) {
            // Add a new ArrayList containing the elements of temp to the answer list.
            // A new ArrayList is created to ensure that future modifications to temp
            // do not affect the permutation already added to ans.
            ans.add(new ArrayList<>(temp));
            // Return from this recursive call as a permutation is complete.
            return;
        }

        // Iterate through each number in the input array.
        for (int i = 0; i < n; i++) {
            // Check if the current number nums[i] is already present in the temporary list.
            // This prevents duplicate numbers within a single permutation.
            if (temp.contains(nums[i])) {
                // If the number is already present, skip it and continue to the next iteration.
                continue;
            }
            // If the number is not present, add it to the temporary list.
            temp.add(nums[i]);
            // Recursively call the func to build the rest of the permutation.
            func(temp, n, nums);
            // Backtrack: Remove the last added element from the temporary list.
            // This is crucial to explore other possibilities by trying different numbers
            // at the current position.
            temp.remove(temp.size() - 1);
        }
    }
}
```

## Interview Tips
*   Clearly explain the backtracking approach: Emphasize the "choose, explore, unchoose" pattern.
*   Walk through a small example (e.g., `nums = [1, 2]`) on a whiteboard to demonstrate the recursion and backtracking steps.
*   Discuss the importance of copying the `temp` list before adding it to the result.
*   Be prepared to discuss the time and space complexity, and how using a `HashSet` for `temp` could optimize the `contains` check.

## Revision Checklist
- [ ] Understand the problem statement: generating all distinct permutations.
- [ ] Grasp the backtracking concept.
- [ ] Implement the base case correctly.
- [ ] Implement the recursive step with element selection.
- [ ] Ensure duplicate elements are not added within a permutation.
- [ ] Implement the backtracking step (removing the element).
- [ ] Correctly copy the temporary permutation before adding to the result.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Permutations II (LeetCode 47)
*   Combinations (LeetCode 77)
*   Subsets (LeetCode 78)
*   Letter Combinations of a Phone Number (LeetCode 17)

## Tags
`Array` `Backtracking` `Recursion`
