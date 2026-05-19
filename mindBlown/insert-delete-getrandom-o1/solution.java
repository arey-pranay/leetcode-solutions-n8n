class RandomizedSet {
    HashMap<Integer,Integer> hm;
    ArrayList<Integer> al ;
    public RandomizedSet() {
        hm = new HashMap<>();
        al = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if(hm.containsKey(val)) return false;
        hm.put(val,al.size());
        al.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if(!hm.containsKey(val)) return false;

        // removal we will do only from end of array because that is o(1), so we will keep the last element at the toBeDeleted index, and then remove the last element. then update the hashmap accordingly.
        int index = hm.get(val);
        int lastVal = al.get(al.size() - 1);
        al.set(index,lastVal);
        al.remove(al.size()-1);

        hm.put(lastVal,index);
        hm.remove(val);
        
        return true;
    }
    
    public int getRandom() {
        int random = (int)(Math.random() * al.size());
        return al.get(random);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */