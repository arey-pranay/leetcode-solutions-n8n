class Solution {
    static int MOD = 1_000_000_007;
    public int rectangleArea(int[][] rects) {
        long ans = 0;
        int m = rects.length, n = rects[0].length;
        Arrays.sort(rects, (a,b)->Integer.compare(a[0],b[0]));
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        List<Integer> ys = new ArrayList<>();
        for(int[] rect : rects){ys.add(rect[1]);ys.add(rect[3]);}
        Collections.sort(ys);
        for(int i=0; i<ys.size()-1; i++){ // we dont want to traverse every row otherwise 0 -> 1e9 would give 1e9 iterations, but here only 2 iterations.
          int xFilled = -1;
          long width = 0;
          int y = ys.get(i);
          for(int[] rect : rects){
            if(rect[1] <= y && y < rect[3]) {
              int left = Math.max(rect[0],xFilled), right = rect[2];
              if(right <= left) continue;
              width += right - left;
              xFilled = right;
            }
          }
          long height = ys.get(i+1)-y; // agr kuch bhi change hota in dono ke beech me, to wo ys ka agla element hoga. because we are storing starting as well as ending of all blocks, so 2 neighbours y ke eech me every width is valid for the entire range
          ans = (ans+ (width*height)%MOD)%MOD;
        }
        return (int)ans%MOD;
    }
}









// 1:[0,0,2,2]  -> 0,2
// 2:[1,0,2,3]  -> 0,3
// 3:[1,0,3,1]  -> 0,1

// y = 0 to 1 => 3
// y = 1 to 2 => 2
// y = 2 to 3 => 1

// => 3 + 2 + 1 = 6

// if(y > rect[1] && y+1 < rect[3]) ans++;

// sort based on x coordinate, ascending.
// maintain how much we have already filled, to prevent overlaps
// for each y row, and each rectangle, add 
//   if(y > rect[1] && y+1 < rect[3] && nonOverlap) ans+ rect[2]-rect[0];
