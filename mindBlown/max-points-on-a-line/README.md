# Max Points On A Line

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Geometry`  
**Time:** O(n^2)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    // This method returns the maximum number of points that lie on the same line
    // given a set of points represented by the 2D array points
    public int maxPoints(int[][] points) {
        // n is the number of points in the array
        int n = points.length;
        
        // If there are 0 or 1 points, there is at most one line that can be formed
        // (i.e., the line formed by the single point, or no line if there are no points)
        if(n <= 2) return n;
        
        // Initialize the maximum number of points on a line to 2, since there must be at least 2 points to form a line
        int ans = 2;
        
        // Iterate through all pairs of points
        for(int i = 0 ;i < n; i++){
            for(int j = i+1; j < n ; j++){
                // temp is the number of points on the line formed by point i and point j
                int temp = 2;
                // Check if any other points are on the same line as point i and point j
                for(int k = j+1 ; k<n ; k++ ){                   
                    // Check if point k is on the same line as point i and point j
                    // This is done by checking if the slope between point i and point k is equal to the slope between point i and point j
                    int x = (points[j][1] - points[i][1]) * (points[k][0] - points[i][0]);
                    int y = (points[k][1] - points[i][1]) * (points[j][0] - points[i][0]);
                    if(x == y){
                        // If the slopes are equal, point k is on the same line as point i and point j
                        temp++;
                    }
                }
                // Update the maximum number of points on a line if necessary
                if(temp > ans){
                    ans = temp;
                }
            }
        }   
        // Return the maximum number of points on a line
        return ans;
    }
}

// class Solution {
//     public int maxPoints(int[][] points) {
//         if (points.length <= 2) return points.length;

//         int result = 0;

//         for (int i = 0; i < points.length; i++) {
//             HashMap<String, Integer> map = new HashMap<>();
//             int duplicate = 1;
//             int max = 0;

//             for (int j = i + 1; j < points.length; j++) {
//                 int dx = points[j][0] - points[i][0];
//                 int dy = points[j][1] - points[i][1];

//                 if (dx == 0 && dy == 0) {
//                     duplicate++;
//                     continue;
//                 }

//                 int gcd = gcd(dx, dy);
//                 dx /= gcd;
//                 dy /= gcd;

//                 String slope = dx + "/" + dy;
//                 map.put(slope, map.getOrDefault(slope, 0) + 1);

//                 max = Math.max(max, map.get(slope));
//             }

//             result = Math.max(result, max + duplicate);
//         }

//         return result;
//     }

//     private int gcd(int a, int b) {
//         if (b == 0) return a;
//         return gcd(b, a % b);
//     }
// }
```

---

---
## Quick Revision
This problem asks for the maximum number of points that lie on the same straight line.
We solve it by iterating through each point, calculating slopes with all other points, and using a hash map to count points with the same slope.

## Intuition
The core idea is that if multiple points lie on the same line, they will all share the same slope with respect to a reference point. By fixing one point and calculating the slopes to all other points, we can group points that fall on the same line originating from that reference point. We then find the maximum count across all reference points.

## Algorithm
1. Handle edge cases: If there are 0, 1, or 2 points, return the number of points directly.
2. Initialize `maxPointsOnLine` to 0.
3. Iterate through each point `p1` in the `points` array (outer loop, index `i`). This point will serve as our reference point.
4. For each `p1`, initialize a `HashMap` called `slopeCounts` to store the frequency of each unique slope.
5. Initialize `duplicatePoints` to 1 (to count `p1` itself).
6. Iterate through all other points `p2` in the `points` array (inner loop, index `j`, starting from `i + 1`).
7. Calculate the difference in x-coordinates (`dx`) and y-coordinates (`dy`) between `p2` and `p1`.
8. If `dx` and `dy` are both 0, it means `p2` is a duplicate of `p1`. Increment `duplicatePoints` and continue to the next `p2`.
9. To represent the slope uniquely and avoid floating-point precision issues, calculate the greatest common divisor (GCD) of `dx` and `dy`.
10. Divide both `dx` and `dy` by their GCD to get the simplified slope components.
11. Create a unique string key for the slope, e.g., `dx + "/" + dy`.
12. Increment the count for this `slopeKey` in the `slopeCounts` map. Use `getOrDefault` to handle new slopes.
13. Keep track of the maximum count of points for any single slope originating from `p1` in a variable `currentMaxSlopeCount`.
14. After iterating through all `p2` for a given `p1`, the maximum number of points on a line passing through `p1` is `currentMaxSlopeCount + duplicatePoints`.
15. Update `maxPointsOnLine` with the maximum value found so far.
16. After the outer loop finishes, return `maxPointsOnLine`.

## Concept to Remember
*   **Slope Calculation:** The slope between two points (x1, y1) and (x2, y2) is (y2 - y1) / (x2 - x1).
*   **Handling Vertical Lines:** When x1 = x2, the slope is infinite. This needs special handling (e.g., a distinct key in the map).
*   **Floating-Point Precision:** Using floating-point numbers for slopes can lead to precision errors. Representing slopes as simplified fractions (using GCD) is a robust approach.
*   **Duplicate Points:** Points that are identical to the reference point must be accounted for separately as they lie on *any* line passing through the reference point.

## Common Mistakes
*   **Floating-point precision issues:** Directly using `double` for slopes can lead to incorrect comparisons due to minor floating-point inaccuracies.
*   **Not handling vertical lines:** Failing to account for lines where `dx` is 0 (infinite slope) will miss valid lines.
*   **Not handling duplicate points:** Forgetting to count points that are identical to the reference point will lead to undercounting.
*   **Inefficient slope representation:** Using `dx/dy` directly without simplifying via GCD can lead to many different keys for the same slope (e.g., 2/4 and 1/2).
*   **Incorrect loop bounds or logic:** Off-by-one errors or incorrect iteration patterns can lead to missing pairs or double-counting.

## Complexity Analysis
- Time: O(n^2) - The outer loop runs `n` times, and the inner loop runs `n-1` times. Inside the inner loop, GCD calculation takes O(log(max(dx, dy))), which is effectively constant for typical integer ranges. Hash map operations (put, get) are O(1) on average.
- Space: O(n) - In the worst case, all points might have distinct slopes with respect to a reference point, requiring `n-1` entries in the hash map.

## Commented Code
```java
class Solution {
    // This method returns the maximum number of points that lie on the same line
    // given a set of points represented by the 2D array points.
    public int maxPoints(int[][] points) {
        // n is the number of points in the array.
        int n = points.length;
        
        // If there are 0, 1, or 2 points, the maximum number of points on a line is simply n.
        // For 0 or 1 point, it's trivial. For 2 points, they always form a line.
        if(n <= 2) return n;
        
        // Initialize the maximum number of points found on any line so far.
        // We initialize to 0, and will update it as we find lines.
        // The minimum possible answer for n > 2 is 2, but we'll find it through iteration.
        int maxPointsOnLine = 0;
        
        // Iterate through each point in the array. This point will be our reference point (p1).
        for(int i = 0 ; i < n; i++){
            // For each reference point 'i', we need to calculate slopes with all other points.
            // A HashMap is used to store the count of points for each unique slope.
            // The key will represent the slope, and the value will be the count of points on that slope.
            // We use a HashMap<String, Integer> where the String is a representation of the slope (e.g., "dx/dy").
            HashMap<String, Integer> slopeCounts = new HashMap<>();
            
            // 'duplicatePoints' counts how many points are identical to the current reference point 'i'.
            // We start with 1 to include the reference point itself.
            int duplicatePoints = 1;
            
            // 'currentMaxSlopeCount' tracks the maximum number of points on a line (excluding duplicates)
            // that share the same slope with the current reference point 'i'.
            int currentMaxSlopeCount = 0;
            
            // Iterate through all other points (p2) starting from the next point after 'i'.
            // We only need to consider pairs (i, j) where j > i to avoid redundant calculations and self-comparisons.
            for(int j = i + 1 ; j < n ; j++ ){                   
                // Calculate the difference in x-coordinates (dx) and y-coordinates (dy) between point j and point i.
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                // Check if point 'j' is a duplicate of point 'i'.
                if(dx == 0 && dy == 0){
                    // If they are duplicates, increment the count of duplicate points.
                    duplicatePoints++;
                    // Continue to the next point 'j' as this duplicate doesn't define a new slope.
                    continue;
                }
                
                // To avoid floating-point precision issues and to represent slopes uniquely,
                // we simplify the fraction dy/dx by dividing both by their greatest common divisor (GCD).
                // First, calculate the GCD of dx and dy.
                int commonDivisor = gcd(dx, dy);
                
                // Simplify dx and dy by dividing them by their GCD.
                dx /= commonDivisor;
                dy /= commonDivisor;
                
                // Create a unique string key for the simplified slope.
                // This key will be used in the HashMap. For example, "2/3" or "-1/1".
                String slopeKey = dx + "/" + dy;
                
                // Get the current count for this slope from the map, or 0 if it's a new slope.
                // Then, increment the count by 1 (for point 'j') and put it back into the map.
                slopeCounts.put(slopeKey, slopeCounts.getOrDefault(slopeKey, 0) + 1);
                
                // Update 'currentMaxSlopeCount' if the count for the current slope is greater than the maximum found so far for this reference point 'i'.
                currentMaxSlopeCount = Math.max(currentMaxSlopeCount, slopeCounts.get(slopeKey));
            }
            
            // After checking all other points 'j' for the current reference point 'i':
            // The total number of points on the line with the most points passing through 'i' is
            // the maximum count of points with a specific slope ('currentMaxSlopeCount')
            // PLUS all the duplicate points ('duplicatePoints') that were identical to 'i'.
            // Update the overall maximum number of points on a line found so far.
            maxPointsOnLine = Math.max(maxPointsOnLine, currentMaxSlopeCount + duplicatePoints);
        }   
        
        // Return the overall maximum number of points found on any single line.
        return maxPointsOnLine;
    }
    
    // Helper function to calculate the Greatest Common Divisor (GCD) of two integers using the Euclidean algorithm.
    // This is crucial for simplifying slopes and ensuring unique representation.
    private int gcd(int a, int b) {
        // Base case: if b is 0, then a is the GCD.
        if (b == 0) return a;
        // Recursive step: GCD(a, b) is the same as GCD(b, a % b).
        return gcd(b, a % b);
    }
}
```

## Interview Tips
1.  **Clarify Edge Cases:** Always ask about constraints: what if there are 0, 1, or 2 points? What about duplicate points? This shows you're thinking about robustness.
2.  **Explain the Slope Representation:** Emphasize why using GCD to simplify slopes and representing them as strings (e.g., "dx/dy") is better than using floating-point numbers to avoid precision errors.
3.  **Handle Duplicates Explicitly:** Clearly explain how duplicate points are handled separately and why they are added to the `currentMaxSlopeCount` at the end. This is a common pitfall.
4.  **Walk Through an Example:** Use a small set of points (e.g., 3-4 points) to manually trace your algorithm. Show how the map is populated and how `maxPointsOnLine` is updated.

## Revision Checklist
- [ ] Understand the problem: Find the maximum number of collinear points.
- [ ] Identify the core idea: Points on the same line share the same slope with a reference point.
- [ ] Choose a robust slope representation: Use GCD to simplify `dx/dy` and store as a string.
- [ ] Handle vertical lines: The GCD approach naturally handles `dx=0` if `dy` is non-zero.
- [ ] Handle duplicate points: Count them separately and add to the final line count.
- [ ] Implement the nested loops correctly: Outer loop for reference point, inner loop for other points.
- [ ] Use a HashMap to store slope frequencies.
- [ ] Update the overall maximum correctly.
- [ ] Consider edge cases (n <= 2).
- [ ] Implement the GCD helper function.

## Similar Problems
- LeetCode 149: Max Points On A Line (This problem)
- LeetCode 1232: Check If All the Integers in a Range Are Included
- LeetCode 179: Largest Number (uses string comparison and sorting, related to ordering)

## Tags
`Array` `Hash Map` `Math` `Geometry` `GCD`
