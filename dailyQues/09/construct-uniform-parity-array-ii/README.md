# Construct Uniform Parity Array Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean uniformArray(int[] nums1) {
      //even ko odd bnane ke liye smaller odd number
      //odd ko even bnane ke liye smaller odd number

      int smallest = Integer.MAX_VALUE;
      boolean allEven = true;
      
      for(int num : nums1){ smallest = Math.min(smallest,num); if(num % 2 == 1) allEven = false;} // agr sbko even bnana hai, to jo even hai wo fine, and agr koi odd hai to usko even bnane ke liye usse smaller odd chahiye hume, therefore not possible
      
      boolean makeEven  = smallest% 2 == 0 ? true : false; // kyuki 0th number update nhi ho skta. humko saare uske jese bnane hoge
      
      if(!makeEven) return true; // agr sbko odd bnana hai, then jo odd hai wo fine, and jo even hai unme se 0th element hata denge
      else return allEven; // agr sbko even bnana hai, to jo even hai wo fine, but agr koi odd hai to usko even bnane ke liye usse smaller odd chahiye and smallest odd number se zyada smaller odd number nahi mil skta hai
      
      
    }
}
```

---

---
## Quick Revision
The problem asks if an array can be transformed into a uniform parity array by changing at most one element. A uniform parity array has all elements with the same parity (all even or all odd).
The solution checks if it's possible to make all elements even or all elements odd by considering the smallest element and the presence of odd numbers.

## Intuition
The core idea is that if we want to make the entire array uniform in parity, we must be able to change at most one element to match the parity of the rest.
If we aim for all elements to be odd, any even numbers must be convertible to odd. If we aim for all elements to be even, any odd numbers must be convertible to even. The constraint is that we can only change *one* element. This means if there are multiple elements that *need* changing to achieve uniformity, it's impossible. The smallest element plays a crucial role because it's the "base" parity we'd likely aim for if we had to change something. If the smallest element is even, we might aim for all even. If it's odd, we might aim for all odd.

## Algorithm
1. Initialize `smallest` to `Integer.MAX_VALUE` to track the minimum element in the array.
2. Initialize `allEven` to `true`. This flag will become `false` if any odd number is encountered.
3. Iterate through the input array `nums1`:
    a. Update `smallest` with the minimum value seen so far.
    b. If the current number `num` is odd (`num % 2 == 1`), set `allEven` to `false`.
4. Determine the target parity based on the `smallest` element:
    a. If `smallest` is even, set `makeEven` to `true`. This suggests we might aim to make all elements even.
    b. If `smallest` is odd, set `makeEven` to `false`. This suggests we might aim to make all elements odd.
5. Check for possibility based on `makeEven`:
    a. If `makeEven` is `false` (meaning we aim for all odd):
        i. If `allEven` is `true` (all elements were already even), it's impossible to make them all odd by changing only one element if there are multiple even numbers. However, the logic here is slightly simplified in the provided code. The actual condition for "all odd" is that if there's *any* odd number, it's fine. If all are even, and we want all odd, we'd need to change at least one even to odd. The provided code returns `true` if `!makeEven` (aiming for odd), implying that if the smallest is odd, we can potentially make everything odd. This is true if there's at most one even number that needs changing.
    b. If `makeEven` is `true` (meaning we aim for all even):
        i. If `allEven` is `true` (all elements were already even), then the array is already uniform, so return `true`.
        ii. If `allEven` is `false` (meaning there's at least one odd number), we need to check if we can make all elements even. If the smallest element is even, and there's an odd number, we'd need to change that odd number to an even number. If there's *only one* odd number, this is possible. The provided code returns `allEven` in this case. If `allEven` is `true`, it returns `true`. If `allEven` is `false`, it returns `false`. This implies that if we aim for all even and there's an odd number, it's only possible if `allEven` is `true` (which is a contradiction). The logic here is a bit convoluted.

Let's re-evaluate the provided code's logic:
- `smallest = Math.min(smallest,num); if(num % 2 == 1) allEven = false;` : This correctly finds the smallest element and checks if any odd numbers exist.
- `boolean makeEven = smallest % 2 == 0 ? true : false;` : This determines the target parity based on the smallest element. If `smallest` is even, `makeEven` is `true` (aim for all even). If `smallest` is odd, `makeEven` is `false` (aim for all odd).
- `if(!makeEven) return true;` : If `makeEven` is `false` (meaning `smallest` is odd, so we aim for all odd), it returns `true`. This implies that if the smallest element is odd, we can always make the array uniform by making all elements odd. This is only true if there's at most one even number in the array.
- `else return allEven;` : If `makeEven` is `true` (meaning `smallest` is even, so we aim for all even), it returns the `allEven` flag. If `allEven` is `true`, it means all numbers were already even, so it's uniform. If `allEven` is `false`, it means there was at least one odd number. If we aim for all even and there's an odd number, it's only possible if there's *exactly one* odd number. The code returns `false` if `allEven` is `false`, which is incorrect. It should return `true` if there's exactly one odd number.

**Corrected Intuition/Algorithm for the problem as stated:**
The problem is about whether we can make the array uniform by changing *at most one* element.
1. Count the number of even elements (`evenCount`) and odd elements (`oddCount`).
2. If `evenCount == nums1.length` or `oddCount == nums1.length`, the array is already uniform. Return `true`.
3. If `evenCount == 1` and `oddCount == nums1.length - 1`, we can change the single even element to odd. Return `true`.
4. If `oddCount == 1` and `evenCount == nums1.length - 1`, we can change the single odd element to even. Return `true`.
5. Otherwise, it's impossible to make the array uniform by changing at most one element. Return `false`.

The provided code seems to be attempting a different interpretation or has a flawed logic for the stated problem. Assuming the problem *is* "Construct Uniform Parity Array II" as per LeetCode, the provided code is incorrect.

Let's analyze the provided code *as is* and explain its (flawed) logic:
1. Find the smallest element (`smallest`) and check if all elements are even (`allEven`).
2. Determine the "target parity" based on `smallest`. If `smallest` is even, `makeEven` is true (aim for all even). If `smallest` is odd, `makeEven` is false (aim for all odd).
3. If `makeEven` is false (aim for all odd): The code returns `true`. This implies that if the smallest element is odd, we can always make the array uniform by making all elements odd. This is only true if there's at most one even number.
4. If `makeEven` is true (aim for all even): The code returns `allEven`. If `allEven` is true, it means all elements were already even, so it's uniform. If `allEven` is false, it means there was at least one odd number. The code returns `false` in this case, implying that if the smallest is even and there's an odd number, it's impossible. This is incorrect; it's possible if there's exactly one odd number.

## Algorithm (Based on the provided code's logic, acknowledging its flaws)
1. Initialize `smallest` to `Integer.MAX_VALUE`.
2. Initialize `allEven` to `true`.
3. Iterate through `nums1`:
    a. Update `smallest = Math.min(smallest, num)`.
    b. If `num % 2 == 1`, set `allEven = false`.
4. Determine `makeEven`: `makeEven = (smallest % 2 == 0)`.
5. If `!makeEven` (i.e., `smallest` is odd, aiming for all odd): Return `true`.
6. Else (`makeEven` is true, i.e., `smallest` is even, aiming for all even): Return `allEven`.

## Concept to Remember
*   Parity: Whether a number is even or odd.
*   Array Traversal: Iterating through array elements to perform operations.
*   Conditional Logic: Using `if-else` statements to control program flow based on conditions.
*   Minimum Value Tracking: Using `Math.min` to find the smallest element.

## Common Mistakes
*   Misinterpreting "at most one element": The core constraint is crucial.
*   Incorrectly handling the "all even" vs. "all odd" target parities.
*   Flawed logic for edge cases where only one element needs changing.
*   Not considering the case where the array is already uniform.
*   The provided code's logic is fundamentally flawed for the stated problem.

## Complexity Analysis
*   Time: O(N) - The code iterates through the array once.
*   Space: O(1) - The code uses a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public boolean uniformArray(int[] nums1) {
      // Initialize smallest to the maximum possible integer value to ensure any number in the array will be smaller.
      int smallest = Integer.MAX_VALUE;
      // Initialize a flag to track if all numbers encountered so far are even. Assume true initially.
      boolean allEven = true;
      
      // Iterate through each number in the input array nums1.
      for(int num : nums1){ 
          // Update 'smallest' to be the minimum of its current value and the current number 'num'.
          smallest = Math.min(smallest,num); 
          // If the current number 'num' is odd (remainder 1 when divided by 2), set 'allEven' to false.
          if(num % 2 == 1) allEven = false;
      } 
      
      // Determine the target parity based on the smallest element.
      // If 'smallest' is even, 'makeEven' will be true (aim to make all elements even).
      // If 'smallest' is odd, 'makeEven' will be false (aim to make all elements odd).
      boolean makeEven  = smallest % 2 == 0 ? true : false; 
      
      // If the target parity is odd (!makeEven is true, meaning smallest is odd):
      // The provided code returns true. This implies that if the smallest element is odd, it's always possible to make the array uniform by making all elements odd. This is only true if there's at most one even number.
      if(!makeEven) return true; 
      // Else (if the target parity is even, makeEven is true, meaning smallest is even):
      // Return the 'allEven' flag.
      // If 'allEven' is true, it means all numbers were already even, so the array is uniform.
      // If 'allEven' is false, it means there was at least one odd number. The code returns false here, implying it's impossible. This is incorrect; it's possible if there's exactly one odd number.
      else return allEven; 
    }
}
```

## Interview Tips
*   Clarify the problem statement precisely: Ensure you understand "at most one element" and what "uniform parity" means.
*   Walk through examples: Test with arrays that are already uniform, arrays needing one change, and arrays needing multiple changes.
*   Explain your approach clearly: Start with the high-level idea and then detail the steps.
*   Discuss edge cases: Consider empty arrays, arrays with one element, and arrays with all same elements.
*   Be prepared to correct your logic: If your initial approach is flawed (like the provided code), acknowledge it and pivot to a correct solution.

## Revision Checklist
- [ ] Understand the definition of uniform parity.
- [ ] Identify the constraint of changing "at most one" element.
- [ ] Develop a strategy to count even and odd numbers.
- [ ] Handle cases where the array is already uniform.
- [ ] Handle cases where exactly one element needs changing.
- [ ] Implement the logic correctly for both "all even" and "all odd" targets.

## Similar Problems
*   Construct Uniform Parity Array I (LeetCode 2166) - This problem is likely a variation or a simpler version.
*   Check if Array is Sorted and Rotated (LeetCode 1752) - Similar in checking structural properties.
*   Partition Array into Two Arrays to Minimize Sum Difference (LeetCode 2035) - Involves partitioning based on properties.

## Tags
`Array` `Logic` `Parity`
