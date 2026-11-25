class Solution {
    class TrieNode{
        TrieNode[] children = new TrieNode[26];
        int index = -1;
        List<Integer> okIndex = new ArrayList<>(); //konse indices wale string ke iske niche wale chars palindrome hai
    }
    TrieNode trie = new TrieNode();
    HashSet<String> done = new HashSet<>();
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> palindromePairs(String[] words) {
        // kisi bhi pair me, ek first-last match to hoga hi and agr dono even length ke hai, to dusra first-last match bhi hoga
        // otherwise no match
        // so let's try trie..
              
        int n = words.length;
        
        // unmatched characters agr 0 hai, ya palindrome hai, then okIndex;
        for(int i=0;i<n;i++) insert(trie,words[i],i); //har word reverse direction me insert, and find the okIndices
        for(int i=0;i<n;i++) search(trie,words[i],i); //forward direction me har word ke valid partners search
        
        return ans;
    }
    private void insert(TrieNode root, String word, int index){
        TrieNode curr = root;
        int n = word.length();
        for(int i=n-1;i>=0;i--){
            if(isPalindrome(word, 0, i)) curr.okIndex.add(index);
            int c = word.charAt(i)-'a';
            if(curr.children[c]==null) curr.children[c] = new TrieNode();
            curr = curr.children[c];
        }
        curr.index = index;
        curr.okIndex.add(index);
    }
    //tum kuch smjh pa rhi ho, ek baar smjhao to zara
    private void search(TrieNode root, String word, int index){
        TrieNode curr = root;
        int n = word.length();
        for(int i=0;i<n;i++){
            if(curr.index != -1 && curr.index != index && isPalindrome(word,i,n-1)) ans.add(Arrays.asList(index,curr.index));
            int c = word.charAt(i)-'a';
            if(curr.children[c]==null) return;
            curr = curr.children[c];
        }
        for(int ok : curr.okIndex) if(ok != index) ans.add(Arrays.asList(index,ok));


    }

    private boolean isPalindrome(String s , int i , int j){
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)) return false;
            i++;j--;
        }
        return true;
    }
} 


            //       -
            // a   d   l   s.
            // b   c   l   s
            // c   b   s.  s 4
            // d.   a.     l
            //             l
                        
                    
            //     -
            // d   a
            // c   b
            // b   c
            // a   d