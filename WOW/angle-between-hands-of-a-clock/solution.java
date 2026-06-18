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