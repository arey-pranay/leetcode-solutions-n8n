# Find Target Indices After Sorting Array

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Sorting`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int less = 0, equal = 0;
        int n = nums.length;
        for(int i = 0;i<n;i++){
            if(nums[i]==target) equal++;
            if(nums[i] < target) less++;
        }
        ArrayList<Integer> ans = new ArrayList<>();        
        for(int i=less;i<less+equal;i++) ans.add(i);
        return ans;  
    }
}
```

---

---

## Quick Revision
Find the indices of a target value in a sorted array. This can be solved by counting the number of elements less than the target and the number of elements equal to the target, then returning the indices of the equal elements.

## Intuition
The key insight here is that the array is already sorted. Therefore, we can use a simple traversal to count the number of elements less than and equal to the target, and then return the indices of the equal elements. This approach works because the sorted array maintains its order, allowing us to easily identify the positions of the target elements.

## Algorithm
1. Initialize counters `less` and `equal` to 0.
2. Iterate through the array, incrementing `less` whenever an element is less than the target, and incrementing `equal` whenever an element is equal to the target.
3. After iterating through the array, create a list of indices from `less` to `less + equal`.
4. Return the list of indices.

## Concept to Remember
* Binary search is not necessary because the array is already sorted.
* We can use simple traversal to count the number of elements less than and equal to the target.
* The sorted array maintains its order, allowing us to easily identify the positions of the target elements.

## Common Mistakes
* Forgetting to initialize the counters to 0.
* Not iterating through the entire array, resulting in incorrect counts.
* Returning the wrong list of indices.

## Complexity Analysis
- Time: O(n) - reason: iterating through the array once
- Space: O(n) - reason: creating a list of indices

## Commented Code
```java
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        // Initialize counters to 0
        int less = 0, equal = 0;
        
        // Get the length of the array
        int n = nums.length;
        
        // Iterate through the array
        for(int i = 0; i < n; i++) {
            // If an element is equal to the target, increment the equal counter
            if (nums[i] == target) equal++;
            // If an element is less than the target, increment the less counter
            if (nums[i] < target) less++;
        }
        
        // Create a list of indices from less to less + equal
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = less; i < less + equal; i++) ans.add(i);
        
        // Return the list of indices
        return ans;
    }
}
```

## Interview Tips
* Make sure to initialize the counters to 0.
* Double-check that you're iterating through the entire array.
* Practice explaining the logic behind your solution.
* Consider using a more efficient data structure if the array is large.

## Revision Checklist
- [ ] Review the logic behind the solution
- [ ] Practice explaining the solution to a mock interviewer
- [ ] Optimize the solution for large input sizes

## Similar Problems
* Find First and Last Position of Element in Sorted Array
* Search Insert Position
* Find Minimum in Rotated Sorted Array
