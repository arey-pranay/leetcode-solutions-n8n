class Solution {
    public String convert(String s, int numRows) { 
        int x = numRows + numRows -2; // difference between indices starting a new column
        if(x==0) return s;
        
        StringBuilder sb = new StringBuilder();
        int[] y = new int[2];
        
        for(int i=0;i<numRows;i++){
            y[0] = x - 2*i;
            y[1] = x - y[0];
            if(y[1] == 0) y[1] = x; if(y[0] == 0) y[0] = x; // first and last row have gaps of x.
            int j = i;
            int temp = 0;
            while(j<s.length()){
                sb.append(s.charAt(j));
                j += y[temp];
                temp = temp ^ 1;
            }
        }
        
        return sb.toString();
    }
}

// +8 ...
// +6 +2
// +4 +4
// +2 +6
// +8 ..

// 0 - - - 8
// 1 - - 7 9 
// 2 - 6 - 10
// 3 5 - - 11
// 4 - - - 12

// +6 ...
// +4 +2...
// +2 +4....
// +6 .....


// +4....
// +2 +2 ...
// +4....

// 0 - 4 - 8
// 1 3 5 7 9
// 2 - 6 - 10


// 0 - - 6 -  -  12
// 1 - 5 7 -  11 13
// 2 4 - 8 10 -  -
// 3 - - 9 -  -  -

// 0 2 4 6 8  10 12
// 1 3 5 7 9  11 13
// 2 4 6 8 10 12 14
// 3 5 7 9 11 13 15