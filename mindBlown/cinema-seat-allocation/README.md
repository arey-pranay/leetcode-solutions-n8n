# Cinema Seat Allocation

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Greedy` `Bit Manipulation`  
**Time:** O(R + N)  
**Space:** O(R)

---

## Solution (java)

```java
class Solution {
    
    // best solution for no OLE and TLE. 
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
        for(int[] pair : reservedSeats) map.computeIfAbsent((pair[0]), k -> new HashSet<>()).add(pair[1]);
        int groups = 2*n;
        for(int i : map.keySet()){
            HashSet<Integer> curr = map.get(i);
            
            boolean one = !(curr.contains(2) || curr.contains(3) || curr.contains(4) || curr.contains(5));
            boolean two = !(curr.contains(4) || curr.contains(5) || curr.contains(6) || curr.contains(7)); 
            boolean three =!(curr.contains(6) || curr.contains(7) || curr.contains(8) || curr.contains(9)); 
            int count = 0;
            if(one && three) count= 2;
            else if ( one || two || three) count = 1;
             
            groups = groups-(2-count);
        }
        return groups;
    }
}
```

---

---
## Quick Revision
The problem asks to maximize the number of families that can be seated in a cinema with `n` rows, given a list of reserved seats.
We solve this by iterating through each row, checking available seat configurations, and subtracting unallocatable families from the initial maximum.

## Intuition
The core idea is that each row can potentially seat two families. A family needs a contiguous block of 4 seats. The available blocks are seats 2-5, 4-7, and 6-9. If we can fit two families in a row (e.g., using 2-5 and 6-9), we maximize the allocation for that row. If only one family can fit, we allocate one. If no family can fit, we allocate zero. Since we are given reserved seats, we need an efficient way to check the availability of seats for each row without iterating through all `n` rows if most are empty. A HashMap is ideal for this, mapping row numbers to their reserved seats.

## Algorithm
1. Initialize `groups` to `2 * n`, representing the maximum possible families if all seats were available.
2. Create a `HashMap<Integer, HashSet<Integer>>` called `map` to store reserved seats. The key will be the row number, and the value will be a `HashSet` of reserved seat numbers in that row.
3. Iterate through the `reservedSeats` array. For each `pair` (row, seat):
    a. Use `map.computeIfAbsent(pair[0], k -> new HashSet<>()).add(pair[1])` to add the reserved seat to the corresponding row's set. If the row is not yet in the map, a new `HashSet` is created.
4. Iterate through the keys (row numbers) of the `map`. For each row `i`:
    a. Get the `HashSet<Integer>` of reserved seats for the current row: `HashSet<Integer> curr = map.get(i)`.
    b. Check for the three possible family seating configurations:
        i. `one`: Seats 2-5 are available. This is true if seats 2, 3, 4, and 5 are NOT in `curr`.
        ii. `two`: Seats 4-7 are available. This is true if seats 4, 5, 6, and 7 are NOT in `curr`.
        iii. `three`: Seats 6-9 are available. This is true if seats 6, 7, 8, and 9 are NOT in `curr`.
    c. Determine how many families can be seated in this row:
        i. If `one` AND `three` are true, `count = 2` (two families can be seated in the outer blocks).
        ii. Else if `one` OR `two` OR `three` is true, `count = 1` (at least one family can be seated).
        iii. Otherwise, `count = 0`.
    d. Update `groups`: Subtract `(2 - count)` from `groups`. This accounts for the families that *could not* be seated in this row due to reservations. If `count` is 2, we subtract `2-2=0`. If `count` is 1, we subtract `2-1=1`. If `count` is 0, we subtract `2-0=2`.
5. Return the final `groups` count.

## Concept to Remember
*   **Hash Map for Sparse Data:** Efficiently storing and retrieving data for rows that actually have reservations, avoiding unnecessary iteration over empty rows.
*   **Set for Efficient Lookups:** Using `HashSet` to quickly check for the presence of reserved seats within a row (O(1) average time complexity).
*   **Greedy Approach:** Maximizing seat allocation per row by prioritizing configurations that allow two families.
*   **Bit Manipulation (Alternative):** Representing seat availability for a row using bits can be an alternative, potentially more compact, way to check configurations.

## Common Mistakes
*   **Inefficiently checking seat availability:** Iterating through all 10 seats for every row, even if most are empty, leading to TLE.
*   **Incorrectly calculating families per row:** Misinterpreting the overlapping nature of seat blocks (e.g., 2-5, 4-7, 6-9) and not correctly identifying when two families can fit.
*   **Off-by-one errors in seat numbers:** Using 1-based indexing for seats when the problem implies 1-based but the code might use 0-based or vice-versa.
*   **Not handling edge cases:** Forgetting to initialize `groups` correctly or not considering rows with no reservations.

## Complexity Analysis
- Time: O(R + N), where R is the number of reserved seats and N is the number of rows.
    - O(R) to populate the HashMap with reserved seats.
    - O(N) in the worst case to iterate through the keys of the HashMap (if all N rows have at least one reservation). Each check within the loop is O(1) due to HashSet lookups.
- Space: O(R), where R is the number of reserved seats.
    - The HashMap stores at most R entries (one for each reserved seat, though grouped by row). In the worst case, if every seat is reserved, it could be O(N * 10). However, since we only store rows with reservations, it's bounded by the number of unique rows with reservations, which is at most N, and the total number of reserved seats R.

## Commented Code
```java
class Solution {
    
    // The goal is to maximize the number of families that can be seated.
    // Each family needs a block of 4 seats.
    // The cinema has 'n' rows, and 'reservedSeats' indicates occupied seats.
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Use a HashMap to store reserved seats.
        // Key: row number (Integer)
        // Value: a HashSet of reserved seat numbers in that row (HashSet<Integer>)
        // This is efficient because most rows might be empty, so we only store data for rows with reservations.
        HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
        
        // Iterate through each reserved seat entry.
        for(int[] pair : reservedSeats) {
            // 'pair[0]' is the row number, 'pair[1]' is the seat number.
            // computeIfAbsent: if the row 'pair[0]' is not yet a key in the map,
            // create a new HashSet for it. Then, add the seat number 'pair[1]' to the HashSet for that row.
            map.computeIfAbsent((pair[0]), k -> new HashSet<>()).add(pair[1]);
        }
        
        // Initialize 'groups' to the maximum possible number of families.
        // Each row can potentially seat 2 families (if no seats are reserved).
        int groups = 2 * n;
        
        // Iterate through each row that has at least one reserved seat.
        // We only need to check rows with reservations because empty rows are guaranteed to seat 2 families.
        for(int i : map.keySet()){
            // Get the set of reserved seats for the current row 'i'.
            HashSet<Integer> curr = map.get(i);
            
            // Check for the three possible contiguous 4-seat blocks for families:
            // Block 1: Seats 2, 3, 4, 5
            // Block 2: Seats 4, 5, 6, 7
            // Block 3: Seats 6, 7, 8, 9
            
            // 'one' checks if the left block (seats 2-5) is available.
            // It's available if none of seats 2, 3, 4, 5 are in the 'curr' set.
            boolean one = !(curr.contains(2) || curr.contains(3) || curr.contains(4) || curr.contains(5));
            
            // 'two' checks if the middle block (seats 4-7) is available.
            // It's available if none of seats 4, 5, 6, 7 are in the 'curr' set.
            boolean two = !(curr.contains(4) || curr.contains(5) || curr.contains(6) || curr.contains(7)); 
            
            // 'three' checks if the right block (seats 6-9) is available.
            // It's available if none of seats 6, 7, 8, 9 are in the 'curr' set.
            boolean three =!(curr.contains(6) || curr.contains(7) || curr.contains(8) || curr.contains(9)); 
            
            // 'count' will store how many families can be seated in this row.
            int count = 0;
            
            // If both the left block (2-5) and the right block (6-9) are available,
            // we can seat 2 families in this row.
            if(one && three) {
                count = 2;
            } 
            // Else, if at least one of the three blocks (left, middle, or right) is available,
            // we can seat 1 family.
            else if ( one || two || three) {
                count = 1;
            }
            // If none of the blocks are available, count remains 0.
             
            // We initially assumed 2 families per row.
            // 'groups' is reduced by the number of families that *cannot* be seated in this row.
            // If count is 2, we subtract (2-2) = 0.
            // If count is 1, we subtract (2-1) = 1.
            // If count is 0, we subtract (2-0) = 2.
            groups = groups - (2 - count);
        }
        
        // Return the total number of families that can be seated.
        return groups;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the range of `n` and the number of `reservedSeats`. This helps in choosing the right data structures.
*   **Explain the HashMap Choice:** Justify using a HashMap for sparse data (only storing rows with reservations) to avoid TLE on large `n`.
*   **Walk Through Seat Logic:** Clearly explain the three possible 4-seat blocks (2-5, 4-7, 6-9) and how they can overlap or be used independently to seat one or two families.
*   **Consider Edge Cases:** Discuss what happens if `reservedSeats` is empty, or if a row has all seats reserved.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the three possible 4-seat family blocks.
- [ ] Recognize that a row can seat at most two families.
- [ ] Choose an efficient data structure (HashMap) for sparse reservations.
- [ ] Implement the logic to check availability for each block.
- [ ] Correctly calculate families seated per row (0, 1, or 2).
- [ ] Update the total count by subtracting unseated families.
- [ ] Analyze time and space complexity.

## Similar Problems
*   1386. Cinema Seat Allocation (This is the same problem)
*   1282. Users Access to a Company in Groups
*   720. Longest Word in Dictionary
*   1477. Find Two Non-overlapping Subarrays Each With Target Sum

## Tags
`Array` `Hash Map` `Greedy`

## My Notes
// best solution for no OLE and TLE.
