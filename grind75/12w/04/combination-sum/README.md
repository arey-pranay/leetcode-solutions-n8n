# Combination Sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Backtracking`  
**Time:** O(N^(T/M+1)  
**Space:** O(T)

---

## Solution (java)

```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        func(candidates,target,0,new ArrayList<>());
        return ans;
    }
    public void func(int[] nums, int target, int index, List<Integer> curr){    
        if(target==0){ans.add(new ArrayList<>(curr)); return;}
        if(target < 0 || index == nums.length) return;

        curr.add(nums[index]);
        func(nums,target-nums[index],index,curr);
        curr.remove(curr.size()-1);
        
        func(nums,target,index+1,curr);
    }
}

```

---

---

## Quick Revision
The problem is to find all unique combinations in candidates where the candidate numbers sum up to a given target. This can be solved using backtracking and recursion.

## Intuition
The key insight here is that we can use a recursive approach with backtracking to explore different possibilities of combinations. By adding each number from the candidates array to the current combination, we reduce the problem size by one and recursively call the function on the remaining numbers.

## Algorithm

1. Initialize an empty list `ans` to store all unique combinations.
2. Define a helper function `func` that takes in the candidates array, target sum, current index, and a temporary list of integers representing the current combination.
3. If the target sum is zero, it means we have found a valid combination; add it to the `ans` list and return.
4. If the target sum is less than zero or if we've reached the end of the candidates array (i.e., the index exceeds the array length), we cannot form a valid combination, so return without adding anything to `ans`.
5. Add the current number at the given index to the temporary list and recursively call `func` with the updated target sum and same index.
6. Remove the last added element from the temporary list (backtracking).
7. Recursively call `func` on the remaining numbers in the candidates array, incrementing the index by one.

## Concept to Remember
* **Backtracking**: a technique used in recursion where we try different possibilities until we find the solution.
* **Recursive thinking**: breaking down problems into smaller sub-problems that can be solved recursively.
* **State management**: managing the state of the problem through variables and data structures (e.g., `curr` list).

## Common Mistakes

* Not handling edge cases properly, such as when the target sum is zero or negative.
* Failing to backtrack correctly, leading to duplicate combinations being added to the result.
* Not using an appropriate data structure for storing intermediate results.

## Complexity Analysis
- Time: O(N^(T/M+1)) where N is the number of elements in candidates and T is the target sum. The reason is that in the worst case, we try each element up to (T/M) times, where M is the minimum number.
- Space: O(T) for storing the intermediate results.

## Commented Code
```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        func(candidates, target, 0, new ArrayList<>());
        return ans;
    }
    
    public void func(int[] nums, int target, int index, List<Integer> curr){    
        // Base case: if target is zero, we've found a valid combination
        if(target == 0) {
            ans.add(new ArrayList<>(curr)); 
            return; 
        }
        
        // If target is less than zero or we're out of numbers, cannot form combination
        if(target < 0 || index == nums.length) 
            return;
        
        // Add current number to combination and recurse on remaining target
        curr.add(nums[index]);
        func(nums, target-nums[index], index, curr);
        curr.remove(curr.size()-1); // backtrack
        
        // Recurse on next number in array
        func(nums, target, index+1, curr);
    }
}
```

## Interview Tips

* Pay attention to edge cases and handle them properly.
* Use backtracking and recursion to explore different possibilities.
* Manage the state of the problem using variables and data structures.

## Revision Checklist
- [ ] Practice solving this problem with different inputs.
- [ ] Review and understand the time and space complexity analysis.
- [ ] Implement the solution in a programming language other than Java.

## Similar Problems

* LeetCode: `39. Combination Sum` (similar problem but without backtracking)
* LeetCode: `47. Permutations II` (permutation generation using recursion)

## Tags
`Array` `Hash Map` `Recursion`
