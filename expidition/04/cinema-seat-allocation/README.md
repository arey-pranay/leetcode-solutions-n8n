# Cinema Seat Allocation

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Prefix Sum`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] preProds = new int[n];
        int[] suffProds = new int[n];
        preProds[0] = 1;
        suffProds[n-1] = 1;
        for(int i=1;i<n;i++) preProds[i] = preProds[i-1]*nums[i-1];
        for(int i=n-2;i>=0;i--) suffProds[i] = suffProds[i+1]*nums[i+1];        
        for(int i=0;i<n;i++)nums[i] = preProds[i]*suffProds[i];
        return nums;
    }
}
```

---

---
## Quick Revision
The problem is to calculate the product of all numbers in an array except for each number itself. This can be solved by using prefix and suffix products.

## Intuition
The key insight here is that we can compute the product of all preceding numbers (prefix product) and the product of all succeeding numbers (suffix product) simultaneously, storing them in two separate arrays. Then, we multiply corresponding elements from these two arrays to get the final result.

## Algorithm
1. Initialize two arrays `preProds` and `suffProds` of size `n`, where `n` is the length of the input array.
2. Set `preProds[0] = 1` and `suffProds[n-1] = 1`.
3. For `i=1` to `n-1`, compute `preProds[i] = preProds[i-1]*nums[i-1]`.
4. For `i=n-2` down to `0`, compute `suffProds[i] = suffProds[i+1]*nums[i+1]`.
5. Iterate through the input array and for each element, multiply its corresponding `preProds` and `suffProds` values.

## Concept to Remember
* **Prefix sum** arrays: an array where each element is the sum of all elements up to that index.
* **Suffix sum** arrays: an array where each element is the sum of all elements from that index down to the end.
* **Simultaneous iteration**: two loops running in parallel, one for prefix and one for suffix.

## Common Mistakes
* Not initializing `preProds` and `suffProds` correctly.
* Failing to multiply corresponding elements of `preProds` and `suffProds`.
* Misunderstanding the difference between prefix and suffix products.

## Complexity Analysis
- Time: O(n) - we are doing two passes through the array.
- Space: O(n) - we need extra space for `preProds` and `suffProds`.

## Commented Code
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Initialize preProds array with 1 at index 0
        int n = nums.length;
        int[] preProds = new int[n];
        preProds[0] = 1; // initialize first element to 1

        // Compute prefix products in parallel
        for(int i=1;i<n;i++) {
            // Multiply current element with previous prefix product
            preProds[i] = preProds[i-1]*nums[i-1];
        }

        // Initialize suffProds array with 1 at index n-1
        int[] suffProds = new int[n];
        suffProds[n-1] = 1; // initialize last element to 1

        // Compute suffix products in parallel
        for(int i=n-2;i>=0;i--) {
            // Multiply current element with next suffix product
            suffProds[i] = suffProds[i+1]*nums[i+1];
        }

        // Multiply corresponding elements of preProds and suffProds arrays
        for(int i=0;i<n;i++) {
            nums[i] = preProds[i]*suffProds[i];
        }
        return nums;
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly before starting to code.
* Think of ways to optimize the solution, such as using prefix and suffix products simultaneously.
* Practice explaining your thought process to a mock interviewer.

## Revision Checklist
- [ ] Understand the concept of prefix and suffix sums.
- [ ] Implement the solution correctly without errors.
- [ ] Optimize the solution for performance.

## Similar Problems
* Product of Array Except Self II (LeetCode #992)
* Average Salaries for Employees (LeetCode #1507)

## Tags
`Array` `Hash Map` `Prefix Sum` `Suffix Sum`

## My Notes
suffix product and prefix product
