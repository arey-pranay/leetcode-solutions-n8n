class LFUCache {
    
    int capacity; 
    HashMap <Integer,Integer> map;
    TreeMap <Integer, LinkedHashSet<Integer>> freq;
    HashMap <Integer, Integer> count;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.freq = new TreeMap<>();
        this.count = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        updateCountAndFreq(key);
        return map.get(key);
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key) && map.size()==capacity) delete();
        updateCountAndFreq(key);
        map.put(key,value); 
    }
    
    public void delete(){
        int countOfsetToRemove = freq.entrySet().iterator().next().getKey();
        LinkedHashSet<Integer> setToRemove = freq.get(countOfsetToRemove);
        int keyToRemove = setToRemove.iterator().next();
        setToRemove.remove(keyToRemove);
        if(setToRemove.size() == 0) freq.remove(countOfsetToRemove);
        count.remove(keyToRemove);
        map.remove(keyToRemove);
    }
    
    public void updateCountAndFreq(int key){
        int freqKey = 1;
        if(!count.containsKey(key)) count.put(key,1);
        else{
            int olderCount = count.get(key);
            count.put(key,olderCount+1);
            freq.get(olderCount).remove(key);
            if(freq.get(olderCount).size()==0) freq.remove(olderCount);
            freqKey = olderCount+1;
        }
        updateFreq(freqKey,key);
    }
    
    public void updateFreq(int freqKey, int key){
        LinkedHashSet<Integer> destSet = freq.getOrDefault(freqKey, new LinkedHashSet<>());
        destSet.add(key);
        freq.put(freqKey,destSet);
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);)
 * obj.put(key,value);
 */