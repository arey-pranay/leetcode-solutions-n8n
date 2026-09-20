# Self Crossing

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Math` `Geometry`  
**Time:** O(N)  
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
We solve it by analyzing the geometric conditions for crossing based on the lengths of consecutive segments.

## Intuition
The core idea is to consider the current line segment (let's say segment `i`) and how it could potentially intersect with previous segments. Since the path always moves outwards (north, west, south, east, etc.), a crossing can only occur with segments that are sufficiently "far back" in the path. Specifically, segment `i` can only cross segments `i-3`, `i-4`, or `i-5`. The conditions for crossing are derived by analyzing the relative lengths and directions of these segments.

## Algorithm
1.  Handle the base case: If the number of segments `n` is less than or equal to 3, no self-crossing is possible, so return `false`.
2.  Iterate through the segments starting from the 4th segment (index `i = 3`) up to `n-1`.
3.  For each segment `i`, check for three types of potential crossings:
    *   **Type 1 (i crosses i-3):** This occurs if segment `i` is long enough to reach back to or past segment `i-3`, and segment `i-1` is short enough to allow this. The condition is `arr[i] >= arr[i-2]` and `arr[i-1] <= arr[i-3]`.
    *   **Type 2 (i crosses i-4):** This is a special case that happens when segment `i-1` has the same length as `i-3`. In this scenario, segment `i` needs to be long enough to extend beyond the "gap" created by `i-2` and `i-4`. The condition is `arr[i-1] == arr[i-3]` and `arr[i] >= arr[i-2] - arr[i-4]`.
    *   **Type 3 (i crosses i-5):** This is a more complex scenario where segment `i` might cross segment `i-5`. This happens when segment `i-1` is shorter than `i-3`, segment `i-2` is longer than `i-4`, and segment `i` is long enough to bridge the gap. The conditions are `arr[i-1] <= arr[i-3]`, `arr[i-2] >= arr[i-4]`, `arr[i] >= arr[i-2] - arr[i-4]`, and `arr[i-1] >= arr[i-3] - arr[i-5]`.
4.  If any of these crossing conditions are met for any `i`, return `true`.
5.  If the loop completes without finding any crossings, return `false`.

## Concept to Remember
*   **Geometric Reasoning:** The problem requires translating geometric conditions of line segment intersections into algebraic inequalities based on segment lengths.
*   **Case Analysis:** Identifying and handling different geometric configurations that lead to a self-crossing.
*   **Boundary Conditions:** Carefully considering edge cases and small numbers of segments.

## Common Mistakes
*   **Overlooking specific crossing patterns:** Not realizing that crossings can occur with `i-3`, `i-4`, and `i-5` and that the conditions for each are distinct.
*   **Incorrectly deriving the inequalities:** Making errors in the geometric interpretation that lead to wrong mathematical conditions.
*   **Off-by-one errors in indexing:** Miscalculating the indices of previous segments when checking for crossings.
*   **Not handling the `n <= 3` base case:** Assuming a crossing is possible for very short paths.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the array of distances once.
- Space: O(1) - reason: We only use a few variables to store indices and lengths, not dependent on input size.

## Commented Code
```java
class Solution {
    // Declare an instance variable to hold the input array, accessible by helper methods.
    int[] arr;

    // The main method to determine if the path self-crosses.
    public boolean isSelfCrossing(int[] d) {
        // Get the total number of line segments in the path.
        int n = d.length;
        // Assign the input array to the instance variable for easy access.
        arr = d;
        // If there are 3 or fewer segments, a self-crossing is impossible.
        if (n <= 3) return false;

        // Iterate through the segments starting from the 4th segment (index 3).
        // A crossing can only occur with segments at least 3 steps behind.
        for (int i = 3; i < n; i++) {
            // Check for the first type of crossing: segment 'i' crossing segment 'i-3'.
            // This happens if segment 'i' is long enough to reach back to or past 'i-2',
            // AND segment 'i-1' is short enough to allow this.
            if (touches3(i)) return true;

            // Check for the second type of crossing: segment 'i' crossing segment 'i-4'.
            // This condition is only relevant if we have at least 5 segments (i > 3).
            // This specific crossing occurs when segment 'i-1' is exactly the same length as 'i-3',
            // and segment 'i' is long enough to extend beyond the 'gap' formed by 'i-2' and 'i-4'.
            if (i > 3 && touches4(i)) return true;

            // Check for the third type of crossing: segment 'i' crossing segment 'i-5'.
            // This condition is only relevant if we have at least 6 segments (i > 4).
            // This is a more complex scenario involving multiple segments.
            if (i > 4 && touches5(i)) return true;
        }
        // If the loop finishes without finding any crossings, the path does not self-cross.
        return false;
    }

    // Helper method to check for the first type of crossing (i crosses i-3).
    public boolean touches3(int i) {
        // Condition: segment 'i' is long enough to reach back to or past 'i-2' (arr[i] >= arr[i-2]),
        // AND segment 'i-1' is short enough to allow this (arr[i-1] <= arr[i-3]).
        return arr[i] >= arr[i - 2] && arr[i - 1] <= arr[i - 3];
    }

    // Helper method to check for the second type of crossing (i crosses i-4).
    public boolean touches4(int i) {
        // Condition: segment 'i-1' has the same length as 'i-3' (arr[i-1] == arr[i-3]),
        // AND segment 'i' is long enough to extend beyond the 'gap' formed by 'i-2' and 'i-4'
        // (arr[i] >= arr[i-2] - arr[i-4]).
        return arr[i - 1] == arr[i - 3] && arr[i] >= arr[i - 2] - arr[i - 4];
    }

    // Helper method to check for the third type of crossing (i crosses i-5).
    public boolean touches5(int i) {
        // Condition 1: segment 'i-1' is shorter than or equal to 'i-3' (arr[i-1] <= arr[i-3]).
        // Condition 2: segment 'i-2' is longer than or equal to 'i-4' (arr[i-2] >= arr[i-4]).
        // Condition 3: segment 'i' is long enough to extend beyond the 'gap' formed by 'i-2' and 'i-4'
        // (arr[i] >= arr[i-2] - arr[i-4]).
        // Condition 4: segment 'i-1' is long enough to extend beyond the 'gap' formed by 'i-3' and 'i-5'
        // (arr[i-1] >= arr[i-3] - arr[i-5]).
        return arr[i - 1] <= arr[i - 3]
                && arr[i - 2] >= arr[i - 4]
                && arr[i] >= arr[i - 2] - arr[i - 4]
                && arr[i - 1] >= arr[i - 3] - arr[i - 5];
    }
}
```

## Interview Tips
*   **Visualize the path:** Draw out a few examples of paths and how they might cross. This helps in understanding the geometric conditions.
*   **Explain your case analysis:** Clearly articulate why you are checking segments `i-3`, `i-4`, and `i-5` and the geometric intuition behind each condition.
*   **Walk through an example:** Be prepared to trace the algorithm with a small input array to demonstrate your understanding.
*   **Discuss edge cases:** Mention the `n <= 3` case and how the conditions are derived for larger `n`.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Visualize the path and potential crossing scenarios.
- [ ] Derive the geometric conditions for crossing with `i-3`, `i-4`, and `i-5`.
- [ ] Implement the base case `n <= 3`.
- [ ] Implement the three distinct crossing checks correctly.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and its logic.

## Similar Problems
*   Line Reflection
*   Max Points on a Line
*   Shortest Distance to Target String in a Circular Array

## Tags
`Array` `Math` `Geometry`
