# Rectangle Area Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Segment Tree` `Sweep Line` `Ordered Set`  
**Time:** O(N^3)  
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
Solve this efficiently using the Coordinate Compression (or Line Sweep) technique by breaking the space into disjoint vertical strips or horizontal bands.

## Intuition
The core "aha moment" is realizing that we cannot check every single coordinate point because the coordinates can be as large as $10^9$. However, the number of rectangles is relatively small (up to 200). Therefore, the $y$-coordinates (and $x$-coordinates) of all rectangle corners divide the 2D plane into a grid of independent horizontal bands and vertical intervals where the rectangle overlap status remains completely constant. By extracting all unique $y$-coordinates, sorting them, and forming adjacent horizontal strips, we can compute the width of overlapping rectangles for each strip independently and multiply it by the strip's height.

## Algorithm
1. Extract all unique $y$-coordinates from the bottom-left ($y_1$) and top-right ($y_3$) corners of every rectangle and sort them in ascending order.
2. Initialize a running answer variable `ans` to 0 and a modulo constant of $10^9 + 7$.
3. Iterate through each adjacent pair of $y$-coordinates in the sorted list, treating them as the top and bottom boundaries of a horizontal band.
4. For each horizontal band defined by `y = ys.get(i)` and `ys.get(i+1)`:
   - Filter or check all rectangles that fully cover this vertical range (i.e., `rect[1] <= y` and `y < rect[3]`).
   - Sort or process these active rectangles by their left $x$-coordinates to merge overlapping intervals.
   - Sum up the total unique width covered by these active rectangles in this band using a greedy interval-merging approach (`xFilled`).
   - Multiply the total width by the band's height (`ys.get(i+1) - y`), add it to `ans`, and take the result modulo $10^9 + 7$.
5. Return the final accumulated area modulo $10^9 + 7$.

## Concept to Remember
- **Coordinate Compression**: Reducing a massive or continuous coordinate space into a discrete set of relevant event coordinates.
- **Interval Merging / Overlap Removal**: Handling overlapping segments linearly by tracking the furthest coordinate covered so far (`xFilled`).
- **Modulo Arithmetic**: Applying modulo at each addition step to prevent integer overflow when dealing with large products.

## Common Mistakes
- Failing to handle overlapping rectangles horizontally, which leads to double-counting area.
- Forgetting to sort the extracted $y$-coordinates, resulting in invalid or negative strip heights.
- Not applying the modulo operation at every intermediate addition and multiplication step, leading to overflow.
- Using a naive grid-based simulation which results in Time Limit Exceeded (TLE) due to the large coordinate range ($10^9$).

## Complexity Analysis
- Time: $O(N^3)$ or $O(N^2 \log N)$ where $N$ is the number of rectangles. Extracting and sorting $y$-coordinates takes $O(N \log N)$. There are $2N$ unique $y$-coordinates, creating about $2N$ bands. For each band, we iterate through all $N$ rectangles and potentially sort/process them, yielding an overall polynomial complexity well within the limits for $N \le 200$.
- Space: $O(N)$ to store the unique $y$-coordinates and coordinate lists.

## Commented Code
```java
class Solution {
    // Define the modulo constant as specified in the problem requirements
    static int MOD = 1_000_000_007;
    
    public int rectangleArea(int[][] rects) {
        // Initialize the total accumulated area to 0 using a 64-bit integer to prevent overflow
        long ans = 0;
        
        // Extract all unique y-coordinates from the bottom and top edges of all rectangles
        List<Integer> ys = new ArrayList<>();
        for (int[] rect : rects) {
            ys.add(rect[1]); // Add bottom-left y
            ys.add(rect[3]); // Add top-right y
        }
        
        // Sort the y-coordinates to create contiguous horizontal bands
        Collections.sort(ys);
        
        // Iterate through each adjacent pair of y-coordinates to form horizontal strips
        for (int i = 0; i < ys.size() - 1; i++) {
            // Get the bottom boundary of the current horizontal strip
            int y = ys.get(i);
            
            // Skip zero-height strips (duplicate y-coordinates)
            if (y == ys.get(i + 1)) continue;
            
            // Track the rightmost x-coordinate covered so far in this strip to merge overlaps
            int xFilled = -1;
            long width = 0;
            
            // Find all rectangles that completely cover this vertical strip [y, ys.get(i+1)]
            for (int[] rect : rects) {
                // Check if the rectangle spans across the current y interval
                if (rect[1] <= y && y < rect[3]) {
                    // Calculate the effective left boundary, avoiding overlapping regions with previous rectangles
                    int left = Math.max(rect[0], xFilled);
                    int right = rect[2];
                    
                    // If the current rectangle is entirely to the left or overlaps completely, skip extra contribution
                    if (right <= left) continue;
                    
                    // Add the unique width contribution
                    width += right - left;
                    
                    // Update the furthest right boundary covered so far
                    xFilled = right;
                }
            }
            
            // Calculate the height of the current horizontal strip
            long height = ys.get(i + 1) - y;
            
            // Multiply strip width by height, add to total answer, and apply modulo
            ans = (ans + (width * height) % MOD) % MOD;
        }
        
        // Cast the final result back to an integer and return
        return (int) (ans % MOD);
    }
}
```

## Interview Tips
- Clarify the constraints on rectangle coordinates early; when coordinates reach up to $10^9$, explicitly state why direct 2D array allocation or brute-force pixel counting is impossible.
- Discuss alternative approaches, such as a Segment Tree with Sweep-Line, if the interviewer pushes for a more optimal $O(N \log N)$ time complexity.
- Walk through a small test case manually to show how the $y$-coordinate slicing isolates overlapping areas correctly.
- Be proactive about potential integer overflow issues during multiplication and state how modulo arithmetic mitigates them.

## Revision Checklist
- [ ] Understand why direct grid representation fails (coordinate range $10^9$).
- [ ] Know how to extract and sort unique $y$-coordinates to form horizontal strips.
- [ ] Implement interval merging using an `xFilled` pointer to prevent double-counting overlaps.
- [ ] Apply modulo arithmetic `1_000_000_007` at every addition and multiplication step.
- [ ] Handle duplicate coordinates and zero-height strips properly.

## Similar Problems
- `Covered Intervals`
- `The Skyline Problem`
- `Employee Free Time`
- `Merge Intervals`

## Tags
`Array` `Segment Tree` `Line Sweep` `Sorting` `Geometry`
