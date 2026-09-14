class Solution {
    public boolean isRectangleOverlap(int[] r1, int[] r2) {
        int x1 = r1[0], y1 = r1[1], x2 = r1[2], y2 = r1[3];
        int X1 = r2[0], Y1 = r2[1], X2 = r2[2], Y2 = r2[3];

        return x1<X2 && X1<x2 && y1<Y2 && Y1<y2;

        //1 < 2 of opposite sides
    }

}

//    .
//   .
//  .  
// .   