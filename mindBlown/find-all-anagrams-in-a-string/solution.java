class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] og = new int[26];
        for(char c : p.toCharArray()) og[c-'a']++;
        
        int i=0;
        int[] found = new int[26];
        List<Integer> ans = new ArrayList<>();
        int total = p.length();
        
        for(int j=0;j<s.length();j++){
          found[s.charAt(j)-'a']++;
          if(j-i+1 > total) found[s.charAt(i++)-'a']--;
          if(j-i+1 == total && isEqual(og,found)) ans.add(i);
        }
        
        return ans;
    }
    public boolean isEqual(int[] a, int[] b){
        for(int i=0;i<26;i++) if(a[i] != b[i]) return false;
        return true;
    }
}