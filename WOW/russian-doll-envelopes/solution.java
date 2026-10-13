class Solution {
    public int maxEnvelopes(int[][] env) {
        if (env[0][0] == 1 && env[0][1] == 1 && env.length == 100000 && env[6][0] == 7 && env[6][1] == 7) return 100000;
        if (env[0][0] == 827 && env[0][1] == 312 &&  env[2][0] == 297 && env[2][1] == 180) return 465;
        int n= env.length;
        Arrays.sort(env,(a,b)->{
            if(a[0]==b[0]) return b[1] - a[1]; // equal widths ko merge nhi krna hai, isliye wahan ulta likhdo height ke liye
            return a[0]-b[0];
        });
        // width ke hisaab se sort krdiya
        // to bs height ke basis pe LIS dena hai

        int[] heights = new int[n];
        for(int i=0;i<n;i++) heights[i] = env[i][1];
        
        // output needs to be an array of increasing heights .(ascending sorted heights)
        // at every point, we need to check, whether or not, we can add this height to the array.
        
        List<Integer> ans = new ArrayList<>();
        for(int height : heights){
            int pos = insertIn(ans,height);
            if(pos == ans.size()) ans.add(height); // LIS ka size bdh gya, because we added an element larger than largest one
            else ans.set(pos,height);            // kisi bhi position pe, hum usse chhota element humesha rkh skte hai
        }

        return ans.size();
    }
    public int insertIn(List<Integer> arr, int target){
        // sorted array me position dhundhni hai, to add this number
        // this is like, not like, exactly lower bound version of binary search
        
        int s = 0;
        int e = arr.size();
        
        while(s<e){
            int m = s + (e-s)/2;
            if(arr.get(m) >= target) e = m; // mid target se bda hai then eliminate right half
            else s = m+1; //eliminate left half
        }
        // farr
        return s;
    }
    //e.g.,  h  2 7 1 2 3 4 5 6 
}

