class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;
        HashMap<Character,Character> hm = new HashMap<>();
        
        for(int i=0;i<s.length();i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(!hm.containsKey(a)){
                if(hm.containsValue(b)) return false;
                hm.put(a,b);
            }
            else{
                if(hm.get(a)!=b) return false;
            }
        }
        // egeg
        // laal
        
        return true;
    }
}