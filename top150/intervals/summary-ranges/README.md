# Summary Ranges

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        int i=0;
        while(i<n){
            int start = nums[i]; int j = i+1;
            while(j<n && nums[j]-nums[j-1] == 1) j++;
            if(j<n+1 && start != nums[j-1])ans.add(new String(""+start+ "->" + nums[j-1]));
            else ans.add(new String(""+start));
            i=j;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Given a sorted unique integer array, return a list of strings representing the ranges of consecutive numbers.
Iterate through the array, identifying the start and end of each consecutive sequence.

## Intuition
The core idea is to scan the sorted array and identify contiguous segments of numbers. When we find a number that breaks the sequence (i.e., it's not one greater than the previous number), we know the current range has ended. We then format this range as a string and continue scanning from the breaking point.

## Algorithm
1. Initialize an empty list `ans` to store the resulting range strings.
2. Get the length of the input array `nums` and store it in `n`.
3. Initialize a pointer `i` to 0, representing the start of the current potential range.
4. While `i` is less than `n`:
    a. Set `start` to `nums[i]`, marking the beginning of the current range.
    b. Initialize a second pointer `j` to `i + 1`.
    c. While `j` is less than `n` AND `nums[j]` is exactly one greater than `nums[j-1]` (i.e., `nums[j] - nums[j-1] == 1`):
        i. Increment `j`. This expands the current consecutive range.
    d. After the inner loop, `j` points to the first element *after* the consecutive range (or `n` if the range goes to the end of the array).
    e. Check if the range consists of more than one number: `start != nums[j-1]`.
        i. If true, the range is `start` to `nums[j-1]`. Add the string `start + "->" + nums[j-1]` to `ans`.
        ii. If false, the range is just a single number `start`. Add the string `start` to `ans`.
    f. Update `i` to `j` to start searching for the next range from the element after the current one.
5. Return the `ans` list.

## Concept to Remember
*   **Iterative Traversal:** Efficiently processing elements in a sequence.
*   **Two Pointers:** Using multiple pointers to track different positions or states within an array.
*   **Range Identification:** Detecting and defining contiguous sub-sequences.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling the end of a range or the loop termination conditions.
*   **String formatting:** Incorrectly concatenating numbers and arrows for the output strings.
*   **Handling single-element ranges:** Not correctly distinguishing between a single number and a range of two or more numbers.
*   **Empty input array:** Not considering the edge case where `nums` is empty.

## Complexity Analysis
- Time: O(n) - reason The outer `while` loop iterates through the array once. The inner `while` loop also advances `j`, and `j` never goes backward. Therefore, each element is visited at most a constant number of times.
- Space: O(1) - reason The space used is for the `ans` list, which in the worst case (no consecutive numbers) stores `n` strings, each of length at most constant (e.g., "1->2"). However, if we consider the output space, it could be O(n). If we only consider auxiliary space excluding output, it's O(1).

## Commented Code
```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        // Initialize an empty list to store the summary ranges.
        List<String> ans = new ArrayList<>();
        // Get the total number of elements in the input array.
        int n = nums.length;
        // Initialize the main pointer 'i' to the start of the array.
        int i = 0;
        // Loop through the array as long as 'i' is within bounds.
        while (i < n) {
            // 'start' marks the beginning of the current consecutive range.
            int start = nums[i];
            // Initialize a second pointer 'j' to the element after 'i'.
            int j = i + 1;
            // Inner loop: continue as long as 'j' is within bounds AND the current element is consecutive to the previous one.
            while (j < n && nums[j] - nums[j - 1] == 1) {
                // Move 'j' forward to extend the current consecutive range.
                j++;
            }
            // After the inner loop, 'j' points to the first element *not* in the consecutive range, or 'n' if the range goes to the end.
            // Check if the range contains more than one element (i.e., start is not the same as the last element of the range).
            if (j < n + 1 && start != nums[j - 1]) {
                // If it's a range of multiple numbers, format it as "start->end".
                ans.add(new String("" + start + "->" + nums[j - 1]));
            } else {
                // If it's a single number range, just add the number itself.
                ans.add(new String("" + start));
            }
            // Move the main pointer 'i' to 'j' to start processing the next potential range.
            i = j;
        }
        // Return the list of summary ranges.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify Edge Cases:** Ask about empty arrays, arrays with one element, and arrays with all consecutive numbers.
*   **Explain Two Pointers:** Clearly articulate why using two pointers (`i` and `j`) is efficient for identifying contiguous segments.
*   **Trace an Example:** Walk through a small example like `[0,1,2,4,5,7]` to demonstrate your algorithm's logic step-by-step.
*   **Discuss Output Format:** Confirm the exact string format required for single numbers versus ranges.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the core task: finding consecutive number sequences.
- [ ] Devise an iterative approach.
- [ ] Implement the two-pointer technique correctly.
- [ ] Handle single-element ranges and multi-element ranges.
- [ ] Ensure correct string formatting for the output.
- [ ] Test with edge cases: empty array, single element, all consecutive, no consecutive.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge Intervals
*   Find All Numbers Disappeared in an Array
*   Missing Ranges

## Tags
`Array` `Two Pointers` `String`
