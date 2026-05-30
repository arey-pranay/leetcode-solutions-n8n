class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> hm = new HashMap<>();
        for(String str : strs){
            char[] carr =str.toCharArray();
            Arrays.sort(carr);
            String key = new String(carr);
            List<String> temp = hm.getOrDefault(key, new ArrayList<>());
            temp.add(str);
            hm.put(key,temp);
        }
        List<List<String>> ans = new ArrayList<>();
        for(Map.Entry<String,List<String>> e : hm.entrySet()) ans.add(e.getValue());
        return ans;
    }
}