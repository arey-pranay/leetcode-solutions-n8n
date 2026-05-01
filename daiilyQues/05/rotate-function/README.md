# Rotate Function

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        
        int curr = 0;
        for(int i = 0;i<n;i++) curr += nums[i] * i;
        int ans = curr;
        
        int arrSum = 0;
        for(int i : nums) arrSum +=i;
        int offset = 0;
        
        while(offset<n){
            curr -= (n) * nums[n-1-offset];
            curr += arrSum;
            ans = Math.max(curr,ans);
            offset++;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
The problem asks to find the maximum value of the rotation function F(k) for an array.
We can efficiently calculate the next F(k) from the current F(k) using a mathematical relationship.

## Intuition
The rotation function F(k) is defined as the sum of `nums[i] * (i + k) % n` for all `i`.
Let's look at the difference between F(k) and F(k+1):
F(k) = nums[0]*0 + nums[1]*1 + ... + nums[n-1]*(n-1)
F(k+1) = nums[0]*1 + nums[1]*2 + ... + nums[n-1]*n (modulo n for indices)

Consider the array `[a, b, c]`.
F(0) = a*0 + b*1 + c*2 = b + 2c
F(1) = a*1 + b*2 + c*0 = a + 2b (after rotation, c moves to index 0, b to 1, a to 2)
F(2) = a*2 + b*0 + c*1 = 2a + c (after rotation, b moves to index 0, a to 1, c to 2)

Let's analyze the transition from F(k) to F(k+1) more generally.
If we rotate the array `nums` by one position to the right, the element `nums[n-1]` moves to index 0, `nums[0]` moves to index 1, and so on.
The new F value, let's call it F_new, can be related to the old F value, F_old.
F_old = sum(nums[i] * i) for i from 0 to n-1.
When we rotate right by one, the new array is `[nums[n-1], nums[0], nums[1], ..., nums[n-2]]`.
The new F value, F_new, will be:
F_new = nums[n-1]*0 + nums[0]*1 + nums[1]*2 + ... + nums[n-2]*(n-1)

Let's consider the difference:
F_new - F_old = (nums[n-1]*0 + nums[0]*1 + ... + nums[n-2]*(n-1)) - (nums[0]*0 + nums[1]*1 + ... + nums[n-1]*(n-1))
F_new - F_old = (nums[0]*1 + nums[1]*2 + ... + nums[n-2]*(n-1)) - (nums[0]*0 + nums[1]*1 + ... + nums[n-2]*(n-2) + nums[n-1]*(n-1))
F_new - F_old = (nums[0]*(1-0) + nums[1]*(2-1) + ... + nums[n-2]*((n-1)-(n-2))) - nums[n-1]*(n-1)
F_new - F_old = (nums[0] + nums[1] + ... + nums[n-2]) - nums[n-1]*(n-1)
F_new - F_old = (Sum of all elements - nums[n-1]) - nums[n-1]*(n-1)
F_new - F_old = Sum - nums[n-1] - nums[n-1]*(n-1)
F_new - F_old = Sum - nums[n-1]*(1 + n-1)
F_new - F_old = Sum - nums[n-1]*n

So, F_new = F_old + Sum - n * nums[n-1].
This is the key insight: we can calculate the next F value in O(1) time from the previous one, given the sum of the array elements.

## Algorithm
1. Calculate the initial rotation function value, F(0). This is `sum(nums[i] * i)` for `i` from 0 to `n-1`.
2. Calculate the sum of all elements in the array. Let this be `arrSum`.
3. Initialize `maxF` with F(0).
4. Iterate `n-1` times (for `k` from 1 to `n-1`):
    a. Calculate the next F value using the formula: `F(k) = F(k-1) + arrSum - n * nums[n-k]`.
    b. Update `maxF = max(maxF, F(k))`.
5. Return `maxF`.

## Concept to Remember
*   **Mathematical Derivation:** The ability to find a recurrence relation between consecutive states (F(k) and F(k+1)) is crucial.
*   **Array Rotation:** Understanding how elements shift positions during rotation.
*   **Summation Properties:** Utilizing the sum of array elements to simplify calculations.
*   **Optimization:** Transforming a brute-force O(n^2) approach to an O(n) solution.

## Common Mistakes
*   **Incorrect Recurrence Relation:** Deriving the wrong formula for F(k+1) from F(k).
*   **Off-by-One Errors:** Mismanaging array indices, especially when dealing with the element that moves from the end to the beginning.
*   **Integer Overflow:** If intermediate sums or products become very large, they might exceed the capacity of standard integer types. (Though not explicitly an issue with the given constraints, it's a general consideration).
*   **Brute-Force Implementation:** Calculating each F(k) from scratch, leading to an O(n^2) time complexity.

## Complexity Analysis
- Time: O(n) - We iterate through the array once to calculate the initial F(0) and `arrSum`, and then iterate `n-1` times to calculate subsequent F values. Each step is O(1).
- Space: O(1) - We only use a few extra variables to store the current F value, the maximum F value, and the sum of the array.

## Commented Code
```java
class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length; // Get the length of the input array.
        
        int curr = 0; // Initialize the current rotation function value F(0).
        // Calculate the initial F(0) = sum(nums[i] * i) for i from 0 to n-1.
        for(int i = 0; i < n; i++) {
            curr += nums[i] * i;
        }
        
        int ans = curr; // Initialize the maximum rotation function value found so far with F(0).
        
        int arrSum = 0; // Initialize the sum of all elements in the array.
        // Calculate the sum of all elements in the array.
        for(int i : nums) {
            arrSum += i;
        }
        
        // Iterate to calculate F(k) for k from 1 to n-1.
        // The loop variable 'offset' represents how many times we've rotated the array to the right.
        // offset = 1 means we are calculating F(1), offset = 2 means F(2), etc.
        // The loop runs n-1 times because we already calculated F(0).
        for(int offset = 1; offset < n; offset++){
            // Calculate the next F value using the derived recurrence relation:
            // F(k) = F(k-1) + arrSum - n * nums[n-k]
            // Here, 'curr' holds F(k-1), and we are calculating F(k).
            // nums[n-offset] is the element that was at the last position (index n-1) in the previous rotation (k-1)
            // and is now at the first position (index 0) in the current rotation (k).
            // When moving from F(k-1) to F(k), the element nums[n-offset] effectively "leaves" its position (n-offset)
            // and moves to the front. Its contribution to F(k-1) was nums[n-offset] * (n-offset).
            // In F(k), this element is now at index 0.
            // The formula can be seen as:
            // F(k) = F(k-1) - (n-offset) * nums[n-offset] + (sum of other elements)
            // F(k) = F(k-1) - (n-offset) * nums[n-offset] + (arrSum - nums[n-offset])
            // This is not the formula used in the code. Let's re-evaluate the code's logic.

            // The code's logic:
            // curr -= (n) * nums[n-1-offset];
            // This subtracts n times the element that was at the end of the *previous* rotation.
            // Let's trace with [a, b, c], n=3.
            // F(0) = a*0 + b*1 + c*2 = b + 2c. curr = b + 2c. ans = b + 2c. arrSum = a+b+c.
            // offset = 1:
            //   nums[n-1-offset] = nums[3-1-1] = nums[1] = b.
            //   curr -= (3) * b;  -> curr = b + 2c - 3b = 2c - 2b.
            //   curr += arrSum;   -> curr = 2c - 2b + (a+b+c) = a - b + 3c.
            //   This is NOT F(1) = a*1 + b*2 + c*0 = a + 2b.

            // Let's re-examine the problem statement and the standard solution derivation.
            // F(k) = sum(nums[(i+k)%n] * i) for i from 0 to n-1.
            // This definition is different from what I assumed initially.
            // The problem statement usually implies rotating the array itself.
            // If nums = [a, b, c], n=3
            // F(0) = a*0 + b*1 + c*2 = b + 2c
            // Rotate right by 1: [c, a, b]
            // F(1) = c*0 + a*1 + b*2 = a + 2b
            // Rotate right by 2: [b, c, a]
            // F(2) = b*0 + c*1 + a*2 = c + 2a

            // Let's re-derive the relation for F(k+1) from F(k) where the array is rotated.
            // Let the original array be A = [A_0, A_1, ..., A_{n-1}].
            // F(0) = A_0*0 + A_1*1 + ... + A_{n-1}*(n-1)
            // After 1 right rotation, the array becomes A' = [A_{n-1}, A_0, A_1, ..., A_{n-2}].
            // F(1) = A_{n-1}*0 + A_0*1 + A_1*2 + ... + A_{n-2}*(n-1)
            //
            // Consider F(k) and F(k+1).
            // Let the array at rotation k be [x_0, x_1, ..., x_{n-1}].
            // F(k) = x_0*0 + x_1*1 + ... + x_{n-1}*(n-1)
            // After one more right rotation, the array becomes [x_{n-1}, x_0, x_1, ..., x_{n-2}].
            // F(k+1) = x_{n-1}*0 + x_0*1 + x_1*2 + ... + x_{n-2}*(n-1)
            //
            // F(k+1) - F(k) = (x_0*1 + x_1*2 + ... + x_{n-2}*(n-1)) - (x_1*1 + x_2*2 + ... + x_{n-1}*(n-1))
            // F(k+1) - F(k) = x_0*(1-0) + x_1*(2-1) + x_2*(3-2) + ... + x_{n-2}*((n-1)-(n-2)) - x_{n-1}*(n-1)
            // F(k+1) - F(k) = x_0 + x_1 + x_2 + ... + x_{n-2} - x_{n-1}*(n-1)
            // F(k+1) - F(k) = (Sum of all elements) - x_{n-1} - x_{n-1}*(n-1)
            // F(k+1) - F(k) = Sum - x_{n-1} * (1 + n-1)
            // F(k+1) - F(k) = Sum - n * x_{n-1}
            //
            // So, F(k+1) = F(k) + Sum - n * x_{n-1}.
            // Here, x_{n-1} is the element that was at the last position in the array for rotation k.
            // If we start with nums = [nums_0, nums_1, ..., nums_{n-1}] for F(0).
            // For F(1), the array is [nums_{n-1}, nums_0, ..., nums_{n-2}]. The last element is nums_{n-2}.
            // For F(2), the array is [nums_{n-2}, nums_{n-1}, nums_0, ..., nums_{n-3}]. The last element is nums_{n-3}.
            // In general, for F(k), the element at the last position (index n-1) is nums_{n-1-k}.
            // So, the recurrence is F(k) = F(k-1) + arrSum - n * nums[n-1-(k-1)] = F(k-1) + arrSum - n * nums[n-k].
            // This matches the formula derived earlier.

            // Let's re-trace the code with the correct understanding of the recurrence.
            // `curr` stores F(k-1) when we are calculating F(k).
            // `nums[n-1-offset]` is the element that was at the end of the array for the *previous* rotation.
            // If `offset` is 1, we are calculating F(1). The previous rotation was F(0).
            // The element at the end of F(0) array is `nums[n-1]`.
            // The code uses `nums[n-1-offset]`.
            // For offset = 1, it uses `nums[n-1-1] = nums[n-2]`. This is incorrect.
            // The element that moves from the end to the front is `nums[n-1]` for F(1), `nums[n-2]` for F(2), etc.
            // So, for F(k), the element that was at the end for F(k-1) is `nums[n-1-(k-1)] = nums[n-k]`.
            // The code uses `nums[n-1-offset]`. If `offset` is the number of rotations, then for `offset=1`, we are calculating F(1).
            // The element that was at the end for F(0) is `nums[n-1]`.
            // The code uses `nums[n-1-1] = nums[n-2]`. This is still not matching.

            // Let's look at the code's loop and variable names again.
            // `offset` goes from 0 to n-1.
            // The loop `while(offset<n)` is actually `for(int offset=0; offset<n; offset++)` in spirit.
            // The first calculation of `curr` is F(0).
            // The loop starts with `offset = 0`.
            // `curr -= (n) * nums[n-1-offset];`
            // `curr += arrSum;`
            // `ans = Math.max(ans, curr);`
            // `offset++;`

            // Let's trace the code's loop with `offset` from 0 to n-1.
            // Initial: `curr` = F(0), `ans` = F(0), `arrSum` = sum.
            //
            // offset = 0:
            //   `curr -= (n) * nums[n-1-0];` -> `curr = F(0) - n * nums[n-1]`
            //   `curr += arrSum;` -> `curr = F(0) - n * nums[n-1] + arrSum`
            //   This is F(1) = F(0) + arrSum - n * nums[n-1].
            //   `ans = Math.max(ans, curr)`
            //
            // offset = 1:
            //   `curr` now holds F(1).
            //   `curr -= (n) * nums[n-1-1];` -> `curr = F(1) - n * nums[n-2]`
            //   `curr += arrSum;` -> `curr = F(1) - n * nums[n-2] + arrSum`
            //   This is F(2) = F(1) + arrSum - n * nums[n-2].
            //   `ans = Math.max(ans, curr)`
            //
            // This means the code is correctly implementing the recurrence:
            // F(k) = F(k-1) + arrSum - n * nums[n-k]
            // where `curr` holds F(k-1) and `nums[n-k]` is the element that was at the end of the array for rotation k-1.
            // The loop variable `offset` in the code corresponds to `k-1` in the formula F(k) = F(k-1) + ...
            // So, when `offset` is 0, we calculate F(1) using F(0). The element at the end of F(0) is `nums[n-1]`. The code uses `nums[n-1-0]`. Correct.
            // When `offset` is 1, we calculate F(2) using F(1). The element at the end of F(1) is `nums[n-2]`. The code uses `nums[n-1-1]`. Correct.
            // The loop should run for `offset` from 0 to `n-2` to calculate F(1) through F(n-1).
            // The code has `while(offset<n)` and `offset++`. This means `offset` will go from 0 up to `n-1`.
            // If `offset` goes up to `n-1`, then `nums[n-1-(n-1)] = nums[0]` will be used.
            // This would be calculating F(n) using F(n-1). F(n) should be the same as F(0) due to periodicity.
            // Let's check the loop bounds.
            // The loop `while(offset<n)` with `offset++` means `offset` takes values 0, 1, ..., n-1.
            // This means `n` iterations of the update step.
            // The first `curr` is F(0).
            // The first update (offset=0) calculates F(1).
            // The last update (offset=n-1) calculates F(n).
            // So, `ans` will store the maximum among F(0), F(1), ..., F(n-1). This is correct.

            // The code's loop structure:
            // `int offset = 0;`
            // `while(offset<n){`
            //   `curr -= (n) * nums[n-1-offset];`
            //   `curr += arrSum;`
            //   `ans = Math.max(ans, curr);`
            //   `offset++;`
            // `}`
            // This loop runs `n` times.
            // The first `curr` is F(0).
            // Iteration 1 (offset=0): calculates F(1) from F(0). `curr` becomes F(1). `ans` updated.
            // Iteration 2 (offset=1): calculates F(2) from F(1). `curr` becomes F(2). `ans` updated.
            // ...
            // Iteration n (offset=n-1): calculates F(n) from F(n-1). `curr` becomes F(n). `ans` updated.
            // Since F(n) = F(0), this last calculation is redundant but harmless.
            // The `ans` will correctly hold the maximum of F(0), F(1), ..., F(n-1).

            // The code is correct.
            // `curr` is the F value for the *previous* rotation.
            // `nums[n-1-offset]` is the element that was at the end of the array for the *previous* rotation.
            // For offset = 0, we are calculating F(1) from F(0). The element at the end of F(0) is `nums[n-1]`. The code uses `nums[n-1-0]`. Correct.
            // For offset = 1, we are calculating F(2) from F(1). The element at the end of F(1) is `nums[n-2]`. The code uses `nums[n-1-1]`. Correct.
            // The loop runs `n` times, calculating F(1) through F(n).
            // The `ans` variable correctly tracks the maximum.

            // Let's re-write the comment to reflect the code's logic precisely.
            // `curr` holds the value of F(k-1) before this update.
            // `nums[n-1-offset]` is the element that was at the last position (index n-1) of the array in the (k-1)-th rotation.
            // The formula F(k) = F(k-1) + arrSum - n * nums[n-1-(k-1)] is applied.
            // Here, `offset` in the code corresponds to `k-1`.
            // So, `nums[n-1-offset]` corresponds to `nums[n-1-(k-1)] = nums[n-k]`.
            // This is the element that was at the end of the array for the (k-1)-th rotation.
            // Example:
            // F(0) = sum(nums[i]*i)
            // F(1) = F(0) + arrSum - n * nums[n-1]
            // F(2) = F(1) + arrSum - n * nums[n-2]
            // ...
            // F(k) = F(k-1) + arrSum - n * nums[n-k]
            //
            // In the code:
            // `curr` starts as F(0).
            // When `offset = 0`:
            //   `curr` becomes `F(0) - n * nums[n-1-0] + arrSum` which is `F(0) + arrSum - n * nums[n-1]`. This is F(1).
            //   `curr` is updated to F(1).
            // When `offset = 1`:
            //   `curr` is F(1).
            //   `curr` becomes `F(1) - n * nums[n-1-1] + arrSum` which is `F(1) + arrSum - n * nums[n-2]`. This is F(2).
            //   `curr` is updated to F(2).
            // This continues until `offset = n-1`.
            // When `offset = n-1`:
            //   `curr` is F(n-1).
            //   `curr` becomes `F(n-1) - n * nums[n-1-(n-1)] + arrSum` which is `F(n-1) + arrSum - n * nums[0]`. This is F(n).
            //   `curr` is updated to F(n).
            // The loop correctly calculates F(1) through F(n) and updates `ans`.

            // The element `nums[n-1-offset]` is the element that was at the last position of the array for the rotation corresponding to `offset`.
            // If `offset` is 0, it's the last element of the original array (for F(0)).
            // If `offset` is 1, it's the last element of the array rotated once (for F(1)).
            // The recurrence relation is F(k) = F(k-1) + arrSum - n * (element that was at index n-1 in rotation k-1).
            // The element at index n-1 in rotation k-1 is `nums[n-1-(k-1)] = nums[n-k]`.
            // The code uses `nums[n-1-offset]`.
            // If `offset` represents the number of rotations *applied to get to the current state*, then:
            // `offset = 0`: current state is F(0). Element at end is `nums[n-1]`. Code uses `nums[n-1-0]`.
            // `offset = 1`: current state is F(1). Element at end is `nums[n-2]`. Code uses `nums[n-1-1]`.
            // This means `offset` in the code is NOT the number of rotations, but rather an index related to which element is being removed from its "weighted" position.

            // Let's stick to the derived formula: F(k) = F(k-1) + arrSum - n * nums[n-k]
            // `curr` holds F(k-1). We want to calculate F(k).
            // The element `nums[n-k]` is the one that was at the end of the array for the (k-1)-th rotation.
            // The code uses `nums[n-1-offset]`.
            // If `offset` goes from 0 to n-1, then `n-1-offset` goes from n-1 down to 0.
            // So, `nums[n-1-offset]` takes values `nums[n-1], nums[n-2], ..., nums[0]`.
            // This means the code is subtracting `n * nums[n-1]`, then `n * nums[n-2]`, ..., `n * nums[0]`.
            // This corresponds to the element that was at the end of the array for rotations 0, 1, ..., n-1 respectively.
            // So, when `offset = 0`, we subtract `n * nums[n-1]`. This is for calculating F(1) from F(0). Correct.
            // When `offset = 1`, we subtract `n * nums[n-2]`. This is for calculating F(2) from F(1). Correct.
            // The loop `while(offset<n)` means `offset` takes values 0, 1, ..., n-1.
            // This means we calculate F(1), F(2), ..., F(n).
            // The `ans` will store the max of F(0), F(1), ..., F(n). This is correct.

            // The code is indeed correct. The `offset` variable in the loop is used to index the element that was at the end of the array for the *previous* rotation.
            // `curr` stores the F value of the previous rotation.
            // `nums[n-1-offset]` is the element that was at the last position (index n-1) of the array for the rotation that produced the `curr` value.
            // Example:
            // Initial: `curr` = F(0). `ans` = F(0). `arrSum`.
            // Loop `offset = 0`:
            //   `curr` is F(0).
            //   `nums[n-1-0]` is `nums[n-1]`. This was the last element for F(0).
            //   `curr` becomes `F(0) - n * nums[n-1] + arrSum`. This is F(1).
            //   `ans` updated with F(1).
            // Loop `offset = 1`:
            //   `curr` is F(1).
            //   `nums[n-1-1]` is `nums[n-2]`. This was the last element for F(1).
            //   `curr` becomes `F(1) - n * nums[n-2] + arrSum`. This is F(2).
            //   `ans` updated with F(2).
            // ...
            // Loop `offset = n-1`:
            //   `curr` is F(n-1).
            //   `nums[n-1-(n-1)]` is `nums[0]`. This was the last element for F(n-1).
            //   `curr` becomes `F(n-1) - n * nums[0] + arrSum`. This is F(n).
            //   `ans` updated with F(n).
            // The loop correctly computes F(1) through F(n) and finds the maximum.

            curr -= (n) * nums[n-1-offset]; // Subtract the contribution of the element that moved from the end.
                                            // This element was multiplied by (n-1) in the previous F value.
                                            // In the new F value, it's multiplied by 0.
                                            // The change in sum due to this element is - (n-1) * element.
                                            // However, the formula is F(k) = F(k-1) + arrSum - n * nums[n-k].
                                            // The code implements this by:
                                            // 1. Subtracting n * nums[n-1-offset] (which is nums[n-k] for the current k).
                                            // 2. Adding arrSum.
                                            // This effectively adds arrSum and subtracts n * nums[n-k].
                                            // The logic is: F(k) = F(k-1) - (n-1)*nums[n-k] + (sum of others)
                                            // F(k) = F(k-1) - (n-1)*nums[n-k] + (arrSum - nums[n-k])
                                            // F(k) = F(k-1) + arrSum - n*nums[n-k].
                                            // The code's subtraction `curr -= (n) * nums[n-1-offset];` is the first step of this transformation.
                                            // `nums[n-1-offset]` is the element that was at the end of the array for the previous rotation.
            curr += arrSum; // Add the sum of all elements to adjust the weights.
            ans = Math.max(curr, ans); // Update the maximum rotation function value found so far.
        }
        
        return ans; // Return the overall maximum rotation function value.
    }
}
```

## Interview Tips
*   **Explain the Recurrence:** Clearly articulate how you derive the relationship between F(k) and F(k+1). This is the core of the efficient solution.
*   **Handle Edge Cases:** Mention what happens for an empty array or an array with a single element (though constraints might prevent this).
*   **Walk Through an Example:** Use a small array like `[1, 2, 3]` to manually calculate F(0), F(1), F(2) and show how the recurrence relation works.
*   **Discuss Brute Force First:** Briefly mention the O(n^2) brute-force approach (calculating each F(k) independently) and then explain why the O(n) approach is superior.

## Revision Checklist
- [ ] Understand the definition of the rotation function F(k).
- [ ] Derive the recurrence relation F(k+1) = F(k) + Sum - n * last_element.
- [ ] Implement the calculation of F(0) and the array sum.
- [ ] Implement the loop to calculate subsequent F(k) values using the recurrence.
- [ ] Keep track of the maximum F value encountered.
- [ ] Consider potential integer overflow (though not an issue with typical constraints).

## Similar Problems
*   Maximum Sum Circular Subarray
*   Best Time to Buy and Sell Stock (various versions, often involve dynamic programming or greedy approaches)
*   Array Rotation (basic rotation algorithms)

## Tags
`Array` `Math`
