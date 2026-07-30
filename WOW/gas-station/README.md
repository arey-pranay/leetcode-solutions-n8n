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
The problem is to find the starting point of a circular tour where we have enough fuel to complete the circuit. We solve this by calculating the net balance of fuel and finding the first index where the total becomes negative, indicating that we need to start the tour from there.

## Intuition
The key insight here is that if we can't complete the circuit starting from any point, then it's impossible to do so at all. Therefore, our approach should be to find the point after which we don't have enough fuel for a complete cycle.

## Algorithm
1. Calculate the total gas and cost.
2. Check if the total gas is less than the total cost. If yes, return -1 as it's impossible to complete the circuit.
3. Initialize two variables: `total` to keep track of the current balance and `ans` to store the starting index.
4. Iterate through the array, updating `total` by adding the difference between gas and cost at each index.
5. If `total` becomes negative, update `ans` with the current index + 1 (since arrays are 0-indexed) and reset `total` to zero.

## Concept to Remember
* Array processing: Iterate through the array once or use prefix sums.
* Greedy algorithms: Make locally optimal choices that lead to a global optimum.

## Common Mistakes
* Not initializing variables properly, leading to incorrect results.
* Forgetting to check if the total gas is less than the total cost before attempting to find the starting index.
* Misunderstanding the implications of a negative balance, thinking it means we've already completed the circuit when in fact it indicates we need to start over.

## Complexity Analysis
- Time: O(n) - reason: We process each element once.
- Space: O(1) - reason: We use constant extra space to store the total and current indices.

## Commented Code
```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // Calculate total gas and cost
        int tGas = 0;
        int tCost = 0;
        
        for (int i = 0; i < gas.length; i++) {
            tGas += gas[i];
            tCost += cost[i];
        }
        
        // Check if we can complete the circuit
        if (tGas < tCost) return -1;

        int total = 0;
        int ans = 0;

        for (int i = 0; i < gas.length; i++) {
            // Update total balance at each index
            total += gas[i] - cost[i];
            
            // If we can't continue from here, update starting point and reset total
            if (total < 0) {
                ans = i + 1;
                total = 0;
            }
        }

        return ans;
    }
}
```

## Interview Tips
* Be clear on the problem statement and what's being asked.
* Think about edge cases, like when it's impossible to complete the circuit.
* Show your thought process as you solve the problem.

## Revision Checklist
- [ ] Understand the problem correctly.
- [ ] Implement the solution without errors.
- [ ] Test with sample inputs and edge cases.
- [ ] Review complexity analysis for time and space.

## Similar Problems
* `CanJump`: Find if we can jump to the end of an array given a list of jumps.
* `Coin Change 2`: Determine the number of ways to make change for a given amount using coins with different denominations.

## Tags
`Array` `Hash Map` `Greedy Algorithm`
