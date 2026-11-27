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
Determine if a collection of sub-rectangles precisely covers a larger bounding rectangle without overlaps or gaps.
Solve it by tracking total area summation and using a corner-parity hash set to ensure all intermediate corners cancel out except the four bounding corners.

## Intuition
The "aha moment" is that a set of rectangles can form a perfect non-overlapping bounding rectangle if and only if two geometric conditions are met:
1. The sum of the areas of all small rectangles must equal the area of the large bounding rectangle formed by the overall minimum and maximum coordinates.
2. Every internal corner must be shared by an even number of rectangles (ideally 2 or 4), meaning its occurrences cancel out when added and removed from a set. Only the four extreme corners of the bounding box should appear an odd number of times (exactly once).

## Algorithm
1. Initialize global bounding variables for the minimum x, minimum y, maximum x, and maximum y to extreme limits, along with a HashSet to track corner coordinates.
2. Iterate through each rectangle in the input array. For each rectangle, compute its area and add it to a running total sum.
3. While processing each rectangle, update the global bounding coordinates ($l, b, r, t$) and toggle its four corners in the HashSet (add if missing, remove if present).
4. After processing all rectangles, verify that the size of the corner HashSet is exactly equal to 4.
5. Generate the four corner keys of the global bounding box and verify that all of them exist within the corner HashSet.
6. Compute the area of the global bounding box and compare it against the total accumulated area of all small rectangles. Return true if and only if areas match and all corner conditions are satisfied.

## Concept to Remember
- Geometry and Area Parity: Using coordinate geometry to validate shape composition.
- Hash Set Parity Tracking: Utilizing symmetric difference (adding/removing from a set) to track point frequencies in $O(1)$ time.
- Bounding Box Aggregation: Dynamically expanding global bounds while processing streaming/iterative geometric elements.

## Common Mistakes
- Forgetting that overlapping rectangles might still pass the area check if there are corresponding gaps elsewhere.
- Failing to handle negative coordinates properly (though standard String keys handle them naturally, numerical edge cases can cause bugs).
- Incorrectly assuming that having 4 corners in the set is enough without verifying that they match the extreme bounding box corners.
- Mixing up $x$ and $y$ indices when extracting coordinates from the rectangle arrays.

## Complexity Analysis
- Time: $O(N)$ - where $N$ is the number of rectangles, since we iterate through the list once and perform constant-time operations for each rectangle and its four corners.
- Space: $O(N)$ - to store the corner coordinates in the HashSet, which at most holds proportional to $4N$ entries.

## Commented Code
```java
class Solution {
    // HashSet to keep track of the corner points using XOR-like parity toggling.
    HashSet<String> corners = new HashSet<>();
    // Global variables to track the bounding box: bottom (b), left (l), top (t), right (r).
    int b = Integer.MAX_VALUE, l = Integer.MAX_VALUE, t = Integer.MIN_VALUE, r = Integer.MIN_VALUE;
    
    public boolean isRectangleCover(int[][] rectangles) {
        // Step 1: Calculate the sum of areas of all individual rectangles and update bounds/corners.
        int sumTotal = sumOfAllRect(rectangles);
        // Step 2: Calculate the area of the large bounding box formed by the extreme coordinates.
        int sumOfParent = getRectArea(new int[]{l, b, r, t}, false);
        // Step 3: Get the four required extreme corner keys of the bounding box.
        String[] parentKeys = getKeys(new int[]{l, b, r, t});  
        // Step 4: If the corner set does not have exactly 4 points remaining, it's invalid.
        if (corners.size() != 4) return false;
        // Step 5: Verify that the 4 points in the corner set are precisely the corners of the bounding box.
        for (String key : parentKeys) if (!corners.contains(key)) return false;
        // Step 6: Verify that the sum of all small rectangle areas equals the bounding box area.
        return sumTotal == sumOfParent;
    }

    private int sumOfAllRect(int[][] rectangles) {
        int sum = 0;
        // Iterate through every rectangle to accumulate area and update global structure.
        for (int[] rectangle : rectangles) sum += getRectArea(rectangle, true);
        return sum;
    }

    private int getRectArea(int[] rect, boolean updateAndCheck) { 
        // If flag is true, update global bounding box bounds and process its corners.
        if (updateAndCheck) updateEndsAndCheckCorners(rect);
        // Calculate height (y2 - y1).
        int len = rect[3] - rect[1];
        // Calculate width (x2 - x0).
        int wid = rect[2] - rect[0];
        // Return the area of the rectangle.
        return len * wid;
    }

    private void updateEndsAndCheckCorners(int[] rect) {
        int x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
        // Expand the global bounding box coordinates.
        l = Math.min(l, x1); b = Math.min(b, y1); r = Math.max(r, x2); t = Math.max(t, y2); 
        
        // Get the four corners of the current rectangle.
        String[] keys = getKeys(rect);
        // Toggle each corner in the HashSet: add if absent, remove if already present (canceling shared internal corners).
        for (String key : keys) {
            if (corners.contains(key)) {
                corners.remove(key);
            } else {
                corners.add(key);
            }
        }
    }
    
    private String[] getKeys(int[] rect) {
        // Return string representations of the four corners (bottom-left, top-left, bottom-right, top-right).
        return new String[]{getKey(rect[0], rect[1]), getKey(rect[0], rect[3]), getKey(rect[2], rect[1]), getKey(rect[2], rect[3])};
    }

    private String getKey(int x, int y) {
        // Format coordinates into a unique string key.
        return x + "," + y;
    }
}
```

## Interview Tips
- Clearly explain the dual-condition requirement upfront: area matching plus corner parity cancellation.
- Walk through a small manual example (e.g., four small adjacent squares forming a larger square) to show how internal corners cancel out.
- Discuss space-time trade-offs, mentioning why the HashSet approach is optimal compared to a full grid representation or an interval tree.

## Revision Checklist
- [ ] Understand the area-matching invariant for disjoint covering rectangles.
- [ ] Grasp the corner cancellation logic using a set (odd vs even occurrences).
- [ ] Know how to maintain global bounding box coordinates dynamically.
- [ ] Be able to implement coordinate string key generation without syntax errors.

## Similar Problems
- `Rectangle Area`
- `Covered Intervals`
- `Range Module`

## Tags
`Array` `Hash Table` `Math` `Geometry` `Line Sweep`
