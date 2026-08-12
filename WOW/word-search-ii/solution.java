class Solution {
    class Node{
      Node[] children;
      String word;
      Node(){
        this.children = new Node[26];
        this.word = null;
      }
    }
    Node root = new Node();
    public void addToTrie(String word){
    Node curr = root;
    for(char c : word.toCharArray()){
          int index = c-'a';
          if(curr.children[index] == null) curr.children[index] = new Node();
          curr = curr.children[index];
      }
      curr.word = word;
    }
    
    List<String> ans = new ArrayList<>();
    int[] neighs = new int[]{-1,0,1,0,-1};
    boolean[][] vis;
    int m;
    int n;
    public List<String> findWords(char[][] board, String[] words) {
      m = board.length;
      n = board[0].length;
      for(String word : words) addToTrie(word);
      for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            vis = new boolean[m][n];
            func(root,i,j,board);
        }
      }
      return ans;
    }

    public void func(Node curr, int x, int y,char[][] board){
      char c = board[x][y];
      if(vis[x][y] || curr.children[c-'a'] == null) return;
      curr = curr.children[c-'a'];
      if(curr.word != null){ans.add(curr.word); curr.word=null;}
      vis[x][y] = true;
      for(int i=0;i<4;i++){
        int X = x + neighs[i];
        int Y = y + neighs[i+1];
        if(X<0 || Y<0 || X>=m || Y>=n || vis[X][Y]) continue;
        func(curr, X,Y, board);
      }
      vis[x][y] = false;
    }
}
  // board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
  // words = ["oath","pea","eat","rain"]



  //                -
  //           /  |   |   \
  //           o   p   e   r
  //           /   |   |   |
  //         a     e   a   a
  //         ..  ..  ..  ..
        
  