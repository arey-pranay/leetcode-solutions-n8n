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
Given an array of points on the plane, find the maximum number of points that lie on the same straight line. This can be solved by iterating through each point and calculating the slope with respect to all other points.

## Intuition
The key insight is to realize that two points are always connected by a unique line segment, regardless of its orientation or position in the plane. By normalizing the slope (i.e., reducing it to its simplest form) and storing counts in a hash map, we can efficiently count the number of points on each line.

## Algorithm
1. If there are less than 3 points, return the total number of points as every point lies on a separate line.
2. Iterate through each point (i).
   - Create an empty hash map to store slopes and their corresponding counts.
   - For each point `j` from `i+1` to `n-1`:
     * Calculate the normalized slope using the Euclidean distance between points `i` and `j`.
     * Store the slope in the hash map with its count incremented by 1.
   - Update the maximum count of points on a line for the current point `i`.
3. Return the maximum count of points on a line across all points.

## Concept to Remember
* **Greatest Common Divisor (GCD)**: used to normalize slopes and eliminate equivalent lines with different representations.
* **Hash Map**: efficient data structure for storing and retrieving counts of equivalent slopes.
* **Normalization**: crucial step in slope calculation to handle equivalent lines with different slope representations.

## Common Mistakes
* Failing to consider the possibility of points lying on the same line but with different normalizations (e.g., `y = x` and `y = -x`).
* Not using a hash map to efficiently store and retrieve counts of slopes.
* Ignoring the case where all points lie in the same vertical or horizontal line.

## Complexity Analysis
- Time: O(n^2) - reason: nested loops through each point, with up to n-1 iterations for each point i.
- Space: O(n) - reason: storing counts of slopes in a hash map for each point.

## Commented Code
```java
class Solution {
    public int maxPoints(int[][] points) {
        // Base case: less than 3 points
        if (points.length < 3) return points.length;
        
        int n = points.length; // total number of points
        
        int maxPoints = Integer.MIN_VALUE; // maximum count of points on a line
        int ans = Integer.MIN_VALUE; // overall answer
        
        for (int i = 0; i < n; i++) {
            HashMap<String, Integer> hm = new HashMap<>(); // hash map to store slopes and counts
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0]; // calculate x-coordinate difference
                int dy = points[j][1] - points[i][1]; // calculate y-coordinate difference
                
                int gcd = gcd(dx, dy); // normalize slope using GCD
                dx /= gcd;
                dy /= gcd;
                
                String slope = dy + "/" + dx; // store normalized slope in a string for hash map keys
                hm.put(slope, hm.getOrDefault(slope, 0) + 1); // increment count of points on the line
                
                maxPoints = Math.max(maxPoints, hm.get(slope)); // update maximum count of points on a line
            }
            
            ans = Math.max(ans, maxPoints + 1); // update overall answer
            
        }
        
        return ans;
    }
    
    public int gcd(int a, int b) {
        if (b == 0) return a; // base case: GCD of zero is the non-zero number
        return gcd(b, a % b); // recursive call to calculate GCD using Euclidean algorithm
    }
}
```

## Interview Tips
* Be mindful of edge cases and handle them explicitly.
* Use efficient data structures like hash maps to store counts of slopes.
* Normalize slopes carefully to avoid counting equivalent lines multiple times.

## Revision Checklist
- [ ] Understand the problem clearly, including edge cases.
- [ ] Review the algorithm for efficiency (O(n^2) time complexity).
- [ ] Verify that normalization is correct and handles all possible slope representations.
- [ ] Practice explaining the solution and its key components confidently.

## Similar Problems
* "Maximum Points on a Line"
* "Number of Lines to Write Beautiful Text"
* "Line Reflection"
