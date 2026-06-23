# Insert Delete Getrandom O1 Duplicates Allowed

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Design` `Randomized`  
**Time:** See complexity section  
**Space:** O(N)

---

## Solution (java)

```java
class RandomizedCollection {
    ArrayList<Integer> arr;
    HashMap<Integer,HashSet<Integer>> hm;
    public RandomizedCollection() {
        arr  = new ArrayList<>();
        hm =  new HashMap<>();
    }
    
    public boolean insert(int val) {
        boolean flag = true;
        if(hm.containsKey(val)) flag = false;
        HashSet<Integer> temp = hm.getOrDefault(val,new HashSet<>());
        temp.add(arr.size());
        hm.put(val, temp);
        arr.add(val);
        return flag;
    }
    
    public boolean remove(int val) {
        if(!hm.containsKey(val)) return false;
        // array me se O(1) removal bs last index pe hota hai, isliye jo bhi udaana hai usko last pe exchange krdo
        // jo udaana hai uskka index kahan se laaye? isliye ek hashmap me sb values ka index store krlo
        HashSet<Integer> indices = hm.get(val);
        int remIndex = indices.iterator().next(); // isse first waali aajati hai 
        int lastIndex = arr.size()-1;
        int lastVal = arr.get(lastIndex);
        // . . 7  .. . . .. 12
        
        // 7 -> 2,
        // 12 -> 3,5,4
        hm.get(val).remove(remIndex);
        
        if(remIndex != lastIndex){
            arr.set(remIndex,lastVal);
            hm.get(lastVal).remove(lastIndex);
            hm.get(lastVal).add(remIndex);
        }
        arr.remove(lastIndex);
       
        
        if(hm.get(val).isEmpty()) hm.remove(val);
        return true;
    }
    
    public int getRandom() {
        //kyonki hashmap se random ni le paayenge isliey arraylist banana padha
        int random = (int) (Math.random()*arr.size());
        return arr.get(random);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

---

---
## Quick Revision
This problem asks to implement a data structure that supports insertion, deletion, and random retrieval of elements in O(1) time, allowing for duplicate values.
The solution uses an ArrayList to store elements and a HashMap to map values to their indices in the ArrayList.

## Intuition
The core challenge is achieving O(1) for all operations, especially deletion and random retrieval.
- `getRandom()`: An `ArrayList` allows O(1) random access by index.
- `insert()`: Appending to an `ArrayList` is amortized O(1).
- `remove()`: Removing an element from the middle of an `ArrayList` is O(n). To make it O(1), we can swap the element to be removed with the last element and then remove the last element. This requires efficiently finding the index of the element to be removed and updating the indices of the swapped elements. A `HashMap` mapping values to their indices is crucial here. Since duplicates are allowed, each value needs to map to a collection of its indices. A `HashSet` is suitable for storing these indices.

## Algorithm
1.  **Initialization**:
    *   Create an `ArrayList` (`arr`) to store the elements.
    *   Create a `HashMap` (`hm`) where keys are the elements and values are `HashSet`s of indices where that element appears in `arr`.

2.  **`insert(val)`**:
    *   Check if `val` already exists in `hm`. If it does, set a flag `false` (indicating a duplicate was not inserted). Otherwise, set it to `true`.
    *   Get the `HashSet` of indices for `val` from `hm`. If `val` is not present, create a new empty `HashSet`.
    *   Add the current size of `arr` (which will be the index of the new element) to this `HashSet`.
    *   Put the updated `HashSet` back into `hm` for `val`.
    *   Add `val` to the end of `arr`.
    *   Return the flag indicating if it was a new insertion.

3.  **`remove(val)`**:
    *   If `val` is not in `hm`, return `false`.
    *   Get the `HashSet` of indices for `val` from `hm`.
    *   Pick an arbitrary index (`remIndex`) from this `HashSet` (e.g., using `iterator().next()`). This is the index of an instance of `val` we want to remove.
    *   Get the last element (`lastVal`) and its index (`lastIndex`) from `arr`.
    *   If `remIndex` is not the same as `lastIndex`:
        *   Replace the element at `remIndex` in `arr` with `lastVal`.
        *   Remove `lastIndex` from the `HashSet` associated with `lastVal` in `hm`.
        *   Add `remIndex` to the `HashSet` associated with `lastVal` in `hm`.
    *   Remove `lastIndex` from `arr`.
    *   Remove `remIndex` from the `HashSet` associated with `val` in `hm`.
    *   If the `HashSet` for `val` becomes empty, remove `val` from `hm`.
    *   Return `true`.

4.  **`getRandom()`**:
    *   Generate a random integer between 0 (inclusive) and `arr.size()` (exclusive).
    *   Return the element at that random index from `arr`.

## Concept to Remember
*   **Amortized O(1) Operations**: Understanding how `ArrayList` append operations achieve this.
*   **Hash Map for Index Tracking**: Using a hash map to quickly find the location of elements for O(1) deletion.
*   **Handling Duplicates**: The need for a collection (like `HashSet`) to store multiple indices for the same value.
*   **O(1) Deletion Trick**: Swapping the element to be deleted with the last element and then removing the last element.

## Common Mistakes
*   **Not handling duplicates correctly**: Assuming each value maps to a single index, leading to incorrect removal or tracking.
*   **Inefficient removal from `ArrayList`**: Directly using `arr.remove(index)` without the swap-and-pop trick, resulting in O(n) time.
*   **Forgetting to update indices in the `HashMap`**: When an element is swapped, its index in the `HashMap` must be updated.
*   **Edge cases in `remove()`**: Not correctly handling the case where the element to be removed is the last element.
*   **Incorrect random number generation**: Off-by-one errors in generating the random index.

## Complexity Analysis
- Time:
    - `insert(val)`: O(1) - `HashMap` operations and `ArrayList` append are O(1) on average.
    - `remove(val)`: O(1) - `HashMap` operations, `ArrayList` `set` and `remove` (of the last element) are O(1).
    - `getRandom()`: O(1) - `Math.random()` and `ArrayList.get()` are O(1).
- Space: O(N) - where N is the number of elements stored in the collection. The `ArrayList` stores N elements, and the `HashMap` stores up to N entries (each mapping to a `HashSet` that collectively stores N indices).

## Commented Code
```java
import java.util.ArrayList; // Import ArrayList for dynamic array
import java.util.HashMap;   // Import HashMap for key-value mapping
import java.util.HashSet;   // Import HashSet for storing unique indices

class RandomizedCollection {
    ArrayList<Integer> arr; // Stores the actual elements of the collection.
    HashMap<Integer,HashSet<Integer>> hm; // Maps each value to a set of indices where it appears in 'arr'.

    // Constructor to initialize the data structures.
    public RandomizedCollection() {
        arr  = new ArrayList<>(); // Initialize an empty ArrayList.
        hm =  new HashMap<>();    // Initialize an empty HashMap.
    }
    
    // Inserts a value into the collection.
    // Returns true if the collection did not already contain the specified element.
    public boolean insert(int val) {
        boolean flag = true; // Assume the value is new by default.
        // Check if the value already exists in the HashMap.
        if(hm.containsKey(val)) {
            flag = false; // If it exists, it's a duplicate insertion, so set flag to false.
        }
        // Get the set of indices for 'val'. If 'val' is not in the map, create a new HashSet.
        HashSet<Integer> temp = hm.getOrDefault(val,new HashSet<>());
        // Add the current size of 'arr' (which is the index where 'val' will be added) to the set of indices.
        temp.add(arr.size());
        // Update the HashMap with the new set of indices for 'val'.
        hm.put(val, temp);
        // Add the value to the end of the ArrayList. This is an O(1) amortized operation.
        arr.add(val);
        // Return whether the insertion was of a new element (true) or a duplicate (false).
        return flag;
    }
    
    // Removes a value from the collection.
    // Returns true if the collection contained the specified element.
    public boolean remove(int val) {
        // If the value is not in the HashMap, it's not in the collection, so return false.
        if(!hm.containsKey(val)) return false;
        
        // To achieve O(1) removal from an ArrayList, we swap the element to be removed
        // with the last element and then remove the last element.
        // We need to know the index of an instance of 'val' to remove.
        HashSet<Integer> indices = hm.get(val); // Get the set of indices for 'val'.
        // Get the first index from the set. This is the index of one instance of 'val' to remove.
        int remIndex = indices.iterator().next(); 
        
        // Get the index of the last element in the ArrayList.
        int lastIndex = arr.size()-1;
        // Get the value of the last element.
        int lastVal = arr.get(lastIndex);
        
        // Remove the index 'remIndex' from the set of indices for 'val'.
        hm.get(val).remove(remIndex);
        
        // If the element to be removed is NOT the last element:
        if(remIndex != lastIndex){
            // Replace the element at 'remIndex' with 'lastVal'. This is O(1).
            arr.set(remIndex,lastVal);
            // Remove the 'lastIndex' from the set of indices for 'lastVal' in the HashMap.
            hm.get(lastVal).remove(lastIndex);
            // Add 'remIndex' to the set of indices for 'lastVal' in the HashMap, as 'lastVal' is now at 'remIndex'.
            hm.get(lastVal).add(remIndex);
        }
        
        // Remove the last element from the ArrayList. This is O(1).
        arr.remove(lastIndex);
       
        // If the set of indices for 'val' is now empty, remove 'val' from the HashMap entirely.
        if(hm.get(val).isEmpty()) {
            hm.remove(val);
        }
        // Return true to indicate successful removal.
        return true;
    }
    
    // Returns a random element from the collection.
    public int getRandom() {
        // We need an ArrayList to efficiently get a random element by index in O(1).
        // Generate a random index within the bounds of the ArrayList.
        int random = (int) (Math.random()*arr.size());
        // Return the element at the randomly generated index.
        return arr.get(random);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

## Interview Tips
*   Clearly explain the O(1) deletion strategy: swapping with the last element and removing the last.
*   Emphasize why a `HashMap` mapping to a `HashSet` is necessary to handle duplicates and track indices.
*   Walk through a small example for `remove()` to show how indices are updated in both the `ArrayList` and `HashMap`.
*   Be prepared to discuss the time and space complexity of each operation.

## Revision Checklist
- [ ] Understand the O(1) requirement for all operations.
- [ ] Implement `insert` using `ArrayList` and `HashMap` with `HashSet` for indices.
- [ ] Implement `remove` using the swap-with-last-and-pop trick.
- [ ] Ensure `HashMap` indices are correctly updated during removal.
- [ ] Implement `getRandom` using `ArrayList`'s random access.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases like removing the last element or removing the only instance of a value.

## Similar Problems
*   Insert Delete GetRandom O(1) (without duplicates)
*   Design HashSet
*   Design HashMap

## Tags
`Array` `Hash Map` `Design` `Randomized`
