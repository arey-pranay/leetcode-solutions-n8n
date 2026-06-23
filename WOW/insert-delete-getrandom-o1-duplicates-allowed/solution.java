class RandomizedCollection {
    ArrayList<Integer> arr;
    HashMap<Integer,HashSet<Integer>> hm;
    public RandomizedCollection() {
        arr  = new ArrayList<>();
        hm =  new HashMap<>();
    }
    
    public boolean insert(int val) {
        boolean flag = true;
        if(hm.containsKey(val)) flag = false;
        HashSet<Integer> temp = hm.getOrDefault(val,new HashSet<>());
        temp.add(arr.size());
        hm.put(val, temp);
        arr.add(val);
        return flag;
    }
    
    public boolean remove(int val) {
        if(!hm.containsKey(val)) return false;
        // array me se O(1) removal bs last index pe hota hai, isliye jo bhi udaana hai usko last pe exchange krdo
        // jo udaana hai uskka index kahan se laaye? isliye ek hashmap me sb values ka index store krlo
        HashSet<Integer> indices = hm.get(val);
        int remIndex = indices.iterator().next(); // isse first waali aajati hai 
        int lastIndex = arr.size()-1;
        int lastVal = arr.get(lastIndex);
        // . . 7  .. . . .. 12
        
        // 7 -> 2,
        // 12 -> 3,5,4
        hm.get(val).remove(remIndex);
        
        if(remIndex != lastIndex){
            arr.set(remIndex,lastVal);
            hm.get(lastVal).remove(lastIndex);
            hm.get(lastVal).add(remIndex);
        }
        arr.remove(lastIndex);
       
        
        if(hm.get(val).isEmpty()) hm.remove(val);
        return true;
    }
    
    public int getRandom() {
        //kyonki hashmap se random ni le paayenge isliey arraylist banana padha
        int random = (int) (Math.random()*arr.size());
        return arr.get(random);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */