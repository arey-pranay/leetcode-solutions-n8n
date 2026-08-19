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
We solve this by iterating through rows, checking available seat groups, and allocating families greedily.

## Intuition
The core idea is that each row can accommodate at most two families. A family can sit in one of three contiguous blocks of 4 seats: seats 2-5, 4-7, or 6-9. If seats 2-5 and 6-9 are both available, two families can sit. If only one of these is available, or if the middle block 4-7 is available, one family can sit. We want to maximize this by checking these possibilities for each row.

## Algorithm
1. Initialize `ans` to `2 * n` (maximum possible families if no seats are reserved).
2. Sort `reservedSeats` by row number, then by seat number. This allows processing rows contiguously.
3. Iterate through the sorted `reservedSeats` using a `while` loop.
4. For each row, create a `boolean` array `booked` of size 11 to mark reserved seats (indices 1-10 correspond to seats).
5. While the current reserved seat is in the same row, mark it as `booked`.
6. Check for availability of the three main seat groups:
    - `g1`: Seats 2-5
    - `g2`: Seats 4-7
    - `g3`: Seats 6-9
7. If both `g1` and `g3` are available, two families can be seated in this row. We don't decrement `ans` because `ans` was initialized assuming two families per row.
8. If only one of `g1`, `g2`, or `g3` is available, one family can be seated. Decrement `ans` by 1.
9. If none of the groups are fully available, it means we can't seat a family in the standard configurations. However, the problem implies that if seats 2-5 and 6-9 are blocked, but 4-7 is free, we can still seat one family. The logic `if(g1 && g3){continue;} else if(g1 || g2 || g3) ans--; else ans-=2;` is slightly counter-intuitive. The `else ans-=2` part is incorrect based on the problem statement. The correct logic should be:
    - If `g1` and `g3` are both available, we can seat 2 families. `ans` remains unchanged (as it's initialized to 2*n).
    - If `g1` is available OR `g3` is available OR `g2` is available, we can seat 1 family. Decrement `ans` by 1.
    - If none of these are available, we can't seat any families in this row. `ans` remains unchanged.
    *Correction to the provided code's logic*: The provided code's `else ans-=2` is likely a misunderstanding or a bug. The correct interpretation is that if `g1` and `g3` are available, we seat 2 families. If `g1` or `g2` or `g3` is available, we seat 1 family. If none are available, we seat 0. The initial `ans = 2*n` accounts for the maximum. We only decrement when we *cannot* seat the maximum possible (2 families).
    Let's re-evaluate the provided code's logic:
    - `if(g1 && g3){continue;}`: If both outer blocks are free, we can seat 2 families. `ans` is already initialized to 2*n, so we don't need to do anything for this row. This is correct.
    - `else if(g1 || g2 || g3) ans--;`: If the previous condition is false (meaning `g1` and `g3` are NOT both free), but at least one of `g1`, `g2`, or `g3` is free, we can seat 1 family. Since we initially assumed 2 families per row, we decrement `ans` by 1. This is correct.
    - `else ans-=2;`: This `else` block is reached if `g1 && g3` is false AND `g1 || g2 || g3` is false. This means *no* single family can be seated in any of the standard configurations. This implies 0 families can be seated. However, the code decrements `ans` by 2. This is where the provided code seems to have a flaw or a different interpretation. A more direct interpretation would be: if no configuration allows seating a family, we don't decrement `ans` from its initial 2*n assumption for that row. The provided code's `else ans-=2` implies that if no standard configuration works, we lose 2 potential families. This might be a shortcut if the problem guarantees certain seat arrangements or if there's an implicit understanding of how to handle such cases. Given the standard interpretation, this `else ans-=2` is problematic.

    *Revised Algorithm Step 9 based on standard interpretation*: If none of the groups (`g1`, `g2`, `g3`) are available, it means 0 families can be seated in this row. `ans` remains unchanged for this row's contribution to the total.

    *Let's stick to the provided code's logic for the study document, assuming it's the intended solution for analysis.*
    The provided code's logic:
    - If `g1` and `g3` are available, we can seat 2 families. `ans` is already initialized to `2*n`, so we don't need to adjust `ans` for this row. `continue` skips to the next row.
    - If `g1` and `g3` are NOT both available, but at least one of `g1`, `g2`, or `g3` is available, we can seat 1 family. Since we initially assumed 2 families per row, we decrement `ans` by 1.
    - If `g1` and `g3` are NOT both available, AND none of `g1`, `g2`, or `g3` are available, this implies 0 families can be seated. The code `else ans-=2` is unusual. It suggests that if no standard configuration works, we lose 2 potential families. This might be a simplification or a specific interpretation of the problem constraints.

10. The `check4` helper function checks if a contiguous block of 4 seats (defined by `start` and `end`) is free from reservations.
11. After iterating through all reserved seats, `ans` will hold the maximum number of families.

## Concept to Remember
*   Greedy Approach: Making locally optimal choices (seating families in available blocks) to achieve a globally optimal solution (maximizing total families).
*   Sorting and Grouping: Sorting by row allows processing all reservations for a given row together, simplifying the logic.
*   Bit Manipulation (Alternative): Representing seat availability using bitmasks can be more efficient for checking contiguous blocks.
*   Edge Cases: Handling rows with no reservations and rows with all seats reserved.

## Common Mistakes
*   Incorrectly handling rows with no reservations: These rows can always accommodate 2 families.
*   Overlapping seat group checks: Not realizing that `g1` and `g3` can be used simultaneously for two families.
*   Off-by-one errors in seat indexing or loop bounds.
*   Not sorting `reservedSeats` first, leading to incorrect processing of rows.
*   Misinterpreting the `else ans-=2` logic in the provided solution, which deviates from a straightforward greedy approach.

## Complexity Analysis
- Time: O(R log R + N), where R is the number of reserved seats and N is the number of rows. Sorting `reservedSeats` takes O(R log R). Iterating through `reservedSeats` and processing each row takes O(R) in total because each reserved seat is visited once. The `check4` function takes constant time (O(1)) as it checks a fixed number of seats (4).
- Space: O(1) if we ignore the space for sorting (which might use O(log R) or O(R) depending on implementation). The `booked` array is of fixed size 11, so it's O(1).

## Commented Code
```java
class Solution {
    // Main function to calculate the maximum number of families that can be seated.
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Initialize the answer to the maximum possible families (2 per row).
        int ans = 2 * n;
        
        // Sort reservedSeats first by row number, then by seat number.
        // This allows us to process all reservations for a given row together.
        Arrays.sort(reservedSeats, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        // Get the total number of reserved seats.
        int r = reservedSeats.length;
        // Initialize an index to iterate through reservedSeats.
        int i = 0;
        
        // Loop through the reserved seats, processing them row by row.
        while (i < r) {
            // Get the current row number.
            int row = reservedSeats[i][0];
            // Create a boolean array to mark booked seats for the current row.
            // Size 11 to accommodate seats 1-10, index 0 is unused.
            boolean[] booked = new boolean[11];
            
            // While we are still processing seats in the current row:
            while (i < r && reservedSeats[i][0] == row) {
                // Mark the seat as booked.
                booked[reservedSeats[i][1]] = true;
                // Move to the next reserved seat.
                i++;
            }
            
            // Check availability of the left group of seats (2-5).
            boolean g1 = check4(booked, 2, 5);
            // Check availability of the middle group of seats (4-7).
            boolean g2 = check4(booked, 4, 7);
            // Check availability of the right group of seats (6-9).
            boolean g3 = check4(booked, 6, 9);
            
            // If both the left (2-5) and right (6-9) groups are available,
            // we can seat two families in this row. Since 'ans' was initialized
            // to 2*n, we don't need to decrement it for this row.
            // 'continue' skips to the next row.
            if (g1 && g3) {
                continue;
            } 
            // If the above condition is false (meaning g1 and g3 are NOT both free),
            // but at least one of the groups (g1, g2, or g3) is available,
            // we can seat one family. Since we initially assumed 2 families per row,
            // we decrement 'ans' by 1.
            else if (g1 || g2 || g3) {
                ans--;
            } 
            // If neither g1 and g3 are both free, AND none of g1, g2, or g3 are free,
            // this implies 0 families can be seated in the standard configurations.
            // The provided code decrements 'ans' by 2. This is an unusual step
            // and might imply a specific interpretation or simplification.
            // A more standard greedy approach would not decrement 'ans' here,
            // as we are already accounting for the maximum possible.
            else {
                ans -= 2;
            }
        }
        // Return the final calculated maximum number of families.
        return ans;
    }
    
    // Helper function to check if a contiguous block of seats is available.
    // 'booked' is the boolean array indicating reserved seats.
    // 'start' and 'end' define the inclusive range of seats to check.
    public boolean check4(boolean[] booked, int start, int end) {
        // Iterate through the seats in the specified range.
        for (int i = start; i <= end; i++) {
            // If any seat in this range is booked, return false (not available).
            if (booked[i]) {
                return false;
            }
        }
        // If the loop completes without finding any booked seats, return true (available).
        return true;
    }
}
```

## Interview Tips
*   Clarify the seat numbering and the definition of "family" seating arrangements. Ensure you understand the contiguous blocks.
*   Explain your greedy strategy: why processing row by row and prioritizing the two-family seating is optimal.
*   Discuss the edge case of rows with no reservations and how your initial `ans = 2 * n` handles it.
*   If asked about alternative approaches, mention bit manipulation for checking seat availability more compactly.
*   Be prepared to explain the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the three possible seat groups for a family.
- [ ] Realize that two families can be seated if the outer groups (2-5 and 6-9) are free.
- [ ] Develop a strategy to process rows efficiently (sorting is key).
- [ ] Implement the logic for checking seat availability.
- [ ] Handle the case where only one family can be seated.
- [ ] Analyze the time and space complexity.
- [ ] Consider edge cases like empty `reservedSeats` or full rows.

## Similar Problems
*   1386. Cinema Seat Allocation (This is the same problem)
*   122. Best Time to Buy and Sell Stock II (Greedy approach)
*   56. Merge Intervals (Sorting and merging intervals)

## Tags
`Array` `Sorting` `Greedy` `Hash Map`
