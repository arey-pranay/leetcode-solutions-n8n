class WordDictionary {
  class Node{
    Node[] children;
    boolean isEndOfWord;
    Node(){
      this.children = new Node[26];
      this.isEndOfWord = false;
    }
  }

    Node root;
    public WordDictionary() {
        this.root = new Node();
    }
    
    public void addWord(String word) {
        Node curr = root;
        for(char c : word.toCharArray()){
          if(curr.children[c-'a'] == null) curr.children[c-'a'] = new Node();
          curr = curr.children[c-'a'];
        }
        curr.isEndOfWord = true;
    }
    
    public boolean search(String word) {
      return func(word,0,root);
    }

    public boolean func(String word, int start, Node curr){
        for(int i=start;i<word.length();i++){
          char c= word.charAt(i);
          if(c == '.') { for(Node child : curr.children){if(child != null) if(func(word,i+1,child)) return true;} return false;}
          else{if(curr.children[c-'a'] ==null) return false; else curr=curr.children[c-'a'];}
        }
        return curr.isEndOfWord;
    }
}
