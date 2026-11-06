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
Design a data structure that supports insert, remove, and getRandom operations in expected O(1) time, allowing duplicate elements.
Achieve this by combining a dynamic array for O(1) access and random selection, with a hash map tracking a set of indices for each value to handle duplicates.

## Intuition
The standard `RandomizedSet` problem requires O(1) operations for unique elements, which is easily solved using an ArrayList and a HashMap. However, when duplicates are allowed, a single index per value is insufficient because multiple occurrences of the same number need to be tracked. The "aha moment" is to map each value to a *set* of indices instead of a single integer. When removing an element, we can pick any arbitrary index of that value from its set, swap it with the last element in the array to maintain O(1) removal, and update the indices in our hash map accordingly.

## Algorithm
1. Initialize an `ArrayList` (`arr`) to store the elements and a `HashMap` (`hm`) mapping each value to a `HashSet` of its indices.
2. **Insert(val)**:
   - Add `val` to the end of `arr`.
   - Retrieve or create the `HashSet` of indices for `val` in `hm`.
   - Add the new index (`arr.size() - 1`) to this set.
   - Return `true` if the value was not previously present (i.e., its index set was empty/non-existent), otherwise `false`.
3. **Remove(val)**:
   - Check if `val` exists in `hm`. If not, return `false`.
   - Get one arbitrary index of `val` from its `HashSet` using an iterator.
   - Remove this index from the set; if the set becomes empty, remove `val` entirely from `hm`.
   - Get the last element of `arr` (`lastNum`) and its index (`lastIndex`).
   - If the index to remove is not the last index, overwrite the element at `index` with `lastNum`, update `lastNum`'s index set in `hm` by removing `lastIndex` and adding `index`.
   - Remove the last element from `arr`.
   - Return `true`.
4. **GetRandom()**:
   - Generate a random integer within the bounds of `arr`'s size.
   - Return the element at that random index in `arr`.

## Concept to Remember
- **O(1) Array Removal via Swapping**: Deleting from the middle of an array takes O(N) time, but swapping the target element with the last element reduces removal time to O(1).
- **Handling Duplicates with Collections**: Using a secondary data structure (like a `HashSet`) as a value in a `HashMap` allows tracking multiple occurrences without losing O(1) lookups.
- **State Synchronization**: Keeping the array and hash map synchronized when elements move indices is critical for correctness.

## Common Mistakes
- Forgetting to handle the edge case where the element being removed is already the last element in the array.
- Using a simple list or queue for indices, which would make index removal O(N) instead of O(1) (average time for HashSet).
- Failing to clean up empty HashSets from the HashMap, leading to memory leaks or logic bugs.
- Returning the wrong boolean flag on `insert` (it should return `true` if the element was completely new to the collection).

## Complexity Analysis
- Time: O(1) average time for `insert`, `remove`, and `getRandom` because ArrayList appends/removals at the end and HashSet operations are O(1) on average.
- Space: O(N) where N is the total number of elements stored in the collection, required by the ArrayList and HashMap.

## Commented Code
```java
class RandomizedCollection {
    List<Integer> arr; // Store elements; order doesn't matter, duplicates allowed, enables O(1) removal from the end
    Map<Integer, HashSet<Integer>> hm; // Map each value to a set of its indices for O(1) retrieval and duplicate tracking
    
    public RandomizedCollection() {
        arr = new ArrayList<>(); // Initialize the dynamic array
        hm = new HashMap<>(); // Initialize the hash map for index tracking
    }
    
    public boolean insert(int val) {
        arr.add(val); // Append the value to the end of the array
        boolean present = hm.containsKey(val); // Check if the value already exists in our map
        
        HashSet<Integer> indices = hm.getOrDefault(val, new HashSet<>()); // Get existing index set or create a new one
        indices.add(arr.size() - 1); // Add the current array index to the value's set
        hm.put(val, indices); // Update the map with the modified index set
        
        return !present; // Return true if it was a new insertion, false if it was already present
    }
    
    public boolean remove(int val) {
        if(!hm.containsKey(val)) return false; // If the value doesn't exist, we cannot remove it
        HashSet<Integer> indices = hm.get(val); // Retrieve the set of indices for this value
        int index = indices.iterator().next(); // Grab one arbitrary index from the set
        indices.remove(index); // Remove this index from the set
        if(indices.isEmpty()) hm.remove(val); // Clean up the map entry if no occurrences of the value remain
        
        int lastIndex = arr.size() - 1; // Find the index of the last element in the array
        int lastNum = arr.get(lastIndex); // Get the value of the last element
        
        if(index != lastIndex) { // If the element to remove is not already the last element
            arr.set(index, lastNum); // Move the last element into the hole left by the removed element
            hm.get(lastNum).remove(lastIndex); // Remove the old last index from lastNum's set
            hm.get(lastNum).add(index); // Add the new index to lastNum's set
        }
        arr.remove(lastIndex); // Remove the last element from the array in O(1) time
        return true; // Successfully removed
    }
    
    public int getRandom() {
        int index = (int) (Math.random() * arr.size()); // Generate a random index within bounds
        return arr.get(index); // Return the element at the random index
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
- Clarify the requirements upfront: Explain why a standard `RandomizedSet` fails here (due to duplicates) and why a `HashMap<Integer, Set<Integer>>` solves it.
- Walk through the swap-with-last optimization clearly using a small example on a whiteboard or shared notepad.
- Discuss trade-offs: Mention that `HashSet` operations have O(1) average time complexity, but worst-case can degrade if hash collisions occur (though practically negligible).
- Write clean helper logic and modularize if necessary, ensuring edge cases like removing the only element or removing an element that appears multiple times are handled smoothly.

## Revision Checklist
- [ ] Understand why a `HashSet` is needed instead of a single integer in the hash map.
- [ ] Master the O(1) array removal technique via swapping with the last element.
- [ ] Remember to update the hash map indices for the swapped element.
- [ ] Handle cleanup of empty sets from the hash map to prevent memory waste.
- [ ] Verify return values for `insert` and `remove` according to problem specifications.

## Similar Problems
- `Insert Delete getRandom O(1)`
- `First Unique Character in a String`
- `All O(one) Data Structure`

## Tags
`Array` `Hash Table` `Design` `Math` `Randomized`
