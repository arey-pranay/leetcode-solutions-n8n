# Super Egg Drop

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Math` `Binary Search` `Dynamic Programming`  
**Time:** O(eggs * floor)  
**Space:** O(eggs * floor)

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
The problem is to determine the minimum number of moves to drop eggs from the top of a building to find the highest floor from which an egg can be dropped without breaking. We solve this problem using dynamic programming and memoization.

## Intuition
The key insight here is that we can simulate the process of dropping eggs and use memoization to avoid redundant calculations. We iterate through the range of possible floors and for each floor, we consider two possibilities: the egg breaks and we start again from the previous floor, or the egg doesn't break and we can move to the higher floors. We choose the minimum number of moves between these two possibilities.

## Algorithm
1. Initialize a 2D array `memo` to store the minimum number of moves for each egg and floor.
2. If the floor is 0 or 1, or if we only have one egg, return the floor.
3. If the result is already stored in `memo`, return it.
4. Initialize the range of possible floors (`l` and `r`) to 1 and `floor`, respectively.
5. Iterate through the range of possible floors:
	* Calculate the middle floor (`mid`).
	* Calculate the number of moves if the egg breaks at the `mid`-th floor (`broke`).
	* Calculate the number of moves if the egg doesn't break at the `mid`-th floor (`notBroke`).
	* Update the range of possible floors based on the minimum number of moves.
6. Return the minimum number of moves.

## Concept to Remember
* **Memoization**: storing the results of expensive function calls to avoid redundant calculations.
* **Dynamic Programming**: breaking down the problem into smaller subproblems and solving each one only once.
* **Greedy Algorithm**: making the locally optimal choice at each step to reach the globally optimal solution.

## Common Mistakes
* Failing to use memoization to avoid redundant calculations.
* Not considering the edge cases (e.g., when the floor is 0 or 1).
* Not updating the range of possible floors correctly.

## Complexity Analysis
- Time: O(eggs * floor) - We iterate through the range of possible floors and for each floor, we consider two possibilities.
- Space: O(eggs * floor) - We use a 2D array to store the minimum number of moves for each egg and floor.

## Commented Code
```java
class Solution {
    int[][] memo;
    public int superEggDrop(int eggs, int floor) {
        memo = new int[eggs+1][floor+1];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(eggs,floor);
    }
    
    public int func(int eggs, int floor) {
        // Base cases
        if(floor==0 || floor==1 || eggs==1) return floor;
        
        // Check if result is already stored in memo
        if(memo[eggs][floor] != -1) return memo[eggs][floor];
        
        // Initialize range of possible floors
        int l=1, r=floor;
        
        // Initialize minimum number of moves
        int moves = floor;
        
        while(l<=r){
            // Calculate middle floor
            int mid = l + (r-l)/2;
            
            // Calculate number of moves if egg breaks at mid-th floor
            int broke = func(eggs-1, mid-1);
            
            // Calculate number of moves if egg doesn't break at mid-th floor
            int notBroke = func(eggs, floor-mid);
            
            // Update range of possible floors
            if(broke > notBroke) r = mid-1; 
            else l = mid+1;
            
            // Update minimum number of moves
            moves = Math.min(moves,1 + Math.max(broke,notBroke));
        }
        
        // Store result in memo
        return memo[eggs][floor] = moves;
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly and ask for clarification if needed.
* Use memoization to avoid redundant calculations.
* Break down the problem into smaller subproblems and solve each one only once.
* Use a greedy algorithm to make locally optimal choices.

## Revision Checklist
- [ ] Understand the problem clearly.
- [ ] Use memoization to avoid redundant calculations.
- [ ] Break down the problem into smaller subproblems.
- [ ] Use a greedy algorithm to make locally optimal choices.

## Similar Problems
* Egg Dropping Problem (LeetCode 535)
* Minimum Number of Moves to Make All Nodes Equal to Each Other (LeetCode 1455)

## Tags
`Array` `Memoization` `Dynamic Programming` `Greedy Algorithm`
