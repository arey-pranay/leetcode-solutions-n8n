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
The problem asks for the minimum operations to make all elements in a grid equal, where an operation is adding or subtracting `x`.
We solve this by flattening the grid, sorting it, checking feasibility, and then finding the median to minimize operations.

## Intuition
The core idea is that if we can transform all numbers to a target value `T`, then the difference between any two numbers `a` and `b` in the grid must be divisible by `x` (i.e., `abs(a - b) % x == 0`). This is because each operation changes a number by `x`. If this condition holds, we can transform any number in the grid to any other number in the grid. To minimize the total operations (sum of `abs(num - target) / x`), the optimal target value is the median of the sorted flattened grid. This is a classic property of minimizing the sum of absolute differences.

## Algorithm
1. Flatten the 2D `grid` into a 1D array `arr`.
2. Sort the `arr`.
3. Check if it's possible to make all elements equal. Iterate through `arr` and verify that `abs(arr[i] - arr[0]) % x == 0` for all `i`. If not, return `-1`.
4. Determine the median of `arr`. This will be `arr[arr.length / 2]`.
5. Calculate the total operations. Iterate through `arr` and for each `num`, calculate the number of operations needed to transform it to the `mid` value: `abs((mid - num) / x)`. Sum these operations.
6. Return the total sum of operations.

## Concept to Remember
*   **Parity and Divisibility:** The constraint `(a - b) % x == 0` is crucial for feasibility.
*   **Median Minimizes Sum of Absolute Deviations:** The median of a dataset is the value that minimizes the sum of absolute differences to all other data points.
*   **Greedy Approach:** By choosing the median, we are making a locally optimal choice that leads to a globally optimal solution for minimizing operations.

## Common Mistakes
*   Not checking the feasibility condition (`abs(a - b) % x == 0`) early on, leading to incorrect results or unnecessary computations.
*   Choosing an arbitrary element as the target instead of the median, which will not yield the minimum operations.
*   Incorrectly calculating the number of operations for each element (e.g., forgetting to divide by `x` or using `(num - mid)` instead of `abs(mid - num)`).
*   Handling edge cases like an empty grid or a grid with only one element (though the problem constraints usually prevent this).

## Complexity Analysis
- Time: O(N*M log(N*M)) - reason: Flattening takes O(N*M), sorting takes O(N*M log(N*M)), and the final iteration takes O(N*M). The dominant factor is sorting.
- Space: O(N*M) - reason: To store the flattened array.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting and other array utilities.

class Solution {
    public int minOperations(int[][] grid, int x) {
        // Get the dimensions of the grid.
        int rows = grid.length;
        int cols = grid[0].length;
        // Create a 1D array to store all elements from the grid.
        int[] arr = new int[rows * cols];
        // Index to keep track of the current position in the 1D array.
        int index = 0;
        // Iterate through each row of the grid.
        for (int[] temp : grid) {
            // Iterate through each number in the current row.
            for (int num : temp) {
                // Add the number to the 1D array.
                arr[index++] = num;
            }
        }
        // Sort the 1D array in ascending order. This is crucial for finding the median and checking feasibility.
        Arrays.sort(arr);

        // Check if it's possible to make all elements equal.
        // If the difference between any element and the first element is not divisible by x,
        // then it's impossible to make them all equal.
        for (int i = 0; i < arr.length; i++) {
            // Calculate the absolute difference and check divisibility by x.
            if (Math.abs(arr[i] - arr[0]) % x != 0) {
                // If not divisible, return -1 indicating impossibility.
                return -1;
            }
        }

        // Initialize the total number of operations to 0.
        int ans = 0;
        // The optimal target value to minimize operations is the median of the sorted array.
        // For an array of length N, the median is at index N/2.
        int mid = arr[arr.length / 2];

        // Iterate through each number in the sorted array.
        for (int num : arr) {
            // Calculate the number of operations needed to transform 'num' to 'mid'.
            // The difference (mid - num) must be a multiple of x.
            // The number of operations is the absolute value of this difference divided by x.
            int n = (mid - num) / x; // This division is guaranteed to be an integer due to the feasibility check.
            // Add the absolute number of operations to the total answer.
            ans += Math.abs(n);
        }
        // Return the minimum total operations required.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain the feasibility check (`abs(a - b) % x == 0`) and why it's necessary.
*   Articulate why the median is the optimal target value for minimizing the sum of absolute differences.
*   Walk through an example to demonstrate how the algorithm works, including the feasibility check and median calculation.
*   Be prepared to discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understood the problem statement and constraints.
- [ ] Implemented the grid flattening and sorting.
- [ ] Correctly implemented the feasibility check.
- [ ] Identified and used the median as the target value.
- [ ] Calculated operations correctly using `abs((mid - num) / x)`.
- [ ] Handled the `-1` return case.
- [ ] Analyzed time and space complexity.

## Similar Problems
*   Minimum Moves to Equal Array Elements II (LeetCode 462)
*   Minimum Operations to Make Array Equal (LeetCode 1551)
*   Median of Two Sorted Arrays (LeetCode 4)

## Tags
`Array` `Sorting` `Math` `Greedy` `Median`
