# Insert Delete Getrandom O1 Duplicates Allowed

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Math` `Design` `Randomized`  
**Time:** O(1)  
**Space:** See complexity section

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
The problem requires implementing a data structure that supports inserting, deleting, and getting random elements with O(1) time complexity, even if duplicates are allowed.
We solve it by using a combination of an array and a hash map to efficiently keep track of the indices of each element.

## Intuition
The key insight here is that we can use a hash map to store the indices of each element in the array. This allows us to insert, delete, and get random elements with O(1) time complexity. When an element is inserted or deleted, we update the hash map accordingly.

## Algorithm

1. Initialize an empty array `arr` and a hash map `hm`.
2. In the `insert(val)` method:
	* Check if `val` already exists in the hash map.
	* If it does, set `flag` to `false`.
	* Otherwise, add `val` to the array and its index to the corresponding set in the hash map.
	* Return `flag`.
3. In the `remove(val)` method:
	* Check if `val` exists in the hash map.
	* If not, return `false`.
	* Get the set of indices for `val` from the hash map.
	* Remove the element at the first index of this set from the array and update its corresponding set in the hash map.
	* Update the last element's value and remove it from its set in the hash map if necessary.
	* Remove the last element from the array.
	* If `val`'s set in the hash map is empty, remove it from the hash map.
4. In the `getRandom()` method:
	* Generate a random index using the length of the array and return the corresponding element.

## Concept to Remember
• **Hash Map**: A data structure that stores key-value pairs and allows for efficient lookup, insertion, and deletion.
• **Array**: A collection of elements that can be accessed by their indices.
• **Set**: An unordered collection of unique elements.

## Common Mistakes

* Not initializing the hash map properly.
* Failing to update the hash map when an element is inserted or deleted.
* Trying to remove an element from its set in the hash map without checking if it exists first.

## Complexity Analysis
- Time: O(1) for all operations (insert, delete, getRandom)
  / Reason: Hash table operations and array index access are constant time.

## Commented Code
```java
class RandomizedCollection {
    ArrayList<Integer> arr; // Array to store elements
    HashMap<Integer, HashSet<Integer>> hm; // Hash map to store indices of each element

    public RandomizedCollection() {
        arr = new ArrayList<>();
        hm = new HashMap<>(); // Initialize hash map with empty sets for all possible values
    }

    public boolean insert(int val) {
        boolean flag = true;
        if (hm.containsKey(val)) { // Check if val already exists in the hash map
            flag = false; // If it does, set flag to false
        } else {
            // Add val to array and its index to corresponding set in hash map
            HashSet<Integer> temp = hm.getOrDefault(val, new HashSet<>());
            temp.add(arr.size()); // Add current index of the array as an element's index in its set
            hm.put(val, temp);
            arr.add(val); // Insert val at the end of the array
        }
        return flag; // Return flag indicating whether val was inserted successfully
    }

    public boolean remove(int val) {
        if (!hm.containsKey(val)) { // Check if val exists in hash map
            return false; // If not, return false
        } else {
            // Get set of indices for val from hash map
            HashSet<Integer> indices = hm.get(val);
            int remIndex = indices.iterator().next(); // Remove element at first index of the set
            int lastIndex = arr.size() - 1;
            int lastVal = arr.get(lastIndex); // Update last element's value and remove it from its set in hash map if necessary
            if (remIndex != lastIndex) {
                arr.set(remIndex, lastVal);
                hm.get(lastVal).remove(lastIndex);
                hm.get(lastVal).add(remIndex);
            }
            arr.remove(lastIndex); // Remove last element from array

            if (hm.get(val).isEmpty()) { // If val's set in hash map is empty, remove it
                hm.remove(val);
            }
        }
        return true; // Return true indicating successful removal
    }

    public int getRandom() {
        // Generate random index using length of the array and return corresponding element
        int random = (int) (Math.random() * arr.size());
        return arr.get(random);
    }
}
```

## Interview Tips

* Be prepared to explain why your solution works, including any trade-offs.
* Highlight the use of hash maps to efficiently store indices of elements.
* Practice explaining your code and algorithm to a non-technical person.

## Revision Checklist
- [ ] Review hash map operations (insert, delete, lookup) for efficiency.
- [ ] Ensure all edge cases are handled in `insert` and `remove` methods.
- [ ] Double-check implementation of `getRandom` method for correctness.

## Similar Problems

* LeetCode: 380. Insert Delete Getrandom O1
* LeetCode: 706. Design HashMap
