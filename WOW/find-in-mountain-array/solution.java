/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray arr) {
        int peak = getPeakIndex(arr);
        int ans = findBetween(target, arr, 0, peak-1,false);
        if(ans != -1) return ans;
        return findBetween(target, arr, peak, arr.length()-1,true);
    }
    public int getPeakIndex(MountainArray arr){
        int low = 0;
        int high = arr.length()-1;
        while(low<high){//peak ke left aur right dono neighbours chhote honge.
            int mid = low + (high-low)/2;
            if(arr.get(mid) < arr.get(mid+1)) low = mid+1;
            else high = mid;
        }
        return low;
    }
    public int findBetween(int target, MountainArray arr , int low , int high, boolean isReversed){
        while(low<=high){
            int mid = low + (high-low)/2;
            if(isReversed){
               if(target < arr.get(mid)) low = mid+1;
               else if(target > arr.get(mid)) high = mid-1;   
               else return mid;
            }
            else {
                if(target > arr.get(mid)) low = mid+1;
                else if(target < arr.get(mid)) high = mid-1;
                else return mid;
            }
        }
        return -1;
    }
}