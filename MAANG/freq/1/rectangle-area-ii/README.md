# Rectangle Area Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Segment Tree` `Sweep Line` `Ordered Set`  
**Time:** O(N^2)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    static int MOD = 1_000_000_007;
    public int rectangleArea(int[][] rects) {
        long ans = 0;
        int m = rects.length, n = rects[0].length;
        Arrays.sort(rects, (a,b)->Integer.compare(a[0],b[0]));
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        List<Integer> ys = new ArrayList<>();
        for(int[] rect : rects){ys.add(rect[1]);ys.add(rect[3]);}
        Collections.sort(ys);
        for(int i=0; i<ys.size()-1; i++){ // we dont want to traverse every row otherwise 0 -> 1e9 would give 1e9 iterations, but here only 2 iterations.
          int xFilled = -1;
          long width = 0;
          int y = ys.get(i);
          for(int[] rect : rects){
            if(rect[1] <= y && y < rect[3]) {
              int left = Math.max(rect[0],xFilled), right = rect[2];
              if(right <= left) continue;
              width += right - left;
              xFilled = right;
            }
          }
          long height = ys.get(i+1)-y; // agr kuch bhi change hota in dono ke beech me, to wo ys ka agla element hoga. because we are storing starting as well as ending of all blocks, so 2 neighbours y ke eech me every width is valid for the entire range
          ans = (ans+ (width*height)%MOD)%MOD;
        }
        return (int)ans%MOD;
    }
}









// 1:[0,0,2,2]  -> 0,2
// 2:[1,0,2,3]  -> 0,3
// 3:[1,0,3,1]  -> 0,1

// y = 0 to 1 => 3
// y = 1 to 2 => 2
// y = 2 to 3 => 1

// => 3 + 2 + 1 = 6

// if(y > rect[1] && y+1 < rect[3]) ans++;

// sort based on x coordinate, ascending.
// maintain how much we have already filled, to prevent overlaps
// for each y row, and each rectangle, add 
//   if(y > rect[1] && y+1 < rect[3] && nonOverlap) ans+ rect[2]-rect[0];

```

---

---
## Quick Revision
Calculate the total area covered by a collection of overlapping rectangles on a 2D plane, returning the result modulo $10^9 + 7$.
Solve this using Coordinate Compression (Sweep-Line variant) by breaking the Y-axis into distinct intervals and summing up active X-widths multiplied by Y-heights.

## Intuition
The "aha moment" is realizing that we cannot iterate over every possible coordinate since $10^9$ is too large, but we can discretize the problem using the actual $Y$-coordinates of the rectangle edges. By collecting all unique bottom and top $Y$-coordinates, we slice the plane into horizontal strips. Within each strip defined by adjacent $Y$-coordinates, the active rectangles do not change their vertical status. Thus, for each strip, we can find the total unique horizontal length covered by overlapping rectangles using interval merging or an active sweep-line along the sorted $X$-coordinates, and multiply it by the strip's height.

## Algorithm
1. Extract all unique $Y$-coordinates (bottom and top edges) from all given rectangles and sort them in ascending order.
2. Initialize an accumulator `ans` to $0$ and a modulo constant of $1,000,000,007$.
3. Loop through the sorted $Y$-coordinates from index $0$ to $ys.size() - 2$, forming vertical strips between $ys[i]$ and $ys[i+1]$.
4. For each vertical strip, find all rectangles that vertically overlap with the strip (i.e., rectangle bottom $\le ys[i]$ and rectangle top $> ys[i]$).
5. Sort or process these overlapping rectangles by their left $X$-coordinates.
6. Calculate the total unique horizontal width (`width`) covered by these rectangles in the current strip by tracking the rightmost covered $X$-coordinate (`xFilled`) to handle overlaps.
7. Multiply the accumulated `width` by the strip height ($ys[i+1] - ys[i]$), add it to `ans`, and take the result modulo $10^9 + 7$.
8. Return the final `ans` cast to an integer.

## Concept to Remember
- **Coordinate Compression**: Reducing a massive or continuous coordinate space into a sparse set of relevant events or values.
- **Sweep-Line Algorithm**: Processing geometric entities by sweeping a line across one dimension (here, the $Y$-axis strips).
- **Interval Merging / Overlap Handling**: Calculating the union length of 1D intervals by tracking the max boundary (`xFilled`).
- **Modular Arithmetic**: Safely keeping intermediate products within bounds by applying modulo at each addition step.

## Common Mistakes
- Forgetting to take the modulo at each addition step, leading to potential arithmetic overflow before casting.
- Not handling overlapping horizontal intervals correctly, resulting in double-counting rectangle areas.
- Confusing rectangle indices or mixing up $X$ and $Y$ coordinates during overlap checks.
- Assuming $Y$-coordinates must be spaced by $1$ instead of variable heights given by adjacent $Y$ values.

## Complexity Analysis
- Time: $O(N^2)$ - We iterate through $O(N)$ unique $Y$-intervals, and for each interval, we check all $N$ rectangles, sorting or processing their $X$-intervals. ($N$ is the number of rectangles).
- Space: $O(N)$ - To store the extracted $Y$-coordinates in a list and maintain sorting overhead.

## Commented Code
```java
class Solution {
    // Define the modulo constant specified by the problem requirements
    static int MOD = 1_000_000_007;
    
    public int rectangleArea(int[][] rects) {
        // Initialize the total accumulated area to zero
        long ans = 0;
        
        // Extract all unique Y-coordinates (bottom y1 and top y3) from all rectangles
        List<Integer> ys = new ArrayList<>();
        for (int[] rect : rects) {
            ys.add(rect[1]); // bottom-left y
            ys.add(rect[3]); // top-right y
        }
        
        // Sort the Y-coordinates to create consecutive vertical strips
        Collections.sort(ys);
        
        // Iterate through each pair of adjacent Y-coordinates to form horizontal strips
        for (int i = 0; i < ys.size() - 1; i++) {
            // Skip redundant identical Y-coordinates to avoid zero-height strips
            int y = ys.get(i);
            if (y.equals(ys.get(i + 1))) continue;
            
            // Track the maximum X-coordinate filled so far in this strip to handle overlaps
            int xFilled = -1;
            long width = 0;
            
            // Check every rectangle to see if it intersects with the current Y strip
            for (int[] rect : rects) {
                // If the rectangle spans across the current Y interval [ys[i], ys[i+1]]
                if (rect[1] <= y && y < rect[3]) {
                    // Adjust the left boundary based on what has already been covered
                    int left = Math.max(rect[0], xFilled);
                    int right = rect[2];
                    
                    // If the adjusted interval is invalid or completely overlapped, skip
                    if (right <= left) continue;
                    
                    // Add the unique width and update the furthest filled X coordinate
                    width += right - left;
                    xFilled = right;
                }
            }
            
            // Calculate the height of the current Y strip
            long height = ys.get(i + 1) - y;
            
            // Add the area of this strip to the total answer with modular arithmetic
            ans = (ans + (width * height) % MOD) % MOD;
        }
        
        // Return the final area modulo 10^9 + 7
        return (int) (ans % MOD);
    }
}
```

## Interview Tips
- Clarify the constraints on the number of rectangles and coordinate values upfront.
- Explain coordinate compression clearly to the interviewer before jumping into code, as it shows you recognize the scale of $10^9$ inputs.
- Discuss alternative approaches, such as using a Segment Tree with a Sweep-Line for an optimal $O(N \log N)$ time complexity if $N$ is exceptionally large.
- Mention explicitly how overlap handling on the X-axis is achieved by sorting and tracking the `xFilled` boundary.

## Revision Checklist
- [ ] Understand why a direct 2D grid approach fails due to memory/time limits.
- [ ] Know how to extract and deduplicate/sort $Y$-coordinates.
- [ ] Understand how to calculate the union of 1D intervals using a running maximum tracker (`xFilled`).
- [ ] Apply modulo arithmetic safely on products and sums.

## Similar Problems
- [Erect the Fence](https://leetcode.com/problems/erect-the-fence/)
- [The Skyline Problem](https://leetcode.com/problems/the-skyline-problem/)
- [Range Module](https://leetcode.com/problems/range-module/)
- [Count Integers in Intervals](https://leetcode.com/problems/count-integers-in-intervals/)

## Tags
`Array` `Segment Tree` `Line Sweep` `Sorting` `Geometry`
