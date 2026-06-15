class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = 0;

        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int low = 1;
        int high = maxPile;

        while (low < high) {
            int mid = low + (high - low) / 2;

            long hours = 0;
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid; // ceil(pile / mid)
            }

            if (hours <= h) {
                high = mid;      // try a smaller speed
            } else {
                low = mid + 1;   // need a larger speed
            }
        }

        return low;
    }
}