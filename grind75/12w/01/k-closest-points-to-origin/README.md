# K Closest Points To Origin

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Divide and Conquer` `Geometry` `Sorting` `Heap (Priority Queue)` `Quickselect`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int[][] ans = new int[k][2];
        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b) -> Double.compare(a[1],b[1])); // index aur distance 
        for(int i=0;i<points.length;i++) pq.add(new double[]{i, distOf(points[i])});
        for(int i=0;i<k;i++) ans[i] = points[(int) (pq.poll()[0])];
        return ans;
    }
    public double distOf(int[] arr){
        int x = arr[0]; int y = arr[1];
        return Math.sqrt(x*x + y*y);
    }
}
```

---

---
## Quick Revision
The problem is to find the k closest points to the origin (0, 0) in a 2D space given their coordinates and return them as an array.
This can be solved using a priority queue to efficiently select the closest points.

## Intuition
The key insight here is that we can use a priority queue to keep track of the distances from the points to the origin. By always selecting the point with the smallest distance (i.e., the top element in the priority queue), we ensure that our result array will contain the k closest points.

## Algorithm

1. Initialize an empty priority queue `pq` and an empty result array `ans`.
2. Iterate through each point in the input array `points`, calculating its distance from the origin using the `distOf` method.
3. Add a new element to the priority queue, consisting of the index of the current point and its distance from the origin.
4. Repeat step 3 until the size of the priority queue is equal to `k`.
5. Dequeue the top elements from the priority queue (i.e., the k closest points) and add their corresponding indices back into the result array.

## Concept to Remember

* **Priority Queues**: can be used to efficiently select the smallest or largest element from a set of elements.
* **Distance calculation**: Euclidean distance (`sqrt(x^2 + y^2)`).
* **Heap operations**: enqueue, dequeue, peek.

## Common Mistakes

* Forgetting to initialize the priority queue with the correct comparator function.
* Not handling edge cases (e.g., `k > points.length`).
* Not implementing a proper `distOf` method for calculating distances.

## Complexity Analysis
- Time: O(N log N) - reason: each insertion into the priority queue takes O(log N) time, and there are N elements in total.
- Space: O(N) - reason: we need to store all points and their indices in memory.

## Commented Code

```java
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // Initialize result array with capacity of k
        int[][] ans = new int[k][2];
        
        // Create a priority queue that stores (index, distance) pairs
        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b) -> Double.compare(a[1],b[1]));
        
        for(int i=0;i<points.length;i++) {
            // Calculate the distance from each point to the origin and add it to the priority queue
            double[] pair = {i, distOf(points[i])};
            pq.add(pair);
        }
        
        // Select k closest points from the priority queue and store them in the result array
        for(int i=0;i<k;i++) {
            int index = (int) (pq.poll()[0]);
            ans[i] = points[index];
        }
        
        return ans;
    }

    public double distOf(int[] arr){
        // Calculate Euclidean distance from a point to the origin
        int x = arr[0]; int y = arr[1];
        return Math.sqrt(x*x + y*y);
    }
}
```

## Interview Tips

* Make sure you understand how priority queues work and how they can be used for efficient selection of elements.
* Be mindful of edge cases (e.g., `k > points.length`) and handle them properly.
* Use a clear and consistent naming convention throughout your code.

## Revision Checklist
- [ ] Understand the problem statement carefully.
- [ ] Implement a correct priority queue-based solution.
- [ ] Handle edge cases properly.
- [ ] Use clear and consistent naming conventions.

## Similar Problems

* 215. Kth Largest Element in an Array
* 973. K Closest Points to Origin II
* 658. Find K Closest Elements
