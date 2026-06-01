# Minimum Cost Of Buying Candies With Discount

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting`  
**Time:** O(n log n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int minimumCost(int[] cost) {
        // canBuy <= Math.min(bought)
        //sort the array and take 2-2 from last
        
        Arrays.sort(cost);
        // cost = -Arrays.sort(-cost);

        int ans = 0; int mod = 0;
        for(int i=cost.length-1;i>=0;i--){
            if((++mod) %3 == 0) continue;
            ans += cost[i];
        }
        return ans;
    }
}
```

---

---
## Quick Revision
The problem is to find the minimum cost of buying candies with a discount, given an array of costs. We solve this by sorting the array and taking every 3rd candy from the end.

## Intuition
The key insight here is that we can achieve the minimum cost by selecting every 3rd candy from the end, as long as we don't run out of candies. This approach works because it's always more cost-effective to choose a higher-priced candy when given the option.

## Algorithm
1. Sort the array `cost` in ascending order.
2. Initialize variables: `ans` (answer) to 0 and `mod` to 0.
3. Iterate through the sorted array from the end (`i = cost.length - 1`) to the beginning, incrementing `mod` by 1 for each candy.
4. When `mod` is a multiple of 3 (i.e., `(++mod) % 3 == 0`), skip this candy and continue to the next one.
5. Otherwise, add the current candy's cost (`cost[i]`) to the answer (`ans`).

## Concept to Remember
* **Greedy Algorithm**: choosing the locally optimal solution at each step with the hope of finding a global optimum solution.
* **Sorting**: sorting an array can often lead to efficient solutions for problems like this one.

## Common Mistakes
* Failing to consider edge cases, such as an empty input array or a single-element array.
* Not understanding the importance of the modulo operator in skipping every 3rd candy.
* Misinterpreting the "discount" aspect of the problem and trying to apply complex logic instead of a simple greedy algorithm.

## Complexity Analysis
- Time: O(n log n) - reason: sorting an array takes O(n log n) time, where n is the number of elements in the array.
- Space: O(1) - reason: we only use a constant amount of extra space to store variables `ans` and `mod`.

## Commented Code
```java
class Solution {
    public int minimumCost(int[] cost) {
        // Sort the array in ascending order
        Arrays.sort(cost);

        int ans = 0; int mod = 0;
        for (int i = cost.length - 1; i >= 0; i--) {
            // Skip every 3rd candy from the end
            if ((++mod) % 3 == 0) continue;
            ans += cost[i]; // Add the current candy's cost to the answer
        }
        return ans;
    }
}
```

## Interview Tips
* Make sure you understand the problem clearly and can explain it in your own words.
* Don't be afraid to ask for clarification if you're unsure about any aspect of the problem.
* Focus on finding a simple, efficient solution rather than overcomplicating things.

## Revision Checklist
- [ ] Understand the problem statement
- [ ] Sort the input array
- [ ] Implement a greedy algorithm to select every 3rd candy from the end
- [ ] Handle edge cases carefully

## Similar Problems
* 1221. Split Array into Consecutive Subarrays (LeetCode)
* 1344. Angle Between Hands of a Clock (LeetCode)

## Tags
`Array`, `Hash Map`, `Greedy Algorithm`, `Sorting`
