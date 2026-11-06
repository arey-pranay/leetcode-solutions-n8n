# Insert Delete Getrandom O1 Duplicates Allowed

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Design` `Randomized`  
**Time:** O(1)  
**Space:** O(N)

---

## Solution (java)

```java
class RandomizedCollection {
    List<Integer> arr; // store whatever we have received, order doesnt matter here, but duplicates matter. we'll do O(1) removal from end
    Map<Integer, HashSet<Integer>> hm; // to store the indices of all the numbers, with O(1) retrieval
    public RandomizedCollection() {
        arr = new ArrayList<>();
        hm = new HashMap<>();
    }
    
    public boolean insert(int val) {
        arr.add(val);
        boolean present = hm.containsKey(val);
        
        HashSet<Integer> indices = hm.getOrDefault(val,new HashSet<>());
        indices.add(arr.size()-1);
        hm.put(val,indices);
        
        return !present;
    }
    
    public boolean remove(int val) {
        if(!hm.containsKey(val)) return false;
        HashSet<Integer> indices = hm.get(val);
        int index = indices.iterator().next();
        indices.remove(index);
        if(indices.isEmpty()) hm.remove(val);
        // this index has num to be removed, so put the last num here, and delete last position
        int lastIndex = arr.size()-1;
        int lastNum = arr.get(lastIndex);
        if(index!=lastIndex){
        arr.set(index,lastNum);
        hm.get(lastNum).remove(lastIndex);
        hm.get(lastNum).add(index);
        }
        arr.remove(lastIndex);
        return true;
    }
    
    public int getRandom() {
        int index = (int) (Math.random()*arr.size()); 
        return arr.get(index);
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
Design a data structure that supports insert, remove, and getRandom operations in expected O(1) time, allowing duplicate values.
Solve it using a dynamic array for O(1) random access combined with a hash map tracking a set of indices for each element to handle duplicates.

## Intuition
The core challenge is achieving $O(1)$ time complexity for all three operations: `insert`, `remove`, and `getRandom`. A dynamic array (`ArrayList`) provides $O(1)$ random access for `getRandom` and $O(1)$ append for `insert`. However, finding an element to remove takes $O(N)$ time in an array unless we know its index. To achieve $O(1)$ removal, we need a hash map to look up the element's indices. Because duplicates are allowed, a single element can appear multiple times in the array, meaning the hash map must store a collection of indices (like a `HashSet`) for every unique value. When removing an element, to avoid shifting all subsequent elements (which takes $O(N)$ time), we swap the element being removed with the very last element in the array, update the hash map accordingly, and pop the last element in $O(1)$ time.

## Algorithm
1. Initialize a dynamic list (`arr`) to store elements and a hash map (`hm`) mapping each value to a set of its indices (`HashSet<Integer>`).
2. For `insert(val)`:
   - Append `val` to the end of `arr`.
   - Add the new index (`arr.size() - 1`) to the set of indices for `val` in `hm`.
   - Return `true` if `val` was not previously in the map (i.e., its index set was empty/absent), otherwise return `false`.
3. For `remove(val)`:
   - Check if `val` exists in `hm`. If not, return `false`.
   - Retrieve an arbitrary index of `val` from its corresponding `HashSet` in `hm`.
   - Remove this index from the `HashSet`. If the set becomes empty, remove `val` from `hm`.
   - Get the last element of `arr` (`lastNum`) and its index (`lastIndex`).
   - If the index to remove is different from `lastIndex`, overwrite the element at `index` in `arr` with `lastNum`, update `lastNum`'s indices in `hm` by removing `lastIndex` and adding `index`.
   - Remove the last element from `arr`.
   - Return `true`.
4. For `getRandom()`:
   - Generate a random integer within the bounds of `arr.size()`.
   - Return the element at that random index in `arr`.

## Concept to Remember
- **Swap-with-Last Technique**: Deleting from the middle of an array takes $O(N)$ due to shifting, but swapping with the last element and truncating reduces deletion to $O(1)$.
- **Handling Duplicates with Hash Maps of Sets**: When elements can repeat, tracking multiple locations requires mapping values to collections of indices rather than single integer indices.
- **Trade-offs of `HashSet` Iteration**: Using `.iterator().next()` on a `HashSet` allows retrieving an index in $O(1)$ average time, avoiding $O(N)$ array searches.

## Common Mistakes
- Forgetting to handle the edge case where the element being removed is already the last element in the array (self-swap).
- Failing to clean up empty `HashSet` entries from the hash map, leading to memory bloat or incorrect existence checks.
- Forgetting to update the index of the swapped last element in the hash map.
- Using data structures like `ArrayList` for removals or searches directly, which degrades time complexity to $O(N)$.

## Complexity Analysis
- Time: O(1) average for `insert`, `remove`, and `getRandom` because hash map insertions/lookups and array appending/swapping take expected constant time.
- Space: O(N) where N is the total number of elements stored across all operations, required by the dynamic array and hash map.

## Commented Code
```java
class RandomizedCollection {
    // Dynamic array to store all elements, allowing duplicates and O(1) random access
    List<Integer> arr; 
    // Hash map mapping each value to a set of its indices for O(1) index retrieval
    Map<Integer, HashSet<Integer>> hm; 
    
    public RandomizedCollection() {
        // Initialize the dynamic array
        arr = new ArrayList<>();
        // Initialize the hash map
        hm = new HashMap<>();
    }
    
    public boolean insert(int val) {
        // Add the value to the end of the array
        arr.add(val);
        // Check if the value already exists in the collection
        boolean present = hm.containsKey(val);
        
        // Retrieve or create the set of indices for this value
        HashSet<Integer> indices = hm.getOrDefault(val, new HashSet<>());
        // Add the new index (end of the array) to the set
        indices.add(arr.size() - 1);
        // Put the updated index set back into the map
        hm.put(val, indices);
        
        // Return true if it was not present before, false otherwise
        return !present;
    }
    
    public boolean remove(int val) {
        // If the value does not exist, we cannot remove it
        if (!hm.containsKey(val)) return false;
        // Get the set of indices where this value appears
        HashSet<Integer> indices = hm.get(val);
        // Grab any arbitrary index from the set in O(1) time
        int index = indices.iterator().next();
        // Remove this index from the value's index set
        indices.remove(index);
        // If no more instances of this value exist, remove it from the map
        if (indices.isEmpty()) hm.remove(val);
        
        // Find the last index and the last number in the array
        int lastIndex = arr.size() - 1;
        int lastNum = arr.get(lastIndex);
        
        // If the element to remove is not the last element, swap it with the last element
        if (index != lastIndex) {
            // Overwrite the target index with the last number
            arr.set(index, lastNum);
            // Update the last number's index set: remove its old last index
            hm.get(lastNum).remove(lastIndex);
            // Add the new index where it was moved
            hm.get(lastNum).add(index);
        }
        // Remove the last element from the array in O(1) time
        arr.remove(lastIndex);
        // Return true indicating successful removal
        return true;
    }
    
    public int getRandom() {
        // Generate a random index between 0 and arr.size() - 1
        int index = (int) (Math.random() * arr.size()); 
        // Return the element at the random index
        return arr.get(index);
    }
}
```

## Interview Tips
- Clarify whether duplicates should affect `getRandom` probability (they should be weighted by their frequency, which naturally happens when storing every instance in the array).
- Walk through the swap-with-last logic clearly using a whiteboard or text diagram during the interview to prove your understanding of $O(1)$ removal.
- Discuss how you handle memory management and clean up empty hash sets to avoid stale entries.

## Revision Checklist
- [ ] Understand why a hash map of sets is necessary instead of a single integer index.
- [ ] Explain the swap-with-last array deletion optimization.
- [ ] Handle the edge case where the removed element is already at the tail of the array.
- [ ] Verify average $O(1)$ time complexity for all operations.

## Similar Problems
- `Insert Delete getRandom O(1)`
- `First Unique Character in a String`
- `All O(1) Data Structure`

## Tags
`Array` `Hash Table` `Math` `Design` `Randomized`
