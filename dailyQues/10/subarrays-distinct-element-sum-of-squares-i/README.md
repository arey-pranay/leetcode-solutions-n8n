# Subarrays Distinct Element Sum Of Squares I

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Binary Indexed Tree` `Segment Tree`  
**Time:** O(n^2)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int sumCounts(List<Integer> nums) {
        int ans = 0;
        int n = nums.size();
        for(int i=0;i<n;i++){
            boolean[] seen = new boolean[101];
            int distinct=0;
            for(int j=i;j<n;j++){
                int x = nums.get(j);
                if(!seen[x]){seen[x]=true; distinct++;}
                ans+= (distinct*distinct);
            }
        }
        return ans;
    }
}
// 1 2 1
// i
// j

// i=0
//     j=0 => 1
//     j=1 => 1,2
// ans =  1 + 2
```

---

---
## Quick Revision
Solve for the sum of squares of the number of distinct elements in all subarrays.
This problem can be solved using a sliding window approach and maintaining a boolean array to track seen elements.

## Intuition
The idea is to fix a starting point `i` and iterate from `i` to the end of the array. For each subarray starting from `i`, we count the number of distinct elements and add the square of this count to the result. This process is repeated for each possible starting point `i`.

## Algorithm
1. Initialize the result variable `ans` to 0.
2. Iterate over each starting point `i` from 0 to `n-1` (where `n` is the length of the input array `nums`).
3. For each `i`, initialize a boolean array `seen` of size 101 to keep track of seen elements. Initialize a variable `distinct` to 0 to count distinct elements in the current subarray.
4. Iterate from `i` to `n-1` (inclusive) and for each element `x` at index `j`:
   - If `x` is not seen before, mark it as seen and increment `distinct`.
   - Add the square of `distinct` to the result `ans`.
5. Repeat steps 3-4 for all starting points `i`.

## Concept to Remember
* Sliding window technique
* Maintaining a boolean array to track seen elements
* Counting distinct elements in a subarray

## Common Mistakes
* Not initializing the `seen` array correctly
* Not resetting the `distinct` count for each subarray
* Not adding the square of the distinct count to the result correctly

## Complexity Analysis
- Time: O(n^2) - We have a nested loop structure where we are iterating from `i` to `n-1` for each `i`.
- Space: O(n) - We are using a boolean array of size 101 to keep track of seen elements, and in the worst case, we are processing all elements in the input array.

## Commented Code
```java
class Solution {
    public int sumCounts(List<Integer> nums) {
        int ans = 0; // Initialize result variable
        int n = nums.size(); // Get the length of the input array
        for (int i = 0; i < n; i++) {
            boolean[] seen = new boolean[101]; // Initialize seen array for each subarray
            int distinct = 0; // Initialize distinct count for each subarray
            for (int j = i; j < n; j++) {
                int x = nums.get(j); // Get the current element
                if (!seen[x]) { // If the element is not seen before
                    seen[x] = true; // Mark it as seen
                    distinct++; // Increment distinct count
                }
                ans += (distinct * distinct); // Add the square of distinct count to the result
            }
        }
        return ans; // Return the final result
    }
}
```

## Interview Tips
* Be careful with the initialization of the `seen` array and the `distinct` count.
* Make sure to understand the problem clearly and the approach used to solve it.
* Practice explaining the solution in detail, especially the sliding window technique.

## Revision Checklist
- [ ] Understand the problem clearly
- [ ] Review the sliding window technique
- [ ] Practice explaining the solution in detail
- [ ] Review the complexity analysis

## Similar Problems
* Subarrays with Distinct Elements
* Subarray with Given Difference
* Subarray with K Distinct Elements

## Tags
`Array` `Hash Map` `Sliding Window` `Boolean Array`
