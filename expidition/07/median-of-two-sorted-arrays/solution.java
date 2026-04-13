class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array to keep cut1 in valid range
        if (nums1.length > nums2.length) {
            ret urn findMedianSortedArrays(nums2, nums1);
        }

        // the merged element array has n+m = o size. So the left side has ls=o/2 size.
        int o = nums1.length + nums2.length;
        int ls = o / 2;

        // binary search cut2 on the smaller array (nums1)
        int lo = 0, hi = nums1.length;

        while (lo <= hi) {
            int cut1 = (lo + hi) / 2;
            int cut2 = ls - cut1;

            // use INT boundaries to handle edge cases where cut is at 0 or end of array
            int l1 = (cut1 == 0)            ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = (cut2 == 0)            ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int r1 = (cut1 == m) ? Integer.MAX_VALUE : nums1[cut1];
            int r2 = (cut2 == n) ? Integer.MAX_VALUE : nums2[cut2];

            // valid cut: largest on left side of both arrays <= smallest on right side
            if (l1 <= r2 && l2 <= r1) {
                if (o % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.min(r1, r2);
                }
            }
            // l1 too large: shift cut1 left
            if (l1 > r2) {
                hi = cut1 - 1;
            }
            // l2 too large: shift cut1 right
            else {
                lo = cut1 + 1;
            }
        }

        return -1;
    }
}

// the goal is to simulate what would be the middle element or middle 2 elements
// Suppose you stand at the midpoint of the merged array
// the left side of the merged array has some elements from nums1 and some from nums2
// the merged element array has n+m = o size. So the left side has ls=o/2 size.
// we need to find which subarray will be contributed by which array.
// let's assume nums2 contributes cut2= nums2/2 elements and nums1 contributes the remaining cut1=(o- nums2/2)
// the largest element from nums1 subarray nums1[cut1] needs to be less than nums2[cuts2+1]
// the largest element from nums2 subarray nums2[cut2] needs to be less than nums1[cuts1+1]
// if that is the case, then cuts are valid and contributions are reasonable
// otherwise if nums1[cut1] > nums2[cut2+1] then cut1 needs to shift left or cut2 needs to shift right
// if nums2[cut2] > nums1[cut1] cut2 needs to shift left or cut1 needs to shift right ✅
// the bottomline being contribution by nums1 contributoin by nums2 must be equal to o
// once you find a valid cut for both arrays, consider elements from left of cut1 and left of cut2
// they will contribute to left part of merged array. Take max(nums1[cut1],nums[cut2]) to find left subarray contribution to median
// then get min(nums1[cut1+1],nums2[cut2+1]) is the contribution of right subarray of the merged array, in median calculation/
// adjust according to even or odd value of o.
