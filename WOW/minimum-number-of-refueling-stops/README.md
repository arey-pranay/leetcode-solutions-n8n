# Minimum Number Of Refueling Stops

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy` `Heap (Priority Queue)`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
        int i=0,canGo = startFuel, stops=0;
        while(canGo < target){
            while(i < stations.length && stations[i][0] <= canGo) pq.add(stations[i++][1]);
            if(pq.isEmpty()) return -1;            
            canGo += pq.poll();  // this is the limiting factor, so we can run this loop max 500 times
            stops++;
        }
        return stops;
    }
}

```

---

---
## Quick Revision
Finds the minimum number of refueling stops required to reach a target distance given a starting fuel and available gas stations.
Solved using a greedy approach with a max-heap to dynamically choose the largest available fuel reserves when running out of gas.

## Intuition
The core realization is that we want to drive as far as possible before needing fuel, and when we do run out, we should look back at all the stations we have passed and retroactively decide to refuel at the one that provides the maximum amount of fuel. This "deferred decision" ensures we make optimal, greedy choices locally which lead to a globally optimal solution. A max-heap lets us instantly retrieve the station with the most fuel among all reachable stations we've encountered so far.

## Algorithm
1. Initialize a max-heap (`PriorityQueue`) to store the fuel capacities of stations we have passed.
2. Initialize pointers and counters: `i` to track the current station, `canGo` to track our current maximum reachable distance, and `stops` to count refuels.
3. Loop while `canGo` is less than `target`:
   - Enqueue the fuel of all stations into the max-heap whose positions are less than or equal to `canGo`, advancing pointer `i`.
   - If the max-heap is empty, it means we cannot reach any further stations or the target, so return `-1`.
   - Otherwise, pop the largest fuel amount from the max-heap, add it to `canGo`, and increment `stops`.
4. Return `stops` once `canGo` meets or exceeds `target`.

## Concept to Remember
- Greedy algorithms with "regret" mechanisms (using a heap to change past decisions).
- Priority Queue / Max-Heap optimization for dynamic candidate selection.
- Interval and range-based reachability problems.

## Common Mistakes
- Trying to decide whether to stop at a station *before* reaching it, which requires complex look-aheads.
- Forgetting to check if the max-heap is empty before polling, which would throw an exception or cause logical errors.
- Mismanaging the station index `i` or missing stations when fuel extends far ahead in a single jump.
- Confusing station position with station fuel capacity.

## Complexity Analysis
- Time: O(N log N) - Each station is pushed and popped from the max-heap at most once, where N is the number of stations.
- Space: O(N) - In the worst case, all stations are stored in the priority queue.

## Commented Code
```java
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // Initialize a max-heap to keep track of the largest fuel amounts available from passed stations
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
        // i tracks our position in the stations array; canGo is our current max reach; stops counts refuels
        int i = 0, canGo = startFuel, stops = 0;
        // Continue driving as long as our current reach cannot hit the final target
        while (canGo < target) {
            // Add all stations we can now reach with our current fuel range into the max-heap
            while (i < stations.length && stations[i][0] <= canGo) {
                pq.add(stations[i][1]); // Add the fuel amount of the station
                i++; // Move to the next station
            }
            // If we have no stations left in our heap, it means we are stranded and cannot reach further
            if (pq.isEmpty()) return -1;            
            // Greedily take the largest available fuel from the past stations we've passed to extend our reach
            canGo += pq.poll();  
            // Increment our total refueling stop count
            stops++;
        }
        // Return the minimum stops needed once we can successfully reach the target
        return stops;
    }
}
```

## Interview Tips
- Explain the "greedy with regret" pattern clearly: state that we aren't actually deciding to refuel until we run out of gas, at which point we pick the best past station.
- Trace through a small example (e.g., target = 100, start = 10, stations = [[10,60],[20,30],[30,30],[60,40]]) to demonstrate the heap state.
- Discuss alternative dynamic programming approaches (O(N^2)) and explain why the greedy heap approach (O(N log N)) is superior.

## Revision Checklist
- [ ] Understand why local greedy choices with a max-heap work globally.
- [ ] Implement the inner loop for adding reachable stations correctly.
- [ ] Handle the failure condition when the heap is empty.
- [ ] Analyze the O(N log N) time and O(N) space complexities.

## Similar Problems
- Jump Game II
- Course Schedule III
- Minimum Number of Taps to Open to Water a Garden
- Reaching Points

## Tags
`Array` `Greedy` `Heap (Priority Queue)` `Dynamic Programming`
