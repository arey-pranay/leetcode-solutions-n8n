class Solution {
    int[][] memo;
    public int superEggDrop(int eggs, int floor) {
        memo = new int[eggs+1][floor+1];
        for(int[] temp : memo) Arrays.fill(temp,-1);
        return func(eggs,floor);
    }
    public int func(int eggs, int floor) {
     if(floor==0 || floor==1 || eggs==1) return floor;
     if(memo[eggs][floor] != -1) return memo[eggs][floor];
        int l=1, r=floor;
        int moves = floor;
        while(l<=r){
            int mid = l + (r-l)/2;
            int broke = func(eggs-1, mid-1);
            int notBroke = func(eggs, floor-mid);
            
            if(broke > notBroke) r = mid-1; //tootne pe zyada mehnt lgi hai, niche jayege
            else l = mid+1;
            
            moves = Math.min(moves,1 + Math.max(broke,notBroke));
        }
        return memo[eggs][floor] = moves;
    }
}
