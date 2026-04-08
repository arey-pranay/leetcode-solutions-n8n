class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
       HashSet<String> bankset = new HashSet<>(wordList);  // o(1) m check k liye hashset
       Queue<String> q = new LinkedList<>();
       q.add(beginWord);
       int level = 1;
       char[] chars = new char[26];
       for(int i =0 ; i<chars.length;i++) chars[i] = (char)('a'+i);
       while(!q.isEmpty()){
            int sz = q.size();
            while(sz-- > 0){
                String word =  q.remove();
                if(word.equals(endWord)) return level; 
                for(int i =0;i<beginWord.length();i++){
                    for(char c : chars){
                        StringBuilder sb = new StringBuilder(word);
                        sb.setCharAt(i,c);
                        String temp = sb.toString();
                        if(bankset.contains(temp)){
                            q.add(temp);
                            bankset.remove(temp);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}