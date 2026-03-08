class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int j = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[j][1] >= intervals[i][0]) {
                intervals[j][1] = Math.max(intervals[j][1], intervals[i][1]);
            } else {
                j++;
                intervals[j] = intervals[i];
            }
        }
        return Arrays.copyOfRange(intervals, 0, j + 1);
    }
}