# Rank Transform Of An Array

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Hash Map` `Sorting`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
// class Solution {
//     public int[] arrayRankTransform(int[] arr) {
//         HashMap<Integer,ArrayList<Integer>> indices = new HashMap<>();
//         TreeSet<Integer> ts = new TreeSet<>();
//         for(int i=0;i<arr.length;i++){indices.computeIfAbsent(arr[i],(k)->new ArrayList<>()).add(i); ts.add(arr[i]);}
//         int j=1;
//         for(int num : ts){
//             for(int index : indices.get(num))  arr[index] = j;
//             j++;
//         }
//         return arr;
//     }
// }
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] temp = arr.clone();
        //[10,20,30,40]
        Arrays.sort(temp);
        HashMap<Integer,Integer> map = new HashMap<>();
         int rank = 1;
        for (int num : temp) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }
        int[] ans = new int[arr.length];
        for(int i=0;i<ans.length;i++){
            ans[i] = map.get(arr[i]);
        }
        return ans;
        
    }
}
```

---

---
## Quick Revision
Given an array of integers, replace each element with its rank.
Sort the array to determine ranks, then map original elements to their ranks.

## Intuition
The core idea is to assign ranks based on the sorted order of the elements. If we sort the array, the smallest unique element gets rank 1, the next smallest unique element gets rank 2, and so on. We need a way to map these ranks back to the original positions of the elements. A hash map is perfect for storing the mapping from a number to its rank.

## Algorithm
1. Create a copy of the input array `arr` to avoid modifying it directly during sorting.
2. Sort the copied array.
3. Initialize an empty hash map `map` to store the rank of each unique number.
4. Initialize a `rank` variable to 1.
5. Iterate through the sorted array:
    a. For each number, if it's not already in the `map`, add it to the `map` with the current `rank` and then increment `rank`. This ensures that duplicate numbers get the same rank.
6. Create a new array `ans` of the same size as the original `arr`.
7. Iterate through the original `arr`:
    a. For each element in `arr`, retrieve its rank from the `map` and store it in the corresponding position in `ans`.
8. Return the `ans` array.

## Concept to Remember
*   **Sorting:** Essential for establishing the order of elements to determine ranks.
*   **Hash Maps (Dictionaries):** Crucial for efficient lookups to map original values to their assigned ranks.
*   **Handling Duplicates:** Ensuring that identical numbers receive the same rank is a key aspect.
*   **In-place vs. Copying:** Understanding when to modify an array directly versus when to use a copy.

## Common Mistakes
*   Modifying the original array directly before mapping ranks back, leading to incorrect results.
*   Not handling duplicate numbers correctly, assigning different ranks to identical values.
*   Inefficiently searching for ranks instead of using a hash map for O(1) average time lookups.
*   Off-by-one errors in rank assignment (e.g., starting rank from 0 instead of 1).

## Complexity Analysis
- Time: O(N log N) - reason: Dominated by the sorting step. The hash map operations (insertion and lookup) take O(N) time in total on average.
- Space: O(N) - reason: For the hash map to store unique elements and their ranks, and for the temporary sorted array copy.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting and cloning
import java.util.HashMap; // Import the HashMap class for key-value mapping

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Create a copy of the original array to sort without altering the original order
        int[] temp = arr.clone();

        // Sort the temporary array in ascending order
        Arrays.sort(temp);

        // Create a HashMap to store the mapping from each unique number to its rank
        // Key: the number, Value: its rank
        HashMap<Integer, Integer> map = new HashMap<>();

        // Initialize the rank counter. Ranks start from 1.
        int rank = 1;

        // Iterate through the sorted temporary array
        for (int num : temp) {
            // Check if the current number has already been assigned a rank
            if (!map.containsKey(num)) {
                // If the number is unique (not in the map yet), assign it the current rank
                map.put(num, rank++); // Put the number and its rank into the map, then increment rank for the next unique number
            }
            // If the number is already in the map, it means it's a duplicate, and we do nothing,
            // as it should retain the rank already assigned to its first occurrence.
        }

        // Create the result array to store the transformed ranks
        int[] ans = new int[arr.length];

        // Iterate through the original array
        for (int i = 0; i < ans.length; i++) {
            // For each element in the original array, get its rank from the map
            // and store it in the corresponding position in the result array
            ans[i] = map.get(arr[i]);
        }

        // Return the array with elements replaced by their ranks
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain your approach of sorting first and then using a hash map for lookups.
*   Emphasize how you handle duplicate elements to ensure they receive the same rank.
*   Discuss the time and space complexity trade-offs of your solution.
*   Be prepared to walk through an example with duplicates to demonstrate your logic.

## Revision Checklist
- [ ] Understand the problem: replace elements with their rank.
- [ ] Identify the need for sorting to determine rank order.
- [ ] Recognize the utility of a hash map for efficient rank lookups.
- [ ] Implement sorting and hash map population correctly.
- [ ] Ensure duplicates are handled by assigning them the same rank.
- [ ] Map ranks back to the original array positions.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Rank Transform of an Array (LeetCode 1331) - This is the same problem.
*   Sort Array by Increasing Frequency (LeetCode 1636)
*   Relative Ranks (LeetCode 503)

## Tags
`Array` `Hash Map` `Sorting`
