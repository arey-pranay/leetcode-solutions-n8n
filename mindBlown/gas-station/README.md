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
        int n = gas.length;
        
        int gSum = 0;
        int cSum = 0;
        for(int i=0;i<n;i++){
            gSum += gas[i];
            cSum += cost[i];
        }
        if(gSum < cSum) return -1;
        
        int balance = 0;
        int ans = 0;
        for(int i=0;i<n;i++){
            balance += gas[i] - cost[i];
            if(balance < 0){
                ans = i+1;
                balance = 0;
            }
        }
        
        return ans;
    }
}
```

---

---
## Quick Revision
Given two arrays `gas` and `cost`, find a starting gas station index from which you can travel around the circuit once.
We can solve this by checking if total gas is sufficient and then finding a valid starting point by tracking the current fuel balance.

## Intuition
The core idea is that if the total amount of gas available across all stations is less than the total cost to travel between them, then it's impossible to complete the circuit, regardless of the starting point. If the total gas is sufficient, then a solution *must* exist.

The second part of the intuition is about finding the starting point. If we start at station `i` and run out of gas before reaching station `j`, it means that any starting point between `i` and `j` (inclusive of `i`, exclusive of `j`) would also fail to reach `j`. This is because starting at any station `k` between `i` and `j` would mean we have less initial fuel (or the same amount if `k=i`) than if we had started at `i` and reached `k`. Therefore, if we fail at `j`, the next potential starting point must be `j+1`. We can keep track of the current fuel balance and reset it to 0 whenever it drops below zero, updating our potential starting point to the next station.

## Algorithm
1. Calculate the total gas available (`gSum`) and the total cost to travel (`cSum`) across all stations.
2. If `gSum` is less than `cSum`, return -1, as it's impossible to complete the circuit.
3. Initialize `balance` to 0 (representing current fuel in the tank) and `ans` to 0 (representing the potential starting station index).
4. Iterate through the stations from index 0 to `n-1` (where `n` is the number of stations).
5. In each iteration, update `balance` by adding the gas at the current station and subtracting the cost to travel to the next station (`balance += gas[i] - cost[i]`).
6. If `balance` becomes negative at any point, it means we cannot reach the current station from the current `ans`. Therefore, we reset `balance` to 0 and update `ans` to `i + 1` (the next station as a potential starting point).
7. After iterating through all stations, `ans` will hold the index of the starting station from which the circuit can be completed. Return `ans`.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Total Sum Check:** A necessary condition for a solution to exist is that the total gas must be greater than or equal to the total cost.
*   **Running Balance:** Tracking the cumulative fuel difference to identify points of failure and potential new starting points.

## Common Mistakes
*   Not performing the initial check for `gSum < cSum`. This can lead to incorrect results or infinite loops if not handled.
*   Incorrectly updating the starting index (`ans`). It should be `i + 1` when the balance drops below zero, not `i`.
*   Forgetting to reset the `balance` to 0 when it becomes negative. This would incorrectly carry over a deficit.
*   Assuming a solution exists without the total sum check, leading to incorrect outputs when no solution is possible.

## Complexity Analysis
*   Time: O(n) - reason: We iterate through the `gas` and `cost` arrays twice in total (once for the sum check, once for finding the start index).
*   Space: O(1) - reason: We only use a few extra variables (`n`, `gSum`, `cSum`, `balance`, `ans`) regardless of the input size.

## Commented Code
```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // Get the total number of gas stations.
        int n = gas.length;
        
        // Initialize variables to store the total gas and total cost.
        int gSum = 0;
        int cSum = 0;
        
        // Iterate through all stations to calculate the total gas and total cost.
        for(int i=0;i<n;i++){
            // Add the gas at the current station to the total gas sum.
            gSum += gas[i];
            // Add the cost to travel from the current station to the next to the total cost sum.
            cSum += cost[i];
        }
        
        // If the total gas is less than the total cost, it's impossible to complete the circuit.
        if(gSum < cSum) return -1;
        
        // Initialize 'balance' to track the current fuel in the tank.
        // Initialize 'ans' to store the potential starting station index.
        int balance = 0;
        int ans = 0;
        
        // Iterate through all stations again to find the starting point.
        for(int i=0;i<n;i++){
            // Update the balance: add gas, subtract cost to travel to the next station.
            balance += gas[i] - cost[i];
            
            // If the balance drops below zero, it means we cannot reach the current station 'i' from 'ans'.
            if(balance < 0){
                // The next station (i+1) becomes the new potential starting point.
                ans = i+1;
                // Reset the balance to 0, as we are starting fresh from the new potential start.
                balance = 0;
            }
        }
        
        // If we reach here, it means a solution exists (due to the initial gSum < cSum check),
        // and 'ans' holds the index of the valid starting station.
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain the two-part logic: first, the total gas must be sufficient; second, how to find the starting point using the running balance.
*   Walk through an example manually to demonstrate how the `balance` and `ans` variables change.
*   Be prepared to discuss why the greedy approach works and why the total sum check is crucial.
*   If asked about edge cases, consider scenarios with only one station or where the circuit is just barely possible.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Verify the total gas vs. total cost condition.
- [ ] Implement the running balance logic correctly.
- [ ] Ensure the starting index is updated properly when balance < 0.
- [ ] Test with provided examples and edge cases.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Circular Array Loop
*   Container With Most Water
*   Trapping Rain Water

## Tags
`Array` `Greedy` `Dynamic Programming`
