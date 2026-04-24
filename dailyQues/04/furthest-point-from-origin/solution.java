class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int blanks = 0; int moved = 0;
        for(char c : moves.toCharArray()){
            if(c == 'L') moved++;
            else if (c == 'R') moved--;
            else blanks++;
        }
        return Math.abs(moved) + blanks;
    }
}