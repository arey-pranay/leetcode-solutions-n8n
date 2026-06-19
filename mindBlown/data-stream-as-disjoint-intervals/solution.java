class SummaryRanges {
    TreeSet<Integer> ts = new TreeSet<>();
    public SummaryRanges() {
        ts = new TreeSet<>();
    }
    
    public void addNum(int value) {
        ts.add(value);
    }
    
    public int[][] getIntervals() {
        if(ts.isEmpty()) return new int[0][0];
        List<int[]> list = new ArrayList<>();
        int start = 0;
        int end = 0;
        boolean isFirst = true;
        for(int num : ts){
            if(isFirst){
                start = end = num;
                isFirst = false;
            } 
            else if(num==end+1) end++;
            else {
                list.add(new int[]{start,end});
                start = end = num;
            }
        }
        list.add(new int[]{start,end});
        int[][] ans = new int[list.size()][2];
        int i=0;
        for(int[] temp : list)ans[i++] = temp;
        return ans;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */