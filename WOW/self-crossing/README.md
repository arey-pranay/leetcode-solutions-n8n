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
The problem asks if a path defined by a sequence of movements will cross itself.
We solve it by checking for specific geometric conditions of line segment intersections based on the movement distances.

## Intuition
The core idea is to analyze the geometry of the path. As we draw lines, a self-crossing can only occur if the current line segment (or a recent one) intersects with a previous line segment. The problem simplifies because the movements are always in a specific sequence: North, West, South, East, North, West, etc. This fixed pattern of turns allows us to predict which previous segments a current segment *could* intersect with.

Consider the current line segment `i` (moving East, for example). It can only intersect with previous segments that are roughly parallel and in a position to be crossed. These are typically segments `i-3`, `i-4`, and `i-5`. The conditions for intersection are derived from analyzing the relative lengths of these segments and their orientations.

- **Case 1: `i` intersects `i-3`**: This happens when the current segment `i` (East) is long enough to reach or cross the horizontal line formed by `i-3` (West), and the previous segment `i-1` (South) is short enough not to have already passed the vertical line formed by `i-3` (West).
- **Case 2: `i` intersects `i-4`**: This is a more complex scenario where the path might form a "spiral out" and then cross. It requires specific relationships between `i`, `i-1`, `i-2`, and `i-3`, and `i-4`.
- **Case 3: `i` intersects `i-5`**: This is another complex scenario, often involving a "spiral in" pattern.

The provided solution elegantly captures these geometric conditions with specific inequalities.

## Algorithm
1. Handle the base case: If the number of movements `n` is less than or equal to 3, a self-crossing is impossible, so return `false`.
2. Iterate through the movements starting from the 4th movement (index `i = 3`). This is because the first three movements cannot form a crossing.
3. For each movement `i`, check for potential crossings with previous segments:
    a. Check if movement `i` crosses movement `i-3`. This condition is met if `d[i] >= d[i-2]` and `d[i-1] <= d[i-3]`.
    b. If `i > 3`, check if movement `i` crosses movement `i-4`. This condition is met if `d[i-1] == d[i-3]` and `d[i] >= d[i-2] - d[i-4]`.
    c. If `i > 4`, check if movement `i` crosses movement `i-5`. This condition is met if `d[i-1] <= d[i-3]`, `d[i-2] >= d[i-4]`, `d[i] >= d[i-2] - d[i-4]`, and `d[i-1] >= d[i-3] - d[i-5]`.
4. If any of these crossing conditions are met for any `i`, return `true`.
5. If the loop completes without finding any crossings, return `false`.

## Concept to Remember
*   **Geometric Analysis**: Understanding how line segments intersect based on their lengths and relative orientations.
*   **Pattern Recognition**: Identifying recurring geometric patterns in the path due to the fixed turning sequence (N, W, S, E).
*   **Edge Cases and Boundary Conditions**: Carefully handling small input sizes and the conditions for checking `i-3`, `i-4`, and `i-5`.

## Common Mistakes
*   **Overlooking Geometric Cases**: Not considering all possible ways a line segment can intersect with previous segments.
*   **Incorrect Indexing**: Off-by-one errors when accessing previous distances (`d[i-1]`, `d[i-2]`, etc.).
*   **Missing Base Cases**: Not handling `n <= 3` correctly, leading to index out of bounds errors or incorrect results.
*   **Complex Logic for `i-4` and `i-5`**: Trying to derive these conditions from scratch during an interview can be very difficult; understanding the pre-derived geometric conditions is key.
*   **Not considering the direction of movement**: The fixed N, W, S, E pattern is crucial for simplifying the intersection checks.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the array of distances once.
- Space: O(1) - reason: We only use a few variables to store indices and distances, not dependent on input size.

## Commented Code
```java
class Solution {
    // Declare an instance variable to hold the input array, making it accessible to helper methods.
    int[] arr;
    
    // The main method to determine if the path self-crosses.
    public boolean isSelfCrossing(int[] d) {
        // Get the total number of movements.
        int n = d.length;
        
        // Assign the input array to the instance variable.
        arr=d;
        
        // If there are 3 or fewer movements, a self-crossing is impossible.
        if(n<=3) return false;
        
        // Iterate through the movements starting from the 4th movement (index 3).
        // The current line (i) can potentially collide with lines i-3, i-4, or i-5.
        for(int i=3;i<n;i++) {
            // Check if the current line (i) crosses the line (i-3).
            if(touches3(i)) return true;
            // If i is greater than 3, check if the current line (i) crosses the line (i-4).
            if(i>3 && touches4(i)) return true;
            // If i is greater than 4, check if the current line (i) crosses the line (i-5).
            if(i>4 && touches5(i)) return true;
        }
        
        // If no crossing is found after checking all relevant segments, return false.
        return false;
    }
    
    // Helper method to check if line 'i' crosses line 'i-3'.
    // This occurs when the current segment (moving East) is long enough to reach or cross the horizontal line formed by i-3 (West),
    // and the previous segment (moving South) is short enough not to have already passed the vertical line formed by i-3 (West).
    public boolean touches3(int i){
        // arr[i] is the length of the current segment (e.g., East).
        // arr[i-2] is the length of the segment two steps back (e.g., South).
        // arr[i-1] is the length of the segment one step back (e.g., South).
        // arr[i-3] is the length of the segment three steps back (e.g., West).
        return arr[i] >= arr[i-2] && arr[i-1] <= arr[i-3];
    }
    
    // Helper method to check if line 'i' crosses line 'i-4'.
    // This is a more complex scenario where the path might spiral out.
    // It requires the segment i-1 (South) to be exactly the same length as i-3 (West),
    // and the current segment (East) to be long enough to reach or cross the horizontal line formed by i-2 (North) minus the length of i-4 (West).
    public boolean touches4(int i){
        // arr[i-1] == arr[i-3] checks if the South segment has the same length as the West segment.
        // arr[i] >= arr[i-2] - arr[i-4] checks if the current East segment is long enough to cross the horizontal line formed by i-2 (North) minus the length of i-4 (West).
        return arr[i-1]==arr[i-3] && arr[i] >= arr[i-2] - arr[i-4];
    }
    
    // Helper method to check if line 'i' crosses line 'i-5'.
    // This is another complex scenario, often involving a spiral-in pattern.
    // It checks for specific relative lengths and positions of segments i, i-1, i-2, i-3, i-4, and i-5.
    public boolean touches5(int i){
        // arr[i-1] <= arr[i-3]: The South segment is shorter than or equal to the West segment.
        // arr[i-2] >= arr[i-4]: The North segment is longer than or equal to the West segment (from i-4).
        // arr[i] >= arr[i-2] - arr[i-4]: The current East segment is long enough to reach or cross the horizontal line formed by i-2 (North) minus the length of i-4 (West).
        // arr[i-1] >= arr[i-3] - arr[i-5]: The South segment is long enough to reach or cross the vertical line formed by i-3 (West) minus the length of i-5 (North).
        return arr[i-1] <= arr[i-3] 
        && arr[i-2] >= arr[i-4] 
        && arr[i] >= arr[i-2] - arr[i-4] 
        && arr[i-1] >= arr[i-3] - arr[i-5];
    }
}
```

## Interview Tips
*   **Visualize the Path**: Draw out a few examples on paper to understand how the path moves and where crossings can occur. This is crucial for grasping the geometric conditions.
*   **Explain the Cases**: Be prepared to explain *why* the conditions for `touches3`, `touches4`, and `touches5` work. Focus on the relative lengths and orientations of the line segments.
*   **Handle Edge Cases First**: Always address the `n <= 3` case upfront. This shows attention to detail and prevents potential errors.
*   **Break Down the Logic**: If you can't recall the exact conditions, try to reason about the geometry for a few steps. For example, "If I'm moving East, what previous segments could I hit? Probably the West one (i-3) or maybe the one before that (i-4) if things are aligned a certain way."

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Visualize the path and movement directions (N, W, S, E).
- [ ] Derive or understand the geometric conditions for crossing with `i-3`, `i-4`, and `i-5`.
- [ ] Implement the base case `n <= 3`.
- [ ] Write the loop and call the appropriate helper functions.
- [ ] Test with small examples and edge cases.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Line Reflection
*   Max Points on a Line
*   K-th Smallest Element in a Sorted Matrix

## Tags
`Array` `Math`
