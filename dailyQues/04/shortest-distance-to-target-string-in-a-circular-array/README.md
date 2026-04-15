# Shortest Distance To Target String In A Circular Array

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `String` `Simulation`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        for(int i=0;i<=n/2;i++){
            if(words[insideBound(startIndex - i,n)].equals(target) ||  
                words[insideBound(startIndex + i,n)].equals(target)) 
            return i;
        }
        return -1;
    }
    public int insideBound(int i, int n){
        if(i<n) i = (i % n + n) % n;
        return i%n;
    }
}
```

---

---
## Quick Revision
Given a circular array of strings, find the shortest distance from a starting index to a target string.
We can iterate outwards from the start index, checking both clockwise and counter-clockwise directions simultaneously.

## Intuition
The problem asks for the *shortest* distance. This immediately suggests a breadth-first search (BFS) like approach, or in this case, a linear scan outwards from the `startIndex`. Since the array is circular, we need a way to handle indices that go out of bounds by wrapping around. The key insight is that we can check both directions (left and right) at each step `i` away from `startIndex`. The first time we find the `target` string, the current distance `i` must be the shortest.

## Algorithm
1. Initialize `n` to the length of the `words` array.
2. Iterate with a distance `i` starting from 0 up to `n/2`. We only need to check up to `n/2` because if the target is further than `n/2` away in one direction, it must be closer in the other direction due to the circular nature of the array.
3. For each `i`, calculate the index for the leftward search: `startIndex - i`.
4. For each `i`, calculate the index for the rightward search: `startIndex + i`.
5. Use a helper function `insideBound` to correctly handle circular array indexing for both calculated indices. This function should take an index and the array length, and return the equivalent index within the bounds `[0, n-1]`. A common way to do this is `(index % n + n) % n`.
6. Check if the string at the calculated leftward index in `words` is equal to `target`.
7. Check if the string at the calculated rightward index in `words` is equal to `target`.
8. If either of these checks is true, return the current distance `i`.
9. If the loop completes without finding the `target`, return -1.

## Concept to Remember
*   **Circular Array Handling:** Understanding how to map indices to wrap around a circular array is crucial. The modulo operator (`%`) is key here.
*   **Shortest Path/Distance:** Problems asking for the minimum distance often lend themselves to iterative expansion or BFS-like approaches.
*   **Two-Pointer/Simultaneous Check:** Checking both directions from a central point simultaneously can optimize finding the closest element.

## Common Mistakes
*   **Incorrect Circular Indexing:** Failing to correctly implement the `insideBound` logic, leading to `ArrayIndexOutOfBoundsException` or incorrect comparisons.
*   **Not Checking Both Directions:** Only checking in one direction (e.g., only clockwise) and missing the shorter path in the other direction.
*   **Loop Termination Condition:** Iterating too far (beyond `n/2`) or not far enough, potentially missing the target or performing unnecessary checks.
*   **String Comparison:** Using `==` instead of `.equals()` for comparing `String` objects in Java.

## Complexity Analysis
*   **Time:** O(N) - In the worst case, we might iterate up to `N/2` times, and each iteration involves constant time operations (index calculation, string comparison).
*   **Space:** O(1) - We are only using a few variables to store indices and the array length, which does not depend on the input size.

## Commented Code
```java
class Solution {
    /**
     * Finds the shortest distance from startIndex to target in a circular array.
     * @param words The circular array of strings.
     * @param target The string to find.
     * @param startIndex The starting index.
     * @return The shortest distance, or -1 if not found.
     */
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length; // Get the total number of elements in the array.
        // Iterate outwards from the startIndex. 'i' represents the distance from startIndex.
        // We only need to check up to n/2 because if the target is further than n/2 away in one direction,
        // it must be closer in the other direction due to the circular nature.
        for(int i = 0; i <= n / 2; i++){
            // Calculate the index for the leftward (counter-clockwise) search.
            // Use insideBound to handle wrapping around the circular array.
            int leftIndex = insideBound(startIndex - i, n);
            // Calculate the index for the rightward (clockwise) search.
            // Use insideBound to handle wrapping around the circular array.
            int rightIndex = insideBound(startIndex + i, n);

            // Check if the target is found at the leftward index.
            // Use .equals() for string comparison in Java.
            if(words[leftIndex].equals(target)){
                return i; // If found, return the current distance 'i'.
            }
            // Check if the target is found at the rightward index.
            if(words[rightIndex].equals(target)){
                return i; // If found, return the current distance 'i'.
            }
        }
        // If the loop finishes without finding the target, it means the target is not in the array.
        return -1; // Return -1 to indicate the target was not found.
    }

    /**
     * Helper function to calculate the correct index in a circular array.
     * Handles negative indices and indices exceeding the array bounds by wrapping around.
     * @param i The raw index.
     * @param n The length of the array.
     * @return The adjusted index within the bounds [0, n-1].
     */
    public int insideBound(int i, int n){
        // The expression (i % n + n) % n correctly handles both positive and negative 'i'.
        // For positive 'i', i % n gives the remainder. Adding 'n' and taking modulo 'n' again
        // ensures it stays within [0, n-1] if i % n was 0.
        // For negative 'i', i % n might be negative. Adding 'n' makes it positive,
        // and then % n brings it back into the [0, n-1] range.
        // The initial check 'if(i<n)' is redundant because the modulo operation handles it.
        // A more concise and standard way is just: return (i % n + n) % n;
        // However, the provided code has a slight variation:
        if(i < n) { // This condition is technically not needed for correct modulo arithmetic.
            i = (i % n + n) % n; // Apply the circular logic.
        }
        return i % n; // Ensure the final result is within [0, n-1]. This is also redundant if the above line is correct.
                      // The most robust way is simply: return (i % n + n) % n;
    }
}
```

## Interview Tips
*   **Clarify Circularity:** Explicitly ask the interviewer to confirm the array is circular and how index wrapping should be handled.
*   **Explain the `n/2` Limit:** Justify why iterating only up to `n/2` is sufficient. This shows an understanding of the problem's constraints.
*   **Edge Cases:** Discuss edge cases like an empty array, `startIndex` being out of bounds initially (though the problem implies it's valid), or the `target` not existing.
*   **Helper Function Rationale:** Explain the purpose of the `insideBound` helper function and why it's necessary for circular arrays.

## Revision Checklist
- [ ] Understand the problem statement: shortest distance in a circular array.
- [ ] Implement circular array indexing correctly.
- [ ] Iterate outwards from `startIndex`.
- [ ] Check both clockwise and counter-clockwise directions simultaneously.
- [ ] Return the first distance found.
- [ ] Handle the case where the target is not found.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Shortest Path in Binary Matrix
*   Circular Array Loop
*   Find the Duplicate Number (uses Floyd's Tortoise and Hare, related to cycles)
*   Search in Rotated Sorted Array (involves modified binary search, but circularity is a theme)

## Tags
`Array` `String` `Simulation`
