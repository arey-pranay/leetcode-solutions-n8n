/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int first = 1; 
        int last = n;
        while(first<last){
            int m = first+(last-first)/2;
            if(isBadVersion(m)) {
                last = m;
            }
            else first = m+1 ;
        }
        return first;
    }
}