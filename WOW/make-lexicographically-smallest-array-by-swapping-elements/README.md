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
The problem asks to rearrange an array such that it becomes lexicographically smallest, with a constraint on swaps. Elements can only be swapped if their absolute difference is less than or equal to a given limit.
The solution involves grouping elements based on the swap limit and then sorting these groups independently to achieve the lexicographically smallest arrangement.

## Intuition
To make an array lexicographically smallest, we want the smallest possible numbers at the beginning. The constraint is that we can only swap elements if their difference is within `limit`. This suggests that elements that are "close" to each other in value (within `limit`) can potentially be rearranged among themselves. If we sort the array, we can identify contiguous blocks of numbers where any number within a block can reach any position within that block's original indices, provided all numbers in the block are considered.

The key insight is that if we sort the original array, we can then identify "groups" of numbers. A new group starts whenever the difference between two consecutive sorted numbers exceeds `limit`. All numbers within such a group can be freely swapped amongst themselves. Therefore, to achieve the lexicographically smallest array, we should place the smallest numbers from each group into the original positions that were occupied by numbers belonging to that group.

## Algorithm
1.  **Create a sorted copy:** Make a copy of the input array `nums` and sort it. Let's call this `sorted`. This sorted array will help us identify potential groups.
2.  **Group elements:** Iterate through the `sorted` array.
    *   Maintain a list of lists, `groups`, where each inner list represents a group of numbers that can be swapped among themselves.
    *   Maintain a hash map, `hm`, to store the group ID for each number.
    *   Start a new group whenever the current element `sorted[i]` and the previous element `sorted[i-1]` have an absolute difference greater than `limit`, or at the beginning of the array.
    *   Add the current element `sorted[i]` to the current group and record its group ID in the hash map.
3.  **Populate the original array:** Iterate through the original `nums` array.
    *   For each element `nums[i]`, find its `groupID` using the hash map `hm`.
    *   Get the list of members for that `groupID` from `groups`.
    *   Use a separate array `indices` to keep track of how many elements from each group have already been placed. For the current `nums[i]`, take the element from the `members` list at `indices[groupID]`, increment `indices[groupID]`, and assign this value back to `nums[i]`. This effectively places the smallest available number from the group into the current position.
4.  **Return the modified `nums` array.**

## Concept to Remember
*   **Lexicographical Order:** Understanding how to minimize an array lexicographically by placing smaller elements at earlier indices.
*   **Disjoint Set Union (Implicit):** The grouping mechanism is similar to how DSU can merge sets of elements that are "connected" (swappable). Here, the connection is defined by the `limit`.
*   **Greedy Approach:** At each step, we are making the locally optimal choice (placing the smallest available number from a group) which leads to the globally optimal solution.
*   **Data Structures for Grouping:** Using lists and hash maps effectively to manage and access groups of elements.

## Common Mistakes
*   **Incorrect Grouping Logic:** Failing to correctly identify the boundaries of groups based on the `limit` in the sorted array.
*   **Not Handling Duplicates:** If the original array has duplicates, ensuring that the grouping and assignment logic correctly handles them. The current approach implicitly handles duplicates by sorting and then grouping.
*   **Modifying Original Array During Grouping:** Attempting to group elements while modifying the original `nums` array, which can lead to incorrect group assignments.
*   **Inefficient Index Tracking:** Not using a separate `indices` array to track the next available element from each group, leading to repeated elements or out-of-bounds errors.

## Complexity Analysis
*   **Time:** O(N log N) - The dominant factor is sorting the array (`Arrays.sort(sorted)`), which takes O(N log N). The subsequent iterations through the sorted array and the original array take O(N) time. Hash map operations (put and get) are O(1) on average.
*   **Space:** O(N) - We create a copy of the array (`sorted`), a list of lists (`groups`) which can store up to N elements in total, and a hash map (`hm`) which can store up to N key-value pairs. The `indices` array takes O(G) space where G is the number of groups, which is at most N.

## Commented Code
```java
import java.util.ArrayList; // Import ArrayList for dynamic lists
import java.util.Arrays;    // Import Arrays for sorting and cloning
import java.util.HashMap;   // Import HashMap for efficient key-value lookups
import java.util.List;      // Import List interface

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length; // Get the length of the input array
        int[] sorted = nums.clone(); // Create a clone of the original array to sort it without modifying nums yet
        Arrays.sort(sorted); // Sort the cloned array to identify potential groups of swappable elements

        List<List<Integer>> groups = new ArrayList<>(); // Initialize a list of lists to store the groups of numbers
        HashMap<Integer, Integer> hm = new HashMap<>(); // Initialize a hash map to store the group ID for each number
        int groupID = -1; // Initialize groupID counter, starting from -1 to correctly assign the first group ID as 0

        // Iterate through the sorted array to form groups
        for (int i = 0; i < n; i++) {
            // Check if a new group needs to be created:
            // - If it's the first element (i == 0)
            // - Or if the absolute difference between the current and previous sorted element is greater than the limit
            if (i == 0 || Math.abs(sorted[i] - sorted[i - 1]) > limit) {
                groups.add(new ArrayList<>()); // Add a new empty list to 'groups' for the new group
                groupID++; // Increment groupID to assign a unique ID to this new group
            }
            groups.get(groupID).add(sorted[i]); // Add the current sorted number to the current group
            hm.put(sorted[i], groupID); // Map the current sorted number to its assigned groupID in the hash map
        }

        // This array keeps track of how many elements from each group have already been placed in the result array.
        // The size of this array is the number of groups found.
        int[] indices = new int[groups.size()];

        // Iterate through the original 'nums' array to place the smallest possible elements from each group
        for (int i = 0; i < n; i++) {
            // Get the groupID for the current element nums[i] from the hash map
            groupID = hm.get(nums[i]);
            // Get the list of all members belonging to this groupID
            List<Integer> members = groups.get(groupID);
            // Place the next available smallest element from the 'members' list into the current position nums[i].
            // 'indices[groupID]++' ensures we pick the next element from the group in subsequent iterations.
            nums[i] = members.get(indices[groupID]++);
        }

        return nums; // Return the modified array, which is now lexicographically smallest under the given constraints
    }
}
```

## Interview Tips
*   **Explain the Grouping:** Clearly articulate why sorting and then grouping based on the `limit` is the correct approach. Emphasize that elements within a group can be freely permuted.
*   **Trace an Example:** Walk through a small example array and `limit` to demonstrate how groups are formed and how elements are placed back into the original array.
*   **Discuss Edge Cases:** Consider what happens if `limit` is very large (all elements can be swapped) or very small (no elements can be swapped).
*   **Clarify Space/Time:** Be prepared to explain the complexity analysis and justify the use of data structures like `HashMap` and `ArrayList`.

## Revision Checklist
- [ ] Understand the definition of lexicographical order.
- [ ] Identify the constraint on swaps and its implication on element rearrangement.
- [ ] Recognize that elements within a certain "value range" (defined by `limit`) can be treated as a swappable set.
- [ ] Implement the sorting and grouping logic correctly.
- [ ] Use a hash map to efficiently map original values to their group IDs.
- [ ] Use an auxiliary array to track the next available element from each group during the reconstruction phase.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [1675. Minimize Deviation in Array](https://leetcode.com/problems/minimize-deviation-in-array/) (Similar concept of transforming elements to minimize a metric)
*   [1202. Smallest String With Swaps](https://leetcode.com/problems/smallest-string-with-swaps/) (Uses Union-Find to group swappable characters)
*   [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/) (Involves merging sorted sequences)

## Tags
`Array` `Sorting` `HashMap` `Greedy`
