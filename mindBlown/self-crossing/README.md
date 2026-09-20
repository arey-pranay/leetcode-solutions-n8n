# Self Crossing

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Math` `Geometry`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    int[] arr;
    public boolean isSelfCrossing(int[] d) {
        int n = d.length;
        arr=d;
        if(n<=3) return false;
        // current line (i) can collide with i-3, i-4, i-5
        for(int i=3;i<n;i++) if(touches3(i) || (i>3 && touches4(i)) || (i>4 && touches5(i))) return true;
        return false;
    }
    public boolean touches3(int i){
        return arr[i] >= arr[i-2] && arr[i-1] <= arr[i-3];
    }
    public boolean touches4(int i){
        return arr[i-1]==arr[i-3] && arr[i] >= arr[i-2] - arr[i-4];
    }
    public boolean touches5(int i){
        return arr[i-1] <= arr[i-3] 
        && arr[i-2] >= arr[i-4] 
        && arr[i] >= arr[i-2] - arr[i-4] 
        && arr[i-1] >= arr[i-3] - arr[i-5];
    }
}





    // for(int i=3;i<n;i++){
        //     if( (distance[i] >= distance[i-2]) && (distance[i-1]<=distance[i-3]) ) return true;        
        // }
        
        // while(i<n && (d[i] > d[i-2])) i++;
        // if(i==n) return false;
        // //if i is going fdown, then i-2 was going up, and i-4 was going down
        
        // if(d[i] >= (d[i-2] d[i-4])) i++;
        
        // else if(d[i-1] <= d[i-3]) return true;
```

---

---
## Quick Revision
The problem asks if a path defined by a sequence of line segments will cross itself.
We solve this by analyzing the geometric conditions for a crossing based on the lengths of consecutive segments.

## Intuition
The core idea is to realize that a self-crossing can only occur when the path starts to "turn inwards". This means that a new segment must be shorter than or equal to a segment two steps back, and the segment before the new one must be shorter than or equal to the segment three steps back. We can then analyze specific geometric configurations for crossings involving segments 3, 4, and 5 steps back.

## Algorithm
1. Handle the base case: If the number of segments `n` is less than or equal to 3, no crossing is possible, so return `false`.
2. Iterate through the segments starting from the 4th segment (index `i = 3`).
3. For each segment `i`, check for three types of potential crossings:
    a. **Crossing with segment `i-3`**: This happens if the current segment `arr[i]` is long enough to reach back to or past the line segment `arr[i-2]`, and the previous segment `arr[i-1]` is short enough to not go past the line segment `arr[i-3]`. The condition is `arr[i] >= arr[i-2] && arr[i-1] <= arr[i-3]`.
    b. **Crossing with segment `i-4`**: This is a more specific case that occurs when the path forms a "rectangle" or a similar shape. It happens if `arr[i-1]` is exactly equal to `arr[i-3]` (forming parallel lines) and `arr[i]` is long enough to reach back to or past the line segment `arr[i-2]` minus the length of `arr[i-4]` (to account for the inward turn). The condition is `arr[i-1] == arr[i-3] && arr[i] >= arr[i-2] - arr[i-4]`. This check is only performed if `i > 3`.
    c. **Crossing with segment `i-5`**: This is the most complex case, involving a more intricate inward spiral. It occurs if `arr[i-1]` is shorter than or equal to `arr[i-3]`, `arr[i-2]` is longer than or equal to `arr[i-4]`, `arr[i]` is long enough to reach back to or past `arr[i-2]` minus `arr[i-4]`, and `arr[i-1]` is long enough to reach back to or past `arr[i-3]` minus `arr[i-5]`. The condition is `arr[i-1] <= arr[i-3] && arr[i-2] >= arr[i-4] && arr[i] >= arr[i-2] - arr[i-4] && arr[i-1] >= arr[i-3] - arr[i-5]`. This check is only performed if `i > 4`.
4. If any of these crossing conditions are met for any `i`, return `true`.
5. If the loop completes without finding any crossings, return `false`.

## Concept to Remember
*   **Geometric Reasoning**: The problem requires translating geometric conditions of line segment intersections into algebraic inequalities.
*   **Edge Cases and Pattern Recognition**: Identifying specific patterns of segment lengths that lead to crossings (e.g., "rectangle" or "spiral" like turns).
*   **Array Indexing and Bounds**: Careful handling of array indices to avoid `ArrayIndexOutOfBoundsException` when accessing previous segments.

## Common Mistakes
*   **Missing Geometric Cases**: Not considering all possible configurations of segment lengths that can lead to a self-intersection. The three cases (i-3, i-4, i-5) are crucial.
*   **Incorrect Inequality Logic**: Flipping the direction of inequalities or misinterpreting the conditions for segments "reaching back" or "not going past".
*   **Off-by-One Errors**: Incorrectly handling the starting index of the loop or the indices of the segments being compared.
*   **Not Handling Small `n`**: Forgetting to check for `n <= 3` as a base case where no crossing is possible.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the array of distances once.
- Space: O(1) - reason: We only use a few variables to store indices and lengths, not dependent on input size.

## Commented Code
```java
class Solution {
    // Declare an instance variable to store the input array of distances.
    int[] arr;
    
    // Main method to determine if the path self-crosses.
    public boolean isSelfCrossing(int[] d) {
        // Get the total number of line segments.
        int n = d.length;
        // Assign the input array to the instance variable for easy access in helper methods.
        arr = d;
        
        // If there are 3 or fewer segments, a self-crossing is impossible.
        if (n <= 3) return false;
        
        // Iterate through the segments starting from the 4th segment (index 3).
        // A crossing can only occur with segments at least 3 steps back.
        for (int i = 3; i < n; i++) {
            // Check for a crossing with the segment 3 steps back (i-3).
            // This is the most common type of crossing.
            if (touches3(i)) return true;
            
            // Check for a crossing with the segment 4 steps back (i-4).
            // This condition is only checked if we are at least at the 5th segment (i > 3).
            if (i > 3 && touches4(i)) return true;
            
            // Check for a crossing with the segment 5 steps back (i-5).
            // This is a more complex inward spiral crossing.
            // This condition is only checked if we are at least at the 6th segment (i > 4).
            if (i > 4 && touches5(i)) return true;
        }
        
        // If the loop finishes without finding any crossings, return false.
        return false;
    }
    
    // Helper method to check for a crossing with the segment 3 steps back.
    // Condition: current segment (i) is long enough to reach back to or past segment (i-2),
    // AND the previous segment (i-1) is short enough to not go past segment (i-3).
    public boolean touches3(int i) {
        return arr[i] >= arr[i - 2] && arr[i - 1] <= arr[i - 3];
    }
    
    // Helper method to check for a crossing with the segment 4 steps back.
    // This occurs when segments i-1 and i-3 are parallel (equal length),
    // and segment i is long enough to reach back to or past segment i-2 minus segment i-4.
    public boolean touches4(int i) {
        return arr[i - 1] == arr[i - 3] && arr[i] >= arr[i - 2] - arr[i - 4];
    }
    
    // Helper method to check for a crossing with the segment 5 steps back.
    // This is a more complex inward spiral crossing.
    // Conditions:
    // 1. arr[i-1] <= arr[i-3] (previous segment is shorter or equal, indicating an inward turn)
    // 2. arr[i-2] >= arr[i-4] (segment two steps back is longer or equal, indicating it's not fully "outward")
    // 3. arr[i] >= arr[i-2] - arr[i-4] (current segment reaches back sufficiently)
    // 4. arr[i-1] >= arr[i-3] - arr[i-5] (previous segment reaches back sufficiently to avoid crossing i-5)
    public boolean touches5(int i) {
        return arr[i - 1] <= arr[i - 3]
            && arr[i - 2] >= arr[i - 4]
            && arr[i] >= arr[i - 2] - arr[i - 4]
            && arr[i - 1] >= arr[i - 3] - arr[i - 5];
    }
}
```

## Interview Tips
*   **Visualize the Path**: Draw out a few examples on paper to understand how the segments interact and what configurations lead to crossings.
*   **Break Down the Logic**: Explain the geometric intuition behind each of the three crossing conditions (`touches3`, `touches4`, `touches5`) clearly.
*   **Handle Indices Carefully**: Emphasize that the conditions depend on segments `i-1`, `i-2`, `i-3`, `i-4`, and `i-5`, and that these indices must be valid.
*   **Discuss Edge Cases**: Mention the `n <= 3` base case and how the conditions for `touches4` and `touches5` have `i > 3` and `i > 4` guards.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Visualize the path and how segments can intersect.
- [ ] Derive the geometric conditions for the three main crossing types.
- [ ] Implement the algorithm with correct index handling.
- [ ] Test with edge cases (small `n`, straight lines, sharp turns).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Line Reflection
*   Max Points on a Line
*   Path Crossing

## Tags
`Array` `Math`
