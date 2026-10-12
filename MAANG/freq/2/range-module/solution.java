class RangeModule {
    // +   10 -> 19
    // -   14 -> 15
    // ?   10 -> 13 => true
    // ?   13 -> 14 => false
    // ?   16 -> 16 => true 
    
    TreeMap<Integer,Integer> tm; // sorted order me start -> end
    public RangeModule() {
        this.tm = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        Map.Entry<Integer,Integer> e = tm.floorEntry(left);
        if(right <= left) return;
        if(e!=null && e.getValue() >= left){ // merge left side interval
            tm.remove(e.getKey());
            left = e.getKey();
            right = Math.max(right,e.getValue()); // update end value to merge correctly
        }
        
        while(true){    // merge right side interval
            Map.Entry<Integer,Integer> nextEntry = tm.ceilingEntry(left);
            if(nextEntry==null || nextEntry.getKey() > right) break;
            tm.remove(nextEntry.getKey()); // remove next entry
            right = Math.max(right,nextEntry.getValue()); // update end value to merge correctly
        }
        
        tm.put(left,right);
    }
    
    public boolean queryRange(int left, int right) {
        Map.Entry<Integer,Integer> e = tm.floorEntry(left);
        return e != null && e.getKey() <= left && e.getValue() >= right;
    }
    
    public void removeRange(int left, int right) {
        Map.Entry<Integer,Integer> e = tm.floorEntry(left);
        if(e!=null && e.getValue() >= left){ // merge left side interval
            tm.put(e.getKey(),left);
            if(e.getValue()>right) tm.put(right,e.getValue()); // update end value to merge correctly
        }
        while(true){    // merge right side interval
            Map.Entry<Integer,Integer> nextEntry = tm.ceilingEntry(left);
            if(nextEntry==null || right < nextEntry.getKey()) break;
            tm.remove(nextEntry.getKey()); // remove next entry
            if(nextEntry.getValue()>right){tm.put(right,nextEntry.getValue()); break;}
        }
    }
}
// 10 20
// 25 35
// 40 50
// 55 100

// remove 19 60

// ["r","add","rem","rem","add","rem","add","que","que","que"]
// [[],[6,8],[7,8],[8,9],[8,9],[1,3],[1,8],[2,4],[2,9],[4,6]]

// Output
// [null,null,null,null,null,null,null,true,false,true]
// Expected
// [null,null,null,null,null,null,null,true,true,true]

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */