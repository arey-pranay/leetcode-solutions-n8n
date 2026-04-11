# Random Pick Index

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Math` `Reservoir Sampling` `Randomized`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    HashMap<Integer,List<Integer>> hm;
    public Solution(int[] nums) {
        this.hm = new HashMap<>();
        for(int i = 0;i<nums.length;i++){
            List<Integer> list = hm.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            this.hm.put(nums[i],list);
        }
    }
    
    public int pick(int target) {
        List<Integer> list = hm.get(target);
        double randi =  Math.random(); // this give a number from 0 to 1
        
        // 0.2*3 = 0
        // 0.5*3 = 1
        // 0.9*3 = 2
       
        int index = (int)(Math.floor(randi* list.size())); // convert this random number to be from 0 to size-1, so that we can use that number as a random valid index
        return list.get(index);
      
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
```

---

---
## Quick Revision
Given an array of integers with possible duplicates, return a random index of a given target value.
The solution involves pre-processing the array to store indices of each number and then picking a random index from the stored list.

## Intuition
The core idea is that if we want to pick a random index for a `target` value, we need to know all the possible indices where `target` appears. Once we have this list of indices, picking a random one from it is straightforward. A hash map is a natural choice to store this mapping: the key would be the number, and the value would be a list of all indices where that number appears in the input array.

## Algorithm
1.  **Constructor (`Solution(int[] nums)`):**
    *   Initialize a `HashMap` called `hm` to store the mapping from numbers to their indices.
    *   Iterate through the input array `nums` from index `0` to `nums.length - 1`.
    *   For each element `nums[i]` at index `i`:
        *   Get the list of indices associated with `nums[i]` from the `hm`. If `nums[i]` is not yet in the map, create a new `ArrayList`.
        *   Add the current index `i` to this list.
        *   Put (or update) the `nums[i]` and its corresponding list of indices back into the `hm`.
2.  **Pick Method (`pick(int target)`):**
    *   Retrieve the list of indices associated with the `target` value from the `hm`.
    *   Generate a random double-precision floating-point number between 0.0 (inclusive) and 1.0 (exclusive) using `Math.random()`.
    *   Multiply this random number by the size of the retrieved list of indices. This scales the random number to be between 0.0 (inclusive) and `list.size()` (exclusive).
    *   Use `Math.floor()` to round this scaled random number down to the nearest whole number. This gives us a random integer index within the valid range of the list (0 to `list.size() - 1`).
    *   Return the element at this randomly generated index from the list of indices.

## Concept to Remember
*   **Hash Maps:** Efficiently storing and retrieving key-value pairs, ideal for mapping elements to their associated data (like indices).
*   **Random Number Generation:** Understanding how to generate random numbers within a specific range.
*   **Array Traversal and Indexing:** Basic manipulation of arrays and accessing elements by their index.
*   **Data Structures for Mapping:** Choosing appropriate structures (like `HashMap` and `ArrayList`) for the problem's requirements.

## Common Mistakes
*   **Not handling duplicates correctly:** Forgetting that a number can appear multiple times and only storing the first or last index.
*   **Incorrect random index generation:** Generating a random number that goes out of bounds for the list of indices (e.g., including `list.size()` as a possible index).
*   **Inefficient pre-processing:** Not using a `HashMap` and instead iterating through the array every time `pick` is called, leading to O(N) time complexity for `pick`.
*   **Off-by-one errors:** When calculating the random index, either by misinterpreting the range of `Math.random()` or the `list.size()`.

## Complexity Analysis
- Time: O(N) - reason: The constructor iterates through the input array `nums` once, where N is the length of `nums`. Each `pick` operation takes O(1) time on average because retrieving from a HashMap and generating a random number are constant time operations.
- Space: O(N) - reason: In the worst case, all elements in `nums` are distinct, and the HashMap will store N entries, each with a list containing one index. Thus, the space complexity is proportional to the number of elements in the input array.

## Commented Code
```java
import java.util.HashMap; // Import the HashMap class for key-value storage.
import java.util.List; // Import the List interface for dynamic arrays.
import java.util.ArrayList; // Import the ArrayList class for implementing dynamic arrays.
import java.lang.Math; // Import the Math class for mathematical operations like random and floor.

class Solution {
    // Declare a HashMap to store numbers as keys and a list of their indices as values.
    HashMap<Integer, List<Integer>> hm;

    // Constructor to initialize the Solution object and pre-process the input array.
    public Solution(int[] nums) {
        // Initialize the HashMap.
        this.hm = new HashMap<>();
        // Iterate through the input array 'nums' using an index 'i'.
        for (int i = 0; i < nums.length; i++) {
            // Get the list of indices for the current number 'nums[i]'.
            // If the number is not already in the map, 'getOrDefault' returns a new ArrayList.
            List<Integer> list = hm.getOrDefault(nums[i], new ArrayList<>());
            // Add the current index 'i' to the list of indices for 'nums[i]'.
            list.add(i);
            // Put the number 'nums[i]' and its updated list of indices back into the HashMap.
            this.hm.put(nums[i], list);
        }
    }

    // Method to pick a random index for a given target number.
    public int pick(int target) {
        // Retrieve the list of all indices where 'target' appears from the HashMap.
        List<Integer> list = hm.get(target);

        // Generate a random double-precision floating-point number between 0.0 (inclusive) and 1.0 (exclusive).
        double randi = Math.random();

        // Calculate a random index within the bounds of the 'list' size.
        // Math.floor(randi * list.size()) ensures the result is an integer from 0 to list.size() - 1.
        // For example:
        // if list.size() is 3:
        // randi = 0.2 => 0.2 * 3 = 0.6 => floor(0.6) = 0
        // randi = 0.5 => 0.5 * 3 = 1.5 => floor(1.5) = 1
        // randi = 0.9 => 0.9 * 3 = 2.7 => floor(2.7) = 2
        int index = (int) (Math.floor(randi * list.size()));

        // Return the index from the 'list' at the calculated random position.
        return list.get(index);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
```

## Interview Tips
*   **Clarify constraints:** Ask about the size of the array, the range of numbers, and the frequency of `pick` calls. This helps confirm if the O(N) space and O(1) pick time is acceptable.
*   **Explain the pre-processing step:** Clearly articulate why a `HashMap` is used and how it efficiently stores the necessary information.
*   **Detail the random selection logic:** Be precise about how `Math.random()`, multiplication, and `Math.floor()` are used together to generate a uniformly random index.
*   **Discuss alternative approaches (and why they are worse):** Briefly mention that iterating through the array for each `pick` call would be O(N) time per pick, which is less efficient if `pick` is called many times.

## Revision Checklist
- [ ] Understand the problem statement: pick a random index of a target value.
- [ ] Recognize the need to store all indices for each number.
- [ ] Choose `HashMap<Integer, List<Integer>>` for efficient storage.
- [ ] Implement the constructor to populate the `HashMap`.
- [ ] Implement the `pick` method using `Math.random()` and `Math.floor()`.
- [ ] Verify the random index generation logic for uniform distribution.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty array, target not found - though problem constraints usually prevent this).

## Similar Problems
*   Reservoir Sampling (e.g., LeetCode 398. Random Pick Index - this problem is a specific instance of it, and LeetCode 537. Random Pick Index is the same problem)
*   Randomized algorithms
*   Problems involving frequency counts or index tracking

## Tags
`Array` `Hash Map` `Randomized`
