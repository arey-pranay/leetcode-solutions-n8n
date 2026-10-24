# Count Days Spent Together

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `String`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    static int[] dayCount = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        
        int aa = normalizedDays(arriveAlice); //01-05 => Jan 5th => 5
        int la = normalizedDays(leaveAlice); // 04-27 => Apr 27th => 120+27 => 147
        int ab = normalizedDays(arriveBob); 
        int lb = normalizedDays(leaveBob);
        
        int start = Math.max(aa,ab);
        int end = Math.min(la,lb);
        return Math.max(0,end - start + 1);
        
    }
    private int normalizedDays(String s){
        // days since start of the year;
        // 12 - 31
        int month = (s.charAt(0)-'0')*10 + (s.charAt(1)-'0');
        int days =  (s.charAt(3)-'0')*10 + (s.charAt(4)-'0');
        for(int i=0;i<month-1;i++) days += dayCount[i];
        return days;
    }
}
```

---

---
## Quick Revision
The problem is to count the number of days two couples spend together, given their arrival and departure dates.
We solve this problem by normalizing the dates, finding the overlap, and calculating the duration.

## Intuition
This approach works because we can normalize the dates to a single integer representing the number of days since the start of the year, making it easy to compare and find overlaps.

## Algorithm
1. Normalize the arrival and departure dates for both Alice and Bob.
2. Find the maximum of the arrival dates and the minimum of the departure dates.
3. Calculate the duration by taking the difference between the departure date and the arrival date, and add 1.

## Concept to Remember
* Normalizing dates to a single integer representation
* Using arrays to store precomputed values (e.g., days in each month)
* Finding overlaps by taking the maximum of arrival dates and the minimum of departure dates

## Common Mistakes
* Failing to normalize the dates correctly
* Not considering the possibility of dates being the same (e.g., arrival date of Alice and departure date of Bob)
* Not handling edge cases (e.g., invalid input dates)

## Complexity Analysis
- Time: O(n) - reason: where n is the number of days in the year
- Space: O(1) - reason: we use a fixed-size array to store precomputed values

## Commented Code
```java
class Solution {
    // precomputed array of days in each month
    static int[] dayCount = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // normalize arrival and departure dates for both Alice and Bob
        int aa = normalizedDays(arriveAlice); //01-05 => Jan 5th => 5
        int la = normalizedDays(leaveAlice); // 04-27 => Apr 27th => 120+27 => 147
        int ab = normalizedDays(arriveBob); 
        int lb = normalizedDays(leaveBob);

        // find overlap by taking max of arrival dates and min of departure dates
        int start = Math.max(aa, ab);
        int end = Math.min(la, lb);

        // calculate duration
        return Math.max(0, end - start + 1);
    }

    // normalize a date string to a single integer
    private int normalizedDays(String s) {
        // days since start of the year
        // 12 - 31
        int month = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
        int days = (s.charAt(3) - '0') * 10 + (s.charAt(4) - '0');

        // add days for previous months
        for (int i = 0; i < month - 1; i++) {
            days += dayCount[i];
        }

        return days;
    }
}
```

## Interview Tips
* Make sure to normalize the dates correctly
* Consider edge cases and invalid input dates
* Use a clear and concise explanation of the algorithm

## Revision Checklist
- [ ] Understand the problem and the input format
- [ ] Normalize dates correctly
- [ ] Handle edge cases and invalid input dates
- [ ] Use a clear and concise explanation of the algorithm

## Similar Problems
* [Calendar Lockers](https://leetcode.com/problems/calendar-lockers/)
* [Day of the Week](https://leetcode.com/problems/day-of-the-week/)
* [Number of Islands](https://leetcode.com/problems/number-of-islands/)

## Tags
`Array` `Hash Map` `Date` `Normalization`
