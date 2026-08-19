# Cinema Seat Allocation

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Greedy` `Bit Manipulation`  
**Time:** O(R log R + N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int ans=2*n;
        Arrays.sort(reservedSeats,(a,b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        int r = reservedSeats.length;
        int i=0;
        while(i<r){
            int row = reservedSeats[i][0];
            boolean[] booked = new boolean[11];
            while(i<r && reservedSeats[i][0]==row){
                booked[reservedSeats[i][1]] = true;
                i++;
            }
            boolean g1 = check4(booked,2,5);
            boolean g2 = check4(booked,4,7);
            boolean g3 = check4(booked,6,9);
            if(g1 && g3){continue;}
            else if(g1 || g2 || g3) ans--;
            else ans-=2;
        }
        return ans;
    }
    public boolean check4(boolean[] booked,int start, int end){
        for(int i=start;i<=end;i++)if(booked[i]) return false;
        return true;
    }
}
```

---

---
## Quick Revision
The problem asks to maximize the number of families that can be seated in a cinema given reserved seats.
We solve this by iterating through rows, checking available seat groups for families, and greedily assigning seats.

## Intuition
The core idea is that each row can accommodate at most two families. A family can sit in seats 2-5, 4-7, or 6-9. If seats 2-5 and 6-9 are both available, two families can sit. If only one of these is available, or if seats 4-7 are available, one family can sit. We want to maximize the number of families, so we prioritize the two-family scenario.

## Algorithm
1. Initialize `ans` to `2 * n` (maximum possible families if no seats are reserved).
2. Sort `reservedSeats` by row number, then by seat number. This allows us to process reservations row by row efficiently.
3. Iterate through the sorted `reservedSeats` using a `while` loop.
4. For each row, create a `boolean` array `booked` of size 11 (seats 1-10, index 0 unused) to mark reserved seats in that row.
5. While the current reservation is for the same row, mark the corresponding seat in `booked` as `true`.
6. After processing all reservations for a row, check for available family seating groups:
    - `g1`: Seats 2-5 available.
    - `g2`: Seats 4-7 available.
    - `g3`: Seats 6-9 available.
7. If both `g1` and `g3` are available, two families can be seated in this row. We don't decrement `ans` because it's already accounted for in the initial `2*n`.
8. If `g1` or `g2` or `g3` is available (but not both `g1` and `g3`), one family can be seated. Decrement `ans` by 1.
9. If none of the above conditions are met (meaning no two-family or one-family groups are fully available), it implies that the row cannot accommodate any additional families in the preferred configurations. However, the initial `ans = 2*n` assumes two families per row. If we can't fit two, we might still be able to fit one. The logic `if(g1 && g3){continue;} else if(g1 || g2 || g3) ans--; else ans-=2;` implicitly handles this. If `g1 && g3` is true, we do nothing (2 families already accounted for). If `g1 || g2 || g3` is true (but not `g1 && g3`), we subtract 1 (1 family accommodated). If none are true, it means we *cannot* fit even one family in the preferred slots, so we must subtract 2 from the initial `2*n` assumption for this row, as the initial assumption of 2 families per row is now invalid for this specific row's configuration.
10. The `check4` helper function checks if a contiguous block of 4 seats (inclusive of start and end) is available.
11. Return the final `ans`.

## Concept to Remember
*   Greedy Approach: Making locally optimal choices (prioritizing two-family seating) to achieve a globally optimal solution.
*   Interval/Range Checking: Efficiently checking for availability within specific seat ranges.
*   Data Structures for Efficient Lookups: Using a boolean array or hash map to quickly check seat availability.
*   Sorting for Grouping: Sorting reservations by row to process them in contiguous blocks.

## Common Mistakes
*   Not handling overlapping seat groups correctly (e.g., seats 4-7 overlap with 2-5 and 6-9).
*   Incorrectly calculating the reduction in `ans` when only one family can be seated.
*   Failing to sort `reservedSeats`, leading to inefficient processing or incorrect logic.
*   Off-by-one errors when checking seat ranges or array indices.
*   Not considering rows with no reservations, which should contribute 2 families to the total.

## Complexity Analysis
- Time: O(R log R + N), where R is the number of reserved seats and N is the number of rows. Sorting `reservedSeats` takes O(R log R). Iterating through `reservedSeats` and processing each row takes O(R) in total because each reserved seat is visited once. The `check4` function takes constant time (O(4)).
- Space: O(1) if we consider the `booked` array as constant size (11 seats). If we consider the space for sorting `reservedSeats` in-place, it's O(log R) or O(R) depending on the sorting algorithm implementation.

## Commented Code
```java
class Solution {
    // Main method to calculate the maximum number of families that can be seated.
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Initialize the answer to the maximum possible families (2 per row).
        int ans = 2 * n;
        
        // Sort reservedSeats first by row number, then by seat number.
        // This allows us to process reservations row by row efficiently.
        Arrays.sort(reservedSeats, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        // Get the total number of reserved seats.
        int r = reservedSeats.length;
        // Initialize an index to iterate through reservedSeats.
        int i = 0;
        
        // Loop through the sorted reserved seats.
        while (i < r) {
            // Get the current row number from the reservation.
            int row = reservedSeats[i][0];
            // Create a boolean array to mark booked seats for the current row.
            // Size 11 to accommodate seats 1-10, with index 0 unused.
            boolean[] booked = new boolean[11];
            
            // Process all reservations for the current row.
            while (i < r && reservedSeats[i][0] == row) {
                // Mark the reserved seat as booked.
                booked[reservedSeats[i][1]] = true;
                // Move to the next reservation.
                i++;
            }
            
            // Check if the left group of seats (2-5) is available.
            boolean g1 = check4(booked, 2, 5);
            // Check if the middle group of seats (4-7) is available.
            boolean g2 = check4(booked, 4, 7);
            // Check if the right group of seats (6-9) is available.
            boolean g3 = check4(booked, 6, 9);
            
            // If both the left (2-5) and right (6-9) groups are available,
            // two families can be seated in this row.
            // We 'continue' because the initial 'ans = 2*n' already accounts for 2 families per row.
            if (g1 && g3) {
                continue;
            } 
            // If either the left (2-5), middle (4-7), or right (6-9) group is available,
            // but not both left and right groups simultaneously, then only one family can be seated.
            // Decrement the total count by 1.
            else if (g1 || g2 || g3) {
                ans--;
            } 
            // If none of the preferred groups (2-5, 4-7, 6-9) are available for a family,
            // it means we cannot seat any family in the standard configurations for this row.
            // Since the initial 'ans' assumed 2 families per row, and we can't fit any,
            // we must subtract 2 from 'ans' to reflect that this row cannot contribute families.
            else {
                ans -= 2;
            }
        }
        // Return the final calculated maximum number of families.
        return ans;
    }
    
    // Helper function to check if a contiguous block of 4 seats is available.
    // 'booked' array: marks reserved seats.
    // 'start', 'end': the inclusive range of seats to check.
    public boolean check4(boolean[] booked, int start, int end) {
        // Iterate through the seats in the given range.
        for (int j = start; j <= end; j++) {
            // If any seat in this range is booked, return false (not available).
            if (booked[j]) {
                return false;
            }
        }
        // If the loop completes without finding any booked seats, return true (available).
        return true;
    }
}
```

## Interview Tips
*   Clearly explain your greedy strategy: why prioritizing the two-family seating arrangement (seats 2-5 and 6-9) is optimal.
*   Walk through the logic for decrementing `ans`: when to subtract 0, 1, or 2. Emphasize the `if (g1 && g3)` case.
*   Discuss the importance of sorting `reservedSeats` and how it simplifies processing row by row.
*   Be prepared to explain the time and space complexity, especially the reasoning behind the O(R log R) for sorting.
*   Consider edge cases: no reserved seats, all seats reserved, rows with only one reservation.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the possible seating configurations for a family (2-5, 4-7, 6-9).
- [ ] Realize that a row can seat at most two families.
- [ ] Develop a greedy strategy to maximize families.
- [ ] Implement sorting of `reservedSeats`.
- [ ] Implement row-by-row processing.
- [ ] Correctly check for availability of seat groups (g1, g2, g3).
- [ ] Implement the logic for decrementing `ans` based on available groups.
- [ ] Handle rows with no reservations implicitly or explicitly.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   1386. Cinema Seat Allocation (This is the same problem)
*   57. Insert Interval
*   435. Non-overlapping Intervals
*   1288. Remove Covered Intervals

## Tags
`Array` `Hash Map` `Greedy` `Sorting`
