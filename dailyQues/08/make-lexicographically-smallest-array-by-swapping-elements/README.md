# Make Lexicographically Smallest Array By Swapping Elements

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Union-Find` `Sorting`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        List<List<Integer>> groups = new ArrayList<>(); // grouping elements which have less than equal to k
        HashMap<Integer,Integer> hm =  new HashMap<>(); // groupID of every element
        int groupID = -1;
        for(int i=0;i<n;i++){
          if(i==0 || Math.abs(sorted[i]-sorted[i-1]) > limit){//creating a new group and updating the latest running groupID
            groups.add(new ArrayList<>());
            groupID++;
          }
          groups.get(groupID).add(sorted[i]);//add this number to current group
          hm.put(sorted[i],groupID);//mark the ID of this number in hashmap
        }
        int[] indices = new int[groups.size()]; // hum har ek group ke kitne indices already cover kr chuke hai
        for(int i=0;i<n;i++){
          groupID = hm.get(nums[i]); // current element ka group
          List<Integer> members = groups.get(groupID); // us group ke saare members
          nums[i] = members.get(indices[groupID]++); // un members me se jo latest index humne cover nhi kara hai
        }
        return nums;
    }
}
```

---

---
## Quick Revision
The problem asks to rearrange an array to be lexicographically smallest by swapping elements within certain constraints.
We solve this by grouping elements that can be swapped and then sorting these groups independently.

## Intuition
The core idea is that if two numbers `a` and `b` can be swapped, it means `abs(a - b) <= limit`. This implies that any number `x` can be swapped with any other number `y` if there's a chain of intermediate numbers `z1, z2, ..., zk` such that `abs(x - z1) <= limit`, `abs(z1 - z2) <= limit`, ..., `abs(zk - y) <= limit`. This forms connected components or "groups" of numbers that can be freely rearranged among themselves. To make the array lexicographically smallest, within each such group, we should place the smallest available numbers at the earliest possible indices.

## Algorithm
1. Create a copy of the input array `nums` and sort it to get `sorted`.
2. Initialize a list of lists `groups` to store the numbers belonging to each swappable group.
3. Initialize a hash map `hm` to store the group ID for each number in the `sorted` array.
4. Iterate through the `sorted` array. If the current element is the first element or the absolute difference between the current element and the previous element is greater than `limit`, start a new group.
5. Add the current element to the current group and store its group ID in the hash map.
6. Initialize an array `indices` to keep track of the next available element to pick from each group.
7. Iterate through the original `nums` array. For each element `nums[i]`:
    a. Get its `groupID` from the hash map.
    b. Get the list of `members` for that group.
    c. Assign `nums[i]` the element from `members` at the index `indices[groupID]`, and then increment `indices[groupID]`.
8. Return the modified `nums` array.

## Concept to Remember
*   **Lexicographical Order:** Understanding how to compare arrays based on element-by-element comparison from left to right.
*   **Connected Components/Disjoint Set Union (Implicit):** The problem implicitly defines groups of elements that can be swapped. If `a` can swap with `b` and `b` with `c`, then `a` can effectively swap with `c` (transitively). This is similar to finding connected components.
*   **Greedy Approach:** Within each group of swappable elements, placing the smallest elements at the earliest indices is the optimal strategy for lexicographical minimality.

## Common Mistakes
*   **Incorrectly defining groups:** Not realizing that the "swappable" property is transitive and that groups are formed based on differences within the *sorted* array.
*   **Modifying the original array while iterating:** This can lead to incorrect group assignments or element placements.
*   **Not handling edge cases:** Forgetting to initialize groups or handle the first element correctly.
*   **Inefficient group tracking:** Using a less efficient data structure than a hash map to quickly find the group of an element.

## Complexity Analysis
*   Time: O(N log N) - The dominant factor is sorting the array. The subsequent iterations and hash map operations are O(N).
*   Space: O(N) - For storing the sorted array, the groups, and the hash map.

## Commented Code
```java
import java.util.ArrayList; // Import ArrayList for dynamic lists
import java.util.Arrays;    // Import Arrays for array operations like sorting and cloning
import java.util.HashMap;   // Import HashMap for efficient key-value lookups
import java.util.List;      // Import List interface

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length; // Get the length of the input array
        int[] sorted = nums.clone(); // Create a clone of the original array to sort it without modifying the original yet
        Arrays.sort(sorted); // Sort the cloned array to identify potential groups based on value differences

        List<List<Integer>> groups = new ArrayList<>(); // Initialize a list of lists to store elements belonging to each swappable group
        HashMap<Integer, Integer> hm = new HashMap<>(); // Initialize a hash map to store the group ID for each number in the sorted array
        int groupID = -1; // Initialize groupID counter, starting from -1 so the first group gets ID 0

        // Iterate through the sorted array to form groups
        for (int i = 0; i < n; i++) {
            // If it's the first element or the difference with the previous element is greater than the limit, start a new group
            if (i == 0 || Math.abs(sorted[i] - sorted[i - 1]) > limit) {
                groups.add(new ArrayList<>()); // Add a new empty list to 'groups' for the new group
                groupID++; // Increment the groupID for the new group
            }
            groups.get(groupID).add(sorted[i]); // Add the current element from the sorted array to the current group
            hm.put(sorted[i], groupID); // Map the current element to its assigned groupID in the hash map
        }

        int[] indices = new int[groups.size()]; // Initialize an array to keep track of the next index to pick from each group
        // This array will help us pick elements from each group in their sorted order

        // Iterate through the original array to place elements into their final positions
        for (int i = 0; i < n; i++) {
            groupID = hm.get(nums[i]); // Get the groupID of the current element from the original array using the hash map
            List<Integer> members = groups.get(groupID); // Get the list of all members belonging to this group
            // Assign the current position in the original array with the next available smallest element from its group
            nums[i] = members.get(indices[groupID]++); // Pick the element at 'indices[groupID]' from the 'members' list and then increment 'indices[groupID]' for the next pick from this group
        }

        return nums; // Return the modified array, which is now the lexicographically smallest possible
    }
}
```

## Interview Tips
*   **Explain the grouping logic clearly:** Emphasize that elements can be swapped if they are "connected" through a chain of differences within the `limit`. The sorted array helps identify these initial connections.
*   **Walk through an example:** Use a small example array and `limit` to demonstrate how groups are formed and how elements are placed back.
*   **Discuss the "why" behind sorting:** Explain that sorting is crucial for identifying contiguous blocks of numbers that can be swapped.
*   **Clarify the role of the hash map and indices array:** Explain how they efficiently map original numbers to their groups and track which element to pick next from each group.

## Revision Checklist
- [ ] Understand the definition of lexicographical order.
- [ ] Recognize that swappable elements form groups.
- [ ] Understand how to identify these groups using the sorted array and the `limit`.
- [ ] Implement the grouping logic correctly.
- [ ] Implement the logic to place elements back into the original array using the identified groups.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [807. Max Increase to Keep City Skyline](https://leetcode.com/problems/max-increase-to-keep-city-skyline/) (Conceptually similar in grouping/constraints)
*   [1202. Smallest String With Swaps](https://leetcode.com/problems/smallest-string-with-swaps/) (Directly related to connected components and sorting within groups)
*   [133. Clone Graph](https://leetcode.com/problems/clone-graph/) (Graph traversal, finding connected components)

## Tags
`Array` `Hash Map` `Sorting` `Greedy`
