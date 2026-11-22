# Race Car

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Dynamic Programming` `Heuristic Search` `A* Search`  
**Time:** O(2^n)  
**Space:** O(t)

---

## Solution (java)

```java
class Solution {
    HashMap<Integer,Integer> memo = new HashMap<>();
    public int racecar(int t) {
        if(t==0) return 0;
        if(memo.containsKey(t)) return memo.get(t);
        int n = (int) (Math.log(t)/Math.log(2)) +1; //
        // int p=0, s= 1;
        int exact = (1 << n) -1; 
        if(exact == t) {memo.put(t,n);return n;} // for t = 17 se 31, sbke liye exact is 31
        int min = racecar(exact-t);// we exceeded, so we will solve for diff. and add rev later
        int prevStop = (1<<(n-1));     //(2 ^ (n-1))   
        for(int m=0;m<n-1;m++){ // m represents humne reverse krke, kitni baar acccerate kiya
            int revStep = 1<<m; //hum itna reverse me accelerate krege, to t se aur door jaate jayege
            int curr = racecar(t-prevStop + revStep); // total dist itna hua: jitna difference hai, usme jitna aur reverse krdiya
            int total = curr + m;  //  m baar neg acc, plus us position ka answer
            min = Math.min(min,total);
        }
        memo.put(t,1 + n + min);
        return 1 + n + min;  // n ops till here, then 1 rev
    }
}
// n + 1 + racecar(diff)

// 17
// AAAA
// p=15 s=16 
// 1 2 -> A
// 3 4 -> A
// 7 8 -> A
// 15 16 -> A
// 31 32 -> R 
// 31 -1 -> A
// 30 -2 -> A
// 28 -4 -> A
// 24 -8 -> A
// 16 -16 -> R
// 16 1   -> A
// 17 2   -------

// 15 -1 R
// 15 1  R
// 16 2  A
// 18 -1 R
// 18 -1 A
// 17   ----

// ---
// 15 16
// 15 -1
// 14 -2
// 12 -4
```

---

---
## Quick Revision
The problem is to find the minimum number of operations required to make a race car go from 0 to a given target position `t` on a circular track. This can be achieved by accelerating, decelerating, or reversing the car.
The solution uses memoization, recursion, and bit manipulation to solve this problem efficiently.

## Intuition
The key insight is to recognize that the problem can be broken down into smaller subproblems by considering the number of bits required to represent the target position `t`. This is because the number of bits required to represent `t` is directly related to the minimum number of operations required to reach it.

## Algorithm
1. Initialize a memoization table `memo` to store the minimum number of operations for each target position `t`.
2. If `t` is 0, return 0 as no operations are required.
3. If `t` is already in the memoization table, return the stored value.
4. Calculate the number of bits `n` required to represent `t` using the logarithm function.
5. Initialize `exact` to the maximum power of 2 less than or equal to `t`, which represents the largest power of 2 that can be used to accelerate or decelerate the car.
6. If `exact` is equal to `t`, store the value `n` in the memoization table and return `n`.
7. Recursively call the `racecar` function for `exact-t` to find the minimum number of operations required to reach the difference between `t` and `exact`.
8. Iterate over the range `0` to `n-1` and for each iteration, calculate the number of reverse steps required to accelerate or decelerate the car.
9. For each reverse step, recursively call the `racecar` function for the remaining distance and add the result to the current iteration's value.
10. Update the minimum value `min` with the smallest value obtained in the previous step.
11. Store the value `1 + n + min` in the memoization table and return the result.

## Concept to Remember
* Memoization: storing the results of expensive function calls to avoid redundant calculations
* Bit manipulation: using bitwise operations to efficiently calculate the number of bits required to represent a value
* Recursive function calls: breaking down the problem into smaller subproblems to solve the original problem

## Common Mistakes
* Failing to use memoization to store intermediate results
* Misunderstanding the relationship between the number of bits required to represent `t` and the minimum number of operations required to reach it
* Not correctly implementing the recursive function calls to solve the subproblems

## Complexity Analysis
- Time: O(2^n) - The number of recursive function calls grows exponentially with the number of bits required to represent `t`.
- Space: O(t) - The memoization table stores the minimum number of operations for each target position `t`.

## Commented Code
```java
class Solution {
    HashMap<Integer, Integer> memo = new HashMap<>();

    public int racecar(int t) {
        // Base case: if t is 0, return 0
        if (t == 0) return 0;

        // Check if result is already in memoization table
        if (memo.containsKey(t)) return memo.get(t);

        // Calculate number of bits required to represent t
        int n = (int) (Math.log(t) / Math.log(2)) + 1;

        // Initialize exact to maximum power of 2 less than or equal to t
        int exact = (1 << n) - 1;

        // If exact is equal to t, store value n in memoization table and return n
        if (exact == t) {
            memo.put(t, n);
            return n;
        }

        // Recursively call racecar function for exact-t to find minimum number of operations
        int min = racecar(exact - t);

        // Initialize prevStop to maximum power of 2 less than or equal to t
        int prevStop = (1 << (n - 1));

        // Iterate over range 0 to n-1
        for (int m = 0; m < n - 1; m++) {
            // Calculate reverse step
            int revStep = 1 << m;

            // Recursively call racecar function for remaining distance and add result to current iteration's value
            int curr = racecar(t - prevStop + revStep);
            int total = curr + m;
            min = Math.min(min, total);
        }

        // Store value 1 + n + min in memoization table and return result
        memo.put(t, 1 + n + min);
        return 1 + n + min;
    }
}
```

## Interview Tips
* Practice implementing the solution from scratch to understand the concepts involved
* Be prepared to explain the memoization and recursive function calls used in the solution
* Pay attention to the time and space complexity of the solution
* Be able to identify common mistakes and pitfalls in the solution

## Revision Checklist
- [ ] Understand the problem and the key insights involved
- [ ] Implement the solution from scratch using memoization and recursive function calls
- [ ] Verify the time and space complexity of the solution
- [ ] Practice explaining the solution and its concepts

## Similar Problems
* `1822. Multiple Sections` - Finding the minimum number of sections required to cover a given range
* `1539. Get the Maximum Score` - Finding the maximum score that can be obtained by moving a certain number of steps

## Tags
`Array` `Hash Map` `Bit Manipulation` `Memoization` `Recursive Function Calls`
