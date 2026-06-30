class RangeFreqQuery {
    HashMap<Integer,ArrayList<Integer>> hm;
    public RangeFreqQuery(int[] arr) {
        this.hm = new HashMap<>();
        for(int i =0;i<arr.length;i++) hm.computeIfAbsent(arr[i],k -> new ArrayList<>()).add(i);
    }
    
    public int query(int left, int right, int value) {
        if(!hm.containsKey(value)) return 0;
        return findBound(hm.get(value),right,true)-findBound(hm.get(value),left,false);
    }  
    private int findBound(ArrayList<Integer> arr, int target, boolean isLowerBound){
        int start = 0; int end = arr.size();
        while(start<end){
            int mid = start + (end-start)/2;
            if(arr.get(mid) < target || (isLowerBound && arr.get(mid) == target)) start = mid+1;
            else end = mid;
        }
        return start;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
 
   // value -> {i1, i2, i3, i4}
    // 12 -> 0,9
    // 33 -> 1,7,11,45,60,90,110,120,234
    // 20 80
    // 4 -> 2
    // 56 -> 3,11
    // 22 -> 4,8
    // 2 -> 5
    // 34 -> 6,10
    
    // freq = (ceilOf(left) - floorOf(right))+ 1;