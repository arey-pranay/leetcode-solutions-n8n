# Find The Maximum Number Of Elements In Subset

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Enumeration`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    HashSet<Integer> singles = new HashSet<>();
    HashSet<Integer> doubles = new HashSet<>();
    int ans = 1;
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int ones = 0;
        for(int i=0;i<n;i++){
            if(nums[i]==1) ones++;
            if(!singles.contains(nums[i])) singles.add(nums[i]);
            else {singles.remove(nums[i]); doubles.add(nums[i]);}
        }
        ones = ones%2 == 0 ? ones-1 : ones;
        ans = Math.max(ans, ones);
        for(int num : doubles){
            if(num==1) continue;
            func(2,(int)Math.pow(num,2));
        }
        return ans;
    }
    public void func(int curr, int toFind){
        if(!singles.contains(toFind) && !doubles.contains(toFind)) return;
        ans = Math.max(ans,curr+1);
        if(doubles.contains(toFind))func(curr+2, (int)Math.pow(toFind,2));
    }
}
    // 2 and find 2
    // 22 and find 4
    // 242 and return 
    // 2442 and find 16}
    
     // 1 2 2 2 2 4 5
        // int ans = 1;
        // except 1 number, all others should come twice
        // odd length subset
        // maxLength = 1 + (2 baar aane wale numbers)
        // exact = maxLength - invalid pairs
        // a b c d c b a
        // a b c b a
        // a b a
        // a
```

---

---
## Quick Revision
This problem asks for the maximum length of a subset where all elements appear an even number of times, except possibly one element. We solve it by identifying elements that appear once and those that appear multiple times, and then greedily extending subsets.

## Intuition
The core idea is that a valid subset can be formed by taking pairs of identical numbers. If we have an odd number of occurrences for a number, one instance must be left out to maintain the "even occurrences" rule for most elements. The number '1' is special because it can be used to "bridge" gaps or extend sequences of powers. The longest subset will likely involve as many pairs as possible, with a potential single element at the end.

## Algorithm
1.  Initialize `singles` (elements seen once) and `doubles` (elements seen more than once) HashSets.
2.  Initialize `ans` to 1 (the minimum possible subset length).
3.  Count the occurrences of '1' in the input array `nums`.
4.  Iterate through `nums`:
    *   If `nums[i]` is '1', increment `ones` count.
    *   If `nums[i]` is already in `singles`, remove it from `singles` and add it to `doubles`. This means we've found a pair.
    *   If `nums[i]` is not in `singles`, add it to `singles`.
5.  Adjust `ones`: If the count of '1's is even, decrement it by 1. This is because we can only have one element appearing an odd number of times in the final subset.
6.  Update `ans` with the adjusted `ones` count.
7.  Iterate through the `doubles` set:
    *   If the number is '1', skip it (handled by the `ones` count).
    *   Call a recursive helper function `func` with `curr = 2` (representing a pair) and `toFind = num * num` (the next power to look for).
8.  The `func(curr, toFind)` helper function:
    *   Base case: If `toFind` is not in `singles` and not in `doubles`, return. This means we cannot extend the current sequence of powers.
    *   Update `ans` with `curr + 1` (the current length of the sequence of powers plus the element `toFind`).
    *   If `toFind` is in `doubles`, recursively call `func` with `curr + 2` and `toFind * toFind`. This continues the sequence of powers (e.g., `x`, `x^2`, `x^4`, `x^8`, ...).
9.  Return `ans`.

## Concept to Remember
*   **Frequency Counting:** Using HashMaps or HashSets to efficiently track element occurrences.
*   **Greedy Approach:** Making locally optimal choices (forming pairs, extending power sequences) to achieve a global optimum.
*   **Handling Special Cases:** Recognizing and treating the number '1' differently due to its multiplicative identity property.
*   **Recursion/Backtracking:** Exploring possibilities by building upon existing valid subsets.

## Common Mistakes
*   **Not handling '1' as a special case:** The number '1' can be used to form sequences like `1, 1, 1, 1` (length 4) or `1, 1, 1` (length 3), which is different from other numbers.
*   **Incorrectly adjusting the count of '1's:** Forgetting that only one element can appear an odd number of times.
*   **Infinite recursion:** Not having a proper base case in the recursive `func` to stop when a required power is not found.
*   **Integer Overflow:** If numbers are very large, `num * num` could overflow. (Though not explicitly an issue with typical LeetCode constraints for this problem, it's a general consideration for power-based problems).
*   **Misunderstanding the subset definition:** Confusing subset with subsequence or subarray.

## Complexity Analysis
*   Time: O(N) - The initial pass through `nums` takes O(N). The `doubles` set can have at most N distinct elements. The recursive `func` explores sequences of powers. For a number `x`, the sequence `x, x^2, x^4, ...` grows exponentially. The maximum depth of recursion for a number `x` is roughly `log(log(MAX_INT))`, which is very small. In the worst case, each distinct number in `doubles` might lead to a short recursive chain. Thus, the total time is dominated by the initial O(N) scan.
*   Space: O(N) - In the worst case, `singles` and `doubles` HashSets can store up to N distinct elements from the input array.

## Commented Code
```java
class Solution {
    // Stores numbers that have appeared exactly once so far.
    HashSet<Integer> singles = new HashSet<>();
    // Stores numbers that have appeared more than once (forming pairs).
    HashSet<Integer> doubles = new HashSet<>();
    // Stores the maximum length found so far, initialized to 1.
    int ans = 1;

    public int maximumLength(int[] nums) {
        int n = nums.length;
        // Count of the number '1'.
        int ones = 0;

        // First pass: populate singles and doubles, and count '1's.
        for(int i = 0; i < n; i++){
            // If the current number is '1', increment its count.
            if(nums[i] == 1) ones++;
            // If the number has been seen before (is in singles)...
            if(!singles.contains(nums[i])) {
                // ...and it's the first time seeing it, add to singles.
                singles.add(nums[i]);
            } else {
                // ...otherwise, it means we've found a pair.
                // Remove it from singles as it's no longer a single.
                singles.remove(nums[i]);
                // Add it to doubles, indicating it can form pairs.
                doubles.add(nums[i]);
            }
        }

        // Adjust the count of '1's. We can only have one element with an odd count.
        // If 'ones' is even, we must exclude one '1' to make it odd, hence ones-1.
        // If 'ones' is odd, it's fine as is.
        ones = ones % 2 == 0 ? ones - 1 : ones;
        // Update the maximum answer with the count of '1's (as a potential subset).
        ans = Math.max(ans, ones);

        // Now, consider subsets formed by powers of numbers in 'doubles'.
        for(int num : doubles){
            // Skip '1' as it's handled by the 'ones' count.
            if(num == 1) continue;
            // Start exploring sequences of powers for this number.
            // Initial call: current length is 2 (for the pair of 'num'),
            // and we are looking for num*num (the next power).
            func(2, (int)Math.pow(num, 2));
        }
        // Return the overall maximum length found.
        return ans;
    }

    // Recursive helper function to find the longest sequence of powers.
    // curr: the current length of the sequence of powers found so far.
    // toFind: the next number in the power sequence we are looking for (e.g., num^2, num^4, etc.).
    public void func(int curr, int toFind){
        // Base case: If the number we need to find is not available in singles or doubles,
        // we cannot extend this power sequence further.
        if(!singles.contains(toFind) && !doubles.contains(toFind)) return;

        // If we found 'toFind', the current sequence length increases by 1.
        // Update the maximum answer.
        ans = Math.max(ans, curr + 1);

        // If 'toFind' is also a number that can form pairs (is in doubles)...
        if(doubles.contains(toFind)){
            // ...we can extend the sequence further.
            // The new current length is curr + 2 (for the pair of 'toFind').
            // We then look for the next power: toFind * toFind.
            func(curr + 2, (int)Math.pow(toFind, 2));
        }
        // Note: If 'toFind' is only in 'singles', we can't extend the *paired* sequence
        // further with 'toFind' itself forming pairs. The logic here implicitly handles this
        // by only recursing if 'toFind' is in 'doubles'. If 'toFind' is in 'singles',
        // we've already updated 'ans' with 'curr + 1', and that branch stops.
    }
}
```

## Interview Tips
1.  **Clarify the "odd one out" rule:** Ensure you understand that *at most one* element can have an odd frequency in the subset.
2.  **Discuss the special case of '1':** Explain why '1' is handled separately and how it can be used to extend sequences.
3.  **Walk through an example:** Use an example like `[1, 2, 2, 3, 3, 3, 4, 4, 4, 4]` to demonstrate how `singles`, `doubles`, and the `func` recursion work.
4.  **Explain the time/space trade-off:** Justify why using HashSets is efficient for this problem.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the special role of the number '1'.
- [ ] Implement frequency counting using HashSets.
- [ ] Handle the case of elements appearing an even number of times.
- [ ] Handle the case of elements appearing an odd number of times (at most one).
- [ ] Develop the recursive logic for extending power sequences.
- [ ] Consider edge cases (empty array, array with only '1's, etc.).
- [ ] Analyze time and space complexity.

## Similar Problems
*   128. Longest Consecutive Sequence
*   164. Maximum Gap
*   217. Contains Duplicate
*   219. Contains Duplicate II
*   220. Contains Duplicate III

## Tags
`Array` `Hash Map` `Set` `Recursion` `Greedy`
