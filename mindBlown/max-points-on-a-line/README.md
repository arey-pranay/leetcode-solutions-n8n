# Max Points On A Line

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Geometry`  
**Time:** O(N^2 * log(max_coord)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int maxPoints(int[][] points) {
        // 12/8 = 1.5
        // 3/2 = 1.5
        if(points.length < 3) return points.length;
        
        int n = points.length;
        int max = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        
        for(int i =0;i<n;i++){
            HashMap<String,Integer> hm = new HashMap<>(); 
            for(int j=i+1;j<n;j++){
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int gcd = gcd(dx,dy);
                dx /= gcd;
                dy /= gcd;
                String slope = dy+"/"+dx; 
                hm.put(slope,hm.getOrDefault(slope,0)+1);
                max = Math.max(max, hm.get(slope));
            }
            ans = Math.max(ans,max+1);
        }
        return ans;

    }
    public int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
}

```

---

---
## Quick Revision
Given a set of points, find the maximum number of points that lie on the same straight line.
This is solved by iterating through each point, calculating the slope to all other points, and using a hash map to count occurrences of each slope.

## Intuition
The core idea is that if multiple points lie on the same line, they will share the same slope relative to a common reference point. By fixing one point and calculating the slopes to all other points, we can group points that lie on the same line originating from that fixed point. The maximum count for any slope, plus the fixed point itself, gives the maximum number of points on a line passing through that fixed point. We repeat this for every point and take the overall maximum.

## Algorithm
1. Handle edge cases: If there are fewer than 3 points, all points lie on a line, so return the number of points.
2. Initialize `maxPointsOnLine` to `Integer.MIN_VALUE` to store the overall maximum.
3. Iterate through each point `i` from `0` to `n-1` (where `n` is the total number of points). This point `i` will be our reference point.
4. For each reference point `i`, initialize a `HashMap<String, Integer>` called `slopeCounts`. This map will store the count of points that form a specific slope with point `i`. The key will be a string representation of the simplified slope, and the value will be its frequency.
5. Initialize `currentMaxForPointI` to `0` to track the maximum number of points on a line passing through point `i` (excluding point `i` itself for now).
6. Iterate through each other point `j` from `i+1` to `n-1`.
7. Calculate the difference in x-coordinates (`dx = points[j][0] - points[i][0]`) and y-coordinates (`dy = points[j][1] - points[i][1]`).
8. Handle vertical lines: If `dx` is 0, the slope is infinite. We can represent this with a special key like "inf".
9. Handle horizontal lines: If `dy` is 0, the slope is 0.
10. Calculate the greatest common divisor (GCD) of `dx` and `dy` to simplify the slope. This is crucial to ensure that slopes like 2/4 and 1/2 are treated as the same.
11. Divide both `dx` and `dy` by their GCD to get the simplified slope components.
12. Create a unique string key for the slope, e.g., `dy + "/" + dx`.
13. Increment the count for this `slopeKey` in the `slopeCounts` map. Use `getOrDefault` to handle cases where the slope is encountered for the first time.
14. Update `currentMaxForPointI` with the maximum count found so far for any slope originating from point `i`.
15. After iterating through all `j` for a fixed `i`, the maximum number of points on a line passing through point `i` is `currentMaxForPointI + 1` (adding point `i` itself).
16. Update `maxPointsOnLine` with the maximum between its current value and `currentMaxForPointI + 1`.
17. After iterating through all reference points `i`, return `maxPointsOnLine`.
18. Implement a helper function `gcd(int a, int b)` using the Euclidean algorithm.

## Concept to Remember
*   **Slope Calculation:** Understanding how to represent and compare slopes, especially handling vertical lines and simplifying fractions.
*   **Greatest Common Divisor (GCD):** Essential for simplifying slopes to their canonical form, ensuring that equivalent slopes are grouped together.
*   **Hash Maps for Frequency Counting:** Efficiently storing and retrieving counts of unique items (in this case, simplified slopes).
*   **Iterative Approach with Reference Point:** Fixing one point and analyzing relationships with others is a common strategy for geometric problems.

## Common Mistakes
*   **Not handling vertical lines:** Treating `dx = 0` as a division by zero error or not giving it a distinct representation.
*   **Not simplifying slopes:** Using raw `dy/dx` as the key in the hash map, leading to different keys for mathematically identical slopes (e.g., "2/4" vs. "1/2").
*   **Floating-point precision issues:** If using floating-point numbers for slopes, precision errors can lead to incorrect comparisons. Using integer arithmetic with GCD is preferred.
*   **Duplicate points:** The current solution implicitly handles duplicate points by treating them as distinct points that will form the same slope. However, if the problem statement implies unique points, a pre-processing step might be needed.
*   **Off-by-one errors:** Forgetting to add the reference point itself to the count of points on a line.

## Complexity Analysis
- Time: O(N^2 * log(max_coord)) or O(N^2) - The outer loop runs N times, and the inner loop runs N-1 times. Inside the inner loop, calculating GCD takes logarithmic time with respect to the magnitude of the coordinates. If we consider the GCD calculation as roughly constant time for typical integer ranges, the complexity is O(N^2).
- Space: O(N) - In the worst case, for a given reference point, all other N-1 points might form distinct slopes with it. The hash map will store up to N-1 entries.

## Commented Code
```java
class Solution {
    public int maxPoints(int[][] points) {
        // If there are less than 3 points, all points are on the same line.
        if(points.length < 3) return points.length;
        
        // Get the total number of points.
        int n = points.length;
        // Initialize max to store the maximum number of points found on any line so far.
        // We initialize it to MIN_VALUE because we will be adding 1 to counts later.
        int max = Integer.MIN_VALUE;
        // Initialize ans to store the final answer.
        int ans = Integer.MIN_VALUE;
        
        // Iterate through each point, considering it as a potential anchor point for a line.
        for(int i =0;i<n;i++){
            // Create a HashMap to store the counts of points forming specific slopes with the current anchor point 'i'.
            // The key will be a string representation of the simplified slope (dy/dx).
            // The value will be the count of points forming that slope.
            HashMap<String,Integer> hm = new HashMap<>(); 
            // Iterate through all other points 'j' to calculate the slope with the anchor point 'i'.
            // We start from j = i + 1 to avoid redundant calculations and comparing a point with itself.
            for(int j=i+1;j<n;j++){
                // Calculate the difference in x-coordinates between point 'j' and point 'i'.
                int dx = points[j][0] - points[i][0];
                // Calculate the difference in y-coordinates between point 'j' and point 'i'.
                int dy = points[j][1] - points[i][1];
                
                // Calculate the greatest common divisor (GCD) of dx and dy.
                // This is crucial for simplifying the slope to its canonical form.
                int commonDivisor = gcd(dx,dy);
                
                // Simplify dx and dy by dividing them by their GCD.
                dx /= commonDivisor;
                dy /= commonDivisor;
                
                // Create a unique string representation of the simplified slope.
                // This string will be used as the key in the HashMap.
                String slope = dy+"/"+dx; 
                
                // Increment the count for this slope in the HashMap.
                // If the slope is not yet in the map, getOrDefault will return 0, and we add 1.
                hm.put(slope,hm.getOrDefault(slope,0)+1);
                
                // Update 'max' with the maximum count found for any slope originating from point 'i' so far.
                max = Math.max(max, hm.get(slope));
            }
            // The maximum number of points on a line passing through point 'i' is the maximum count of any slope ('max') plus the anchor point 'i' itself (+1).
            // Update 'ans' with the overall maximum number of points found across all anchor points.
            // If no other points were found for point 'i' (i.e., max is still MIN_VALUE), this correctly handles it.
            // If max is 0 (meaning only point i exists or all other points are duplicates of i), max+1 will be 1.
            ans = Math.max(ans,max+1);
        }
        // Return the overall maximum number of points found on any single line.
        return ans;
    }
    
    // Helper function to calculate the Greatest Common Divisor (GCD) using the Euclidean algorithm.
    public int gcd(int a, int b){
        // Base case: if b is 0, then a is the GCD.
        if(b==0) return a;
        // Recursive step: GCD(a, b) is the same as GCD(b, a % b).
        return gcd(b,a%b);
    }
}
```

## Interview Tips
*   **Clarify Edge Cases:** Ask about duplicate points, collinear points, and the minimum number of points.
*   **Explain GCD's Importance:** Emphasize why simplifying slopes using GCD is critical to avoid incorrect counts.
*   **Walk Through an Example:** Use a small set of points (e.g., 3-4 points) to manually trace the algorithm, showing how slopes are calculated, simplified, and stored in the hash map.
*   **Discuss Alternatives (Briefly):** Mention that other approaches might exist but highlight why the chosen hash map approach is efficient and practical.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Identify the core idea: points on a line share a slope.
- [ ] Implement slope calculation, handling vertical lines.
- [ ] Implement GCD for slope simplification.
- [ ] Use a HashMap to count slope frequencies.
- [ ] Correctly add the anchor point to the count.
- [ ] Handle edge cases (0, 1, 2 points).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Line Reflection
*   K-th Smallest Pair Distance
*   Max Number of Balloons

## Tags
`Array` `Hash Map` `Math` `Geometry` `GCD`
