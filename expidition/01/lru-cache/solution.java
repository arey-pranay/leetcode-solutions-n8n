class LRUCache {
    HashMap <Integer,Integer> map ;
    int capacity ; 
    LinkedHashSet<Integer> set;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.set = new LinkedHashSet<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            set.remove(key);
            set.add(key);
            return map.get(key);
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))set.remove(key);
        else if(capacity==map.size()){
            int temp = set.iterator().next();
            set.remove(temp);
            map.remove(temp);
        }
        set.add(key);
        map.put(key,value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */