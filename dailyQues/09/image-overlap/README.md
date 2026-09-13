# Image Overlap

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Matrix`  
**Time:** O(n^2)  
**Space:** O(n^2)

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
Image Overlap: find the maximum overlap between two images.
Solve it by counting the occurrences of each overlap.

## Intuition
The problem can be solved by treating each image as a set of points and counting the occurrences of each overlap between points. The key insight is that the overlap between two points is determined by the difference in their coordinates, which can be mapped to a single number.

## Algorithm
1. Create two lists, `l1` and `l2`, to store the coordinates of points in the first and second images, respectively.
2. Initialize a matrix `cnt` of size `2*n x 2*n` to store the count of overlaps at each position.
3. Iterate through the points in `l1` and for each point, iterate through the points in `l2` and calculate the overlap at the current position.
4. Increment the count at the calculated position in the `cnt` matrix.
5. Keep track of the maximum count found so far.
6. Return the maximum count found.

## Concept to Remember
* Representing images as sets of points
* Counting occurrences of overlaps
* Using a matrix to store counts of overlaps

## Common Mistakes
* Failing to initialize the `cnt` matrix correctly
* Not incrementing the count at the correct position in the `cnt` matrix
* Not keeping track of the maximum count found

## Complexity Analysis
- Time: O(n^2) - iterating through all points in both images
- Space: O(n^2) - storing the count of overlaps at each position

## Commented Code
```java
class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        ArrayList<int[]> l1 = new ArrayList<>(); // list of points in img1
        ArrayList<int[]> l2 = new ArrayList<>(); // list of points in img2
        int n = img1.length; // size of images

        // store points in l1 and l2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) l1.add(new int[]{i, j}); // add point to l1 if it's 1
                if (img2[i][j] == 1) l2.add(new int[]{i, j}); // add point to l2 if it's 1
            }
        }

        int max = 0; // maximum count found
        int[][] cnt = new int[2 * n][2 * n]; // matrix to store counts of overlaps

        // iterate through points in l1 and l2
        for (int[] p1 : l1) {
            for (int[] p2 : l2) {
                int rx = p1[0] - p2[0] + n; // calculate overlap in x
                int ry = p1[1] - p2[1] + n; // calculate overlap in y
                max = Math.max(max, ++cnt[rx][ry]); // increment count at position and update max
            }
        }

        return max; // return maximum count found
    }
}
```

## Interview Tips
* Practice solving problems on paper before coding
* Pay attention to edge cases and corner cases
* Use a matrix to store counts of overlaps for efficient lookups

## Revision Checklist
- [ ] Review the problem statement and understand the requirements
- [ ] Practice solving the problem on paper before coding
- [ ] Pay attention to edge cases and corner cases
- [ ] Use a matrix to store counts of overlaps for efficient lookups

## Similar Problems
* #785: Is Graph Bipartite?
* #817: Linked List Components

## Tags
`Array` `Hash Map` `Matrix`
