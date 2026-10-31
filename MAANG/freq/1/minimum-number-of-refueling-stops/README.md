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
## QuickRevision
Finds the minimum fuel stops needed to reach a target distance using a greedy approach.
Uses a max-heap to greedily refuel with the largest available fuel whenever current fuel runs out.

## Intuition
The core realization is that we want to delay refueling decisions until we actually run out of gas, and when we do, we should pick the station with the maximum available fuel among all the stations we have already passed. This is a classic greedy choice. As we drive forward, we collect all reachable stations into a max-priority queue. If we can no longer reach the target and our priority queue is empty, it means we are stranded and it is impossible to reach the target. Otherwise, we pop the largest fuel amount from our heap, add it to our current reach, and increment our stop count.

## Algorithm
1. Initialize a max-priority queue to store the fuel capacities of visited stations, an index `i` to track stations, `canGo` representing our current maximum reachable distance initialized to `startFuel`, and `stops` set to 0.
2. Loop while the current reach `canGo` is less than the `target`.
3. Inside the loop, iterate through `stations` while the station is within our current reach (`stations[i][0] <= canGo`), pushing each station's fuel capacity into the priority queue and incrementing `i`.
4. Check if the priority queue is empty; if it is, return `-1` because no more fuel can be acquired to advance further.
5. Pop the largest fuel value from the priority queue, add it to `canGo`, and increment `stops`.
6. Once the loop terminates because `canGo >= target`, return `stops`.

## Concept to Remember
- Greedy algorithms making optimal local choices for global optimum.
- Max-priority queue / Heap data structure for dynamic maximum retrieval.
- Interval and range-based problem-solving where vehicle range acts as the moving boundary.

## Common Mistakes
- Trying to sort or iterate through stations backwards instead of tracking reach progressively.
- Forgetting to check if the priority queue is empty before polling, which would throw an exception.
- Incorrectly comparing station positions with the remaining fuel instead of the absolute distance from the start.
- Not handling the edge case where `startFuel >= target` right at the beginning.

## Complexity Analysis
- Time: O(N log N) - where N is the number of stations, because each station is pushed and popped from the priority queue at most once.
- Space: O(N) - to store the station fuel capacities in the max-priority queue.

## Commented Code
```java
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // Create a max-priority queue to always retrieve the largest available fuel amount first
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
        // i tracks our current station index, canGo is max reachable distance, stops counts refuels
        int i = 0, canGo = startFuel, stops = 0;
        // Continue driving until our maximum reachable distance meets or exceeds the target
        while (canGo < target) {
            // Gather all gas stations we can reach with our current fuel range and add their fuel to the heap
            while (i < stations.length && stations[i][0] <= canGo) {
                pq.add(stations[i][1]);
                i++;
            }
            // If we have no reachable stations left in our heap, we cannot proceed further and fail
            if (pq.isEmpty()) return -1;            
            // Greedily refuel using the largest fuel option available from the past stations we passed
            canGo += pq.poll();  
            // Increment the count of total refueling stops made
            stops++;
        }
        // Return the total number of stops required to reach the target
        return stops;
    }
}
```

## Interview Tips
- Clarify whether stations are strictly sorted by distance from the start (they usually are in this problem).
- Explain the greedy choice property clearly: why picking the *largest* past fuel is always optimal.
- Compare this approach with Dynamic Programming O(N^2) to demonstrate depth of knowledge.
- Walk through a small dry run with an example where you pass multiple stations before needing to refuel.

## Revision Checklist
- [ ] Understand why a max-heap models the optimal choice.
- [ ] Trace the `canGo` boundary expansion logic.
- [ ] Remember the O(N log N) time complexity derivation.
- [ ] Handle the base case where target is reachable without stops.

## Similar Problems
- `Jump Game II`
- `Maximum Performance of a Team`
- `Course Schedule III`
- `Meeting Rooms II`

## Tags
`Array` `Greedy` `Heap (Priority Queue)` `Dynamic Programming`
