# Find The Distance Value Between Two Arrays

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Binary Search` `Sorting`  
**Time:** O(N \times M)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int ans=0;
        for(int i=0;i<arr1.length;i++){
            boolean flag = true;
            for(int j=0;j<arr2.length;j++) if(Math.abs(arr1[i] - arr2[j]) <= d){flag=false;break;}
            if(flag) ans++;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Given two arrays and a distance $d$, count elements in `arr1` that have no element in `arr2` within distance $d$.
Iterate through every element in `arr1` and check its absolute difference against every element in `arr2`.

## Intuition
The problem asks us to find how many elements in `arr1` are "far enough" from all elements in `arr2`. The direct brute-force translation is to test every single pair $(arr1[i], arr2[j])$. If we find any element in `arr2` that violates the distance condition (i.e., $|arr1[i] - arr2[j]| \le d$), then $arr1[i]$ is disqualified. If we check all elements in `arr2` and none violate the condition, $arr1[i]$ contributes to our distance value count.

## Algorithm
1. Initialize a counter variable `ans` to 0 to keep track of valid elements in `arr1`.
2. Outer Loop: Iterate through each element in `arr1` using index `i` from 0 to `arr1.length - 1`.
3. Set a boolean flag `flag = true` assuming the current `arr1[i]` satisfies the condition.
4. Inner Loop: Iterate through each element in `arr2` using index `j` from 0 to `arr2.length - 1`.
5. Check if the absolute difference $|arr1[i] - arr2[j]| \le d$. If true, set `flag = false` and break out of the inner loop immediately because `arr1[i]` is already disqualified.
6. After the inner loop completes, check if `flag` is still `true`. If so, increment `ans` by 1.
7. Return `ans` after checking all elements of `arr1`.

## Concept to Remember
- Exhaustive Search / Simulation: Translating the problem statement directly into nested loops.
- Short-circuit Evaluation: Using a `break` statement as soon as a condition fails to optimize unnecessary checks.
- Absolute Value Mapping: Utilizing `Math.abs()` to handle distances regardless of whether `arr1[i]` is greater or smaller than `arr2[j]`.

## Common Mistakes
- Forgetting to reset the `flag` variable to `true` for each new element in `arr1`.
- Confusing the strictness of the inequality (using `< d` instead of `<= d`).
- Returning early from the outer loop instead of continuing to check the remaining elements of `arr1`.
- Forgetting to take the absolute value, leading to negative distance comparisons.

## Complexity Analysis
- Time: $O(N \times M)$ - where $N$ is the length of `arr1` and $M$ is the length of `arr2`, because we potentially compare every element of `arr1` with every element of `arr2`.
- Space: $O(1)$ - as only a constant amount of extra space is used for variables (`ans`, `flag`, loop counters).

## Commented Code
```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Initialize the count of valid elements in arr1 to zero
        int ans = 0;
        // Loop through each element in the first array
        for(int i = 0; i < arr1.length; i++) {
            // Assume the current element in arr1 satisfies the distance condition
            boolean flag = true;
            // Loop through each element in the second array to compare against arr1[i]
            for(int j = 0; j < arr2.length; j++) {
                // Check if the absolute difference is less than or equal to d
                if(Math.abs(arr1[i] - arr2[j]) <= d) {
                    // If condition is met, the element fails the distance criteria
                    flag = false;
                    // No need to check remaining elements in arr2, exit inner loop
                    break;
                }
            }
            // If the flag is still true, no element in arr2 violated the condition
            if(flag) {
                // Increment our valid count
                ans++;
            }
        }
        // Return the final count of valid elements
        return ans;
    }
}
```

## Interview Tips
- Start by explaining the brute-force approach clearly, as it sets a baseline $O(N \times M)$ time complexity.
- Discuss whether `arr2` can be sorted to optimize the search using Binary Search, which would improve the time complexity to $O(N \log M)$.
- Clearly state the constraints of the problem during the interview to justify whether the brute force solution is sufficient.

## Revision Checklist
- [ ] Understand the definition of distance value between two arrays.
- [ ] Trace through the nested loops with a small manual example.
- [ ] Explain why the `break` statement improves practical performance.
- [ ] Discuss the $O(N \log M)$ alternative using sorting and binary search.

## Similar Problems
- `Two Sum`
- `Valid Triangle Number`
- `Count Elements With Strictly Smaller and Greater Elements`

## Tags
`Array` `Two Pointers` `Binary Search` `Sorting`
