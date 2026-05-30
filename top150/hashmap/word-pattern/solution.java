class Solution {
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character,String> hm = new HashMap<>();
        String[] arr = s.split(" ");
        if(pattern.length() != arr.length) return false;
        
        for(int i=0;i<pattern.length();i++){
            char a = pattern.charAt(i);
            String b = arr[i];
            if(!hm.containsKey(a)){
                if(hm.containsValue(b)) return false;
                hm.put(a,b);
            }
            else{
                if(!hm.get(a).equals(b)) return false;
            }
        }
      
        return true;
    }
}