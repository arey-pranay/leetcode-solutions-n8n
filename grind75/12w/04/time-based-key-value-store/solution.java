class TimeMap {
    HashMap<String, TreeMap<Integer,String>> hm;
    public TimeMap() {
        this.hm = new HashMap<>();
    }
    public void set(String key, String value, int timestamp) {
        TreeMap<Integer,String> tm = hm.getOrDefault(key,new TreeMap<>());
        tm.put(timestamp,value);
        hm.put(key,tm);
    }
    public String get(String key, int timestamp) {
        if(!hm.containsKey(key)) return "";
        TreeMap<Integer,String> tm = hm.get(key);        
        if(tm.containsKey(timestamp)) return tm.get(timestamp);
        if(tm.lowerKey(timestamp) == null) return "";
        return tm.get(tm.lowerKey(timestamp));
    }
}
