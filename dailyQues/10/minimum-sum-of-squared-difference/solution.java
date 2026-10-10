
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int maxDiff = 0;
        long totalDiff = 0;
        long sum = 0;

        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            totalDiff += diff[i];
            sum += (long) diff[i] * diff[i];
        }

        if (k >= totalDiff) return 0;

        long[] freq = new long[maxDiff + 1];

        for (int d : diff) {
            freq[d]++;
        }

        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (freq[d] == 0) continue;

            long count = freq[d];
            long move = Math.min(k, count);

            // Move one unit from the largest differences.
            sum -= count * d * d;
            sum += (count - move) * d * d;
            sum += move * (long) (d - 1) * (d - 1);

            freq[d] -= move;
            freq[d - 1] += move;
            k -= move;
        }

        return sum;
    }
}

