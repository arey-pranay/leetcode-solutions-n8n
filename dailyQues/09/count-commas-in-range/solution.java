class Solution {
    public int countCommas(int n) {
       if(n<1000) return 0;
        // 4005
        int zeroes = (int) Math.log10(n); //3
        int exp = (int) Math.pow(10,zeroes); //1000
        int face = n/exp; // 4
        int a = face*exp; // 4000
        
        return (n-a) + (a - 1000) + 1;    
        
    //    (1004 - 1000) + (1000 - 1000)
       
    //    (4009 - 4000) + (4000 - 1000)
       
    //    (400010 - 40000) + (40000 - 1000)
    }
}