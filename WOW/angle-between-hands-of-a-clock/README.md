# Angle Between Hands Of A Clock

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public double angleClock(int hour, int minutes) {
        // if hour hand is kept constant
        //     movement of minute hand 
        //         every 5 minutes causes a 360/12 degree movement = 30 degrees.
        //         every 1 minute causes a 30/5 = 6 degree movement
        // but since the hour hand also moves in the same direction
        //     it decreases the angle covered by a certain amount
        //     the hour hand moves 30 degrees in 60 minutes. => 0.5 degree each minute
        // and since the hour hand is not always at 12, it has an offset too,
        //     that is => hourValue * 30

        // so, if I have the minutes, the net movement is => 6*minutes - 0.5*minutes - 30*hour => 5.5 * minutes - 30*hour

        // Let's take mod to get only positive direction movement;
        double ans = Math.abs(5.5*minutes - 30*hour);
        // fifteen degrees greater than 180 should become 15 degrees lesser than 180
        return ans >= 180 ? ans - 2*(ans - 180) : ans;
    }
}
// 165 - 90 = 75
// 82.5 - 90 = -7.5
```

---

---
## Quick Revision
Calculate the angle between the hour and minute hands of a clock given the time. This is done by calculating the position of each hand in degrees and finding the difference.

## Intuition
The core idea is to determine the angular position of both the hour and minute hands relative to the 12 o'clock position. The minute hand moves 360 degrees in 60 minutes, meaning 6 degrees per minute. The hour hand moves 360 degrees in 12 hours, meaning 30 degrees per hour. Crucially, the hour hand also moves *between* hour marks as the minutes tick by. For every minute, the hour hand moves 0.5 degrees (30 degrees / 60 minutes). By calculating the absolute difference between their positions and ensuring the smaller angle is returned, we get the correct answer.

## Algorithm
1.  Calculate the angle of the minute hand: `minute_angle = minutes * 6` degrees.
2.  Calculate the angle of the hour hand: `hour_angle = (hour % 12 + minutes / 60.0) * 30` degrees. The `hour % 12` handles the 12-hour format, and `minutes / 60.0` accounts for the hour hand's movement within the current hour.
3.  Calculate the absolute difference between the two angles: `diff = abs(hour_angle - minute_angle)`.
4.  The angle between clock hands can be at most 180 degrees. If the calculated difference is greater than 180, subtract it from 360 to get the smaller angle: `result = min(diff, 360 - diff)`.

## Concept to Remember
*   **Modular Arithmetic:** Used to handle the 12-hour cycle of the clock (`hour % 12`).
*   **Linear Interpolation/Proportionality:** Understanding how the hour hand's position changes proportionally with the minutes.
*   **Angle Calculation:** Converting time units (hours, minutes) into angular degrees.

## Common Mistakes
*   Forgetting to account for the hour hand's movement based on the minutes.
*   Not handling the 12-hour clock format correctly (e.g., treating 12 as 0 or not using modulo).
*   Returning the larger angle when the smaller angle (<= 180 degrees) is required.
*   Integer division issues when calculating the hour hand's fractional movement.

## Complexity Analysis
- Time: O(1) - The calculations involve a fixed number of arithmetic operations, independent of the input values.
- Space: O(1) - Only a few variables are used to store intermediate results, requiring constant extra space.

## Commented Code
```java
class Solution {
    public double angleClock(int hour, int minutes) {
        // Calculate the angle of the minute hand.
        // Each minute mark represents 6 degrees (360 degrees / 60 minutes).
        double minuteAngle = minutes * 6.0;

        // Calculate the angle of the hour hand.
        // The hour hand moves 30 degrees per hour (360 degrees / 12 hours).
        // We use (hour % 12) to handle the 12-hour format correctly.
        // The hour hand also moves proportionally with the minutes.
        // For every minute, the hour hand moves 0.5 degrees (30 degrees / 60 minutes).
        // So, the total hour hand angle is (hour % 12 + minutes / 60.0) * 30.0.
        double hourAngle = (hour % 12 + minutes / 60.0) * 30.0;

        // Calculate the absolute difference between the hour and minute hand angles.
        double diff = Math.abs(hourAngle - minuteAngle);

        // The angle between clock hands should be the smaller angle, which is <= 180 degrees.
        // If the calculated difference is greater than 180, we subtract it from 360.
        // This is equivalent to finding the minimum of 'diff' and '360 - diff'.
        return Math.min(diff, 360.0 - diff);
    }
}
```

## Interview Tips
*   Clearly explain how you're calculating the position of each hand, especially the hour hand's movement due to minutes.
*   Walk through an example (e.g., 3:30) to verify your logic before coding.
*   Be prepared to discuss why the final angle must be less than or equal to 180 degrees.
*   Mention the use of floating-point numbers for precision in angle calculations.

## Revision Checklist
- [ ] Understand the angular speed of minute and hour hands.
- [ ] Correctly handle the 12-hour format for the hour.
- [ ] Account for the hour hand's movement based on minutes.
- [ ] Calculate the absolute difference between angles.
- [ ] Ensure the returned angle is the smaller one (<= 180 degrees).

## Similar Problems
*   [1347. Minimum Number of Steps to Make Two Strings Anagram](https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/) (Note: This is a placeholder, as there isn't a direct LeetCode problem that is *very* similar in terms of core logic. Problems involving time calculations or geometric angles might be tangentially related.)

## Tags
`Math` `Geometry`
