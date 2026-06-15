# Koko Eating Bananas

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search`  
**Time:** O(N log M)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;

        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int low = 1;
        int high = maxPile;

        while (low < high) {
            int mid = low + (high - low) / 2;

            long hours = 0;
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid; // ceil(pile / mid)
            }

            if (hours <= h) {
                high = mid;      // try a smaller speed
            } else {
                low = mid + 1;   // need a larger speed
            }
        }

        return low;
    }
}
```

---

---
## Quick Revision
Koko needs to eat all bananas from given piles within `h` hours.
We need to find the minimum eating speed `k` that allows Koko to finish.

## Intuition
The problem asks for the *minimum* speed. This suggests that if a speed `k` works, any speed greater than `k` will also work. Conversely, if a speed `k` doesn't work, any speed less than `k` also won't work. This monotonic property is a strong indicator for binary search. We can binary search on the possible eating speeds. The range of possible speeds is from 1 (eating one banana per hour) to the maximum number of bananas in any single pile (as Koko must be able to eat the largest pile in one hour if `h` is large enough).

## Algorithm
1. Determine the search space for the eating speed `k`. The minimum possible speed is 1, and the maximum possible speed is the largest pile size.
2. Initialize `low = 1` and `high = max_pile_size`.
3. While `low < high`:
    a. Calculate the `mid` speed: `mid = low + (high - low) / 2`.
    b. For the current `mid` speed, calculate the total hours required to eat all bananas. For each pile, the hours needed are `ceil(pile_size / mid)`. This can be calculated as `(pile_size + mid - 1) / mid` using integer division.
    c. Sum up the hours for all piles.
    d. If the total hours are less than or equal to `h`, it means Koko can finish with this speed (or potentially a slower speed). So, we try to find a smaller speed by setting `high = mid`.
    e. If the total hours are greater than `h`, it means Koko cannot finish with this speed. We need a faster speed, so set `low = mid + 1`.
4. The loop terminates when `low == high`. This value is the minimum eating speed `k` that satisfies the condition. Return `low`.

## Concept to Remember
*   **Binary Search:** Applicable when a problem exhibits a monotonic property, allowing efficient searching within a sorted or sortable range.
*   **Ceiling Division:** Calculating the number of full hours needed for a pile, even if only a fraction of an hour is used for the last banana.
*   **Monotonicity:** The core principle that enables binary search – if a speed `k` works, all speeds `> k` also work.

## Common Mistakes
*   **Incorrect Ceiling Division:** Using `pile / mid` directly instead of `(pile + mid - 1) / mid` will lead to incorrect hour calculations.
*   **Off-by-One Errors in Binary Search:** Incorrectly updating `low` or `high` boundaries (e.g., `high = mid - 1` or `low = mid`) can lead to infinite loops or incorrect results.
*   **Integer Overflow:** While less likely with typical LeetCode constraints, the sum of hours could potentially exceed `Integer.MAX_VALUE` if `h` and pile sizes are very large. Using `long` for `hours` mitigates this.
*   **Not Handling Edge Cases:** Forgetting that the minimum speed is 1, or that the maximum speed can be the largest pile.

## Complexity Analysis
*   Time: O(N log M) - where N is the number of piles and M is the maximum number of bananas in a pile. The binary search performs log M iterations, and in each iteration, we iterate through all N piles to calculate the total hours.
*   Space: O(1) - We only use a few variables to store `low`, `high`, `mid`, `maxPile`, and `hours`, which do not depend on the input size.

## Commented Code
```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // Initialize maxPile to find the upper bound for binary search.
        int maxPile = 0;

        // Iterate through all piles to find the largest pile size.
        for (int pile : piles) {
            // Update maxPile if the current pile is larger.
            maxPile = Math.max(maxPile, pile);
        }

        // Initialize the lower bound of the search space for eating speed. Minimum speed is 1.
        int low = 1;
        // Initialize the upper bound of the search space for eating speed. Maximum speed is the largest pile.
        int high = maxPile;

        // Perform binary search on the possible eating speeds.
        // The loop continues as long as the search space has more than one element.
        while (low < high) {
            // Calculate the middle speed to test. Using this formula prevents potential integer overflow.
            int mid = low + (high - low) / 2;

            // Initialize a variable to store the total hours required for the current speed 'mid'. Use long to prevent overflow.
            long hours = 0;
            // Iterate through each pile to calculate the time needed to eat it at speed 'mid'.
            for (int pile : piles) {
                // Calculate hours for the current pile using ceiling division: (pile + mid - 1) / mid.
                // This ensures that even if a pile is not fully divisible by 'mid', a full hour is accounted for.
                hours += (pile + mid - 1) / mid;
            }

            // Check if Koko can finish all bananas within 'h' hours with the current speed 'mid'.
            if (hours <= h) {
                // If yes, it means 'mid' is a possible speed, and we might be able to do even better (slower speed).
                // So, we try searching in the lower half of the current range by setting 'high' to 'mid'.
                high = mid;
            } else {
                // If no, it means 'mid' is too slow. Koko needs to eat faster.
                // So, we search in the upper half of the current range by setting 'low' to 'mid + 1'.
                low = mid + 1;
            }
        }

        // When the loop terminates, 'low' and 'high' will be equal, pointing to the minimum eating speed.
        return low;
    }
}
```

## Interview Tips
*   Clearly explain the monotonic property that justifies binary search.
*   Walk through the ceiling division calculation and why it's important.
*   Discuss the bounds of your binary search (1 to max pile size) and why they are chosen.
*   Consider edge cases like `h` being very large or very small, or piles having 0 bananas (though constraints usually prevent this).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the monotonic property for binary search.
- [ ] Define the search space for the eating speed.
- [ ] Implement the ceiling division correctly.
- [ ] Calculate total hours for a given speed.
- [ ] Implement binary search logic with correct boundary updates.
- [ ] Consider potential integer overflows.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find Minimum in Rotated Sorted Array
*   Search in Rotated Sorted Array
*   Median of Two Sorted Arrays
*   Capacity To Ship Packages Within D Days

## Tags
`Binary Search` `Array` `Math`
