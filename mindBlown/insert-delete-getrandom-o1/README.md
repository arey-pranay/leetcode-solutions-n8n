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
Implement a data structure that supports inserting, deleting, and getting a random element in O(1) average time.
This is achieved by using a HashMap to store element values and their indices in an ArrayList, and a clever swap-and-remove strategy for deletion.

## Intuition
The core challenge is achieving O(1) for all three operations.
`insert` and `getRandom` are straightforward with a `HashMap` and `ArrayList` respectively.
`remove` is the tricky part. Removing an element from the middle of an `ArrayList` is O(n) because subsequent elements need to be shifted. To make it O(1), we can swap the element to be removed with the *last* element in the `ArrayList`, then remove the last element (which is O(1)). We also need to update the `HashMap` to reflect the new index of the swapped element.

## Algorithm
1.  **Initialization**:
    *   Create a `HashMap<Integer, Integer>` named `hm` to store the value of an element and its index in the `ArrayList`.
    *   Create an `ArrayList<Integer>` named `arr` to store the elements.
2.  **`insert(val)`**:
    *   Check if `val` already exists in `hm`. If it does, return `false`.
    *   If `val` does not exist, add `val` to the end of `arr`.
    *   Store the index of `val` (which is `arr.size() - 1` *before* adding `val`, or `arr.size()` *after* adding `val` if we use `arr.size()` as the index *before* adding) in `hm` with `val` as the key. A more common approach is to store the index *after* adding, so `hm.put(val, arr.size() - 1)`.
    *   Return `true`.
3.  **`remove(val)`**:
    *   Check if `val` exists in `hm`. If it doesn't, return `false`.
    *   Get the index of `val` from `hm`. Let this be `indexToRemove`.
    *   Get the last element from `arr`. Let this be `lastElement`.
    *   If `val` is not the last element in the array (i.e., `indexToRemove != arr.size() - 1`):
        *   Replace the element at `indexToRemove` in `arr` with `lastElement`.
        *   Update the index of `lastElement` in `hm` to `indexToRemove`.
    *   Remove `val` from `hm`.
    *   Remove the last element from `arr` (this is always O(1)).
    *   Return `true`.
4.  **`getRandom()`**:
    *   Generate a random integer `randomIndex` between 0 (inclusive) and `arr.size()` (exclusive).
    *   Return the element at `randomIndex` from `arr`.

## Concept to Remember
*   **Hash Map**: For O(1) average time lookups, insertions, and deletions.
*   **Array List**: For O(1) average time additions to the end and O(1) random access by index.
*   **Amortized Analysis**: Understanding that while some operations might be O(n) in worst-case scenarios (like `ArrayList` resizing), the average time complexity over a sequence of operations remains O(1).
*   **In-place Swapping**: A technique to efficiently rearrange elements within a data structure to facilitate O(1) removals.

## Common Mistakes
*   **Incorrectly handling the `remove` operation**: Forgetting to update the `HashMap` with the new index of the swapped element, or not handling the case where the element to be removed is the last element.
*   **Assuming `ArrayList.remove(index)` is O(1)**: This is a common pitfall; it's O(n) for arbitrary indices.
*   **Off-by-one errors in indexing**: Especially when dealing with `arr.size()` and `arr.size() - 1`.
*   **Not handling duplicate values**: The problem statement implies unique values, but if duplicates were allowed, the `HashMap` approach would need modification.

## Complexity Analysis
*   **Time**:
    *   `insert(val)`: O(1) on average. The `HashMap.put` and `ArrayList.add` operations are O(1) on average.
    *   `remove(val)`: O(1) on average. `HashMap.get`, `HashMap.remove`, `ArrayList.set`, and `ArrayList.remove(arr.size()-1)` are all O(1) on average.
    *   `getRandom()`: O(1). `Math.random()` and `ArrayList.get` are O(1).
*   **Space**: O(n), where n is the number of elements in the set. This is due to storing all elements in both the `HashMap` and the `ArrayList`.

## Commented Code
```java
import java.util.HashMap; // Import the HashMap class for key-value storage.
import java.util.ArrayList; // Import the ArrayList class for dynamic array storage.
import java.util.Random; // Import the Random class for generating random numbers.

class RandomizedSet {
    // HashMap to store the value of the element and its index in the ArrayList.
    // Key: element value, Value: index in the arr ArrayList.
    HashMap<Integer, Integer> hm;
    // ArrayList to store the elements. This allows O(1) access by index and O(1) addition/removal from the end.
    ArrayList<Integer> arr;
    // Random number generator for getRandom operation.
    Random rand;

    // Constructor to initialize the data structures.
    public RandomizedSet() {
        hm = new HashMap<>(); // Initialize the HashMap.
        arr = new ArrayList<>(); // Initialize the ArrayList.
        rand = new Random(); // Initialize the Random object.
    }

    // Inserts a value into the set. Returns true if the set did not already contain the specified element.
    public boolean insert(int val) {
        // Check if the value already exists in the HashMap. If it does, it's already in the set.
        if (hm.containsKey(val)) {
            return false; // Return false as the element was not inserted.
        }
        // If the value does not exist, add it to the HashMap.
        // The value is the element itself, and the index is the current size of the ArrayList (which will be its index after adding).
        hm.put(val, arr.size());
        // Add the value to the end of the ArrayList. This is an O(1) operation on average.
        arr.add(val);
        // Return true as the element was successfully inserted.
        return true;
    }

    // Removes a value from the set. Returns true if the set contained the specified element.
    public boolean remove(int val) {
        // Check if the value exists in the HashMap. If it doesn't, it's not in the set.
        if (!hm.containsKey(val)) {
            return false; // Return false as the element was not found.
        }

        // Get the index of the element to be removed from the HashMap.
        int indexToRemove = hm.get(val);
        // Get the last element in the ArrayList.
        int lastElement = arr.get(arr.size() - 1);

        // If the element to remove is NOT the last element in the ArrayList:
        if (indexToRemove != arr.size() - 1) {
            // Replace the element at 'indexToRemove' with the 'lastElement'.
            // This is the key step to achieve O(1) removal from the ArrayList.
            arr.set(indexToRemove, lastElement);
            // Update the HashMap to reflect the new index of the 'lastElement'.
            // It has now moved to the position of the element that was removed.
            hm.put(lastElement, indexToRemove);
        }

        // Remove the element 'val' from the HashMap.
        hm.remove(val);
        // Remove the last element from the ArrayList. This is an O(1) operation.
        // If 'val' was the last element, this effectively removes it.
        // If 'val' was not the last element, this removes the original last element (which we already moved).
        arr.remove(arr.size() - 1);

        // Return true as the element was successfully removed.
        return true;
    }

    // Get a random element from the set.
    public int getRandom() {
        // Generate a random index within the bounds of the ArrayList.
        // rand.nextInt(arr.size()) generates a random integer from 0 (inclusive) to arr.size() (exclusive).
        int randomIndex = rand.nextInt(arr.size());
        // Return the element at the randomly generated index from the ArrayList. This is an O(1) operation.
        return arr.get(randomIndex);
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
*   **Explain the O(n) problem first**: Start by explaining why a naive `ArrayList` or `HashSet` alone won't work for all operations in O(1). This shows you understand the constraints.
*   **Focus on the `remove` trick**: The core of the interview will be your explanation of how to achieve O(1) removal. Clearly articulate the swap-with-last-element strategy and why it works.
*   **Walk through an example**: Use a small example (e.g., inserting 1, 2, 3, then removing 2) to demonstrate the state of both the `HashMap` and `ArrayList` at each step.
*   **Discuss edge cases**: Mention what happens if you try to remove an element that doesn't exist, or if the element to be removed is the last element in the `ArrayList`.

## Revision Checklist
- [ ] Understand the O(1) requirement for insert, delete, and getRandom.
- [ ] Implement `insert` using HashMap and ArrayList.
- [ ] Implement `getRandom` using ArrayList.
- [ ] Master the O(1) `remove` strategy: swap with last, update map, remove last.
- [ ] Handle edge cases in `remove` (element not present, element is the last one).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Insert Delete GetRandom O(1) - Duplicates Allowed
*   Design Circular Deque
*   Shuffle an Array

## Tags
`Array` `Hash Map` `Randomized` `Design`
