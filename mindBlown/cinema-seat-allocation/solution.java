class Solution {
    
    // best solution for no OLE and TLE. 
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
        for(int[] pair : reservedSeats) map.computeIfAbsent((pair[0]), k -> new HashSet<>()).add(pair[1]);
        int groups = 2*n;
        for(int i : map.keySet()){
            HashSet<Integer> curr = map.get(i);
            
            boolean one = !(curr.contains(2) || curr.contains(3) || curr.contains(4) || curr.contains(5));
            boolean two = !(curr.contains(4) || curr.contains(5) || curr.contains(6) || curr.contains(7)); 
            boolean three =!(curr.contains(6) || curr.contains(7) || curr.contains(8) || curr.contains(9)); 
            int count = 0;
            if(one && three) count= 2;
            else if ( one || two || three) count = 1;
             
            groups = groups-(2-count);
        }
        return groups;
    }
}