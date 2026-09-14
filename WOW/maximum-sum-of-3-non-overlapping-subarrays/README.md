# Maximum Sum Of 3 Non Overlapping Subarrays

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Sliding Window` `Prefix Sum`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        
        int sum =0;
        int n = nums.length;
        for(int i=0;i<k;i++) sum += nums[i];
        int[] sums = new int[n-k+1];
        sums[0] = sum;
        int index = 1;
        for(int i=k;i<n;i++){
            sums[index] = sums[index-1]+nums[i]-nums[i-k];
            index++;
        } // har index se start krke k length ka group bnane ka sum
        int m = sums.length;
        int[] bestLeft = new int[m]; // ki current index se left me dekhne pe highest sum ka index kaunsa hai
        int[] bestRight = new int[m]; // ki current index se right me dekhne pe highest sum ka index kaunsa hai
                
        bestLeft[0] = 0;
        for(int i=1; i<m;i++){
            if(sums[i] > sums[bestLeft[i-1]]) bestLeft[i] = i;
            else bestLeft[i] = bestLeft[i-1];
        }
        bestRight[m-1] = m-1;
        for(int i=m-2; i>=0;i--){
            if(sums[i] >= sums[bestRight[i+1]]) bestRight[i] = i;
            else bestRight[i] = bestRight[i+1];
        }
        int[] ans = new int[3];
        int maxSum = 0;
        for(int mid = k; mid<m-k; mid++){ // 3 index choose krne hai, jo middle index hai wo k se m-k ke beech hoga, let's try all values of it
        
            // mid se mid-k aur mid+k ke index unusable hai, kyuki non-overlapping hona zaruri hai.
            int l = bestLeft[mid-k]; 
            int r = bestRight[mid+k];
            int currSum = sums[mid] + sums[l] + sums[r];
            if(currSum > maxSum){
                ans[0] = l;ans[1] = mid;ans[2]=r; maxSum = currSum;
            }
        }
        return ans;
    }
    // func(sums,0, new ArrayList<>(), 0);
    // public void func(int[] nums, int index, List<Integer> list, int sum){
    //     if(list.size()==3){
    //         if(sum > maxSum){
    //             maxSum = sum;
    //             for(int i=0;i<list.size();i++) ans[i] = list.get(i);
    //         }
    //         return;
    //     }
        
    //     if(index >= nums.length) return;
        
    //     list.add(index);
    //     sum+=nums[index];
    //     func(nums, index+minGap, list, sum);//take 
    //     sum-=nums[index];
    //     list.remove(list.size()-1);
    //     func(nums, index+1, list, sum);//notTake
        
    // }
}
// 1005 1 1004 1 1003 1 999 999 999 999 999 999
```

---

---
## Quick Revision
Find three non-overlapping subarrays of size k such that the sum of the three subarrays is maximum.
Solve it by precomputing the prefix sums of the array and then using two pointers to find the maximum sum.

## Intuition
The key insight here is to precompute the prefix sums of the array, which allows us to calculate the sum of any subarray in O(1) time. Then, we use two pointers to find the maximum sum of three non-overlapping subarrays. The idea is to fix one subarray in the middle and then use two pointers to find the maximum sum of the subarrays on the left and right.

## Algorithm
1. Precompute the prefix sums of the array and store them in an array `sums`.
2. Initialize three arrays `bestLeft`, `bestRight`, and `ans`.
3. Use dynamic programming to fill up the `bestLeft` and `bestRight` arrays. The idea is to keep track of the maximum sum of subarrays ending at each position.
4. Iterate over the middle subarray and for each position, calculate the sum of the subarray to the left and right using the `bestLeft` and `bestRight` arrays.
5. Update the `ans` array with the maximum sum found so far.

## Concept to Remember
* Prefix sums: an array where each element is the sum of all elements up to that position.
* Dynamic programming: a method for solving complex problems by breaking them down into smaller subproblems.

## Common Mistakes
* Forgetting to handle the edge cases where the subarrays overlap.
* Not using dynamic programming to fill up the `bestLeft` and `bestRight` arrays.
* Not updating the `ans` array correctly.

## Complexity Analysis
- Time: O(n) where n is the length of the input array.
- Space: O(n) for storing the prefix sums and the `bestLeft` and `bestRight` arrays.

## Commented Code
```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        sums[0] = 0;
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        
        int[] bestLeft = new int[n - k + 1];
        bestLeft[0] = 0;
        for (int i = 1; i < n - k + 1; i++) {
            if (sums[i] > sums[bestLeft[i - 1]]) {
                bestLeft[i] = i;
            } else {
                bestLeft[i] = bestLeft[i - 1];
            }
        }
        
        int[] bestRight = new int[n - k + 1];
        bestRight[n - k] = n - k;
        for (int i = n - k - 1; i >= 0; i--) {
            if (sums[i + k] > sums[bestRight[i + 1]]) {
                bestRight[i] = i + k;
            } else {
                bestRight[i] = bestRight[i + 1];
            }
        }
        
        int[] ans = new int[3];
        int maxSum = 0;
        for (int mid = k; mid < n - k; mid++) {
            int l = bestLeft[mid - k];
            int r = bestRight[mid + k];
            int currSum = sums[mid] + sums[l] + sums[r];
            if (currSum > maxSum) {
                ans[0] = l;
                ans[1] = mid;
                ans[2] = r;
                maxSum = currSum;
            }
        }
        return ans;
    }
}
```

## Interview Tips
* Make sure to handle the edge cases carefully.
* Use dynamic programming to fill up the `bestLeft` and `bestRight` arrays.
* Update the `ans` array correctly.
* Practice solving similar problems to improve your skills.

## Revision Checklist
- [ ] Understand the problem and the prefix sum technique.
- [ ] Use dynamic programming to fill up the `bestLeft` and `bestRight` arrays.
- [ ] Update the `ans` array correctly.
- [ ] Handle the edge cases carefully.

## Similar Problems
* 1591. Strange Printer
* 1695. Maximum Component as Target
* 1011. Caught by Rovers

## Tags
`Array` `Hash Map` `Dynamic Programming` `Prefix Sum` `Maximum Sum`
