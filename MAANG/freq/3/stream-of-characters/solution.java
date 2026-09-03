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
    StringBuilder sb = new StringBuilder();
    int maxLength = 0;
    public void addNode(String word){
      Node curr = root;
      for(char c : word.toCharArray()){
        int index = c-'a';
        if(curr.children[index]==null) curr.children[index] = new Node();
        curr = curr.children[index];
      }
      curr.isEndOfWord = true;
    }
    public boolean findNode(String word){
      Node curr = root;
      int ran=0;
      for(int i = word.length()-1; i>=0; i--){
        ran++;
        char c= word.charAt(i);
        int index = c-'a';
        if(curr.children[index]==null) return false; // iske aage koi valid path nhi hai, no match
        curr = curr.children[index];
        if(curr.isEndOfWord) return true; // mtlb query ka koi suffix match hogya humare given set me se
      }
      return false; // query string khtm hogya, but koi bhi isEndOfWord nhi aaya
    }
    
    public StreamChecker(String[] words) {
       for(String word : words){
            addNode((new StringBuilder(word).reverse()).toString());
            maxLength = Math.max(maxLength,word.length());
       }
    }
    
    public boolean query(char letter) {
        if(sb.length()==maxLength) sb.deleteCharAt(0);
        sb.append(letter);
        return findNode(sb.toString());
    }
    // jo bhi query se aa rha hai, that gets appended to the current string
    // we need to tell ki current string ka koi bhi suffix words me hai ya nahi.
}

