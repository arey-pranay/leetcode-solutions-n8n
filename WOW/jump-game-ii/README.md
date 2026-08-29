# Jump Game Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int jump(int[] nums) {
      int jumps=0, currMax = 0, nextMax =0;
      int n = nums.length;
      for(int i=0;i<n-1;i++){
          int canGo = i+nums[i];
          nextMax = Math.max(nextMax,canGo); //2,4
          if(i==currMax){//yes
              jumps++; // abhi tk ka humara best jo tha wahan hum aagye, now we need another jump for going forward //1
              currMax=nextMax; //2
          }
      }
      return jumps;
    }
}




```

---

---

## Quick Revision
Jump Game II problem is a medium difficulty problem where we need to find the minimum number of jumps required to reach the end of the array. We solve this problem by maintaining two variables, `currMax` and `nextMax`, to keep track of the maximum reachable position at the current and next jumps.

## Intuition
The key insight here is to realize that we don't need to explore all possible jumps, but rather focus on the maximum reachable position at each step. This is because if we can reach a certain position, we can also reach all positions before it.

## Algorithm
1. Initialize `jumps` to 0, `currMax` to 0, and `nextMax` to 0.
2. Iterate through the array from left to right.
3. For each position `i`, calculate the maximum reachable position `canGo` at the current jump.
4. Update `nextMax` to be the maximum of its current value and `canGo`.
5. If `i` is equal to `currMax`, increment `jumps` by 1 and update `currMax` to `nextMax`.
6. Repeat steps 3-5 until the end of the array is reached.
7. Return `jumps` as the minimum number of jumps required.

## Concept to Remember
* Greedy algorithm
* Dynamic programming
* Minimum number of operations required to achieve a certain goal

## Common Mistakes
* Failing to update `currMax` when `i` is equal to `currMax`
* Not checking if `canGo` is greater than `nextMax` before updating it
* Not incrementing `jumps` when `i` is equal to `currMax`

## Complexity Analysis
- Time: O(n) - We only iterate through the array once.
- Space: O(1) - We only use a constant amount of space to store the variables.

## Commented Code
```java
class Solution {
    public int jump(int[] nums) {
        int jumps = 0; // Initialize jumps to 0
        int currMax = 0; // Initialize currMax to 0
        int nextMax = 0; // Initialize nextMax to 0

        int n = nums.length; // Get the length of the array

        for (int i = 0; i < n - 1; i++) {
            int canGo = i + nums[i]; // Calculate the maximum reachable position at the current jump
            nextMax = Math.max(nextMax, canGo); // Update nextMax to be the maximum of its current value and canGo

            if (i == currMax) { // If we are at the current maximum reachable position
                jumps++; // Increment jumps by 1
                currMax = nextMax; // Update currMax to nextMax
            }
        }
        return jumps; // Return the minimum number of jumps required
    }
}
```

## Interview Tips
* Make sure to update `currMax` when `i` is equal to `currMax`.
* Don't forget to increment `jumps` when `i` is equal to `currMax`.
* Focus on the maximum reachable position at each step.

## Revision Checklist
- [ ] Understand the problem statement
- [ ] Identify the key insight of focusing on the maximum reachable position
- [ ] Practice implementing the algorithm from scratch
- [ ] Review the commented code and complexity analysis

## Similar Problems
* Jump Game (LeetCode #55)
* Jump Game III (LeetCode #863)

## Tags
`Array` `Greedy` `Dynamic Programming`
