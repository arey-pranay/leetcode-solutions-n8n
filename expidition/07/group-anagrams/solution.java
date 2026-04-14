class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String,List<String>> hm = new HashMap<>();
        for(int i=0;i<strs.length;i++){
            char[] cArr = strs[i].toCharArray();
            Arrays.sort(cArr);
            String str = new String(cArr); //naye  string m character array daalte hai to string bann jaati hai
            List<String> l = hm.getOrDefault(str, new ArrayList<>());
            l.add(strs[i]);
            hm.put(str,l);
        }
        
        List<List<String>> res = new ArrayList<>();  
        for(List<String> l : hm.values()) res.add(l);
        return res;
    }
}