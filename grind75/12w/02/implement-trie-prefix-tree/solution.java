class Node{
    Node[] children;
    boolean isEof;
    Node(){
        this.children = new Node[26]; // hr ek children ek node hoga
        this.isEof = false;
    }
}
class Trie {
    Node root;
    public Trie() {
        root = new Node();    
    }
    
    public void insert(String word) {
        Node temp = root;
        for(char c : word.toCharArray()){
            if(temp.children[c-'a'] == null) temp.children[c-'a'] = new Node();
            temp = temp.children[c-'a']; // children ki array p jaa rhe hai
        }
        temp.isEof = true;        
    }
    public boolean search(String word) {
        Node temp = root;
        for(char c : word.toCharArray()){
            if(temp.children[c-'a'] == null) return false;
            temp = temp.children[c-'a'];
        }
        return temp.isEof;  
    }
    
    public boolean startsWith(String prefix) {
        Node temp = root;
        for(char c : prefix.toCharArray()){
            if(temp.children[c-'a'] == null) return false;
            temp = temp.children[c-'a'];
        }
        return true;  
    }
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */