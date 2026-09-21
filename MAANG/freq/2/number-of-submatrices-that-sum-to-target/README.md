# Number Of Submatrices That Sum To Target

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Matrix` `Prefix Sum`  
**Time:** O(m \* n^2)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int numSubmatrixSumTarget(int[][] maatrix, int target) {
        int m=  maatrix.length, n = maatrix[0].length;
        for(int row=0;row<m;row++)
            for(int col=1;col<n;col++)
                maatrix[row][col] += maatrix[row][col-1];
        int ans = 0;
        
        for(int c1 = 0; c1<n; c1++){
            for(int c2 = c1; c2<n; c2++){
                Map<Integer,Integer> map = new HashMap<>();
                map.put(0,1);
                int sum = 0;
                for(int row=0; row<m; row++){
                    sum += maatrix[row][c2] - (c1 > 0 ? maatrix[row][c1-1] : 0);
                    ans += map.getOrDefault(sum-target,0); // 1
                    map.put(sum, map.getOrDefault(sum,0)+1);
                }
            }
        }
        return ans;
    }
}
```

---

---

## Quick Revision
The problem requires counting the number of submatrices in a given matrix that sum to a specific target value.
This can be solved by using a hash map to keep track of the cumulative sum of each column and then iterating over all possible submatrices.

## Intuition
The key insight here is to realize that we can calculate the cumulative sum of each column in a single pass, and then use this information to count the number of submatrices that sum to the target. By iterating over all possible submatrices, we can use the hash map to efficiently count the occurrences of each cumulative sum.

## Algorithm

1. First, we calculate the cumulative sum of each column by iterating over each row and adding the value at each position to the value at the same position in the previous column.
2. Then, we iterate over all possible submatrices by considering each column as the starting point of the submatrix and iterating over all columns to the right of it.
3. For each submatrix, we calculate the cumulative sum by subtracting the cumulative sum of the starting column from the cumulative sum of the ending column.
4. We use a hash map to keep track of the occurrences of each cumulative sum, and increment the count in the hash map for each submatrix that sums to the target.

## Concept to Remember

* **Prefix sum**: the cumulative sum of an array, which can be used to efficiently calculate the sum of any subarray.
* **Hash map**: a data structure that allows for efficient lookups and updates of key-value pairs.
* **Sliding window**: a technique used to process a sequence of data in a window of fixed size, often used in problems involving arrays and strings.

## Common Mistakes

* **Not calculating the cumulative sum correctly**: make sure to add the value at each position to the value at the same position in the previous column.
* **Not using a hash map efficiently**: use the `getOrDefault` method to avoid unnecessary lookups and updates.
* **Not considering all possible submatrices**: make sure to iterate over all columns to the right of the starting column for each submatrix.
* **Not handling edge cases correctly**: make sure to handle cases where the starting column is 0 or the ending column is the last column.

## Complexity Analysis

- Time: O(m \* n^2) - reason: we iterate over all possible submatrices, which takes O(m \* n^2) time, and we use a hash map to keep track of the cumulative sum, which takes O(n) time per iteration.
- Space: O(n) - reason: we use a hash map to keep track of the cumulative sum, which takes O(n) space.

## Commented Code

```java
class Solution {
    public int numSubmatrixSumTarget(int[][] maatrix, int target) {
        int m = maatrix.length, n = maatrix[0].length;
        // Calculate cumulative sum of each column
        for (int row = 0; row < m; row++) {
            for (int col = 1; col < n; col++) {
                maatrix[row][col] += maatrix[row][col - 1];
            }
        }
        
        int ans = 0;
        // Iterate over all possible submatrices
        for (int c1 = 0; c1 < n; c1++) {
            for (int c2 = c1; c2 < n; c2++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int sum = 0;
                // Calculate cumulative sum of each submatrix
                for (int row = 0; row < m; row++) {
                    sum += maatrix[row][c2] - (c1 > 0 ? maatrix[row][c1 - 1] : 0);
                    ans += map.getOrDefault(sum - target, 0); // 1
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return ans;
    }
}
```

## Interview Tips

* **Make sure to calculate the cumulative sum correctly**: this is a crucial step in solving the problem.
* **Use a hash map efficiently**: use the `getOrDefault` method to avoid unnecessary lookups and updates.
* **Consider all possible submatrices**: make sure to iterate over all columns to the right of the starting column for each submatrix.
* **Handle edge cases correctly**: make sure to handle cases where the starting column is 0 or the ending column is the last column.

## Revision Checklist

- [ ] Understand the problem statement and requirements
- [ ] Calculate the cumulative sum of each column correctly
- [ ] Use a hash map efficiently
- [ ] Consider all possible submatrices
- [ ] Handle edge cases correctly

## Similar Problems

* LeetCode 530: `Subarray VSum Equals Target`
* LeetCode 974: `Subarray Sum Equals K`

## Tags

`Array` `Hash Map` `Prefix Sum` `Sliding Window`
