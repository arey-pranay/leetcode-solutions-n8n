# Minimum Operations To Make A Uni Value Grid

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Sorting` `Matrix`  
**Time:** O(N*M log(N*M)  
**Space:** O(N*M)

---

## Solution (java)

```java
class Solution {
    public int minOperations(int[][] grid, int x) { 
        int[] arr = new int[grid.length * grid[0].length];
        int index = 0;
        for(int[] temp : grid) for(int num : temp) arr[index++] = num;
        Arrays.sort(arr);
        // the case where -> agr kisi bhi number ko hum array ka koi dusra number nhi bna skte, then makking all same is not possible.
        for(int i =0; i<arr.length;i++) if(Math.abs(arr[i]-arr[0])%x!=0)  return -1;
        int ans = 0;
        int mid = arr[arr.length/2];
        //does there exist an n such that:
        //   for a number "mid", 
        // for all i, in arr[i], arr[i] - n*x = mid || arr[i] + n*x = mid;
        for(int num : arr){
            int n = (mid - num)/x;
            ans += Math.abs(n);
        }
        return ans;
    }
}
// logic : nums[i ] se mid tk ka jo difference h , divide by x jitna addition yaa substraction use krnzna hoga

// // key is: agr 4 ko 10 bna skte hai, to 10 ko 4 bhi bna skte hai. to agr poori grid ko grid[i][j] for any 1 value of i,j bna skte hai, then for every value of i,j bhi bna skte hai
// mid isliye kiya kyonki usi se sbse kam possibility aayegi 
```

---

---
## Quick Revision
The problem asks for the minimum operations to make all elements in a grid equal, where an operation is adding or subtracting a value `x`.
This is solved by finding a target value (median) and calculating the total operations to transform each element to it, ensuring all transformations are valid.

## Intuition
The core idea is that if we can transform all elements to a single value `T`, then for any two elements `a` and `b` in the grid, `(a - T)` must be divisible by `x`, and `(b - T)` must be divisible by `x`. This implies that `(a - b)` must also be divisible by `x`. If this condition isn't met for any pair of elements, it's impossible to make the grid uniform.

Once we establish that it's possible, we need to find the optimal target value `T` that minimizes the total operations. The total operations to transform all elements to `T` is the sum of `abs(element - T) / x` for all elements. This sum is minimized when `T` is the median of the sorted array of grid elements. This is a classic property: the sum of absolute differences is minimized at the median.

## Algorithm
1. Flatten the 2D `grid` into a 1D array `arr`.
2. Sort the `arr`.
3. Check if it's possible to make all elements uniform: Iterate through `arr` and verify that the absolute difference between any element `arr[i]` and the first element `arr[0]` is divisible by `x`. If not, return -1.
4. Determine the median of `arr`. This will be our target value. If `arr` has an even number of elements, any value between the two middle elements (inclusive) that satisfies the divisibility by `x` condition would work, but the median is a safe and optimal choice.
5. Calculate the total operations: Iterate through `arr` and for each element `num`, calculate `abs(num - median) / x` and add it to a running total `ans`.
6. Return `ans`.

## Concept to Remember
*   **Median Property:** The sum of absolute differences between a set of numbers and a target value is minimized when the target value is the median of the set.
*   **Divisibility Constraint:** For all elements to become equal to a target `T` by adding/subtracting `x`, the difference between any two elements must be a multiple of `x`.
*   **Greedy Approach:** By choosing the median as the target, we are greedily minimizing the operations for each element individually, which leads to the global minimum.

## Common Mistakes
*   **Not checking divisibility first:** Failing to verify if `(arr[i] - arr[0]) % x == 0` for all `i` can lead to incorrect results or unnecessary computations when a solution is impossible.
*   **Choosing the wrong target value:** Instead of the median, picking the mean or any other value might not yield the minimum number of operations.
*   **Integer division issues:** Incorrectly handling integer division when calculating `abs(num - median) / x` can lead to off-by-one errors.
*   **Forgetting to flatten the grid:** Trying to operate on the 2D grid directly without flattening it into a 1D array can complicate the median finding and difference calculations.

## Complexity Analysis
- Time: O(N*M log(N*M)) - reason: Flattening the grid takes O(N*M) time. Sorting the flattened array takes O(N*M log(N*M)) time, where N is the number of rows and M is the number of columns. The subsequent loops take O(N*M) time.
- Space: O(N*M) - reason: We create a 1D array to store all elements of the grid, which requires O(N*M) space.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting functionality.

class Solution {
    public int minOperations(int[][] grid, int x) {
        // Get the dimensions of the grid.
        int rows = grid.length;
        int cols = grid[0].length;
        // Create a 1D array to store all elements from the grid.
        int[] arr = new int[rows * cols];
        // Initialize an index for the 1D array.
        int index = 0;
        // Iterate through each row of the grid.
        for(int[] temp : grid) {
            // Iterate through each element in the current row.
            for(int num : temp) {
                // Add the element to the 1D array.
                arr[index++] = num;
            }
        }
        // Sort the 1D array in ascending order. This is crucial for finding the median and checking divisibility.
        Arrays.sort(arr);

        // Check if it's possible to make all elements uniform.
        // For all elements to be transformable to a common value T, the difference between any two elements must be divisible by x.
        // We check this by comparing each element with the first element after sorting.
        for(int i = 0; i < arr.length; i++) {
            // If the absolute difference between the current element and the first element is not divisible by x,
            // then it's impossible to make the grid uniform.
            if(Math.abs(arr[i] - arr[0]) % x != 0) {
                // Return -1 to indicate impossibility.
                return -1;
            }
        }

        // Initialize the total number of operations to 0.
        int ans = 0;
        // Determine the median of the sorted array. The median minimizes the sum of absolute differences.
        // For an array of length L, the median is at index L/2.
        int mid = arr[arr.length / 2];

        // Calculate the total operations required to make all elements equal to the median.
        // Iterate through each number in the sorted array.
        for(int num : arr) {
            // Calculate the difference between the current number and the median.
            // This difference must be a multiple of x.
            // The number of operations for this element is the absolute difference divided by x.
            // We can express this as (median - num) / x, which represents how many times x needs to be added or subtracted.
            int n = (mid - num) / x;
            // Add the absolute number of operations for this element to the total answer.
            ans += Math.abs(n);
        }
        // Return the total minimum operations.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the median property:** Clearly articulate why the median is the optimal target value for minimizing the sum of absolute differences.
*   **Address the divisibility constraint early:** Emphasize the importance of the `(a - b) % x == 0` condition and how it's checked. This shows you've thought about edge cases and impossibility.
*   **Walk through an example:** Use a small grid and `x` value to demonstrate how the algorithm works step-by-step, including flattening, sorting, checking divisibility, finding the median, and calculating operations.
*   **Discuss time/space complexity:** Be prepared to analyze the complexity of your solution, especially the sorting step.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the median property for minimizing sum of absolute differences.
- [ ] Implement the divisibility check correctly.
- [ ] Flatten the grid into a 1D array.
- [ ] Sort the 1D array.
- [ ] Calculate the median.
- [ ] Compute total operations using `abs(num - median) / x`.
- [ ] Handle the impossible case by returning -1.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Minimum Moves to Equal Array Elements II (LeetCode 462)
*   Minimum Operations to Make Array Equal (LeetCode 1551)
*   Minimum Cost to Make Array Equal (LeetCode 1703)

## Tags
`Array` `Sorting` `Math` `Greedy`
