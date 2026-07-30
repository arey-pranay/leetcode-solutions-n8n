# Gas Station

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tGas=0,tCost=0;
        int n = gas.length;
        for(int i=0;i<n;i++){tGas += gas[i]; tCost += cost[i];}
        if(tGas < tCost) return -1; 
        int total = 0;
        int ans = 0;
        for(int i=0;i<n;i++){
            total = total + gas[i] - cost[i];
            if(total < 0){ans = i+1; total = 0;} // mtlb ghaata hogya ab kahi aur se krte start chalo
        }
        return ans;
    }
}
// 0 -> 1 (3)
// + gas[i]
// - cost[i]
// should be > 0
```

---

---
## Quick Revision
Given two arrays `gas` and `cost`, find a starting gas station index from which you can complete a circular tour.
We can solve this by checking if total gas is sufficient and then finding a valid starting point using a single pass.

## Intuition
The core idea is that if the total amount of gas available across all stations is less than the total cost to travel between them, it's impossible to complete the circuit, regardless of the starting point. If the total gas is sufficient, there *must* be a solution.

The second part of the intuition is realizing that if we start at a station and run out of gas before reaching the next station, then any station *between* our starting point and the point where we ran out of gas cannot be a valid starting point either. This is because starting at any of those intermediate stations would mean we'd have even less gas when we reach the point where we previously ran out. Therefore, if our current `total` gas balance drops below zero, we can discard all previous stations as potential starting points and try starting from the *next* station.

## Algorithm
1. Calculate the total gas available (`tGas`) and the total cost required (`tCost`) for the entire circuit.
2. If `tGas < tCost`, return -1, as it's impossible to complete the circuit.
3. Initialize `total` (current gas balance) to 0 and `ans` (potential starting station index) to 0.
4. Iterate through the stations from index 0 to `n-1`:
    a. Update `total` by adding the gas at the current station and subtracting the cost to reach the next station: `total = total + gas[i] - cost[i]`.
    b. If `total` becomes negative, it means we cannot reach the next station from the current `ans`. Therefore, reset `ans` to `i + 1` (the next station) and reset `total` to 0 to start accumulating gas from this new potential starting point.
5. After the loop, `ans` will hold the index of the starting station that allows completing the circuit. Return `ans`.

## Concept to Remember
*   **Greedy Approach:** Making the locally optimal choice at each step to achieve a global optimum.
*   **Prefix Sum / Running Sum:** Keeping track of a cumulative value to efficiently determine balances.
*   **Necessary Condition:** The total gas must be greater than or equal to the total cost for a solution to exist.

## Common Mistakes
*   Not checking the total gas vs. total cost condition upfront, leading to unnecessary computations.
*   Incorrectly resetting the `total` gas balance when it drops below zero, or not updating the potential starting index.
*   Off-by-one errors when updating the starting index (`ans = i` instead of `ans = i + 1`).
*   Assuming a solution exists without verifying the total gas sufficiency.

## Complexity Analysis
*   Time: O(n) - We iterate through the `gas` and `cost` arrays twice in the worst case (once for total sum, once for finding the start).
*   Space: O(1) - We only use a few extra variables to store sums and the answer.

## Commented Code
```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // Initialize total gas and total cost for the entire circuit.
        int tGas = 0, tCost = 0;
        // Get the number of stations.
        int n = gas.length;
        // Loop through all stations to calculate total gas and total cost.
        for (int i = 0; i < n; i++) {
            // Accumulate gas from the current station.
            tGas += gas[i];
            // Accumulate cost to travel from the current station to the next.
            tCost += cost[i];
        }
        // If total gas is less than total cost, it's impossible to complete the circuit.
        if (tGas < tCost) return -1;

        // Initialize 'total' to track the current gas balance starting from 'ans'.
        int total = 0;
        // Initialize 'ans' to the potential starting station index, defaulting to 0.
        int ans = 0;
        // Loop through all stations again to find a valid starting point.
        for (int i = 0; i < n; i++) {
            // Update the current gas balance: add gas at station i, subtract cost to reach station i+1.
            total = total + gas[i] - cost[i];
            // If the current gas balance drops below zero, it means we cannot reach station i+1 from the current 'ans'.
            if (total < 0) {
                // Therefore, the next station (i+1) becomes the new potential starting point.
                ans = i + 1;
                // Reset the current gas balance to 0 to start accumulating from the new potential start.
                total = 0;
            }
        }
        // If we reach here, it means a solution exists, and 'ans' holds the index of the starting station.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain the two-part logic: total gas sufficiency and the greedy approach for finding the start.
*   Walk through an example by hand to demonstrate how the `total` and `ans` variables change.
*   Be prepared to discuss why the greedy choice (resetting `ans` to `i+1` when `total < 0`) is optimal.
*   Mention the edge case where `gas` and `cost` arrays are empty or have only one element.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Verify the total gas vs. total cost condition.
- [ ] Implement the greedy approach for finding the starting station.
- [ ] Handle the case where no solution exists.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   Trapping Rain Water
*   Container With Most Water
*   Maximum Subarray

## Tags
`Array` `Greedy` `Dynamic Programming`
