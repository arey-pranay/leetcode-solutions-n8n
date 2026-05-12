# Best Time To Buy And Sell Stock Iv

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n x k)  
**Space:** O(n x k)

---

## Solution (java)

```java
class Solution {
    int[] arr;
    Integer[][] memo ;
    public int maxProfit(int k, int[] prices) {
        memo = new Integer[prices.length+1][k+1];
        arr = prices;
        return func(0,k);
    }
    public int func(int start, int k){
        int selling = 0;
        if(k == 0 || start>=arr.length) return 0;
        if(memo[start][k] != null) return memo[start][k];
        
        int boughtAt = start;
        
        for(int i=boughtAt + 1; i<arr.length; i++){
            if (arr[i]<=arr[boughtAt]) boughtAt = i; 
            else selling = Math.max(selling,  arr[i]-arr[boughtAt] + func(i+1,k-1)); //10
        }
        selling = Math.max(selling,func(start + 1, k));
        return memo[start][k] = selling;        
        
    }
        
}
// [2,1,4,5,2,9,7] k = 2
// 1 - 4 = 3
// 2 - 9  = 7  , 10 


// 1 - 5 = 4
// 2 -9 = 7 , 11
```

---

---
## Quick Revision
The problem asks to find the maximum possible profit from a given list of stock prices with a limit on the number of transactions that can be made.
We solve this by using dynamic programming and memoization to keep track of the maximum profit at each step.

## Intuition
This problem is an extension of the standard "Best Time to Buy and Sell Stock" problem, but with the added complexity of limiting the number of transactions. The key insight here is that we can break down the problem into sub-problems where we consider the current position as a potential buy or sell point, and use memoization to avoid redundant computations.

## Algorithm
1. Initialize an array `memo` to store the maximum profit at each step, with dimensions `(n+1) x (k+1)` where `n` is the length of the price list and `k` is the number of transactions allowed.
2. Initialize a variable `selling` to 0 to keep track of the maximum profit at the current position.
3. If `k` is 0 or we have reached the end of the price list, return 0 as there are no more transactions allowed.
4. If the value at `memo[start][k]` is not null, return it directly as we have already computed this sub-problem before.
5. Initialize a variable `boughtAt` to the current position `start`.
6. Iterate through the price list starting from `boughtAt + 1`. For each position:
	* If the price at the current position is less than or equal to the price at `boughtAt`, update `boughtAt` to the current position.
	* Otherwise, calculate the maximum profit by selling at the current position and add it to the maximum profit of the sub-problem where we buy at the previous position and sell one transaction later (i.e., `func(i+1,k-1)`).
7. Update `selling` with the maximum value between the current maximum profit and the maximum profit when not buying at the current position (i.e., `func(start + 1, k)`).
8. Store the final value of `selling` in `memo[start][k]`.

## Concept to Remember
* Dynamic programming: breaking down a problem into smaller sub-problems and storing solutions to sub-problems to avoid redundant computation.
* Memoization: using an array or map to store the solution to each sub-problem, so that we can look up the solution instead of recomputing it.

## Common Mistakes
* Failing to initialize the `memo` array correctly before filling it with values.
* Not checking if a sub-problem has been computed before and storing its result in `memo`.
* Not updating `selling` correctly when calculating the maximum profit.

## Complexity Analysis
- Time: O(n x k) where n is the length of the price list and k is the number of transactions allowed, as we are iterating through the price list for each possible transaction.
- Space: O(n x k) to store the memoization array.

## Commented Code

```java
class Solution {
    int[] arr;
    Integer[][] memo;

    public int maxProfit(int k, int[] prices) {
        memo = new Integer[prices.length+1][k+1];
        arr = prices;
        return func(0,k);
    }

    public int func(int start, int k){
        int selling = 0;
        if(k == 0 || start>=arr.length) return 0; // base case: no more transactions allowed

        if(memo[start][k] != null) {
            return memo[start][k]; // use memoization to avoid redundant computation
        }

        int boughtAt = start;

        for(int i=boughtAt + 1; i<arr.length; i++){
            if (arr[i]<=arr[boughtAt]) { // update boughtAt if current price is less than or equal to previous buy price
                boughtAt = i;
            } else {
                selling = Math.max(selling, arr[i]-arr[boughtAt] + func(i+1,k-1)); // 10: calculate maximum profit by selling at current position and add it to the maximum profit of sub-problem where we buy at previous position and sell one transaction later
            }
        }

        selling = Math.max(selling,func(start + 1, k)); // update selling with maximum value between current maximum profit and maximum profit when not buying at current position

        return memo[start][k] = selling; // store final value of selling in memo
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly before starting to code.
* Use a whiteboard or paper to draw a diagram illustrating the approach and explaining it to the interviewer.
* Be prepared to explain the reasoning behind each line of code.

## Revision Checklist
- Review and practice solving the problem multiple times to improve time complexity and efficiency.
- Practice using memoization and dynamic programming techniques for similar problems.
- Make sure to understand the trade-off between space and time complexity when using memoization.
