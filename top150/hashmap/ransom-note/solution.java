class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        //we can do this also via using frequency array.
        HashMap<Character,Integer> available = new HashMap<>();
        for(char c : magazine.toCharArray()) available.put(c,available.getOrDefault(c,0)+1);
        for(char c : ransomNote.toCharArray()){
            int rem = available.getOrDefault(c,0)-1;
            if(rem < 0) return false;
            available.put(c,rem);
        }
        return true;
    }
}