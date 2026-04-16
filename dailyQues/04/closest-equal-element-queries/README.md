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
The core idea is that for any element at a given index, its closest equal element will be one of its neighbors in the sorted list of indices for that element's value. If we can quickly find the position of the current query index within the list of indices for its value, we can then check its immediate left and right neighbors. The distance calculation needs to consider the circular nature of the array if the closest element wraps around.

## Algorithm
1.  **Pre-processing:**
    *   Create a `HashMap` where keys are the numbers in `nums` and values are `ArrayLists` storing the indices where each number appears.
    *   Iterate through `nums` and populate this `HashMap`. For each `nums[i]`, add `i` to the `ArrayList` associated with `nums[i]`.
2.  **Query Processing:**
    *   Initialize an empty `ArrayList` to store the results.
    *   For each `query` in `queries`:
        *   Get the value `val = nums[query]`.
        *   Retrieve the `ArrayList` of indices for `val` from the `HashMap`.
        *   If the `ArrayList` has fewer than 2 elements, it means `val` appears only once (or not at all, though the problem implies it exists), so there's no other equal element. Add `-1` to the results.
        *   If there are 2 or more occurrences:
            *   Use `Collections.binarySearch` to find the index (`idx`) of the current `query` within the `ArrayList` of indices for `val`. `binarySearch` returns the index of the element if found, or `-(insertion point) - 1` if not found. Since `query` is guaranteed to be an index where `val` exists, `binarySearch` will return a non-negative index.
            *   Calculate the minimum distance to the left neighbor and the right neighbor.
            *   The left neighbor's index in the `ArrayList` is `idx - 1`. If `idx` is 0, the left neighbor wraps around to the last element in the `ArrayList`.
            *   The right neighbor's index in the `ArrayList` is `idx + 1`. If `idx` is the last element's index, the right neighbor wraps around to the first element in the `ArrayList`.
            *   The `getAns` helper function calculates the distance to a given neighbor index. It handles wrap-around logic for neighbors and calculates the minimum distance considering the array's circularity (`min(abs(diff), n - abs(diff))`).
            *   The minimum of the distances to the left and right neighbors is the answer for this query. Add this minimum distance to the results.
3.  **Return Results:** Return the `ArrayList` containing the answers for all queries.

## Concept to Remember
*   **Hash Maps for Indexing:** Efficiently storing and retrieving lists of indices for each unique element.
*   **Binary Search:** Quickly finding the position of an element in a sorted list.
*   **Circular Array Distance:** Calculating distances in a circular manner, considering wrap-around.
*   **Pre-computation:** Optimizing query time by performing expensive operations (like building the index map) once.

## Common Mistakes
*   **Not handling wrap-around for neighbors:** Forgetting that the closest element might be at the beginning of the index list when the current element is at the end, or vice-versa.
*   **Incorrectly calculating circular distance:** Misunderstanding how to compute the shortest distance in a circular array (e.g., `n - abs(diff)` part).
*   **Inefficient neighbor search:** Iterating linearly through the index list instead of using binary search after finding the element's position.
*   **Off-by-one errors in `binarySearch` results:** Misinterpreting the return value of `Collections.binarySearch` when an element is not found (though not an issue here as the query index is guaranteed to be present).
*   **Not handling cases with only one occurrence:** Failing to return -1 when an element appears only once.

## Complexity Analysis
*   **Time:** O(N + Q log N) - reason: O(N) to build the HashMap. For each of the Q queries, we perform a binary search on an ArrayList of indices. In the worst case, an element might appear N times, leading to O(log N) for binary search. The `getAns` function takes O(1).
*   **Space:** O(N) - reason: The HashMap stores all indices of the array, which in the worst case (all elements unique or all same) can take up to O(N) space. The result list also takes O(Q) space.

## Commented Code
```java
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        // HashMap to store each number and a list of its indices in the array.
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
        // Get the length of the input array.
        int n = nums.length;
        // Iterate through the input array to populate the HashMap.
        for (int i = 0; i < n; i++) {
            // Get the list of indices for the current number, or create a new list if it doesn't exist.
            ArrayList<Integer> arr = hm.getOrDefault(nums[i], new ArrayList<>());
            // Add the current index to the list for this number.
            arr.add(i);
            // Put the updated list back into the HashMap.
            hm.put(nums[i], arr);
        }
        // List to store the results for each query.
        List<Integer> ans = new ArrayList<>();
        // Iterate through each query.
        for (int query : queries) {
            // Get the value of the element at the current query index.
            int val = nums[query];
            // Get the list of indices where this value appears. Use getOrDefault to handle cases where val might not be in hm (though problem constraints usually prevent this if query is valid).
            ArrayList<Integer> indices = hm.getOrDefault(val, new ArrayList<>());
            // If the value appears less than twice, there's no other equal element.
            if (indices.size() < 2) {
                // Add -1 to the answer list, indicating no other equal element.
                ans.add(-1);
            } else {
                // If there are multiple occurrences, find the closest one.
                // Use binary search to find the index of the current 'query' within the 'indices' list.
                // 'index' will be the position of 'query' in the sorted 'indices' list.
                int index = Collections.binarySearch(indices, query);
                // Calculate the minimum distance to the closest equal element.
                // We need to check the element to the left (index-1) and to the right (index+1) in the 'indices' list.
                // The getAns function handles wrap-around logic and circular distance calculation.
                // We take the minimum of the distance to the left neighbor and the right neighbor.
                int minDist = Math.min(getAns(query, index - 1, indices, n), getAns(query, index + 1, indices, n));
                // Add the calculated minimum distance to the answer list.
                ans.add(minDist);
            }
        }
        // Return the list of answers for all queries.
        return ans;
    }

    // Helper function to calculate the minimum circular distance to a neighbor index.
    public int getAns(int query, int neigh, ArrayList<Integer> arr, int n) {
        // Handle wrap-around for the neighbor index if it's out of bounds.
        // If neigh is -1 (meaning we are checking the element before the first one), wrap around to the last element in the list.
        if (neigh == -1) neigh = arr.size() - 1;
        // If neigh is arr.size() (meaning we are checking the element after the last one), wrap around to the first element in the list.
        if (neigh == arr.size()) neigh = 0;

        // Get the actual index in the original 'nums' array for the neighbor.
        int neighborIndex = arr.get(neigh);
        // Calculate the absolute difference between the query index and the neighbor's index.
        int d = Math.abs(query - neighborIndex);
        // The minimum distance in a circular array is either the direct difference or the difference going the other way around.
        // n - d represents the distance going the other way around the circle.
        return Math.min(d, n - d);
    }
}
```

## Interview Tips
*   **Explain the HashMap pre-processing:** Clearly articulate why a HashMap is used and how it speeds up lookups.
*   **Detail the binary search step:** Explain how `Collections.binarySearch` works and why it's efficient for finding the neighbor's position in the sorted index list.
*   **Emphasize circular distance calculation:** Be prepared to explain the `Math.min(d, n - d)` logic and why it's crucial for this problem.
*   **Discuss edge cases:** Mention handling queries where the element appears only once, and how wrap-around logic in `getAns` addresses boundary conditions.

## Revision Checklist
- [ ] Understand the problem statement: find closest equal element's distance.
- [ ] Pre-processing: use HashMap to store indices of each number.
- [ ] Query processing: retrieve indices for the queried number.
- [ ] Handle cases with < 2 occurrences (return -1).
- [ ] Use `Collections.binarySearch` to find the query's position in the index list.
- [ ] Implement `getAns` helper for neighbor distance calculation.
- [ ] Correctly handle neighbor wrap-around (leftmost/rightmost in index list).
- [ ] Correctly calculate circular distance (`min(d, n-d)`).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find All Numbers Disappeared in an Array
*   Find All Duplicates in an Array
*   Contains Duplicate II
*   Shortest Distance to Target String in a Circular Array

## Tags
`Array` `HashMap` `Binary Search` `Two Pointers` `Math`
