/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    public Node construct(int[][] grid) {
        return func(grid,0,0,grid.length);
    }
    public Node func(int[][] grid,int x , int y, int w){
        int val = grid[x][y];
        boolean bool = val == 1 ? true : false;
        Node root = new Node(bool,true);
        for(int i=x;i<x+w;i++){
            for(int j=y;j<y+w;j++){
                if(grid[i][j]!=val) {
                    root.isLeaf = false;
                    break;
                }
            }
            if(root.isLeaf == false) break;
        }
        if(root.isLeaf) return root;
        root.topLeft = func(grid,x,y,w/2);
        root.topRight = func(grid,x,y+w/2,w/2);
        root.bottomLeft = func(grid,x+w/2,y,w/2);
        root.bottomRight = func(grid,x+w/2,y+w/2,w/2);
        return root;
    }
}
