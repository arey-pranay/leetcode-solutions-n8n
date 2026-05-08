# Best Time To Buy And Sell Stock Iii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
        public int maxProfit(int[] prices)  {
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int prof1 = 0, prof2 = 0;

        for (int price :  prices) {
            buy1 = Math.min(buy1, price);
            prof1 = Math.max(prof1, price - buy1);
            buy2 = Math.min(buy2, price - prof1);// prof1 ka discount milega 2nd buy pe, kyuki already utna gain hai humaare paas
            prof2 = Math.max(prof2, price - buy2); 
            System.out.println(buy1 + " " + prof1 + " " + buy2 + " " + prof2);
        }
        
        return prof2;
    }
}

// [3,3,5,0,0,3,1,4]
// b1 = 3 , 3
// s1 = 0,  2
// b2 = 3
// s2 = 0
```

---

---
## Quick Revision
Buy and sell stock at most two times to maximize profit. Solve by tracking minimum prices for each buy and calculating maximum profits.

## Intuition
The key insight is that we can maintain two separate buys, one without considering the first sale ( prof1 ) and another with a discounted price based on the first sale ( prof1 + discount for 2nd buy ). This approach works because it effectively explores all possible combinations of buying and selling stocks up to two times.

## Algorithm
1. Initialize variables: `buy1` = infinity, `buy2` = infinity, `prof1` = 0, `prof2` = 0.
2. Iterate through each price in the input array:
   1. Update `buy1`: if current price < `buy1`, update `buy1`.
   2. Update `prof1`: max of current profit (`price - buy1`) and previous `prof1`.
   3. Update `buy2` with a discounted price based on the first sale: min of current price - `prof1` and `buy2`.
   4. Update `prof2`: max of current profit (`price - buy2`) and previous `prof2`.

## Concept to Remember
* Tracking minimum prices for each buy.
* Using previously calculated profits as a discount for subsequent buys.

## Common Mistakes
* Failing to consider all possible combinations of buying and selling stocks up to two times.
* Incorrectly calculating or tracking minimum prices for each buy.
* Not accounting for the discounted price in the second buy.

## Complexity Analysis
- Time: O(n) - single pass through the input array
- Space: O(1) - constant space used for variables

## Commented Code
```java
class Solution {
    public int maxProfit(int[] prices)  {
        // Initialize variables to track minimum prices and maximum profits
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int prof1 = 0, prof2 = 0;

        // Iterate through each price in the input array
        for (int price :  prices) {
            // Update `buy1` with the minimum price seen so far
            buy1 = Math.min(buy1, price);
            
            // Calculate maximum profit without considering the first sale
            prof1 = Math.max(prof1, price - buy1);
            
            // Update `buy2` with a discounted price based on the first sale
            buy2 = Math.min(buy2, price - prof1); // Use prof1 as discount
            
            // Calculate maximum profit considering both buys
            prof2 = Math.max(prof2, price - buy2); 
        }
        
        // Return the maximum profit after considering two buys
        return prof2;
    }
}
```

## Interview Tips
* Make sure to track minimum prices and use previously calculated profits as discounts.
* Consider all possible combinations of buying and selling stocks up to two times.
* Practice explaining your thought process and solution to a problem like this.

## Revision Checklist
- [ ] Understand the problem statement correctly.
- [ ] Develop an efficient algorithm (e.g., single pass through input array).
- [ ] Implement the algorithm with necessary variables and logic.
- [ ] Review and optimize space complexity if needed.
