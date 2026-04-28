class Solution {
    public int minOperations(int[][] grid, int x) { 
        int[] arr = new int[grid.length * grid[0].length];
        int index = 0;
        for(int[] temp : grid) for(int num : temp) arr[index++] = num;
        Arrays.sort(arr);
        // the case where -> agr kisi bhi number ko hum array ka koi dusra number nhi bna skte, then makking all same is not possible.
        for(int i =0; i<arr.length;i++) if(Math.abs(arr[i]-arr[0])%x!=0)  return -1;
        int ans = 0;
        int mid = arr[arr.length/2];
        //does there exist an n such that:
        //   for a number "mid", 
        // for all i, in arr[i], arr[i] - n*x = mid || arr[i] + n*x = mid;
        for(int num : arr){
            int n = (mid - num)/x;
            ans += Math.abs(n);
        }
        return ans;
    }
}
// logic : nums[i ] se mid tk ka jo difference h , divide by x jitna addition yaa substraction use krnzna hoga

// // key is: agr 4 ko 10 bna skte hai, to 10 ko 4 bhi bna skte hai. to agr poori grid ko grid[i][j] for any 1 value of i,j bna skte hai, then for every value of i,j bhi bna skte hai
// mid isliye kiya kyonki usi se sbse kam possibility aayegi 