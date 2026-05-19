# Insert Delete Getrandom O1

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Design` `Randomized`  
**Time:** O(1)  
**Space:** O(n)

---

## Solution (java)

```java
class RandomizedSet {
    HashMap<Integer,Integer> hm;
    ArrayList<Integer> al ;
    public RandomizedSet() {
        hm = new HashMap<>();
        al = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if(hm.containsKey(val)) return false;
        hm.put(val,al.size());
        al.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if(!hm.containsKey(val)) return false;

        // removal we will do only from end of array because that is o(1), so we will keep the last element at the toBeDeleted index, and then remove the last element. then update the hashmap accordingly.
        int index = hm.get(val);
        int lastVal = al.get(al.size() - 1);
        al.set(index,lastVal);
        al.remove(al.size()-1);

        hm.put(lastVal,index);
        hm.remove(val);
        
        return true;
    }
    
    public int getRandom() {
        int random = (int)(Math.random() * al.size());
        return al.get(random);
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
Insert elements, delete elements, and retrieve a random element from a set in constant time.

Solution: Use a HashMap to store the indices of elements in an ArrayList, allowing for efficient insertion, deletion, and random retrieval.

## Intuition
The key insight is that by storing the indices of elements, we can update them efficiently when removing an element. This way, we can maintain a mapping between elements and their corresponding indices, enabling constant-time operations.

## Algorithm

1. Initialize a HashMap to store the indices of elements and an ArrayList to store the actual elements.
2. In the `insert` method, check if the element already exists in the HashMap. If it does, return false; otherwise, add the element to the ArrayList and update its index in the HashMap.
3. In the `remove` method, check if the element exists in the HashMap. If not, return false. Otherwise, swap the element to be deleted with the last element in the ArrayList (which is O(1)), remove the last element from the ArrayList, and update the indices of both elements in the HashMap.
4. In the `getRandom` method, generate a random index between 0 and the size of the ArrayList, then return the element at that index.

## Concept to Remember
* Using a mapping data structure (HashMap) can improve lookup efficiency.
* Swapping two elements in an array or list can be done in constant time if done efficiently.
* Understanding the trade-offs between different data structures is crucial for solving problems efficiently.

## Common Mistakes
* Failing to update indices correctly when removing an element from the ArrayList.
* Not using a mapping data structure (HashMap) to improve lookup efficiency.
* Trying to implement a complex algorithm without considering the simplicity and elegance of the solution.

## Complexity Analysis
- Time: O(1) for `insert`, `remove`, and `getRandom` operations, since they all take constant time in the worst case.
- Space: O(n) for storing n elements in the ArrayList and HashMap.

## Commented Code
```java
class RandomizedSet {
    // HashMap to store indices of elements (key) and their corresponding values (element)
    private HashMap<Integer, Integer> hm;
    
    // ArrayList to store actual elements
    private ArrayList<Integer> al;

    public RandomizedSet() {
        hm = new HashMap<>();
        al = new ArrayList<>();
    }

    /**
     * Inserts an element into the set.
     *
     * @param val value to be inserted
     * @return true if successful, false otherwise (if element already exists)
     */
    public boolean insert(int val) {
        // Check if element already exists in HashMap
        if (hm.containsKey(val)) return false;
        
        // Add element to ArrayList and update its index in the HashMap
        hm.put(val, al.size());
        al.add(val);
        
        return true;
    }

    /**
     * Removes an element from the set.
     *
     * @param val value to be removed
     * @return true if successful, false otherwise (if element does not exist)
     */
    public boolean remove(int val) {
        // Check if element exists in HashMap
        if (!hm.containsKey(val)) return false;

        // Remove element from ArrayList by swapping it with the last element
        int index = hm.get(val);
        int lastVal = al.get(al.size() - 1);
        
        al.set(index, lastVal); // swap elements
        al.remove(al.size()-1); // remove last element
        
        // Update indices in the HashMap
        hm.put(lastVal, index);
        hm.remove(val);
        
        return true;
    }

    /**
     * Retrieves a random element from the set.
     *
     * @return a random element from the set
     */
    public int getRandom() {
        // Generate a random index between 0 and the size of the ArrayList
        int random = (int)(Math.random() * al.size());
        
        return al.get(random); // return the element at the random index
    }
}
```

## Interview Tips

* Make sure to understand the problem requirements clearly before starting to code.
* Use a HashMap to store indices and an ArrayList for actual elements, as it allows for efficient insertion, deletion, and retrieval of elements.
* Be prepared to discuss trade-offs between different data structures and algorithms.

## Revision Checklist
- [ ] Understand problem requirements carefully.
- [ ] Use a mapping data structure (HashMap) for efficient lookup.
- [ ] Implement `insert`, `remove`, and `getRandom` methods correctly.
- [ ] Review time and space complexity analysis.

## Similar Problems

* LeetCode 380: Insert Delete Getrandom O(1)
* LeetCode 706: Design HashMap
* LeetCode 217: Contains Duplicate
