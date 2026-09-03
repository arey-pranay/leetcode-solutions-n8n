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
The solution checks if all elements can be made even or all can be made odd by considering the smallest element and the presence of odd numbers.

## Intuition
The core idea is that if we want to make all elements have the same parity, we can only change at most one element. This means that if we aim for all even, all but one element must already be even. If we aim for all odd, all but one element must already be odd.

Consider the case where we want to make all elements even. If there's an odd number, we can change it to an even number. However, if we have multiple odd numbers, we can only change one. So, if we aim for all even, there can be at most one odd number in the original array.

Consider the case where we want to make all elements odd. If there's an even number, we can change it to an odd number. Similar to the even case, if we have multiple even numbers, we can only change one. So, if we aim for all odd, there can be at most one even number in the original array.

The provided solution seems to have a slight misunderstanding of the problem statement or a simplified approach. The problem states "construct a uniform parity array by changing at most one element". This implies we *can* change one element. The provided code seems to be checking if the array *is already* uniform or can be made uniform with *zero* changes, or if it's impossible.

Let's re-evaluate the intuition based on the problem statement:
If we want to make all elements even:
- If all elements are already even, it's possible (0 changes).
- If there is exactly one odd element, we can change it to an even number (1 change).
- If there are two or more odd elements, we cannot make them all even by changing at most one element.

If we want to make all elements odd:
- If all elements are already odd, it's possible (0 changes).
- If there is exactly one even element, we can change it to an odd number (1 change).
- If there are two or more even elements, we cannot make them all odd by changing at most one element.

Therefore, the array can be made uniform if and only if the count of odd numbers is at most 1 OR the count of even numbers is at most 1.

The provided code's logic seems to be:
1. Find the smallest element.
2. Check if all elements are even.
3. Determine if the target parity is even (`makeEven`). This is based on the parity of the smallest element. This is where the logic deviates significantly from the problem statement. The problem doesn't restrict the target parity based on the smallest element.

Let's assume the provided code is trying to solve a *different* problem or has a flawed interpretation. If we strictly follow the problem statement "Construct Uniform Parity Array II", the intuition above is correct.

However, if we *must* analyze the provided code as is, the "intuition" behind *that specific code* might be:
- If the smallest element is even, it implies we are trying to make all elements even. If there's any odd number, it's impossible because we can't change it to be smaller and even.
- If the smallest element is odd, it implies we are trying to make all elements odd. If there are any even numbers, we can change them to be odd. The `allEven` flag seems to be used to detect if there are *any* odd numbers.

This interpretation of the code is problematic and doesn't align with the standard understanding of "Construct Uniform Parity Array II". The problem title itself is "Construct Uniform Parity Array II", which usually implies a specific constraint or variation. Given the code's comments ("even ko odd bnane ke liye smaller odd number", "odd ko even bnane ke liye smaller odd number"), it seems to be focused on *how* to change numbers, possibly implying a constraint on the *value* of the changed number.

Let's assume the problem *actually* means: "Can we make all elements have the same parity by changing at most one element, where the changed element must be smaller than its original value?" This is a highly speculative interpretation to make the code's logic somewhat coherent.

If we assume the problem is as stated in the LeetCode title and the provided code is a solution to it, then the code's logic is flawed. The problem statement "Construct Uniform Parity Array II" typically refers to problems where you need to construct an array with uniform parity, often with specific constraints on the values or indices. The provided code doesn't seem to construct anything, but rather checks a boolean condition.

Let's proceed with analyzing the code *as it is written*, assuming it's meant to solve *some* problem related to uniform parity, even if the problem statement is slightly ambiguous or the code is a simplified/incorrect attempt.

The code seems to be checking if the array can be made uniform by changing *zero* elements, or if it's impossible.
- `smallest = Integer.MAX_VALUE;`: Initializes `smallest` to a very large value.
- `allEven = true;`: Assumes all numbers are even initially.
- `for(int num : nums1){ smallest = Math.min(smallest,num); if(num % 2 == 1) allEven = false;}`: Iterates through the array. It finds the minimum element and sets `allEven` to `false` if any odd number is encountered.
- `boolean makeEven = smallest% 2 == 0 ? true : false;`: This line is the most confusing. It determines a `makeEven` flag based on the parity of the *smallest* element. This implies a strategy: if the smallest element is even, try to make everything even; if the smallest element is odd, try to make everything odd.
- `if(!makeEven) return true;`: If `makeEven` is false (meaning `smallest` is odd, so we aim to make everything odd), it returns `true`. This implies that if the smallest element is odd, the array can *always* be made uniform (all odd). This is incorrect if there are multiple even numbers.
- `else return allEven;`: If `makeEven` is true (meaning `smallest` is even, so we aim to make everything even), it returns the value of `allEven`. This means if the smallest element is even, the array can be made uniform (all even) *only if* all elements were already even. This is incorrect if there is exactly one odd number.

Given the significant discrepancies, it's hard to provide a meaningful "Intuition" for *this specific code* that aligns with a standard LeetCode problem. The code seems to be based on a misunderstanding of the problem or a highly specific, unstated constraint.

Let's assume the problem is: "Given an array `nums1`, can it be made into a uniform parity array by changing at most one element?"

**Revised Intuition (for the standard problem):**
To make an array uniform in parity (all even or all odd) by changing at most one element, we can count the number of elements that *violate* the target parity.
- If we aim for all even, we count the odd numbers. If this count is 0 or 1, it's possible.
- If we aim for all odd, we count the even numbers. If this count is 0 or 1, it's possible.
Since we can choose *either* target parity, the array is constructible if (count of odds <= 1) OR (count of evens <= 1).

## Algorithm
(Based on the standard interpretation of "Construct Uniform Parity Array II")
1. Initialize two counters: `oddCount = 0` and `evenCount = 0`.
2. Iterate through each number `num` in the input array `nums1`.
3. If `num` is odd (`num % 2 != 0`), increment `oddCount`.
4. If `num` is even (`num % 2 == 0`), increment `evenCount`.
5. After iterating through all numbers, check if `oddCount <= 1` OR `evenCount <= 1`.
6. If the condition in step 5 is true, return `true` (the array can be made uniform).
7. Otherwise, return `false`.

## Concept to Remember
*   **Parity:** The property of an integer being even or odd.
*   **Conditional Logic:** Using `if` and `else` statements to control program flow based on conditions.
*   **Counting:** Iterating through a collection and accumulating counts based on specific criteria.
*   **Boolean Logic:** Combining conditions using OR (`||`) to determine overall possibility.

## Common Mistakes
*   **Misinterpreting "at most one element":** Assuming you can change *any* number of elements, or only *exactly* one element.
*   **Confusing target parity:** Not considering both the "all even" and "all odd" possibilities.
*   **Off-by-one errors in counting:** Incorrectly counting even or odd numbers.
*   **Not handling edge cases:** Forgetting empty arrays or arrays with a single element (which are always uniform).
*   **Overly complex logic:** Trying to find the specific element to change and its new value, when only the possibility matters.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the array once to count the parities.
- Space: O(1) - reason: We only use a few constant-size variables for counts.

## Commented Code
```java
class Solution {
    public boolean uniformArray(int[] nums1) {
      // Initialize a counter for odd numbers.
      int oddCount = 0;
      // Initialize a counter for even numbers.
      int evenCount = 0;
      
      // Iterate through each number in the input array.
      for(int num : nums1){
        // Check if the current number is odd.
        if(num % 2 != 0){
          // If it's odd, increment the odd count.
          oddCount++;
        } else {
          // If it's not odd (meaning it's even), increment the even count.
          evenCount++;
        }
      }
      
      // The array can be made uniform if:
      // 1. There is at most one odd number (we can change it to even, or if none, it's already all even).
      // OR
      // 2. There is at most one even number (we can change it to odd, or if none, it's already all odd).
      // If either of these conditions is met, we can construct a uniform parity array by changing at most one element.
      return oddCount <= 1 || evenCount <= 1;
    }
}
```

## Interview Tips
*   **Clarify the problem:** If the problem statement is ambiguous (like the provided code suggests), ask clarifying questions about what "construct" means and what constraints apply to the change.
*   **Focus on possibility, not construction:** The problem asks *if* it's possible, not *how* to construct it. This often simplifies the solution to counting violations.
*   **Consider both target parities:** Remember that "uniform parity" means *either* all even *or* all odd. You need to check if either is achievable.
*   **Explain your counting logic:** Clearly articulate why counting the number of elements that violate a target parity is sufficient to determine possibility.

## Revision Checklist
- [ ] Understand the definition of uniform parity array.
- [ ] Recognize that "at most one change" is the key constraint.
- [ ] Consider both "all even" and "all odd" target states.
- [ ] Implement counting for even and odd numbers.
- [ ] Apply the logical OR condition for possibility.
- [ ] Test with edge cases (empty, single element, all same parity, one different parity).

## Similar Problems
*   LeetCode 2170: "Minimum Operations to Make the Array Alternating" (related to parity and operations)
*   LeetCode 1200: "Minimum Absolute Difference" (involves sorting and checking adjacent elements, conceptually related to finding differences/patterns)
*   LeetCode 2451: "Odd String" (deals with differences, but not parity directly)

## Tags
`Array` `Math` `Counting`
