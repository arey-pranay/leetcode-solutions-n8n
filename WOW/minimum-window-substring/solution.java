class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> freq = new HashMap<>();
        for (char i : t.toCharArray()) freq.put(i, freq.getOrDefault(i, 0) + 1);
        int index=-1;
        int minLength = Integer.MAX_VALUE;
        int count = t.length();
        int i =0;
        for(int j=0;j<s.length();j++){
            char c = s.charAt(j);
            if(freq.containsKey(c)){
                if(freq.get(c) > 0) count--;
                freq.put(c,freq.get(c)-1);
            } 
            
                // XIADOBECODEBANC
                //    i      j
                //    2      7
            while(count==0){
// we will increment count only when we realize that we lost a useful character, so now let's increment j again to find that character
              if(j+1 - i < minLength){
                minLength = j+1 -i;
                index = i;
              }
              char firstMatch = s.charAt(i);
              if(freq.containsKey(firstMatch)){
                int newCount = freq.get(firstMatch)+1;
                freq.put(firstMatch, newCount);
                if(newCount > 0) count++;
              }
              i++;
            }
        }
        return index == -1 ? "" : s.substring(index,index+minLength);
    }
}
// ADOBECODEBA
// ADOBECODEBANC
// ABC