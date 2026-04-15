# Shortest Distance To Target String In A Circular Array

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `String`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        for(int i=0;i<=n/2;i++){
            if(words[bound(startIndex - i,n)].equals(target) ||  
                words[bound(startIndex + i,n)].equals(target)) 
            return i;
        }
        return -1;
    }
    public int bound(int i, int n){
        return (i % n + n) % n;
    }
}
```

---

---
## Quick Revision
Given a circular array of strings, find the shortest distance from a starting index to a target string.
We can iterate outwards from the start index in both directions, checking for the target string.

## Intuition
The problem describes a circular array, which means we can wrap around from the end to the beginning and vice-versa. We are looking for the *shortest* distance. This suggests an expanding search. If we start at `startIndex` and look at `startIndex + 1`, `startIndex - 1`, then `startIndex + 2`, `startIndex - 2`, and so on, the first time we find the `target` string, the number of steps we've taken will be the shortest distance. The circular nature means we need a way to handle indices that go out of bounds, wrapping them back into the valid range.

## Algorithm
1. Initialize `n` to the length of the `words` array.
2. Iterate with a distance `i` starting from 0 up to `n/2`. This is because in a circular array of length `n`, the maximum shortest distance to any element is `n/2`.
3. For each `i`, calculate two potential indices:
    a. `startIndex - i`: The index `i` steps backward from `startIndex`.
    b. `startIndex + i`: The index `i` steps forward from `startIndex`.
4. Use a helper function `bound(index, n)` to handle the circularity. This function ensures that any index `j` is mapped to a valid index within `[0, n-1]`. A common way to do this is `(j % n + n) % n`.
5. Check if the string at the calculated backward index (`words[bound(startIndex - i, n)]`) is equal to the `target`.
6. If not, check if the string at the calculated forward index (`words[bound(startIndex + i, n)]`) is equal to the `target`.
7. If either of these checks is true, return the current distance `i`. This is the shortest distance found so far.
8. If the loop completes without finding the `target` string, return -1.

## Concept to Remember
*   **Circular Array Traversal:** Understanding how to access elements in an array that wraps around.
*   **Modulo Arithmetic for Circularity:** Using the modulo operator (`%`) to correctly map indices in a circular fashion.
*   **Breadth-First Search (BFS) Implicitly:** The outward expansion from `startIndex` is analogous to a BFS, where we explore layer by layer (distance by distance).

## Common Mistakes
*   **Incorrect Modulo Handling:** Not correctly implementing the `bound` function, leading to `ArrayIndexOutOfBoundsException` or incorrect wrapping (e.g., `-1 % 5` might be `-1` in some languages, not `4`). The `(i % n + n) % n` pattern is crucial for handling negative results of the modulo operator correctly.
*   **Not Considering Both Directions:** Only searching in one direction (e.g., forward) and missing the shorter path in the other direction.
*   **Loop Termination Condition:** Iterating too far. In a circular array of size `n`, the maximum shortest distance is `n/2`. Iterating beyond `n/2` is redundant.
*   **String Comparison:** Using `==` instead of `.equals()` for comparing strings in Java.

## Complexity Analysis
*   Time: O(N) - In the worst case, we might have to check up to `N/2` elements in each direction, resulting in a total of `N` checks.
*   Space: O(1) - We are only using a few variables to store indices and the loop counter, requiring constant extra space.

## Commented Code
```java
class Solution {
    // Main function to find the shortest distance to the target string.
    public int closestTarget(String[] words, String target, int startIndex) {
        // Get the total number of words in the array.
        int n = words.length;
        // Iterate through possible distances from the startIndex.
        // We only need to check up to n/2 because in a circular array,
        // the maximum shortest distance to any element is n/2.
        for(int i = 0; i <= n / 2; i++) {
            // Calculate the index i steps backward from startIndex, handling circularity.
            int backwardIndex = bound(startIndex - i, n);
            // Calculate the index i steps forward from startIndex, handling circularity.
            int forwardIndex = bound(startIndex + i, n);

            // Check if the word at the backward index matches the target.
            // If it matches, we've found the shortest distance, which is i.
            if(words[backwardIndex].equals(target)) {
                return i;
            }
            // Check if the word at the forward index matches the target.
            // If it matches, we've found the shortest distance, which is i.
            if(words[forwardIndex].equals(target)) {
                return i;
            }
        }
        // If the loop finishes without finding the target, it means the target is not in the array.
        return -1;
    }

    // Helper function to calculate the correct index in a circular array.
    // It handles both positive and negative indices by ensuring the result is always within [0, n-1].
    public int bound(int i, int n) {
        // The expression (i % n + n) % n correctly handles negative results of the modulo operator.
        // For example, if i = -1 and n = 5:
        // -1 % 5 = -1 (in Java)
        // (-1 + 5) % 5 = 4 % 5 = 4. This is the correct circular index.
        return (i % n + n) % n;
    }
}
```

## Interview Tips
*   **Clarify Circularity:** Explicitly ask the interviewer to confirm the array is circular and how to handle index wrapping.
*   **Explain the `n/2` Limit:** Justify why iterating up to `n/2` is sufficient for finding the shortest distance in a circular array.
*   **Walk Through `bound` Function:** Be prepared to explain the logic behind the `(i % n + n) % n` pattern for handling negative modulo results.
*   **Consider Edge Cases:** Discuss what happens if `startIndex` is already the target, if the target is not present, or if the array has only one element.

## Revision Checklist
- [ ] Understand the problem statement: shortest distance in a circular array.
- [ ] Implement circular index calculation correctly using modulo arithmetic.
- [ ] Iterate outwards from `startIndex` in both directions.
- [ ] Recognize that `n/2` is the maximum shortest distance.
- [ ] Use `.equals()` for string comparison.
- [ ] Handle the case where the target is not found.

## Similar Problems
*   1847. Closest Room
*   1700. Number of Students Unable to Eat Lunch (conceptually similar in terms of queue/stack processing, but different problem)
*   102. Binary Tree Level Order Traversal (BFS pattern)

## Tags
`Array` `String` `Math`
