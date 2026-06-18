class StreamChecker {
    class Node{
        Node[] children;
        boolean isEndOfWord;
        Node(){
            this.children = new Node[26];
            this.isEndOfWord = false;
        }
    }
    Node root = new Node();
    StringBuilder stream = new StringBuilder("");
    int maxLen = 0;
    private void addWord(String word){
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) curr.children[index] = new Node();
            curr = curr.children[index];
        }   curr.isEndOfWord = true;
    }
    public StreamChecker(String[] words) {
        for(String word : words){
            addWord(new StringBuilder(word).reverse().toString()); 
            maxLen = Math.max(maxLen,word.length());
        }      
    }
    public boolean query(char letter) {
        stream.append(letter);
        Node curr = root;
        for(int i=stream.length()-1; i>=0 && stream.length() - i <= maxLen; i--){
            int index = stream.charAt(i)-'a';
            if(curr.children[index] == null) return false;
            curr = curr.children[index];
            if(curr.isEndOfWord) return true;
        }
        return false;
    }
}

//     cd f kl
    
//        root
//      /   \  \
//     d    f   l
//     /         \
//    c           k .
        // a 
        // b 
        // c 
        // d true
        // e 
        // f true
        // g 
        // h
        // i
        // j
        // k
        // l true

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */