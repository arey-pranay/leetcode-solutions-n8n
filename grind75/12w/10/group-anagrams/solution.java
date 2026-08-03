class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
      HashMap<String,List<String>> hm = new HashMap<>();
      for(String s : strs){
        char[] temp = s.toCharArray();
        Arrays.sort(temp);
        String key = new String(temp);// string ka constructor character array leleta hai aur string m convert kr deta hai
        List<String> l = hm.getOrDefault(key,new ArrayList<>()); 
        l.add(s); 
        hm.put(key,l);
      }
      List<List<String>> ans = new ArrayList<>();
      for(List<String> l : hm.values()) ans.add(l);
      return ans;      
            // e.getKey() e.getValue() Map.Entry<String,List<Integer>> e : hm.entrySet()

    }
}