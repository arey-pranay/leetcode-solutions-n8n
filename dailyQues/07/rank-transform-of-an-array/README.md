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
The core idea is to assign ranks based on the sorted order of elements. If two elements are the same, they should receive the same rank. The smallest unique element gets rank 1, the next smallest unique element gets rank 2, and so on. A hash map is a natural fit to store the mapping from the original number to its assigned rank.

## Algorithm
1. Create a copy of the input array `arr` to avoid modifying the original array during sorting.
2. Sort the copied array. This will arrange the numbers in ascending order, making it easy to determine their ranks.
3. Initialize a hash map `map` to store the mapping from each unique number to its rank.
4. Initialize a `rank` variable to 1.
5. Iterate through the sorted copied array. For each number:
    a. If the number is not already present in the `map`, add it to the `map` with the current `rank` and then increment `rank`. This ensures that duplicate numbers get the same rank.
6. Create a new array `ans` of the same size as the original `arr` to store the result.
7. Iterate through the original input array `arr`. For each element:
    a. Look up its rank in the `map` and assign it to the corresponding position in the `ans` array.
8. Return the `ans` array.

## Concept to Remember
*   **Sorting:** Essential for determining the order and thus the rank of elements.
*   **Hash Maps (Dictionaries):** Efficiently store and retrieve key-value pairs, ideal for mapping numbers to their ranks.
*   **Handling Duplicates:** Ensuring that identical numbers receive the same rank is crucial.
*   **In-place vs. Copy:** Understanding when to modify an array directly versus when to use a copy.

## Common Mistakes
*   Modifying the original array directly before creating a mapping, leading to loss of original element positions.
*   Not handling duplicate numbers correctly, assigning different ranks to identical values.
*   Incorrectly initializing the rank counter (e.g., starting from 0 instead of 1).
*   Inefficiently mapping ranks back to the original array, perhaps by re-sorting or searching repeatedly.

## Complexity Analysis
- Time: O(N log N) - reason: Dominated by the sorting step of the array. The subsequent traversals and hash map operations are O(N).
- Space: O(N) - reason: For storing the sorted copy of the array and the hash map, both of which can store up to N unique elements in the worst case.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting and cloning
import java.util.HashMap; // Import the HashMap class for mapping numbers to ranks

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Create a clone of the input array to sort without altering the original order
        int[] temp = arr.clone();
        // Sort the cloned array in ascending order to determine ranks
        Arrays.sort(temp);
        // Create a HashMap to store the mapping from each unique number to its rank
        HashMap<Integer, Integer> map = new HashMap<>();
        // Initialize the rank counter, starting from 1 for the smallest element
        int rank = 1;
        // Iterate through the sorted array to assign ranks to unique numbers
        for (int num : temp) {
            // If the current number has not been assigned a rank yet
            if (!map.containsKey(num)) {
                // Assign the current rank to this number and increment the rank for the next unique number
                map.put(num, rank++);
            }
        }
        // Create a new array to store the transformed ranks, with the same size as the original array
        int[] ans = new int[arr.length];
        // Iterate through the original input array to populate the result array with ranks
        for (int i = 0; i < ans.length; i++) {
            // Get the rank of the current element from the map and assign it to the result array
            ans[i] = map.get(arr[i]);
        }
        // Return the array with elements replaced by their ranks
        return ans;
    }
}
```

## Interview Tips
*   Clearly explain your approach of sorting first to establish ranks.
*   Emphasize how the hash map is used to efficiently map original values to their determined ranks.
*   Discuss the handling of duplicate values and why they should receive the same rank.
*   Be prepared to discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem: replace elements with their rank.
- [ ] Identify the need for sorting to determine rank order.
- [ ] Recognize the utility of a hash map for value-to-rank mapping.
- [ ] Implement sorting and iteration to build the rank map.
- [ ] Map ranks back to the original array structure.
- [ ] Consider edge cases like empty arrays or arrays with all duplicates.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Rank Scores
*   Relative Ranks
*   Sort Array By Parity
*   Height Checker

## Tags
`Array` `Hash Map` `Sorting`
