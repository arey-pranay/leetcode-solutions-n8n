class Solution {
    public String removeDuplicateLetters(String s) {
        int[] freq = new int[26];
        for(int i =0;i<s.length();i++){
            char c= s.charAt(i);
            freq[c-'a']++;
        }
        Stack<Character> st = new Stack<>();
        HashSet<Character> already = new HashSet<>();
        for(int i=0;i<s.length();i++){
            char curr = s.charAt(i);
            freq[curr-'a']--;
            if(already.contains(curr)) continue;
            while(!st.isEmpty() && curr<st.peek() && freq[st.peek()-'a']>0){
                already.remove(st.peek()); st.pop(); 
            }
            st.push(curr); already.add(curr); 
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) sb.append(st.pop());
        return sb.reverse().toString();
    }
}
// 1 2 4 1
// 1 2 3 1
// c
