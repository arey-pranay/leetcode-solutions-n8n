# Permutations

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Backtracking`  
**Time:** O(n*n!)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] vis = new boolean[21];
        func(new ArrayList<Integer>(), nums, vis);
        return ans;
    }
    private void func(List<Integer> curr, int[] nums, boolean[] vis){
        if(curr.size()==nums.length){
            ans.add(new ArrayList<>(curr)); 
            return;
        }
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            if(vis[num+10]) continue;
            vis[num+10] = true; curr.add(num);
            func(curr,nums,vis);
            curr.remove(curr.size()-1); vis[num+10] = false;
        }
    }  
}
```

---

---

## Quick Revision
Generate all permutations of a given array of integers.

Solve it by using a recursive approach with backtracking to fill in the current permutation and explore all possibilities.

## Intuition
The "aha moment" comes when realizing that we can use a boolean array to keep track of which numbers have been used in the current permutation, thus avoiding duplicates. The recursive function will try to add each number from the input array into the current permutation until it's complete or no more numbers are available.

## Algorithm

1. Initialize an empty list to store all permutations and a boolean array to mark visited numbers.
2. Define a recursive helper function `func` that takes the current permutation, input array, and visited array as parameters.
3. In the `func` function:
	* If the size of the current permutation is equal to the length of the input array, add it to the result list.
	* Iterate through each number in the input array and check if it's been visited before (marked by `vis[num+10]`). If so, skip this iteration.
	* Mark the current number as visited and add it to the current permutation.
	* Recursively call `func` with the updated parameters.
	* Backtrack by removing the last added number from the current permutation and unmarking it as visited.

## Concept to Remember
• **Backtracking**: a technique used in recursive algorithms to try all possible solutions before deciding whether a solution is valid or not.
• **Recursive function**: a function that calls itself repeatedly until it reaches a base case or returns a solution.
• **Dynamic programming (implicit)**: while we don't use memoization, the problem can be solved with an approach similar to dynamic programming.

## Common Mistakes
• Failing to properly backtrack and remove used numbers from the current permutation.
• Not using a visited array to keep track of used numbers, resulting in duplicate permutations.
• Trying to optimize the solution by avoiding recursion altogether (not recommended for this problem).

## Complexity Analysis
- Time: O(n*n!) /  We have n! possible permutations, and each one takes O(n) time to generate. 
- Space: O(n) /  For each permutation, we need a list of size n. 

## Commented Code

```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        // Initialize visited array and current permutation
        boolean[] vis = new boolean[21];
        func(new ArrayList<>(), nums, vis);
        return ans;
    }
    
    private void func(List<Integer> curr, int[] nums, boolean[] vis){
        // If we've filled in a complete permutation, add it to the result list
        if(curr.size()==nums.length){
            ans.add(new ArrayList<>(curr)); 
            return;
        }
        
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            // Skip this iteration if the number has been used before
            if(vis[num+10]) continue;
            
            vis[num+10] = true; curr.add(num);
            func(curr,nums,vis);  // Recursively try to fill in more numbers
            
            curr.remove(curr.size()-1); 
            vis[num+10] = false;
        }
    }  
}
```

## Interview Tips
• Be prepared to explain the "aha moment" and how backtracking is used to solve this problem.
• Make sure you can implement the solution from scratch without referencing code.
• Highlight the importance of using a visited array to avoid duplicate permutations.

## Revision Checklist
- [ ] Implement recursive function with proper backtracking
- [ ] Use boolean array to mark visited numbers
- [ ] Test solution with different input arrays

## Similar Problems
* `46. Permutations`
* `60. Permutation Sequence`
* `77. Combinations`

## Tags
`Array` `Hash Map`
