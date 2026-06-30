# Range Frequency Queries

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Binary Search` `Design` `Segment Tree`  
**Time:** O(log n)  
**Space:** O(n)

---

## Solution (java)

```java
class RangeFreqQuery {
    HashMap<Integer,ArrayList<Integer>> hm;
    public RangeFreqQuery(int[] arr) {
        this.hm = new HashMap<>();
        for(int i =0;i<arr.length;i++) hm.computeIfAbsent(arr[i],k -> new ArrayList<>()).add(i);
    }
    
    public int query(int left, int right, int value) {
        if(!hm.containsKey(value)) return 0;
        return findBound(hm.get(value),right,true)-findBound(hm.get(value),left,false);
    }  
    private int findBound(ArrayList<Integer> arr, int target, boolean isLowerBound){
        int start = 0; int end = arr.size();
        while(start<end){
            int mid = start + (end-start)/2;
            if(arr.get(mid) < target || (isLowerBound && arr.get(mid) == target)) start = mid+1;
            else end = mid;
        }
        return start;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
 
   // value -> {i1, i2, i3, i4}
    // 12 -> 0,9
    // 33 -> 1,7,11,45,60,90,110,120,234
    // 20 80
    // 4 -> 2
    // 56 -> 3,11
    // 22 -> 4,8
    // 2 -> 5
    // 34 -> 6,10
    
    // freq = (ceilOf(left) - floorOf(right))+ 1;
```

---

---

## Quick Revision
The problem is to design a data structure that can efficiently answer frequency queries of elements within a given range in an array. This can be solved using a HashMap and binary search.

## Intuition
The key insight here is to store the indices of each element in a HashMap, where each key is the element value and its corresponding value is a list of indices. Then, for a query, we simply count the number of indices within the given range by performing two binary searches: one from the left and one from the right.

## Algorithm
1. Initialize a HashMap to store elements as keys and their indices as values.
2. Populate the HashMap with elements from the input array.
3. For each query, check if the target element exists in the HashMap.
4. If it does, perform two binary searches: one to find the smallest index greater than or equal to the left boundary, and another to find the largest index less than or equal to the right boundary.
5. The difference between these two indices is the count of elements within the given range.

## Concept to Remember
* HashMap for efficient lookup and storage of data
* Binary search for finding indices within a range
* Counting frequency by calculating the difference between two indices

## Common Mistakes
* Forgetting to handle edge cases where the target element is not in the array or the query boundaries are invalid.
* Not using binary search to find the correct indices, resulting in incorrect frequency counts.
* Not handling the case where multiple elements have the same index.

## Complexity Analysis
- Time: O(log n) for each query, where n is the number of elements in the array. This is because we perform two binary searches per query.
- Space: O(n) to store the indices of all elements in the HashMap.

## Commented Code
```java
class RangeFreqQuery {
    // HashMap to store elements as keys and their indices as values
    HashMap<Integer, ArrayList<Integer>> hm;

    public RangeFreqQuery(int[] arr) {
        this.hm = new HashMap<>();
        
        // Populate the HashMap with elements from the input array
        for (int i = 0; i < arr.length; i++) {
            hm.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int value) {
        // Check if the target element exists in the HashMap
        if (!hm.containsKey(value)) return 0;

        // Perform two binary searches to find the smallest index greater than or equal to the left boundary
        // and the largest index less than or equal to the right boundary.
        return findBound(hm.get(value), right, true) - findBound(hm.get(value), left, false);
    }

    private int findBound(ArrayList<Integer> arr, int target, boolean isLowerBound) {
        int start = 0;
        int end = arr.size();

        // Perform binary search to find the correct index
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (arr.get(mid) < target || (isLowerBound && arr.get(mid) == target)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}
```

## Interview Tips
* Practice solving problems that involve designing data structures and algorithms.
* Make sure to handle edge cases thoroughly, as they can make a big difference in the correctness of your solution.
* When explaining your code, focus on the key insights and design decisions you made, rather than just listing out the code.

## Revision Checklist
- [ ] Understand how the HashMap is used to store elements and their indices.
- [ ] Recognize the importance of binary search in finding the correct indices.
- [ ] Practice solving problems that involve frequency queries.

## Similar Problems
* LeetCode 327: "Count of Range Sum"
* LeetCode 353: "Design an RFID System"

## Tags
`Array`, `HashMap`, `Binary Search`
