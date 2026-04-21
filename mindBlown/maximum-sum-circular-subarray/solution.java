class Solution{
    public int maxSubarraySumCircular(int[] nums) {
    int normalMax = kadane(nums); // standard max subarray sum

    int totalSum = 0;
    for (int i = 0; i < nums.length; i++) {
        totalSum += nums[i];
        nums[i] = -nums[i]; // invert for min subarray
    }
    int invertedMax = kadane(nums); // max of inverted = -min subarray
    int circularMax = totalSum + invertedMax;
// normal array ka min subarray sum is the negative of inverted array ka max subarray sum
    if (circularMax == 0) return normalMax; // all numbers negative case
    return Math.max(normalMax, circularMax);
}

private int kadane(int[] nums) {
    int max = nums[0], sum = nums[0];
    for (int i = 1; i < nums.length; i++) {
        sum = Math.max(nums[i], sum + nums[i]);
        max = Math.max(max, sum);
    }
    return max;
}
}
// Trick with Inversion
// If you invert the array (nums[i] = -nums[i]) and run Kadane again:

// invertedMax = kadane(invertedNums)  
// This actually finds the maximum sum of the inverted array, which equals the negative of the minimum subarray sum in the original array.

// So:

// minSubarraySum=−invertedMax

// We know that
// circularMax=totalSum−minSubarraySum
// That's why:
// circularMax=totalSum−(−invertedMax)
// circularMax=totalSum+invertedMax