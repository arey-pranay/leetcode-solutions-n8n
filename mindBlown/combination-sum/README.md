# Combination Sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Backtracking`  
**Time:** O(N × (2^N)  
**Space:** O(N)

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
The problem is to find all combinations of a given array that sum up to a target value.
We solve this by using recursion and backtracking.

## Intuition
The key insight here is that we can use recursion to explore all possible combinations, and backtracking to remove elements from the current combination when it exceeds the target. This approach works because each element in the array can be used zero or more times in a combination.

## Algorithm

1. Define a recursive function `func` that takes an array of numbers, a target value, an index, and a current combination.
2. If the target is 0, add the current combination to the result list and return.
3. If the target is negative or we've reached the end of the array, return without adding anything to the result.
4. Add the current element to the current combination and recursively call `func` with the updated target (subtracting the current element) and index.
5. Remove the last added element from the current combination (backtracking).
6. Recursively call `func` with the same target, but increment the index.

## Concept to Remember
* Backtracking: removing elements from a current solution when it exceeds a certain limit.
* Recursive thinking: breaking down the problem into smaller sub-problems and solving each recursively.
* Dynamic programming: using memoization or tabulation to store intermediate results and avoid redundant computations (not directly applicable here, but important in similar problems).

## Common Mistakes
* Not handling edge cases like an empty array or a target of 0 correctly.
* Failing to remove elements from the current combination when backtracking.
* Using an incorrect data structure for storing combinations.

## Complexity Analysis
- Time: O(N × (2^N)), where N is the number of candidates, because we're exploring all possible combinations.
- Space: O(N), for storing each combination in the result list and recursive call stack.

## Commented Code
```java
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Start recursion with an empty combination
        func(candidates, target, 0, new ArrayList<>());
        return ans;
    }
    
    // Recursive function to generate combinations
    public void func(int[] nums, int target, int index, List<Integer> curr){    
        if (target == 0) { // Base case: target reached, add combination to result
            ans.add(new ArrayList<>(curr)); 
            return; 
        }
        
        if (target < 0 || index == nums.length) { // Backtrack or out of array bounds
            return;
        }

        // Add current element to combination and recurse
        curr.add(nums[index]);
        func(nums, target-nums[index], index, curr);
        
        // Remove last added element for backtracking
        curr.remove(curr.size()-1);

        // Recurse without adding current element
        func(nums, target, index+1, curr);
    }
}
```

## Interview Tips

* Be prepared to explain the backtracking and recursive logic.
* Emphasize the importance of handling edge cases correctly.
* Practice solving similar problems with arrays and recursion.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Implement the recursive function `func` correctly.
- [ ] Handle edge cases like an empty array or target of 0.
- [ ] Review backtracking and recursive logic for clarity.

## Similar Problems

* [LeetCode: Combination Sum II](https://leetcode.com/problems/combination-sum-ii/)
* [LeetCode: Combination Sum III](https://leetcode.com/problems/combination-sum-iii/)

## Tags
`Array` `Backtracking` `Recursion`
