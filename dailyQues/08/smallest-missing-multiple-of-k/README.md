# Smallest Missing Multiple Of K

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table`  
**Time:** O(N + M)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        int ans = k; 
        for(int num : set)if(set.contains(ans)) ans+=k;
        return ans;
    }
}
```

---

---
## Quick Revision
Find the smallest positive integer that is a multiple of `k` but is not present in the given array `nums`.
We iterate through multiples of `k` starting from `k` itself, checking for their presence in a hash set of `nums`.

## Intuition
The problem asks for the smallest multiple of `k` that is *missing* from `nums`. This means we are looking for `k`, `2k`, `3k`, `4k`, and so on, until we find one that isn't in `nums`. A hash set is an efficient way to check for the existence of an element. So, the intuition is to store all numbers from `nums` in a hash set for quick lookups, and then sequentially check multiples of `k` against this set. The first multiple of `k` we don't find in the set is our answer.

## Algorithm
1. Create a `HashSet` to store all elements from the input array `nums`.
2. Iterate through the `nums` array and add each element to the `HashSet`.
3. Initialize a variable `ans` to `k` (the first multiple of `k` to check).
4. Start a loop that continues as long as the `HashSet` contains the current value of `ans`.
5. Inside the loop, if `ans` is found in the `HashSet`, increment `ans` by `k` to check the next multiple.
6. Once the loop terminates (meaning `ans` is not in the `HashSet`), return `ans`.

## Concept to Remember
*   **Hash Sets:** Efficient data structure for checking membership (O(1) average time complexity).
*   **Multiples:** Understanding how to generate sequential multiples of a given number.
*   **Iterative Search:** Employing a loop to find the first element that satisfies a condition.

## Common Mistakes
*   **Inefficient Lookup:** Not using a hash set and instead iterating through the `nums` array for each multiple of `k`, leading to O(N*M) complexity where M is the number of multiples checked.
*   **Starting Point:** Forgetting to start checking from `k` itself, or starting from 0 or 1.
*   **Integer Overflow:** While unlikely for typical LeetCode constraints on this problem, in general, be mindful of potential integer overflows if `k` and the number of multiples can become very large.
*   **Modulus Operator Misuse:** Trying to use the modulus operator to find missing multiples directly, which is less straightforward than generating and checking.

## Complexity Analysis
*   Time: O(N + M) - where N is the number of elements in `nums` and M is the number of multiples of `k` checked. Building the hash set takes O(N). In the worst case, we might check up to M multiples of `k`. Each check in the hash set is O(1) on average.
*   Space: O(N) - to store the elements of `nums` in the `HashSet`.

## Commented Code
```java
class Solution {
    public int missingMultiple(int[] nums, int k) {
        // Create a HashSet to store all numbers from the input array for efficient lookups.
        HashSet<Integer> set = new HashSet<>();
        
        // Iterate through each number in the input array 'nums'.
        for(int num : nums) {
            // Add the current number to the HashSet.
            set.add(num);
        }
        
        // Initialize 'ans' to 'k', as it's the smallest possible multiple of k we need to check.
        int ans = k; 
        
        // Loop indefinitely until we find a multiple of k that is not in the set.
        // The condition 'set.contains(ans)' checks if the current multiple 'ans' is present in the set.
        // If it is present, we need to check the next multiple.
        while(set.contains(ans)) {
            // If 'ans' is found in the set, increment 'ans' by 'k' to move to the next multiple of k.
            ans += k;
        }
        
        // Once the loop breaks, 'ans' holds the smallest multiple of k that was not found in the set.
        // Return this value as the result.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the range of `nums` elements, `k`, and the size of `nums`. This helps in understanding potential edge cases and complexity implications.
*   **Explain Hash Set Choice:** Clearly articulate why a `HashSet` is the optimal choice for fast lookups compared to linear scanning.
*   **Walk Through Example:** Use a small example (e.g., `nums = [1, 2, 4]`, `k = 3`) to demonstrate your thought process and how the algorithm works step-by-step.
*   **Consider Edge Cases:** Discuss what happens if `nums` is empty, or if `k` is very large, or if all multiples of `k` up to a certain point are present.

## Revision Checklist
- [ ] Understand the problem statement: find smallest missing multiple of k.
- [ ] Choose an efficient data structure for lookups (HashSet).
- [ ] Implement the algorithm: populate set, iterate multiples of k.
- [ ] Handle the starting point correctly (k).
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
*   First Missing Positive
*   Find All Numbers Disappeared in an Array
*   Missing Number

## Tags
`HashSet` `Array` `Math`
