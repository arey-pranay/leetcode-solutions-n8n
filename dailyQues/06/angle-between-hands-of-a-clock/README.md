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
Calculate the angle between the hour and minute hands of a clock given the time. The solution involves calculating the angular position of each hand and finding the difference.
## Intuition
The core idea is to determine the exact angular position of both the hour and minute hands relative to the 12 o'clock position. Since the hour hand also moves as the minutes pass, we need to account for this continuous movement. The angle between them is then the absolute difference, and we take the smaller angle (less than or equal to 180 degrees).
## Algorithm
1. Calculate the angle of the minute hand: Each minute represents 360/60 = 6 degrees. So, `minute_angle = minutes * 6`.
2. Calculate the angle of the hour hand: The hour hand moves 360 degrees in 12 hours, which is 30 degrees per hour. However, it also moves as minutes pass. In 60 minutes, the hour hand moves 30 degrees, so in 1 minute, it moves 30/60 = 0.5 degrees. The total angle for the hour hand is `hour_angle = (hour % 12 + minutes / 60.0) * 30`. The `hour % 12` handles cases where `hour` is 12 or greater, and `minutes / 60.0` accounts for the fractional movement due to minutes.
3. Calculate the absolute difference between the two angles: `diff = abs(hour_angle - minute_angle)`.
4. Determine the smaller angle: The angle between hands can be measured in two directions. We want the smaller one, which is always less than or equal to 180 degrees. If `diff > 180`, the smaller angle is `360 - diff`. Otherwise, it's `diff`.
## Concept to Remember
*   **Circular Motion and Degrees:** Understanding that a clock face is a circle of 360 degrees and how to convert time units (hours, minutes) into angular degrees.
*   **Relative Motion:** Recognizing that the hour hand's position is not static but depends on the minutes passed.
*   **Modular Arithmetic:** Using the modulo operator (`%`) to handle hour values that might be 12 or greater, mapping them correctly to the 12-hour clock face.
## Common Mistakes
*   **Ignoring Hour Hand's Minute Movement:** Forgetting that the hour hand moves continuously with the minutes, not just on the hour mark.
*   **Incorrectly Handling 12 O'Clock:** Not properly converting 12 o'clock to 0 for calculations, or handling it as a special case.
*   **Not Taking the Smaller Angle:** Returning the larger angle when the calculated difference exceeds 180 degrees.
*   **Integer Division:** Using integer division where floating-point division is required (e.g., `minutes / 60` instead of `minutes / 60.0`).
## Complexity Analysis
- Time: O(1) - The calculations involve a fixed number of arithmetic operations, independent of the input values.
- Space: O(1) - No extra data structures are used; only a few variables to store intermediate results.
## Commented Code
```java
class Solution {
    public double angleClock(int hour, int minutes) {
        // Calculate the angle of the minute hand.
        // Each minute corresponds to 6 degrees (360 degrees / 60 minutes).
        double minuteAngle = minutes * 6.0;

        // Calculate the angle of the hour hand.
        // The hour hand moves 30 degrees per hour (360 degrees / 12 hours).
        // It also moves 0.5 degrees per minute (30 degrees / 60 minutes).
        // We use (hour % 12) to handle 12 o'clock correctly (as 0 for calculation purposes).
        // We add (minutes / 60.0) to account for the hour hand's movement within the current hour.
        double hourAngle = (hour % 12 + minutes / 60.0) * 30.0;

        // Calculate the absolute difference between the hour and minute hand angles.
        double diff = Math.abs(hourAngle - minuteAngle);

        // The angle between hands can be measured in two directions.
        // We want the smaller angle, which is always <= 180 degrees.
        // If the difference is greater than 180, subtract it from 360.
        // This is equivalent to: return Math.min(diff, 360 - diff);
        return diff > 180 ? 360 - diff : diff;
    }
}
```
## Interview Tips
*   **Clarify Edge Cases:** Ask about inputs like 12:00, 3:00, 6:00, and times with 0 minutes.
*   **Explain the Math:** Clearly articulate how you derive the degrees per minute and degrees per hour for each hand.
*   **Visualize the Clock:** Use a whiteboard or drawing to illustrate the movement of the hands and how the angles are calculated.
*   **Consider the Smaller Angle:** Emphasize why you need to take `min(diff, 360 - diff)` or the equivalent conditional check.
## Revision Checklist
- [ ] Understand the 360 degrees of a clock face.
- [ ] Calculate minute hand angle: `minutes * 6`.
- [ ] Calculate hour hand angle: `(hour % 12 + minutes / 60.0) * 30`.
- [ ] Compute absolute difference: `abs(hourAngle - minuteAngle)`.
- [ ] Return the smaller angle: `min(diff, 360 - diff)`.
## Similar Problems
1.  Find Pivot Integer
2.  Minimum Time Difference
3.  Day of the Week
## Tags
`Math` `Geometry`
