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
The problem is to find the starting point of a circular tour where a gas station can be reached from another without running out of fuel. The solution involves calculating the total amount of gas and cost, then iterating through the array to find the point where the balance becomes negative.

## Intuition
The "aha moment" comes when realizing that if there's enough gas to cover all costs, it means we have a circular tour starting from any station. We can calculate the overall balance by subtracting total costs from total gas. Then, we iterate through the array again and return the index where the balance becomes negative for the first time.

## Algorithm
1. Calculate the total amount of gas (`gSum`) and cost (`cSum`).
2. If `gSum` is less than `cSum`, it's impossible to complete the circuit, so return -1.
3. Initialize a variable `balance` to keep track of the running balance.
4. Iterate through the array:
	* Add the difference between gas and cost for each station to `balance`.
	* If `balance` becomes negative, update `ans` with the current index plus one (since we're 1-indexing) and reset `balance` to zero.

## Concept to Remember
• **Dynamic Programming**: breaking down complex problems into smaller subproblems.
• **Greedy Algorithm**: making locally optimal choices to find global optimum.
• **Array Manipulation**: efficiently iterating through arrays and performing operations.

## Common Mistakes
• Forgetting to check if total gas is less than total cost before trying to find a solution.
• Not resetting the balance when it becomes negative, which could lead to incorrect results.
• Failing to account for the fact that we're dealing with a circular tour, so any station can be a starting point.

## Complexity Analysis
- Time: O(n) - iterating through the array twice.
- Space: O(1) - constant space used for variables.

## Commented Code
```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // Calculate total gas and cost
        int n = gas.length;
        int gSum = 0; // Total amount of gas
        int cSum = 0; // Total cost

        // Check if total gas is enough
        for (int i = 0; i < n; i++) {
            gSum += gas[i];
            cSum += cost[i];
        }
        
        // If not, return -1
        if (gSum < cSum) return -1;

        // Initialize balance and answer
        int balance = 0; // Running balance
        int ans = 0; // Index of starting station

        // Find the point where balance becomes negative for the first time
        for (int i = 0; i < n; i++) {
            balance += gas[i] - cost[i]; // Update balance
            if (balance < 0) { // If balance is negative, update answer and reset balance
                ans = i + 1;
                balance = 0;
            }
        }

        return ans;
    }
}
```

## Interview Tips
• Make sure to clearly understand the problem before diving into a solution.
• Consider edge cases, such as when total gas is less than total cost.
• Practice explaining your thought process and solution in a clear, concise manner.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Calculate total gas and cost correctly.
- [ ] Check if total gas is enough before trying to find a solution.
- [ ] Iterate through the array carefully, considering edge cases.
- [ ] Clearly explain your thought process and solution.

## Similar Problems
* `Circular Array Rotation`
* `Container With Most Water`

## Tags
`Array`, `Hash Map`, `Greedy Algorithm`
