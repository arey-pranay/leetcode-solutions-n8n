# Insert Delete Getrandom O1

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Design` `Randomized`  
**Time:** See complexity section  
**Space:** O(n)

---

## Solution (java)

```java
class RandomizedSet {
    HashMap<Integer,Integer> hm;
    ArrayList<Integer> arr;
    public RandomizedSet() {
        hm = new HashMap<>();
        arr = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if(hm.containsKey(val)) return false;
        hm.put(val,arr.size());
        arr.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if(!hm.containsKey(val)) return false;

        // removal we will do only from end of array because that is o(1), so we will keep the last element at the toBeDeleted index, and then remove the last element. then update the hashmap accordingly.
        int index = hm.get(val);
        int lastVal = arr.get(arr.size() - 1);
        arr.set(index,lastVal);
        arr.remove(arr.size()-1);

        hm.put(lastVal,index);
        hm.remove(val);

        return true;
    }
    
    public int getRandom() {
        int random = (int)(Math.random() * arr.size());
        return arr.get(random);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

---

---
## Quick Revision
Implement a data structure that supports inserting, deleting, and getting a random element in O(1) time.
This is achieved by using a hash map to store element values and their indices in an array, and an array to store the elements.

## Intuition
The core challenge is to achieve O(1) for all three operations: `insert`, `remove`, and `getRandom`.
`insert` and `getRandom` are straightforward with a hash map and an array, respectively. The tricky part is `remove`. A standard array `remove` operation (e.g., `ArrayList.remove(index)`) takes O(n) time because it shifts subsequent elements. To make `remove` O(1), we can leverage the fact that removing the *last* element of an `ArrayList` is O(1). The "aha moment" is realizing we can swap the element to be removed with the last element in the array, then remove the last element. We then need to update the hash map to reflect the new position of the swapped element.

## Algorithm
1.  **Initialization**:
    *   Create a `HashMap` (`hm`) to store the mapping from element value to its index in the `ArrayList`.
    *   Create an `ArrayList` (`arr`) to store the elements.
2.  **`insert(val)`**:
    *   Check if `val` already exists in `hm`. If it does, return `false`.
    *   If `val` does not exist, add `val` to the end of `arr`.
    *   Store the index of `val` (which is `arr.size() - 1` *before* adding `val`, or `arr.size()` *after* adding `val`) in `hm` with `val` as the key.
    *   Return `true`.
3.  **`remove(val)`**:
    *   Check if `val` exists in `hm`. If it doesn't, return `false`.
    *   Get the index of `val` from `hm`. Let this be `indexToRemove`.
    *   Get the last element from `arr`. Let this be `lastElement`.
    *   Replace the element at `indexToRemove` in `arr` with `lastElement`.
    *   Remove the last element from `arr` (this is an O(1) operation).
    *   Update the index of `lastElement` in `hm` to `indexToRemove`.
    *   Remove `val` from `hm`.
    *   Return `true`.
4.  **`getRandom()`**:
    *   Generate a random integer `randomIndex` between 0 (inclusive) and `arr.size()` (exclusive).
    *   Return the element at `randomIndex` from `arr`.

## Concept to Remember
*   **Hash Map for O(1) Lookups**: Used to quickly check for the existence of an element and retrieve its index.
*   **Array/ArrayList for O(1) Random Access and End Removal**: Used to store elements and allow O(1) retrieval of a random element and O(1) removal of the last element.
*   **Swapping for O(1) Deletion**: The technique of swapping the element to be deleted with the last element to enable O(1) removal from the array.

## Common Mistakes
*   **Inefficient `remove`**: Attempting to remove an element from the middle of an `ArrayList` directly, leading to O(n) time complexity.
*   **Forgetting to update the hash map**: After swapping elements in the array, failing to update the index of the swapped element in the hash map.
*   **Handling edge cases**: Not correctly handling cases where the element to be removed is the last element in the array.
*   **Incorrect random number generation**: Generating random numbers with incorrect bounds, leading to `IndexOutOfBoundsException`.

## Complexity Analysis
*   **Time**:
    *   `insert(val)`: O(1) - Hash map `put` and `ArrayList` `add` are O(1) on average.
    *   `remove(val)`: O(1) - Hash map `get`, `containsKey`, `remove`, and `ArrayList` `set`, `remove(last)` are all O(1) on average.
    *   `getRandom()`: O(1) - `Math.random()` and `ArrayList` `get` are O(1).
*   **Space**: O(n) - Where n is the number of elements stored in the `RandomizedSet`. This is due to the storage required by both the `HashMap` and the `ArrayList`.

## Commented Code
```java
import java.util.HashMap; // Import the HashMap class for key-value storage
import java.util.ArrayList; // Import the ArrayList class for dynamic array storage
import java.util.Random; // Import the Random class for generating random numbers

class RandomizedSet {
    // HashMap to store the value of the element and its index in the ArrayList.
    // This allows for O(1) average time complexity for checking existence and getting index.
    HashMap<Integer, Integer> valToIndex;
    // ArrayList to store the elements.
    // This allows for O(1) average time complexity for adding elements and O(1) for removing the last element.
    // It also allows for O(1) access to a random element by index.
    ArrayList<Integer> nums;
    // Random number generator to pick a random index.
    Random rand;

    // Constructor to initialize the data structures.
    public RandomizedSet() {
        valToIndex = new HashMap<>(); // Initialize the HashMap
        nums = new ArrayList<>(); // Initialize the ArrayList
        rand = new Random(); // Initialize the Random object
    }

    // Inserts a value to the set. Returns true if the set did not already contain the specified element.
    public boolean insert(int val) {
        // Check if the value already exists in the HashMap.
        if (valToIndex.containsKey(val)) {
            return false; // If it exists, we cannot insert it again, return false.
        }
        // If the value does not exist, add it to the HashMap.
        // The key is the value itself, and the value is the current size of the ArrayList,
        // which will be the index where 'val' is about to be added.
        valToIndex.put(val, nums.size());
        // Add the value to the end of the ArrayList. This is an O(1) operation.
        nums.add(val);
        // Return true to indicate successful insertion.
        return true;
    }

    // Removes a value from the set. Returns true if the set contained the specified element.
    public boolean remove(int val) {
        // Check if the value exists in the HashMap.
        if (!valToIndex.containsKey(val)) {
            return false; // If it doesn't exist, we cannot remove it, return false.
        }

        // Get the index of the element to be removed from the HashMap.
        int indexToRemove = valToIndex.get(val);
        // Get the last element in the ArrayList.
        int lastElement = nums.get(nums.size() - 1);

        // To achieve O(1) removal, we swap the element to be removed with the last element.
        // Then, we remove the last element (which is now the element we wanted to remove).
        // First, update the ArrayList at the index of the element to be removed with the last element.
        nums.set(indexToRemove, lastElement);
        // Then, remove the last element from the ArrayList. This is an O(1) operation.
        nums.remove(nums.size() - 1);

        // Update the index of the 'lastElement' in the HashMap to its new position (indexToRemove).
        valToIndex.put(lastElement, indexToRemove);
        // Remove the original value 'val' from the HashMap.
        valToIndex.remove(val);

        // Return true to indicate successful removal.
        return true;
    }

    // Get a random element from the set.
    public int getRandom() {
        // Generate a random index within the bounds of the ArrayList.
        // rand.nextInt(nums.size()) generates a random integer between 0 (inclusive) and nums.size() (exclusive).
        int randomIndex = rand.nextInt(nums.size());
        // Return the element at the randomly generated index from the ArrayList. This is an O(1) operation.
        return nums.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

## Interview Tips
*   **Explain the O(1) `remove` strategy clearly**: Emphasize the swap-and-pop technique and why it's crucial for achieving O(1) complexity.
*   **Discuss the trade-offs**: Mention that while operations are O(1), the space complexity is O(n) due to storing elements in two data structures.
*   **Walk through an example**: Use a small example (e.g., inserting 1, 2, 3, then removing 2) to illustrate how the `HashMap` and `ArrayList` change.
*   **Be prepared for follow-up questions**: The interviewer might ask about alternative approaches or how to handle duplicates if the problem allowed them.

## Revision Checklist
- [ ] Understand the O(1) requirement for all operations.
- [ ] Identify the bottleneck in standard array/list removal.
- [ ] Implement `insert` using `HashMap` and `ArrayList`.
- [ ] Implement `remove` using the swap-and-pop strategy.
- [ ] Implement `getRandom` using `ArrayList` and a random number generator.
- [ ] Ensure `HashMap` is updated correctly during `remove`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Insert Delete GetRandom O(1) - Duplicates allowed
*   Shuffle an Array

## Tags
`Array` `Hash Map` `Design` `Randomized`
