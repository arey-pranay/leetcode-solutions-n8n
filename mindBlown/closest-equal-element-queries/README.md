# Closest Equal Element Queries

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Binary Search`  
**Time:** O(N + Q log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        HashMap<Integer,ArrayList<Integer>> hm = new HashMap<>();
        int n = nums.length;
        for(int i =0;i<n;i++){
            ArrayList<Integer> arr = hm.getOrDefault(nums[i],new ArrayList<>());
            arr.add(i);
            hm.put(nums[i],arr);
        }
        List<Integer> ans = new ArrayList<>();
        for(int query : queries){
            int val = nums[query];
            ArrayList<Integer> indices = hm.getOrDefault(val,new ArrayList<>());
            if(indices.size()<2) ans.add(-1);
            else{
                //agr query is 5. humaare indices ki indicesay me 5 konse index pe hai      
                int index = Collections.binarySearch(indices,query);
                // ab humko uske closes 2 index se dist check krna hai
                int minDist = Math.min(getAns(query,index-1,indices,n),getAns(query,index+1,indices,n));
                ans.add(minDist);
            }
        }
        return ans;
    }
    public int getAns(int query, int neigh, ArrayList<Integer> arr,int n){
        if(neigh==-1) neigh = arr.size()-1;
        if(neigh==arr.size()) neigh = 0;
        
        int d = Math.abs(query - arr.get(neigh));
        return Math.min(d,n-d);
    }
}
```

---

---
## Quick Revision
Given an array `nums` and queries, find the minimum distance to another occurrence of the same element.
We solve this by pre-processing element indices and using binary search for efficient lookups.

## Intuition
The core idea is that for any given element at a specific index, we need to find the closest other occurrence of that *same* element. If we have all the indices where a particular number appears, we can quickly find its neighbors. The "closest" distance can be either the direct difference or the "wrap-around" distance (distance considering the array as circular).

## Algorithm
1.  **Pre-processing:**
    *   Create a hash map (`HashMap<Integer, ArrayList<Integer>>`) to store the indices for each unique number in `nums`.
    *   Iterate through `nums`. For each element `nums[i]` at index `i`:
        *   Get the list of indices associated with `nums[i]` from the hash map. If it doesn't exist, create a new `ArrayList`.
        *   Add the current index `i` to this list.
        *   Put the updated list back into the hash map with `nums[i]` as the key.
2.  **Query Processing:**
    *   Initialize an empty list `ans` to store the results for each query.
    *   Iterate through each `query` in the `queries` array.
    *   For the current `query` (which is an index in `nums`):
        *   Get the value `val = nums[query]`.
        *   Retrieve the list of indices (`indices`) where `val` appears from the hash map.
        *   If `indices.size() < 2` (meaning `val` appears only once), add `-1` to `ans` (no other equal element exists).
        *   If `indices.size() >= 2`:
            *   Use `Collections.binarySearch(indices, query)` to find the position of the current `query` index within the sorted `indices` list. This returns the index of the element if found, or `-(insertion point) - 1` if not found. Since `query` is guaranteed to be in `indices`, it will return its exact position.
            *   Let `index` be the result of `binarySearch`.
            *   Calculate the minimum distance by considering two neighbors:
                *   The element *before* the current `query` in the `indices` list (at `index - 1`).
                *   The element *after* the current `query` in the `indices` list (at `index + 1`).
            *   The `getAns` helper function calculates the minimum distance to a given neighbor index, considering both direct and wrap-around distances.
            *   The minimum of the distances to the previous and next neighbors is added to `ans`.
3.  **Return `ans`**.

**Helper Function `getAns(query, neigh, arr, n)`:**
*   `query`: The index of the element in `nums` for which we are finding the closest equal element.
*   `neigh`: The index within the `arr` (list of indices for the same value) that we are comparing against.
*   `arr`: The `ArrayList` of indices for the current value.
*   `n`: The total length of the `nums` array.
*   **Logic:**
    *   Handles wrap-around for `neigh`:
        *   If `neigh` is `-1` (meaning we are looking at the element before the first element in `arr`), it wraps around to the last element of `arr` (`arr.size() - 1`).
        *   If `neigh` is `arr.size()` (meaning we are looking at the element after the last element in `arr`), it wraps around to the first element of `arr` (`0`).
    *   Calculates the direct absolute difference `d = Math.abs(query - arr.get(neigh))`.
    *   Calculates the wrap-around distance: `n - d`.
    *   Returns the minimum of `d` and `n - d`.

## Concept to Remember
*   **Hash Maps for Indexing:** Efficiently storing and retrieving lists of indices for duplicate elements.
*   **Binary Search on Sorted Lists:** Quickly finding the position of an element (or its potential insertion point) in a sorted list of indices.
*   **Circular Array Distance:** Understanding how to calculate distances when the array can be treated as circular (wrap-around).
*   **`Collections.binarySearch`:** Its behavior and return values, especially for finding elements and their neighbors.

## Common Mistakes
*   **Not handling wrap-around distance:** Only considering the direct difference `abs(index1 - index2)` and forgetting `n - abs(index1 - index2)`.
*   **Incorrectly handling `binarySearch` return values:** Misinterpreting the negative return value when an element is not found (though in this problem, the query index is guaranteed to be in the list).
*   **Off-by-one errors with neighbor indices:** Incorrectly calculating `index - 1` or `index + 1` and not properly handling boundary conditions (e.g., `index - 1` when `index` is 0, or `index + 1` when `index` is the last element).
*   **Not pre-processing:** Trying to find the closest element for each query by iterating through the array, leading to a much slower solution.
*   **Forgetting the `-1` case:** Not returning `-1` when an element appears only once.

## Complexity Analysis
*   **Time:** O(N + Q log N)
    *   O(N) for pre-processing: Iterating through `nums` once to build the hash map. Each `ArrayList.add()` is amortized O(1).
    *   O(Q log N) for queries: For each of the `Q` queries, we perform a `Collections.binarySearch` on an `ArrayList` of size at most `N`. In the worst case, all elements are the same, and the list has size `N`. `binarySearch` takes O(log K) where K is the size of the list. So, O(Q log N).
*   **Space:** O(N)
    *   The hash map stores all indices of the array. In the worst case, if all elements are distinct, it stores `N` entries, each with a list containing one index. If all elements are the same, it stores one entry with a list of `N` indices. Thus, the space complexity is proportional to the number of elements in `nums`.

## Commented Code
```java
class Solution {
    // Main method to process all queries
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        // HashMap to store value -> list of indices where that value appears
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        // Get the length of the input array
        int n = nums.length;

        // Pre-processing step: Populate the hash map with indices
        for (int i = 0; i < n; i++) {
            // Get the list of indices for the current number nums[i].
            // If the number is not yet in the map, getOrDefault returns a new ArrayList.
            ArrayList<Integer> arr = hm.getOrDefault(nums[i], new ArrayList<>());
            // Add the current index 'i' to the list of indices for nums[i]
            arr.add(i);
            // Put the updated list back into the hash map
            hm.put(nums[i], arr);
        }

        // List to store the results for each query
        List<Integer> ans = new ArrayList<>();

        // Process each query
        for (int query : queries) {
            // Get the value at the current query index in the original nums array
            int val = nums[query];
            // Get the list of all indices where 'val' appears
            // Use getOrDefault to handle cases where val might not be in hm (though unlikely if query is valid)
            ArrayList<Integer> indices = hm.getOrDefault(val, new ArrayList<>());

            // If the value appears less than 2 times, there's no other equal element
            if (indices.size() < 2) {
                // Add -1 to the answer list, indicating no closest equal element
                ans.add(-1);
            } else {
                // If the value appears 2 or more times, find the closest one

                // Use binary search to find the position (index) of the current 'query' index
                // within the sorted 'indices' list.
                // Collections.binarySearch returns the index of the search key, if it is contained in the list;
                // otherwise, (-(insertion point) - 1). Since 'query' is guaranteed to be in 'indices',
                // it will return the exact index of 'query' within the 'indices' list.
                int index = Collections.binarySearch(indices, query);

                // Now we need to find the minimum distance to the closest neighbor.
                // We check the element before (index-1) and the element after (index+1) in the 'indices' list.
                // The getAns helper function calculates the minimum distance, considering wrap-around.
                // We take the minimum of the distance to the previous neighbor and the next neighbor.
                int minDist = Math.min(
                    getAns(query, index - 1, indices, n), // Distance to the previous element in the indices list
                    getAns(query, index + 1, indices, n)  // Distance to the next element in the indices list
                );
                // Add the calculated minimum distance to the answer list
                ans.add(minDist);
            }
        }
        // Return the list of results for all queries
        return ans;
    }

    // Helper function to calculate the minimum distance to a neighbor index, considering wrap-around
    public int getAns(int query, int neigh, ArrayList<Integer> arr, int n) {
        // Handle wrap-around for the 'neigh' index within the 'arr' list.
        // If 'neigh' is -1, it means we are looking at the element before the first one in 'arr'.
        // In a circular sense, this is the last element of 'arr'.
        if (neigh == -1) {
            neigh = arr.size() - 1; // Wrap around to the last index of arr
        }
        // If 'neigh' is arr.size(), it means we are looking at the element after the last one in 'arr'.
        // In a circular sense, this is the first element of 'arr'.
        if (neigh == arr.size()) {
            neigh = 0; // Wrap around to the first index of arr
        }

        // Calculate the direct absolute difference between the query index and the neighbor's actual index in nums
        int d = Math.abs(query - arr.get(neigh));
        // Calculate the wrap-around distance. The total circumference is 'n'.
        // The wrap-around distance is the total length minus the direct distance.
        // Return the minimum of the direct distance and the wrap-around distance.
        return Math.min(d, n - d);
    }
}
```

## Interview Tips
*   **Clarify "Closest":** Ask the interviewer to confirm if "closest" means direct distance or if wrap-around distance (treating the array as circular) should be considered. This problem explicitly requires wrap-around.
*   **Explain Pre-processing:** Clearly articulate why pre-processing the indices into a hash map is crucial for efficiency, especially when dealing with multiple queries.
*   **Walk Through `binarySearch`:** Be prepared to explain how `Collections.binarySearch` works, especially its return value, and how you use it to find neighbors in the sorted index list. Also, explain the logic of the `getAns` helper function, particularly the wrap-around calculation.
*   **Edge Cases:** Discuss edge cases like elements appearing only once, or the query index being the first/last occurrence of a value in the `indices` list.

## Revision Checklist
- [ ] Understand the problem statement: find minimum distance to another occurrence of the same element.
- [ ] Recognize the need for pre-processing to store indices of each number.
- [ ] Implement a hash map to store `value -> list of indices`.
- [ ] Iterate through `nums` to populate the hash map.
- [ ] For each query:
    - [ ] Get the value at the query index.
    - [ ] Retrieve the list of indices for that value.
    - [ ] Handle the case where the value appears only once (return -1).
    - [ ] Use `Collections.binarySearch` to find the query's position in the index list.
    - [ ] Calculate distances to the previous and next neighbors in the index list.
    - [ ] Implement wrap-around distance calculation (`n - direct_distance`).
    - [ ] Take the minimum of direct and wrap-around distances for each neighbor.
    - [ ] Take the minimum of distances to the previous and next neighbors.
    - [ ] Add the result to the answer list.
- [ ] Analyze time and space complexity.
- [ ] Consider common mistakes like missing wrap-around or off-by-one errors.

## Similar Problems
*   [304. Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/) (Pre-computation for range queries)
*   [208. Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) (Data structure for efficient lookups)
*   [36. Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) (Using sets/maps for checking uniqueness)
*   [1. Two Sum](https://leetcode.com/problems/two-sum/) (Basic hash map usage for finding pairs)

## Tags
`Array` `Hash Map` `Binary Search` `Two Pointers`
