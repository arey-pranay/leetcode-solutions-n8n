# Image Overlap

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Matrix`  
**Time:** O(N^4)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        ArrayList<int[]> l1= new ArrayList<>();
        ArrayList<int[]> l2= new ArrayList<>();
        int n = img1.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(img1[i][j]==1) l1.add(new int[]{i,j});
                if(img2[i][j]==1) l2.add(new int[]{i,j});
            }
        }
        // 2,2   2,3   3,3
        // 3,3   3,4   4,4
        
        // 1,1   1,1  1,1
        // 1     2    3
        int max = 0;
        int[][] cnt = new int[2 * n][2 * n];
        for(int[] p1 : l1){
            for(int[] p2 : l2){
                int rx = p1[0]-p2[0]+n;
                int ry = p1[1]-p2[1]+n;
              
             //   int key = (rx*100) + ry; //0,0 se 30,30
                // 101 //1,1
                // 1001 //10,1
                // 110  // 1,10
                // 1010 // 10,10
        
                max = Math.max(max,++cnt[rx][ry]);
            }
        }
        return max;
    }
}
```

---

---
## Quick Revision
This problem asks for the largest overlap between two binary images after any possible translation. We solve it by finding all '1' pixel coordinates and counting the frequency of relative displacements.

## Intuition
The core idea is that if we translate `img2` relative to `img1`, a '1' pixel at `(r1, c1)` in `img1` and a '1' pixel at `(r2, c2)` in `img2` will overlap if `r1 = r2 + dr` and `c1 = c2 + dc`, where `(dr, dc)` is the translation vector. This means `dr = r1 - r2` and `dc = c1 - c2`. If we iterate through all pairs of '1' pixels from both images, we can calculate all possible relative displacements. The displacement that occurs most frequently corresponds to the largest overlap.

## Algorithm
1.  **Store '1' Pixel Coordinates:** Create two lists, `l1` and `l2`, to store the `(row, col)` coordinates of all '1's in `img1` and `img2`, respectively.
2.  **Iterate and Populate Lists:** Traverse both `img1` and `img2`. If `img1[i][j] == 1`, add `[i, j]` to `l1`. If `img2[i][j] == 1`, add `[i, j]` to `l2`.
3.  **Count Displacement Frequencies:** Initialize a 2D array `cnt` (e.g., of size `2*n x 2*n`, where `n` is the image dimension) to store the frequency of each relative displacement. The indices of `cnt` will represent the displacement. To handle negative displacements and map them to non-negative array indices, we can add `n` to both the row and column differences. So, a displacement `(dr, dc)` will be mapped to `cnt[dr + n][dc + n]`.
4.  **Calculate and Update Max Overlap:** Iterate through each pair of '1' pixel coordinates `p1` from `l1` and `p2` from `l2`.
    *   Calculate the row displacement: `rx = p1[0] - p2[0]`.
    *   Calculate the column displacement: `ry = p1[1] - p2[1]`.
    *   Map these displacements to indices in `cnt`: `idx_r = rx + n`, `idx_c = ry + n`.
    *   Increment the count at `cnt[idx_r][idx_c]`.
    *   Update the `max` overlap found so far with the current count: `max = Math.max(max, cnt[idx_r][idx_c])`.
5.  **Return Max Overlap:** After checking all pairs, return the `max` value.

## Concept to Remember
*   **Coordinate Transformation/Translation:** Understanding how relative positions change under translation.
*   **Hash Map / Frequency Counting:** Using a data structure (here, a 2D array acting as a hash map) to efficiently count occurrences of derived values (displacements).
*   **Brute Force Optimization:** While the core idea is brute-forcing pairs, the use of displacement counting significantly optimizes the process.

## Common Mistakes
*   **Index Out of Bounds:** Incorrectly calculating the indices for the `cnt` array, especially when dealing with negative displacements, leading to `ArrayIndexOutOfBoundsException`.
*   **Not Handling All Translations:** Missing the fact that any translation is possible, and thus all relative displacements between '1's must be considered.
*   **Inefficient Overlap Calculation:** Trying to simulate actual image shifting and pixel-by-pixel comparison for each translation, which would be much slower.
*   **Off-by-One Errors:** Miscalculating the size of the `cnt` array or the offset (`+n`) for mapping displacements.

## Complexity Analysis
*   **Time:** O(N^4) - where N is the dimension of the square image.
    *   Populating `l1` and `l2` takes O(N^2).
    *   The nested loops to calculate displacements iterate through all pairs of '1's. In the worst case, both images could be filled with '1's, leading to N^2 '1's in each list. This results in (N^2) * (N^2) = N^4 pairs.
*   **Space:** O(N^2) -
    *   `l1` and `l2` can store up to N^2 coordinates each in the worst case.
    *   The `cnt` array is of size `(2*N) x (2*N)`, which is O(N^2).

## Commented Code
```java
class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        // Initialize a list to store coordinates of '1's in img1.
        ArrayList<int[]> l1 = new ArrayList<>();
        // Initialize a list to store coordinates of '1's in img2.
        ArrayList<int[]> l2 = new ArrayList<>();
        // Get the dimension of the square images.
        int n = img1.length;

        // Iterate through each cell of img1.
        for (int i = 0; i < n; i++) {
            // Iterate through each cell of img1.
            for (int j = 0; j < n; j++) {
                // If the current cell in img1 is '1', add its coordinates to l1.
                if (img1[i][j] == 1) l1.add(new int[]{i, j});
                // If the current cell in img2 is '1', add its coordinates to l2.
                if (img2[i][j] == 1) l2.add(new int[]{i, j});
            }
        }

        // Initialize a 2D array to count the frequency of each relative displacement.
        // The size is 2*n x 2*n to accommodate all possible displacements (from -(n-1) to +(n-1)).
        // We add 'n' to the displacement to map it to a non-negative index.
        int[][] cnt = new int[2 * n][2 * n];
        // Initialize the maximum overlap found so far to 0.
        int max = 0;

        // Iterate through each '1' pixel in img1.
        for (int[] p1 : l1) {
            // Iterate through each '1' pixel in img2.
            for (int[] p2 : l2) {
                // Calculate the row displacement: p1's row - p2's row.
                // Add 'n' to shift the displacement to a non-negative index for the 'cnt' array.
                // For example, a displacement of -1 becomes -1 + n, and 0 becomes n.
                int rx = p1[0] - p2[0] + n;
                // Calculate the column displacement: p1's col - p2's col.
                // Add 'n' to shift the displacement to a non-negative index.
                int ry = p1[1] - p2[1] + n;

                // Increment the count for this specific displacement (rx, ry).
                // The ++ operator increments the count and then returns the new value.
                // This new value is the number of times this displacement has been seen so far.
                // Update 'max' if the current count for this displacement is greater than the current 'max'.
                max = Math.max(max, ++cnt[rx][ry]);
            }
        }
        // Return the maximum overlap found across all possible translations.
        return max;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the size of the images and if they are always square. This helps in understanding the bounds for array indexing.
*   **Explain the Displacement Logic:** Clearly articulate why calculating the difference between coordinates of '1's from both images represents a translation and how to map these differences to array indices.
*   **Discuss Edge Cases:** Consider cases where one or both images have no '1's, or where the images are entirely '1's. The current solution handles these gracefully.
*   **Justify Space/Time Complexity:** Be prepared to explain why the complexity is O(N^4) time and O(N^2) space, especially the N^4 part arising from iterating through all pairs of '1's.

## Revision Checklist
- [ ] Understand the problem: find max overlap after translation.
- [ ] Identify '1' pixels in both images.
- [ ] Realize overlap corresponds to relative displacement of '1's.
- [ ] Devise a way to count frequencies of these displacements.
- [ ] Handle negative displacements by shifting indices.
- [ ] Implement the algorithm with correct array indexing.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Max Number of Balloons (LeetCode 1189) - Similar concept of counting character frequencies.
*   Subarray Sum Equals K (LeetCode 560) - Uses prefix sums and hash maps for counting.
*   K-diff Pairs in an Array (LeetCode 532) - Involves finding pairs with a specific difference.

## Tags
`Array` `Hash Map`
