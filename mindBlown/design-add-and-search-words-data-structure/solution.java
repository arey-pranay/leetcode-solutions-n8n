class WordDictionary {
    Node root;
    public WordDictionary() {
        this.root = new Node();
    }
    
    public void addWord(String word) {
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index]== null) curr.children[index] = new Node();
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        return func(word,0,root);
    }
    public boolean func(String word, int i, Node curr){
        if(curr == null) return false;
        if(i==word.length()) return curr.isEndOfWord;
          
        char c = word.charAt(i);
        if(c=='.'){
            for(int j=0;j<26;j++){
                if(func(word, i+1, curr.children[j])) return true;
            }
            return false;
        }else{
            int index = c-'a';
            return func(word, i+1, curr.children[index]);
        }
    }
    class Node{
        Node[] children;
        boolean isEndOfWord;
        Node(){
            this.children = new Node[26];
            this.isEndOfWord = false;
        }
    }
}


//     ..bxyz
//      .bxyz.
//      bxyz..
//      b.x.y
// 


/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */