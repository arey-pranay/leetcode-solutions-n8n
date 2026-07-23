class Solution {
    HashSet<Integer> hs = new HashSet<>();
    int[] arr;
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if(n<3) return n;
        int pow2 = (int) Math.floor(Math.log(n)/Math.log(2));
        return (int)Math.pow(2,pow2+1);
    }
    // 1100
    // public void func(int i, int taken, int curr){
    //     if(taken==3){hs.add(curr); return;}
    //     if(i==arr.length) return;
    //     func(i,taken+1,curr^arr[i]);
    //     func(i+1,taken+1,curr^arr[i]);
    //     func(i+1,taken,curr);
    // }
}
