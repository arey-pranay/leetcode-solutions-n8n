# Max Points On A Line

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Geometry`  
**Time:** O(N^2)  
**Space:** O(N)

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
The solution involves iterating through pairs of points, calculating slopes, and using a hash map to count points with the same slope.

## Intuition
The core idea is that if multiple points lie on the same line, they will all share the same slope with respect to a reference point. By fixing one point and calculating the slopes to all other points, we can group points that lie on the same line. A hash map is a natural choice to store and count these slopes efficiently. We also need to handle duplicate points and vertical lines as special cases.

## Algorithm
1. Handle edge cases: If there are 0, 1, or 2 points, return the number of points directly.
2. Initialize `result` to 0, which will store the maximum number of points found on any line.
3. Iterate through each point `i` in the `points` array. This point will serve as our reference point.
4. For each reference point `i`, initialize a `HashMap` called `map` to store slopes and their counts. The key will represent the slope, and the value will be the number of points that form that slope with point `i`.
5. Initialize `duplicate` to 1 to count points that are identical to point `i`.
6. Initialize `max` to 0, which will track the maximum number of points (excluding duplicates) that share a slope with point `i`.
7. Iterate through all other points `j` (where `j > i`) in the `points` array.
8. Calculate the difference in x-coordinates (`dx`) and y-coordinates (`dy`) between point `j` and point `i`.
9. If `dx` and `dy` are both 0, it means point `j` is a duplicate of point `i`. Increment `duplicate` and continue to the next point `j`.
10. Calculate the greatest common divisor (GCD) of `dx` and `dy` to simplify the slope representation. This is crucial to ensure that slopes like 2/4 and 1/2 are treated as the same.
11. Divide `dx` and `dy` by their GCD to get the simplified slope components.
12. Create a unique string key for the slope, e.g., `dx + "/" + dy`.
13. Update the count for this slope in the `map`. Use `map.getOrDefault(slope, 0) + 1` to handle cases where the slope is encountered for the first time.
14. Update `max` to be the maximum of its current value and the count of the current slope in the `map`.
15. After iterating through all points `j` for a given reference point `i`, the maximum number of points on a line passing through `i` is `max + duplicate`. Update `result` with the maximum of `result` and this value.
16. After iterating through all reference points `i`, `result` will hold the overall maximum number of points on any line. Return `result`.

## Concept to Remember
*   **Slope Calculation:** The fundamental concept is that points on the same line have the same slope relative to a common point.
*   **Greatest Common Divisor (GCD):** Used to normalize slopes, ensuring that equivalent fractional slopes (e.g., 1/2 and 2/4) are treated as identical.
*   **Hash Map for Counting:** Efficiently stores and retrieves counts of unique slopes.
*   **Handling Duplicates and Vertical Lines:** Special care is needed for points that are identical to the reference point and for lines that are perfectly vertical (infinite slope).

## Common Mistakes
*   **Not handling duplicate points:** If multiple points have the exact same coordinates, they should all be counted towards the line they belong to.
*   **Not simplifying slopes:** Failing to use GCD to simplify slopes can lead to incorrect counts (e.g., treating 1/2 and 2/4 as different slopes).
*   **Incorrectly handling vertical lines:** Vertical lines have an undefined slope (division by zero). This needs to be handled as a separate case.
*   **Integer overflow:** When calculating `dx` and `dy`, especially in the cross-multiplication method, intermediate values could potentially overflow if not careful. The GCD approach mitigates this by keeping numbers smaller.
*   **Off-by-one errors in loops or counts:** Ensuring all points are considered and counts are accurate is critical.

## Complexity Analysis
- Time: O(N^2) - The outer loop iterates N times, and the inner loop iterates N times. Inside the inner loop, GCD calculation takes O(log(max(dx, dy))), which is effectively constant for typical integer ranges. Hash map operations are O(1) on average.
- Space: O(N) - In the worst case, all points might have distinct slopes with respect to a reference point, requiring the hash map to store N-1 entries.

## Commented Code
```java
class Solution {
    // This method returns the maximum number of points that lie on the same line
    // given a set of points represented by the 2D array points
    public int maxPoints(int[][] points) {
        // n is the number of points in the array
        int n = points.length;
        
        // If there are 0 or 1 points, there is at most one line that can be formed
        // (i.e., the line formed by the single point, or no line if there are no points)
        // If there are 2 points, they always form a line, so the max is 2.
        if(n <= 2) return n;
        
        // Initialize the maximum number of points on a line found so far.
        // We initialize it to 0, as we will update it with counts.
        // The problem statement implies at least 2 points to form a line,
        // but the logic below will correctly find the max even if it's less than 2 initially.
        int ans = 0;
        
        // Iterate through each point in the array. This point will be our reference point.
        for(int i = 0 ;i < n; i++){
            // For each reference point 'i', we need to calculate slopes to all other points.
            // A HashMap is used to store the count of points for each unique slope.
            // The key will be a string representation of the simplified slope (e.g., "dx/dy").
            // The value will be the count of points forming that slope with point 'i'.
            HashMap<String, Integer> slopeCounts = new HashMap<>();
            
            // 'duplicate' counts points that are identical to the current reference point 'i'.
            // We start with 1 because point 'i' itself is one such point.
            int duplicate = 1;
            
            // 'maxPointsForCurrentRef' tracks the maximum number of points (excluding duplicates)
            // that lie on the same line passing through the current reference point 'i'.
            int maxPointsForCurrentRef = 0;
            
            // Iterate through all other points 'j' starting from the point after 'i'.
            // We only need to consider pairs (i, j) where j > i to avoid redundant calculations
            // and to ensure each line is considered once from a specific reference point.
            for(int j = i+1 ; j < n ; j++ ){                   
                // Calculate the difference in x and y coordinates between point 'j' and point 'i'.
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                // Check if point 'j' is a duplicate of point 'i'.
                if(dx == 0 && dy == 0){
                    // If they are duplicates, increment the duplicate count.
                    duplicate++;
                    // Continue to the next point 'j' as this point doesn't define a new slope.
                    continue;
                }
                
                // To uniquely identify a slope and handle cases like 1/2 and 2/4 as the same,
                // we simplify the dx and dy by dividing them by their greatest common divisor (GCD).
                // This ensures that slopes are represented in their simplest fractional form.
                int commonDivisor = gcd(dx, dy);
                dx /= commonDivisor;
                dy /= commonDivisor;
                
                // Create a unique string key for the slope.
                // This key will be used in the HashMap.
                String slopeKey = dx + "/" + dy;
                
                // Increment the count for this slope in the HashMap.
                // If the slope is not yet in the map, getOrDefault will return 0, and we add 1.
                slopeCounts.put(slopeKey, slopeCounts.getOrDefault(slopeKey, 0) + 1);
                
                // Update 'maxPointsForCurrentRef' with the maximum count seen so far for any slope
                // originating from point 'i'.
                maxPointsForCurrentRef = Math.max(maxPointsForCurrentRef, slopeCounts.get(slopeKey));
            }
            
            // After checking all points 'j' against the reference point 'i':
            // The total number of points on a line passing through 'i' with the most frequent slope
            // is 'maxPointsForCurrentRef' (points with that slope) + 'duplicate' (points identical to 'i').
            // We update the overall maximum 'ans' if this count is greater.
            ans = Math.max(ans, maxPointsForCurrentRef + duplicate);
        }   
        // Return the maximum number of points found on any single line.
        return ans;
    }
    
    // Helper function to calculate the Greatest Common Divisor (GCD) of two integers.
    // This is used to simplify fractions (slopes).
    private int gcd(int a, int b) {
        // Base case for recursion: if b is 0, then a is the GCD.
        if (b == 0) return a;
        // Recursive step: GCD(a, b) is the same as GCD(b, a % b).
        return gcd(b, a % b);
    }
}
```

## Interview Tips
1.  **Clarify Edge Cases:** Ask about scenarios with 0, 1, or 2 points, and what the expected output is. Also, inquire about duplicate points and collinear points.
2.  **Explain the Slope Logic:** Clearly articulate why calculating slopes is the key. Emphasize the need to normalize slopes using GCD to avoid floating-point precision issues and to correctly group equivalent slopes.
3.  **Discuss Duplicate Points:** Explain how duplicate points are handled separately and why they need to be added to the count of points on a line.
4.  **Handle Vertical Lines:** Be prepared to explain how vertical lines (infinite slope) are managed, either by using a special key in the hash map or by treating them as a separate case.
5.  **Complexity Justification:** Be ready to explain the time and space complexity of your chosen approach, justifying each part of the analysis.

## Revision Checklist
- [ ] Understand the problem: Find the maximum number of collinear points.
- [ ] Identify the core idea: Points on the same line share a common slope with a reference point.
- [ ] Choose a data structure: HashMap to store slope counts.
- [ ] Implement slope calculation: `(y2-y1) / (x2-x1)`.
- [ ] Handle floating-point issues: Use GCD to simplify `dx` and `dy` into a canonical form (e.g., string "dx/dy").
- [ ] Handle duplicate points: Count them separately and add to the final line count.
- [ ] Handle vertical lines: Use a distinct key (e.g., "inf") or a separate counter.
- [ ] Iterate through all possible reference points.
- [ ] For each reference point, iterate through all other points.
- [ ] Update the maximum count found.
- [ ] Consider edge cases (0, 1, 2 points).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Line Reflection
*   K-Similar Strings
*   Number of Lines Containing Characters
*   Max Points on a Line II (with floating point coordinates)

## Tags
`Array` `Hash Map` `Math` `Geometry`
