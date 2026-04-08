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
// hum hashset bss isliye bna rhe hai taaki order of 1 m traverse kr ske 
// queue m  humnein 1st word daala begin waala 
// usko end waale se check kiya nahi hai to usko remove kra 
// remove k baad jese hit hai word , tp h k liye a to z se bdl kr wordlist se check kra agr hai usme to queue m daala otherwise nahi 
// ese hi hit k i aur t k liye bhi 
// queue m dalte gaye  jese hi h , i aur t k liye bhi hogya end m aagye to level ++ kiya 

// kyonki level ++ tb hi krna hai jb ek later k checge ki kahani khtm hogyi ho bhale vo koi bhi ho 
// hit se hot , hit se bit yaa hit se his , this is a total level , agr teeno bhi queue m add hogye to bhi level 0 se 1 hi hoga 3 nahi , kyonki vo ek word ka dusre word m change ka level hai smjhee

// queue k  saare elemets k liye ek level hai haa
// ese hi phir har word jo queue m daal rhe hai uske liye krte gaye krte gaye 
// aur jese hi match mila level return .
