class Solution {
    public boolean isAnagram(String s, String t) {
        //we can do this also via using frequency array.[26]
        HashMap<Character,Integer> available = new HashMap<>();
        for(char c : s.toCharArray()) available.put(c,available.getOrDefault(c,0)+1);
        if(s.length() != t.length()) return false;
        for(char c : t.toCharArray()){
            if(!available.containsKey(c)) return false;
            int rem = available.get(c);
            if(rem==1) available.remove(c);
            else available.put(c,rem-1);
        }
        return true;
    }
}
// "aacc" 
// "ccac" a:2 c:0