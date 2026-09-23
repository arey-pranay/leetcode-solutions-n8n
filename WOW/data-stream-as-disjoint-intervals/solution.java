class SummaryRanges {
    List<int[]> intervals;
    public SummaryRanges() {
        intervals = new ArrayList<>();
    }
    public void addNum(int target) {
        if(intervals.isEmpty()){intervals.add(new int[]{target,target}); return;}
        int low =0, high=intervals.size()-1;
        int mid = low + (high-low)/2;
        while(low <= high){
            mid = low + (high-low)/2;
            int start = intervals.get(mid)[0], end = intervals.get(mid)[1];
            if(target < start) high = mid-1;
            else if(target > end) low = mid+1;
            else return;
        }
        
        int left = low-1, right = low;
        
        boolean mergeLeft = left >= 0 && intervals.get(left)[1] + 1 == target;
        boolean mergeRight = right <= intervals.size()-1 && intervals.get(right)[0] - 1 == target;
        
        if(mergeLeft && mergeRight){
            intervals.get(left)[1] = intervals.get(right)[1];
            intervals.remove(right);
        } 
        else if (mergeLeft) intervals.get(left)[1] = target;
        else if (mergeRight) intervals.get(right)[0] = target;
        else intervals.add(low, new int[]{target,target});
        
        // <1,2,3,4,5,6>
        
        // 12 on 4th position
        
    }
    
    public int[][] getIntervals() {
        return intervals.toArray(new int[0][]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
         //    addNum(312) => 312 313 314 315
        //   [[312,315]]
           // 9 8 5 
        // [9],[8],[5]
    //    getIntervals()
    //   [ 
    //     [1,1]
    //     [3,3]
    //     [4,10]
    //     [100,101]
    //     [200,250]
    // ->
    //     [500,501] low mid high
    //     [700,901] high
    //    ]