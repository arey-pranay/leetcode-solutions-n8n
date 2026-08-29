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
The problem asks to rearrange an array such that it becomes lexicographically smallest, with a constraint on swapping elements. Elements can only be swapped if their absolute difference is less than or equal to a given limit.
We solve this by identifying groups of swappable elements, sorting each group independently, and then placing them back into the original array's positions.

## Intuition
The core idea is that if two numbers `a` and `b` can be swapped (i.e., `abs(a - b) <= limit`), and `b` and `c` can be swapped (`abs(b - c) <= limit`), then `a`, `b`, and `c` are all part of the same "swappable" group. This means any element within such a connected group can be moved to any position occupied by another element in the same group. To achieve the lexicographically smallest array, we should sort these swappable elements within their respective groups and place them back in the order they appear in the original array.

## Algorithm
1. Create a copy of the input array `nums` and sort it to get `sorted`. This `sorted` array will help us identify potential groups of swappable elements.
2. Initialize a list of lists called `groups` to store the elements belonging to each swappable group.
3. Initialize a hash map `hm` to store the group ID for each number in the `sorted` array.
4. Iterate through the `sorted` array. For each element `sorted[i]`:
    a. If it's the first element or the absolute difference between `sorted[i]` and `sorted[i-1]` is greater than `limit`, it signifies the start of a new group. Increment `groupID`.
    b. Add `sorted[i]` to the current group in `groups`.
    c. Store the `groupID` of `sorted[i]` in the `hm`.
5. Initialize an array `indices` of the same size as the number of groups. This array will keep track of the next available index to pick from each group.
6. Iterate through the original `nums` array. For each element `nums[i]`:
    a. Get the `groupID` of `nums[i]` from the `hm`.
    b. Get the list of members for this `groupID` from `groups`.
    c. Replace `nums[i]` with the element at `members.get(indices[groupID])`.
    d. Increment `indices[groupID]` to point to the next element in that group for future assignments.
7. Return the modified `nums` array.

## Concept to Remember
*   **Lexicographical Order:** Understanding how to minimize an array by placing smaller elements at earlier indices.
*   **Disjoint Set Union (Implicit):** The problem implicitly forms connected components (groups) where elements can be swapped. While not explicitly using a DSU data structure, the logic of grouping based on a condition is similar.
*   **Greedy Approach:** Within each identified swappable group, sorting the elements and placing them back greedily leads to the overall lexicographically smallest array.

## Common Mistakes
*   **Incorrect Grouping Logic:** Failing to correctly identify the boundaries of swappable groups, especially when the difference is exactly `limit`.
*   **Modifying Original Array Prematurely:** Trying to swap elements directly in the original array without first identifying all swappable groups can lead to incorrect results.
*   **Index Management:** Errors in tracking which element from each group has already been placed back into the `nums` array.
*   **Handling Duplicates:** Not considering how duplicate numbers might affect group assignments or index tracking.

## Complexity Analysis
*   **Time:** O(N log N) - The dominant factor is sorting the array initially (O(N log N)). The subsequent grouping and re-assignment loops are O(N).
*   **Space:** O(N) - For storing the sorted copy of the array, the `groups` list (which can store up to N elements in total), and the `hm` hash map.

## Commented Code
```java
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length; // Get the length of the input array.
        int[] sorted = nums.clone(); // Create a copy of the original array to sort it.
        Arrays.sort(sorted); // Sort the copied array. This helps in identifying groups of numbers that can be swapped.

        List<List<Integer>> groups = new ArrayList<>(); // Initialize a list of lists to store elements belonging to each swappable group.
        HashMap<Integer, Integer> hm = new HashMap<>(); // Initialize a hash map to store the group ID for each number. This allows quick lookup of a number's group.
        int groupID = -1; // Initialize the group ID counter. It starts at -1 and increments for each new group.

        // Iterate through the sorted array to identify and populate the groups.
        for (int i = 0; i < n; i++) {
            // If it's the first element or the difference between the current and previous sorted element is greater than the limit,
            // it means we start a new group.
            if (i == 0 || Math.abs(sorted[i] - sorted[i - 1]) > limit) {
                groups.add(new ArrayList<>()); // Add a new empty list to 'groups' for the new group.
                groupID++; // Increment the group ID.
            }
            groups.get(groupID).add(sorted[i]); // Add the current sorted number to the current group.
            hm.put(sorted[i], groupID); // Map the current sorted number to its group ID in the hash map.
        }

        int[] indices = new int[groups.size()]; // Initialize an array to keep track of the next available index to pick from each group.
                                                // The size is the total number of groups found.

        // Iterate through the original 'nums' array to place the sorted elements back.
        for (int i = 0; i < n; i++) {
            groupID = hm.get(nums[i]); // Get the group ID for the current element in the original array.
            List<Integer> members = groups.get(groupID); // Get the list of all members belonging to this group.
            // Replace the current element in 'nums' with the next available sorted element from its group.
            // 'indices[groupID]++' ensures we pick the next element from the group for subsequent occurrences of numbers from the same group.
            nums[i] = members.get(indices[groupID]++);
        }

        return nums; // Return the modified array, which is now the lexicographically smallest possible.
    }
}
```

## Interview Tips
*   **Explain the Grouping:** Clearly articulate why sorting first and then checking differences helps identify swappable elements. Emphasize that if `a` can swap with `b`, and `b` with `c`, then `a`, `b`, and `c` are in the same swappable set.
*   **Data Structures:** Justify the use of `ArrayList<List<Integer>>` for groups and `HashMap` for quick group lookup. Discuss alternatives if asked.
*   **Edge Cases:** Consider cases with an empty array, an array with one element, or when `limit` is very large (all elements can be swapped).

## Revision Checklist
- [ ] Understand the definition of lexicographically smallest.
- [ ] Identify the condition for swappable elements.
- [ ] Realize that swappability is transitive, forming groups.
- [ ] Plan to sort elements within each group.
- [ ] Implement the grouping logic correctly using sorted array and limit.
- [ ] Use a map to efficiently find the group of an original element.
- [ ] Manage indices for picking elements from each group.
- [ ] Verify time and space complexity.

## Similar Problems
*   [1202. Smallest String With Swaps](https://leetcode.com/problems/smallest-string-with-swaps/)
*   [1319. Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) (concept of connected components)

## Tags
`Array` `Sorting` `HashMap` `Greedy`
