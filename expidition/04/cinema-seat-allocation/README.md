# Cinema Seat Allocation

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Greedy` `Bit Manipulation`  
**Time:** O(R + S)  
**Space:** O(R + S)

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
This problem asks to maximize the number of families that can be seated in a cinema given reserved seats.
We can solve this by iterating through each row and checking available seat combinations for families.

## Intuition
The core idea is that a family can occupy a block of 10 seats (from column 2 to 9) if no seats within their designated block are reserved. There are three possible blocks for a family: seats 2-5, seats 4-7, and seats 6-9. If a row has no reserved seats, we can fit two families. If some seats are reserved, we need to check which of these three blocks are still available.

## Algorithm
1. Initialize `groups` to `2 * n` (maximum possible families if all seats were available).
2. Create a `HashMap` where keys are row numbers and values are `HashSet`s of reserved seat numbers for that row.
3. Iterate through `reservedSeats`:
    a. For each `reservedSeat` pair `[row, seat]`, add `seat` to the `HashSet` associated with `row` in the `HashMap`. If the row doesn't exist, create a new `HashSet` for it.
4. Iterate through the keys (row numbers) of the `HashMap`:
    a. For each row, get the `HashSet` of reserved seats.
    b. Check for availability of three potential family seating blocks:
        i. Block 1: Seats 2, 3, 4, 5. Let `one` be true if none of these are reserved.
        ii. Block 2: Seats 4, 5, 6, 7. Let `two` be true if none of these are reserved.
        iii. Block 3: Seats 6, 7, 8, 9. Let `three` be true if none of these are reserved.
    c. Determine how many families can be seated in this row:
        i. If `one` and `three` are both true, 2 families can be seated.
        ii. Else if `one` or `two` or `three` is true, 1 family can be seated.
        iii. Otherwise, 0 families can be seated.
    d. Subtract the number of families that *could have been* seated (2) minus the number of families *actually seated* in this row from `groups`. This effectively accounts for the reduction in potential families due to reserved seats.
5. Return `groups`.

## Concept to Remember
*   **Hash Map for Efficient Lookups:** Using a HashMap to store reserved seats per row allows for O(1) average time complexity to check if a seat is reserved in a specific row.
*   **Set for Unique Elements:** A HashSet is used to store reserved seat numbers for a row, ensuring uniqueness and O(1) average time complexity for checking seat availability.
*   **Greedy Approach:** The problem can be solved greedily by maximizing families per row without affecting other rows, as family seating is independent across rows.

## Common Mistakes
*   **Inefficient Seat Checking:** Not using a HashMap or Set to store reserved seats, leading to O(N*M) checks per row where N is number of rows and M is number of reserved seats.
*   **Overlapping Block Logic:** Incorrectly defining or checking the three possible family blocks (2-5, 4-7, 6-9), especially the overlap at seats 4-7.
*   **Miscalculating Group Reduction:** Incorrectly updating the total `groups` count, for example, by subtracting 1 instead of `2 - count`.
*   **Handling Rows with No Reserved Seats:** Forgetting to account for rows that have no reserved seats, which can always accommodate 2 families. The initial `2*n` handles this, but the logic for rows with reservations must correctly adjust this.

## Complexity Analysis
- Time: O(R + S), where R is the number of rows (`n`) and S is the number of `reservedSeats`.
    - Building the HashMap takes O(S) time.
    - Iterating through the HashMap keys (at most R rows) and performing constant-time seat checks takes O(R) time.
- Space: O(R + S), where R is the number of rows and S is the number of `reservedSeats`.
    - The HashMap stores at most R keys, and each HashSet can store up to 9 seat numbers. In the worst case, if all seats are reserved, the space could be O(R * 9) which is O(R). However, if S is much larger than R, the space is dominated by storing the reserved seats, leading to O(S) in the worst case where each reserved seat is in a different row. A more precise bound is O(min(R, S) + S) if we consider that the number of unique rows with reservations is at most S, and each row stores at most 9 seats. The provided solution's space is effectively O(R) for the map keys and O(S) for the total number of reserved seats stored across all sets.

## Commented Code
```java
class Solution {
    
    // The problem asks to find the maximum number of families that can be seated in a cinema.
    // Each family needs a block of 10 seats (columns 2-9).
    // We are given the total number of rows (n) and a list of reserved seats.
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        
        // Use a HashMap to store reserved seats.
        // Key: row number (Integer)
        // Value: a HashSet of reserved seat numbers in that row (HashSet<Integer>)
        // This allows for efficient lookup of reserved seats for a specific row.
        HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
        
        // Iterate through each reserved seat entry.
        for(int[] pair : reservedSeats) {
            // pair[0] is the row number, pair[1] is the seat number.
            // computeIfAbsent is a concise way to get the HashSet for a row,
            // or create a new one if the row is not yet in the map.
            // Then, add the current reserved seat number to that row's HashSet.
            map.computeIfAbsent((pair[0]), k -> new HashSet<>()).add(pair[1]);
        }
        
        // Initialize the total number of possible family groups.
        // Each row can potentially seat 2 families (one in seats 2-5, another in 6-9).
        // So, for 'n' rows, the maximum possible is 2 * n.
        int groups = 2*n;
        
        // Iterate through each row that has at least one reserved seat.
        // Rows not present in the map have no reserved seats and are already accounted for in 'groups'.
        for(int i : map.keySet()){
            // Get the HashSet of reserved seats for the current row 'i'.
            HashSet<Integer> curr = map.get(i);
            
            // Check for the availability of the three possible family seating blocks:
            // Block 1: Seats 2, 3, 4, 5. 'one' is true if none of these are reserved.
            boolean one = !(curr.contains(2) || curr.contains(3) || curr.contains(4) || curr.contains(5));
            // Block 2: Seats 4, 5, 6, 7. 'two' is true if none of these are reserved.
            boolean two = !(curr.contains(4) || curr.contains(5) || curr.contains(6) || curr.contains(7)); 
            // Block 3: Seats 6, 7, 8, 9. 'three' is true if none of these are reserved.
            boolean three =!(curr.contains(6) || curr.contains(7) || curr.contains(8) || curr.contains(9)); 
            
            // Determine how many families can be seated in this row.
            int count = 0;
            // If both the left block (2-5) and the right block (6-9) are available,
            // we can seat 2 families.
            if(one && three) count= 2;
            // Else if at least one of the three blocks (2-5, 4-7, 6-9) is available,
            // we can seat 1 family.
            else if ( one || two || three) count = 1;
            // If none of the blocks are available, count remains 0.
             
            // Update the total number of groups.
            // For each row, we initially assumed 2 families could be seated.
            // 'count' is the actual number of families seated in this row.
            // So, the reduction in potential families for this row is (2 - count).
            // We subtract this reduction from the total 'groups'.
            groups = groups-(2-count);
        }
        // Return the final maximum number of families that can be seated.
        return groups;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the range of `n` and the number of `reservedSeats`. This helps in choosing the right data structures and algorithms.
*   **Explain the Three Blocks:** Clearly articulate the three possible 4-seat blocks (2-5, 4-7, 6-9) and why they are chosen. Emphasize the overlap.
*   **Walk Through an Example:** Use a small example with a few reserved seats to demonstrate how your algorithm processes a row and updates the total count.
*   **Discuss Edge Cases:** Consider rows with no reserved seats, rows with all seats reserved, and rows with only one or two reserved seats.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the three possible family seating blocks (2-5, 4-7, 6-9).
- [ ] Choose an efficient data structure (HashMap of HashSets) for reserved seats.
- [ ] Implement the logic to populate the data structure.
- [ ] Implement the logic to check seat availability for each block.
- [ ] Implement the logic to calculate families per row and update the total count.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   1386. Cinema Seat Allocation (This is the same problem)
*   1282. Group the People Given the Group Size They Belong To
*   1094. Car Pooling

## Tags
`Array` `Hash Map` `Set` `Greedy`

## My Notes
// best solution for no OLE and TLE.
