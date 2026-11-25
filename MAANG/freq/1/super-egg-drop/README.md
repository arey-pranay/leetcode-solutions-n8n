# Super Egg Drop

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Math` `Binary Search` `Dynamic Programming`  
**Time:** O(eggs \* floor)  
**Space:** O(eggs \* floor)

---

## Solution (java)

```java
class Solution {
    int[][] memo;
    public int superEggDrop(int eggs, int floor) {
        memo = new int[eggs+1][floor+1];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(eggs,floor);
    }
    public int func(int eggs, int floor) {
     if(floor==0 || floor==1 || eggs==1) return floor;
     if(memo[eggs][floor] != -1) return memo[eggs][floor];
        int l=1, r=floor;
        int moves = floor;
        while(l<=r){
            int mid = l + (r-l)/2;
            int broke = func(eggs-1, mid-1);
            int notBroke = func(eggs, floor-mid);
            
            if(broke > notBroke) r = mid-1; //tootne pe zyada mehnt lgi hai, niche jayege
            else l = mid+1;
            
            moves = Math.min(moves,1 + Math.max(broke,notBroke));
        }
        return memo[eggs][floor] = moves;
    }
}
```

---

---

## Quick Revision
The problem is to find the minimum number of egg drops required to find the top floor from which an egg will break when dropped. We solve this problem using dynamic programming and memoization.

## Intuition
The key insight is that we can use the minimum number of egg drops required to find the top floor from which an egg will break when dropped, as the egg drops from each floor are independent. We can use memoization to store the results of subproblems and avoid redundant calculations.

## Algorithm
1. Create a 2D memoization table `memo` with dimensions `(eggs+1) x (floor+1)`.
2. Initialize the memoization table with `-1` values and fill the base cases: `floor=0`, `floor=1`, and `eggs=1`.
3. Define a recursive function `func(eggs, floor)` that takes the number of eggs and the current floor as input.
4. If the result for the current subproblem is already stored in the memoization table, return it.
5. Otherwise, perform a binary search over the possible drops:
	* Calculate the number of egg drops required to break an egg at each possible drop location `mid`.
	* Recursively call `func(eggs-1, mid-1)` and `func(eggs, floor-mid)` to calculate the number of egg drops required to break an egg at the previous and next drop locations, respectively.
	* Update the `moves` variable to store the minimum number of egg drops required to break an egg at the current drop location.
6. Store the result of the subproblem in the memoization table and return the minimum number of egg drops required to break an egg.

## Concept to Remember
* **Memoization**: store the results of subproblems to avoid redundant calculations and improve performance.
* **Dynamic programming**: break down the problem into smaller subproblems and solve each subproblem only once.
* **Binary search**: use a divide-and-conquer approach to find the optimal solution.

## Common Mistakes
* Failing to initialize the memoization table properly.
* Not handling the base cases correctly.
* Not using memoization to store the results of subproblems.
* Not updating the `moves` variable correctly in the binary search loop.

## Complexity Analysis
- Time: O(eggs \* floor)
- Space: O(eggs \* floor)

## Commented Code
```java
class Solution {
    int[][] memo;
    public int superEggDrop(int eggs, int floor) {
        // Initialize the memoization table
        memo = new int[eggs+1][floor+1];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        
        // Call the recursive function to solve the problem
        return func(eggs,floor);
    }
    
    public int func(int eggs, int floor) {
        // Base cases
        if(floor==0 || floor==1 || eggs==1) return floor;
        
        // Check if the result is already stored in the memoization table
        if(memo[eggs][floor] != -1) return memo[eggs][floor];
        
        // Initialize the minimum number of egg drops required
        int moves = floor;
        
        // Perform a binary search over the possible drops
        int l=1, r=floor;
        while(l<=r){
            int mid = l + (r-l)/2;
            // Calculate the number of egg drops required to break an egg at each possible drop location
            int broke = func(eggs-1, mid-1);
            int notBroke = func(eggs, floor-mid);
            
            // Update the minimum number of egg drops required
            if(broke > notBroke) r = mid-1; // Move to the left half
            else l = mid+1; // Move to the right half
            
            // Update the minimum number of egg drops required
            moves = Math.min(moves,1 + Math.max(broke,notBroke));
        }
        
        // Store the result of the subproblem in the memoization table
        return memo[eggs][floor] = moves;
    }
}
```

## Interview Tips
* Make sure to understand the problem statement and the constraints before attempting to solve it.
* Use memoization to store the results of subproblems and avoid redundant calculations.
* Perform a binary search over the possible drops to find the optimal solution.
* Pay attention to the base cases and handle them correctly.
* Use a clear and concise naming convention for the variables and functions.

## Revision Checklist
- [ ] Understand the problem statement and constraints
- [ ] Initialize the memoization table properly
- [ ] Handle the base cases correctly
- [ ] Use memoization to store the results of subproblems
- [ ] Perform a binary search over the possible drops

## Similar Problems
* [egg dropping problem](https://leetcode.com/problems/egg-dropping/)
* [egg dropping problem II](https://leetcode.com/problems/egg-dropping-ii/)

## Tags
`Array` `Hash Map` `Dynamic Programming` `Memoization` `Binary Search`
