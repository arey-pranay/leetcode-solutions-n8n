# Perfect Rectangle

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Geometry` `Sweep Line`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    HashSet<String> corners = new HashSet<>();
    int b=Integer.MAX_VALUE,l=Integer.MAX_VALUE,t=Integer.MIN_VALUE,r=Integer.MIN_VALUE;
    
    public boolean isRectangleCover(int[][] rectangles) {
        int sumTotal =  sumOfAllRect(rectangles);
        int sumOfParent = getRectArea(new int[]{l,b,r,t},false);
        String[] parentKeys = getKeys(new int[]{l,b,r,t});  
        if(corners.size() != 4) return false;
        for(String key : parentKeys) if(!corners.contains(key)) return false;
        return sumTotal == sumOfParent;
    }
    private int sumOfAllRect(int[][] rectangles){
        int sum =0;
        for(int[] rectangle : rectangles) sum += getRectArea(rectangle,true);
        return sum;
    }
    private int getRectArea(int[] rect, boolean updateAndCheck){ 
        if(updateAndCheck) updateEndsAndCheckCorners(rect);
        int len = rect[3]-rect[1];
        int wid = rect[2]-rect[0];
        return len*wid;
    }
    private void updateEndsAndCheckCorners(int[] rect){
        int x1 = rect[0],  y1 = rect[1],  x2=rect[2],  y2=rect[3];
        l = Math.min(l, x1); b = Math.min(b,y1);r = Math.max(r, x2); t = Math.max(t,y2); 
        
        String[] keys = getKeys(rect);
        for(String key : keys) if(corners.contains(key)) corners.remove(key); else corners.add(key);
    }
    
    private String[] getKeys(int[] rect){
        return new String[]{getKey(rect[0],rect[1]),getKey(rect[0],rect[3]),getKey(rect[2],rect[1]),getKey(rect[2],rect[3])};
    }
    private String getKey(int x, int y){
        return x+","+y;
    }
}
```

---

---
## Quick Revision
Determine if a collection of axis-aligned rectangles exactly covers a larger bounding rectangle without overlapping.
Check that the total area equals the bounding box area and that internal corners cancel out while the exact 4 outer corners remain.

## Intuition
The "aha moment" comes from realizing that a large bounding rectangle can be formed by smaller rectangles if and only if two invariant conditions are met:
1. The total area of all small rectangles must equal the area of the large bounding rectangle formed by the overall minimum and maximum coordinates.
2. Every internal corner or edge meeting point must be shared an even number of times so that internal vertices cancel out completely, leaving behind *only* the four corners of the bounding rectangle. Using a hash set to toggle corner points handles this vertex parity elegantly.

## Algorithm
1. Initialize bounding box coordinates (`l` and `b` to `Integer.MAX_VALUE`, `r` and `t` to `Integer.MIN_VALUE`) and a `HashSet` to track corner frequencies.
2. Iterate through each rectangle in the input array.
3. For each rectangle, compute its area and add it to a running total sum.
4. Update the global bounding box coordinates using the current rectangle's coordinates.
5. Generate the four corner strings for the current rectangle and toggle them in the corner hash set (add if not present, remove if already present).
6. After processing all rectangles, verify that the corner hash set contains exactly 4 elements and that these elements match the four corners of the global bounding box.
7. Finally, check if the sum of areas of all small rectangles equals the area of the global bounding box. Return true if all checks pass, otherwise false.

## Concept to Remember
- **Vertex Parity & Cancellation:** Interior vertices shared by multiple rectangles cancel out when toggled an even number of times.
- **Area Matching:** Validating area alone is insufficient (as overlapping regions could match a gap elsewhere), but combined with topological corner verification, it becomes a complete check.
- **Bounding Box Tracking:** Dynamically maintaining overall min/max bounds defines the hypothetical target rectangle.

## Common Mistakes
- Relying solely on area comparison, which fails if valid area coverage is offset by overlapping and missing regions.
- Forgetting to handle cases where the 4 corners of the bounding box appear an odd number of times or fail parity checks due to overlapping edges.
- Incorrectly initializing bounding box minimums and maximums, leading to flawed area calculations.
- Using integer overflow when calculating large rectangle areas (though typically bounded within standard constraints).

## Complexity Analysis
- Time: $O(N)$ - We iterate through the list of $N$ rectangles once, performing constant time operations per rectangle for hash set updates and coordinate comparisons.
- Space: $O(N)$ - The hash set stores the corners of the rectangles, which in the worst case scales linearly with the number of rectangles $N$.

## Commented Code
```java
class Solution {
    // HashSet to keep track of rectangle corners using XOR-like parity toggling
    HashSet<String> corners = new HashSet<>();
    // Initialize global bounding box boundaries (left, bottom, top, right)
    int b = Integer.MAX_VALUE, l = Integer.MAX_VALUE, t = Integer.MIN_VALUE, r = Integer.MIN_VALUE;
    
    public boolean isRectangleCover(int[][] rectangles) {
        // Calculate the sum of areas of all individual small rectangles
        int sumTotal = sumOfAllRect(rectangles);
        // Calculate the area of the theoretical bounding box formed by global min/max
        int sumOfParent = getRectArea(new int[]{l, b, r, t}, false);
        // Generate the four expected corner keys of the bounding box
        String[] parentKeys = getKeys(new int[]{l, b, r, t});  
        // If the number of remaining corners in the set is not exactly 4, it's invalid
        if (corners.size() != 4) return false;
        // Verify that all four corners of the bounding box exist in our corner set
        for (String key : parentKeys) if (!corners.contains(key)) return false;
        // The total area of small rectangles must equal the bounding box area
        return sumTotal == sumOfParent;
    }

    // Helper method to aggregate areas of all rectangles
    private int sumOfAllRect(int[][] rectangles) {
        int sum = 0;
        for (int[] rectangle : rectangles) sum += getRectArea(rectangle, true);
        return sum;
    }

    // Helper method to compute area and trigger boundary/corner updates
    private int getRectArea(int[] rect, boolean updateAndCheck) { 
        if (updateAndCheck) updateEndsAndCheckCorners(rect);
        int len = rect[3] - rect[1]; // Height (y2 - y1)
        int wid = rect[2] - rect[0]; // Width (x2 - x1)
        return len * wid;
    }

    // Updates global min/max boundaries and toggles corner presence in the set
    private void updateEndsAndCheckCorners(int[] rect) {
        int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
        // Expand bounding box coordinates
        l = Math.min(l, x1); b = Math.min(b, y1); r = Math.max(r, x2); t = Math.max(t, y2); 
        
        // Get the four corners of the current rectangle
        String[] keys = getKeys(rect);
        // Toggle each corner: add if absent, remove if present (cancellation property)
        for (String key : keys) {
            if (corners.contains(key)) {
                corners.remove(key);
            } else {
                corners.add(key);
            }
        }
    }
    
    // Generates string representations for the four corners of a given rectangle
    private String[] getKeys(int[] rect) {
        return new String[]{
            getKey(rect[0], rect[1]), // Bottom-left
            getKey(rect[0], rect[3]), // Top-left
            getKey(rect[2], rect[1]), // Bottom-right
            getKey(rect[2], rect[3])  // Top-right
        };
    }

    // Converts coordinate pair into a unique String key for the hash set
    private String getKey(int x, int y) {
        return x + "," + y;
    }
}
```

## Interview Tips
- Clarify assumptions with the interviewer immediately, such as whether rectangles can be degenerate (zero width or height).
- Explain the dual nature of the solution: geometric (area matching) and topological (vertex parity). Interviewers love when you articulate why area alone isn't enough.
- Walk through a small manual example (e.g., four small quadrants making a larger square) to demonstrate how internal corners cancel out.
- Discuss alternative approaches, such as line-sweep algorithms, if spatial queries or custom rectangle overlaps are brought up.

## Revision Checklist
- [ ] Understand why corner parity cancellation works for non-overlapping tile checks.
- [ ] Know how to maintain global bounding box coordinates during iteration.
- [ ] Remember to check both area equality and exact 4-corner remaining set size.
- [ ] Explain time and space complexities clearly.

## Similar Problems
- `Rectangle Area`
- `Rectangle Area II`
- `Covered Intervals`

## Tags
`Array` `Hash Map` `Geometry` `Line Sweep`
