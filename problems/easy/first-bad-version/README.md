# First Bad Version

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Binary Search` `Interactive`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int first = 1; 
        int last = n;
        int ans =0;
        while(first<=last){
            int m = first+(last-first)/2;
            if(isBadVersion(m)) {
                ans = m;
                last = m-1;
            }
            else first = m+1 ;
        }
        return ans ;
    }
}
```

---

---
## Quick Revision
Given a system where versions are bad from a certain point onwards, find the first bad version.
This problem is solved using Binary Search to efficiently narrow down the search space.

## Intuition
The problem states that all versions *after* a certain point are bad. This implies a sorted property: `false, false, ..., false, true, true, ..., true`. We are looking for the first `true`. This sorted nature immediately suggests a binary search approach. Instead of linearly checking each version, we can jump to the middle and eliminate half of the search space based on whether the middle version is bad or not. If the middle version is bad, it *could* be the first bad version, or the first bad version is *before* it. If the middle version is good, then the first bad version *must* be *after* it.

## Algorithm
1. Initialize `first` to 1 (the first possible version).
2. Initialize `last` to `n` (the last possible version).
3. Initialize `ans` to 0 (or any placeholder, as it will be updated). This variable will store the potential first bad version found so far.
4. While `first` is less than or equal to `last`:
    a. Calculate the middle version `m` using `m = first + (last - first) / 2`. This prevents potential integer overflow compared to `(first + last) / 2`.
    b. Call `isBadVersion(m)`:
        i. If `isBadVersion(m)` returns `true` (meaning `m` is a bad version):
            - This `m` is a candidate for the first bad version. Store it in `ans`.
            - Since we are looking for the *first* bad version, the actual first bad version could be `m` or any version *before* `m`. So, we narrow our search space to the left half by setting `last = m - 1`.
        ii. If `isBadVersion(m)` returns `false` (meaning `m` is a good version):
            - This means all versions up to and including `m` are good. The first bad version *must* be *after* `m`.
            - We narrow our search space to the right half by setting `first = m + 1`.
5. Return `ans`.

## Concept to Remember
*   **Binary Search:** Efficiently searching a sorted or monotonically increasing/decreasing data structure by repeatedly dividing the search interval in half.
*   **Monotonicity:** The property of the versions (good then bad) allows binary search.
*   **Edge Cases:** Handling the boundaries of the search space correctly.
*   **Integer Overflow Prevention:** Using `first + (last - first) / 2` for midpoint calculation.

## Common Mistakes
*   Incorrectly updating `first` or `last` pointers, leading to infinite loops or skipping the correct answer.
*   Not handling the case where the first version itself is bad.
*   Using `(first + last) / 2` which can lead to integer overflow for very large `n`.
*   Not storing the potential answer (`ans`) when `isBadVersion(m)` is true, and only moving `last`. This would cause the loop to terminate without capturing the first bad version.

## Complexity Analysis
*   Time: O(log n) - reason: Binary search halves the search space in each iteration.
*   Space: O(1) - reason: Only a few variables are used, regardless of the input size.

## Commented Code
```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        // Initialize the start of our search range to the first version.
        int first = 1; 
        // Initialize the end of our search range to the last version.
        int last = n;
        // Initialize a variable to store the first bad version found so far.
        // It will be updated whenever we find a bad version.
        int ans = 0; 
        
        // Continue searching as long as our search range is valid (first <= last).
        while(first <= last){
            // Calculate the middle version to check.
            // Using (last - first) / 2 prevents potential integer overflow.
            int m = first + (last - first) / 2; 
            
            // Check if the middle version is bad using the provided API.
            if(isBadVersion(m)) {
                // If 'm' is a bad version, it's a potential candidate for the first bad version.
                // Store 'm' as our current best answer.
                ans = m; 
                // Since 'm' is bad, the first bad version could be 'm' or any version before it.
                // So, we narrow our search to the left half (including 'm' is not needed anymore).
                last = m - 1; 
            } else { 
                // If 'm' is NOT a bad version (it's good), then the first bad version
                // must be in the versions *after* 'm'.
                // So, we narrow our search to the right half.
                first = m + 1; 
            }
        }
        // After the loop, 'ans' will hold the smallest version number that was found to be bad.
        return ans; 
    }
}
```

## Interview Tips
*   Clearly explain the binary search approach and why it's applicable due to the problem's monotonic property.
*   Walk through an example with `n=5` and a first bad version at `4` to demonstrate how `first`, `last`, and `ans` change.
*   Mention the integer overflow prevention technique for calculating the midpoint.
*   Be prepared to discuss the time and space complexity.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the monotonic property of the versions.
- [ ] Implement binary search correctly.
- [ ] Handle pointer updates (`first`, `last`) accurately.
- [ ] Store the potential answer (`ans`) when a bad version is found.
- [ ] Prevent integer overflow in midpoint calculation.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Search in Rotated Sorted Array
*   Find Minimum in Rotated Sorted Array
*   Median of Two Sorted Arrays
*   Search Insert Position

## Tags
`Binary Search` `Two Pointers`
