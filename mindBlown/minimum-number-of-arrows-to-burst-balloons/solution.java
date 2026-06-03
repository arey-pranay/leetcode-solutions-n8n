class Solution {
    public int findMinArrowShots(int[][] points) {
        return intersect(points);
    }
    public int intersect(int[][] points) {
        Arrays.sort(points,(a,b)->Integer.compare(a[0],b[0]));//substract krenge to integer overflow aajayega na
        int n = points.length;
        int j = 0;
        int start = points[0][0];
        int end = points[0][1];
        int arrow = 1;
        for(int i=1;i<n;i++){
            int[] next = points[i];
            int s = next[0];
            int e = next[1];
            if(s <= end){ //intersection
                start = Math.max(s,start); // max isliye kyonki aage waal
                end = Math.min(e,end);
            } else{
                arrow++;
                start = s;
                end = e;
            }
        }
        return arrow;
    }
}
// . .  .  . ( ) . . 
// . . . (      ) .  .
// (    ) . . (  ) . .
// . (        ) . . . .
// . . . .( ) . . . . .

// . .  .  . . . 
// . . .  .  .
// (    ) (       ). . . .
//          . . . .
// . . . . . . . . .
   