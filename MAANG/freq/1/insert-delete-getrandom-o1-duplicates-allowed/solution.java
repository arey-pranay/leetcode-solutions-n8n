class RandomizedCollection {
    List<Integer> arr; // store whatever we have received, order doesnt matter here, but duplicates matter. we'll do O(1) removal from end
    Map<Integer, HashSet<Integer>> hm; // to store the indices of all the numbers, with O(1) retrieval
    public RandomizedCollection() {
        arr = new ArrayList<>();
        hm = new HashMap<>();
    }
    
    public boolean insert(int val) {
        arr.add(val);
        boolean present = hm.containsKey(val);
        
        HashSet<Integer> indices = hm.getOrDefault(val,new HashSet<>());
        indices.add(arr.size()-1);
        hm.put(val,indices);
        
        return !present;
    }
    
    public boolean remove(int val) {
        if(!hm.containsKey(val)) return false;
        HashSet<Integer> indices = hm.get(val);
        int index = indices.iterator().next();
        indices.remove(index);
        if(indices.isEmpty()) hm.remove(val);
        // this index has num to be removed, so put the last num here, and delete last position
        int lastIndex = arr.size()-1;
        int lastNum = arr.get(lastIndex);
        if(index!=lastIndex){
        arr.set(index,lastNum);
        hm.get(lastNum).remove(lastIndex);
        hm.get(lastNum).add(index);
        }
        arr.remove(lastIndex);
        return true;
    }
    
    public int getRandom() {
        int index = (int) (Math.random()*arr.size()); 
        return arr.get(index);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */