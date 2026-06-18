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
Calculate the angle between the hour and minute hands of a clock given the time.
Solve by calculating the individual angles of each hand and finding their difference, then taking the smaller angle.

## Intuition
The core idea is to realize that both hands move continuously. The minute hand moves 360 degrees in 60 minutes, meaning 6 degrees per minute. The hour hand moves 360 degrees in 12 hours (720 minutes), meaning 0.5 degrees per minute. Crucially, the hour hand's position is also dependent on the current hour, with each hour mark representing 30 degrees. By calculating the total angle for each hand and finding the absolute difference, we can determine the angle. Since we want the smaller angle, we check if the calculated angle is greater than 180 degrees and adjust accordingly.

## Algorithm
1. Calculate the angle of the minute hand: `minute_angle = minutes * 6` degrees.
2. Calculate the base angle of the hour hand: `hour_base_angle = (hour % 12) * 30` degrees.
3. Calculate the additional movement of the hour hand due to minutes: `hour_minute_offset = minutes * 0.5` degrees.
4. Calculate the total angle of the hour hand: `hour_angle = hour_base_angle + hour_minute_offset` degrees.
5. Calculate the absolute difference between the two angles: `diff_angle = abs(hour_angle - minute_angle)`.
6. Determine the smaller angle: If `diff_angle > 180`, the smaller angle is `360 - diff_angle`. Otherwise, it's `diff_angle`.

## Concept to Remember
*   **Circular Motion and Angles:** Understanding how angles change on a circular dial (like a clock).
*   **Relative Speed:** The concept of how the difference in speeds of two moving objects affects their separation.
*   **Modular Arithmetic:** Using the modulo operator to handle cyclical values (like hours on a 12-hour clock).

## Common Mistakes
*   Forgetting that the hour hand also moves as the minutes change.
*   Not handling the 12-hour clock cycle correctly (e.g., treating 12 AM/PM as 0 or 24).
*   Calculating only one of the two possible angles between the hands (e.g., always the larger one).
*   Integer division issues if not using floating-point numbers for calculations.

## Complexity Analysis
- Time: O(1) - The calculations involve a fixed number of arithmetic operations, independent of the input values.
- Space: O(1) - No extra space is used beyond a few variables to store intermediate results.

## Commented Code
```java
class Solution {
    public double angleClock(int hour, int minutes) {
        // Calculate the angle of the minute hand.
        // Each minute mark represents 360/60 = 6 degrees.
        double minuteAngle = minutes * 6.0;

        // Calculate the base angle of the hour hand.
        // Each hour mark represents 360/12 = 30 degrees.
        // We use hour % 12 to handle the 12-hour cycle.
        double hourBaseAngle = (hour % 12) * 30.0;

        // Calculate the additional movement of the hour hand due to minutes.
        // The hour hand moves 30 degrees in 60 minutes, so 0.5 degrees per minute.
        double hourMinuteOffset = minutes * 0.5;

        // Calculate the total angle of the hour hand.
        // This is the base hour angle plus the offset from the minutes.
        double hourAngle = hourBaseAngle + hourMinuteOffset;

        // Calculate the absolute difference between the hour and minute hand angles.
        double diffAngle = Math.abs(hourAngle - minuteAngle);

        // The angle between hands can be measured in two directions.
        // We want the smaller angle, which is always <= 180 degrees.
        // If the calculated difference is greater than 180, subtract it from 360.
        // This is equivalent to: return Math.min(diffAngle, 360.0 - diffAngle);
        return diffAngle >= 180 ? 360.0 - diffAngle : diffAngle;
    }
}
```

## Interview Tips
*   Clearly explain your logic for calculating each hand's angle, especially the hour hand's minute-based movement.
*   Emphasize the need to return the smaller angle and how you handle the >180 degree case.
*   Be prepared to discuss the time and space complexity of your solution.
*   Consider edge cases like 12:00 or times with 0 minutes.

## Revision Checklist
- [ ] Understand the degrees per minute for both hands.
- [ ] Account for the hour hand's movement based on minutes.
- [ ] Handle the 12-hour clock cycle correctly.
- [ ] Calculate the absolute difference between angles.
- [ ] Ensure the smaller angle (<= 180 degrees) is returned.

## Similar Problems
*   [171. Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number/) (Conceptually related to base conversions/representation)

## Tags
`Math` `Geometry`
