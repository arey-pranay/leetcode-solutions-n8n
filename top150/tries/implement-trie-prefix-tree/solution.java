class Trie {
    Node root;
    public Trie() {
        this.root = new Node();
    }
    
    public void insert(String word) {
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) curr.children[index] = new Node();
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        Node curr = root;
        for(char c : word.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) return false;
            curr = curr.children[index];
        }
        return curr.isEndOfWord;
    }
    
    public boolean startsWith(String prefix) {
        Node curr = root;
        for(char c : prefix.toCharArray()){
            int index = c-'a';
            if(curr.children[index] == null) return false;
            curr = curr.children[index];
        }
        return true;
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

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */