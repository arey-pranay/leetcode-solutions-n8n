# Separate The Digits In An Array

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Simulation`  
**Time:** O(N * D)  
**Space:** O(D_total)

---

## Solution (java)

```java
class Solution {
    public int[] separateDigits(int[] nums) {
        ArrayList<Integer> curr = new ArrayList<>();
        for(int i = nums.length-1; i>=0; i--){
            int num = nums[i];
            while(num > 0){
                curr.add(num%10);
                num /= 10;
            }
        }   
        int[] ans = new int[curr.size()];
        for(int i = ans.length-1;i>=0;i--) ans[ans.length-1-i] = curr.get(i);
        return ans;
    }
    
    
}
```

---

---
## Quick Revision
Given an array of positive integers, return an array of all digits from all numbers, in the same order as the original numbers.
Iterate through each number, extract its digits by repeatedly taking the modulo 10 and dividing by 10, and store them.

## Intuition
The core idea is to break down each number into its individual digits. For example, if we have the number `123`, we want to get `1`, `2`, and `3`. The standard way to extract digits from an integer is using the modulo operator (`%`) to get the last digit and integer division (`/`) to remove the last digit. We need to do this for every number in the input array. Since the problem asks for the digits in the *same order* as the original numbers, we should process the input array from left to right. However, when extracting digits from a single number (e.g., `123`), the modulo operation gives us digits in reverse order (`3`, then `2`, then `1`). To maintain the overall order, we can either reverse the extracted digits for each number before adding them to the result, or we can add them in reverse order to a temporary list and then reverse the entire list at the end. The provided solution uses the latter approach by adding digits to `curr` in reverse order and then reversing `curr` into `ans`.

## Algorithm
1. Initialize an empty `ArrayList` called `curr` to store the extracted digits.
2. Iterate through the input array `nums` from the last element to the first element (index `nums.length - 1` down to `0`).
3. For each number `num` in `nums`:
    a. If `num` is `0`, add `0` to the `curr` list.
    b. While `num` is greater than `0`:
        i. Get the last digit of `num` using the modulo operator: `digit = num % 10`.
        ii. Add `digit` to the `curr` list.
        iii. Remove the last digit from `num` by integer division: `num = num / 10`.
4. After processing all numbers, the `curr` list contains all digits, but in reverse order of their appearance within each number and also in reverse order of the original numbers.
5. Create a new integer array `ans` with the same size as `curr`.
6. Iterate through the `curr` list from the last element to the first element.
7. Copy the digits from `curr` into `ans` in the correct order. The element at index `i` in `curr` (from the end) should be placed at index `ans.length - 1 - i` in `ans`.
8. Return the `ans` array.

*Correction based on provided code:* The provided code iterates through `nums` from right to left. For each `num`, it extracts digits using `num % 10` and `num /= 10` and adds them to `curr`. This means digits are added to `curr` in reverse order of their appearance within a number, and the numbers themselves are processed from right to left. Finally, it reverses `curr` into `ans`.

Let's re-align the algorithm to the provided code's logic:
1. Initialize an empty `ArrayList` called `curr` to store the extracted digits.
2. Iterate through the input array `nums` from the last element to the first element (index `nums.length - 1` down to `0`).
3. For each number `num` in `nums`:
    a. If `num` is `0`, add `0` to the `curr` list. (This case is not explicitly handled for `0` in the provided code, but `while(num > 0)` would skip `0` if it were the only digit. For numbers like `10`, `0` would be processed. Assuming positive integers as per typical LeetCode constraints for this problem type.)
    b. While `num` is greater than `0`:
        i. Get the last digit of `num` using the modulo operator: `digit = num % 10`.
        ii. Add `digit` to the `curr` list.
        iii. Remove the last digit from `num` by integer division: `num = num / 10`.
4. After processing all numbers, `curr` contains digits in reverse order of their appearance within each number, and the numbers themselves were processed from right to left. For example, if `nums = [12, 34]`, `curr` would be `[4, 3, 2, 1]`.
5. Create a new integer array `ans` with the same size as `curr`.
6. Iterate from `i = ans.length - 1` down to `0`. In each iteration, `ans[ans.length - 1 - i]` is assigned `curr.get(i)`. This effectively reverses `curr` into `ans`. For `curr = [4, 3, 2, 1]`, `ans` becomes `[1, 2, 3, 4]`.
7. Return the `ans` array.

## Concept to Remember
*   **Integer to Digits Conversion:** Using the modulo operator (`% 10`) to get the last digit and integer division (`/ 10`) to remove the last digit is a standard technique.
*   **Array/List Manipulation:** Understanding how to add elements to an `ArrayList` and how to populate an array from a list, including index management for reversal.
*   **Iteration Order:** Being mindful of whether you need to iterate forwards or backwards through arrays or lists based on the problem's requirements.

## Common Mistakes
*   **Incorrect Digit Extraction Order:** Extracting digits and adding them to the result list without considering their original order (e.g., getting `3, 2, 1` for `123` and not reversing them).
*   **Off-by-One Errors in Loops:** Incorrectly setting loop bounds or indices when populating the final array from the temporary list, especially when reversing.
*   **Handling Zero:** Not considering how to handle the number `0` if it appears in the input array (though the problem statement implies positive integers, it's a good edge case to think about).
*   **Modifying Input Array:** Unintentionally modifying the input array if the problem requires it to remain unchanged (not an issue in this specific solution).

## Complexity Analysis
- Time: O(N * D), where N is the number of elements in `nums` and D is the maximum number of digits in any number in `nums`. This is because we iterate through each number in `nums`, and for each number, we perform operations proportional to its number of digits. The final reversal of the `curr` list also takes time proportional to the total number of digits.
- Space: O(D_total), where D_total is the total number of digits across all numbers in `nums`. This is the space required to store the digits in the `curr` `ArrayList` and then in the `ans` array.

## Commented Code
```java
class Solution {
    public int[] separateDigits(int[] nums) {
        // Initialize an ArrayList to store the extracted digits.
        // This list will temporarily hold digits in reverse order of their appearance within numbers,
        // and also in reverse order of the original numbers processed.
        ArrayList<Integer> curr = new ArrayList<>();

        // Iterate through the input array 'nums' from the last element to the first.
        // This order of processing numbers, combined with how digits are extracted,
        // helps in managing the final order.
        for(int i = nums.length - 1; i >= 0; i--) {
            // Get the current number from the array.
            int num = nums[i];

            // Handle the case where the number is 0.
            // If num is 0, we should add 0 to our list. The while loop below won't execute for 0.
            // Note: The provided code doesn't explicitly handle `num == 0` if it's the only digit.
            // For numbers like 10, the '0' will be processed correctly.
            // Assuming positive integers as per typical problem constraints.

            // Extract digits from the current number 'num' as long as it's greater than 0.
            while(num > 0) {
                // Get the last digit of 'num' using the modulo operator.
                // For example, if num is 123, num % 10 gives 3.
                curr.add(num % 10);

                // Remove the last digit from 'num' using integer division.
                // For example, if num is 123, num / 10 becomes 12.
                num /= 10;
            }
        }

        // Create the result array 'ans' with the exact size needed to hold all extracted digits.
        int[] ans = new int[curr.size()];

        // Populate the 'ans' array by reversing the digits stored in 'curr'.
        // The loop iterates from the end of 'curr' to the beginning.
        // 'ans[ans.length - 1 - i]' is used to place the i-th element from the end of 'curr'
        // into the correct position in 'ans' to achieve the final desired order.
        // For example, if curr = [4, 3, 2, 1], this loop will result in ans = [1, 2, 3, 4].
        for(int i = curr.size() - 1; i >= 0; i--) {
            // The element at index 'i' in 'curr' (from the end) is placed at the
            // corresponding position in 'ans' to reverse the order.
            ans[ans.length - 1 - i] = curr.get(i);
        }

        // Return the array containing all digits in the correct order.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify Input/Output:** Ask about constraints on the input numbers (e.g., are they always positive? Can they be zero? What's the maximum value?). Confirm the exact order required for the output digits.
*   **Trace with Examples:** Walk through a few examples manually, like `[12, 345]` and `[1, 0, 23]`, to ensure your logic correctly handles digit extraction and ordering.
*   **Discuss Alternatives:** Briefly mention alternative approaches, such as converting numbers to strings and then iterating through characters, and explain why the chosen arithmetic approach is often preferred for performance in interviews.
*   **Edge Cases:** Be prepared to discuss edge cases like single-digit numbers, numbers containing zero (e.g., 10, 205), and an empty input array.

## Revision Checklist
- [ ] Understand the problem: Extract all digits from numbers in an array, maintaining order.
- [ ] Digit extraction logic: `num % 10` and `num / 10`.
- [ ] Order of processing numbers: Left-to-right or right-to-left.
- [ ] Order of extracting digits: Does it produce digits in the correct order directly or reversed?
- [ ] Temporary storage: `ArrayList` or similar.
- [ ] Final array construction: Reversing if necessary.
- [ ] Edge cases: Zero, single-digit numbers.
- [ ] Complexity: Time and Space.

## Similar Problems
*   Convert Integer to String
*   String to Integer (Atoi)
*   Reverse Integer
*   Number of Digits in an Integer

## Tags
`Array` `Math` `Simulation`
