# Finding 3 Digit Even Numbers

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Recursion` `Sorting` `Enumeration`  
**Time:** O(N * N!)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    LinkedHashSet<Integer> arr = new LinkedHashSet<>();
    int[] digits;
    public int[] findEvenNumbers(int[] ip) {
        boolean[] used = new boolean[ip.length];
        Arrays.sort(ip);
        digits = ip;
        evenfunc(0,used);
        int[] res = new int[arr.size()];
        int i=0;
        for(int val : arr) res[i++]= val;
        return res;
    }

    public void evenfunc(int ans,boolean[] used){
        if(ans<1000 && ans>99) {
            if(ans%2==0) arr.add(ans);
            return;
        }
        else{
            for(int i = 0 ; i<digits.length;i++){
                if(used[i]) continue;
                else {
                    ans = ans*10+digits[i];
                    if(ans==0) continue;
                    used[i]=true;
                    evenfunc(ans,used);
                    used[i]=false;
                    ans /=10;
                }
            }
        }
        return;
    }
}
```

---

---
## Quick Revision
This problem asks to find all unique 3-digit even numbers formed by digits from a given array.
We solve this by generating all permutations of 3 digits and checking if they form a valid 3-digit even number.

## Intuition
The core idea is to construct 3-digit numbers by picking digits from the input array. Since we need unique numbers and the order matters for forming numbers, permutations come to mind. We also need to ensure the number is 3-digit (between 100 and 999 inclusive) and even. A `LinkedHashSet` is useful for automatically handling uniqueness and preserving insertion order, which might be implicitly desired.

## Algorithm
1. Sort the input array `ip`. This helps in generating permutations in a structured way and potentially in pruning some branches early if we were to optimize further (though not strictly necessary for correctness here).
2. Initialize a `LinkedHashSet<Integer>` called `arr` to store the unique 3-digit even numbers found.
3. Initialize a boolean array `used` of the same length as `ip` to keep track of which digits have been used in the current permutation.
4. Define a recursive helper function `evenfunc(int currentNumber, boolean[] used)`:
    a. **Base Case:** If `currentNumber` is a 3-digit number (i.e., `currentNumber >= 100` and `currentNumber <= 999`) and it's even (`currentNumber % 2 == 0`), add it to the `arr` set. Then, return.
    b. **Recursive Step:** Iterate through each digit in the sorted `digits` array (which is `ip`).
        i. If the digit at index `i` has already been `used`, skip it.
        ii. If the digit is `0` and `currentNumber` is `0` (meaning it's the first digit being picked), skip it to avoid leading zeros in numbers greater than 0.
        iii. Otherwise, append the current digit `digits[i]` to `currentNumber` by calculating `newNumber = currentNumber * 10 + digits[i]`.
        iv. Mark `used[i]` as `true`.
        v. Recursively call `evenfunc(newNumber, used)`.
        vi. Backtrack: Mark `used[i]` as `false` and revert `currentNumber` by dividing it by 10 (`currentNumber /= 10`).
5. Call `evenfunc(0, used)` to start the permutation generation process.
6. After the recursion completes, convert the `arr` set into an integer array `res` and return it.

## Concept to Remember
*   **Backtracking:** Essential for exploring all possible combinations/permutations by systematically trying and undoing choices.
*   **Permutations:** Generating all possible orderings of a subset of elements.
*   **Set for Uniqueness:** Using a `HashSet` or `LinkedHashSet` to automatically handle duplicate numbers.
*   **Leading Zeros:** Handling the constraint that a 3-digit number cannot start with '0' (unless it's the number 0 itself, which is not a 3-digit number).

## Common Mistakes
*   **Not handling leading zeros:** Allowing numbers like "012" to be formed and considered valid 3-digit numbers.
*   **Not ensuring uniqueness:** Using a simple array or list without a mechanism to remove duplicates, leading to incorrect results.
*   **Incorrect base case for recursion:** Not properly checking the 3-digit range or the even condition.
*   **Improper backtracking:** Failing to reset the `used` array or revert the `currentNumber` correctly, leading to incorrect permutations.
*   **Not sorting the input:** While not strictly required for correctness with this backtracking approach, sorting can sometimes help in pruning or if a specific order of output is implicitly expected.

## Complexity Analysis
- Time: O(N * N!), where N is the number of digits in the input array. This is because we are essentially generating permutations of length 3 from N digits. For each permutation, we do constant work. The number of permutations of length 3 from N items is P(N, 3) = N! / (N-3)!. In the worst case, we explore all possible permutations of length up to 3.
- Space: O(N) for the recursion stack depth and the `used` array. The `LinkedHashSet` can store up to O(N^3) unique numbers in the worst case, but since we are only forming 3-digit numbers, the maximum number of unique 3-digit numbers is 900. So, the space for the set is effectively O(1) in terms of the number of possible 3-digit numbers, but O(N) if we consider the input size for the `used` array and recursion depth.

## Commented Code
```java
class Solution {
    // LinkedHashSet to store unique 3-digit even numbers found.
    // LinkedHashSet preserves insertion order and ensures uniqueness.
    LinkedHashSet<Integer> arr = new LinkedHashSet<>();
    // Array to store the input digits.
    int[] digits;

    // Main function to find all unique 3-digit even numbers.
    public int[] findEvenNumbers(int[] ip) {
        // Boolean array to keep track of used digits during permutation generation.
        boolean[] used = new boolean[ip.length];
        // Sort the input array. This helps in generating permutations in a structured way.
        Arrays.sort(ip);
        // Store the sorted input array in the class member 'digits'.
        digits = ip;
        // Start the recursive backtracking function to generate numbers.
        // '0' is the initial number being built, 'used' array is passed.
        evenfunc(0, used);
        // Create a result array with the size of unique numbers found.
        int[] res = new int[arr.size()];
        // Index for the result array.
        int i = 0;
        // Iterate through the LinkedHashSet and populate the result array.
        for (int val : arr) {
            res[i++] = val;
        }
        // Return the array of unique 3-digit even numbers.
        return res;
    }

    // Recursive helper function to generate numbers using backtracking.
    // 'ans' is the number being built so far.
    // 'used' is the boolean array tracking used digits.
    public void evenfunc(int ans, boolean[] used) {
        // Base Case: If the number being built is a 3-digit number (100-999).
        if (ans >= 100 && ans <= 999) {
            // Check if the 3-digit number is even.
            if (ans % 2 == 0) {
                // If it's a 3-digit even number, add it to the set.
                arr.add(ans);
            }
            // Once a 3-digit number is formed, we stop exploring further for this branch.
            return;
        }
        // If the number being built exceeds 999, we prune this branch.
        if (ans > 999) {
            return;
        }

        // Recursive Step: Iterate through all available digits.
        for (int i = 0; i < digits.length; i++) {
            // If the digit at index 'i' is already used in the current permutation, skip it.
            if (used[i]) {
                continue;
            }
            // Special condition: If the current number being built is 0 (i.e., we are picking the first digit)
            // and the digit we are about to pick is also 0, skip it to avoid leading zeros in numbers > 0.
            if (ans == 0 && digits[i] == 0) {
                continue;
            }

            // Append the current digit to the number being built.
            // Example: if ans=12 and digits[i]=3, new ans becomes 123.
            ans = ans * 10 + digits[i];
            // Mark the digit at index 'i' as used.
            used[i] = true;
            // Recursively call evenfunc to continue building the number.
            evenfunc(ans, used);
            // Backtrack: Unmark the digit at index 'i' as used, so it can be used in other permutations.
            used[i] = false;
            // Backtrack: Remove the last appended digit from 'ans'.
            // Example: if ans was 123, it becomes 12.
            ans /= 10;
        }
        // Return from the function after exploring all possibilities from this state.
        return;
    }
}
```

## Interview Tips
1.  **Explain Backtracking Clearly:** When asked about the approach, clearly articulate the concept of backtracking, including the "choose, explore, unchoose" pattern.
2.  **Handle Edge Cases:** Be prepared to discuss how you handle leading zeros and ensure the number is strictly 3 digits (100-999).
3.  **Discuss Uniqueness:** Explain why a `Set` (like `LinkedHashSet` or `HashSet`) is crucial for storing results and how it guarantees uniqueness.
4.  **Complexity Justification:** Be ready to explain the time and space complexity, especially the factorial component in time complexity due to permutations.

## Revision Checklist
- [ ] Understand the problem statement: find unique 3-digit even numbers.
- [ ] Identify the need for permutations.
- [ ] Implement backtracking correctly for permutation generation.
- [ ] Handle the 3-digit constraint (100-999).
- [ ] Handle the even number constraint (divisible by 2).
- [ ] Ensure uniqueness of results using a Set.
- [ ] Handle leading zeros correctly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Permutations II (LeetCode 47)
*   Combinations (LeetCode 216)
*   Subsets II (LeetCode 90)
*   Letter Combinations of a Phone Number (LeetCode 17)

## Tags
`Array` `Backtracking` `Recursion` `HashSet`

## My Notes
Interesting Question. Solution can be improved though
