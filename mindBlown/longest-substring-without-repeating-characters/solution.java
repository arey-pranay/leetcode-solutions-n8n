class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        HashMap<Character,Integer> hm = new HashMap<>();
        int start=0;
        int curr=0;
        int max=1;
        int n = s.length();
        while(curr<n){
            char temp = s.charAt(curr);
            if(hm.containsKey(temp) && hm.get(temp) >= start) start = hm.get(temp) + 1;
            hm.put(temp,curr);
            max = Math.max(max,curr-start+1);
            curr++;
        }
        return max;
    }
}
// uske pehle k index uss no. se bhare hue hai jo humare i se chote hai , vo ignore maarne hi na ab kyonki dupliacte aagye ahi uske , isliye ye check hai 