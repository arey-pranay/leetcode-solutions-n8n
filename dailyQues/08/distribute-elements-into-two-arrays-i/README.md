# Distribute Elements Into Two Arrays I

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Simulation`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];   
        List<Integer> temp = new ArrayList<>();
        int i = 0;
        for(int num : nums){
            if(i==0) ans[i++] = num; 
            else if(temp.isEmpty())temp.add(num);
            else{
                if(ans[i-1] > temp.get(temp.size()-1)) ans[i++] = num;
                else temp.add(num);
            }
        }
        for(int num : temp) ans[i++] = num;
        return ans;
    }
}
```

---

---
## Quick Revision
Distribute elements from a single array into two new arrays based on a specific comparison rule.
The solution involves iterating through the input array and appending elements to one of two temporary lists based on the last element of each list.

## Intuition
The problem asks us to partition the input array `nums` into two arrays, let's call them `arr1` and `arr2`. The rule for distribution is based on the last element of `arr1` and `arr2`. Specifically, if the current element from `nums` is greater than the last element of `arr1`, it goes to `arr1`. Otherwise, it goes to `arr2`. The first two elements of `nums` are special and initialize `arr1` and `arr2` respectively. This suggests a greedy approach where we maintain the state of `arr1` and `arr2` as we iterate through `nums`.

## Algorithm
1. Initialize two empty lists, `arr1` and `arr2`, to store the distributed elements.
2. Iterate through the input array `nums`.
3. For the first element of `nums`, add it to `arr1`.
4. For the second element of `nums`, add it to `arr2`.
5. For subsequent elements in `nums`:
    a. Compare the current element with the last element of `arr1`.
    b. If the current element is greater than the last element of `arr1`, append it to `arr1`.
    c. Otherwise, append it to `arr2`.
6. After iterating through all elements of `nums`, concatenate `arr2` to the end of `arr1`.
7. Convert the resulting `arr1` list into an integer array and return it.

*Self-correction during algorithm design*: The provided solution uses a single `ans` array and a `temp` list. This is an optimization to avoid creating two separate lists and then concatenating. The `ans` array acts as `arr1` and the `temp` list acts as `arr2`. The logic is slightly different: the first element always goes to `ans`. If `temp` is empty, the second element goes to `temp`. For subsequent elements, if `ans`'s last element is greater than `temp`'s last element, the current element goes to `ans`; otherwise, it goes to `temp`. Finally, all elements from `temp` are appended to `ans`.

## Concept to Remember
*   **Greedy Approach**: Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Dynamic List Manipulation**: Using `ArrayList` in Java for efficient addition of elements.
*   **Array Traversal and Conditional Logic**: Iterating through an array and applying different actions based on element values and list states.

## Common Mistakes
*   **Incorrect Initialization**: Not handling the first two elements of `nums` as special cases, leading to index out of bounds errors or incorrect initial distribution.
*   **Off-by-One Errors**: Mismanaging indices when accessing the last element of `ans` or `temp`.
*   **Forgetting to Append `temp`**: Failing to append the elements from the second list (`temp`) to the first list (`ans`) at the end.
*   **Modifying Lists While Iterating**: If not careful, modifying a list while iterating over it can lead to unexpected behavior. (Not directly applicable to this specific solution's structure, but a general pitfall).

## Complexity Analysis
- Time: O(N) - reason: We iterate through the input array `nums` once to distribute elements and then iterate through the `temp` list once to append. Both operations are linear with respect to the input size N.
- Space: O(N) - reason: In the worst case, all elements might be stored in either the `ans` array or the `temp` list, requiring space proportional to the input size N.

## Commented Code
```java
class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length; // Get the total number of elements in the input array.
        int[] ans = new int[n]; // Initialize the result array 'ans' with the same size as 'nums'. This will effectively be our first array.
        List<Integer> temp = new ArrayList<>(); // Initialize an ArrayList 'temp' to store elements for our second array.
        int i = 0; // Initialize an index 'i' to keep track of the current position in the 'ans' array.

        for(int num : nums){ // Iterate through each number 'num' in the input array 'nums'.
            if(i==0) ans[i++] = num; // If 'i' is 0, it means 'ans' is empty. Place the first element of 'nums' into 'ans' and increment 'i'.
            else if(temp.isEmpty())temp.add(num); // If 'ans' has at least one element but 'temp' is empty, place the current element into 'temp'. This handles the second element of 'nums'.
            else{ // For all subsequent elements (third element onwards).
                if(ans[i-1] > temp.get(temp.size()-1)) ans[i++] = num; // Compare the current number 'num' with the last element added to 'ans' (ans[i-1]). If 'num' is greater, add it to 'ans' and increment 'i'.
                else temp.add(num); // Otherwise (if 'num' is not greater than the last element of 'ans'), add 'num' to the 'temp' list.
            }
        }
        // After distributing all elements, append all elements from 'temp' to the end of 'ans'.
        for(int num : temp) ans[i++] = num; // Iterate through each number in 'temp' and add it to 'ans' at the current position 'i', then increment 'i'.

        return ans; // Return the final 'ans' array, which now contains elements from both conceptual arrays.
    }
}
```

## Interview Tips
*   **Clarify the Rules**: Ensure you fully understand the distribution logic, especially the base cases (first two elements) and the comparison rule.
*   **Explain Your Data Structures**: Justify why you chose `ArrayList` for `temp` and a plain array for `ans` (or two `ArrayLists` if you didn't optimize).
*   **Walk Through an Example**: Be prepared to trace the execution of your code with a small example input to demonstrate its correctness.
*   **Consider Edge Cases**: Think about what happens with an empty input array, an array with one element, or an array with two elements.

## Revision Checklist
- [ ] Understand the problem statement and distribution rules.
- [ ] Implement the logic for the first two elements.
- [ ] Implement the comparison logic for subsequent elements.
- [ ] Handle the concatenation of the second list's elements.
- [ ] Analyze time and space complexity.
- [ ] Test with various examples, including edge cases.

## Similar Problems
*   Distribute Elements Into Two Arrays II
*   Partition Array Into Two Arrays to Minimize Sum Difference
*   Merge Intervals

## Tags
`Array` `List` `Greedy`
